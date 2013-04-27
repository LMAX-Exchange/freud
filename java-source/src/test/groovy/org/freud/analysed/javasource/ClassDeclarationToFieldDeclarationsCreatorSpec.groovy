package org.freud.analysed.javasource

import spock.lang.Specification
import spock.lang.Subject

class ClassDeclarationToFieldDeclarationsCreatorSpec extends Specification {

    @Subject
    ClassDeclarationToFieldDeclarationsCreator creator = new ClassDeclarationToFieldDeclarationsCreator()

    def 'returns field declarations from class declaration'() {
    given:
        ClassDeclaration classDeclaration = Mock()
        VarDeclaration varDeclaration = Mock()
        classDeclaration.fieldDeclarations >> [ varDeclaration ]
    expect:
        creator.create(classDeclaration) == [ varDeclaration ]
    }
}
