package org.freud.analysed.text

import spock.lang.Specification
import spock.lang.Subject

class TextToLinesCreatorSpec extends Specification {

    @Subject
    TextToLinesCreator creator = new TextToLinesCreator()

    def 'breaks text into lines'() {
        given:
            Text text = new Text('line0\nline1\nline2\n\n')
        when:
            Iterable<TextLine> textLines = creator.create(text)
        then:
            textLines.collect { it.line } == ['line0', 'line1', 'line2', '']
            textLines.collect { it.lineNumber } == [0, 1, 2, 3]
    }

    def 'breaks text that started as a stream into lines'() {
    given:
        Text text = new Text(new StringReader('line0\nline1\nline2\n\n'))
    when:
        Iterable<TextLine> textLines = creator.create(text)
    then:
        textLines.collectEntries { [it.lineNumber, it.line] } == [ 0: 'line0', 1: 'line1', 2: 'line2', 3: '']
    }

}
