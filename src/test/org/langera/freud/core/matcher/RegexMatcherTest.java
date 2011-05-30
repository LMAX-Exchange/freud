package org.langera.freud.core.matcher;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public final class RegexMatcherTest
{
    private RegexMatcher<String> matchAssertion;
    private RegexMatcher<String> containsAssertion;

    @Test
    public void shouldPassACompleteMatch()
    {
        Assert.assertTrue(matchAssertion.matches("abc12"));
    }

    @Test
    public void shouldFailACompleteMatch()
    {
        Assert.assertFalse(matchAssertion.matches("abcd"));
    }

    @Test
    public void shouldPassAPartialMatch()
    {
        Assert.assertTrue(containsAssertion.matches("ab12cd"));
    }

    @Test
    public void shouldFailAPartialMatch()
    {
        Assert.assertFalse(containsAssertion.matches("abcd"));
    }

    @Before
    public void setUp()
    {
        RegexMatcherAdapter<String> adapter = new IdentityRegexMatcherAdapter();
        matchAssertion = new RegexMatcher<String>("abc\\d+", true, adapter);
        containsAssertion = new RegexMatcher<String>("\\d+", false, adapter);
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
