// Freud generated code 2010-07-22 08:46:00
package org.langera.freudgenerated.instance;

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