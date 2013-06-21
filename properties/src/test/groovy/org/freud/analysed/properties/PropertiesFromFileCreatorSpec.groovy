package org.freud.analysed.properties

import spock.lang.Specification
import spock.lang.Subject

class PropertiesFromFileCreatorSpec extends Specification {

    @Subject
    PropertiesFromFileCreator creator = new PropertiesFromFileCreator()

    def 'build Properties from a File'() {
    given:
        File file = new File(ClassLoader.getSystemResource('PropertiesFromFileCreatorSpec/file.properties').file)
    when:
        Properties result = creator.create(file)
    then:
        result == [a: 'b', c: 'd', e: 'f'] as Properties
    }
}
