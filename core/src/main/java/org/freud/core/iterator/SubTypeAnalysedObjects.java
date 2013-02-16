package org.freud.core.iterator;

import org.freud.core.Creator;

import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;

public final class SubTypeAnalysedObjects<T, A> extends SupportsBreadcrumbs<T, A> {

    private final Iterator<T> superTypeIterator;
    private final Creator<T, Iterable<A>> creator;


    public SubTypeAnalysedObjects(final Creator<T, Iterable<A>> creator,
                                  final Iterable<T> superTypeIterable) {
        super(getDepth(superTypeIterable) + 1);
        this.superTypeIterator = superTypeIterable.iterator();
        this.creator = creator;
    }

    @Override
    public Iterator<A> iterator() {
        return new InternalIterator();
    }

    private class InternalIterator implements Iterator<A> {

        private Iterator<A> currentSubTypes = Collections.<A>emptyList().iterator();
        private T currentSuperType;

        @Override
        public boolean hasNext() {
            iterate();
            return currentSubTypes.hasNext();
        }

        @Override
        public A next() {
            iterate();
            if (!currentSubTypes.hasNext()) {
                throw new NoSuchElementException();
            }
            handleBreadcrumbs(currentSuperType);
            return currentSubTypes.next();
        }

        @Override
        public final void remove() {
            throw new UnsupportedOperationException();
        }

        private void iterate() {
            while (!currentSubTypes.hasNext() && superTypeIterator.hasNext()) {
                currentSuperType = superTypeIterator.next();
                currentSubTypes = creator.create(currentSuperType).iterator();
            }
        }
    }
}
