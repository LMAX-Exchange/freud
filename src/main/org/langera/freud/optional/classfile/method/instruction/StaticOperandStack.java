package org.langera.freud.optional.classfile.method.instruction;

public final class StaticOperandStack extends AbstractOperandStack
{
    private final String type;

    public StaticOperandStack(final String type, final OperandStack next, final Opcode opcode)
    {
        super(next, opcode);
        this.type = type;
    }

    @Override
    protected String getTypeForCurrentOperandStackItem()
    {
        return type;
    }
}
