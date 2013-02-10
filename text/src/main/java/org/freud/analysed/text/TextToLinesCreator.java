package org.freud.analysed.text;

import org.freud.core.Creator;
import org.freud.core.util.IoUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public final class TextToLinesCreator implements Creator<Text, Iterable<TextLine>> {

    @Override
    public Iterable<TextLine> create(final Text source) {
        if (source.getTextAsString() != null) {
            return getTextLines(source.getTextAsString());
        }
        return new TextLines(source.getTextAsStream());
    }

    private Iterable<TextLine> getTextLines(final String textAsString) {
        List<TextLine> textLineList;
        try {
            String[] lines = IoUtil.readLines(new StringReader(textAsString));
            textLineList = new ArrayList<TextLine>(lines.length);
            for (int i = 0, size = lines.length; i < size; i++) {
                textLineList.add(new TextLine(lines[i], i));
            }
        }
        catch (IOException e) {
            throw new IllegalStateException("Failed to parse text into lines", e);
        }
        return textLineList;
    }

    private static class TextLines implements Iterable<TextLine>, Iterator<TextLine> {

        private final BufferedReader text;
        private TextLine currentLine = null;
        private int lineCounter = 0;

        private TextLines(final Reader text) {
            this.text = (BufferedReader)
                    (BufferedReader.class.isAssignableFrom(text.getClass()) ? text : new BufferedReader(text));
        }

        @Override
        public boolean hasNext() {
            return currentLine != null || readNextLine();
        }

        @Override
        public TextLine next() {
            if (currentLine != null) {
                return returnCurrentLine();
            }
            readNextLine();
            if (currentLine != null) {
                return returnCurrentLine();
            }
            throw new NoSuchElementException();
        }

        private TextLine returnCurrentLine() {
            TextLine lineToReturn = currentLine;
            currentLine = null;
            return lineToReturn;
        }


        private boolean readNextLine() {
            try {
                String line = text.readLine();
                if (line == null) {
                    return false;
                }
                currentLine = new TextLine(line, lineCounter++);
                return true;
            }
            catch (IOException e) {
                throw new IllegalStateException("Failed to parse stream into lines", e);
            }
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Iterator<TextLine> iterator() {
            return this;
        }
    }
}
