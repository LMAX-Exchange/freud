package org.freud.analysed.javasource.jdom;

import org.freud.analysed.javasource.JavaSource;
import org.freud.core.Creator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public final class JavaSourceJdomFromFileCreator implements Creator<File,JavaSource> {
    @Override
    public JavaSource create(final File source) {
        try {
            return JavaSourceJdomParser.parseJavaSource(new BufferedReader(new FileReader(source)), source.getCanonicalPath());
        }
        catch (Exception e) {
            throw new IllegalArgumentException("Filed to parse java source " + source, e);
        }
    }
}
