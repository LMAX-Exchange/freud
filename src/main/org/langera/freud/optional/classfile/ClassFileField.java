package org.langera.freud.optional.classfile;

public interface ClassFileField extends ClassFileElement
{
    String getName();

    String getDesc();

    String getSignature();

    Object getValue();

    boolean isEnumField();

    boolean isVolatile();

    boolean isTransient();

    boolean isStatic();
}
