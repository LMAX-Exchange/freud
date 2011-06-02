package org.langera.freud.core.matcher;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public final class RegexMatcherBuilderTest
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
        matchesRegexMatcher = new RegexMatcherBuilder<String>(adapter).matches("abc\\d+");
        containsRegexMatcher = new RegexMatcherBuilder<String>(adapter).contains("\\d+");
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
