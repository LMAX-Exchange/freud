package org.freud.core.listener;

public interface AnalysisListener
{
    void passed(Object analysedObject);

    void failed(Object analysedObject, String details);

    void filtered(Object analysedObject, String details);

    void unexpected(Object analysedObject, Exception exception);

    void done();

}
