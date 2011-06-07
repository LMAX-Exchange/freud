package org.langera.freud.core;

import org.langera.freud.core.iterator.AnalysedObjectIterator;

import java.util.HashMap;
import java.util.Map;

public final class FreudRuntimeContext
{
    private FreudRuntimeContext()
    {
        // static utility
    }

    private static final ThreadLocal<Map<Class, AnalysedObjectIterator>> CONTEXT =
            new ThreadLocal<Map<Class, AnalysedObjectIterator>>()
            {
                @Override
                protected Map<Class, AnalysedObjectIterator> initialValue()
                {
                    return new HashMap<Class, AnalysedObjectIterator>();
                }
            };

    public static void clear()
    {
        CONTEXT.get().clear();
    }

    @SuppressWarnings("unchecked")
    public static <T> T getCurrentlyAnalysed(Class<T> type)
    {
        final AnalysedObjectIterator analysedObjectIterator = CONTEXT.get().get(type);
        return (T) ((analysedObjectIterator == null) ? null : analysedObjectIterator.current());
    }

    static <T> void register(AnalysedObjectIterator<T> iterator)
    {
        CONTEXT.get().put(iterator.analysedObjectType(), iterator);
    }
}
