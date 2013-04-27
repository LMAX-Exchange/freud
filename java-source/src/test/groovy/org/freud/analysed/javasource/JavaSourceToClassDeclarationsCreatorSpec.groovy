package org.freud.analysed.javasource

import spock.lang.Specification
import spock.lang.Subject

class JavaSourceToClassDeclarationsCreatorSpec extends Specification {

    @Subject
    JavaSourceToClassDeclarationsCreator creator = new JavaSourceToClassDeclarationsCreator()

    def 'returns class declaration for Java source'() {
    given:
        JavaSource javaSource = Mock()
        ClassDeclaration classDeclaration = Mock()
        javaSource.classDeclaration >> classDeclaration
    expect:
        creator.create(javaSource) as List == [ classDeclaration ]
    }
}
