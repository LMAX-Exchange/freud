package org.langera.freud.css.cssrule.selector.assertion;

import org.langera.freud.AnalysisAssertion;
import org.langera.freud.css.cssrule.selector.CssSelector;

/**
 *   This file is part of "Freud".
 *
 *   Freud is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU Lesser General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   Freud is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with Freud.  If not, see <http://www.gnu.org/licenses/>.
 *
 *   @author Amir Langer  langera_at_gmail_dot_com
**/

public final class CssSelectorTypeAssertion implements AnalysisAssertion<CssSelector>
{
    private final CssSelector.Type type;

    public CssSelectorTypeAssertion(CssSelector.Type type)
    {
        this.type = type;
    }

    public boolean analyse(CssSelector toBeAnalysed)
    {
        return type == toBeAnalysed.getType();
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CssSelectorTypeAssertion that = (CssSelectorTypeAssertion) o;

        if (type != that.type) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        return type.hashCode();
    }

    @Override
    public String toString()
    {
        return "cssSelectorType(" + type.name() + ")";
    }
}
