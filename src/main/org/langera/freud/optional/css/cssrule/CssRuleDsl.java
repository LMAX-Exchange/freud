/*
 * Copyright (c) 2011.
 * This file is part of "Freud".
 *
 * Freud is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Freud is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Freud.  If not, see <http://www.gnu.org/licenses/>.
 * @author Amir Langer  langera_at_gmail_dot_com
 */

package org.langera.freud.optional.css.cssrule;

import org.langera.freud.core.matcher.FreudMatcher;
import org.langera.freud.core.matcher.IntOperatorMatcherAdapter;
import org.langera.freud.core.matcher.IntOperatorMatcherBuilder;
import org.langera.freud.optional.css.cssrule.selector.CssSelector;

import java.util.List;


public final class CssRuleDsl
{
    private CssRuleDsl()
    {
        // static utility
    }

    public static FreudMatcher<CssRule> containsSelector(final CssSelector.Type selectorType)
    {
        return selectors(selectorType).greaterThan(0);
    }

    public static IntOperatorMatcherBuilder<CssRule> selectors(final CssSelector.Type selectorType)
    {
        return new IntOperatorMatcherBuilder<CssRule>(new IntOperatorMatcherAdapter<CssRule>()
        {
            @Override
            public int valueOf(final CssRule matched)
            {
                int counter = 0;
                for (CssSelector selector : matched.getCssSelectorList())
                {
                    if (selector.getType() == selectorType)
                    {
                        counter++;
                    }
                }
                return counter;
            }

            @Override
            public String matcherDisplayName()
            {
                return "CssSelectorsCount(" + selectorType.name() + ")";
            }
        });
    }

    public static IntOperatorMatcherBuilder<CssRule> selectors()
    {
        return new IntOperatorMatcherBuilder<CssRule>(new IntOperatorMatcherAdapter<CssRule>()
        {
            @Override
            public int valueOf(final CssRule matched)
            {
                return matched.getCssSelectorList().size();
            }

            @Override
            public String matcherDisplayName()
            {
                return "CssSelectorsCount()";
            }
        });
    }

    public static IntOperatorMatcherBuilder<CssRule> lastIndexOfSelector(final CssSelector.Type selectorType)
    {
        return new IntOperatorMatcherBuilder<CssRule>(new IntOperatorMatcherAdapter<CssRule>()
        {
            @Override
            public int valueOf(final CssRule matched)
            {
                final List<CssSelector> selectorList = matched.getCssSelectorList();
                for (int i = selectorList.size() - 1; i >= 0; i--)
                {
                    CssSelector selector = selectorList.get(i);
                    if (selector.getType() == selectorType)
                    {
                        return i;
                    }
                }
                return -1;
            }

            @Override
            public String matcherDisplayName()
            {
                return "LastIndexOfCssSelectorType(" + selectorType.name() + ")";
            }
        });
    }
}
