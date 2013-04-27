package org.freud.analysed.css.jdom

import spock.lang.Specification

class CssJdomParserSpec extends Specification {


    def 'creates CssRules from a File'() {
    expect:
        CssParsingFixture.exampleCssfileParsedBy { File file -> CssJdomParser.parseCssRules(new FileReader(file)) }
    }
}