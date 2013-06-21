package org.freud.analysed.classbytecode;

import org.freud.core.Creator;

public final class FieldsOfClassByteCodeCreator implements Creator<ClassByteCode, Iterable<ClassByteCodeField>> {
    @Override
    public Iterable<ClassByteCodeField> create(final ClassByteCode source) {
        return source.getFieldList();
    }
}
