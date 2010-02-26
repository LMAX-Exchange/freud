// Freud generated code 2010-02-14 19:03:25
package org.langera.freudgenerated.spring;

import org.langera.freud.*;
import org.langera.freud.dsl.*;
import org.langera.freud.spring.SpringBean;
import java.lang.Class;
import java.util.Collections;    

public final class SpringBeanToClassAnalysisAdapter implements NestedTypeAnalysisAdapter<SpringBean, Class>
{
    private static final SpringBeanToClassAnalysisAdapter SINGLETON = new SpringBeanToClassAnalysisAdapter();

    private SpringBeanToClassAnalysisAdapter()
    {
        // singleton
    }

    public static SpringBeanToClassAnalysisAdapter getInstance()
    {
        return SINGLETON;
    }

    public Iterable<Class> getNestedObjectsToAnalyse(SpringBean toBeAnalysed)
    {
        return Collections.singleton(toBeAnalysed.getTargetClass());
    }
}