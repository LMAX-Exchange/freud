package org.langera.freud.aclass.assertion;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HasDeclaredFieldAssertionTest 
{
    private HasDeclaredFieldAssertion assertion;
    private HasDeclaredFieldAssertion assertionOnStringField;

    @Test   
    public void shouldPassDeclaredField()
    {
        Assert.assertTrue(assertion.analyse(HasDeclaredFieldAssertionTest.class));        
    }


    @Test
    public void shouldFailDeclaredField()
    {
        Assert.assertFalse(assertionOnStringField.analyse(HasDeclaredFieldAssertionTest.class));
    }

    @Before
    public void setUp()
    {
        assertion = new HasDeclaredFieldAssertion(HasDeclaredFieldAssertion.class);
        assertionOnStringField = new HasDeclaredFieldAssertion(String.class);
    }
}
