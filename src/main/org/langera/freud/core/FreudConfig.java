package org.langera.freud.core;

import org.langera.freud.core.iterator.AnalysedObjectIterator;

public interface FreudConfig<T>
{
    AnalysedObjectIterator<T> iteratorAdapter(AnalysedObjectIterator<?> superTypeIterator) throws FreudBuilderException;
}
