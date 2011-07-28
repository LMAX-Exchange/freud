package org.langera.freud.optional.classfile.method.instruction;

public final class IntOperandInstruction extends Instruction
{

    public IntOperandInstruction(final int index, final Opcode opcode, final int currentLineNumber, final int intOperand)
    {
        super(index, opcode, currentLineNumber, null, null, null, null, intOperand, null, -1, null, null, null);
    }
}
