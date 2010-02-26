package org.langera.freud.spring;

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
