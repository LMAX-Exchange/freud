package org.freud.core.iterator;

import java.util.Iterator;

public abstract class OneShotNonThreadSafeIterable<T> implements Iterable<T>, Iterator<T> {

    private boolean hasNext = true;

    @Override
    public final Iterator<T> iterator() {
        return this;
    }

    @Override
    public final boolean hasNext() {
        if (!hasNext) {
            throw new IllegalStateException("This iterator can only be iterated through once");
        }
        hasNext = calculateHasNext();
        return hasNext;
    }

    protected abstract boolean calculateHasNext();

    @Override
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
