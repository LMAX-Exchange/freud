package org.langera.freud.optional.classfile.method.instruction;

public final class Label
{
    private final int createdInstructionIndex;
    private final int key;
    private final boolean defaultKey;
    private final String handledType;
    private int declaredInstructionIndex;
    private int lineNumber;

    public static Label create(final int createdInstructionIndex)
    {
        return new Label(createdInstructionIndex, null, -1, false);
    }

    public static Label createHandler(final int createdInstructionIndex, final String handledType)
    {
        return new Label(createdInstructionIndex, handledType, -1, false);
    }

    public static Label createLookupKey(final int createdInstructionIndex, final int key)
    {
        return new Label(createdInstructionIndex,  null, key, false);
    }

    public static Label createDefaultLookupKey(final int createdInstructionIndex)
    {
        return new Label(createdInstructionIndex,  null, -1, true);
    }

    private Label(final int createdInstructionIndex, final String handledType, final int key, final boolean defaultKey)
    {
        this.createdInstructionIndex = createdInstructionIndex;
        this.handledType = handledType;
        this.key = key;
        this.defaultKey = defaultKey;
        declaredInstructionIndex = -1;
        lineNumber = -1;
    }

    public void declare(final int instructionIndex)
    {
        declaredInstructionIndex = instructionIndex;
    }

    public void setLineNumber(final int lineNumber)
    {
        this.lineNumber = lineNumber;
    }

    public int getKey()
    {
        return key;
    }

    public int getCreatedInstructionIndex()
    {
        return createdInstructionIndex;
    }

    public int getDeclaredInstructionIndex()
    {
        return declaredInstructionIndex;
    }

    public String getHandledType()
    {
        return handledType;
    }

    public int getLineNumber()
    {
        return lineNumber;
    }
}
