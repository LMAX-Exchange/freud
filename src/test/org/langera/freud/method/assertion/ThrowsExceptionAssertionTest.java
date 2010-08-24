package org.langera.freud.method.assertion;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public final class ThrowsExceptionAssertionTest
{
    private ThrowsExceptionAssertion throwsExceptionAssertion;
    private ThrowsExceptionAssertion throwsRuntimeExceptionAssertion;

    @Test
    public void shouldFailAnalysisBecauseMethodDoesNotThrowAnyException() throws Exception
    {
        Assert.assertFalse(throwsExceptionAssertion.matches(ThrowsExceptionAssertionTest.class.getMethod("setUp")));
    }

    @Test
    public void shouldPassAnalysis() throws Exception
    {
        Assert.assertTrue(throwsExceptionAssertion.matches(MethodModifierAssertionTest.class.getMethod("shouldPassAnalysis")));
    }

    @Test
    public void shouldFailAnalysisBecauseMethodThrowsOtherException() throws Exception
    {
        Assert.assertFalse(throwsRuntimeExceptionAssertion.matches(MethodModifierAssertionTest.class.getMethod("shouldPassAnalysis")));
    }

    @Before
    public final void setUp()
    {
        throwsExceptionAssertion = new ThrowsExceptionAssertion(Exception.class);
        throwsRuntimeExceptionAssertion = new ThrowsExceptionAssertion(RuntimeException.class);
    }
}
