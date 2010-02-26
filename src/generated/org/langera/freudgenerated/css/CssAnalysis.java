// Freud generated code [CssAnalysis] [2010-02-14 19:03:24]
package org.langera.freudgenerated.css;

import org.langera.freud.*;
import org.langera.freud.dsl.*;
import org.langera.freud.util.collection.AnalysedObjectIterator;
import org.langera.freud.css.cssrule.selector.*;
import org.langera.freud.css.cssrule.*;
import org.langera.freud.css.*;
import org.langera.freud.css.cssrule.declaration.*;
import org.langera.freud.dsl.ReadableDsl;
import org.langera.freud.dsl.BooleanOperatorDsl;
import org.langera.freud.css.cssrule.declaration.CssDeclarationAnalysisBuilder;

public class CssAnalysis extends AbstractAnalysis<Css>
        implements
                    CssSelectorDsl,
                    CssRuleDsl,
                    CssDsl,
                    CssDeclarationDsl,
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

            public ReadableDsl<CssSelectorAnalysisBuilder> selector()
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
        
                public BooleanOperatorDsl<CssRuleAnalysisBuilder> cssRule()
        {

            return new CssRuleAnalysisBuilder().cssRule(
                        );
        }
        
                public BooleanOperatorDsl<CssAnalysisBuilder> css()
        {

            return new CssAnalysisBuilder().css(
                        );
        }
        
                public ReadableDsl<CssDeclarationAnalysisBuilder> declaration()
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
        
    
}
