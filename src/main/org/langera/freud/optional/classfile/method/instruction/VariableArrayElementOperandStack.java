package org.langera.freud.optional.classfile.method.instruction;

public final class VariableArrayElementOperandStack extends AbstractOperandStack
{
    private final OperandStack arrayOperand;

    public VariableArrayElementOperandStack(final OperandStack arrayOperand, final OperandStack next, final Opcode opcode)
    {
        super(next, opcode);
        this.arrayOperand = arrayOperand;
    }

    @Override
    protected String getTypeForCurrentOperandStackItem()
    {
        return getArrayType().substring(1);
    }

    public String getArrayType()
    {
        return arrayOperand.getOperandType(0);
    }
}
