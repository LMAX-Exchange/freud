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



package org.freud.core.parser

import org.antlr.runtime.Token
import spock.lang.Specification
import spock.lang.Subject

class JdomTreeAdaptorSpec extends Specification {

    @Subject
    JdomTreeAdaptor adaptor
    Token payload = Mock()
    TokenType type0 = Mock()
    TokenType type1 = Mock()

    def setup() {
        adaptor = new JdomTreeAdaptor('root', [type0, type1] as TokenType[])
        type0.name >> 'name'
        type1.name >> 'other_name'
        payload.line >> 17
        payload.charPositionInLine >> 19
        payload.type >> 0
        payload.text >> 'text'

    }

    def 'appends line and column number as attributes'() {
    when:
        JdomTreeAdaptor.JdomTree tree = adaptor.create(payload) as JdomTreeAdaptor.JdomTree
    then:
        tree.element.getAttribute(JdomTreeAdaptor.LINE_NUMBER_ATTR).value == '17'
        tree.element.getAttribute(JdomTreeAdaptor.COL_NUMBER_ATTR).value == '19'
    }

    def 'sets name and text to element'() {
    when:
        JdomTreeAdaptor.JdomTree tree = adaptor.create(payload) as JdomTreeAdaptor.JdomTree
    then:
        tree.element.name == 'name'
        tree.element.text == 'text'
    }
}
