package org.freud.analysed.classobject

import spock.lang.Specification
import spock.lang.Subject

import static org.freud.analysed.classobject.MethodsOfClassCreator.Type.DECLARED
import static org.freud.analysed.classobject.MethodsOfClassCreator.Type.PUBLIC

class MethodsOfClassCreatorSpec extends Specification {

    @Subject
    MethodsOfClassCreator creator = new MethodsOfClassCreator()

    def 'generates methods for class'() {
    expect:
        creator.create(Integer) as Set == (Integer.methods + Integer.declaredMethods) as Set
    }

    def 'generates public methods for class'() {
    given:
        creator = new MethodsOfClassCreator(PUBLIC)
    expect:
        creator.create(Integer) as Set == Integer.methods as Set
    }

    def 'generates declared methods for class'() {
    given:
        creator = new MethodsOfClassCreator(DECLARED)
    expect:
        creator.create(Integer) as Set == Integer.declaredMethods as Set
    }

    def 'generates methods for interface'() {
    expect:
        creator.create(Runnable) as List == [Runnable.getMethod('run')]
    }

    def 'generates public methods for inner class'() {
    given:
        creator = new MethodsOfClassCreator(PUBLIC)
    expect:
        creator.create(InnerClass) as List == InnerClass.methods as List
    }

    private static class InnerClass implements Runnable {

        private void someMethod() {}

        @Override
        void run() {}
    }
}