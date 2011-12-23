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
        return Freud.iterateOver(Method.class).
                forEach(declaredMethod().and(publicMethod())).
                of(subTypeOf(JdbcDaoSupport.class), Class.class).
                of(springBeanName().matches(".*?Dao"), SpringBean.class).
                assertThat(methodAnnotation(Transactional.class)).within(iterator);

    }
}