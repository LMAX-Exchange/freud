package org.freud.analysed.javasource

import spock.lang.Specification
import spock.lang.Subject

class MethodDeclarationToParamDeclarationsCreatorSpec extends Specification {

    @Subject
    MethodDeclarationToParamDeclarationsCreator creator = new MethodDeclarationToParamDeclarationsCreator()

    def 'returns import declarations for Java source'() {
    given:
        MethodDeclaration methodDeclaration = Mock()
        ParamDeclaration param1 = Mock()
        ParamDeclaration param2 = Mock()
        ParamDeclaration param3 = Mock()
        methodDeclaration.parametersDeclarations >> [param1, param2, param3]
    expect:
        creator.create(methodDeclaration) as List == [ param1, param2, param3 ]
    }    
}
