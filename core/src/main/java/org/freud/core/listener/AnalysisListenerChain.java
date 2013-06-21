package org.freud.core.listener;

public final class AnalysisListenerChain implements AnalysisListener {
    private AnalysisListener[] listeners;

    public AnalysisListenerChain(AnalysisListener... listeners) {
        this.listeners = listeners;
    }

    @Override
    public void failed(Object analysedObject, String details) {
        for (AnalysisListener listener : listeners) {
            listener.failed(analysedObject, details);
        }
    }

    @Override
    public void filtered(Object analysedObject, String details) {
        for (AnalysisListener listener : listeners) {
            listener.filtered(analysedObject, details);
        }
    }

    @Override
    public void passed(Object analysedObject) {
        for (AnalysisListener listener : listeners) {
            listener.passed(analysedObject);
        }
    }

    @Override
    public void unexpected(Object analysedObject, Exception exception) {
        for (AnalysisListener listener : listeners) {
            listener.unexpected(analysedObject, exception);
        }
    }

    @Override
    public void done() {
        for (AnalysisListener listener : listeners) {
            listener.done();
        }
    }
}
