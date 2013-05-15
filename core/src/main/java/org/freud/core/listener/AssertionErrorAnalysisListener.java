package org.freud.core.listener;

public final class AssertionErrorAnalysisListener implements AnalysisListener {
    @Override
    public void failed(Object analysedObject, String details) {
        throw new AssertionError("Analysis on [" + analysedObject + "] failed." +
                (String.valueOf(details)));
    }

    @Override
    public void filtered(Object analysedObject, String details) {
        // do nothing
    }

    @Override
    public void passed(Object analysedObject) {
        // do nothing
    }

    @Override
    public void unexpected(Object analysedObject, Exception exception) {
        throw new AssertionError("Unexpected exception [" + exception + "]" +
                ((analysedObject == null) ? "" : " while analysing [" + analysedObject + "]."));
    }

    @Override
    public void done() {
        // do nothing
    }
}
