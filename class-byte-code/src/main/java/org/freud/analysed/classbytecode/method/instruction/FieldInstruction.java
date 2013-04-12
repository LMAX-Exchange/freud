package org.freud.analysed.classbytecode.method.instruction;

public final class FieldInstruction extends Instruction
{

    public FieldInstruction(final int index,
                            final Opcode opcode, final int currentLineNumber,
                            final String owner, final String name, final String desc)
    {
        super(index, opcode, currentLineNumber, owner, name, null, null, -1, desc, -1, null, null, null);
    }

    @Override
    public String toString()
    {
        return "Instruction{" +
                "index=" + getInstructionIndex() +
                ", opcode=" + getOpcode() +
                ", lineNumber=" + getLineNumber() +
                ", owner='" + getOwner() + '\'' +
                ", name='" + getName() + '\'' +
                ", desc='" + getDesc() + '\'' +
                '}';
    }
}
