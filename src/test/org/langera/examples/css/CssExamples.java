package org.langera.examples.css;

import org.langera.freud.core.Freud;
import org.langera.freud.core.FreudAnalyser;
import org.langera.freud.core.iterator.AnalysedObjectIterator;
import org.langera.freud.optional.css.Css;
import org.langera.freud.optional.css.cssrule.CssRule;
import org.langera.freud.optional.css.cssrule.declaration.CssDeclaration;
import org.langera.freud.optional.css.cssrule.selector.CssSelector;

import static org.langera.freud.core.matcher.FreudDsl.no;
import static org.langera.freud.optional.css.cssrule.CssRuleDsl.containsSelector;
import static org.langera.freud.optional.css.cssrule.CssRuleDsl.lastIndexOfSelector;
import static org.langera.freud.optional.css.cssrule.CssRuleDsl.selectors;
import static org.langera.freud.optional.css.cssrule.declaration.CssDeclarationDsl.declarationKey;
import static org.langera.freud.optional.css.cssrule.declaration.CssDeclarationDsl.declarationValue;
import static org.langera.freud.optional.css.cssrule.selector.CssSelectorDsl.classSelector;
import static org.langera.freud.optional.css.cssrule.selector.CssSelectorDsl.idSelector;
import static org.langera.freud.optional.css.cssrule.selector.CssSelectorDsl.selector;

public final class CssExamples
{
    private CssExamples()
    {
        // a class of static methods - should not be initialised
    }

    public static FreudAnalyser classOrIdCssSelectorsNameMustNotContainUpperCaseCharacters(final AnalysedObjectIterator<Css> iterator)
    {
        return Freud.iterateOver(CssSelector.class).within(iterator).
                forEach(classSelector().or(idSelector())).
                assertThat(no(selector().contains("[A-Z]")));
    }

    public static FreudAnalyser cssDisplayDeclarationIsAlwaysNone(final AnalysedObjectIterator<Css> iterator)
    {
        return Freud.iterateOver(CssDeclaration.class).within(iterator).
                forEach(declarationKey().matches("display")).
                assertThat(declarationValue().matches("none"));
    }

    /**
     * @see https://developer.mozilla.org/en/Writing_Efficient_CSS
     */
    public static FreudAnalyser doNotQualifyIdRuleWithTagName(final AnalysedObjectIterator<Css> iterator)
    {
        return Freud.iterateOver(CssRule.class).within(iterator).
        assertThat(no(containsSelector(CssSelector.Type.TAG).and(
                lastIndexOfSelector(CssSelector.Type.TAG).lessThan(lastIndexOfSelector(CssSelector.Type.ID)))));
    }

    /**
     * @see https://developer.mozilla.org/en/Writing_Efficient_CSS
     */
    public static FreudAnalyser doNotQualifyIdRuleWithClassName(final AnalysedObjectIterator<Css> iterator)
    {
        return Freud.iterateOver(CssRule.class).within(iterator).
        assertThat(no(containsSelector(CssSelector.Type.CLASS).and(
                lastIndexOfSelector(CssSelector.Type.CLASS).lessThan(lastIndexOfSelector(CssSelector.Type.ID)))));
    }

    /**
     * @see http://css-tricks.com/efficiently-rendering-css/
     */
    public static FreudAnalyser descendantSelectorsAreTheWorst(final AnalysedObjectIterator<Css> iterator)
    {
        return Freud.iterateOver(CssRule.class).within(iterator).
        assertThat(selectors(CssSelector.Type.TAG).lessThanOrEqualTo(1));
    }
}