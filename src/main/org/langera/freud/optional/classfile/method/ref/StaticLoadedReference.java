package org.langera.freud.optional.classfile.method.ref;

public final class StaticLoadedReference implements LoadedReference
{
    private final Type loadedReferenceType;
    private final String type;
    private final int instructionIndex;

    public enum Type
    {
        CONSTANT,
        ARRAY_LENGTH,
        ARRAY_ELEMENT,
    }

    public StaticLoadedReference(final String type, final int index, final Type loadedReferenceType)
    {
        this.type = type;
        instructionIndex = index;
        this.loadedReferenceType = loadedReferenceType;
    }

    @Override
    public String getTypeOfReference()
    {
        return type;
    }

    @Override
    public int getInstructionIndex()
    {
        return instructionIndex;
    }
}
