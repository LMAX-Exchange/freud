package org.langera.freud.optional.classfile;

public interface ClassFileInnerClass extends ClassFile
{
    boolean isStatic();

    boolean isAnonymous();

    String getOuterName();

    String getOuterDesc();
}
