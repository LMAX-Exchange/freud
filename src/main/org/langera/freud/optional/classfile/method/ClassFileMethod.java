package org.langera.freud.optional.classfile.method;

import org.langera.freud.optional.classfile.ClassFileElement;
import org.langera.freud.optional.classfile.ClassFileInnerClass;

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

    LocalVariable getLocalVariable(int index);

    List<Instruction> getInstructionList();

    void findInstruction(final InstructionVisitor instructionVisitor, final Opcode... opcodes);
}
