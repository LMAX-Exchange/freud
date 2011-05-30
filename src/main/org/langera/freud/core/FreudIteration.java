package org.langera.freud.core;

import org.langera.freud.core.iterator.AnalysedObjectIterator;

public interface FreudIteration<T>
{
    FreudRule<T> in(AnalysedObjectIterator<T> iterator);

    FreudRule<T> within(AnalysedObjectIterator<?> iterator);
}
