package org.freud.analysed.css.jdom

import org.freud.analysed.css.rule.CssRule
import spock.lang.Specification
import spock.lang.Subject

class CssRulesJdomFromFileCreatorSpec extends Specification {

    @Subject
    CssRulesJdomFromFileCreator creator = new CssRulesJdomFromFileCreator()

    def 'creates CssRules from a File'() {
    given:
        File file = new File(ClassLoader.getSystemResource('CssRulesJdomFromFileCreatorSpec/example.css').file)

    when:
        List<CssRule> rules = creator.create(file) as List
    then:
        rules.size() == 11
        // TODO
    }
}
