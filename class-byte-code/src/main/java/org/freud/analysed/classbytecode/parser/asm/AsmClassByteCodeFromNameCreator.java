package org.freud.analysed.classbytecode.parser.asm;

import org.freud.analysed.classbytecode.ClassByteCode;
import org.freud.core.Creator;

public final class AsmClassByteCodeFromNameCreator implements Creator<String,ClassByteCode> {

    public AsmClassByteCodeFromNameCreator() {
    }

    public AsmClassByteCodeFromNameCreator(final ClassLoader classLoader) {
    }

    @Override
    public ClassByteCode create(final String source) {
        // TODO
        return null;
    }
}
