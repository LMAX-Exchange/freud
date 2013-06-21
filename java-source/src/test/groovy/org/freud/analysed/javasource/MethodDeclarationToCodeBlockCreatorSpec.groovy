package org.freud.analysed.javasource

import spock.lang.Specification
import spock.lang.Subject

class MethodDeclarationToCodeBlockCreatorSpec extends Specification {

    @Subject
    MethodDeclarationToCodeBlockCreator creator = new MethodDeclarationToCodeBlockCreator()

    def 'returns implementation code block for method declaration'() {
    given:
        MethodDeclaration methodDeclaration = Mock()
        CodeBlock codeBlock = Mock()
        methodDeclaration.implementation >> codeBlock
    expect:
        creator.create(methodDeclaration) == codeBlock
    }
}
