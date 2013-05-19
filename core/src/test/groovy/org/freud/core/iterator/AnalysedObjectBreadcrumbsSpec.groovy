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
