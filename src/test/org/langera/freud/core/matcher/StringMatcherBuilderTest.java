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

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public final class StringMatcherBuilderTest
{
    private FreudMatcher<String> matchesRegexMatcher;
    private FreudMatcher<String> containsRegexMatcher;

    @Test
    public void shouldPassACompleteMatch()
    {
        Assert.assertTrue(matchesRegexMatcher.matches("abc12"));
    }

    @Test
    public void shouldFailACompleteMatch()
    {
        Assert.assertFalse(matchesRegexMatcher.matches("abcd"));
    }

    @Test
    public void shouldPassAPartialMatch()
    {
        Assert.assertTrue(containsRegexMatcher.matches("ab12cd"));
    }

    @Test
    public void shouldFailAPartialMatch()
    {
        Assert.assertFalse(containsRegexMatcher.matches("abcd"));
    }

    @Before
    public void setUp()
    {
        RegexMatcherAdapter<String> adapter = new IdentityRegexMatcherAdapter();
        matchesRegexMatcher = new StringMatcherBuilder<String>(adapter).matches("abc\\d+");
        containsRegexMatcher = new StringMatcherBuilder<String>(adapter).contains("\\d+");
    }

    private static class IdentityRegexMatcherAdapter implements RegexMatcherAdapter<String>
    {
        public String getStringToMatch(String toBeAnalysed)
        {
            return toBeAnalysed;
        }

        @Override
        public String matcherDisplayName()
        {
            return "test";
        }
    }
}
