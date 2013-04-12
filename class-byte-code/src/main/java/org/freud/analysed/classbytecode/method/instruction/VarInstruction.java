package org.freud.analysed.classbytecode.method.instruction;

public final class VarInstruction extends Instruction
{

    public VarInstruction(final int index, final Opcode opcode, final int currentLineNumber, final int varIndex)
    {
        super(index, opcode, currentLineNumber, null, null, null, null, -1, null, varIndex, null, null, null);
    }


    @Override
    public String toString()
    {
        return "Instruction{" +
                "index=" + getInstructionIndex() +
                ", opcode=" + getOpcode() +
                ", lineNumber=" + getLineNumber() +
                ", varIndex=" + getVarIndex() +
                '}';
    }

}
