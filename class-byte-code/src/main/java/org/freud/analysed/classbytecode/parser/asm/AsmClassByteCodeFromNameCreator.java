package org.freud.analysed.classbytecode.parser.asm;

import org.freud.analysed.classbytecode.ClassByteCode;
import org.freud.core.Creator;

import static java.lang.ClassLoader.getSystemClassLoader;

public final class AsmClassByteCodeFromNameCreator implements Creator<String, ClassByteCode> {

    private final ClassLoader classLoader;

    public AsmClassByteCodeFromNameCreator() {
        this(getSystemClassLoader());
    }

    public AsmClassByteCodeFromNameCreator(final ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public ClassByteCode create(final String source) {
        // TODO
        return null;
    }
}
