/*
 * Copyright 2013 LMAX Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
