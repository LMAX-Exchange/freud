package org.freud

class CssExamplesSpock {

    def 'classOrIdCssSelectorsNameMustNotContainUpperCaseCharacters'() {
//        return Freud.iterateOver(CssSelector.class).
//                forEach(classSelector().or(idSelector())).
//                assertThat(no(selector().contains("[A-Z]"))).within(iterator);
    }

    def 'cssDisplayDeclarationIsAlwaysNone'() {
//        return Freud.iterateOver(CssDeclaration.class).
//                forEach(declarationKey().matches("display")).
//                assertThat(declarationValue().matches("none")).within(iterator);
    }

    /**
     * @see https://developer.mozilla.org/en/Writing_Efficient_CSS
     */
    def 'doNotQualifyIdRuleWithTagName'() {
//        return Freud.iterateOver(CssRule.class).
//                assertThat(no(containsSelector(CssSelector.Type.TAG).and(
//                        lastIndexOfSelector(CssSelector.Type.TAG).lessThan(lastIndexOfSelector(CssSelector.Type.ID))))).within(iterator);
    }

    /**
     * @see https://developer.mozilla.org/en/Writing_Efficient_CSS
     */
    def 'doNotQualifyIdRuleWithClassName'() {
//        return Freud.iterateOver(CssRule.class).
//                assertThat(no(containsSelector(CssSelector.Type.CLASS).and(
//                        lastIndexOfSelector(CssSelector.Type.CLASS).lessThan(lastIndexOfSelector(CssSelector.Type.ID))))).within(iterator);
    }

    /**
     * @see http://css-tricks.com/efficiently-rendering-css/
     */
    def 'descendantSelectorsAreTheWorst'() {
//        return Freud.iterateOver(CssRule.class).
//                assertThat(selectors(CssSelector.Type.TAG).lessThanOrEqualTo(1)).within(iterator);
    }

}
