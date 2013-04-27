package org.freud.analysed.css.jdom

import spock.lang.Specification
import spock.lang.Subject

class CssRulesJdomFromFileCreatorSpec extends Specification {

    @Subject
    CssRulesJdomFromFileCreator creator = new CssRulesJdomFromFileCreator()

    def 'creates CssRules from a File'() {
    expect:
        CssParsingFixture.exampleCssfileParsedBy { File file -> creator.create(file) }
    }
}
