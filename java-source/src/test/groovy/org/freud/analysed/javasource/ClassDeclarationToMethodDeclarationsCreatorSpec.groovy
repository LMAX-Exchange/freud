package org.freud.analysed.javasource

import spock.lang.Specification
import spock.lang.Subject

class ClassDeclarationToMethodDeclarationsCreatorSpec extends Specification {

    @Subject
    ClassDeclarationToMethodDeclarationsCreator creator = new ClassDeclarationToMethodDeclarationsCreator()

    def 'returns method declarations from class declaration'() {
    given:
        ClassDeclaration classDeclaration = Mock()
        MethodDeclaration m1 = Mock()
        MethodDeclaration m2 = Mock()
        MethodDeclaration m3 = Mock()
        classDeclaration.methodDeclarationListByNameMap >> [ method1: [m1, m2], method2: [m3] ]
    expect:
        creator.create(classDeclaration) as List == [ m1, m2, m3 ]
    }
}
