package org.freud.analysed.properties

import spock.lang.Specification
import spock.lang.Subject

class PropertiesFromUrlCreatorSpec extends Specification {

    @Subject
    PropertiesFromUrlCreator creator = new PropertiesFromUrlCreator()

    def 'build Properties from a URL'() {
    when:
        Properties result = creator.create(ClassLoader.getSystemResource('PropertiesFromFileCreatorSpec/file.properties'))
    then:
        result == [a: 'b', c: 'd', e: 'f'] as Properties
    }
}
