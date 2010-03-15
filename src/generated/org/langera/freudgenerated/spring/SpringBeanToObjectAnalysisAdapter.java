// Freud generated code 2010-03-15 22:02:26
package org.langera.freudgenerated.spring;

import org.langera.freud.*;
import org.langera.freud.dsl.*;
import org.langera.freud.spring.SpringBean;
import java.lang.Object;
import java.util.Collections;    

public final class SpringBeanToObjectAnalysisAdapter implements NestedTypeAnalysisAdapter<SpringBean, Object>
{
    private static final SpringBeanToObjectAnalysisAdapter SINGLETON = new SpringBeanToObjectAnalysisAdapter();

    private SpringBeanToObjectAnalysisAdapter()
    {
        // singleton
    }

    public static SpringBeanToObjectAnalysisAdapter getInstance()
    {
        return SINGLETON;
    }

    public Iterable<Object> getNestedObjectsToAnalyse(SpringBean toBeAnalysed)
    {
        return Collections.singleton(toBeAnalysed.getBean());
    }
}