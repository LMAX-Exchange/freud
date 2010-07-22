package org.langera.freud.css.cssrule;

import org.langera.freud.AbstractAnalysisBuilder;
import org.langera.freud.css.cssrule.assertion.HasSelectorTypeAssertion;
import org.langera.freud.css.cssrule.assertion.SelectorCountingCalculation;
import org.langera.freud.css.cssrule.assertion.SelectorTypeLastIndexCalculation;
import org.langera.freud.css.cssrule.selector.CssSelector;
import org.langera.freud.dsl.BooleanOperatorDsl;
import org.langera.freud.dsl.NumericOperatorDsl;

/**
 * This file is part of "Freud".
 * <p/>
 * Freud is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p/>
 * Freud is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU General Public License
 * along with Freud.  If not, see <http://www.gnu.org/licenses/>.
 *
 * @author Amir Langer  langera_at_gmail_dot_com
 */

public final class CssRuleAnalysisBuilder extends AbstractAnalysisBuilder<CssRuleAnalysisBuilder, CssRule>
        implements CssRuleDsl
{
    public BooleanOperatorDsl<CssRuleAnalysisBuilder> cssRule()
    {
        return trueAssertion();
    }

    public BooleanOperatorDsl<CssRuleAnalysisBuilder> containsSelector(CssSelector.Type selectorType)
    {
        setAssertion(new HasSelectorTypeAssertion(selectorType));
        return this;
    }

    public NumericOperatorDsl<CssRuleAnalysisBuilder> numberOfSelectors()
    {
        setCalculation(new SelectorCountingCalculation());
        return this;
    }

    public NumericOperatorDsl<CssRuleAnalysisBuilder> lastIndexOfSelector(CssSelector.Type selectorType)
    {
        setCalculation(new SelectorTypeLastIndexCalculation(selectorType));
        return this;
    }

    public NumericOperatorDsl<CssRuleAnalysisBuilder> numberOfSelectors(CssSelector.Type selectorType)
    {
        setCalculation(new SelectorCountingCalculation(selectorType));
        return this;
    }

    public Class<CssRule> getType()
    {
        return CssRule.class;
    }
}
