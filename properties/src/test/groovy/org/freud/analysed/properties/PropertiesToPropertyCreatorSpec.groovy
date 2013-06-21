package org.freud.analysed.properties

import spock.lang.Specification
import spock.lang.Subject

class PropertiesToPropertyCreatorSpec extends Specification {

    @Subject
    PropertiesToPropertyCreator creator = new PropertiesToPropertyCreator()

    def 'build Properties from a String'() {
    when:
        Iterable<Property> result = creator.create([a: 'b', c: 'd', e: 'f'] as Properties)
    then:
        result.collect().sort() { it.key }  as List ==
                [ new Property('a', 'b'), new Property('c', 'd'), new Property('e', 'f')]
    }
}
