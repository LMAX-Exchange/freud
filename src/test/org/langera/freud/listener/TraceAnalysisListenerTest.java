package org.langera.freud.listener;

import org.junit.Test;

public class TraceAnalysisListenerTest 
{
    @Test
    public void testShouldLogAndNotBlowUp() throws Exception 
    {        
        TraceAnalysisListener listener = new TraceAnalysisListener();

        listener.passed(null, null);
        listener.filtered(null, null);
        listener.failed(null, null);
        listener.unexpected(null, null);
    }
}
