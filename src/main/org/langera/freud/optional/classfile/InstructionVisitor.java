package org.langera.freud.optional.classfile;

public interface InstructionVisitor
{
    void instruction(final Opcode opcode);

    void methodInvocation(final Opcode opcode, final int lineNumber, String owner, String methodName, String... args);
}
