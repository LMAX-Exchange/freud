package org.langera.freud.core;

import org.langera.freud.core.iterator.AnalysedObjectIterator;

public class DefaultFreudConfig<T> implements FreudConfig<T>
{
    private static final DefaultFreudConfig INSTANCE = new DefaultFreudConfig();

    protected DefaultFreudConfig()
    {
    }

    @SuppressWarnings("unchecked")
    public static <T> FreudConfig<T> getInstance()
    {
        return INSTANCE;
    }

    @Override
    public AnalysedObjectIterator<T> iteratorAdapter(final AnalysedObjectIterator<?> superTypeIterator) throws FreudBuilderException
    {
        throw new FreudBuilderException("Config missing");
    }

    @Override
    public Class<?> supports()
    {
        return null;
    }
}
