package org.freud.core.listener;

public class NoOpAnalysisListener implements AnalysisListener {
    @Override
    public void failed(final Object analysedObject, final String details) {
        // no op
    }

    @Override
    public void passed(final Object analysedObject) {
        // no op
    }

    @Override
    public void filtered(final Object analysedObject, final String details) {
        // no op
    }

    @Override
    public void unexpected(final Object analysedObject, final Exception exception) {
        // no op
    }

    @Override
    public void done() {
        // no op
    }
}
