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



package org.freud.core.iterator

import spock.lang.Specification
import spock.lang.Subject

class ClassNamesSpec extends Specification {

    @Subject
    ClassNames classNamesIterator
    File root = ClassLoader.getSystemResource('ClassNamesSpec/src').file as File


    def 'iterates over class names'() {
    given:
        classNamesIterator = new ClassNames([root], false, null)
    when:
        List classNames = classNamesIterator.collect { it }
    then:
        classNames == ['Test']
    }

    def 'iterates over class names recursively'() {
    given:
        classNamesIterator = new ClassNames([root], true, null)
    when:
        List classNames = classNamesIterator.collect { it }
    then:
        classNames.sort() == [
                'Test',
                'a.TestA',
                'a.TestAA',
                'b.TestB',
                'b.c.TestC',
        ]
    }

    def 'iterates over class names using filter'() {
    given:
        classNamesIterator = new ClassNames([root], true, { dir, name -> name.endsWith('.myjava') } as FilenameFilter)
    when:
        List classNames = classNamesIterator.collect { it }
    then:
        classNames.sort() == [
                'Test',
                'a.TestA',
                'b.TestB',
                'b.c.TestC',
        ]
    }

    def 'iterates over class names in several roots'() {
    given:
        classNamesIterator = new ClassNames([new File(root, 'a'), new File(root, 'b')], true, null)
    when:
        List classNames = classNamesIterator.collect { it }
    then:
        classNames.sort() == [
                'TestA',
                'TestAA',
                'TestB',
                'c.TestC',
        ]
    }
}
