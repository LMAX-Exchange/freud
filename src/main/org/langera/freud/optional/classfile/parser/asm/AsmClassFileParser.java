package org.langera.freud.optional.classfile.parser.asm;

import org.langera.freud.core.FreudBuilderException;
import org.langera.freud.core.iterator.AnalysedObjectIterator;
import org.langera.freud.core.iterator.resource.Resource;
import org.langera.freud.core.iterator.resource.ResourceIterators;
import org.langera.freud.core.iterator.resource.ResourceParser;
import org.langera.freud.core.iterator.resource.ResourceParserException;
import org.langera.freud.optional.classfile.ClassFile;
import org.langera.freud.optional.classfile.parser.InnerClassFileResourceIdentifierGetter;
import org.langera.freud.util.io.IoUtil;
import org.objectweb.asm.ClassReader;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;

public final class AsmClassFileParser implements ResourceParser<ClassFile>, AsmClassFileGetter
{
    private static final int CACHE_SIZE = 5;
    private final LinkedHashMap<String, AsmClassFile> classByNameCache;
    private final InnerClassFileResourceIdentifierGetter innerClassFileResourceIdentifierGetter;
    private Resource currentResource;
    private String currentResourceIdentifier;

    public AsmClassFileParser(final InnerClassFileResourceIdentifierGetter innerClassFileResourceIdentifierGetter)
    {
        this.innerClassFileResourceIdentifierGetter = innerClassFileResourceIdentifierGetter;
        classByNameCache = new LinkedHashMap<String, AsmClassFile>();
    }

    @Override
    public Class<ClassFile> getType()
    {
        return ClassFile.class;
    }

    @Override
    public ClassFile parseResource(final String resourceIdentifier, final Resource resource) throws IOException, ResourceParserException
    {
        currentResource = resource;
        currentResourceIdentifier = resourceIdentifier;
        return parseClassFileInternal(resourceIdentifier, resource);
    }

    @Override
    public AsmClassFile getClassFile(final String name, final AsmClassFile currentClassFile)
    {
        AsmClassFile asmClassFile;
        try
        {
            asmClassFile = classByNameCache.get(name);
            if (asmClassFile == null)
            {
                asmClassFile = parseClassFileInternal(
                        innerClassFileResourceIdentifierGetter.getResourceIdentifier(name, currentClassFile, currentResourceIdentifier),
                        currentResource);
            }
        }
        catch (IOException e)
        {
            throw new FreudBuilderException("Failed to find class file "+name, e);
        }
        return asmClassFile;
    }

    private AsmClassFile parseClassFileInternal(final String resourceIdentifier, final Resource resource) throws IOException
    {
        final InputStream inputStream = resource.getResource(resourceIdentifier);
        try
        {
            final AsmClassFileFactory visitor = new AsmClassFileFactory(this);
            final ClassReader reader = new ClassReader(inputStream);
            visitor.clear();
            reader.accept(visitor, 0);

            reader.accept(visitor, 0);
            return addToCacheAndReturn(visitor.getClassFile());
        }
        finally
        {
            IoUtil.safeClose(inputStream);
        }
    }

    private AsmClassFile addToCacheAndReturn(final AsmClassFile classFile)
    {
        classByNameCache.put(classFile.getName(), classFile);
        if (classByNameCache.size() > CACHE_SIZE)
        {
            classByNameCache.remove(classByNameCache.values().iterator().next());
        }
        return classFile;
    }

    public static void main(String[] args) throws IOException, ResourceParserException
    {
        final AnalysedObjectIterator<ClassFile> iterator = ResourceIterators.filesByPathResourceIterator(
                 new AsmClassFileParser(new InnerClassFileResourceIdentifierGetter()
                 {
                     @Override
                     public String getResourceIdentifier(final String name, final ClassFile currentClassFile, final String currentResourceIdentifier)
                     {
                         int indexOfClassesDir = currentResourceIdentifier.indexOf("classes/");
                         return currentResourceIdentifier.substring(0, indexOfClassesDir + "classes/".length()) + name + ".class";
                     }
                 }),
                new FilenameFilter()
                {
                    @Override
                    public boolean accept(final File dir, final String name)
                    {
                        return name.endsWith(".class");
                    }
                }, true, "../../java/classes");
//                }, true, "../trunk/build/classes/");
//                }, true, "build");

        while (iterator.hasNext())
        {
            ClassFile classFile = iterator.next();
System.out.println(classFile.getName());
        }
    }
}
