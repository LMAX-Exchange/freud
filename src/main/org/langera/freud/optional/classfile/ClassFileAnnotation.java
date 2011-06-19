package org.langera.freud.optional.classfile;

public interface ClassFileAnnotation
{

    int getParameterIndex();

    String getDesc();

    boolean isVisible();

    boolean isDefaultAnnotation();

    String getName();

    Object getValue();

    String getValueDesc();
}
