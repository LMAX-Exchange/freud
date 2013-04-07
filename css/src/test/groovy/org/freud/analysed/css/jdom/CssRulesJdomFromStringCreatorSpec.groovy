package org.freud.analysed.css.jdom

import org.freud.analysed.css.rule.CssRule
import org.freud.analysed.css.rule.selector.CssSelector
import spock.lang.Specification
import spock.lang.Subject

class CssRulesJdomFromStringCreatorSpec extends Specification {

    @Subject
    CssRulesJdomFromStringCreator creator = new CssRulesJdomFromStringCreator()

    def 'creates CssRules from a String'() {
    when:
        List<CssRule> rules = creator.create(
                '''
                tag .class {
                 display: none;
                }
                #id .class {
                 display: none;
                }
                #id {
                 display: none;
                } ''') as List
    then:
        rules[0].cssSelectorList[0].type == CssSelector.Type.TAG
        rules[0].cssSelectorList[0].selectorString == 'tag'
        rules[0].cssSelectorList[1].type == CssSelector.Type.CLASS
        rules[0].cssSelectorList[1].selectorString == 'class'
        rules[0].cssSelectorList.size() == 2

        rules[1].cssSelectorList[0].type == CssSelector.Type.ID
        rules[1].cssSelectorList[0].selectorString == 'id'
        rules[1].cssSelectorList[1].type == CssSelector.Type.CLASS
        rules[1].cssSelectorList[1].selectorString == 'class'
        rules[1].cssSelectorList.size() == 2

        rules[2].cssSelectorList[0].type == CssSelector.Type.ID
        rules[2].cssSelectorList[0].selectorString == 'id'
        rules[2].cssSelectorList.size() == 1
        rules.size() == 3
    }
}
