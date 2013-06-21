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
