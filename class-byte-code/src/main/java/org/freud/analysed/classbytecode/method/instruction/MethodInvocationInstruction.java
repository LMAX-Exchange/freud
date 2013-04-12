package org.freud.analysed.classbytecode.method.instruction;

import java.util.Arrays;

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


    @Override
    public String toString()
    {
        return "Instruction{" +
                "index=" + getInstructionIndex() +
                ", opcode=" + getOpcode() +
                ", lineNumber=" + getLineNumber() +
                ", owner='" + getOwner() + '\'' +
                ", name='" + getName() + '\'' +
                ", returnType='" + getReturnType() + '\'' +
                ", args=" + (getArgs() == null ? null : Arrays.asList(getArgs())) +
                '}';
    }
}
