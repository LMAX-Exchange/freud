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
