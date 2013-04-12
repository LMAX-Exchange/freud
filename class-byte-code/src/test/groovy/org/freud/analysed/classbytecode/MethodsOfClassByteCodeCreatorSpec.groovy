package org.freud.analysed.classbytecode

import org.freud.analysed.classbytecode.method.ClassByteCodeMethod
import spock.lang.Specification
import spock.lang.Subject

class MethodsOfClassByteCodeCreatorSpec extends Specification {

    @Subject
    MethodsOfClassByteCodeCreator creator = new MethodsOfClassByteCodeCreator()

    def 'returns all methods from every ClassByteCode'() {
        given:
            ClassByteCode source = Mock()
            ClassByteCodeMethod method1 = Mock()
            ClassByteCodeMethod method2 = Mock()
            source.methodList >> [ method1, method2 ]
        expect:
            creator.create(source) == [ method1, method2 ]

    }
}
