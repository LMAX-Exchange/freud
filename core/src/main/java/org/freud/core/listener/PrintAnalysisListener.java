package org.freud.core.listener;

import java.io.PrintWriter;

public final class PrintAnalysisListener implements AnalysisListener {
    private final PrintWriter out;

    public PrintAnalysisListener(final PrintWriter out) {
        this.out = out;
    }

    @Override
    public void failed(Object analysedObject, String details) {
        out.println("Analysis on [" + analysedObject + "] failed.");
    }

    @Override
    public void filtered(Object analysedObject, String details) {
        out.println("Analysis on [" + analysedObject + "] filtered.");
    }

    @Override
    public void passed(Object analysedObject) {
        out.println("Analysis on [" + analysedObject + "] passed.");
    }

    @Override
    public void unexpected(Object analysedObject, Exception exception) {
        out.println("Analysis got unexpected exception.");
        exception.printStackTrace(out);
    }

    @Override
    public void done() {
        // do nothing
    }
}
