package org.langera.freud.css.cssrule.selector;

import org.jdom.Element;
import org.langera.freud.css.cssrule.CssRule;
import org.langera.freud.util.parser.JdomTreeAdaptor;

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

public final class CssSelectorJdom implements CssSelector
{
    private final String selectorString;
    private final Type type;
    private final CssRule cssRule;

    public CssSelectorJdom(final CssRule cssRule, final Element element)
    {
        this.cssRule = cssRule;
        type = Type.valueOf(element.getName());
        selectorString = element.getAttributeValue(JdomTreeAdaptor.ID_ATTR);
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

    @Override
    public String toString()
    {
        return type + ":" + selectorString;
    }
}
