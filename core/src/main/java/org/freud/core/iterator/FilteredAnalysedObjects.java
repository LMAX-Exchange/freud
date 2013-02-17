package org.freud.core.iterator;

import org.freud.core.Filter;

import java.util.Iterator;
import java.util.NoSuchElementException;

public final class FilteredAnalysedObjects<A> extends SupportsBreadcrumbs<A, A> {

    private final Iterator<A> unfiltered;
    private final Filter<A> filter;

    public FilteredAnalysedObjects(final Iterable<A> unfiltered, final Filter<A> filter) {
        super(getDepth(unfiltered), false);
        this.unfiltered = unfiltered.iterator();
        this.filter = filter;
    }

    @Override
    public Iterator<A> iterator() {
        return new InternalIterator();
    }

    private class InternalIterator implements Iterator<A> {

        private A current = null;

        @Override
        public boolean hasNext() {
            if (current != null) {
                return true;
            }
            while (unfiltered.hasNext()) {
                current = unfiltered.next();
                if (!filter.filter(current)) {
                    return true;
                }
            }
            return false;

        }

        @Override
        public A next() {
            if (current != null) {
                A nextToReturn = current;
                current = null;
                return nextToReturn;
            }
            while (unfiltered.hasNext()) {
                A nextToReturn = unfiltered.next();
                if (!filter.filter(nextToReturn)) {
                    return nextToReturn;
                }
            }
            throw new NoSuchElementException();
        }

        @Override
        public final void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
