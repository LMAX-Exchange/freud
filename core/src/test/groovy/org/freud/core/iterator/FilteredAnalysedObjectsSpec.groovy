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



package org.freud.core.iterator

import org.freud.core.Creator
import org.freud.core.Filter
import spock.lang.Specification
import spock.lang.Subject

import static org.freud.core.iterator.AnalysedObjectBreadcrumbs.BREADCRUMBS

class FilteredAnalysedObjectsSpec extends Specification {

    @Subject
    FilteredAnalysedObjects filtered
    Filter filter

    def setup() {
        filter = Mock()
        filtered = new FilteredAnalysedObjects(['a', 'b', 'c', 'd'], filter)
    }

    def 'first item is filtered'() {
    given:
        filter.accept('a') >> false
        filter.accept('b') >> true
        filter.accept('c') >> true
        filter.accept('d') >> true
    when:
        List results = []
        for (Object o : filtered) {
            results.add(o)
        }
    then:
        results == ['b', 'c', 'd']
    }

    def 'several items are filtered'() {
    given:
        filter.accept('a') >> false
        filter.accept('b') >> false
        filter.accept('c') >> false
        filter.accept('d') >> true
    when:
        List results = []
        for (Object o : filtered) {
            results.add(o)
        }
    then:
        results == ['d']
    }

    def 'last item is filtered'() {
    given:
        filter.accept('a') >> true
        filter.accept('b') >> true
        filter.accept('c') >> true
        filter.accept('d') >> false
    when:
        List results = []
        for (Object o : filtered) {
            results.add(o)
        }
    then:
        results == ['a', 'b', 'c']
    }

    def 'all items are filtered'() {
    given:
        filter.accept('a') >> false
        filter.accept('b') >> false
        filter.accept('c') >> false
        filter.accept('d') >> false
    when:
        List results = []
        for (Object o : filtered) {
            results.add(o)
        }
    then:
        results == []
    }

    def 'does not store items as breadcrumbs - only a filter'() {
    given:
        Iterator filteredIterator =
            new FilteredAnalysedObjects(new AnalysedObjects({ it } as Creator, ['a', 'b', 'c', 'd']), filter).iterator()
        filter.accept('a') >> false
        filter.accept('b') >> true
        filter.accept('c') >> false
        filter.accept('d') >> true
    when:
        filteredIterator.next();
    then:
        BREADCRUMBS.size() == 1
        BREADCRUMBS.get(0) == 'b'
    when:
        filteredIterator.next();
    then:
        BREADCRUMBS.size() == 1
        BREADCRUMBS.get(0) == 'd'
    }
}
