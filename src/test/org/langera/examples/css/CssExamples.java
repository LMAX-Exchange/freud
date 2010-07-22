package org.langera.examples.css;

import org.langera.freud.Analysis;
import org.langera.freud.css.Css;
import org.langera.freud.css.cssrule.selector.CssSelector;
import org.langera.freud.util.collection.AnalysedObjectIterator;
import org.langera.freudgenerated.css.CssAnalysis;

public final class CssExamples
{
    private CssExamples()
    {
        // a class of static methods - should not be initialised
    }

    public static Analysis classOrIdCssSelectorsNameMustNotContainUpperCaseCharacters(final AnalysedObjectIterator<Css> iterator)
    {
        return new CssAnalysis(iterator)
        {
            {
                forEach(classSelector().or(idSelector()));
                assertThat(no(selector().contains("[A-Z]")));
            }
        };
    }

    public static Analysis cssDisplayDeclarationIsAlwaysNone(final AnalysedObjectIterator<Css> iterator)
    {
        return new CssAnalysis(iterator)
        {
            {
                forEach(declaration().matches("display"));
                assertThat(declarationValue("none"));
            }
        };
    }

    /**
     * @see https://developer.mozilla.org/en/Writing_Efficient_CSS
     */
    public static Analysis doNotQualifyIdRuleWithTagName(final AnalysedObjectIterator<Css> iterator)
    {
        return new CssAnalysis(iterator)
        {
            {
                forEach(cssRule());
                assertThat(no(containsSelector(CssSelector.Type.TAG).and(
                        lastIndexOfSelector(CssSelector.Type.TAG).lessThan(lastIndexOfSelector(CssSelector.Type.ID)))));
            }
        };
    }

    /**
     * @see https://developer.mozilla.org/en/Writing_Efficient_CSS
     */
    public static Analysis doNotQualifyIdRuleWithClassName(final AnalysedObjectIterator<Css> iterator)
    {
        return new CssAnalysis(iterator)
        {
            {
                forEach(cssRule());
                assertThat(no(containsSelector(CssSelector.Type.CLASS).and(
                        lastIndexOfSelector(CssSelector.Type.CLASS).lessThan(lastIndexOfSelector(CssSelector.Type.ID)))));
            }
        };
    }

    /**
     * @see http://css-tricks.com/efficiently-rendering-css/
     */
    public static Analysis descendantSelectorsAreTheWorst(final AnalysedObjectIterator<Css> iterator)
    {
        return new CssAnalysis(iterator)
        {
            {
                forEach(cssRule());
                assertThat(numberOfSelectors(CssSelector.Type.TAG).lessThanOrEqualTo(1));
            }
        };
    }
}