package org.langera.freud.optional.classfile.method.ref;

public final class CastLoadedReference implements LoadedReference
{
    private final LoadedReference ref;
    private final String castType;

    public CastLoadedReference(final String castType, final LoadedReference ref)
    {
        this.castType = castType;
        this.ref = ref;
    }

    @Override
    public int getInstructionIndex()
    {
        return ref.getInstructionIndex();
    }

    @Override
    public String getTypeOfReference()
    {
        return castType;
    }

    public LoadedReference getLoadedReference()
    {
        return ref;
    }
}
