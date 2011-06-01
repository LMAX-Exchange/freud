package org.langera.freud.optional.css.cssrule;

import org.langera.freud.optional.css.cssrule.declaration.CssDeclaration;
import org.langera.freud.optional.css.cssrule.selector.CssSelector;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class CssRuleStub implements CssRule
{
    private List<CssSelector> cssSelectorList = Collections.emptyList();
    private List<CssDeclaration> cssDeclarationList = Collections.emptyList();

    public List<CssSelector> getCssSelectorList()
    {
        return cssSelectorList;
    }

    public List<CssDeclaration> getCssDeclarationList()
    {
        return cssDeclarationList;
    }

    public CssRuleStub cssSelectors(CssSelector... cssSelectors)
    {
        this.cssSelectorList = Arrays.asList(cssSelectors);
        return this;
    }

    public CssRuleStub cssDeclarations(CssDeclaration... cssDeclarations)
    {
        this.cssDeclarationList = Arrays.asList(cssDeclarations);
        return this;
    }
}
