package org.freud.analysed.css.rule;

import org.freud.analysed.css.rule.declaration.CssDeclaration;
import org.freud.analysed.css.rule.selector.CssSelector;

import java.util.List;

public interface CssRule {
    public List<CssSelector> getCssSelectorList();

    public List<CssDeclaration> getCssDeclarationList();
}
