// Freud generated code 2010-02-14 19:03:25
package org.langera.freudgenerated.spring;

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