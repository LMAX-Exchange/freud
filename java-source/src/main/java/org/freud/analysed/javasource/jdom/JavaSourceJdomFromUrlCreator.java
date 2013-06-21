package org.freud.analysed.javasource.jdom;

import org.freud.analysed.javasource.JavaSource;
import org.freud.core.Creator;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import static org.freud.analysed.javasource.jdom.JavaSourceJdomParser.parseJavaSource;

public final class JavaSourceJdomFromUrlCreator implements Creator<URL, JavaSource> {
    @Override
    public JavaSource create(final URL source) {
        try {
            return parseJavaSource(new BufferedReader(new InputStreamReader(source.openStream())), source.toExternalForm());
        }
        catch (Exception e) {
            throw new IllegalArgumentException("Failed to parse java source " + source, e);
        }
    }
}
