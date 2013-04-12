package org.freud.analysed.classbytecode;

import java.util.List;

public interface ClassByteCodeElement {
    public List<ClassByteCodeAnnotation> getAnnotationList();

    boolean isPublic();

    boolean isPrivate();

    boolean isProtected();

    boolean isFinal();

    boolean isSynthetic();
}
