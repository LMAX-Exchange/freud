// Freud generated code 2010-08-24 21:26:16
package org.langera.freudgenerated.instance;

import org.langera.freud.NestedTypeAnalysisAdapter;

import java.util.Collections;

public final class ObjectToClassAnalysisAdapter implements NestedTypeAnalysisAdapter<Object, Class>
{
    private static final ObjectToClassAnalysisAdapter SINGLETON = new ObjectToClassAnalysisAdapter();

    private ObjectToClassAnalysisAdapter()
    {
        // singleton
    }

    public static ObjectToClassAnalysisAdapter getInstance()
    {
        return SINGLETON;
    }

    public Iterable<Class> getNestedObjectsToAnalyse(Object toBeAnalysed)
    {
        return Collections.<Class>singleton(toBeAnalysed.getClass());
    }
}