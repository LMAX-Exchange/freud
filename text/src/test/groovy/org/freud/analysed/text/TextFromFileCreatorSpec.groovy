package org.freud.analysed.text

import spock.lang.Specification
import spock.lang.Subject

class TextFromFileCreatorSpec extends Specification {

    @Subject
    TextFromFileCreator creator = new TextFromFileCreator()

    def 'creates a Text object from File'() {
    given:
        File file = new File(ClassLoader.getSystemResource('TextFromFileCreatorSpec/file').file)
    expect:
        creator.create(file).textAsStream.readLine() == 'text in file'
        creator.create(file).textAsString == null
    }
}
