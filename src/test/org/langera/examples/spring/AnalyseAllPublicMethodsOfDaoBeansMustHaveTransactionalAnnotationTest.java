package org.langera.examples.spring;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.langera.freud.Analysis;
import org.langera.freud.TestAnalysisListenerStub;
import org.langera.freud.spring.SpringBeanIterator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static org.langera.freud.method.MethodMatcher.method;
import static org.langera.freud.spring.SpringBeanNameMatcher.beanNamed;

public final class AnalyseAllPublicMethodsOfDaoBeansMustHaveTransactionalAnnotationTest
{
    private TestAnalysisListenerStub listener;
    private ApplicationContext applicationContext;

    @Test
    public void shouldAnalysePassFailAndFilter()
    {
        Analysis analysis = SpringBeanExamples.allPublicMethodsOfDaoBeansMustHaveTransactionalAnnotation(
                new SpringBeanIterator(applicationContext, true)
        );

        analysis.analyse(listener);

        Assert.assertEquals(31, listener.getTotalObjectsAnalysed());
        listener.assertPassed(method("goodDao"));
        listener.assertFailed(method("badDao"));
        listener.assertFiltered(method("getDataSource"));
        listener.assertFiltered(beanNamed("someOtherBean"));
    }

    @Before
    public void setUp() throws Exception
    {
        listener = new TestAnalysisListenerStub();
        applicationContext = new ClassPathXmlApplicationContext("dao-transactional-example-ac.xml");
        
    }

    public static final class GoodExampleDao extends JdbcDaoSupport
    {
        @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
        public void goodDao()
        {
            // do nothing
        }

        @Override
        protected void checkDaoConfig()
        {
            // its fine
        }
    }

    public static final class BadExampleDao extends JdbcDaoSupport
    {
        public void badDao()
        {
            // do nothing
        }

        @Override
        protected void checkDaoConfig()
        {
            // its fine
        }
    }

    public static final class SomeOtherBean
    {
    }
}
