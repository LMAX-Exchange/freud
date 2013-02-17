package org.freud.core.iterator;

import static java.lang.Math.max;
import static org.freud.core.iterator.AnalysedObjectBreadcrumbs.BREADCRUMBS;

public abstract class SupportsBreadcrumbs<S, A> implements Iterable<A> {

    private final int depth;
    private final boolean enabled;
    private boolean firstInvocation = true;

    protected SupportsBreadcrumbs(final int depth) {
        this(depth, true);
    }

    protected SupportsBreadcrumbs(final int depth, final boolean enabled) {
        this.depth = max(depth, 0);
        this.enabled = enabled;
    }


    protected void handleBreadcrumbs(final S source) {
        if (firstInvocation) {
            if (depth == 0) {
                BREADCRUMBS.clear();
            }
            firstInvocation = false;
        }
        if (enabled) {
            if (BREADCRUMBS.size() == depth) {
                BREADCRUMBS.add(source);
            }
            else {
                BREADCRUMBS.set(depth, source);
            }
        }
    }

    protected static int getDepth(Iterable sourcesIterable) {
        return (SupportsBreadcrumbs.class.isAssignableFrom(sourcesIterable.getClass())) ?
                ((SupportsBreadcrumbs) sourcesIterable).depth : -1;
    }
}
