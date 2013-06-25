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

import java.util.Iterator;

public final class AnalysedObjects<S, A> extends SupportsBreadcrumbs<S, A> {

    private final Creator<S, A> creator;
    private final Iterator<S> sourcesIterator;

    public AnalysedObjects(final Creator<S, A> creator, final Iterable<S> sources) {
        this(creator, sources, true);
    }

    public AnalysedObjects(final Creator<S, A> creator, final Iterable<S> sources, final boolean breadcrumbsEnabled) {
        super(getDepth(sources) + 1, breadcrumbsEnabled);
        this.creator = creator;
        this.sourcesIterator = sources.iterator();
    }

    @Override
    public Iterator<A> iterator() {
        return new InternalIterator();
    }

    private class InternalIterator implements Iterator<A> {

        @Override
        public boolean hasNext() {
            return sourcesIterator.hasNext();
        }

        @Override
        public A next() {
            final S source = sourcesIterator.next();
            handleBreadcrumbs(source);
            return creator.create(source);
        }

        @Override
        public final void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
