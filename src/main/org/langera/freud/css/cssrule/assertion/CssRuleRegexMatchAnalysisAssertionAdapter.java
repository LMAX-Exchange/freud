package org.langera.freud.css.cssrule.assertion;

import org.langera.freud.css.cssrule.CssRule;
import org.langera.freud.css.cssrule.declaration.CssDeclaration;
import org.langera.freud.css.cssrule.selector.CssSelector;
import org.langera.freud.util.regex.RegexMatchAnalysisAssertionAdapter;

import java.util.List;

public final class CssRuleRegexMatchAnalysisAssertionAdapter implements RegexMatchAnalysisAssertionAdapter<CssRule>
{
    public String getStringToMatch(CssRule toBeAnalysed)
    {
        StringBuilder sb = new StringBuilder();
        final List<CssSelector> selectorList = toBeAnalysed.getCssSelectorList();
        for (CssSelector selector : selectorList)
        {
            sb.append(selector.getSelectorString()).append(' ');
        }
        sb.append("{\n");
        final List<CssDeclaration> declarationList = toBeAnalysed.getCssDeclarationList();
        for (CssDeclaration declaration : declarationList)
        {
            sb.append('\t').append(declaration.getKey()).append(": ").
                    append(declaration.getValue()).append("\n");
        }
        sb.append("}\n");
        return sb.toString();
    }

    public String assertionDisplayName()
    {
        return "CssRule";
    }
}
