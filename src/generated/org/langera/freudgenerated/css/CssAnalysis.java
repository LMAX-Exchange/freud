// Freud generated code [CssAnalysis] [2010-08-29 18:09:34]
package org.langera.freudgenerated.css;

import org.langera.freud.*;
import org.langera.freud.dsl.*;
import org.langera.freud.util.collection.AnalysedObjectIterator;
import org.langera.freud.css.cssrule.declaration.*;
import org.langera.freud.css.cssrule.selector.*;
import org.langera.freud.css.*;
import org.langera.freud.css.cssrule.*;
import org.langera.freud.dsl.CommonDsl;
import org.langera.freud.dsl.NumericOperatorDsl;
import org.langera.freud.dsl.ReadableDsl;
import org.langera.freud.css.cssrule.selector.CssSelector;
import org.langera.freud.dsl.MatchingDsl;
import org.langera.freud.dsl.BooleanOperatorDsl;

public class CssAnalysis extends AbstractAnalysis<Css, CssAnalysis>
        implements
                    CssDeclarationDsl,
                    CssSelectorDsl,
                    CssDsl,
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
        
                public MatchingDsl<CssAnalysisBuilder, Css> css()
        {
            return new CssAnalysisBuilder().css(
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
