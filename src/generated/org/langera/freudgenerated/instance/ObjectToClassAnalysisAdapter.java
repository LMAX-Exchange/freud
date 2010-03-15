// Freud generated code 2010-03-15 22:02:26
package org.langera.freudgenerated.instance;

import org.langera.freud.*;
import org.langera.freud.dsl.*;
import java.lang.Object;
import java.lang.Class;
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