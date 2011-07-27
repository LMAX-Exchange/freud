package org.langera.freud.optional.classfile.method.instruction;

import org.langera.freud.optional.classfile.method.ClassFileMethod;
import org.langera.freud.optional.classfile.method.LocalVariable;

public final class LocalVariableOperandStack extends AbstractOperandStack
{
    private final ClassFileMethod method;
    private final int varIndex;
    private final int category;

    public LocalVariableOperandStack(final ClassFileMethod method, final int varIndex, final OperandStack next, final Opcode opcode)
    {
        super(next, opcode);
        this.varIndex = varIndex;
        this.method = method;
        this.category = calculateComputationalTypeCategory(method.getLocalVariableType(varIndex));
    }

    @Override
    protected String getTypeForCurrentOperandStackItem()
    {
        return getLocalVariable().getDesc();
    }


    public LocalVariable getLocalVariable()
    {
        return method.getLocalVariable(varIndex);
    }

    @Override
    protected String toStringInternal()
    {
        return "local variable " + varIndex;
    }

    @Override
    public int getComputationalTypeCategory()
    {
        return category;
    }

    @Override
    public OperandStack dup(final OperandStack next, final Opcode opcode)
    {
        return new LocalVariableOperandStack(method, varIndex, next, opcode);
    }
}
