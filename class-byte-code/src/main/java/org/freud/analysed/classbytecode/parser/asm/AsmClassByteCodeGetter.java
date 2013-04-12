package org.freud.analysed.classbytecode.parser.asm;

interface AsmClassByteCodeGetter
{
    AsmClassByteCode getClassByteCode(final String name, final AsmClassByteCode classByteCode);
}
