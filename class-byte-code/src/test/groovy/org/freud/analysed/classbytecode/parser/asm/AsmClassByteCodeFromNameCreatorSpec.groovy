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
