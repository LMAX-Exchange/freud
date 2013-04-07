package org.freud.analysed.css

import org.freud.analysed.css.rule.CssRule
import spock.lang.Specification
import spock.lang.Subject

class CssRuleToSelectorsCreatorSpec extends Specification {

    @Subject
    CssRuleToSelectorsCreator creator = new CssRuleToSelectorsCreator()

    def 'returns selectors for rule'() {
    given:
        List expected = []
        CssRule rule = Mock()
        rule.cssSelectorList >> expected
    expect:
        creator.create(rule).is(expected)

    }
}
