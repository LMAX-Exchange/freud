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
        filter.filter('a') >> true
        filter.filter('b') >> false
        filter.filter('c') >> false
        filter.filter('d') >> false
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
        filter.filter('a') >> true
        filter.filter('b') >> true
        filter.filter('c') >> true
        filter.filter('d') >> false
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
        filter.filter('a') >> false
        filter.filter('b') >> false
        filter.filter('c') >> false
        filter.filter('d') >> true
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
        filter.filter('a') >> true
        filter.filter('b') >> true
        filter.filter('c') >> true
        filter.filter('d') >> true
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
            new FilteredAnalysedObjects(new AnalysedObjects(['a', 'b', 'c', 'd'], { it } as Creator), filter).iterator()
        filter.filter('a') >> true
        filter.filter('b') >> false
        filter.filter('c') >> true
        filter.filter('d') >> false
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
