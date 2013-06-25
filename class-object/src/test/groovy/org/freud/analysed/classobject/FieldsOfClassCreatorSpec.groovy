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



package org.freud.analysed.classobject

import spock.lang.Specification
import spock.lang.Subject

import static org.freud.analysed.classobject.FieldsOfClassCreator.Type.DECLARED
import static org.freud.analysed.classobject.FieldsOfClassCreator.Type.PUBLIC

class FieldsOfClassCreatorSpec extends Specification {

    @Subject
    FieldsOfClassCreator creator = new FieldsOfClassCreator()

    def 'generates fields for class'() {
    expect:
        creator.create(Integer) as Set == (Integer.fields + Integer.declaredFields) as Set
    }

    def 'generates public fields for class'() {
    given:
        creator = new FieldsOfClassCreator(PUBLIC)
    expect:
        creator.create(Integer) as Set == Integer.fields as Set
    }

    def 'generates declared fields for class'() {
    given:
        creator = new FieldsOfClassCreator(DECLARED)
    expect:
        creator.create(Integer) as Set == Integer.declaredFields as Set
    }

    def 'generates public fields for inner class'() {
    given:
        creator = new FieldsOfClassCreator(PUBLIC)
    expect:
        creator.create(InnerClass) as List == InnerClass.fields as List
    }

    private static class InnerClass {

        private int i, j, k;
    }
}