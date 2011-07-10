package org.langera.freud.optional.classfile;

import java.util.List;

public interface ClassFileMethod extends ClassFileElement
{
    String getName();

    String getDesc();

    String getSignature();

    String[] getExceptions();

    boolean isStatic();

    boolean isSynchronized();

    boolean isBridge();

    boolean isVarargs();

    boolean isNative();

    boolean isStrict();

    boolean isAbstract();

    List<ClassFileInnerClass> getAnonymousClassList();

    LocalVariable getLocalVariable(String name);

    List<Instruction> getInstructionList();

    void findInstruction(final InstructionVisitor instructionVisitor, final Opcode... opcodes);
}
