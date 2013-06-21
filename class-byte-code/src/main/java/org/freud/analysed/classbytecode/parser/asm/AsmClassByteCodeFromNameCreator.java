package org.freud.analysed.classbytecode.parser.asm;

import org.freud.analysed.classbytecode.ClassByteCode;
import org.freud.core.Creator;

import java.io.File;
import java.io.InputStream;

import static java.lang.ClassLoader.getSystemClassLoader;

public final class AsmClassByteCodeFromNameCreator implements Creator<String, ClassByteCode> {

    private final AsmClassByteCodeFromStreamCreator fromStreamCreator;
    private final ClassLoader classLoader;

    public AsmClassByteCodeFromNameCreator() {
        this(getSystemClassLoader());
    }

    public AsmClassByteCodeFromNameCreator(final ClassLoader classLoader) {
        this.classLoader = classLoader;
        this.fromStreamCreator = new AsmClassByteCodeFromStreamCreator();
    }

    @Override
    public ClassByteCode create(final String source) {
        String fileName = toFileName(source);
        final InputStream inputStream = classLoader.getResourceAsStream(fileName);
        return fromStreamCreator.create(inputStream);
    }

    private String toFileName(final String className) {
        return className.replace('.', File.separatorChar) + ".class";
    }
}
