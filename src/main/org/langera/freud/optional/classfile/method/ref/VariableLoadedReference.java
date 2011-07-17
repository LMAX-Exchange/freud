package org.langera.freud.optional.classfile.method.ref;

import org.langera.freud.optional.classfile.method.ClassFileMethod;

public final class VariableLoadedReference implements LoadedReference
{
    private final ClassFileMethod method;
    private final int instructionIndex;
    private final int localVariableIndex;

    public VariableLoadedReference(final ClassFileMethod method, final int localVariableIndex, final int instructionIndex)
    {
        this.method = method;
        this.localVariableIndex = localVariableIndex;
        this.instructionIndex = instructionIndex;
    }

    @Override
    public int getInstructionIndex()
    {
        return instructionIndex;
    }

    @Override
    public String getTypeOfReference()
    {
        return method.getLocalVariable(localVariableIndex).getDesc();
    }
}
