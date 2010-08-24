package org.langera.freud.annotatedelement.assertion;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;

public class AnnotationAssertionTest
{
    private AnnotationAssertion<Method> assertion;
    private AnnotationAssertion<Method> assertionWithValue;
    private AnnotationAssertion<Method> assertionWithContainsMatcher;


    @Test
    public void testShouldPassAnalysis() throws Exception
    {
        Assert.assertTrue(assertion.matches(getClass().getMethod("setUp")));
    }

    @Test
    public void testShouldFailAnalysis() throws Exception
    {
        Assert.assertFalse(assertion.matches(getClass().getMethod("testShouldFailAnalysis")));
    }

    @Test
    public void testShouldPassAnalysisForAnnotationWithValue() throws Exception
    {
        Assert.assertTrue(assertionWithValue.matches(getClass().getMethod("setUp")));
    }

    @Test
    @Dummy("other value")
    public void testShouldFailAnalysisForAnnotationWithValue() throws Exception
    {
        Assert.assertFalse(assertionWithValue.matches(getClass().getMethod("testShouldFailAnalysisForAnnotationWithValue")));
    }

    @Test
    @DummyArray({"other-value", "and-other"})
    public void testShouldFailAnalysisForAnnotationWithMatcher() throws Exception
    {
        Assert.assertFalse(assertionWithContainsMatcher.matches(getClass().getMethod("testShouldFailAnalysisForAnnotationWithMatcher")));
    }

    @Test
    @DummyArray({"my-value", "other"})
    public void testShouldPassAnalysisForAnnotationWithMatcher() throws Exception
    {
        Assert.assertTrue(assertionWithContainsMatcher.matches(getClass().getMethod("testShouldPassAnalysisForAnnotationWithMatcher")));
    }

    @Before
    @Dummy("value to test")
    public void setUp() throws Exception
    {
        assertion = new AnnotationAssertion<Method>(Before.class);
        assertionWithValue = new AnnotationAssertion<Method>(Dummy.class, "value to test");
        assertionWithContainsMatcher =
                new AnnotationAssertion<Method>(DummyArray.class, Matchers.hasItemInArray("my-value"));
    }

    @java.lang.annotation.Retention(value = RetentionPolicy.RUNTIME)
    @java.lang.annotation.Target(value = {ElementType.METHOD})
    public @interface Dummy
    {
        String value();
    }

    @java.lang.annotation.Retention(value = RetentionPolicy.RUNTIME)
    @java.lang.annotation.Target(value = {ElementType.METHOD})
    public @interface DummyArray
    {
        String[] value();
    }
}

