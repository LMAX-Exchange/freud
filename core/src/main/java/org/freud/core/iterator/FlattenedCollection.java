package org.freud.core.iterator;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public final class FlattenedCollection<E> extends AbstractCollection<E> {
    private Collection<? extends Collection<E>> collectionCollection;

    public FlattenedCollection(Collection<? extends Collection<E>> collectionCollection) {
        this.collectionCollection = collectionCollection;
    }

    @Override
    public Iterator<E> iterator() {
        return new FlattenedIterator<E>(collectionCollection);
    }

    @Override
    public int size() {
        int sz = 0;
        for (Collection<E> collection : collectionCollection) {
            sz += collection.size();
        }
        return sz;
    }

    private static final class FlattenedIterator<E> implements Iterator<E> {
        private final Iterator<? extends Collection<E>> collectionIterator;
        private Iterator<E> tempIterator;

        public FlattenedIterator(Collection<? extends Collection<E>> collectionCollection) {
            this.collectionIterator = collectionCollection.iterator();
        }

        public boolean hasNext() {
            if (tempIterator == null || !tempIterator.hasNext()) {
                if (collectionIterator.hasNext()) {
                    tempIterator = collectionIterator.next().iterator();
                    return hasNext();
                }
                else {
                    return false;
                }
            }
            return true;
        }

        public E next() {
            if (tempIterator == null) {
                throw new NoSuchElementException();
            }
            return tempIterator.next();
        }

        public void remove() {
            if (tempIterator == null) {
                throw new IllegalStateException();
            }
            tempIterator.remove();
        }
    }

}
