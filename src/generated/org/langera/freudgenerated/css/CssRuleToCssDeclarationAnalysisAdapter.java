// Freud generated code 2010-02-14 19:03:24
package org.langera.freudgenerated.css;

import org.langera.freud.*;
import org.langera.freud.dsl.*;
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