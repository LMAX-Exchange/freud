package org.freud.analysed.classbytecode;

import org.freud.analysed.classbytecode.method.ClassByteCodeMethod;
import org.freud.core.Creator;

public final class MethodsOfClassByteCodeCreator implements Creator<ClassByteCode, Iterable<ClassByteCodeMethod>> {
    @Override
    public Iterable<ClassByteCodeMethod> create(final ClassByteCode source) {
        return source.getMethodList();
    }
}
