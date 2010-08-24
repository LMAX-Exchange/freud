package org.langera.freud.spring;

import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SpringBeanAnalysisBuilderTest
{
    private SpringBeanAnalysisBuilder builder;
    private SpringBeanAnalysisBuilder otherBuilder;


    @Test
    public void testShouldSetAssertionAsTrueAssertion() throws Exception
    {
        builder.springBean();

        Matcher assertion = builder.buildAssertion();

        Assert.assertEquals("TRUE", assertion.toString());
    }

    @Test
    public void testShouldSetAssertionAsBeanNameMatch() throws Exception
    {
        builder.springBean().matches("bean\\s+Name");

        Matcher assertion = builder.buildAssertion();

        Assert.assertEquals("BeanNameMatch(bean\\s+Name)", assertion.toString());
    }


    @Test
    public void testShouldSetAssertionAsAnAndOperator() throws Exception
    {

        builder.springBean().matches("bean\\s+Name").and(otherBuilder.springBean().matches("bean\\s+\\S+"));

        Matcher assertion = builder.buildAssertion();

        Assert.assertEquals("(BeanNameMatch(bean\\s+Name) AND BeanNameMatch(bean\\s+\\S+))", assertion.toString());
    }

    @Test
    public void testShouldSetAssertionAsANegateOperator() throws Exception
    {

        builder.springBean().matches("bean\\s+Name").or(otherBuilder.springBean().matches("bean\\s+\\S+"));

        Matcher assertion = builder.buildAssertion();

        Assert.assertEquals("(BeanNameMatch(bean\\s+Name) OR BeanNameMatch(bean\\s+\\S+))", assertion.toString());
    }

    @Test
    public void testShouldSetAssertionAsAnOrOperator() throws Exception
    {

        builder.no(otherBuilder.springBean().matches("bean\\s+Name"));

        Matcher assertion = builder.buildAssertion();

        Assert.assertEquals("(NOT BeanNameMatch(bean\\s+Name))", assertion.toString());
    }

    @Before
    public void setUp() throws Exception
    {
        builder = new SpringBeanAnalysisBuilder();
        otherBuilder = new SpringBeanAnalysisBuilder();
    }
}
