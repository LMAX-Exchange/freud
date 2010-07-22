// Freud generated code 2010-07-22 08:46:00
package org.langera.freudgenerated.javasource;

import org.langera.freud.NestedTypeAnalysisAdapter;
import org.langera.freud.javasource.JavaSource;

import java.util.Collections;

public final class JavaSourceToClassAnalysisAdapter implements NestedTypeAnalysisAdapter<JavaSource, Class>
{
    private static final JavaSourceToClassAnalysisAdapter SINGLETON = new JavaSourceToClassAnalysisAdapter();

    private JavaSourceToClassAnalysisAdapter()
    {
        // singleton
    }

    public static JavaSourceToClassAnalysisAdapter getInstance()
    {
        return SINGLETON;
    }

    public Iterable<Class> getNestedObjectsToAnalyse(JavaSource toBeAnalysed)
    {
        return Collections.<Class>singleton(toBeAnalysed.loadClass());
    }
}