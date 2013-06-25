/*
 * Copyright 2013 LMAX Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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

import static org.freud.core.util.IoUtil.safeClose;

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

    private static class TextLines implements Iterable<TextLine> {

        private final BufferedReader text;

        private TextLines(final Reader text) {
            this.text = (BufferedReader)
                    (BufferedReader.class.isAssignableFrom(text.getClass()) ? text : new BufferedReader(text));
        }

        @Override
        public Iterator<TextLine> iterator() {
            return new InternalIterator();
        }

        private class InternalIterator implements Iterator<TextLine> {

            private List<TextLine> currentLine = new ArrayList<TextLine>();
            private int lineCounter = 0;
            private int linesIterated = 0;

            @Override
            public boolean hasNext() {
                return lineCounter > linesIterated || readNextLine() != null;
            }

            @Override
            public TextLine next() {
                if (lineCounter > linesIterated) {
                    return currentLine.get(linesIterated++);
                }
                TextLine nextLine = readNextLine();
                if (nextLine != null) {
                    linesIterated++;
                    return nextLine;
                }
                throw new NoSuchElementException();
            }

            @Override
            public final void remove() {
                throw new UnsupportedOperationException();
            }


            private TextLine readNextLine() {
                try {
                    String line = text.readLine();
                    if (line == null) {
                        safeClose(text);
                        return null;
                    }
                    final TextLine nextLine = new TextLine(line, lineCounter++);
                    currentLine.add(nextLine);
                    return nextLine;
                }
                catch (IOException e) {
                    safeClose(text);
                    return null;
                }
            }
        }
    }
}
