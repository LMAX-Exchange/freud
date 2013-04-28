package org.freud.analysed.css.jdom

import org.freud.analysed.css.CssParsingFixture
import spock.lang.Specification

class CssJdomParserSpec extends Specification {


    def 'parses a file into CssRules'() {
    expect:
        CssParsingFixture.exampleCssfileParsedBy { File file -> CssJdomParser.parseCssRules(new FileReader(file)) }
    }
}