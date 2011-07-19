package org.langera.freud.optional.classfile.method.instruction;

import org.langera.freud.optional.classfile.method.ClassFileMethod;
import org.langera.freud.optional.classfile.method.LocalVariable;

public final class LocalVariableOperandStack extends AbstractOperandStack
{
    private final ClassFileMethod method;
    private final int varIndex;

    public LocalVariableOperandStack(final ClassFileMethod method, final int varIndex, final OperandStack next, final Opcode opcode)
    {
        super(next, opcode);
        this.varIndex = varIndex;
        this.method = method;
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


}
