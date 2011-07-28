package org.langera.freud.optional.classfile.method.instruction;

public final class MethodInvocationInstruction extends Instruction
{
    public MethodInvocationInstruction(final int index, final Opcode opcode, final int currentLineNumber,
                                       final String owner, final String name, final String[] args, final String returnType)
    {
        super(index, opcode, currentLineNumber, owner, name, null, null, -1, null, -1, null, args, returnType);
    }

    public void visit(final InstructionVisitor instructionVisitor)
    {
        instructionVisitor.methodInvocation(this, getOwner(), getName(), getArgs());
    }
}
