package org.langera.freud.optional.classfile.method.instruction;

import org.langera.freud.optional.classfile.method.ClassFileMethod;

public final class StringOperandInstruction extends Instruction
{
    public StringOperandInstruction(final ClassFileMethod method, final int index, final Opcode opcode, final int currentLineNumber, final String operand)
    {
        this(method, index, opcode, currentLineNumber, operand, -1);
    }

    public StringOperandInstruction(final ClassFileMethod method, final int index, final Opcode opcode, final int currentLineNumber, final String operand, final int varIndex)
    {
        super(method, index, opcode, currentLineNumber, null, null, operand, -1, null, varIndex, null, null, null);
    }
}
