package org.freud.analysed.css.jdom

import spock.lang.Specification
import spock.lang.Subject

import static org.freud.analysed.css.CssParsingFixture.exampleCssUrlParsedBy

class CssRulesJdomFromUrlCreatorSpec extends Specification {

    @Subject
    CssRulesJdomFromUrlCreator creator = new CssRulesJdomFromUrlCreator()

    def 'creates CssRules from a Url'() {
    expect:
        exampleCssUrlParsedBy { URL url -> creator.create(url) }
    }
}
