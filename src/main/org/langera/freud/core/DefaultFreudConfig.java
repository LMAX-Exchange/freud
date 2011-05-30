package org.langera.freud.core;

import org.langera.freud.core.iterator.AnalysedObjectIterator;

public final class DefaultFreudConfig<T> implements FreudConfig<T>
{
    private static final DefaultFreudConfig SINGLETON = new DefaultFreudConfig();

    private DefaultFreudConfig()
    {
        // singleton
    }

    @SuppressWarnings("unchecked")
    public static <T> FreudConfig<T> getInstance()
    {
        return SINGLETON;
    }

    @Override
    public AnalysedObjectIterator<T> iteratorAdapter(final AnalysedObjectIterator<?> superTypeIterator) throws FreudBuilderException
    {
        throw new FreudBuilderException("Config missing");
    }

}
