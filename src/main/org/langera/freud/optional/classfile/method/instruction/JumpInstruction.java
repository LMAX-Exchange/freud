package org.langera.freud.optional.classfile.method.instruction;

import org.langera.freud.optional.classfile.method.ClassFileMethod;

public final class JumpInstruction extends Instruction
{
    public JumpInstruction(final ClassFileMethod method, final OperandStack currentOperandStack,
                           final int index, final Opcode opcode, final int currentLineNumber, final Label label)
    {
        super(method, currentOperandStack, index, opcode, currentLineNumber, null, null, null, null, -1, null, -1, label, null, null);
    }
}
