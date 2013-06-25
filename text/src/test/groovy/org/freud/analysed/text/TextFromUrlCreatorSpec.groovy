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



package org.freud.analysed.text

import spock.lang.Specification
import spock.lang.Subject

class TextFromUrlCreatorSpec extends Specification {

    @Subject
    TextFromUrlCreator creator = new TextFromUrlCreator()

    def 'creates a Text object from URL'() {
    given:
        URL url = ClassLoader.getSystemResource('TextFromFileCreatorSpec/file')
    expect:
        creator.create(url).textAsStream.readLine() == 'text in file'
        creator.create(url).textAsString == null
    }
}
