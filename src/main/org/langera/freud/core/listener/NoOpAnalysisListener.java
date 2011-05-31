package org.langera.freud.core.listener;

import org.hamcrest.Matcher;

public class NoOpAnalysisListener implements AnalysisListener
{
    @Override
    public void passed(final Object analysedObject, final Matcher matcher)
    {
        // no op
    }

    @Override
    public void failed(final Object analysedObject, final Matcher matcher)
    {
        // no op
    }

    @Override
    public void filtered(final Object analysedObject, final Matcher matcher)
    {
        // no op
    }

    @Override
    public void unexpected(final Object analysedObject, final Exception exception)
    {
        // no op
    }

    @Override
    public void done()
    {
        // no op
    }
}
