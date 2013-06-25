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


class ClassFromNameCreatorSpec extends Specification {

    private static final Class<String> CLASS_OBJECT = String
    ClassLoader classLoader = Mock()
    @Subject
    ClassFromNameCreator creator = new ClassFromNameCreator(classLoader)

    def 'creator uses ClassLoader to create class'() {
    given:
        classLoader.loadClass('org.classname') >> CLASS_OBJECT
    when:
        Class classObject = creator.create('org.classname')
    then:
        classObject == CLASS_OBJECT
    }

    def 'creator creates class for inner class'() {
    given:
        creator = new ClassFromNameCreator()
    when:
        Class classObject = creator.create("${ClassFromNameCreatorSpec.class.name}\$InnerClass")
    then:
        classObject == InnerClass.class
    }

    private static class InnerClass {
        private void someMethod() {}
    }
}
