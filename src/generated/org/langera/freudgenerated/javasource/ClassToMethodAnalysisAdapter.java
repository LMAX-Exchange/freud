// Freud generated code 2010-08-24 21:26:16
package org.langera.freudgenerated.javasource;

import org.langera.freud.NestedTypeAnalysisAdapter;

import java.lang.reflect.Method;
import java.util.Arrays;

public final class ClassToMethodAnalysisAdapter implements NestedTypeAnalysisAdapter<Class, Method>
{
    private static final ClassToMethodAnalysisAdapter SINGLETON = new ClassToMethodAnalysisAdapter();

    private ClassToMethodAnalysisAdapter()
    {
        // singleton
    }

    public static ClassToMethodAnalysisAdapter getInstance()
    {
        return SINGLETON;
    }

    public Iterable<Method> getNestedObjectsToAnalyse(Class toBeAnalysed)
    {
        return Arrays.asList(toBeAnalysed.getMethods());
    }
}