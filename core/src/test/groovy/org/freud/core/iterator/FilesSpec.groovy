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

class FilesSpec extends Specification {

    @Subject
    Files fileIterator
    File root = ClassLoader.getSystemResource('FilesSpec/src').file as File


    def 'iterates over files'() {
    given:
        fileIterator = new Files(root, false, null)
    when:
        List files = fileIterator.collect { it }
    then:
        files == [
                new File(root, 'test'),
        ]
    }

    def 'iterates over files recursively'() {
    given:
        fileIterator = new Files(root, true, null)
    when:
        List files = fileIterator.collect { it }
    then:
        files.sort() == [
                new File(root, 'a/a.other'),
                new File(root, 'a/atest'),
                new File(root, 'b/btest'),
                new File(root, 'b/c/ctest'),
                new File(root, 'test'),
        ]
    }

    def 'iterates over files using filter'() {
    given:
        fileIterator = new Files([root], true, { dir, name -> name.endsWith('test') } as FilenameFilter)
    when:
        List files = fileIterator.collect { it }
    then:
        files.sort() == [
                new File(root, 'a/atest'),
                new File(root, 'b/btest'),
                new File(root, 'b/c/ctest'),
                new File(root, 'test'),
        ]
    }

    def 'iterates over files in several roots'() {
    given:
        fileIterator = new Files([new File(root, 'a'), new File(root, 'b')], true, { dir, name -> name.endsWith('test') } as FilenameFilter)
    when:
        List files = fileIterator.collect { it }
    then:
        files.sort() == [
                new File(root, 'a/atest'),
                new File(root, 'b/btest'),
                new File(root, 'b/c/ctest'),
        ]
    }
}
