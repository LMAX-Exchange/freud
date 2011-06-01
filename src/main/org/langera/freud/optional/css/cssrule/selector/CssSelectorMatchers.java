package org.langera.freud.optional.css.cssrule.selector;

import org.hamcrest.Description;
import org.langera.freud.core.matcher.FreudMatcher;
import org.langera.freud.core.matcher.RegexMatcher;
import org.langera.freud.core.matcher.RegexMatcherAdapter;


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

public final class CssSelectorMatchers
{
    private CssSelectorMatchers()
    {
        // static utility
    }

    public static FreudMatcher<CssSelector> selectorMatches(final String regex)
    {
        return regexMatcher(regex, true);
    }

    public static FreudMatcher<CssSelector> selectorContains(final String regex)
    {
        return regexMatcher(regex, false);
    }

    public static FreudMatcher<CssSelector> classSelector()
    {
        return selectorTypeMatcher(CssSelector.Type.CLASS);
    }

    public static FreudMatcher<CssSelector> tagSelector()
    {
        return selectorTypeMatcher(CssSelector.Type.TAG);
    }

    public static FreudMatcher<CssSelector> idSelector()
    {
        return selectorTypeMatcher(CssSelector.Type.ID);
    }

    private static FreudMatcher<CssSelector> regexMatcher(final String regex, final boolean completeMatch)
    {
        return new RegexMatcher<CssSelector>(regex, completeMatch, new RegexMatcherAdapter<CssSelector>()
        {
            @Override
            public String getStringToMatch(final CssSelector toBeAnalysed)
            {
                return toBeAnalysed.getSelectorString();
            }

            @Override
            public String matcherDisplayName()
            {
                return "CssSelector";
            }
        });
    }

    private static FreudMatcher<CssSelector> selectorTypeMatcher(final CssSelector.Type type)
    {
        return new FreudMatcher<CssSelector>()
        {
            @Override
            protected boolean matchesSafely(final CssSelector item)
            {
                return item.getType() == type;
            }

            @Override
            public void describeTo(final Description description)
            {
                description.appendText("CssSelector type [" + type + "]");
            }
        };
    }

}
// TODO hierarchy between selector --> declaration
//     public static FreudMatcher<CssSelector> hasDeclaration(String regex);
