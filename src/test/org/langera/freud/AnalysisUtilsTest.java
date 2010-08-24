package org.langera.freud;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AnalysisUtilsTest
{

    private Matcher<AnalysisUtilsTest> dummyTrueAssertion;
    private Matcher<AnalysisUtilsTest> dummyFalseAssertion;
    private AnalysisCalculation<AnalysisUtilsTest> dummyThreeCalculation;
    private AnalysisCalculation<AnalysisUtilsTest> dummyFourCalculation;

    @Test
    public void testShouldCreateNegatedAssertion() throws Exception
    {

        Matcher<AnalysisUtilsTest> negatedTrue =
                AnalysisUtils.negatedAssertion(dummyTrueAssertion);
        Matcher<AnalysisUtilsTest> negatedFalse =
                AnalysisUtils.negatedAssertion(dummyFalseAssertion);

        Assert.assertTrue(negatedFalse.matches(this));
        Assert.assertFalse(negatedTrue.matches(this));
    }

    @Test
    public void testShouldCreateAndAssertion() throws Exception
    {

        Assert.assertTrue(AnalysisUtils.andOperatorAssertion(
                dummyTrueAssertion, dummyTrueAssertion).matches(this));
        Assert.assertFalse(AnalysisUtils.andOperatorAssertion(
                dummyFalseAssertion, dummyTrueAssertion).matches(this));
        Assert.assertFalse(AnalysisUtils.andOperatorAssertion(
                dummyTrueAssertion, dummyFalseAssertion).matches(this));
        Assert.assertFalse(AnalysisUtils.andOperatorAssertion(
                dummyFalseAssertion, dummyFalseAssertion).matches(this));
    }

    @Test
    public void testShouldCreateOrAssertion() throws Exception
    {

        Assert.assertTrue(AnalysisUtils.orOperatorAssertion(
                dummyTrueAssertion, dummyTrueAssertion).matches(this));
        Assert.assertTrue(AnalysisUtils.orOperatorAssertion(
                dummyFalseAssertion, dummyTrueAssertion).matches(this));
        Assert.assertTrue(AnalysisUtils.orOperatorAssertion(
                dummyTrueAssertion, dummyFalseAssertion).matches(this));
        Assert.assertFalse(AnalysisUtils.orOperatorAssertion(
                dummyFalseAssertion, dummyFalseAssertion).matches(this));
    }

    @Test
    public void testShouldCreateTrueAssertion() throws Exception
    {
        Assert.assertTrue(AnalysisUtils.trueAssertion().matches(this));
    }

    @Test
    public void testShouldCreateEqualAssertion() throws Exception
    {

        Assert.assertTrue(AnalysisUtils.equalOperatorAssertion(
                dummyThreeCalculation, dummyThreeCalculation).matches(this));
        Assert.assertFalse(AnalysisUtils.equalOperatorAssertion(
                dummyThreeCalculation, dummyFourCalculation).matches(this));
        Assert.assertFalse(AnalysisUtils.equalOperatorAssertion(
                dummyFourCalculation, dummyThreeCalculation).matches(this));
        Assert.assertTrue(AnalysisUtils.equalOperatorAssertion(
                dummyFourCalculation, dummyFourCalculation).matches(this));
    }

    @Test
    public void testShouldCreateGreaterThanOrEqualAssertion() throws Exception
    {

        Assert.assertTrue(AnalysisUtils.greaterThanEqualOperatorAssertion(
                dummyThreeCalculation, dummyThreeCalculation).matches(this));
        Assert.assertFalse(AnalysisUtils.greaterThanEqualOperatorAssertion(
                dummyThreeCalculation, dummyFourCalculation).matches(this));
        Assert.assertTrue(AnalysisUtils.greaterThanEqualOperatorAssertion(
                dummyFourCalculation, dummyThreeCalculation).matches(this));
        Assert.assertTrue(AnalysisUtils.greaterThanEqualOperatorAssertion(
                dummyFourCalculation, dummyFourCalculation).matches(this));
    }

    @Test
    public void testGreaterThanOrEqualAssertionEquality() throws Exception
    {
        final Matcher assertion1 = AnalysisUtils.greaterThanEqualOperatorAssertion(
                dummyFourCalculation, dummyFourCalculation);
        final Matcher assertion2 = AnalysisUtils.greaterThanEqualOperatorAssertion(
                dummyThreeCalculation, dummyFourCalculation);
        final Matcher assertion3 = AnalysisUtils.greaterThanEqualOperatorAssertion(
                dummyFourCalculation, dummyThreeCalculation);
        Assert.assertFalse(assertion2.equals(assertion3));
        Assert.assertFalse(assertion3.equals(assertion2));
        Assert.assertFalse(assertion1.equals(assertion3));
        Assert.assertFalse(assertion1.equals(assertion2));
    }


    @Test
    public void testShouldCreateGreaterThanAssertion() throws Exception
    {

        Assert.assertFalse(AnalysisUtils.greaterThanOperatorAssertion(
                dummyThreeCalculation, dummyThreeCalculation).matches(this));
        Assert.assertFalse(AnalysisUtils.greaterThanOperatorAssertion(
                dummyThreeCalculation, dummyFourCalculation).matches(this));
        Assert.assertTrue(AnalysisUtils.greaterThanOperatorAssertion(
                dummyFourCalculation, dummyThreeCalculation).matches(this));
        Assert.assertFalse(AnalysisUtils.greaterThanOperatorAssertion(
                dummyFourCalculation, dummyFourCalculation).matches(this));
    }


    @Test
    public void testShouldCreateLessThanOrEqualAssertion() throws Exception
    {

        Assert.assertTrue(AnalysisUtils.lessThanEqualOperatorAssertion(
                dummyThreeCalculation, dummyThreeCalculation).matches(this));
        Assert.assertTrue(AnalysisUtils.lessThanEqualOperatorAssertion(
                dummyThreeCalculation, dummyFourCalculation).matches(this));
        Assert.assertFalse(AnalysisUtils.lessThanEqualOperatorAssertion(
                dummyFourCalculation, dummyThreeCalculation).matches(this));
        Assert.assertTrue(AnalysisUtils.lessThanEqualOperatorAssertion(
                dummyFourCalculation, dummyFourCalculation).matches(this));
    }

    @Test
    public void testLessThanOrEqualAssertionEquality() throws Exception
    {
        final Matcher assertion1 = AnalysisUtils.lessThanEqualOperatorAssertion(
                dummyFourCalculation, dummyFourCalculation);
        final Matcher assertion2 = AnalysisUtils.lessThanEqualOperatorAssertion(
                dummyThreeCalculation, dummyFourCalculation);
        final Matcher assertion3 = AnalysisUtils.lessThanEqualOperatorAssertion(
                dummyFourCalculation, dummyThreeCalculation);
        Assert.assertFalse(assertion2.equals(assertion3));
        Assert.assertFalse(assertion3.equals(assertion2));
        Assert.assertFalse(assertion1.equals(assertion3));
        Assert.assertFalse(assertion1.equals(assertion2));
    }


    @Test
    public void testShouldCreateLessThanAssertion() throws Exception
    {

        Assert.assertFalse(AnalysisUtils.lessThanOperatorAssertion(
                dummyThreeCalculation, dummyThreeCalculation).matches(this));
        Assert.assertTrue(AnalysisUtils.lessThanOperatorAssertion(
                dummyThreeCalculation, dummyFourCalculation).matches(this));
        Assert.assertFalse(AnalysisUtils.lessThanOperatorAssertion(
                dummyFourCalculation, dummyThreeCalculation).matches(this));
        Assert.assertFalse(AnalysisUtils.lessThanOperatorAssertion(
                dummyFourCalculation, dummyFourCalculation).matches(this));
    }


    @Test
    public void testShouldCreateMultiplyCalculation() throws Exception
    {

        Assert.assertTrue(9 == AnalysisUtils.multiplyOperatorCalculation(
                dummyThreeCalculation, dummyThreeCalculation).analyse(this));
        Assert.assertTrue(12 == AnalysisUtils.multiplyOperatorCalculation(
                dummyThreeCalculation, dummyFourCalculation).analyse(this));
        Assert.assertTrue(12 == AnalysisUtils.multiplyOperatorCalculation(
                dummyFourCalculation, dummyThreeCalculation).analyse(this));
        Assert.assertTrue(16 == AnalysisUtils.multiplyOperatorCalculation(
                dummyFourCalculation, dummyFourCalculation).analyse(this));
    }

    @Test
    public void testShouldCreateSubtractCalculation() throws Exception
    {

        Assert.assertTrue(0 == AnalysisUtils.subtractOperatorCalculation(
                dummyThreeCalculation, dummyThreeCalculation).analyse(this));
        Assert.assertTrue(-1 == AnalysisUtils.subtractOperatorCalculation(
                dummyThreeCalculation, dummyFourCalculation).analyse(this));
        Assert.assertTrue(1 == AnalysisUtils.subtractOperatorCalculation(
                dummyFourCalculation, dummyThreeCalculation).analyse(this));
        Assert.assertTrue(0 == AnalysisUtils.subtractOperatorCalculation(
                dummyFourCalculation, dummyFourCalculation).analyse(this));
    }

    @Test
    public void testShouldCreateAddCalculation() throws Exception
    {

        Assert.assertTrue(6 == AnalysisUtils.addOperatorCalculation(
                dummyThreeCalculation, dummyThreeCalculation).analyse(this));
        Assert.assertTrue(7 == AnalysisUtils.addOperatorCalculation(
                dummyThreeCalculation, dummyFourCalculation).analyse(this));
        Assert.assertTrue(7 == AnalysisUtils.addOperatorCalculation(
                dummyFourCalculation, dummyThreeCalculation).analyse(this));
        Assert.assertTrue(8 == AnalysisUtils.addOperatorCalculation(
                dummyFourCalculation, dummyFourCalculation).analyse(this));
    }

    ////////////////////////////////////////////////////////////////////////////////////////

    @Before
    public void setUp() throws Exception
    {
        dummyTrueAssertion = new TypeSafeMatcher<AnalysisUtilsTest>()
        {
            public final boolean matchesSafely(final AnalysisUtilsTest toBeAnalysed)
            {
                return true;
            }

            public void describeTo(Description description)
            {
                description.appendText(toString());
            }
        };

        dummyFalseAssertion = new TypeSafeMatcher<AnalysisUtilsTest>()
        {
            public final boolean matchesSafely(final AnalysisUtilsTest toBeAnalysed)
            {
                return false;
            }

            public void describeTo(Description description)
            {
                description.appendText(toString());
            }
        };
        dummyThreeCalculation = new AnalysisCalculation<AnalysisUtilsTest>()
        {
            public int analyse(AnalysisUtilsTest toBeAnalysed)
            {
                return 3;
            }
        };
        dummyFourCalculation = new AnalysisCalculation<AnalysisUtilsTest>()
        {
            public int analyse(AnalysisUtilsTest toBeAnalysed)
            {
                return 4;
            }
        };
    }
}
