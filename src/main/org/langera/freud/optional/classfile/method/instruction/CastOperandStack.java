package org.langera.freud.optional.classfile.method.instruction;

public final class CastOperandStack extends AbstractOperandStack
{
    private final String fromType;
    private final String toType;

    public CastOperandStack(final String fromType, final String toType, final OperandStack next, final Opcode opcode)
    {
        super(next, opcode);
        this.fromType = fromType;
        this.toType = toType;
    }

    @Override
    protected String getTypeForCurrentOperandStackItem()
    {
        return fromType;
    }

    public String getFromType()
    {
        return fromType;
    }
}
