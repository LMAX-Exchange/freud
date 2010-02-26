package org.langera.freud.aclass.assertion;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class SubTypeOfAssertionTest 
{
    private SubTypeOfAssertion assertion;

    @Test
    public void testShouldMatchSubType() throws Exception 
    {
        Assert.assertTrue(assertion.analyse(ArrayList.class));
    }

    @Test
    public void testShouldNotMatchANonSubType() throws Exception 
    {
        Assert.assertFalse(assertion.analyse(HashMap.class));
    }

    @Before
    public void setUp()
    {
        assertion = new SubTypeOfAssertion(Collection.class);        
    }
}
