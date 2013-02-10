package org.freud.core.iterator;

import org.freud.core.Filter;

import java.util.Iterator;
import java.util.NoSuchElementException;

public final class FilteredAnalysedObjectIterator<A> extends SupportsBreadcrumbs<A, A> {

    private final Iterator<A> unfiltered;
    private final Filter<A> filter;
    private A current = null;

    public FilteredAnalysedObjectIterator(final Iterable<A> unfiltered, final Filter<A> filter) {
        super(getDepth(unfiltered));
        this.unfiltered = unfiltered.iterator();
        this.filter = filter;
    }

    @Override
    protected boolean calculateHasNext() {
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
}
