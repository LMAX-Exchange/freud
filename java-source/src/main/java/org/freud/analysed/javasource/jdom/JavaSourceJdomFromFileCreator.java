package org.freud.analysed.javasource.jdom;

import org.freud.analysed.javasource.JavaSource;
import org.freud.core.Creator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import static org.freud.analysed.javasource.jdom.JavaSourceJdomParser.parseJavaSource;

public final class JavaSourceJdomFromFileCreator implements Creator<File,JavaSource> {
    @Override
    public JavaSource create(final File source) {
        try {
            return parseJavaSource(new BufferedReader(new FileReader(source)), source.getCanonicalPath());
        }
        catch (Exception e) {
            throw new IllegalArgumentException("Failed to parse java source " + source, e);
        }
    }
}
