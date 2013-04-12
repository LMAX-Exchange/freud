package org.freud.analysed.classbytecode.parser;


import org.freud.analysed.classbytecode.ClassByteCode;

public interface InnerClassByteCodeResourceIdentifierGetter {
    String getResourceIdentifier(final String name, final ClassByteCode currentClassByteCode, final String currentResourceIdentifier);
}
