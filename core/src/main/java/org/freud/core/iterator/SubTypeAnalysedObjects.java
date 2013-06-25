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

import org.freud.core.Creator;

import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;

public final class SubTypeAnalysedObjects<T, A> extends SupportsBreadcrumbs<T, A> {

    private final Iterator<T> superTypeIterator;
    private final Creator<T, Iterable<A>> creator;


    public SubTypeAnalysedObjects(final Creator<T, Iterable<A>> creator,
                                  final Iterable<T> superTypeIterable) {
        this(creator, superTypeIterable, true);
    }

    public SubTypeAnalysedObjects(final Creator<T, Iterable<A>> creator,
                                  final Iterable<T> superTypeIterable,
                                  final boolean breadcrumbsEnabled) {
        super(getDepth(superTypeIterable) + 1, breadcrumbsEnabled);
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
