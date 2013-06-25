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
        analysedObjects = new AnalysedObjects(creator, sources)
    }

    def 'returns no next item when there is not one'() {
    given:
        sourcesIterator.hasNext() >> false
        0 * creator._
    expect:
        !analysedObjects.iterator().hasNext()
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
        sourcesIterator.hasNext() >>> [true, true, false]
        sourcesIterator.next() >>> ['a', 'b']
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
        sourcesIterator.hasNext() >>> [true, true, false]
        sourcesIterator.next() >>> ['a', 'b']
        creator.create('a') >> '1'
        creator.create('b') >> '2'
        analysedObjects = new AnalysedObjects(creator, sources)
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
        Iterator iterator = new AnalysedObjects({ '1' } as Creator, ['a'], false).iterator()
    when:
        iterator.next()
    then:
        BREADCRUMBS.size() == 0
    }


    def 'clears breadcrumbs at start of each run'() {
    given:
        sourcesIterator.hasNext() >>> [true, true, false]
        sourcesIterator.next() >>> ['a', 'b']
        creator.create('a') >> '1'
        creator.create('b') >> '2'
        Iterator iterator = analysedObjects.iterator()
        Iterator otherIterator =
            new AnalysedObjects<String, String>({ it } as Creator,
                                                new AnalysedObjects<String, String>({ it } as Creator,
                                                                                    new AnalysedObjects<String, String>({ it } as Creator, ['x', 'y', 'z'])
                                                )
            ).iterator()
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
