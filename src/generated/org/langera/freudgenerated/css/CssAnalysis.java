// Freud generated code [CssAnalysis] [2010-08-24 21:26:16]
package org.langera.freudgenerated.css;

import org.langera.freud.AbstractAnalysis;
import org.langera.freud.Analysis;
import org.langera.freud.NestedTypeAnalysisAdapter;
import org.langera.freud.NestedTypeAnalysisAdapterChain;
import org.langera.freud.css.Css;
import org.langera.freud.css.CssAnalysisBuilder;
import org.langera.freud.css.CssDsl;
import org.langera.freud.css.cssrule.CssRule;
import org.langera.freud.css.cssrule.CssRuleAnalysisBuilder;
import org.langera.freud.css.cssrule.CssRuleDsl;
import org.langera.freud.css.cssrule.declaration.CssDeclaration;
import org.langera.freud.css.cssrule.declaration.CssDeclarationAnalysisBuilder;
import org.langera.freud.css.cssrule.declaration.CssDeclarationDsl;
import org.langera.freud.css.cssrule.selector.CssSelector;
import org.langera.freud.css.cssrule.selector.CssSelectorAnalysisBuilder;
import org.langera.freud.css.cssrule.selector.CssSelectorDsl;
import org.langera.freud.dsl.BooleanOperatorDsl;
import org.langera.freud.dsl.NumericOperatorDsl;
import org.langera.freud.dsl.ReadableDsl;
import org.langera.freud.util.collection.AnalysedObjectIterator;

public class CssAnalysis extends AbstractAnalysis<Css, CssAnalysis>
        implements
        CssDsl,
        CssDeclarationDsl,
        CssSelectorDsl,
        CssRuleDsl,
        Analysis
{
    public CssAnalysis(AnalysedObjectIterator<Css> cssAnalysedObjectIterator)
    {
        super(cssAnalysedObjectIterator, Css.class);
    }

    protected NestedTypeAnalysisAdapter getAnalysisAdapter(final Class type, Class nestedType)
    {
        if (type == CssRule.class)
        {
            if (nestedType == CssSelector.class)
            {
                return CssRuleToCssSelectorAnalysisAdapter.getInstance();
            }
            if (nestedType == CssDeclaration.class)
            {
                return CssRuleToCssDeclarationAnalysisAdapter.getInstance();
            }
            return null;
        }
        if (type == Css.class)
        {
            if (nestedType == CssRule.class)
            {
                return CssToCssRuleAnalysisAdapter.getInstance();
            }
            if (nestedType == CssSelector.class)
            {
                return NestedTypeAnalysisAdapterChain.createChain(
                        CssToCssRuleAnalysisAdapter.getInstance(),
                        CssRuleToCssSelectorAnalysisAdapter.getInstance());
            }
            if (nestedType == CssDeclaration.class)
            {
                return NestedTypeAnalysisAdapterChain.createChain(
                        CssToCssRuleAnalysisAdapter.getInstance(),
                        CssRuleToCssDeclarationAnalysisAdapter.getInstance());
            }
            return null;
        }
        return null;
    }

    //////////////////////////////////////////////////////////////////////////////////
    /// DSL

    public BooleanOperatorDsl<CssAnalysisBuilder> css()
    {
        BooleanOperatorDsl<CssAnalysisBuilder> builder = new CssAnalysisBuilder().css(
        );
        return builder;
    }

    public ReadableDsl<CssDeclarationAnalysisBuilder> declaration()
    {
        ReadableDsl<CssDeclarationAnalysisBuilder> builder = new CssDeclarationAnalysisBuilder().declaration(
        );
        return builder;
    }

    public ReadableDsl<CssDeclarationAnalysisBuilder> declarationValue(String regex)
    {
        ReadableDsl<CssDeclarationAnalysisBuilder> builder = new CssDeclarationAnalysisBuilder().declarationValue(
                regex
        );
        return builder;
    }

    public ReadableDsl<CssSelectorAnalysisBuilder> selector()
    {
        ReadableDsl<CssSelectorAnalysisBuilder> builder = new CssSelectorAnalysisBuilder().selector(
        );
        return builder;
    }

    public ReadableDsl<CssSelectorAnalysisBuilder> classSelector()
    {
        ReadableDsl<CssSelectorAnalysisBuilder> builder = new CssSelectorAnalysisBuilder().classSelector(
        );
        return builder;
    }

    public ReadableDsl<CssSelectorAnalysisBuilder> tagSelector()
    {
        ReadableDsl<CssSelectorAnalysisBuilder> builder = new CssSelectorAnalysisBuilder().tagSelector(
        );
        return builder;
    }

    public ReadableDsl<CssSelectorAnalysisBuilder> idSelector()
    {
        ReadableDsl<CssSelectorAnalysisBuilder> builder = new CssSelectorAnalysisBuilder().idSelector(
        );
        return builder;
    }

    public BooleanOperatorDsl<CssRuleAnalysisBuilder> cssRule()
    {
        BooleanOperatorDsl<CssRuleAnalysisBuilder> builder = new CssRuleAnalysisBuilder().cssRule(
        );
        return builder;
    }

    public BooleanOperatorDsl<CssRuleAnalysisBuilder> containsSelector(CssSelector.Type selectorType)
    {
        BooleanOperatorDsl<CssRuleAnalysisBuilder> builder = new CssRuleAnalysisBuilder().containsSelector(
                selectorType
        );
        return builder;
    }

    public NumericOperatorDsl<CssRuleAnalysisBuilder> numberOfSelectors(CssSelector.Type selectorType)
    {
        NumericOperatorDsl<CssRuleAnalysisBuilder> builder = new CssRuleAnalysisBuilder().numberOfSelectors(
                selectorType
        );
        return builder;
    }

    public NumericOperatorDsl<CssRuleAnalysisBuilder> numberOfSelectors()
    {
        NumericOperatorDsl<CssRuleAnalysisBuilder> builder = new CssRuleAnalysisBuilder().numberOfSelectors(
        );
        return builder;
    }

    public NumericOperatorDsl<CssRuleAnalysisBuilder> lastIndexOfSelector(CssSelector.Type selectorType)
    {
        NumericOperatorDsl<CssRuleAnalysisBuilder> builder = new CssRuleAnalysisBuilder().lastIndexOfSelector(
                selectorType
        );
        return builder;
    }


}
