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

package examples.org.freud;

import org.freud.analysed.text.TextLine;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.File;
import java.io.FilenameFilter;

import static org.freud.analysed.text.TextDsl.textLineWithin;
import static org.freud.analysed.text.TextDsl.textOf;
import static org.freud.java.Freud.analyse;
import static org.freud.java.Freud.filesIn;
import static org.freud.java.Freud.parameterise;
import static org.junit.Assert.assertTrue;
import static org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public final class TextExamplesParameterizedJunit {

    private static File root = new File(ClassLoader.getSystemResource("TextExamples").getFile());

    private TextLine analysed;
    private File source;

    public TextExamplesParameterizedJunit(final TextLine analysed,
                                          @SuppressWarnings("unused") final Object parent,
                                          final File source) {
        this.analysed = analysed;
        this.source = source;
    }

    @Test
    public void lineLengthDoesNotExceed80() {
        assertTrue(analyse(analysed, new LineDoesNoExceed(80)));
    }

    @Parameters(name = "{0}")
    public static Iterable<Object[]> data() {
        return parameterise(textLineWithin(textOf(filesIn(new DoesNotContainsLongFilenameFilter(), root))), 3);
    }

    private static class DoesNotContainsLongFilenameFilter implements FilenameFilter {
        @Override
        public boolean accept(final File dir, final String name) {
            return !name.contains("Long");
        }
    }

    private static class LineDoesNoExceed extends TypeSafeMatcher<TextLine> {

        private final int maxLength;

        private LineDoesNoExceed(final int maxLength) {
            this.maxLength = maxLength;
        }

        @Override
        protected boolean matchesSafely(final TextLine line) {
            return line.getLine().length() < maxLength;
        }

        @Override
        public void describeTo(final Description description) {
            description.appendText("line < " + maxLength);
        }
    }
}
