package org.langera.freud.core.iterator;

public interface SubTypeIteratorBuilder<S,T>
{
    Iterable<T> buildIterable(S superTypeItem);
}
