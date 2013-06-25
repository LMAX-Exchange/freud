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

class ClassFromFileCreatorSpec extends Specification {

    private static final File ROOT_DIR = new File('/tmp')
    private static final Class<String> CLASS_OBJECT = String

    ClassLoader classLoader = Mock()
    ClassFromNameCreator fromNameCreator = new ClassFromNameCreator(classLoader)
    @Subject
    ClassFromFileCreator creator = new ClassFromFileCreator(fromNameCreator, ROOT_DIR)

    def setup() {
        classLoader.loadClass('org.freud.classname') >> CLASS_OBJECT
    }

    def 'creates class from class file using name'() {
    when:
        Class classObject = creator.create(new File(ROOT_DIR, 'org/freud/classname.class'))
    then:
        classObject == CLASS_OBJECT
    }

    def 'creates class from source file'() {
    when:
        Class classObject = creator.create(new File(ROOT_DIR, 'org/freud/classname.java'))
    then:
        classObject == CLASS_OBJECT
    }

    def 'creates class from source file even on inferior operating systems'() {
    when:
        Class classObject = creator.create(new File(ROOT_DIR, 'org\\freud\\classname.java'))
    then:
        classObject == CLASS_OBJECT
    }

}
