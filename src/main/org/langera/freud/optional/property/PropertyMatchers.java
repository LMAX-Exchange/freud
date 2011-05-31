package org.langera.freud.optional.property;

import org.langera.freud.core.matcher.FreudMatcher;
import org.langera.freud.core.matcher.RegexMatcher;
import org.langera.freud.core.matcher.RegexMatcherAdapter;

public final class PropertyMatchers
{
    private PropertyMatchers()
    {
        // static utility
    }

    public static FreudMatcher<Property> propertyKeyMatches(final String regex)
    {
        return keyRegexMatcher(regex, true);
    }

    public static FreudMatcher<Property> propertyValueMatches(final String regex)
    {
        return valueRegexMatcher(regex, true);
    }


    public static FreudMatcher<Property> propertyKeyContains(final String regex)
    {
        return keyRegexMatcher(regex, false);
    }

    public static FreudMatcher<Property> propertyValueContains(final String regex)
    {
        return valueRegexMatcher(regex, false);
    }

    private static FreudMatcher<Property> valueRegexMatcher(final String regex, final boolean completeMatch)
    {
        return new RegexMatcher<Property>(regex, completeMatch, new RegexMatcherAdapter<Property>()
        {
            @Override
            public String getStringToMatch(final Property toBeAnalysed)
            {
                return toBeAnalysed.getValue();
            }

            @Override
            public String matcherDisplayName()
            {
                return "PropertyValue";
            }
        });
    }

    private static FreudMatcher<Property> keyRegexMatcher(final String regex, final boolean completeMatch)
    {
        return new RegexMatcher<Property>(regex, completeMatch, new RegexMatcherAdapter<Property>()
        {
            @Override
            public String getStringToMatch(final Property toBeAnalysed)
            {
                return toBeAnalysed.getKey();
            }

            @Override
            public String matcherDisplayName()
            {
                return "PropertyKey";
            }
        });
    }
}
