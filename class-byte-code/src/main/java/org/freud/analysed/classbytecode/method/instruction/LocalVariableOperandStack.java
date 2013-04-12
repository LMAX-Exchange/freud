package org.freud.analysed.classbytecode.method.instruction;

import org.freud.analysed.classbytecode.method.ClassByteCodeMethod;
import org.freud.analysed.classbytecode.method.LocalVariable;

public final class LocalVariableOperandStack extends AbstractOperandStack
{
    private final ClassByteCodeMethod method;
    private final int varIndex;
    private final int category;

    public LocalVariableOperandStack(final ClassByteCodeMethod method, final int varIndex, final OperandStack next, final Opcode opcode)
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
