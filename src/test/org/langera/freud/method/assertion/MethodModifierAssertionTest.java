package org.langera.freud.method.assertion;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Modifier;

public final class MethodModifierAssertionTest
{
    private MethodModifierAssertion finalModifierAssertion;

    @Test
    public void shouldPassAnalysis() throws Exception
    {
        Assert.assertTrue(finalModifierAssertion.analyse(MethodModifierAssertionTest.class.getMethod("setUp")));
    }

    @Test
    public void shouldFailAnalysis() throws Exception
    {
        Assert.assertFalse(finalModifierAssertion.analyse(MethodModifierAssertionTest.class.getMethod("shouldPassAnalysis")));
    }

    @Before
    public final void setUp()
    {
        finalModifierAssertion = new MethodModifierAssertion(Modifier.FINAL);    
    }

}
