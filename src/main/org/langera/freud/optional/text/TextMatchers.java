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
        return new RegexMatcher<Text>(regex, true, new RegexMatcherAdapter<Text>()
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
