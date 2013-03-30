package org.freud.analysed.text;

import org.freud.core.FreudSource;
import org.freud.core.iterator.AnalysedObjects;
import org.freud.core.iterator.SubTypeAnalysedObjects;

import java.io.File;

public final class TextDsl {

    private TextDsl() {
        // static utility
    }

    @SuppressWarnings("unchecked")
    public static <T> Iterable<Text> textOf(Iterable<T> iterable, Class<T> type) {
        return textOf(new FreudSource(iterable, type));
    }

    @SuppressWarnings("unchecked")
    public static Iterable<Text> textOf(FreudSource source) {
        if (File.class.equals(source.getType())) {
            return new AnalysedObjects<File, Text>(source.getSources(), new TextFromFileCreator());
        }
        if (String.class.equals(source.getType())) {
            return new AnalysedObjects<String, Text>(source.getSources(), new TextFromStringCreator());
        }
        throw new UnsupportedOperationException("Unsupported conversion " + source.getType() + " to Text");
    }

    public static Iterable<TextLine> textLineWithin(Iterable<Text> texts) {
        return new SubTypeAnalysedObjects<Text, TextLine>(new TextToLinesCreator(), texts);
    }
}
