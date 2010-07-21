package org.langera.freud.css.cssrule.assertion;

import org.langera.freud.AnalysisCalculation;
import org.langera.freud.css.cssrule.CssRule;
import org.langera.freud.css.cssrule.selector.CssSelector;

public final class SelectorCountingCalculation implements AnalysisCalculation<CssRule>
{
    private final CssSelector.Type selectorType;

    public SelectorCountingCalculation()
    {
        this(null);
    }

    public SelectorCountingCalculation(CssSelector.Type selectorType)
    {
        this.selectorType = selectorType;
    }

    public int analyse(CssRule toBeAnalysed)
    {
        int count = 0;
        for (CssSelector cssSelector : toBeAnalysed.getCssSelectorList())
        {
            if (selectorType != null)
            {
                if (selectorType == cssSelector.getType())
                {
                    count++;
                }
            }
            else
            {
                count++;
            }
        }
        return count;
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

        SelectorCountingCalculation that = (SelectorCountingCalculation) o;

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