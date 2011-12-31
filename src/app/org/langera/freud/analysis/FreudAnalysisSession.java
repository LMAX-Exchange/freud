package org.langera.freud.analysis;

import org.langera.freud.core.EmbeddedFreudAnalyser;
import org.langera.freud.core.FreudConfig;
import org.langera.freud.core.FreudConfigRegistry;
import org.langera.freud.core.FreudRule;
import org.langera.freud.core.listener.AnalysisListener;

public final class FreudAnalysisSession
{
    private final EmbeddedFreudAnalyser[] analysers;
    private final AnalysisListener listener;
    private final FreudConfigRegistry freudConfigRegistry = new FreudConfigRegistry();

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

    @SuppressWarnings("unchecked")
    public void analyse(final Iterable toAnalyse)
    {
        for (Object itemToAnalyse : toAnalyse)
        {
            for (EmbeddedFreudAnalyser analyser : analysers)
            {
                Iterable itemOfType = convertToType(itemToAnalyse, analyser.getType());
                for (Object itemOfRightType : itemOfType)
                {
                    analyser.analyse(listener, itemOfRightType);
                }
            }

        }
    }

    @SuppressWarnings("unchecked")
    private Iterable convertToType(final Object itemToAnalyse, final Class type)
    {
        final FreudConfig config = freudConfigRegistry.configOf(type);
// TODO       return config.iteratorAdapter(itemToAnalyse);
        return null;
    }
}
