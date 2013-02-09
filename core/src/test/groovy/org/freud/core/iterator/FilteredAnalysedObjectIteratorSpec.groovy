package org.freud.core.iterator

import org.freud.core.Filter
import spock.lang.Specification
import spock.lang.Subject

import static org.freud.core.iterator.AnalysedObjectBreadcrumbs.BREADCRUMBS
import org.freud.core.Creator

class FilteredAnalysedObjectIteratorSpec extends Specification {

    @Subject
    FilteredAnalysedObjectIterator iterator
    Filter filter

    def setup() {
        filter = Mock()
        iterator = new FilteredAnalysedObjectIterator(['a', 'b', 'c', 'd'], filter)
    }

    def 'first item is filtered'() {
    given:
        filter.filter('a') >> true
        filter.filter('b') >> false
        filter.filter('c') >> false
        filter.filter('d') >> false
    when:
        List results = []
        for (Object o : iterator) {
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
        for (Object o : iterator) {
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
        for (Object o : iterator) {
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
        for (Object o : iterator) {
            results.add(o)
        }
    then:
        results == []
    }

    def 'stores items as breadcrumbs if not filtered'() {
    given:
        filter.filter('a') >> true
        filter.filter('b') >> false
        filter.filter('c') >> true
        filter.filter('d') >> false
    when:
        iterator.next();
    then:
        BREADCRUMBS.size() == 1
        BREADCRUMBS.get(0) == 'b'
    when:
        iterator.next();
    then:
        BREADCRUMBS.size() == 1
        BREADCRUMBS.get(0) == 'd'
    }


    def 'do not store twice items as breadcrumbs when previous iterator supports breadcrumbs as well'() {
    given:
        iterator = new FilteredAnalysedObjectIterator(new AnalysedObjectIterator({ it } as Creator, ['a', 'b', 'c', 'd']), filter)
        filter.filter('a') >> true
        filter.filter('b') >> false
        filter.filter('c') >> true
        filter.filter('d') >> false
    when:
        iterator.next();
    then:
        BREADCRUMBS.size() == 1
        BREADCRUMBS.get(0) == 'b'
    when:
        iterator.next();
    then:
        BREADCRUMBS.size() == 1
        BREADCRUMBS.get(0) == 'd'
    }
}
