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

import static org.freud.core.iterator.AnalysedObjectBreadcrumbs.BREADCRUMBS

class AnalysedObjectBreadcrumbsSpec extends Specification {


    @Unroll
    def 'returns elements by index #index'() {
    given:
        BREADCRUMBS.add('0')
        BREADCRUMBS.add('1')
        BREADCRUMBS.add('2')
        BREADCRUMBS.add('3')
    expect:
        BREADCRUMBS.get(index) == expected
    where:
        index | expected
        0     | '0'
        1     | '1'
        2     | '2'
        3     | '3'
    }

    @Unroll
    def 'returns elements by type #type'() {
    given:
        BREADCRUMBS.add('0')
        BREADCRUMBS.add(1)
        BREADCRUMBS.add(BigDecimal.valueOf(2))
        BREADCRUMBS.add('3')
    expect:
        BREADCRUMBS.get(type) == expected
    where:
        type       | expected
        String     | '0'
        int        | 1
        Integer    | 1
        BigDecimal | new BigDecimal(2)
    }

    def setup() {
        BREADCRUMBS.clear()
    }
}
