package org.langera.freud.optional.css.cssrule.selector;

import org.langera.freud.optional.css.cssrule.CssRule;

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

public interface CssSelector
{
    public enum Type
    {
        CLASS, TAG, ID, UNIVERSAL, PSEUDO, ATTRIB;

        public static boolean isType(String name)
        {
            final Type[] types = Type.values();
            for (int i = 0; i < types.length; i++)
            {
                Type type = types[i];
                if (type.name().equals(name))
                {
                    return true;
                }
            }
            return false;
        }
    }

    public enum Combinator
    {
        ADJACENT_SIBLING, CHILD, DESCENDANT;

        public static boolean isCombinator(String name)
        {
            final Combinator[] combinators = Combinator.values();
            for (int i = 0; i < combinators.length; i++)
            {
                Combinator combinator = combinators[i];
                if (combinator.name().equals(name))
                {
                    return true;
                }
            }
            return false;
        }
    }

    String getSelectorString();

    Type getType();

    CssRule getCssRuleForSelector();

    Combinator getCombinator();
}
