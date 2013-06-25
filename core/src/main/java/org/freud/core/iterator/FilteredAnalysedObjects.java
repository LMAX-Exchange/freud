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
                if (filter.accept(current)) {
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
                if (filter.accept(nextToReturn)) {
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
