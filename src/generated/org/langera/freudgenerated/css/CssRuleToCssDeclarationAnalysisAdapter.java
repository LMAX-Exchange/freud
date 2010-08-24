// Freud generated code 2010-08-24 21:26:16
package org.langera.freudgenerated.css;

import org.langera.freud.NestedTypeAnalysisAdapter;
import org.langera.freud.css.cssrule.CssRule;
import org.langera.freud.css.cssrule.declaration.CssDeclaration;

public final class CssRuleToCssDeclarationAnalysisAdapter implements NestedTypeAnalysisAdapter<CssRule, CssDeclaration>
{
    private static final CssRuleToCssDeclarationAnalysisAdapter SINGLETON = new CssRuleToCssDeclarationAnalysisAdapter();

    private CssRuleToCssDeclarationAnalysisAdapter()
    {
        // singleton
    }

    public static CssRuleToCssDeclarationAnalysisAdapter getInstance()
    {
        return SINGLETON;
    }

    public Iterable<CssDeclaration> getNestedObjectsToAnalyse(CssRule toBeAnalysed)
    {
        return toBeAnalysed.getCssDeclarationList();
    }
}