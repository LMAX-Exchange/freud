package org.langera.freud.optional.classfile.method.instruction;

import org.langera.freud.optional.classfile.method.ClassFileMethod;

public final class VarInstruction extends Instruction
{

    public VarInstruction(final ClassFileMethod method, final int index, final Opcode opcode, final int currentLineNumber, final int varIndex)
    {
        super(method, index, opcode, currentLineNumber, null, null, null, -1, null, varIndex, null, null, null);
    }

}
