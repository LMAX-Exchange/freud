package examples.org.freud.matchers;

import org.freud.analysed.text.TextLine;
import org.freud.java.matcher.IntOperatorMatcherAdapter;
import org.freud.java.matcher.IntOperatorMatcherBuilder;
import org.freud.java.matcher.RegexMatcherAdapter;
import org.freud.java.matcher.StringMatcherBuilder;

public final class TextLineMatchers {
    private TextLineMatchers() {
        // static utility
    }

    public static StringMatcherBuilder<TextLine> line() {
        return new StringMatcherBuilder<TextLine>(new RegexMatcherAdapter<TextLine>() {
            @Override
            public String getStringToMatch(final TextLine toBeAnalysed) {
                return toBeAnalysed.getLine();
            }

            @Override
            public String matcherDisplayName() {
                return "TextLine";
            }
        });
    }

    public static IntOperatorMatcherBuilder<TextLine> lineLength() {
        return new IntOperatorMatcherBuilder<TextLine>(new IntOperatorMatcherAdapter<TextLine>() {
            @Override
            public int valueOf(final TextLine matched) {
                return matched.getLine().length();
            }

            @Override
            public String matcherDisplayName() {
                return "LineLength()";
            }
        });
    }

    public static IntOperatorMatcherBuilder<TextLine> lineNumber() {
        return new IntOperatorMatcherBuilder<TextLine>(new IntOperatorMatcherAdapter<TextLine>() {
            @Override
            public int valueOf(final TextLine matched) {
                return matched.getLineNumber();
            }

            @Override
            public String matcherDisplayName() {
                return "LineNumber()";
            }
        });
    }
}
