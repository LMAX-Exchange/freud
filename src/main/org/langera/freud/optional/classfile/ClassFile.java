package org.langera.freud.optional.classfile;

import org.langera.freud.optional.classfile.method.ClassFileMethod;

import java.util.List;

public interface ClassFile extends ClassFileElement
{
    List<ClassFileField> getFieldList();

    List<ClassFileMethod> getMethodList();

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

    List<ClassFileInnerClass> getInnerClassList();

}
