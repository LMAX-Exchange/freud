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

import spock.lang.Specification
import spock.lang.Unroll

class FlattenedCollectionSpec extends Specification {

    @Unroll
    def 'returns correct size #size for #collection'() {
    expect:
        collection.size() == size
    where:
        collection                                               | size
        new FlattenedCollection([])                              | 0
        new FlattenedCollection([['1']])                         | 1
        new FlattenedCollection([['1', '2']])                    | 2
        new FlattenedCollection([['1'], ['2']])                  | 2
        new FlattenedCollection([['1'], ['2', '3']])             | 3
        new FlattenedCollection([['1', '2'], ['2', '3'], ['3']]) | 5
    }

    @Unroll
    def 'flattens all collections in #collection'() {
    expect:
        collection.iterator().collect() == expected
    where:
        collection                                               | expected
        new FlattenedCollection([])                              | []
        new FlattenedCollection([['1']])                         | ['1']
        new FlattenedCollection([['1', '2']])                    | ['1', '2']
        new FlattenedCollection([['1'], ['2']])                  | ['1', '2']
        new FlattenedCollection([['1'], ['2', '3']])             | ['1', '2', '3']
        new FlattenedCollection([['1', '2'], ['2', '3'], ['3']]) | ['1', '2', '2', '3', '3']
    }

}
