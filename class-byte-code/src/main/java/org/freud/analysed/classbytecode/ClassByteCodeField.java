package org.freud.analysed.classbytecode;

public interface ClassByteCodeField extends ClassByteCodeElement {
    String getName();

    String getDesc();

    String getSignature();

    Object getValue();

    boolean isEnumField();

    boolean isVolatile();

    boolean isTransient();

    boolean isStatic();
}
