package org.freud.analysed.text

import spock.lang.Specification
import spock.lang.Subject

class TextFromStringCreatorSpec extends Specification {

    @Subject
    TextFromStringCreator creator = new TextFromStringCreator()

    def 'creates a Text object from string'() {
        expect:
            creator.create('source').textAsString == 'source'
            creator.create('source').textAsStream.readLine() == 'source'
    }
}
