package org.langera.freud.optional.text;

import org.langera.freud.core.matcher.RegexMatcherAdapter;
import org.langera.freud.core.matcher.RegexMatcherBuilder;

public final class TextDsl
{
    private TextDsl()
    {
        // static utility
    }

    public static RegexMatcherBuilder<Text> text()
    {
        return new RegexMatcherBuilder<Text>(new RegexMatcherAdapter<Text>()
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
