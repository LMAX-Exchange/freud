package org.freud.analysed.properties

import spock.lang.Specification
import spock.lang.Subject

class PropertiesFromStringCreatorSpec extends Specification {

    @Subject
    PropertiesFromStringCreator creator = new PropertiesFromStringCreator()

    def 'build Properties from a String'() {
    when:
        Properties result = creator.create('a=b\nc=d\ne=f')
    then:
        result == [a: 'b', c: 'd', e: 'f'] as Properties
    }
}
