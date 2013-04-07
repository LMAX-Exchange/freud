package org.freud.analysed.css;

import org.freud.analysed.css.rule.CssRule;
import org.freud.analysed.css.rule.declaration.CssDeclaration;
import org.freud.core.Creator;

public final class CssRuleToDeclarationsCreator implements Creator<CssRule, Iterable<CssDeclaration>> {
    @Override
    public Iterable<CssDeclaration> create(final CssRule source) {
        return source.getCssDeclarationList();
    }
}
