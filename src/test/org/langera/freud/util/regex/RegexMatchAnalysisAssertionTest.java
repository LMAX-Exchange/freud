package org.langera.freud.util.regex;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public final class RegexMatchAnalysisAssertionTest
{
    private RegexMatchAnalysisAssertion<String> matchAssertion;
    private RegexMatchAnalysisAssertion<String> containsAssertion;

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
        RegexMatchAnalysisAssertionAdapter<String> adapter = new IdentityRegexMatchAnalysisAssertionAdapter();
        matchAssertion = new RegexMatchAnalysisAssertion<String>("abc\\d+", true, adapter);
        containsAssertion = new RegexMatchAnalysisAssertion<String>("\\d+", false, adapter);
    }

    private static class IdentityRegexMatchAnalysisAssertionAdapter implements RegexMatchAnalysisAssertionAdapter<String>
    {
        public String getStringToMatch(String toBeAnalysed)
        {
            return toBeAnalysed;
        }

        public String assertionDisplayName()
        {
            return "test";
        }
    }
}
