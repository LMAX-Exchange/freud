package org.langera.freud.core.matcher;

import org.hamcrest.Description;

import java.util.regex.Pattern;

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

public final class RegexMatcherBuilder<T>
{
    private final RegexMatcherAdapter<T> adapter;

    public RegexMatcherBuilder(final RegexMatcherAdapter<T> adapter)
    {
        this.adapter = adapter;
    }

    public FreudMatcher<T> matches(final String regex)
    {
        return new RegexMatcher<T>(regex, true, adapter);
    }

    public FreudMatcher<T> contains(final String regex)
    {
        return new RegexMatcher<T>(regex, false, adapter);
    }

    private static final class RegexMatcher<T> extends FreudMatcher<T>
    {
        private final Pattern regex;
        private final boolean completeMatch;
        private final RegexMatcherAdapter<T> adapter;

        public RegexMatcher(final String regex, final boolean completeMatch,
                                   final RegexMatcherAdapter<T> adapter)
        {
            this.adapter = adapter;
            this.completeMatch = completeMatch;
            this.regex = Pattern.compile(regex);
        }

        public final boolean matchesSafely(final T toBeAnalysed)
        {
            final String stringToMatch = adapter.getStringToMatch(toBeAnalysed);
            return (completeMatch) ? regex.matcher(stringToMatch).matches() :
                    regex.matcher(stringToMatch).find();
        }

        @Override
        public String toString()
        {
            return adapter.matcherDisplayName() + ((completeMatch) ? "Match" : "Contains") +
                    "(" + regex.pattern() + ")";
        }

        public void describeTo(Description description)
        {
            description.appendText(toString());
        }
    }
}