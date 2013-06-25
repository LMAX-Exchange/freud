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
