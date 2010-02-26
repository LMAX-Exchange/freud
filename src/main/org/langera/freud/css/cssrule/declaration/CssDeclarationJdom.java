package org.langera.freud.css.cssrule.declaration;

import org.jdom.Element;
import org.langera.freud.css.cssrule.CssRule;
import org.langera.freudgenerated.css.parser.CssTokenType;

import java.util.List;

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

public final class CssDeclarationJdom implements CssDeclaration
{
    private final CssRule cssRule;
    private final String key;
    private final String[] values;

    @SuppressWarnings("unchecked")
    public CssDeclarationJdom(final CssRule cssRule, final Element element)
    {
        this.cssRule = cssRule;
        List<Element> declaration = (List<Element>) element.getChildren(CssTokenType.IDENT.name());
        int size = declaration.size();
        if (size > 0)
        {
            key = declaration.get(0).getText();
            values = new String[size - 1];
            for (int i = 1; i < size; i++)
            {
                values[i - 1] = declaration.get(i).getText();
            }
        }
        else
        {
            key = null;
            values = new String[0];
        }
    }

    public String getKey()
    {
        return key;
    }

    public String[] getValues()
    {
        return values;
    }

    public CssRule getCssRuleForDeclaration()
    {
        return cssRule;
    }
}
