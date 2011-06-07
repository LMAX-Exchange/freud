package org.langera.freud.optional.classobject.method;

import org.langera.freud.core.FreudBuilderException;
import org.langera.freud.core.FreudConfig;
import org.langera.freud.core.iterator.AnalysedObjectIterator;
import org.langera.freud.core.iterator.SubTypeAnalysedObjectIterator;
import org.langera.freud.core.iterator.SubTypeIteratorBuilder;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public final class MethodFreudConfig implements FreudConfig<Method>
{

    @Override
    public Class<?> supports()
    {
        return Class.class;
    }

    @Override
    @SuppressWarnings("unchecked")
    public AnalysedObjectIterator<Method> iteratorAdapter(final AnalysedObjectIterator<?> superTypeIterator) throws FreudBuilderException
    {
        return new SubTypeAnalysedObjectIterator<Class, Method>((AnalysedObjectIterator<Class>) superTypeIterator,
                new SubTypeIteratorBuilder<Class, Method>()
                {
                    @Override
                    public Iterable<Method> buildIterable(final Class superTypeItem)
                    {
                        final Method[] methods = superTypeItem.getMethods();
                        final Method[] declaredMethods = superTypeItem.getDeclaredMethods();
                        final Set<Method> methodSet = new HashSet<Method>(methods.length + declaredMethods.length);
                        for (int i = 0; i < methods.length; i++)
                        {
                            methodSet.add(methods[i]);
                        }
                        for (int i = 0; i < declaredMethods.length; i++)
                        {
                            methodSet.add(declaredMethods[i]);
                        }
                        return methodSet;
                    }
                }, Method.class);
    }
}
