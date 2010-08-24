package org.langera.freud.instance;

import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InstanceAnalysisBuilderTest
{
    private InstanceAnalysisBuilder builder;

    @Test
    public void testShouldSetAssertionAsTrueAssertion() throws Exception
    {
        builder.instance();

        Matcher assertion = builder.buildAssertion();

        Assert.assertEquals("TRUE", assertion.toString());
    }

    @Before
    public void setUp() throws Exception
    {
        builder = new InstanceAnalysisBuilder();
    }
}
