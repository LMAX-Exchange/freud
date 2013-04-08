package org.freud

import org.freud.analysed.css.rule.CssRule
import org.freud.analysed.css.rule.selector.CssSelector
import org.spockframework.runtime.ConditionNotSatisfiedError
import spock.lang.FailsWith
import spock.lang.Specification

import static org.freud.analysed.css.CssDsl.cssDeclarationsWithin
import static org.freud.analysed.css.CssDsl.cssRulesOf
import static org.freud.analysed.css.CssDsl.cssSelectorsWithin
import static org.freud.analysed.css.rule.selector.CssSelector.Combinator.DESCENDANT
import static org.freud.analysed.css.rule.selector.CssSelector.Type.CLASS
import static org.freud.analysed.css.rule.selector.CssSelector.Type.ID
import static org.freud.analysed.css.rule.selector.CssSelector.Type.TAG
import static org.freud.groovy.Freud.analyse
import static org.freud.groovy.Freud.forEach

class CssExamplesSpock extends Specification {

    static URL root = ClassLoader.getSystemResource('CssExamples/')

    def 'classOrIdCssSelectorsNameMustNotContainUpperCaseCharacters'() {
    expect:
        analyse(analysed) { (it.type != CLASS && it.type != ID) || !it.selectorString.matches(".*[A-Z].*") }
    where:
        analysed << forEach(cssSelectorsWithin(cssRulesOf([new URL(root, 'file.css').text], String)))
    }

    @FailsWith(ConditionNotSatisfiedError)
    def 'classOrIdCssSelectorsNameMustNotContainUpperCaseCharacters - failing test'() {
    expect:
        analyse(analysed) { (it.type != CLASS && it.type != ID) || !it.selectorString.matches(".*[A-Z].*") }
    where:
        analysed << forEach(cssSelectorsWithin(cssRulesOf([new URL(root, 'classWithUpperCase.css').text], String)))
    }


    def 'cssDisplayDeclarationIsAlwaysNone'() {
    expect:
        analyse(analysed) { it.key != 'display' || it.value == 'none' }
    where:
        analysed << forEach(cssDeclarationsWithin(cssRulesOf([new URL(root, 'displayNone.css').text], String)))
    }

    @FailsWith(ConditionNotSatisfiedError)
    def 'cssDisplayDeclarationIsAlwaysNone - failing test'() {
    expect:
        analyse(analysed) { it.key != 'display' || it.value == 'none' }
    where:
        analysed << forEach(cssDeclarationsWithin(cssRulesOf([new URL(root, 'displayBlock.css').text], String)))
    }

    /**
     * see https://developer.mozilla.org/en/Writing_Efficient_CSS
     */
    def 'doNotQualifyIdRuleWithTagOrClassName'() {
    expect:
        analyse(analysed) { CssSelector selector ->
            selector.type != ID || selector.combinator != DESCENDANT
        }
    where:
        analysed << forEach(cssSelectorsWithin(cssRulesOf([new URL(root, 'file.css').text], String)))
    }

    @FailsWith(ConditionNotSatisfiedError) // slightly different iteration because test requires to fail on EVERY analysed object
    def 'doNotQualifyIdRuleWithTagOrClassName - failing test'() {
    expect:
        analyse(analysed) { CssRule rule ->
            rule.cssSelectors.every { CssSelector selector ->
                selector.type != ID || selector.combinator != DESCENDANT
            }
        }
    where:
        analysed << forEach(cssRulesOf([new URL(root, 'doNotQualifyIdRuleWithTagOrClassName.css').text], String))
    }

    /**
     * see https://developer.mozilla.org/en/Writing_Efficient_CSS
     */
    def 'doNotQualifyClassRuleWithTagName'() {
    expect:
        analyse(analysed) { CssRule rule ->
            int firstClassIndex = rule.cssSelectors.findIndexOf { it.type == CLASS }

            int firstTagIndex = rule.cssSelectors.findIndexOf { it.type == TAG }

            return firstTagIndex == -1 || firstClassIndex == -1 || firstClassIndex < firstTagIndex
        }
    where:
        analysed << forEach(cssRulesOf([new URL(root, 'file.css').text], String))
    }

    @FailsWith(ConditionNotSatisfiedError)
    def 'doNotQualifyClassRuleWithTagName - failing test'() {
    expect:
        analyse(analysed) { CssRule rule ->

            int firstClassIndex = rule.cssSelectors.findIndexOf { it.type == CLASS }

            int firstTagIndex = rule.cssSelectors.findIndexOf { it.type == TAG }

            return firstTagIndex == -1 || firstClassIndex == -1 || firstClassIndex < firstTagIndex
        }
    where:
        analysed << forEach(cssRulesOf([new URL(root, 'doNotQualifyClassRuleWithTagName.css').text], String))
    }

    /**
     * see http://css-tricks.com/efficiently-rendering-css/
     */
    def 'descendantSelectorsAreTheWorst'() {
    expect:
        analyse(analysed) { CssRule rule ->
            rule.cssSelectors.size() <= 1
        }
    where:
        analysed << forEach(cssRulesOf([new URL(root, 'descendantSelectorsAreTheWorst.css').text], String))
    }

    @FailsWith(ConditionNotSatisfiedError)
    def 'descendantSelectorsAreTheWorst failing test'() {
    expect:
        analyse(analysed) { CssRule rule ->
            rule.cssSelectors.size() <= 1
        }
    where:
        analysed << forEach(cssRulesOf([new URL(root, 'descendantSelectorsAreTheWorst_failing.css').text], String))
    }
}
