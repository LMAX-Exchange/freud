/*
 * Copyright (c) 2011.
 * This file is part of "Freud".
 *
 * Freud is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Freud is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Freud.  If not, see <http://www.gnu.org/licenses/>.
 * @author Amir Langer  langera_at_gmail_dot_com
 */

package org.langera.examples.spring;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.langera.freud.core.FreudAnalyser;
import org.langera.freud.core.listener.AnalysisListenerStub;
import org.langera.freud.optional.spring.SpringBeanIterator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static org.langera.freud.optional.classobject.method.MethodTestMatcher.method;


public final class AnalyseAllPublicMethodsOfDaoBeansMustHaveTransactionalAnnotationTest
{
    private AnalysisListenerStub listener;
    private ApplicationContext applicationContext;

    @Test
    public void shouldAnalysePassFailAndFilter()
    {
        FreudAnalyser analysis = SpringBeanExamples.allPublicMethodsOfDaoBeansMustHaveTransactionalAnnotation(
                new SpringBeanIterator(applicationContext, true)
        );

        analysis.analyse(listener);

        Assert.assertEquals(42, listener.getTotalObjectsAnalysed());
        listener.assertPassed(method("goodDao"));
        listener.assertFailed(method("badDao"));
        listener.assertFiltered(method("getDataSource"));
        listener.assertFiltered(method("someOtherMethod"));
    }

    @Before
    public void setUp() throws Exception
    {
        listener = new AnalysisListenerStub();
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
        public void someOtherMethod()
        {
            // do nothing
        }
    }
}
