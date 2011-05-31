package org.langera.freud.optional.text;

import org.langera.freud.core.matcher.FreudMatcher;
import org.langera.freud.core.matcher.RegexMatcher;
import org.langera.freud.core.matcher.RegexMatcherAdapter;

public final class TextMatchers
{
    private TextMatchers()
    {
        // static utility
    }

    public static FreudMatcher<Text> textMatches(final String regex)
    {
        return regexMatcher(regex, true);
    }

    public static FreudMatcher<Text> textContains(final String regex)
    {
        return regexMatcher(regex, false);
    }

    private static FreudMatcher<Text> regexMatcher(final String regex, final boolean completeMatch)
    {
        return new RegexMatcher<Text>(regex, completeMatch, new RegexMatcherAdapter<Text>()
        {
            @Override
            public String getStringToMatch(final Text toBeAnalysed)
            {
                return toBeAnalysed.getText();
            }

            @Override
            public String matcherDisplayName()
            {
                return "Text";
            }
        });
    }
}
