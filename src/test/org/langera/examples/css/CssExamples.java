package org.langera.examples.css;

import org.langera.freud.Analysis;
import org.langera.freud.css.Css;
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
}