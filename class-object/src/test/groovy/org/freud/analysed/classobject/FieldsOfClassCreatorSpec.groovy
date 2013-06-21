package org.freud.analysed.classobject

import spock.lang.Specification
import spock.lang.Subject

import static org.freud.analysed.classobject.FieldsOfClassCreator.Type.DECLARED
import static org.freud.analysed.classobject.FieldsOfClassCreator.Type.PUBLIC

class FieldsOfClassCreatorSpec extends Specification {

    @Subject
    FieldsOfClassCreator creator = new FieldsOfClassCreator()

    def 'generates fields for class'() {
    expect:
        creator.create(Integer) as Set == (Integer.fields + Integer.declaredFields) as Set
    }

    def 'generates public fields for class'() {
    given:
        creator = new FieldsOfClassCreator(PUBLIC)
    expect:
        creator.create(Integer) as Set == Integer.fields as Set
    }

    def 'generates declared fields for class'() {
    given:
        creator = new FieldsOfClassCreator(DECLARED)
    expect:
        creator.create(Integer) as Set == Integer.declaredFields as Set
    }

    def 'generates public fields for inner class'() {
    given:
        creator = new FieldsOfClassCreator(PUBLIC)
    expect:
        creator.create(InnerClass) as List == InnerClass.fields as List
    }

    private static class InnerClass {

        private int i, j, k;
    }
}