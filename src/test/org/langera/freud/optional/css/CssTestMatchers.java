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

package org.langera.freud.optional.css;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.langera.freud.optional.css.cssrule.CssRule;
import org.langera.freud.optional.css.cssrule.declaration.CssDeclaration;
import org.langera.freud.optional.css.cssrule.selector.CssSelector;

import java.util.List;

public final class CssTestMatchers
{
    private CssTestMatchers()
    {
        // static utility
    }

    public static Matcher<CssSelector> cssSelector(final CssSelector.Type type, final String selector)
    {
        return new CssSelectorMatcher(type, selector);
    }

    public static Matcher<CssDeclaration> cssDeclaration(final String key, final String value)
    {
        return new CssDeclarationMatcher(key, value);
    }

    public static Matcher<CssRule> cssRule(final Matcher<CssSelector>... cssSelectorMatcher)
    {
        return new CssRuleByItsSelectorsMatcher(cssSelectorMatcher);
    }

    private static class CssRuleByItsSelectorsMatcher extends TypeSafeMatcher<CssRule>
    {
        private final Matcher<CssSelector>[] cssSelectorMatcher;

        private CssRuleByItsSelectorsMatcher(final Matcher<CssSelector>... cssSelectorMatcher)
        {
            this.cssSelectorMatcher = cssSelectorMatcher;
        }

        @Override
        public final boolean matchesSafely(final CssRule cssRule)
        {
            List<CssSelector> cssSelectorList = cssRule.getCssSelectorList();
            for (int i = 0; i < cssSelectorMatcher.length; i++)
            {
                Matcher<CssSelector> matcher = cssSelectorMatcher[i];
                if (!matcher.matches(cssSelectorList.get(i)))
                {
                    return false;
                }
            }

            return true;
        }

        public void describeTo(Description description)
        {
            description.appendText("CssRule[");
            for (int i = 0; i < cssSelectorMatcher.length; i++)
            {
                if (i > 0)
                {
                    description.appendText(", ");
                }
                Matcher<CssSelector> matcher = cssSelectorMatcher[i];
                matcher.describeTo(description);
            }
            description.appendText("]");
        }
    }

    private static class CssSelectorMatcher extends TypeSafeMatcher<CssSelector>
    {
        private final String selector;
        private final CssSelector.Type type;

        private CssSelectorMatcher(final CssSelector.Type type, final String selector)
        {
            this.selector = selector;
            this.type = type;
        }

        @Override
        public final boolean matchesSafely(final CssSelector cssSelector)
        {
            return selector.equals(cssSelector.getSelectorString()) &&
                    cssSelector.getType() == type;
        }

        public void describeTo(Description description)
        {
            description.appendText("CssSelector[").
                    appendText(selector).appendText("]");
        }
    }


    private static class CssDeclarationMatcher extends TypeSafeMatcher<CssDeclaration>
    {
        private final String key;
        private final String value;

        private CssDeclarationMatcher(String key, String value)
        {
            this.key = key;
            this.value = value;
        }

        @Override
        public final boolean matchesSafely(final CssDeclaration cssDeclaration)
        {
            return key.equals(cssDeclaration.getKey()) && value.equals(cssDeclaration.getValue());
        }

        public void describeTo(Description description)
        {
            description.appendText("CssDeclaration[").
                    appendText(key).appendText(":").appendText(value).appendText("]");
        }
    }
}
