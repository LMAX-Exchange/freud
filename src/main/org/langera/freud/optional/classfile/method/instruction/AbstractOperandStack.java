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

        @Override
        public OperandStack next()
        {
            throw new IllegalStateException("cannot pop an empty stack");
        }

        @Override
        public OperandStack dup(final OperandStack next, final Opcode opcode)
        {
            throw new UnsupportedOperationException("Cannot duplicate empty stack");
        }

        @Override
        public int depth()
        {
            return 0;
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
    public String getOperandType()
    {
        return getTypeForCurrentOperandStackItem();
    }

    @Override
    public OperandStack next()
    {
        return next;
    }

    @Override
    public Opcode getGeneratingOpcode()
    {
        return opcode;
    }

    @Override
    public int depth()
    {
        return 1 + next.depth();
    }
}
