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

import org.jdom.Element
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Subject

import static org.freud.core.parser.JdomTreeAdaptor.COL_NUMBER_ATTR
import static org.freud.core.parser.JdomTreeAdaptor.LINE_NUMBER_ATTR
import static org.jdom.Namespace.NO_NAMESPACE

class JdomTreePositionComparatorSpec extends Specification {

    @Subject
    JdomTreePositionComparator comparator = JdomTreePositionComparator.instance
    @Shared
    Element line17Col19 = new Element('name', NO_NAMESPACE)
    @Shared
    Element line17Col23 = new Element('name', NO_NAMESPACE)
    @Shared
    Element line29Col13 = new Element('name', NO_NAMESPACE)

    def setup() {
        line17Col19.setAttribute(LINE_NUMBER_ATTR, '17')
        line17Col19.setAttribute(COL_NUMBER_ATTR, '19')
        line17Col23.setAttribute(LINE_NUMBER_ATTR, '17')
        line17Col23.setAttribute(COL_NUMBER_ATTR, '23')
        line29Col13.setAttribute(LINE_NUMBER_ATTR, '29')
        line29Col13.setAttribute(COL_NUMBER_ATTR, '13')
    }

    def 'compares elements by their position in the file'() {
    expect:
        comparator.compare(element1, element2) == expected
    where:
        element1    | element2    | expected
        line17Col19 | line17Col19 | 0
        line17Col19 | line29Col13 | -1
        line29Col13 | line17Col19 | 1
        line17Col19 | line17Col23 | -1
        line17Col23 | line17Col19 | 1
    }
}
