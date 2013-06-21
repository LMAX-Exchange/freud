package examples.org.freud;

import org.freud.analysed.css.rule.CssRule;
import org.freud.analysed.css.rule.declaration.CssDeclaration;
import org.freud.analysed.css.rule.selector.CssSelector;
import org.freud.java.Freud;
import org.junit.Test;

import java.net.URL;

import static examples.org.freud.matchers.CssDeclarationMatchers.declarationKey;
import static examples.org.freud.matchers.CssDeclarationMatchers.declarationValue;
import static examples.org.freud.matchers.CssRuleMatchers.containsSelector;
import static examples.org.freud.matchers.CssRuleMatchers.lastIndexOfSelector;
import static examples.org.freud.matchers.CssRuleMatchers.selectors;
import static examples.org.freud.matchers.CssSelectorMatchers.classSelector;
import static examples.org.freud.matchers.CssSelectorMatchers.idSelector;
import static examples.org.freud.matchers.CssSelectorMatchers.selector;
import static java.util.Arrays.asList;
import static org.freud.analysed.css.CssDsl.cssDeclarationsWithin;
import static org.freud.analysed.css.CssDsl.cssRulesOf;
import static org.freud.analysed.css.CssDsl.cssSelectorsWithin;
import static org.freud.analysed.css.rule.selector.CssSelector.Type.CLASS;
import static org.freud.analysed.css.rule.selector.CssSelector.Type.ID;
import static org.freud.analysed.css.rule.selector.CssSelector.Type.TAG;
import static org.freud.java.matcher.FreudDsl.no;

@SuppressWarnings("JavadocReference")
public final class CssExamplesJunit {

    static URL root = ClassLoader.getSystemResource("CssExamples/");
    private AnalysisListenerStub listener = new AnalysisListenerStub();

    /**
     * @see https://developer.mozilla.org/en/Writing_Efficient_CSS
     */
    @Test
    public void doNotQualifyIdRuleWithTagName() throws Exception {
        Freud.iterateOver(CssRule.class).
                assertThat(no(containsSelector(TAG).and(
                        lastIndexOfSelector(TAG).lessThan(lastIndexOfSelector(ID))))).
        in(cssRulesOf(asList(new URL(root, "file.css")))).analyse(listener);
    }

    /**
     * @see https://developer.mozilla.org/en/Writing_Efficient_CSS
     */
    @Test
    public void doNotQualifyIdRuleWithClassName() throws Exception {
        Freud.iterateOver(CssRule.class).
                assertThat(no(containsSelector(CLASS).and(
                        lastIndexOfSelector(CLASS).lessThan(lastIndexOfSelector(ID))))).
        in(cssRulesOf(asList(new URL(root, "file.css")))).analyse(listener);

        listener.assertNotFailed();
    }

    /**
     * @see http://css-tricks.com/efficiently-rendering-css/
     */
    @Test
    public void descendantSelectorsAreTheWorst() throws Exception {
        Freud.iterateOver(CssRule.class).
                assertThat(selectors(TAG).lessThanOrEqualTo(1)).in(cssRulesOf(asList(new URL(root, "file.css")))).
        analyse(listener);

        listener.assertPassed(11);
        listener.assertFailed(2);
    }

    @Test
    public void classOrIdCssSelectorsNameMustNotContainUpperCaseCharacters() throws Exception {
        Freud.iterateOver(CssSelector.class).
                forEach(classSelector().or(idSelector())).
                assertThat(no(selector().contains("[A-Z]"))).
        in(cssSelectorsWithin(cssRulesOf(asList(new URL(root, "file.css"))))).analyse(listener);
        listener.assertNotFailed();
    }

    @Test
    public void cssDisplayDeclarationIsAlwaysNone() throws Exception {
        Freud.iterateOver(CssDeclaration.class).
                forEach(declarationKey().matches("display")).
                assertThat(declarationValue().matches("none")).
        in(cssDeclarationsWithin(cssRulesOf(asList(new URL(root, "file.css"))))).analyse(listener);

        listener.assertPassed(7);
        listener.assertFailed(4);
    }
}
