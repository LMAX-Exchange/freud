package org.langera.freud.listener;

import org.junit.Assert;
import org.junit.Test;

@SuppressWarnings({"ThrowableInstanceNeverThrown"})
public class AssertionErrorAnalysisListenerTest
{


    @Test
    public void testShouldThrowAssertionErrorOnlyOnFailureOrUnexpected() throws Exception 
    {
        AssertionErrorAnalysisListener listener = new AssertionErrorAnalysisListener();

        listener.passed(null, null);
        listener.filtered(null, null);
        
        try
        {
            listener.failed(null, null);
            Assert.fail();
        } catch (AssertionError e)
        {
            Assert.assertEquals("Analysis on [null] failed.", e.getMessage());
        }

        try
        {
            listener.unexpected(null, new Exception("foo"));
            Assert.fail();
        } catch (AssertionError e)
        {
            Assert.assertEquals("Unexpected exception [java.lang.Exception: foo]", e.getMessage());
        }
    }
}
