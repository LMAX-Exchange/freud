package org.freud.analysed.css.rule.declaration;

import org.freud.analysed.css.rule.CssRule;

public interface CssDeclaration {
    String getKey();

    String getValue();

    CssRule getCssRuleForDeclaration();
}
