package org.langera.examples.spring;

import org.langera.freud.core.Freud;
import org.langera.freud.core.FreudAnalyser;
import org.langera.freud.core.iterator.AnalysedObjectIterator;
import org.langera.freud.optional.classobject.method.MethodFreudConfig;
import org.langera.freud.optional.spring.ClassObjectOfSpringBeanFreudConfig;
import org.langera.freud.optional.spring.SpringBean;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;

import static org.langera.freud.optional.classobject.ClassObjectDsl.subTypeOf;
import static org.langera.freud.optional.classobject.method.MethodDsl.declaredMethod;
import static org.langera.freud.optional.classobject.method.MethodDsl.methodAnnotation;
import static org.langera.freud.optional.classobject.method.MethodDsl.publicMethod;
import static org.langera.freud.optional.spring.SpringBeanDsl.springBeanName;

public final class SpringBeanExamples
{
    static
    {
        // Class is a third party class that needs a config as SpringBean being its super type - point ot it using a System property
        System.setProperty(Class.class.getName() + Freud.FREUD_CONFIG_SUFFIX, ClassObjectOfSpringBeanFreudConfig.class.getName());

        // Method is a third party class that needs a config - point ot it using a System property
        System.setProperty(Method.class.getName() + Freud.FREUD_CONFIG_SUFFIX, MethodFreudConfig.class.getName());
    }

    private SpringBeanExamples()
    {
        // a class of static methods - should not be initialised
    }


    public static FreudAnalyser allPublicMethodsOfDaoBeansMustHaveTransactionalAnnotation(final AnalysedObjectIterator<SpringBean> iterator)
    {
        return Freud.iterateOver(Method.class).within(iterator).
                forEach(declaredMethod().and(publicMethod())).
                of(subTypeOf(JdbcDaoSupport.class), Class.class).
                of(springBeanName().matches(".*?Dao"), SpringBean.class).
                assertThat(methodAnnotation(Transactional.class));

    }
}