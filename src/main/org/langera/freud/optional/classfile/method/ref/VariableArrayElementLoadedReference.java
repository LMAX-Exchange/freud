package org.langera.freud.optional.classfile.method.ref;

import org.langera.freud.optional.classfile.method.ClassFileMethod;

public final class VariableArrayElementLoadedReference implements LoadedReference
{
    private final ClassFileMethod method;
    private final int instructionIndex;

    public VariableArrayElementLoadedReference(final ClassFileMethod method, final int instructionIndex)
    {
        this.method = method;
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
        int i = instructionIndex;
        while (i >= 0)
        {
            LoadedReference arrayRef = method.getInstruction(i - 1).getLoadedReference();
            if (arrayRef != null && arrayRef.getTypeOfReference().startsWith("["))
            {
                return arrayRef.getTypeOfReference().substring(1);
            }
            i--;
        }
        return ""; // ?
    }
}
