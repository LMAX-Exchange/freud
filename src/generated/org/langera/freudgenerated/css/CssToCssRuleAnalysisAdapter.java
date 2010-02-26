// Freud generated code 2010-02-14 19:03:24
package org.langera.freudgenerated.css;

import org.langera.freud.*;
import org.langera.freud.dsl.*;
import org.langera.freud.css.Css;
import org.langera.freud.css.cssrule.CssRule;

public final class CssToCssRuleAnalysisAdapter implements NestedTypeAnalysisAdapter<Css, CssRule>
{
    private static final CssToCssRuleAnalysisAdapter SINGLETON = new CssToCssRuleAnalysisAdapter();

    private CssToCssRuleAnalysisAdapter()
    {
        // singleton
    }

    public static CssToCssRuleAnalysisAdapter getInstance()
    {
        return SINGLETON;
    }

    public Iterable<CssRule> getNestedObjectsToAnalyse(Css toBeAnalysed)
    {
        return toBeAnalysed.getCssRuleList();
    }
}