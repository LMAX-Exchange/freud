package org.freud.analysed.classbytecode;

public interface ClassByteCodeInnerClass extends ClassByteCode {
    boolean isStatic();

    boolean isAnonymous();

    String getOuterName();

    String getOuterDesc();
}
