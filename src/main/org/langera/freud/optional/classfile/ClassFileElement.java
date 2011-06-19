package org.langera.freud.optional.classfile;

import java.util.List;

public interface ClassFileElement
{
    public List<ClassFileAnnotation> getAnnotationList();

    boolean isPublic();

    boolean isPrivate();

    boolean isProtected();

    boolean isFinal();

    boolean isSynthetic();
}
