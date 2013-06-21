package org.freud.analysed.classbytecode.method.instruction;

public final class JumpInstruction extends Instruction {
    public JumpInstruction(final int index, final Opcode opcode, final int currentLineNumber, final Label label) {
        super(index, opcode, currentLineNumber, null, null, null, null, -1, null, -1, label, null, null);
    }


    @Override
    public String toString() {
        return "Instruction{" +
                "index=" + getInstructionIndex() +
                ", opcode=" + getOpcode() +
                ", lineNumber=" + getLineNumber() +
                ", label=" + getLabel() +
                '}';
    }
}
