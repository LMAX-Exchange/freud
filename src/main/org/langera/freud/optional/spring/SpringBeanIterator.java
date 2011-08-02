package org.langera.freud.optional.spring;

import org.langera.freud.core.iterator.AbstractAnalysedObjectIterator;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;

/**
 * This file is part of "Freud".
 * <p/>
 * Freud is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p/>
 * Freud is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU General Public License
 * along with Freud.  If not, see <http://www.gnu.org/licenses/>.
 *
 * @author Amir Langer  langera_at_gmail_dot_com
 */

public final class SpringBeanIterator extends AbstractAnalysedObjectIterator<SpringBean>
{
    private final String[] beanNames;
    private final ApplicationContext applicationContext;

    private transient int ptr = 0;

    public SpringBeanIterator(final ApplicationContext applicationContext, final boolean alertOnEmptyIterator)
    {
        this(applicationContext, alertOnEmptyIterator, applicationContext.getBeanDefinitionNames());
    }

    public SpringBeanIterator(final ApplicationContext applicationContext, final boolean alertOnEmptyIterator, final Class<? extends Integer> beansType)
    {
        this(applicationContext, alertOnEmptyIterator, applicationContext.getBeanNamesForType(beansType));
    }

    public SpringBeanIterator(final ApplicationContext applicationContext, final boolean alertOnEmptyIterator, final String... beanNames)
    {
        super(SpringBean.class, alertOnEmptyIterator);
        this.applicationContext = applicationContext;
        this.beanNames = beanNames;
    }

    @SuppressWarnings({"ThrowableInstanceNeverThrown"})
    @Override
    protected SpringBean generateNextItem()
    {
        while (ptr < beanNames.length)
        {
            final String beanName = beanNames[ptr++];
            try
            {
                final Object bean = applicationContext.getBean(beanName);
                if (bean != null)
                {
                    return new SpringBeanImpl(beanName, bean);
                }
            }
            catch (NoSuchBeanDefinitionException e)
            {
                getListener().unexpected(null, new IllegalArgumentException("Failed to retrieve spring bean [" + beanName + "]", e));
            }
            catch (BeansException e)
            {
                // bean is abstract / inner
                // ignore
            }
        }
        return null;
    }
}
