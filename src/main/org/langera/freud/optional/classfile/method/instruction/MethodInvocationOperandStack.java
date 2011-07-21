package org.langera.freud.optional.classfile.method.instruction;

public final class MethodInvocationOperandStack extends AbstractOperandStack
{
    private final MethodInvocationInstruction instruction;

    public MethodInvocationOperandStack(final MethodInvocationInstruction instruction, final OperandStack next, final Opcode opcode)
    {
        super(popStack(instruction, next), opcode);
        this.instruction = instruction;
    }

    public static OperandStack popStack(final MethodInvocationInstruction instruction, final OperandStack next)
    {
        OperandStack stack = next;
        if (instruction.getOpcode() != Opcode.INVOKESTATIC)
        {
            stack = next.next(); // pop owner
        }
        return popArgs(instruction, stack);
    }

    private static OperandStack popArgs(final MethodInvocationInstruction instruction, final OperandStack next)
    {
        OperandStack stack = next;
        final int length = instruction.getArgs().length;
        for (int i=0; i < length; i++)
        {
            stack = stack.next();
        }
        return stack;
    }

    @Override
    protected String getTypeForCurrentOperandStackItem()
    {
        return instruction.getReturnType();
    }

    @Override
    public OperandStack dup(final OperandStack next, final Opcode opcode)
    {
        return new MethodInvocationOperandStack(instruction, next, opcode);
    }
}
