package org.freud.analysed.css;

import org.freud.analysed.css.rule.CssRule;
import org.freud.analysed.css.rule.selector.CssSelector;
import org.freud.core.Creator;

public final class CssRuleToSelectorsCreator implements Creator<CssRule, Iterable<CssSelector>> {
    @Override
    public Iterable<CssSelector> create(final CssRule source) {
        return source.getCssSelectorList();
    }
}
