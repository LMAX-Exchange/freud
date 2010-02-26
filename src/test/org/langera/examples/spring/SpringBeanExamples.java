package org.langera.examples.spring;

import org.langera.freud.Analysis;
import org.langera.freud.spring.SpringBean;
import org.langera.freud.util.collection.AnalysedObjectIterator;
import org.langera.freudgenerated.spring.SpringBeanAnalysis;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.transaction.annotation.Transactional;

public final class SpringBeanExamples
{
    private SpringBeanExamples()
    {
        // a class of static methods - should not be initialised
    }


    public static Analysis allPublicMethodsOfDaoBeansMustHaveTransactionalAnnotation(final AnalysedObjectIterator<SpringBean> iterator)
    {
        return new SpringBeanAnalysis(iterator)
        {
            {
                forEach(declaredMethod().and(publicMethod())).
                        of(subTypeOf(JdbcDaoSupport.class)).of(springBean().matches(".*?Dao"));
                assertThat(methodAnnotation(Transactional.class));
            }
        };
    }
}