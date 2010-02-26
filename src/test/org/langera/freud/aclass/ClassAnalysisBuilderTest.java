package org.langera.freud.aclass;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.langera.freud.AnalysisAssertion;

public class ClassAnalysisBuilderTest 
{
    private ClassAnalysisBuilder builder;
    private ClassAnalysisBuilder otherBuilder;

    @Test
    public void testShouldSetAssertionAsTrueAssertion() throws Exception
    {
        builder.aClass();

        AnalysisAssertion assertion = builder.buildAssertion();

        Assert.assertEquals("TRUE", assertion.toString());
    }

    @Test
    public void testShouldSetAssertionAsRegexAssertion() throws Exception
    {
        builder.aClass().contains(".*Impl");

        AnalysisAssertion assertion = builder.buildAssertion();

        Assert.assertEquals("ClassSimpleNameContains(.*Impl)", assertion.toString());
    }

    @Test
    public void testShouldSetAssertionAsSubTypeOf() throws Exception
    {
        builder.subTypeOf(String.class);

        AnalysisAssertion assertion = builder.buildAssertion();

        Assert.assertEquals("subTypeOf(java.lang.String)", assertion.toString());
    }

    @Test
    public void testShouldSetAssertionAsAnAndOperator() throws Exception

    {
        builder.subTypeOf(String.class).and(otherBuilder.subTypeOf(Integer.class));

        AnalysisAssertion assertion = builder.buildAssertion();

        Assert.assertEquals("(subTypeOf(java.lang.String) AND subTypeOf(java.lang.Integer))", assertion.toString());
    }

    @Test
    public void testShouldSetAssertionAsAnOrOperator() throws Exception

    {
        builder.subTypeOf(String.class).or(otherBuilder.subTypeOf(Integer.class));

        AnalysisAssertion assertion = builder.buildAssertion();

        Assert.assertEquals("(subTypeOf(java.lang.String) OR subTypeOf(java.lang.Integer))", assertion.toString());
    }

    @Test
    public void testShouldSetAssertionAsANegateOperator() throws Exception

    {
        builder.no(otherBuilder.subTypeOf(Integer.class));

        AnalysisAssertion assertion = builder.buildAssertion();

        Assert.assertEquals("(NOT subTypeOf(java.lang.Integer))", assertion.toString());
    }

    @Before
    public void setUp() throws Exception 
    {
        builder = new ClassAnalysisBuilder();
        otherBuilder = new ClassAnalysisBuilder();
    }


}
