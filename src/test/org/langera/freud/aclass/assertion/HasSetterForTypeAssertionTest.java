package org.langera.freud.aclass.assertion;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public final class HasSetterForTypeAssertionTest
{
    private HasSetterForTypeAssertion assertionOfObjectProperty;
    private HasSetterForTypeAssertion assertionOfIntProperty;

    @Test
    public void shouldPassSetter()
    {
        Assert.assertTrue(assertionOfIntProperty.matches(BigDecimal.class));
    }


    @Test
    public void shouldFailSetter()
    {
        Assert.assertFalse(assertionOfObjectProperty.matches(String.class));
    }

    @Before
    public void setUp()
    {
        assertionOfIntProperty = new HasSetterForTypeAssertion(int.class);
        assertionOfObjectProperty = new HasSetterForTypeAssertion(Object.class);
    }
}
