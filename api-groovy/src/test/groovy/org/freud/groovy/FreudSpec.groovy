package org.freud.groovy

import org.freud.core.Creator
import org.freud.core.Filter
import org.freud.core.iterator.AnalysedObjectIterator
import org.freud.core.iterator.FilteredAnalysedObjectIterator
import org.freud.core.iterator.SubTypeAnalysedObjectIterator
import spock.lang.Specification

class FreudSpec extends Specification {

    def 'usage example with spock data driven test'() {
    expect:
        Freud.analyse(analysed) { it.startsWith('Z') }
    where:
        analysed << new AnalysedObjectIterator({ "Z$it" } as Creator,
                new SubTypeAnalysedObjectIterator({ ["X$it", "Y$it"] } as Creator,
                        new FilteredAnalysedObjectIterator(
                                new AnalysedObjectIterator({ "_$it" } as Creator, ['1', '2', '3']), { it.contains('3')} as Filter)))
    }

    def 'returns the return value from assertion'() {
    expect:
        Freud.analyse('', { true })
        !Freud.analyse('', { false })
    }

    def 'passes analysed object and creators to assertion'() {
    given:
        Iterator iterator = new AnalysedObjectIterator({ "Z$it" } as Creator,
                new SubTypeAnalysedObjectIterator({ ["X$it", "Y$it"] } as Creator,
                        new FilteredAnalysedObjectIterator(
                                new AnalysedObjectIterator({ "_$it" } as Creator, ['1', '2', '3']), { it.contains('3')} as Filter)))

    expect:
        Freud.analyse(iterator.next(), { a -> a == 'ZX_1' })
        Freud.analyse(iterator.next(), { a, b -> a == 'ZY_1' && b == 'Y_1' })
        Freud.analyse(iterator.next(), { a, b, c -> a == 'ZX_2' && b == 'X_2' && c == '_2' })
        Freud.analyse(iterator.next(), { a, b, c, d -> a == 'ZY_2' && b == 'Y_2' && c == '_2' && d == '2' })
    }

    def 'blows up if assertion has too many parameters'() {
    given:
        Iterator iterator = new AnalysedObjectIterator({ "Z$it" } as Creator,
                new SubTypeAnalysedObjectIterator({ ["X$it", "Y$it"] } as Creator,
                        new FilteredAnalysedObjectIterator(
                                new AnalysedObjectIterator({ "_$it" } as Creator, ['1', '2', '3']), { it.contains('3')} as Filter)))

    when:
        Freud.analyse(iterator.next(), { a, b, c, d, e -> true })
    then:
        thrown IllegalArgumentException
    }
}
