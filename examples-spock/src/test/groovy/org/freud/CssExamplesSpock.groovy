package org.freud

import org.spockframework.runtime.ConditionNotSatisfiedError
import spock.lang.FailsWith
import spock.lang.Specification

import static org.freud.analysed.css.CssDsl.cssDeclarationsWithin
import static org.freud.analysed.css.CssDsl.cssRulesOf
import static org.freud.analysed.css.CssDsl.cssSelectorsWithin
import static org.freud.analysed.css.rule.selector.CssSelector.Type.CLASS
import static org.freud.analysed.css.rule.selector.CssSelector.Type.ID
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
    def 'doNotQualifyIdRuleWithTagName'() {
//        return Freud.iterateOver(CssRule.class).
//                assertThat(no(containsSelector(CssSelector.Type.TAG).and(
//                        lastIndexOfSelector(CssSelector.Type.TAG).lessThan(lastIndexOfSelector(CssSelector.Type.ID))))).within(iterator);
    }

    /**
     * see https://developer.mozilla.org/en/Writing_Efficient_CSS
     */
    def 'doNotQualifyIdRuleWithClassName'() {
//        return Freud.iterateOver(CssRule.class).
//                assertThat(no(containsSelector(CssSelector.Type.CLASS).and(
//                        lastIndexOfSelector(CssSelector.Type.CLASS).lessThan(lastIndexOfSelector(CssSelector.Type.ID))))).within(iterator);
    }

    /**
     * see http://css-tricks.com/efficiently-rendering-css/
     */
    def 'descendantSelectorsAreTheWorst'() {
//        return Freud.iterateOver(CssRule.class).
//                assertThat(selectors(CssSelector.Type.TAG).lessThanOrEqualTo(1)).within(iterator);
    }

}
