package org.freud.analysed.classbytecode.parser.asm

import org.freud.analysed.classbytecode.ClassByteCode
import spock.lang.Specification
import spock.lang.Subject

import static java.io.File.separatorChar

class AsmClassByteCodeFromFileCreatorSpec extends Specification {

    @Subject
    AsmClassByteCodeFromFileCreator creator = new AsmClassByteCodeFromFileCreator()

    def 'creates ClassByteCode from file'() {
    given:
        String classPath = this.getClass().name.replace((char) '.', separatorChar) + '.class'
    when:
        ClassByteCode classByteCode = creator.create(new File(ClassLoader.getSystemResource(classPath).file))
    then:
        classByteCode.name == this.getClass().name
        classByteCode.fieldList[0].name == 'creator'
        classByteCode.methodList.find { it.name == 'getCreator' }
        classByteCode.methodList.find { it.name == 'setCreator' }
    }
}
