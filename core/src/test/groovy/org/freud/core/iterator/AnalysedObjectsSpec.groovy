package org.freud.core.iterator

import org.freud.core.Creator
import spock.lang.Specification
import spock.lang.Subject

import static AnalysedObjectBreadcrumbs.BREADCRUMBS

class AnalysedObjectsSpec extends Specification {

    Creator creator = Mock()
    Iterable sources = Mock()
    Iterator sourcesIterator = Mock()
    @Subject
    AnalysedObjects analysedObjects

    def setup() {
       sources.iterator() >> sourcesIterator
       analysedObjects = new AnalysedObjects(sources, creator)
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
        analysedObjects = new AnalysedObjects(sources, creator)
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


    def 'does not save breadcrumbs when not enabled'() {
    given:
        Iterator iterator = new AnalysedObjects(['a'], { '1' } as Creator, false).iterator()
    when:
        iterator.next()
    then:
        BREADCRUMBS.size() == 0
    }


    def 'clears breadcrumbs at start of each run'() {
    given:
        sourcesIterator.hasNext() >>> [ true, true, false ]
        sourcesIterator.next() >>> [ 'a', 'b' ]
        creator.create('a') >> '1'
        creator.create('b') >> '2'
        Iterator iterator = analysedObjects.iterator()
        Iterator otherIterator =
                new AnalysedObjects<String, String>(
                        new AnalysedObjects<String, String>(
                            new AnalysedObjects<String, String>(['x', 'y', 'z'], { it } as Creator),
                                { it } as Creator),
                        { it } as Creator).iterator()
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
            new AnalysedObjects<String, String>(['1', '2', '3'], { "X${it}" } as Creator)
        AnalysedObjects<String, String> iterator2 = new AnalysedObjects(iterator1, { "${it}X"} as Creator)
        Iterator iterator = new AnalysedObjects(iterator2, { it } as Creator).iterator()
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
