package org.freud.analysed.classbytecode.method;


import org.freud.analysed.classbytecode.ClassByteCodeElement;
import org.freud.analysed.classbytecode.ClassByteCodeInnerClass;
import org.freud.analysed.classbytecode.method.instruction.Instruction;
import org.freud.analysed.classbytecode.method.instruction.InstructionVisitor;

import java.util.List;

public interface ClassByteCodeMethod extends ClassByteCodeElement
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

    List<ClassByteCodeInnerClass> getAnonymousClassList();

    LocalVariable getLocalVariable(String name);

    LocalVariable getLocalVariable(int index);

    void findInstruction(final InstructionVisitor instructionVisitor);

    Instruction getInstruction(int index);

    String getReturnType();

    String getLocalVariableType(int index);
}
