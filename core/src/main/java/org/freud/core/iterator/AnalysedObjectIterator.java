package org.freud.core.iterator;

import org.freud.core.Creator;

import java.util.Iterator;

public final class AnalysedObjectIterator<S, A> extends SupportsBreadcrumbs<S, A> {

    private final Creator<S, A> creator;
    private final Iterator<S> sourcesIterator;

    public AnalysedObjectIterator(final Creator<S, A> creator, final Iterable<S> sources) {
        super(getDepth(sources) + 1);
        this.creator = creator;
        this.sourcesIterator = sources.iterator();
    }

    @Override
    protected boolean calculateHasNext() {
        return sourcesIterator.hasNext();
    }

    @Override
    public A next() {
        final S source = sourcesIterator.next();
        handleBreadcrumbs(source);
        return creator.create(source);
    }
}
