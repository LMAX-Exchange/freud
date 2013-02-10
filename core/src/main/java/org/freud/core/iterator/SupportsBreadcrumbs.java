package org.freud.core.iterator;

import static java.lang.Math.max;
import static org.freud.core.iterator.AnalysedObjectBreadcrumbs.BREADCRUMBS;

public abstract class SupportsBreadcrumbs<S, A> extends OneShotNonThreadSafeIterable<A> {

    private final int depth;
    private boolean firstInvocation = true;

    protected SupportsBreadcrumbs(final int depth) {
        this.depth = max(depth, 0);
    }

    protected void handleBreadcrumbs(final S source) {
        if (firstInvocation) {
            if (depth == 0) {
                BREADCRUMBS.clear();
            }
            firstInvocation = false;
        }
        if (BREADCRUMBS.size() == depth) {
            BREADCRUMBS.add(source);
        }
        else {
            BREADCRUMBS.set(depth, source);
        }
    }

    protected static int getDepth(Iterable sourcesIterable) {
        return (SupportsBreadcrumbs.class.isAssignableFrom(sourcesIterable.getClass())) ?
                ((SupportsBreadcrumbs) sourcesIterable).depth : -1;
    }
}
