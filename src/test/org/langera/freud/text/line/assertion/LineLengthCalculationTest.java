package org.langera.freud.text.line.assertion;

import org.junit.Assert;
import org.junit.Test;
import org.langera.freud.text.line.TextLine;

public class LineLengthCalculationTest 
{
    @Test
    public void testShouldTestLineLength() throws Exception 
    {
        LineLengthCalculation assertion = LineLengthCalculation.getInstance();

        Assert.assertTrue(10 == assertion.analyse(new TextLine("1234567890", 1, null, null)));
    }

}
