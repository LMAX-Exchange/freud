package org.freud.core.iterator

import spock.lang.Specification
import spock.lang.Unroll

class FlattenedCollectionSpec extends Specification {

    @Unroll
    def 'returns correct size #size for #collection'() {
        expect:
            collection.size() == size
        where:
            collection                                          | size
            new FlattenedCollection([])                             | 0
            new FlattenedCollection([['1']])                        | 1
            new FlattenedCollection([['1', '2']])                   | 2
            new FlattenedCollection([['1'], ['2']])                 | 2
            new FlattenedCollection([['1'], ['2', '3']])            | 3
            new FlattenedCollection([['1','2'], ['2', '3'], ['3']]) | 5
    }

    @Unroll
    def 'flattens all collections in #collection'() {
    expect:
        collection.iterator().collect() == expected
    where:
        collection                                          | expected
        new FlattenedCollection([])                             | []
        new FlattenedCollection([['1']])                        | ['1']
        new FlattenedCollection([['1', '2']])                   | ['1', '2']
        new FlattenedCollection([['1'], ['2']])                 | ['1', '2']
        new FlattenedCollection([['1'], ['2', '3']])            | ['1', '2', '3']
        new FlattenedCollection([['1','2'], ['2', '3'], ['3']]) | ['1', '2', '2', '3', '3']
    }

}
