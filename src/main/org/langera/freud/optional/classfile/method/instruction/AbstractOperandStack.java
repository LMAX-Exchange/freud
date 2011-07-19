package org.langera.freud.optional.classfile.method.instruction;

public abstract class AbstractOperandStack implements OperandStack
{
    public static final OperandStack EMPTY_STACK = new AbstractOperandStack(null, null)
    {
        @Override
        protected String getTypeForCurrentOperandStackItem()
        {
            throw new IllegalStateException("cannot pop an empty stack");
        }
    };

    private final OperandStack next;
    private final Opcode opcode;


    public AbstractOperandStack(final OperandStack next, final Opcode opcode)
    {
        this.next = next;
        this.opcode = opcode;
    }

    protected abstract String getTypeForCurrentOperandStackItem();

    @Override
    public String getOperandType(final int index)
    {
        if (index == 0)
        {
            return getTypeForCurrentOperandStackItem();
        }
        return next.getOperandType(index - 1);
    }

    @Override
    public OperandStack next()
    {
        return next;
    }

    @Override
    public Opcode generatingOpcode()
    {
        return opcode;
    }
}
