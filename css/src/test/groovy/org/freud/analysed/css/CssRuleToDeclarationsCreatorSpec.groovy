package org.freud.analysed.css

import org.freud.analysed.css.rule.CssRule
import spock.lang.Specification
import spock.lang.Subject

class CssRuleToDeclarationsCreatorSpec extends Specification {

    @Subject
    CssRuleToDeclarationsCreator creator = new CssRuleToDeclarationsCreator()

    def 'returns declarations for rule'() {
    given:
        List expected = []
        CssRule rule = Mock()
        rule.cssDeclarationList >> expected
    expect:
        creator.create(rule).is(expected)

    }
}
