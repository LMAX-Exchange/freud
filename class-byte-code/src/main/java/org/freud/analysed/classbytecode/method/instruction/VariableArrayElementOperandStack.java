package org.freud.analysed.classbytecode.method.instruction;

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

    @Override
    protected String toStringInternal()
    {
        return "[" + arrayOperand.toString();
    }

    public String getArrayType()
    {
        return arrayOperand.getOperandType();
    }

    @Override
    public int getComputationalTypeCategory()
    {
        return 1;
    }

    @Override
    public OperandStack dup(final OperandStack next, final Opcode opcode)
    {
        return new VariableArrayElementOperandStack(arrayOperand, next, opcode);
    }
}
