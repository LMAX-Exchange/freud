
package org.langera.freud.text.line.assertion;

import org.junit.Assert;
import org.junit.Test;
import org.langera.freud.text.line.TextLine;

public class LineNumberCalculationTest
{


    @Test
    public void testShouldTestLineNumber() throws Exception
    {
        LineNumberCalculation assertion = LineNumberCalculation.getInstance();

        Assert.assertTrue(11 == assertion.analyse(new TextLine("123456789", 11, null, null)));
    }

}
