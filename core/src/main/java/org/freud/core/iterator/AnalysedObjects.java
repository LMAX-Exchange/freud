package org.freud.core.iterator;

import org.freud.core.Creator;

import java.util.Iterator;

public final class AnalysedObjects<S, A> extends SupportsBreadcrumbs<S, A> {

    private final Creator<S, A> creator;
    private final Iterator<S> sourcesIterator;

    public AnalysedObjects(final Iterable<S> sources, final Creator<S, A> creator) {
        this(sources, creator, true);
    }

    public AnalysedObjects(final Iterable<S> sources, final Creator<S, A> creator, final boolean breadcrumbsEnabled) {
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
