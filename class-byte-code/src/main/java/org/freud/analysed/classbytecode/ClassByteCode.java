package org.freud.analysed.classbytecode;

import org.freud.analysed.classbytecode.method.ClassByteCodeMethod;

import java.util.List;

public interface ClassByteCode extends ClassByteCodeElement
{
    List<ClassByteCodeField> getFieldList();

    List<ClassByteCodeMethod> getMethodList();

    String getName();

    String[] getInterfaces();

    String getSignature();

    String getSuperName();

    boolean isInterface();

    boolean isEnum();

    boolean isAnnotation();

    boolean isAbstract();

    boolean isSuper();

    int getVersion();

    List<ClassByteCodeInnerClass> getInnerClassList();

}
