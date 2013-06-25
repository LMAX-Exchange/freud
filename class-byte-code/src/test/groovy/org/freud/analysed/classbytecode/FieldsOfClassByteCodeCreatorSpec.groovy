/*
 * Copyright 2013 LMAX Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */



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
        source.fieldList >> [field1, field2]
    expect:
        creator.create(source) == [field1, field2]

    }
}
