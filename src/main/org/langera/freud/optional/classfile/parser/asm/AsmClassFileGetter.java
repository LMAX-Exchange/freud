package org.langera.freud.optional.classfile.parser.asm;

interface AsmClassFileGetter
{
    AsmClassFile getClassFile(final String name, final AsmClassFile currentClassFile);
}
