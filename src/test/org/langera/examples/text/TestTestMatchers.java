package org.langera.examples.text;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.langera.freud.optional.text.textline.TextLine;

final class TextTestMatchers
{
    public static TextLineMatcher line(final int lineNumber, final String line)
    {
        return new TextLineMatcher(line, lineNumber);
    }

    private TextTestMatchers()
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
        public final boolean matchesSafely(final TextLine item)
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

