package org.freud.core.iterator;

import org.freud.core.SubTypesCreator;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public final class SubTypeAnalysedObjectIterator<A, S, T> extends SupportsBreadcrumbs<T, A> {

    private final AnalysedObjectIterator<S, T> superTypeIterator;
    private final SubTypesCreator<T, A> creator;
    private final LinkedList<A> currentSubTypes = new LinkedList<A>();
    private T currentSuperType;

    public SubTypeAnalysedObjectIterator(final AnalysedObjectIterator<S, T> superTypeIterator,
                                         final SubTypesCreator<T, A> creator) {
        super(getDepth(superTypeIterator) + 1);
        this.superTypeIterator = superTypeIterator;
        this.creator = creator;
    }

    @Override
    public boolean hasNext() {
        iterate();
        return !currentSubTypes.isEmpty();
    }

    @Override
    public A next() {
        iterate();
        if (currentSubTypes.isEmpty()) {
            throw new NoSuchElementException();
        }
        handleBreadcrumbs(currentSuperType);
        return currentSubTypes.removeFirst();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<A> iterator() {
        return this;
    }

    private void iterate() {
        while (currentSubTypes.isEmpty() && superTypeIterator.hasNext()) {
            currentSuperType = superTypeIterator.next();
            creator.create(currentSuperType, currentSubTypes);
        }
    }
}
