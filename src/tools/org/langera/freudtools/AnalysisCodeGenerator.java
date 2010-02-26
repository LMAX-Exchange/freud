package org.langera.freudtools;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.langera.freud.Builder;
import org.langera.freud.util.io.IoUtil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
public final class AnalysisCodeGenerator
{
    private static final File JAVA_SRC_DIR = new File("src/java");
    private static final File GENERATED_SRC_DIR = new File("src/generated");
    private static final Pattern METHOD_DECL_REGEX = Pattern.compile("\\{\\s*([^\\}]+)", Pattern.DOTALL);
    private static final Pattern IMPORT_REGEX = Pattern.compile("import\\s+([^;]+)");
    private final File defFile;
    private Class analysedClass;
    private String packageName;
    private Set<String> importSet;
    private Set<Class> dslSet;
    private List<AdapterDef> adapterDefList;
    private Map<Class, List<AdapterDef>> adapterDefByTypeMap;
    private Map<Class, Class> builderClassByDslMap;
    private Map<Class, DslMethodDecl[]> methodDeclByDslMap;
    private String analysisClassName;

    public AnalysisCodeGenerator(File defFile)
    {
        this.defFile = defFile;
    }

    public static void main(String[] args)
    {
        try
        {
            if (args.length == 0)
            {
                usage();
            }
            for (int i = 0; i < args.length; i++)
            {
                AnalysisCodeGenerator generator = init(args[i]);
                generator.parseDefinition();
                generator.createAnalysis();
                generator.createAdapters();
            }
            System.exit(0);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private void parseDefinition()
            throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException
    {
        final Properties def = new Properties();
        def.load(new BufferedReader(new FileReader(defFile)));
        packageName = def.getProperty("package");
        final String importsString = def.getProperty("imports");
        final String[] imports = (importsString == null) ? null : importsString.split("\\s*,\\s*");
        importSet = new HashSet<String>();
        if (imports != null)
        {
            for (int i = 0; i < imports.length; i++)
            {
                importSet.add(imports[i]);
            }
        }
        dslSet = new HashSet<Class>();
        adapterDefList = new LinkedList<AdapterDef>();
        adapterDefByTypeMap = new HashMap<Class, List<AdapterDef>>();
        builderClassByDslMap = new HashMap<Class, Class>();
        methodDeclByDslMap = new HashMap<Class, DslMethodDecl[]>();
        parseChildrenDefinition(def, null, null, null, defFile.getParentFile());
        generateAdapterDefChains();
        parseDslSource();
        analysisClassName = def.getProperty("analysisName", analysedClass.getSimpleName() + "Analysis");
    }

    private void parseChildrenDefinition(final Properties def, final Class parentType,
                                         final String adapterCode, final String[] additionalAdapterImports, final File defFileDir)
            throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException
    {
        Class builderClass = Class.forName(def.getProperty("builder"));
        final Builder builder = (Builder) builderClass.newInstance();
        final Class type = builder.getType();
        final Class dsl = builderClass.getInterfaces()[0];
        dslSet.add(dsl);
        if (parentType == null)
        {
            analysedClass = type;
        }
        else
        {
            adapterDefList.add(new AdapterDef(adapterCode, additionalAdapterImports, parentType, type, packageName));
        }
        builderClassByDslMap.put(dsl, builderClass);
        final String childrenStr = def.getProperty("children");
        if (childrenStr != null)
        {
            final String[] children = childrenStr.split(",");
            for (int i = 0; i < children.length; i++)
            {
                String child = children[i].trim();
                final Properties childDef = new Properties();
                final String childDefFilename = def.getProperty(child + ".def", child + ".properties");                
                final File childDefFile = new File(defFileDir, childDefFilename);
                childDef.load(new BufferedReader(new FileReader(childDefFile)));
                final String childAdapterCode = def.getProperty(child + ".adapter");
                final String childAdapterAdditionalImportsString = def.getProperty(child + ".adapter.imports");
                final String[] childAdapterAdditionalImports = (childAdapterAdditionalImportsString == null) ? null :
                        childAdapterAdditionalImportsString.split(",");
                parseChildrenDefinition(
                        childDef, type, childAdapterCode, childAdapterAdditionalImports, childDefFile.getParentFile());
            }
        }
    }


    private void parseDslSource() throws IOException
    {
        for (Class dsl : dslSet)
        {
            final String name = dsl.getName().replace('.', File.separatorChar) + ".java";
            String dslSource = IoUtil.readFully(new BufferedReader(new FileReader(new File(JAVA_SRC_DIR, name))));
            final Matcher methodDeclMatcher = METHOD_DECL_REGEX.matcher(dslSource);
            if (methodDeclMatcher.find())
            {
                String methodsDecl = methodDeclMatcher.group(1);
                methodDeclByDslMap.put(dsl, createDslMethodDeclarations(methodsDecl.split("\\s*;\\s*")));
            }
            final Matcher importMatcher = IMPORT_REGEX.matcher(dslSource);
            while (importMatcher.find())
            {
                importSet.add(importMatcher.group(1));
            }
        }
    }

    private DslMethodDecl[] createDslMethodDeclarations(String[] signatures)
    {
        DslMethodDecl[] dslMethodDecls = new DslMethodDecl[signatures.length];
        for (int i = 0; i < signatures.length; i++)
        {
            dslMethodDecls[i] = new DslMethodDecl(signatures[i].trim());
        }
        return dslMethodDecls;
    }

    private void generateAdapterDefChains()
    {
        for (AdapterDef adapterDef : adapterDefList)
        {
            List<AdapterDef> specificTypeAdapterDefList = adapterDefByTypeMap.get(adapterDef.getType());
            if (specificTypeAdapterDefList == null)
            {
                specificTypeAdapterDefList = new LinkedList<AdapterDef>();
                adapterDefByTypeMap.put(adapterDef.getType(), specificTypeAdapterDefList);
            }
            specificTypeAdapterDefList.add(adapterDef);
        }
        List<AdapterChainDef> toAdd = new ArrayList<AdapterChainDef>();
        do
        {
            adapterDefList.addAll(toAdd);
            toAdd.clear();
            addNewAdapterChains(toAdd);
        }
        while (!toAdd.isEmpty());
    }

    private void addNewAdapterChains(List<AdapterChainDef> toAdd)
    {
        for (AdapterDef adapterDef : adapterDefList)
        {
            final List<AdapterDef> typeAdapterDefList = adapterDefByTypeMap.get(adapterDef.getType());
            final List<AdapterDef> nestedTypeAdapterDefList = adapterDefByTypeMap.get(adapterDef.getNestedType());
            possiblyAddNewAdapterChain(toAdd, adapterDef, typeAdapterDefList, nestedTypeAdapterDefList);
        }
    }

    private void possiblyAddNewAdapterChain(List<AdapterChainDef> toAdd, AdapterDef adapterDef, List<AdapterDef> typeAdapterDefList, List<AdapterDef> nestedTypeAdapterDefList)
    {
        if (nestedTypeAdapterDefList != null)
        {
            for (AdapterDef nestedTypeAdapterDef : nestedTypeAdapterDefList)
            {
                if (shouldAddChainAdapter(adapterDef.getType(), nestedTypeAdapterDef.getNestedType()))
                {
                    final AdapterChainDef adapterChainDef;
                    if (adapterDef instanceof AdapterChainDef)
                    {
                        adapterChainDef = new AdapterChainDef(((AdapterChainDef) adapterDef).getChain(), nestedTypeAdapterDef.getNestedType());
                    }
                    else
                    {
                        adapterChainDef = new AdapterChainDef(adapterDef.getType(), adapterDef.getNestedType(), nestedTypeAdapterDef.getNestedType());
                    }
                    typeAdapterDefList.add(adapterChainDef);
                    toAdd.add(adapterChainDef);
                }
            }
        }
    }

    private boolean shouldAddChainAdapter(Class type, Class nestedType)
    {
        List<AdapterDef> specificTypeAdapterDefList = adapterDefByTypeMap.get(type);
        for (AdapterDef adapterDef : specificTypeAdapterDefList)
        {
            if (nestedType == adapterDef.getNestedType())
            {
                return false;
            }
        }
        return true;
    }

    private void createAnalysis() throws Exception
    {
        final Template analysisTemplate = Velocity.getTemplate("src/templates/AnalysisClassTemplate.vm");
        final VelocityContext context = new VelocityContext();
        context.put("context", this);
        final File dir = new File(GENERATED_SRC_DIR.getAbsolutePath() + File.separatorChar + packageName.replace('.', File.separatorChar));
        if (dir.exists() || dir.mkdirs())
        {
            final File file = new File(dir, analysisClassName + ".java");
            final BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            try
            {
                analysisTemplate.merge(context, writer);
                System.out.println("Generated analysis class [" + file.getAbsolutePath() + "]");
            }
            finally
            {
                writer.close();
            }
        }
        else
        {
            throw new IOException("Failed to create dir [" + dir.getAbsolutePath() + "]");
        }

    }

    private void createAdapters() throws Exception
    {
        final Template adapterTemplate = Velocity.getTemplate("src/templates/AnalysisAdapterClassTemplate.vm");
        for (AdapterDef adapterDef : adapterDefList)
        {
            if (adapterDef.getAdapterCode() != null)
            {
                final File dir = new File(GENERATED_SRC_DIR.getAbsolutePath() + File.separatorChar +
                                                adapterDef.getPackageName().replace('.', File.separatorChar));
                final String classFileName = adapterDef.getName() + ".java";
                final VelocityContext context = new VelocityContext();
                context.put("context", this);
                context.put("adapter", adapterDef);

                if (dir.exists() || dir.mkdirs())
                {
                    final File file = new File(dir, classFileName);
                    final BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                    try
                    {
                        adapterTemplate.merge(context, writer);
                        System.out.println("Generated adapter class [" + file.getAbsolutePath() + "]");                        
                    }
                    finally
                    {
                        writer.close();
                    }
                }
                else
                {
                    throw new IOException("Failed to create dir [" + dir.getAbsolutePath() + "]");
                }
            }
        }
    }

    private static AnalysisCodeGenerator init(String defFilename) throws Exception
    {
        final File defFile = new File(defFilename);
        verifyExist(defFile);
        AnalysisCodeGenerator generator = new AnalysisCodeGenerator(defFile);
        Velocity.init();
        return generator;

    }

    public Class getAnalysedClass()
    {
        return analysedClass;
    }

    public String getPackageName()
    {
        return packageName;
    }

    public Set<Class> getDslSet()
    {
        return dslSet;
    }

    public Set<String> getImports()
    {
        return importSet;
    }

    public List<AdapterDef> getAdapterDefList()
    {
        return adapterDefList;
    }

    public Map<Class, List<AdapterDef>> getAdapterDefByTypeMap()
    {
        return adapterDefByTypeMap;
    }

    public Map<Class, Class> getBuilderClassByDslMap()
    {
        return builderClassByDslMap;
    }

    public Map<Class, DslMethodDecl[]> getMethodDeclByDslMap()
    {
        return methodDeclByDslMap;
    }

    public String getAnalysisClassName()
    {
        return analysisClassName;
    }

    public String getDateString()
    {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    private static void verifyExist(File file)
    {
        if (!file.exists())
        {
            System.err.println("Definition file [" + file.getAbsolutePath() + "]  does not exist.");
            System.exit(-1);
        }
    }

    private static void usage()
    {
        System.err.println("Usage: java org.langera.freudtools.AnalysisCodeGenerator <def file>*");
        System.exit(-1);
    }

    public class AdapterDef
    {
        private final String adapterCode;
        private final Class type;
        private final Class nestedType;
        private final String[] additionalImports;
        private final String packageName;

        private AdapterDef(final String adapterCode, final String[] additionalImports,
                           final Class type, final Class nestedType, final String packageName)
        {
            this.adapterCode = adapterCode;
            this.type = type;
            this.nestedType = nestedType;
            this.additionalImports = additionalImports;
            this.packageName = packageName;
        }

        public final String getAdapterCode()
        {
            return adapterCode;
        }

        public final Class getType()
        {
            return type;
        }

        public final Class getNestedType()
        {
            return nestedType;
        }

        public String getPackageName()
        {
            return packageName;
        }

        public String[] getAdditionalImports()
        {
            return additionalImports;
        }

        public final String getName()
        {
            return type.getSimpleName() + "To" + nestedType.getSimpleName() + "AnalysisAdapter";
        }

        public String getInstantiationCode()
        {
            return getName() + ".getInstance()";
        }
    }

    public final class AdapterChainDef extends AdapterDef
    {
        private final Class[] chain;

        private AdapterChainDef(Class... chain)
        {
            super(null, null, chain[0], chain[chain.length - 1], null);
            this.chain = chain;
        }

        public AdapterChainDef(Class[] chain, Class nestedType)
        {
            super(null, null, chain[0], nestedType, null);
            this.chain = new Class[chain.length + 1];
            System.arraycopy(chain, 0, this.chain, 0, chain.length);
            this.chain[chain.length] = nestedType;
        }

        public final Class[] getChain()
        {
            return chain;
        }

        @Override
        public String getInstantiationCode()
        {
            StringBuilder sb = new StringBuilder("NestedTypeAnalysisAdapterChain.createChain(\n\t\t\t");
            for (int i = 0; i < chain.length - 1; i++)
            {
                sb.append(chain[i].getSimpleName()).append("To").append(chain[i + 1].getSimpleName()).
                        append("AnalysisAdapter.getInstance()");
                if (i < chain.length - 2)
                {
                    sb.append(",\n\t\t\t");
                }
            }
            sb.append(")");
            return sb.toString();
        }
    }

    public final static class DslMethodDecl
    {
        private static final Pattern PATTERN = Pattern.compile("\\s+([a-zA-Z]\\w*)\\s*\\(([^\\)]*)");
        private final String signature;
        private final String name;
        private final String[] params;

        public DslMethodDecl(final String signature)
        {
            this.signature = signature;
            final Matcher matcher = PATTERN.matcher(signature);
            if (matcher.find())
            {
                this.name = matcher.group(1);
                final String paramsString = matcher.group(2).trim();
                if (!paramsString.isEmpty())
                {
                    final String[] paramsDefs = paramsString.split("\\s*,\\s*");
                    this.params = new String[paramsDefs.length];
                    for (int i = 0; i < paramsDefs.length; i++)
                    {
                        final String[] paramFragments = paramsDefs[i].split("\\s+");
                        params[i] = paramFragments[paramFragments.length - 1];
                    }
                }
                else
                {
                    this.params = new String[0];
                }
            }
            else
            {
                throw new IllegalStateException("Could not parse DSL method signature [" + signature + "]");
            }
        }

        public String getSignature()
        {
            return signature;
        }

        public String getName()
        {
            return name;
        }

        public String[] getParams()
        {
            return params;
        }
    }
}
