package org.freud.analysed.classbytecode.method.instruction;

public final class ConstInstruction extends Instruction
{
    public ConstInstruction(final int index, final Opcode opcode, final int currentLineNumber, final Object constant)
    {
        super(index, opcode, currentLineNumber, null, null,
                AbstractInstructionVisitor.typeEncoding(constant.getClass()), constant, -1, null, -1, null, null, null);

    }

    @Override
    public String toString()
    {
        return "ConstInstruction{" +
                "index=" + getInstructionIndex() +
                ", opcode=" + getOpcode() +
                ", lineNumber=" + getLineNumber() +
                ", operandType='" + getOperandType() + '\'' +
                ", constant=" + getConstant() +
                '}';
    }
}
