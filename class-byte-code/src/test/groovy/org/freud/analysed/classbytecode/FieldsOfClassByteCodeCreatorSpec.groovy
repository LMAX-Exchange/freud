package org.freud.analysed.classbytecode

import spock.lang.Specification
import spock.lang.Subject

class FieldsOfClassByteCodeCreatorSpec extends Specification {

    @Subject
    FieldsOfClassByteCodeCreator creator = new FieldsOfClassByteCodeCreator()

    def 'returns all fields from every ClassByteCode'() {
        given:
            ClassByteCode source = Mock()
            ClassByteCodeField field1 = Mock()
            ClassByteCodeField field2 = Mock()
            source.fieldList >> [ field1, field2 ]
        expect:
            creator.create(source) == [ field1, field2 ]

    }
}
