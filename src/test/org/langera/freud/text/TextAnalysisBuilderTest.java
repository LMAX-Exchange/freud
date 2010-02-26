package org.langera.freud.text;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.langera.freud.AnalysisAssertion;

public class TextAnalysisBuilderTest 
{
    private TextAnalysisBuilder builder;


    @Test
    public void testShouldSetAssertionAsTrueAssertion() throws Exception
    {
        builder.text();

        AnalysisAssertion assertion = builder.buildAssertion();

        Assert.assertEquals("TRUE", assertion.toString());
    }

    @Test
    public void testShouldSetAssertionAsRegexContainsAssertion() throws Exception
    {
        builder.text().contains("regex");

        AnalysisAssertion assertion = builder.buildAssertion();

        Assert.assertEquals("TextContains(regex)", assertion.toString());
    }

    @Test
    public void testShouldSetAssertionAsRegexMatchAssertion() throws Exception
    {
        builder.text().matches("regex");

        AnalysisAssertion assertion = builder.buildAssertion();

        Assert.assertEquals("TextMatch(regex)", assertion.toString());
    }

    @Before
    public void setUp() throws Exception
    {
        builder = new TextAnalysisBuilder();
    }

}
