package org.langera.freud.css.cssrule;

import org.langera.freud.css.cssrule.declaration.CssDeclaration;
import org.langera.freud.css.cssrule.selector.CssSelector;

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

    public void setCssSelectorList(List<CssSelector> cssSelectorList)
    {
        this.cssSelectorList = cssSelectorList;
    }

    public void setCssDeclarationList(List<CssDeclaration> cssDeclarationList)
    {
        this.cssDeclarationList = cssDeclarationList;
    }
}
