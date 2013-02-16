package org.freud.core.iterator

import org.freud.core.Creator
import spock.lang.Specification
import spock.lang.Subject

import static AnalysedObjectBreadcrumbs.BREADCRUMBS

class AnalysedObjectIteratorSpec extends Specification {

    Creator creator = Mock()
    Iterator sourcesIterator = Mock()
    @Subject
    AnalysedObjects analysedObjects

    def setup() {
       Iterable sources = Mock()
       sources.iterator() >> sourcesIterator
       analysedObjects = new AnalysedObjects(creator, sources)
    }

    def 'returns no next item when there is not one'() {
        given:
            sourcesIterator.hasNext() >> false
            0 * creator._
        expect:
            ! analysedObjects.iterator().hasNext()
    }

    def 'returns true for next item when there is one'() {
        given:
            sourcesIterator.hasNext() >> true
            0 * creator._
        expect:
            analysedObjects.iterator().hasNext()
    }

    def 'returns next items when called in order'() {
        given:
            sourcesIterator.hasNext() >>> [ true, true, false ]
            sourcesIterator.next() >>> [ 'a', 'b' ]
            creator.create('a') >> '1'
            creator.create('b') >> '2'
        when:
            List results = []
            for (Object o : analysedObjects) {
                results.add(o)
            }
        then:
            results == ['1', '2']
    }


    def 'saves sources and analysed object in order as breadcrumbs'() {
    given:
        sourcesIterator.hasNext() >>> [ true, true, false ]
        sourcesIterator.next() >>> [ 'a', 'b' ]
        creator.create('a') >> '1'
        creator.create('b') >> '2'
        Iterator iterator = analysedObjects.iterator()
    when:
        iterator.next()
    then:
        BREADCRUMBS.size() == 1
        BREADCRUMBS.get(0) == 'a'
    when:
        iterator.next()
    then:
        BREADCRUMBS.size() == 1
        BREADCRUMBS.get(0) == 'b'
    }


    def 'clears breadcrumbs at start of each run'() {
    given:
        sourcesIterator.hasNext() >>> [ true, true, false ]
        sourcesIterator.next() >>> [ 'a', 'b' ]
        creator.create('a') >> '1'
        creator.create('b') >> '2'
        Iterator iterator = analysedObjects.iterator()
        Iterator otherIterator =
                new AnalysedObjects<String, String>({ it } as Creator,
                new AnalysedObjects<String, String>({ it } as Creator,
                new AnalysedObjects<String, String>({ it } as Creator, ['x', 'y', 'z']))).iterator()
    when:
        otherIterator.next()
        iterator.next()
    then:
        BREADCRUMBS.size() == 1
        BREADCRUMBS.get(0) == 'a'
    }

    def 'does not clear breadcrumbs when iterator chained to other breadcrumbs supported iterators'() {
    given:
        AnalysedObjects<String, String> iterator1 =
            new AnalysedObjects<String, String>({ "X${it}" } as Creator, ['1', '2', '3'])
        AnalysedObjects<String, String> iterator2 = new AnalysedObjects({ "${it}X"} as Creator, iterator1)
        Iterator iterator = new AnalysedObjects({ it } as Creator, iterator2).iterator()
    when:
        iterator.next()
    then:
        BREADCRUMBS.size() == 3
        BREADCRUMBS.get(0) == '1'
        BREADCRUMBS.get(1) == 'X1'
        BREADCRUMBS.get(2) == 'X1X'
    }


    def 'blows up when called and there is no next item'() {
        given:
            sourcesIterator.next() >> { throw new NoSuchElementException() }
            0 * creator._
        when:
            analysedObjects.iterator().next()
        then:
            thrown NoSuchElementException
    }
}
