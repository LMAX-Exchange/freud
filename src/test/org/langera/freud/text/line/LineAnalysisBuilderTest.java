package org.langera.freud.text.line;

import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LineAnalysisBuilderTest
{
    private LineAnalysisBuilder builder;


    @Test
    public void testShouldSetAssertionAsTrueAssertion() throws Exception
    {
        builder.line();

        Matcher assertion = builder.buildAssertion();

        Assert.assertEquals("TRUE", assertion.toString());
    }


    @Test
    public void testShouldSetAssertionAsRegexAssertion() throws Exception
    {
        builder.line().matches("abc.*def");

        Matcher assertion = builder.buildAssertion();

        Assert.assertEquals("TextLineMatch(abc.*def)", assertion.toString());
    }

    @Test
    public void testShouldSetAssertionAsLineLengthLessThanAssertion() throws Exception
    {
        builder.lineLength().lessThan(666);

        Matcher assertion = builder.buildAssertion();

        Assert.assertEquals("(LineLength() < 666)", assertion.toString());
    }

    @Test
    public void testShouldSetAssertionAsLineLengthGreaterThanAssertion() throws Exception
    {
        builder.lineLength().greaterThan(666);

        Matcher assertion = builder.buildAssertion();

        Assert.assertEquals("(LineLength() > 666)", assertion.toString());
    }


    @Test
    public void testShouldSetAssertionAsLineLengthLessThanEqualsAssertion() throws Exception
    {
        builder.lineLength().lessThanOrEqualTo(666);

        Matcher assertion = builder.buildAssertion();

        Assert.assertEquals("(LineLength() <= 666)", assertion.toString());
    }


    @Test
    public void testShouldSetAssertionAsLineLengthGreaterThanEqualsAssertion() throws Exception
    {
        builder.lineLength().greaterThanOrEqualTo(666);

        Matcher assertion = builder.buildAssertion();

        Assert.assertEquals("(LineLength() >= 666)", assertion.toString());
    }

    @Test
    public void testShouldSetAssertionAsLineLengthEqualsAssertion() throws Exception
    {
        builder.lineLength().equalTo(666);

        Matcher assertion = builder.buildAssertion();

        Assert.assertEquals("(LineLength() == 666)", assertion.toString());
    }

    @Test
    public void testShouldSetAssertionAsLineLengthLessThanLineLengthAssertion() throws Exception
    {
        builder.lineLength().lessThan(builder.lineLength());

        Matcher assertion = builder.buildAssertion();

        Assert.assertEquals("(LineLength() < LineLength())", assertion.toString());
    }

    @Test
    public void testShouldSetAssertionAsLineLengthGreaterThanLineLengthAssertion() throws Exception
    {
        builder.lineLength().greaterThan(builder.lineLength());

        Matcher assertion = builder.buildAssertion();

        Assert.assertEquals("(LineLength() > LineLength())", assertion.toString());
    }


    @Test
    public void testShouldSetAssertionAsLineLengthLessThanEqualsLineLengthAssertion() throws Exception
    {
        builder.lineLength().lessThanOrEqualTo(builder.lineLength());

        Matcher assertion = builder.buildAssertion();

        Assert.assertEquals("(LineLength() <= LineLength())", assertion.toString());
    }


    @Test
    public void testShouldSetAssertionAsLineLengthGreaterThanEqualsLineLengthAssertion() throws Exception
    {
        builder.lineLength().greaterThanOrEqualTo(builder.lineLength());

        Matcher assertion = builder.buildAssertion();

        Assert.assertEquals("(LineLength() >= LineLength())", assertion.toString());
    }

    @Test
    public void testShouldSetAssertionAsLineLengthEqualsLineLengthAssertion() throws Exception
    {
        builder.lineLength().equalTo(builder.lineLength());

        Matcher assertion = builder.buildAssertion();

        Assert.assertEquals("(LineLength() == LineLength())", assertion.toString());
    }

    @Test
    public void testShouldSetAssertionAsLineLengthAdditionAssertion() throws Exception
    {
        builder.lineLength().add(17).greaterThan(666);

        Matcher assertion = builder.buildAssertion();

        Assert.assertEquals("((LineLength() + 17) > 666)", assertion.toString());
    }

    @Test
    public void testShouldSetAssertionAsLineLengthSutractionAssertion() throws Exception
    {
        builder.lineLength().subtract(17).greaterThan(666);

        Matcher assertion = builder.buildAssertion();

        Assert.assertEquals("((LineLength() - 17) > 666)", assertion.toString());
    }

    @Test
    public void testShouldSetAssertionAsLineLengthMultiplicationAssertion() throws Exception
    {
        builder.lineLength().multiply(17).greaterThan(666);

        Matcher assertion = builder.buildAssertion();

        Assert.assertEquals("((LineLength() * 17) > 666)", assertion.toString());
    }


    @Test
    public void testShouldSetAssertionAsLineLengthAdditionToDynamicValueAssertion() throws Exception
    {
        builder.lineLength().add(builder.lineLength()).greaterThan(666);

        Matcher assertion = builder.buildAssertion();

        Assert.assertEquals("((LineLength() + LineLength()) > 666)", assertion.toString());
    }


    @Test
    public void testShouldSetAssertionAsLineLengthSubtractionToDynamicValueAssertion() throws Exception
    {
        builder.lineLength().subtract(builder.lineLength()).greaterThan(666);

        Matcher assertion = builder.buildAssertion();

        Assert.assertEquals("((LineLength() - LineLength()) > 666)", assertion.toString());
    }


    @Test
    public void testShouldSetAssertionAsLineLengthMultiplicationToDynamicValueAssertion() throws Exception
    {
        builder.lineLength().multiply(builder.lineLength()).greaterThan(666);

        Matcher assertion = builder.buildAssertion();

        Assert.assertEquals("((LineLength() * LineLength()) > 666)", assertion.toString());
    }

    @Test
    public void testShouldSetAssertionAsRegexContainsAssertion() throws Exception
    {
        builder.line().contains("regex");

        Matcher assertion = builder.buildAssertion();

        Assert.assertEquals("TextLineContains(regex)", assertion.toString());
    }


    @Test
    public void testShouldSetAssertionAsRegexMatchAssertion() throws Exception
    {
        builder.line().matches("regex");

        Matcher assertion = builder.buildAssertion();

        Assert.assertEquals("TextLineMatch(regex)", assertion.toString());
    }

    @Before
    public void setUp() throws Exception
    {
        builder = new LineAnalysisBuilder();
    }
}
