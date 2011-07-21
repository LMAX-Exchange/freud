package org.langera.freud.optional.classfile.method.instruction;

public final class ReferenceOperandStack extends AbstractOperandStack
{
    private final OperandStack reference;
    private final boolean arrayOfReference;

    public ReferenceOperandStack(final OperandStack reference, final OperandStack next, final Opcode opcode)
    {
        this(reference, false, next, opcode);
    }

    public ReferenceOperandStack(final OperandStack reference, final boolean arrayOfReference,
                                 final OperandStack next, final Opcode opcode)
    {
        super(next, opcode);
        this.reference = reference;
        this.arrayOfReference = arrayOfReference;
    }


    @Override
    protected String getTypeForCurrentOperandStackItem()
    {
        return (arrayOfReference) ? "[" + reference.getOperandType() : reference.getOperandType();
    }

    @Override
    public OperandStack dup(final OperandStack next, final Opcode opcode)
    {
        return null;
    }
}
