package org.freud.analysed.classobject

import spock.lang.Specification
import spock.lang.Subject

class ClassFromFileCreatorSpec extends Specification {

    private static final File ROOT_DIR = new File('/tmp')
    private static final Class<String> CLASS_OBJECT = String

    ClassLoader classLoader = Mock()
    ClassFromNameCreator fromNameCreator = new ClassFromNameCreator(classLoader)
    @Subject
    ClassFromFileCreator creator = new ClassFromFileCreator(fromNameCreator, ROOT_DIR)

    def setup() {
        classLoader.loadClass('org.freud.classname') >> CLASS_OBJECT
    }

    def 'creates class from class file using name'() {
    when:
        Class classObject = creator.create(new File(ROOT_DIR, 'org/freud/classname.class'))
    then:
        classObject == CLASS_OBJECT
    }

    def 'creates class from source file'() {
    when:
        Class classObject = creator.create(new File(ROOT_DIR, 'org/freud/classname.java'))
    then:
        classObject == CLASS_OBJECT
    }

    def 'creates class from source file even on inferior operating systems'() {
    when:
        Class classObject = creator.create(new File(ROOT_DIR, 'org\\freud\\classname.java'))
    then:
        classObject == CLASS_OBJECT
    }

}
