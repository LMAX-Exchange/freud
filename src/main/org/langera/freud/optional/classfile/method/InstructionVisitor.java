package org.langera.freud.optional.classfile.method;

public interface InstructionVisitor
{
    void noArgInstruction(final Instruction instruction);

    void methodInvocation(final Instruction instruction, String owner, String methodName, String... args);
}
