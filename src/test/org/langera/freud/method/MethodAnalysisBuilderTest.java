package org.langera.freud.method;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.langera.freud.AnalysisAssertion;

public class MethodAnalysisBuilderTest 
{
    private MethodAnalysisBuilder builder;


    @Test
    public void testShouldSetAssertionAsTrueAssertion() throws Exception
    {
        builder.method();

        AnalysisAssertion assertion = builder.buildAssertion();

        Assert.assertEquals("TRUE", assertion.toString());
    }

    @Test
    public void testShouldSetAssertionAsRegexAssertion() throws Exception
    {
        builder.method().matches("should.+");

        AnalysisAssertion assertion = builder.buildAssertion();

        Assert.assertEquals("MethodNameMatch(should.+)", assertion.toString());
    }

    @Before
    public void setUp() throws Exception 
    {
        builder = new MethodAnalysisBuilder();
    }


}
