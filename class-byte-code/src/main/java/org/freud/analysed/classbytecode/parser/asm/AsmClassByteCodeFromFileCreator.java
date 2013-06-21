package org.freud.analysed.classbytecode.parser.asm;

import org.freud.analysed.classbytecode.ClassByteCode;
import org.freud.core.Creator;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public final class AsmClassByteCodeFromFileCreator implements Creator<File, ClassByteCode> {


    private final AsmClassByteCodeFromStreamCreator fromStreamCreator;

    public AsmClassByteCodeFromFileCreator() {
        this.fromStreamCreator = new AsmClassByteCodeFromStreamCreator();
    }

    @Override
    public ClassByteCode create(final File source) {
        try {
            return fromStreamCreator.create(new BufferedInputStream(new FileInputStream(source)));
        }
        catch (FileNotFoundException e) {
            throw new IllegalArgumentException("File " + source, e);
        }
    }
}
