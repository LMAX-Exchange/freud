package org.freud.analysed.css.jdom;

import org.freud.analysed.css.rule.CssRule;
import org.freud.core.Creator;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import static org.freud.analysed.css.jdom.CssJdomParser.parseCssRules;

public final class CssRulesJdomFromUrlCreator implements Creator<URL, Iterable<CssRule>> {
    @Override
    public Iterable<CssRule> create(final URL source) {
        try {
            return parseCssRules(new BufferedReader(new InputStreamReader(source.openStream())));
        }
        catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }
}
