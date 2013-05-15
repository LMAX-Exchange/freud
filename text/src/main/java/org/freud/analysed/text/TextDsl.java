package org.freud.analysed.text;

import org.freud.core.FreudSource;
import org.freud.core.iterator.AnalysedObjects;
import org.freud.core.iterator.SubTypeAnalysedObjects;

import java.io.File;
import java.net.URL;

import static org.freud.core.FreudSource.typeOf;

public final class TextDsl {

    private TextDsl() {
        // static utility
    }

    @SuppressWarnings("unchecked")
    public static <T> Iterable<Text> textOf(Iterable<T> iterable) {
        Class type = typeOf(iterable);
        return textOf(new FreudSource(iterable, type));
    }

    @SuppressWarnings("unchecked")
    public static Iterable<Text> textOf(FreudSource source) {
        if (File.class.equals(source.getType())) {
            return new AnalysedObjects<File, Text>(new TextFromFileCreator(), source.getSources());
        }
        if (URL.class.equals(source.getType())) {
            return new AnalysedObjects<URL, Text>(new TextFromUrlCreator(), source.getSources());
        }
        if (String.class.equals(source.getType())) {
            return new AnalysedObjects<String, Text>(new TextFromStringCreator(), source.getSources());
        }
        throw new UnsupportedOperationException("Unsupported conversion " + source.getType() + " to Text");
    }

    public static Iterable<TextLine> textLineWithin(Iterable<Text> texts) {
        return new SubTypeAnalysedObjects<Text, TextLine>(new TextToLinesCreator(), texts);
    }
}
