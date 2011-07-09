package org.langera.freud.optional.classfile.parser;

import org.langera.freud.optional.classfile.ClassFile;

public interface InnerClassFileResourceIdentifierGetter
{
    String getResourceIdentifier(final String name, final ClassFile currentClassFile, final String currentResourceIdentifier);
}
