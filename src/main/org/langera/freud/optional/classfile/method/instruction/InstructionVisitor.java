package org.langera.freud.optional.classfile.method.instruction;

public interface InstructionVisitor
{
    void noArgInstruction(final Instruction instruction);

    void methodInvocation(final Instruction instruction, String owner, String methodName, String... args);
}
