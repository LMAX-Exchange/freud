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
