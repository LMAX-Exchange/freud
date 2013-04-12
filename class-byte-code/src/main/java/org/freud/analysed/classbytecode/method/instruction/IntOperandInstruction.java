package org.freud.analysed.classbytecode.method.instruction;

public final class IntOperandInstruction extends Instruction {

    public IntOperandInstruction(final int index, final Opcode opcode, final int currentLineNumber, final int intOperand) {
        super(index, opcode, currentLineNumber, null, null, null, null, intOperand, null, -1, null, null, null);
    }

    @Override
    public String toString() {
        return "Instruction{" +
                "index=" + getInstructionIndex() +
                ", opcode=" + getOpcode() +
                ", lineNumber=" + getLineNumber() +
                ", intOperand=" + getIntOperand() +
                '}';
    }
}
