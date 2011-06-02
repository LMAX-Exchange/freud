package org.langera.freud.optional.property;

import org.langera.freud.core.matcher.RegexMatcherAdapter;
import org.langera.freud.core.matcher.RegexMatcherBuilder;

public final class PropertyDsl
{
    private PropertyDsl()
    {
        // static utility
    }

    public static RegexMatcherBuilder<Property> propertyKey()
    {
        return new RegexMatcherBuilder<Property>(new RegexMatcherAdapter<Property>()
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


    public static RegexMatcherBuilder<Property> propertyValue()
    {
        return new RegexMatcherBuilder<Property>(new RegexMatcherAdapter<Property>()
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
}
