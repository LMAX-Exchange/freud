package org.langera.freud.optional.classobject.method;

import org.langera.freud.core.FreudBuilderException;
import org.langera.freud.core.FreudConfig;
import org.langera.freud.core.iterator.AnalysedObjectIterator;
import org.langera.freud.core.iterator.SubTypeAnalysedObjectIterator;
import org.langera.freud.core.iterator.SubTypeIteratorBuilder;

import java.lang.reflect.Method;
import java.util.Arrays;

public final class MethodFreudConfig implements FreudConfig<Method>
{
    @Override
    @SuppressWarnings("unchecked")
    public AnalysedObjectIterator<Method> iteratorAdapter(final AnalysedObjectIterator<?> superTypeIterator) throws FreudBuilderException
    {
        if (Class.class.equals(superTypeIterator.analysedObjectType()))
        {
            return new SubTypeAnalysedObjectIterator<Class, Method>((AnalysedObjectIterator<Class>) superTypeIterator,
                    new SubTypeIteratorBuilder<Class, Method>()
                    {
                        @Override
                        public Iterable<Method> buildIterable(final Class superTypeItem)
                        {
                            return Arrays.asList(superTypeItem.getMethods());
                        }
                    }, Method.class);
        }
        else
        {
            throw new FreudBuilderException("Cannot iterate over TextLine objects from [" +
                    superTypeIterator.analysedObjectType() + "] iterator.");
        }
    }
}
