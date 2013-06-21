package org.freud.analysed.css.jdom;

import org.freud.analysed.css.rule.CssRule;
import org.freud.core.Creator;

import java.io.StringReader;

import static org.freud.analysed.css.jdom.CssJdomParser.parseCssRules;

public final class CssRulesJdomFromStringCreator implements Creator<String, Iterable<CssRule>> {
    @Override
    public Iterable<CssRule> create(final String source) {
        try {
            return parseCssRules(new StringReader(source));
        }
        catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }
}
