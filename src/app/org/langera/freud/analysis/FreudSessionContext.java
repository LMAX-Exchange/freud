package org.langera.freud.analysis;

import org.langera.freud.core.listener.AnalysisListener;

public interface FreudSessionContext
{
    void sessionRuntimeContextSetUp();

    AnalysisListener getListener();
}
