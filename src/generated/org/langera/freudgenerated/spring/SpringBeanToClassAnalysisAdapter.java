// Freud generated code 2010-08-24 21:26:16
package org.langera.freudgenerated.spring;

import org.langera.freud.NestedTypeAnalysisAdapter;
import org.langera.freud.spring.SpringBean;

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