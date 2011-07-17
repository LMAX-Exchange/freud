package org.langera.freud.optional.classfile.method.instruction;

import org.langera.freud.optional.classfile.method.ClassFileMethod;

public final class FieldInstruction extends Instruction
{

    public FieldInstruction(final ClassFileMethod method, final int index, final Opcode opcode, final int currentLineNumber,
                            final String owner, final String name, final String desc)
    {
        super(method, index, opcode, currentLineNumber, owner, name, null, -1, desc, -1, null, null, null);
    }
}
