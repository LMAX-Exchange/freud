package org.freud.analysed.classbytecode.parser.asm

import org.freud.analysed.classbytecode.ClassByteCode
import spock.lang.Specification
import spock.lang.Subject

class AsmClassByteCodeFromNameCreatorSpec extends Specification {

    @Subject
    AsmClassByteCodeFromNameCreator creator = new AsmClassByteCodeFromNameCreator()

    def 'creates ClassByteCode from class name'() {
    when:
        ClassByteCode classByteCode = creator.create(this.getClass().name)
    then:
        classByteCode.name == this.getClass().name
        classByteCode.fieldList[0].name == 'creator'
        classByteCode.methodList.find { it.name == 'getCreator' }
        classByteCode.methodList.find { it.name == 'setCreator' }
    }
}
