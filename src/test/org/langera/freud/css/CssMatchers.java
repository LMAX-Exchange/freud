package org.langera.freud.css;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.internal.matchers.TypeSafeMatcher;
import org.langera.freud.css.cssrule.declaration.CssDeclaration;
import org.langera.freud.css.cssrule.selector.CssSelector;

import java.util.Arrays;

public final class CssMatchers
{
    public static Matcher<CssSelector> cssSelector(final CssSelector.Type type, final String selector)
    {
        return new CssSelectorMatcher(type, selector);
    }

    public static Matcher<CssDeclaration> cssDeclaration(final String key, final String... values)
    {
        return new CssDeclarationMatcher(key, values);
    }

    private CssMatchers()
    {
        // static utility
    }

    private static class CssSelectorMatcher extends TypeSafeMatcher<CssSelector>
    {
        private final String selector;
        private final CssSelector.Type type;

        private CssSelectorMatcher(final CssSelector.Type type, final String selector)
        {
            this.selector = selector;
            this.type = type;
        }

        @Override
        public boolean matchesSafely(CssSelector cssSelector)
        {
            return selector.equals(cssSelector.getSelectorString()) &&
                    cssSelector.getType() == type;
        }

        public void describeTo(Description description)
        {
            description.appendText("CssSelector[").
                    appendText(selector).appendText("]");
        }
    }


    private static class CssDeclarationMatcher extends TypeSafeMatcher<CssDeclaration>
    {
        private final String key;
        private final String[] values;

        private CssDeclarationMatcher(String key, String[] values)
        {
            this.key = key;
            this.values = values;
        }

        @Override
        public boolean matchesSafely(CssDeclaration cssDeclaration)
        {
            if (key.equals(cssDeclaration.getKey()))
            {
                final String[] actualValues = cssDeclaration.getValues();
                if (values.length == actualValues.length)
                {
                    for (int i = 0, size = values.length; i < size; i++)
                    {
                        if (!values[i].equals(actualValues[i]))
                        {
                            return false;
                        }
                    }
                    return true;
                }
                else
                {
                    return false;
                }
            }
            else
            {
                return false;
            }
        }

        public void describeTo(Description description)
        {
            description.appendText("CssDeclaration[").
                    appendText(key).appendText(Arrays.toString(values)).appendText("]");
        }
    }
}
