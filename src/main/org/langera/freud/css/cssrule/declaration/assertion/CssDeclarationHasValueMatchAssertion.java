package org.langera.freud.css.cssrule.declaration.assertion;

import org.langera.freud.AnalysisAssertion;
import org.langera.freud.css.cssrule.declaration.CssDeclaration;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

public final class CssDeclarationHasValueMatchAssertion implements AnalysisAssertion<CssDeclaration>
{
    private final Pattern pattern;

    public CssDeclarationHasValueMatchAssertion(final String regex)
    {
        this.pattern = Pattern.compile(regex);
    }

    public boolean analyse(CssDeclaration toBeAnalysed)
    {
        final String[] values = toBeAnalysed.getValues();
        for (int i = 0, size = values.length; i < size; i++)
        {
            final Matcher matcher = pattern.matcher(values[i]);
            if (matcher.matches())
            {
                return true;
            }
        }
        return false;
    }

    public String toString()
    {
        return "CssDeclarationHasValueMatches[" + pattern.pattern() + "]";
    }
}
