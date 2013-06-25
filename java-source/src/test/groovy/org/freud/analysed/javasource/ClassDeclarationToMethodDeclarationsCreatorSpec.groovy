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



package org.freud.analysed.javasource

import spock.lang.Specification
import spock.lang.Subject

class ClassDeclarationToMethodDeclarationsCreatorSpec extends Specification {

    @Subject
    ClassDeclarationToMethodDeclarationsCreator creator = new ClassDeclarationToMethodDeclarationsCreator()

    def 'returns method declarations from class declaration'() {
    given:
        ClassDeclaration classDeclaration = Mock()
        MethodDeclaration m1 = Mock()
        MethodDeclaration m2 = Mock()
        MethodDeclaration m3 = Mock()
        classDeclaration.methodDeclarationListByNameMap >> [method1: [m1, m2], method2: [m3]]
    expect:
        creator.create(classDeclaration) as List == [m1, m2, m3]
    }
}
