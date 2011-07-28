package org.langera.freud.optional.classfile.method.instruction;

public final class JumpInstruction extends Instruction
{
    public JumpInstruction(final int index, final Opcode opcode, final int currentLineNumber, final Label label)
    {
        super(index, opcode, currentLineNumber, null, null, null, null, -1, null, -1, label, null, null);
    }
}
