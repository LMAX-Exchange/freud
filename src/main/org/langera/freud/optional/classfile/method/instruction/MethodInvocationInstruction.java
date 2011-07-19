package org.langera.freud.optional.classfile.method.instruction;

import org.langera.freud.optional.classfile.method.ClassFileMethod;

public final class MethodInvocationInstruction extends Instruction
{
    public MethodInvocationInstruction(final ClassFileMethod method, final OperandStack currentOperandStack,
                                       final int index, final Opcode opcode, final int currentLineNumber,
                                       final String owner, final String name, final String[] args, final String returnType)
    {
        super(method, currentOperandStack, index, opcode, currentLineNumber, owner, name, null, null, -1, null, -1, null, args, returnType);
    }

    public void visit(final InstructionVisitor instructionVisitor)
    {
        instructionVisitor.methodInvocation(this, getOwner(), getName(), getArgs());
    }
}
