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
import org.freud.java.Freud;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Test;

import static examples.org.freud.matchers.TextLineMatchers.lineLength;
import static java.util.Arrays.asList;
import static org.freud.analysed.text.TextDsl.textLineWithin;
import static org.freud.analysed.text.TextDsl.textOf;

public final class TextExamplesJunit {

    private AnalysisListenerStub listener = new AnalysisListenerStub();

    @Test
    public void lineLengthDoesNotExceed80() {
        Freud.iterateOver(TextLine.class).assertThat(lineLength().lessThan(80)).in(
                textLineWithin(textOf(asList(
                        ClassLoader.getSystemResource("TextExamples/textFile"),
                        ClassLoader.getSystemResource("TextExamples/textFileWithLongLine"))))).
                analyse(listener);

        listener.assertNotFiltered();
        listener.assertPassed(10);
        listener.assertFailed(line(0, "123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890"));
    }

    private static TextLineMatcher line(final int lineNumber, final String line) {
        return new TextLineMatcher(line, lineNumber);
    }

    private static class TextLineMatcher extends TypeSafeMatcher<TextLine> {
        private final int lineNumber;
        private final String line;

        private TextLineMatcher(final String line, final int lineNumber) {
            this.line = line;
            this.lineNumber = lineNumber;
        }

        @Override
        public final boolean matchesSafely(final TextLine item) {
            return item.getLineNumber() == lineNumber && line.equals(item.getLine());
        }

        public void describeTo(Description description) {
            description.appendText("line[")
                    .appendText(lineNumber + ":")
                    .appendText(line)
                    .appendText("]");
        }
    }

}
