// Freud generated code 2010-02-17 22:32:45
package org.langera.freudgenerated.javasource;

import org.langera.freud.*;
import org.langera.freud.dsl.*;
import java.lang.Class;
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