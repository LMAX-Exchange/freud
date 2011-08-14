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

package org.langera.freud.optional.css.cssrule.selector;

import org.langera.freud.optional.css.cssrule.CssRule;

public final class CssSelectorStub implements CssSelector
{
    private String selectorString;
    private Type type;
    private CssRule cssRule;
    private Combinator combinator;

    public CssSelectorStub(Type type, String selectorString, CssRule cssRule)
    {
        this(type, selectorString, Combinator.DESCENDANT, cssRule);
    }

    public CssSelectorStub(Type type, String selectorString, Combinator combinator, CssRule cssRule)
    {
        this.selectorString = selectorString;
        this.type = type;
        this.cssRule = cssRule;
        this.combinator = combinator;
    }

    public String getSelectorString()
    {
        return selectorString;
    }

    public Type getType()
    {
        return type;
    }

    public CssRule getCssRuleForSelector()
    {
        return cssRule;
    }

    public Combinator getCombinator()
    {
        return combinator;
    }

    public void setSelectorString(String selectorString)
    {
        this.selectorString = selectorString;
    }

    public void setType(Type type)
    {
        this.type = type;
    }

    public void setCssRule(CssRule cssRule)
    {
        this.cssRule = cssRule;
    }
}
