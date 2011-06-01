package org.langera.examples.css;

import org.langera.freud.core.Freud;
import org.langera.freud.core.FreudAnalyser;
import org.langera.freud.core.iterator.AnalysedObjectIterator;
import org.langera.freud.optional.css.Css;
import org.langera.freud.optional.css.cssrule.CssRule;
import org.langera.freud.optional.css.cssrule.declaration.CssDeclaration;
import org.langera.freud.optional.css.cssrule.selector.CssSelector;

import static org.langera.freud.core.matcher.FreudMatchers.no;
import static org.langera.freud.optional.css.cssrule.CssRuleMatchers.containsSelector;
import static org.langera.freud.optional.css.cssrule.CssRuleMatchers.lastIndexOfSelector;
import static org.langera.freud.optional.css.cssrule.CssRuleMatchers.selectors;
import static org.langera.freud.optional.css.cssrule.declaration.CssDeclarationMatchers.declarationKeyMatches;
import static org.langera.freud.optional.css.cssrule.declaration.CssDeclarationMatchers.declarationValueMatches;
import static org.langera.freud.optional.css.cssrule.selector.CssSelectorMatchers.classSelector;
import static org.langera.freud.optional.css.cssrule.selector.CssSelectorMatchers.idSelector;
import static org.langera.freud.optional.css.cssrule.selector.CssSelectorMatchers.selectorContains;

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
                assertThat(no(selectorContains("[A-Z]")));
    }

    public static FreudAnalyser cssDisplayDeclarationIsAlwaysNone(final AnalysedObjectIterator<Css> iterator)
    {
        return Freud.iterateOver(CssDeclaration.class).within(iterator).
                forEach(declarationKeyMatches("display")).
                assertThat(declarationValueMatches("none"));
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