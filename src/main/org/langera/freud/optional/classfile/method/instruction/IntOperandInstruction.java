package org.langera.freud.optional.classfile.method.instruction;

import org.langera.freud.optional.classfile.method.ClassFileMethod;

public final class IntOperandInstruction extends Instruction
{

    public IntOperandInstruction(final ClassFileMethod method, final OperandStack currentOperandStack,
                                 final int index, final Opcode opcode, final int currentLineNumber, final int intOperand)
    {
        super(method, currentOperandStack, index, opcode, currentLineNumber, null, null, null, null, intOperand, null, -1, null, null, null);
    }
}
