package org.freud.groovy

import org.freud.core.Creator
import org.freud.core.Filter
import org.freud.core.iterator.AnalysedObjects
import org.freud.core.iterator.FilteredAnalysedObjects
import org.freud.core.iterator.SubTypeAnalysedObjects
import spock.lang.Specification

class FreudSpec extends Specification {

    def 'usage example with spock data driven test'() {
    expect:
        Freud.analyse(analysed) { it.startsWith('Z') }
    where:
        analysed << new AnalysedObjects({ "Z$it" } as Creator, new SubTypeAnalysedObjects({ ["X$it", "Y$it"] } as Creator,
                new FilteredAnalysedObjects(
                        new AnalysedObjects({ "_$it" } as Creator, ['1', '2', '3']), { it.contains('3')} as Filter))
        )
    }

    def 'returns the return value from assertion'() {
    expect:
        Freud.analyse('', { true })
        !Freud.analyse('', { false })
    }

    def 'passes analysed object and creators to assertion'() {
    given:
        Iterator iterator = new AnalysedObjects({ "Z$it" } as Creator, new SubTypeAnalysedObjects({ ["X$it", "Y$it"] } as Creator,
                new FilteredAnalysedObjects(
                        new AnalysedObjects({ "_$it" } as Creator, ['1', '2', '3']), { it.contains('3')} as Filter))
        ).iterator()

    expect:
        Freud.analyse(iterator.next(), { a -> a == 'ZX_1' })
        Freud.analyse(iterator.next(), { a, b -> a == 'ZY_1' && b == 'Y_1' })
        Freud.analyse(iterator.next(), { a, b, c -> a == 'ZX_2' && b == 'X_2' && c == '_2' })
        Freud.analyse(iterator.next(), { a, b, c, d -> a == 'ZY_2' && b == 'Y_2' && c == '_2' && d == '2' })
    }

    def 'blows up if assertion has too many parameters'() {
    given:
        Iterator iterator = new AnalysedObjects({ "Z$it" } as Creator, new SubTypeAnalysedObjects({ ["X$it", "Y$it"] } as Creator,
                new FilteredAnalysedObjects(
                        new AnalysedObjects({ "_$it" } as Creator, ['1', '2', '3']), { it.contains('3')} as Filter))
        ).iterator()

    when:
        Freud.analyse(iterator.next(), { a, b, c, d, e -> true })
    then:
        thrown IllegalArgumentException
    }

    def 'creates a FreudSource of files'() {
        // TODO
    }
}
