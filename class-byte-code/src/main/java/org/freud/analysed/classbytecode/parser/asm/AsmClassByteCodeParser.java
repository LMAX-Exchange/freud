package org.freud.analysed.classbytecode.parser.asm;

import org.freud.analysed.classbytecode.ClassByteCode;
import org.freud.analysed.classbytecode.parser.InnerClassByteCodeResourceIdentifierGetter;

import java.util.LinkedHashMap;

public final class AsmClassByteCodeParser implements AsmClassByteCodeGetter {
    private static final int CACHE_SIZE = 5;
    private final LinkedHashMap<String, AsmClassByteCode> classByNameCache;
    private final InnerClassByteCodeResourceIdentifierGetter innerClassByteCodeResourceIdentifierGetter;
    //    private Resource currentResource;
    private String currentResourceIdentifier;

    public AsmClassByteCodeParser(final InnerClassByteCodeResourceIdentifierGetter innerClassByteCodeResourceIdentifierGetter) {
        this.innerClassByteCodeResourceIdentifierGetter = innerClassByteCodeResourceIdentifierGetter;
        classByNameCache = new LinkedHashMap<String, AsmClassByteCode>();
    }

    public Class<ClassByteCode> getType() {
        return ClassByteCode.class;
    }

    @Override
    public AsmClassByteCode getClassByteCode(final String name, final AsmClassByteCode classByteCode) {
        return null;
    }


    //    public ClassByteCode parseResource(final String resourceIdentifier, final Resource resource) throws IOException, ResourceParserException
//    {
//        currentResource = resource;
//        currentResourceIdentifier = resourceIdentifier;
//        return parseClassByteCodeInternal(resourceIdentifier, resource);
//    }

//    public AsmClassByteCode getClassByteCode(final String name, final AsmClassByteCode classByteCode)
//    {
//        AsmClassByteCode asmClassByteCode;
//        try
//        {
//            asmClassByteCode = classByNameCache.get(name);
//            if (asmClassByteCode == null)
//            {
//                asmClassByteCode = parseClassByteCodeInternal(
//                        innerClassByteCodeResourceIdentifierGetter.getResourceIdentifier(name, classByteCode, currentResourceIdentifier),
//                        currentResource);
//            }
//        }
//        catch (IOException e)
//        {
//            throw new FreudBuilderException("Failed to find class byte code " + name, e);
//        }
//        return asmClassByteCode;
//    }

//    private AsmClassByteCode parseClassByteCodeInternal(final String resourceIdentifier, final Resource resource) throws IOException
//    {
//        final InputStream inputStream = resource.getResource(resourceIdentifier);
//        try
//        {
//            final AsmClassByteCodeFactory visitor = new AsmClassByteCodeFactory(this);
//            final ClassReader reader = new ClassReader(inputStream);
//            visitor.clear();
//
//System.out.println("**********************************************************************");
//System.out.println(resourceIdentifier);
//System.out.println("**********************************************************************");

//            reader.accept(visitor, 0);
//            return addToCacheAndReturn(visitor.getClassByteCode());
//        }
//        finally
//        {
//            IoUtil.safeClose(inputStream);
//        }
//    }

    private AsmClassByteCode addToCacheAndReturn(final AsmClassByteCode classByteCode) {
        classByNameCache.put(classByteCode.getName(), classByteCode);
        if (classByNameCache.size() > CACHE_SIZE) {
            classByNameCache.remove(classByNameCache.values().iterator().next());
        }
        return classByteCode;
    }
}
