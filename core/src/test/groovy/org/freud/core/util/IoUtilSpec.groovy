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



package org.freud.core.util

import spock.lang.Specification

class IoUtilSpec extends Specification {

    def 'parses a stream into lines'() {
    given:
        Reader reader = new InputStreamReader(new ByteArrayInputStream('line1 \nline2\t\nline3\n\n'.bytes))
    expect:
        IoUtil.readLines(reader) == ['line1 ', 'line2\t', 'line3', '']
    }

    def 'fully reads a stream'() {
    given:
        Reader reader = new InputStreamReader(new ByteArrayInputStream('line1 \nline2\t\nline3\n\nline4'.bytes))
    when:
        String text = IoUtil.readFully(reader);
    then:
        text == 'line1 \nline2\t\nline3\n\nline4'
    }

}
