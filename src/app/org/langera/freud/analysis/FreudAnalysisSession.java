package org.langera.freud.analysis;

import org.langera.freud.core.EmbeddedFreudAnalyser;
import org.langera.freud.core.FreudRule;
import org.langera.freud.core.listener.AnalysisListener;

public final class FreudAnalysisSession
{
    private final EmbeddedFreudAnalyser[] analysers;
    private final AnalysisListener listener;

    public FreudAnalysisSession(final FreudSessionContext sessionContext, final FreudRule... rules)
    {
        sessionContext.sessionRuntimeContextSetUp();
        listener = sessionContext.getListener();
        int i = 0;
        analysers = new EmbeddedFreudAnalyser[rules.length];
        for (FreudRule rule : rules)
        {
            analysers[i] = rule.embedded();
            analysers[i].getType();
            i++;
        }
    }

    public void analyse()
    {

    }
}
