package org.freud.analysed.javasource

import spock.lang.Specification
import spock.lang.Subject

class JavaSourceToPackageDeclarationCreatorSpec extends Specification {

    @Subject
    JavaSourceToPackageDeclarationCreator creator = new JavaSourceToPackageDeclarationCreator()

    def 'returns class declaration for Java source'() {
    given:
        JavaSource javaSource = Mock()
        PackageDeclaration packageDeclaration = Mock()
        javaSource.packageDeclaration >> packageDeclaration
    expect:
        creator.create(javaSource)  == packageDeclaration
    }
}
