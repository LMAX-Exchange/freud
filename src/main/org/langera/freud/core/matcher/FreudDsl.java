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

package org.langera.freud.core.matcher;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

public final class FreudDsl
{
    private FreudDsl()
    {
        // static utility
    }

    public static <T> FreudMatcher<T> no(final Matcher<T> matcher)
    {
        return new FreudMatcher<T>()
        {
            @Override
            public boolean matchesSafely(final T item)
            {
                return !matcher.matches(item);
            }

            @Override
            public void describeTo(final Description description)
            {
                description.appendText("NO (");
                matcher.describeTo(description);
                description.appendText(")");
            }
        };
    }

    public static <T> FreudMatcher<T> and(final Matcher<T> matcher1, final Matcher<T> matcher2)
    {
        return new FreudMatcher<T>()
        {
            @Override
            public boolean matchesSafely(final T item)
            {
                return matcher1.matches(item) && matcher2.matches(item);
            }

            @Override
            public void describeTo(final Description description)
            {
                description.appendText("(");
                matcher1.describeTo(description);
                description.appendText(") AND (");
                matcher2.describeTo(description);
                description.appendText(")");
            }
        };
    }

    public static <T> FreudMatcher<T> or(final Matcher<T> matcher1, final Matcher<T> matcher2)
    {
        return new FreudMatcher<T>()
        {
            @Override
            public boolean matchesSafely(final T item)
            {
                return matcher1.matches(item) || matcher2.matches(item);
            }

            @Override
            public void describeTo(final Description description)
            {
                description.appendText("(");
                matcher1.describeTo(description);
                description.appendText(") OR (");
                matcher2.describeTo(description);
                description.appendText(")");
            }
        };
    }
}
