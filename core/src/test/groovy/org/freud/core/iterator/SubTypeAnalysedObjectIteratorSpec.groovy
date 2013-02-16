package org.freud.core.iterator

import org.freud.core.Creator
import spock.lang.Specification
import spock.lang.Subject

import static AnalysedObjectBreadcrumbs.BREADCRUMBS

class SubTypeAnalysedObjectIteratorSpec extends Specification {

    @Subject
    SubTypeAnalysedObjects subTypeAnalysedObjects
    Creator creator

    def setup() {
        creator = Mock()
        subTypeAnalysedObjects = new SubTypeAnalysedObjects(creator, ['a', 'b', 'c', 'd'])
    }

    def 'element contains several sub types'() {
    given:
        creator.create('a') >> ['a1', 'a2', 'a3']
        creator.create(!'a') >> ['x']
    when:
        List results = []
        for (Object o : subTypeAnalysedObjects) {
            results.add(o)
        }
    then:
        results == ['a1', 'a2', 'a3', 'x', 'x', 'x']
    }

    def 'element contains no sub types'() {
    given:
        creator.create('a') >> []
        creator.create(!'a') >> ['x']
    when:
        List results = []
        for (Object o : subTypeAnalysedObjects) {
            results.add(o)
        }
    then:
        results == ['x', 'x', 'x']
    }

    def 'element contains one sub types'() {
    given:
        creator.create('a') >> ['a']
        creator.create(!'a') >> ['x']
    when:
        List results = []
        for (Object o : subTypeAnalysedObjects) {
            results.add(o)
        }
    then:
        results == ['a', 'x', 'x', 'x']
    }

    def 'elements contains variations of sub types'() {
    given:
        creator.create('a') >> []
        creator.create('b') >> ['b1', 'b2', 'b3']
        creator.create('c') >> ['c1', 'c2']
        creator.create('d') >> ['d']
    when:
        List results = []
        for (Object o : subTypeAnalysedObjects) {
            results.add(o)
        }
    then:
        results == ['b1', 'b2', 'b3', 'c1', 'c2', 'd']
    }

    def 'analysed object saved in breadcrumbs'() {
    given:
        creator.create('a') >> []
        creator.create('b') >> ['b1', 'b2']
        creator.create('c') >> ['c1', 'c2']
        creator.create('d') >> ['d']
        Iterator iterator = subTypeAnalysedObjects.iterator()
    when:
        iterator.next()
    then:
        BREADCRUMBS.size() == 1
        BREADCRUMBS.get(0) == 'b'
    when:
        iterator.next()
    then:
        BREADCRUMBS.size() == 1
        BREADCRUMBS.get(0) == 'b'
    when:
        iterator.next()
    then:
        BREADCRUMBS.size() == 1
        BREADCRUMBS.get(0) == 'c'
    }

    def 'analysed object appended to breadcrumbs'() {
    given:
        Iterator iterator = new SubTypeAnalysedObjects(creator,
                        new AnalysedObjects({ "X$it" } as Creator, ['a', 'b', 'c', 'd'])).iterator()
        creator.create('Xa') >> []
        creator.create('Xb') >> ['b1', 'b2']
        creator.create('Xc') >> ['c1', 'c2']
        creator.create('Xd') >> ['d']
    when:
        iterator.next()
    then:
        BREADCRUMBS.size() == 2
        BREADCRUMBS.get(0) == 'b'
        BREADCRUMBS.get(1) == 'Xb'
    when:
        iterator.next()
    then:
        BREADCRUMBS.size() == 2
        BREADCRUMBS.get(0) == 'b'
        BREADCRUMBS.get(1) == 'Xb'
    when:
        iterator.next()
    then:
        BREADCRUMBS.size() == 2
        BREADCRUMBS.get(0) == 'c'
        BREADCRUMBS.get(1) == 'Xc'
    }
}
