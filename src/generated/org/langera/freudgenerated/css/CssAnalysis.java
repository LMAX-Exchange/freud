// Freud generated code [CssAnalysis] [2010-11-09 14:03:56]
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
import org.langera.freud.dsl.*;
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

            public MatchingDsl<CssAnalysisBuilder, Css> css()
        {
            return new CssAnalysisBuilder().css(
                        );
        }
        
                public CommonDsl<CssDeclarationAnalysisBuilder, CssDeclaration> declaration()
        {
            return new CssDeclarationAnalysisBuilder().declaration(
                        );
        }
        
            public ReadableDsl<CssDeclarationAnalysisBuilder> declarationValue(String regex)
        {
            return new CssDeclarationAnalysisBuilder().declarationValue(
                            regex
                                        );
        }
        
                public CommonDsl<CssSelectorAnalysisBuilder, CssSelector> selector()
        {
            return new CssSelectorAnalysisBuilder().selector(
                        );
        }
        
            public ReadableDsl<CssSelectorAnalysisBuilder> classSelector()
        {
            return new CssSelectorAnalysisBuilder().classSelector(
                        );
        }
        
            public ReadableDsl<CssSelectorAnalysisBuilder> tagSelector()
        {
            return new CssSelectorAnalysisBuilder().tagSelector(
                        );
        }
        
            public ReadableDsl<CssSelectorAnalysisBuilder> idSelector()
        {
            return new CssSelectorAnalysisBuilder().idSelector(
                        );
        }
        
                public CommonDsl<CssRuleAnalysisBuilder, CssRule> cssRule()
        {
            return new CssRuleAnalysisBuilder().cssRule(
                        );
        }
        
            public BooleanOperatorDsl<CssRuleAnalysisBuilder> containsSelector(CssSelector.Type selectorType)
        {
            return new CssRuleAnalysisBuilder().containsSelector(
                            selectorType
                                        );
        }
        
            public NumericOperatorDsl<CssRuleAnalysisBuilder> selectors(CssSelector.Type selectorType)
        {
            return new CssRuleAnalysisBuilder().selectors(
                            selectorType
                                        );
        }
        
            public NumericOperatorDsl<CssRuleAnalysisBuilder> selectors()
        {
            return new CssRuleAnalysisBuilder().selectors(
                        );
        }
        
            public NumericOperatorDsl<CssRuleAnalysisBuilder> lastIndexOfSelector(CssSelector.Type selectorType)
        {
            return new CssRuleAnalysisBuilder().lastIndexOfSelector(
                            selectorType
                                        );
        }
        
    
}
