package org.langera.freud.css.cssrule.assertion;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.langera.freud.css.cssrule.CssRule;
import org.langera.freud.css.cssrule.selector.CssSelector;

public final class HasSelectorTypeAssertion extends TypeSafeMatcher<CssRule>
{
    private final CssSelector.Type selectorType;

    public HasSelectorTypeAssertion(CssSelector.Type selectorType)
    {
        this.selectorType = selectorType;
    }

    public final boolean matchesSafely(final CssRule toBeAnalysed)
    {
        for (CssSelector cssSelector : toBeAnalysed.getCssSelectorList())
        {
            if (cssSelector.getType() == selectorType)
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }

        HasSelectorTypeAssertion that = (HasSelectorTypeAssertion) o;

        if (selectorType != that.selectorType)
        {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        return selectorType.hashCode();
    }

    @Override
    public String toString()
    {
        return "hasCssSelectorType(" + selectorType.name() + ")";
    }

    public void describeTo(Description description)
    {
        description.appendText(toString());
    }
}
