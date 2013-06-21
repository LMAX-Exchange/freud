package org.freud.java

import org.freud.core.Creator
import org.freud.core.Filter
import org.freud.core.iterator.AnalysedObjects
import org.freud.core.iterator.FilteredAnalysedObjects
import org.freud.core.iterator.SubTypeAnalysedObjects
import org.hamcrest.BaseMatcher
import org.hamcrest.CoreMatchers
import org.hamcrest.Description
import spock.lang.Specification

import static org.freud.core.iterator.AnalysedObjectBreadcrumbs.BREADCRUMBS

class FreudSpec extends Specification {

    def 'returns the return value from assertion'() {
    expect:
        Freud.analyse('',  CoreMatchers.anything())
        !Freud.analyse('', CoreMatchers.not(CoreMatchers.anything()))
    }

    def 'has access to breadcrumbs'() {
    given:
        Iterator<String> iterator = new AnalysedObjects({ "Z$it" } as Creator, new SubTypeAnalysedObjects({ ["X$it", "Y$it"] } as Creator,
                new FilteredAnalysedObjects(
                        new AnalysedObjects({ "_$it" } as Creator, ['1', '2', '3']), { !it.contains('3')} as Filter))
        ).iterator()

    expect:
        Freud.analyse(iterator.next(), new MyCustomMatcher())
    }

    private static class MyCustomMatcher extends BaseMatcher {

        private Object[] copy

        @Override
        boolean matches(final Object item) {
            copy = BREADCRUMBS.copy()
            return item == 'ZX_1' && copy.size() == 3 && copy[0] == '1' && copy[1] == '_1' && copy[2] == 'X_1'
        }

        @Override
        void describeTo(final Description description) {
            description.appendText("Expected: ['1', '_1', 'X_1'] Got: $copy")
        }
    }
}
