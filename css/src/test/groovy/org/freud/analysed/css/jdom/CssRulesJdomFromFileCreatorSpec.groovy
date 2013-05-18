package org.freud.analysed.css.jdom

import spock.lang.Specification
import spock.lang.Subject

import static org.freud.analysed.css.CssParsingFixture.exampleCssFileParsedBy

class CssRulesJdomFromFileCreatorSpec extends Specification {

    @Subject
    CssRulesJdomFromFileCreator creator = new CssRulesJdomFromFileCreator()

    def 'creates CssRules from a File'() {
    expect:
        exampleCssFileParsedBy { File file -> creator.create(file) }
    }
}
