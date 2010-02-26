package org.langera.freud.aclass.assertion;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public final class HasConstructorArgOfTypeAssertionTest
{
    private HasConstructorArgOfTypeAssertion assertionOfStringArg;
    private HasConstructorArgOfTypeAssertion assertionOfIntegerArg;

    @Test
    public void shouldPassConstructorArg()
    {
        Assert.assertTrue(assertionOfStringArg.analyse(String.class));
    }


    @Test
    public void shouldFailConstructorArg()
    {
        Assert.assertFalse(assertionOfIntegerArg.analyse(String.class));
    }

    @Before
    public void setUp()
    {
        assertionOfIntegerArg = new HasConstructorArgOfTypeAssertion(Integer.class);
        assertionOfStringArg = new HasConstructorArgOfTypeAssertion(String.class);
    }
}
