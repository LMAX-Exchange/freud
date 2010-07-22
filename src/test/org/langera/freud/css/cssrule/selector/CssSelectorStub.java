package org.langera.freud.css.cssrule.selector;

import org.langera.freud.css.cssrule.CssRule;

public final class CssSelectorStub implements CssSelector
{
    private String selectorString;
    private Type type;
    private CssRule cssRule;
    private Combinator combinator;

    public CssSelectorStub(Type type, String selectorString, CssRule cssRule)
    {
        this(type, selectorString, Combinator.DESCENDANT, cssRule);
    }

    public CssSelectorStub(Type type, String selectorString, Combinator combinator, CssRule cssRule)
    {
        this.selectorString = selectorString;
        this.type = type;
        this.cssRule = cssRule;
        this.combinator = combinator;
    }

    public String getSelectorString()
    {
        return selectorString;
    }

    public Type getType()
    {
        return type;
    }

    public CssRule getCssRuleForSelector()
    {
        return cssRule;
    }

    public Combinator getCombinator()
    {
        return combinator;
    }

    public void setSelectorString(String selectorString)
    {
        this.selectorString = selectorString;
    }

    public void setType(Type type)
    {
        this.type = type;
    }

    public void setCssRule(CssRule cssRule)
    {
        this.cssRule = cssRule;
    }
}
