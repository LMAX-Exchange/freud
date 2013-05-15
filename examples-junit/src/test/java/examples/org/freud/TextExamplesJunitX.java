package examples.org.freud;

import org.freud.analysed.text.TextLine;
import org.freud.java.Freud;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Test;

import static examples.org.freud.matchers.TextLineDsl.lineLength;
import static java.util.Arrays.asList;
import static org.freud.analysed.text.TextDsl.textLineWithin;
import static org.freud.analysed.text.TextDsl.textOf;

public final class TextExamplesJunitX {

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
