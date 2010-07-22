package org.langera.freud.css.cssrule.assertion;

import org.langera.freud.AnalysisCalculation;
import org.langera.freud.css.cssrule.CssRule;
import org.langera.freud.css.cssrule.selector.CssSelector;

import java.util.List;

public final class SelectorTypeLastIndexCalculation implements AnalysisCalculation<CssRule>
{
    private final CssSelector.Type selectorType;

    public SelectorTypeLastIndexCalculation(CssSelector.Type selectorType)
    {
        this.selectorType = selectorType;
    }

    public int analyse(CssRule toBeAnalysed)
    {
        final List<CssSelector> selectorList = toBeAnalysed.getCssSelectorList();
        for (int i = selectorList.size() - 1; i >= 0; i--)
        {
            CssSelector selector = selectorList.get(i);
            if (selector.getType() == selectorType)
            {
                return i;
            }
        }
        return -1;
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

        SelectorTypeLastIndexCalculation that = (SelectorTypeLastIndexCalculation) o;

        if (selectorType != that.selectorType)
        {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        return (selectorType == null) ? 0 : selectorType.hashCode();
    }

    @Override
    public String toString()
    {
        return "hasCssSelectorType(" + ((selectorType == null) ? "" : selectorType.name()) + ")";
    }
}