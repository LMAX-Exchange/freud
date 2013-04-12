package org.freud.analysed.classbytecode.method.instruction;

public final class ReferenceOperandInstruction extends Instruction
{
    public ReferenceOperandInstruction(final int index, final Opcode opcode, final int currentLineNumber, final String operandType)
    {
        this(index, opcode, currentLineNumber, operandType, -1);
    }

    public ReferenceOperandInstruction(final int index, final Opcode opcode, final int currentLineNumber, final String operandType, final int intOperand)
    {
        super(index, opcode, currentLineNumber, null, null, operandType, null, intOperand, null, -1, null, null, null);
    }


    @Override
    public String toString()
    {
        return "Instruction{" +
                "index=" + getInstructionIndex() +
                ", opcode=" + getOpcode() +
                ", lineNumber=" + getLineNumber() +
                ", intOperand=" + getIntOperand() +
                ", operandType='" + getOperandType() + '\'' +
                '}';
    }
}
