package org.langera.freud.css.cssrule.selector.assertion;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.langera.freud.css.cssrule.selector.CssSelector;

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

public final class CssSelectorTypeAssertion extends TypeSafeMatcher<CssSelector>
{
    private final CssSelector.Type type;

    public CssSelectorTypeAssertion(CssSelector.Type type)
    {
        this.type = type;
    }

    public final boolean matchesSafely(final CssSelector toBeAnalysed)
    {
        return type == toBeAnalysed.getType();
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }

        CssSelectorTypeAssertion that = (CssSelectorTypeAssertion) o;

        if (type != that.type)
        {
            return false;
        }

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

    public void describeTo(Description description)
    {
        description.appendText(toString());
    }
}
