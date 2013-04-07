package org.freud.analysed.css.jdom

import org.freud.analysed.css.rule.CssRule
import org.freud.analysed.css.rule.declaration.CssDeclaration
import org.freud.analysed.css.rule.selector.CssSelector

class CssTestUtil {

    static Closure<Boolean> matchClosuresToList = {
        List<Closure<Boolean>> closures, List list ->
        int index = 0
        return closures.size() == list.size() && closures.every { Closure<Boolean> closure ->
            closure.call(list[index++])
        }
    }

    static Closure<Boolean> cssRule(List<Closure<Boolean>> selectorsConditions) {
        return { CssRule rule ->
            println rule
            matchClosuresToList.call(selectorsConditions, rule.cssSelectorList)
        }
    }

    static Closure<Boolean> cssSelector(CssSelector.Type type, String selectorString,
                                        List<Closure<Boolean>> declarationsConditions) {
        return cssSelector(type, null, selectorString, declarationsConditions)
    }

    static Closure<Boolean> cssSelector(CssSelector.Type type,
                                        CssSelector.Combinator combinator,
                                        String selectorString,
                                        List<Closure<Boolean>> declarationsConditions) {
        return { CssSelector selector ->
            selector.type == type && selector.selectorString == selectorString &&
                    selector.combinator == combinator &&
                    matchClosuresToList.call(declarationsConditions, selector.cssRuleForSelector.cssDeclarationList)
        }
    }

    static Closure<Boolean> cssDeclaration(String key, String value) {
        return { CssDeclaration declaration ->
            declaration.key == key && declaration.value == value
        }
    }
}
