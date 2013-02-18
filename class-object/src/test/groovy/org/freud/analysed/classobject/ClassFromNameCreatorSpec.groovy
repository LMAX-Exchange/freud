package org.freud.analysed.classobject

import spock.lang.Specification
import spock.lang.Subject


class ClassFromNameCreatorSpec extends Specification {

    private static final Class<String> CLASS_OBJECT = String
    ClassLoader classLoader = Mock()
    @Subject
    ClassFromNameCreator creator = new ClassFromNameCreator(classLoader)

    def 'creator uses ClassLoader to create class'() {
    given:
        classLoader.loadClass('org.classname') >> CLASS_OBJECT
    when:
        Class classObject = creator.create('org.classname')
    then:
        classObject == CLASS_OBJECT
    }
}
