package org.langera.freud.optional.spring;

import org.langera.freud.core.FreudBuilderException;
import org.langera.freud.core.FreudConfig;
import org.langera.freud.core.iterator.AnalysedObjectIterator;
import org.langera.freud.core.iterator.SubTypeAnalysedObjectIterator;
import org.langera.freud.core.iterator.SubTypeIteratorBuilder;

import java.util.Collections;

public final class ClassObjectOfSpringBeanFreudConfig implements FreudConfig<Class>
{
    @Override
    public Class<?> supports()
    {
        return SpringBean.class;
    }

    @Override
    @SuppressWarnings("unchecked")
    public AnalysedObjectIterator<Class> iteratorAdapter(final AnalysedObjectIterator<?> superTypeIterator) throws FreudBuilderException
    {
        return new SubTypeAnalysedObjectIterator<SpringBean, Class>((AnalysedObjectIterator<SpringBean>) superTypeIterator,
                new SubTypeIteratorBuilder<SpringBean, Class>()
                {
                    @Override
                    public Iterable<Class> buildIterable(final SpringBean superTypeItem)
                    {
                        return Collections.singleton(superTypeItem.getTargetClass());
                    }
                }, Class.class);
    }
}
