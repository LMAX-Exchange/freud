package org.langera.freud.instance;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.langera.freud.AnalysisAssertion;

public class InstanceAnalysisBuilderTest 
{
    private InstanceAnalysisBuilder builder;

    @Test
    public void testShouldSetAssertionAsTrueAssertion() throws Exception
    {
        builder.instance();

        AnalysisAssertion assertion = builder.buildAssertion();

        Assert.assertEquals("TRUE", assertion.toString());
    }

    @Before
    public void setUp() throws Exception 
    {
        builder = new InstanceAnalysisBuilder();
    }
}
