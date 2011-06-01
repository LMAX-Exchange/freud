package org.langera.freud.optional.css.cssrule.declaration;

import org.langera.freud.optional.css.cssrule.CssRule;

public final class CssDeclarationStub implements CssDeclaration
{
    private final String key;
    private final String value;
    private final CssRule rule;

    public CssDeclarationStub(final String key, final String value, final CssRule rule)
    {
        this.key = key;
        this.value = value;
        this.rule = rule;
    }

    @Override
    public String getKey()
    {
        return key;
    }

    @Override
    public String getValue()
    {
        return value;
    }

    @Override
    public CssRule getCssRuleForDeclaration()
    {
        return rule;
    }
}
