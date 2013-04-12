package org.freud.analysed.classbytecode;

public interface ClassByteCodeAnnotation
{

    int getParameterIndex();

    String getDesc();

    boolean isVisible();

    boolean isDefaultAnnotation();

    String getName();

    Object getValue();

    String getValueDesc();
}
