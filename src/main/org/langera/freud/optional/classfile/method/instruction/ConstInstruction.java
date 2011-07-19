package org.langera.freud.optional.classfile.method.instruction;

import org.langera.freud.optional.classfile.method.ClassFileMethod;

public final class ConstInstruction extends Instruction
{
    public ConstInstruction(final ClassFileMethod method, final OperandStack currentOperandStack,
                            final int index, final Opcode opcode, final int currentLineNumber, final Object constant)
    {
        super(method, currentOperandStack, index, opcode, currentLineNumber, null, null,
                AbstractInstructionVisitor.typeEncoding(constant.getClass()), constant, -1, null, -1, null, null, null);

    }
}
