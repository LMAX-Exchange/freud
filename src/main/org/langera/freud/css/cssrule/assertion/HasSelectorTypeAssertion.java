package org.langera.freud.css.cssrule.assertion;

import org.langera.freud.AnalysisAssertion;
import org.langera.freud.css.cssrule.CssRule;
import org.langera.freud.css.cssrule.selector.CssSelector;

public final class HasSelectorTypeAssertion implements AnalysisAssertion<CssRule>
{
    private final CssSelector.Type selectorType;

    public HasSelectorTypeAssertion(CssSelector.Type selectorType)
    {
        this.selectorType = selectorType;
    }

    public boolean analyse(CssRule toBeAnalysed)
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
}
