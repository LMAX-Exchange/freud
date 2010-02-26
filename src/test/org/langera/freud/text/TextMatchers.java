package org.langera.freud.text;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.langera.freud.text.line.TextLine;

public final class TextMatchers
{
    public static TextLineMatcher line(final int lineNumber, final String line)
    {
        return new TextLineMatcher(line, lineNumber);
    }

    private TextMatchers()
    {
        // static utility
    }

    private static class TextLineMatcher extends TypeSafeMatcher<TextLine>
    {
        private final int lineNumber;
        private final String line;

        private TextLineMatcher(final String line, final int lineNumber)
        {
            this.line = line;
            this.lineNumber = lineNumber;
        }

        @Override
        public boolean matchesSafely(TextLine item)
        {
            return item.getLineNumber() == lineNumber && line.equals(item.getLine());
        }

        public void describeTo(Description description)
        {
            description.appendText("line[")
                    .appendText(lineNumber + ":")
                    .appendText(line)
                    .appendText("]");
        }
    }
}
