package org.langera.freud.css.cssrule.declaration;

import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CssDeclarationAnalysisBuilderTest
{
    private CssDeclarationAnalysisBuilder builder;

    @Test
    public void shouldBuildDeclarationAssertion() throws Exception
    {
        builder.declaration();

        Matcher assertion = builder.buildAssertion();

        Assert.assertEquals("TRUE", assertion.toString());
    }

    @Test
    public void shouldBuildDeclarationAssertionWithRegexForKey() throws Exception
    {
        builder.declaration().contains("color");

        Matcher assertion = builder.buildAssertion();

        Assert.assertEquals("CssDeclarationKeyContains(color)", assertion.toString());
    }


    @Before
    public void setUp()
    {
        builder = new CssDeclarationAnalysisBuilder();
    }
}
