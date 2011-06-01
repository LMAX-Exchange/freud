package org.langera.freud.optional.css.cssrule.declaration;

import org.jdom.Element;
import org.langera.freud.optional.css.cssrule.CssRule;

import java.util.List;

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

public final class CssDeclarationJdom implements CssDeclaration
{
    private final CssRule cssRule;
    private final String key;
    private final String value;

    @SuppressWarnings("unchecked")
    public CssDeclarationJdom(final CssRule cssRule, final Element element)
    {
        this.cssRule = cssRule;
        List<Element> declaration = (List<Element>) element.getChildren();
        int size = declaration.size();
        if (size > 0)
        {
            key = declaration.get(0).getText();
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i < size; i++)
            {
                if (i > 1)
                {
                    sb.append(' ');
                }
                sb.append(declaration.get(i).getText());
            }
            value = sb.toString();
        }
        else
        {
            key = null;
            value = "";
        }
    }

    public String getKey()
    {
        return key;
    }

    public String getValue()
    {
        return value;
    }

    public CssRule getCssRuleForDeclaration()
    {
        return cssRule;
    }

    @Override
    public String toString()
    {
        return key + ": " + value;
    }
}
