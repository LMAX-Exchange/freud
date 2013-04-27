package org.freud.analysed.javasource

import spock.lang.Specification
import spock.lang.Subject

class JavaSourceToImportDeclarationsCreatorSpec extends Specification {

    @Subject
    JavaSourceToImportDeclarationsCreator creator = new JavaSourceToImportDeclarationsCreator()

    def 'returns import declarations for Java source'() {
    given:
        JavaSource javaSource = Mock()
        ImportDeclaration import1 = Mock()
        ImportDeclaration import2 = Mock()
        ImportDeclaration import3 = Mock()
        javaSource.importDeclarations >> [import1, import2, import3]
    expect:
        creator.create(javaSource) as List == [ import1, import2, import3 ]
    }
}
