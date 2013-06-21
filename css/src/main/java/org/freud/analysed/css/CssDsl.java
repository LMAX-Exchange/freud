package org.freud.analysed.css;

import org.freud.analysed.css.jdom.CssRulesJdomFromFileCreator;
import org.freud.analysed.css.jdom.CssRulesJdomFromStringCreator;
import org.freud.analysed.css.jdom.CssRulesJdomFromUrlCreator;
import org.freud.analysed.css.rule.CssRule;
import org.freud.analysed.css.rule.declaration.CssDeclaration;
import org.freud.analysed.css.rule.selector.CssSelector;
import org.freud.core.FreudSource;
import org.freud.core.iterator.SubTypeAnalysedObjects;

import java.io.File;
import java.net.URL;

import static org.freud.core.FreudSource.typeOf;

public final class CssDsl {

    private CssDsl() {
        // static utility
    }

    @SuppressWarnings("unchecked")
    public static <T> Iterable<CssRule> cssRulesOf(Iterable<T> iterable) {
        Class type = typeOf(iterable);
        return cssRulesOf(new FreudSource(iterable, type));
    }

    @SuppressWarnings("unchecked")
    public static Iterable<CssRule> cssRulesOf(FreudSource source) {
        if (File.class.equals(source.getType())) {
            return new SubTypeAnalysedObjects<File, CssRule>(new CssRulesJdomFromFileCreator(), source.getSources());
        }
        if (URL.class.equals(source.getType())) {
            return new SubTypeAnalysedObjects<URL, CssRule>(new CssRulesJdomFromUrlCreator(), source.getSources());
        }
        if (String.class.equals(source.getType())) {
            return new SubTypeAnalysedObjects<String, CssRule>(new CssRulesJdomFromStringCreator(), source.getSources());
        }
        throw new UnsupportedOperationException("Unsupported conversion " + source.getType() + " to Css");
    }

    public static Iterable<CssSelector> cssSelectorsWithin(Iterable<CssRule> cssRules) {
        return new SubTypeAnalysedObjects<CssRule, CssSelector>(new CssRuleToSelectorsCreator(), cssRules);
    }

    public static Iterable<CssDeclaration> cssDeclarationsWithin(Iterable<CssRule> cssRules) {
        return new SubTypeAnalysedObjects<CssRule, CssDeclaration>(new CssRuleToDeclarationsCreator(), cssRules);
    }

}
