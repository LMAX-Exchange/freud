package org.freud.analysed.css.jdom;

import org.freud.analysed.css.rule.CssRule;
import org.freud.core.Creator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import static org.freud.analysed.css.jdom.CssJdomParser.parseCssRules;

public final class CssRulesJdomFromFileCreator implements Creator<File, Iterable<CssRule>> {
    @Override
    public Iterable<CssRule> create(final File source) {
        try {
            return parseCssRules(new BufferedReader(new FileReader(source)));
        }
        catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }
}
