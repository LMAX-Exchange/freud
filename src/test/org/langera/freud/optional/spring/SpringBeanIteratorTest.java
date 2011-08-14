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

package org.langera.freud.optional.spring;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.StaticApplicationContext;

public final class SpringBeanIteratorTest
{

    @Test
    public void shouldIterateOverSpringBeansWithCorrectHasNext()
    {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("test-ac.xml");
        SpringBeanIterator iterator = new SpringBeanIterator(applicationContext, true);

        Assert.assertTrue(iterator.hasNext());
        final SpringBean bean1 = iterator.next();
        Assert.assertEquals("bean1", bean1.getBeanName());
        Assert.assertEquals(17, bean1.getBean());
        Assert.assertTrue(iterator.hasNext());
        final SpringBean bean2 = iterator.next();
        Assert.assertEquals("bean2", bean2.getBeanName());
        Assert.assertEquals(19, bean2.getBean());
        Assert.assertFalse(iterator.hasNext());
    }

    @Test
    public void shouldIterateOverSpringBeans()
    {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("test-ac.xml");
        SpringBeanIterator iterator = new SpringBeanIterator(applicationContext, true);

        final SpringBean bean1 = iterator.next();
        Assert.assertEquals("bean1", bean1.getBeanName());
        Assert.assertEquals(17, bean1.getBean());
        final SpringBean bean2 = iterator.next();
        Assert.assertEquals("bean2", bean2.getBeanName());
        Assert.assertEquals(19, bean2.getBean());
        Assert.assertNull(iterator.next());
    }


    @Test
    public void shouldIterateOverEmptyList()
    {
        StaticApplicationContext staticApplicationContext = new StaticApplicationContext();
        SpringBeanIterator iterator = new SpringBeanIterator(staticApplicationContext, false);
        Assert.assertFalse(iterator.hasNext());
    }    
}
