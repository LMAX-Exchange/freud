package examples.org.freud.matchers;

import org.freud.analysed.css.rule.selector.CssSelector;
import org.freud.java.matcher.FreudExtendedMatcher;
import org.freud.java.matcher.RegexMatcherAdapter;
import org.freud.java.matcher.StringMatcherBuilder;
import org.hamcrest.Description;

public final class CssSelectorMatchers {
    private CssSelectorMatchers() {
        // static utility
    }

    public static StringMatcherBuilder<CssSelector> selector() {
        return new StringMatcherBuilder<CssSelector>(new RegexMatcherAdapter<CssSelector>() {
            @Override
            public String getStringToMatch(final CssSelector toBeAnalysed) {
                return toBeAnalysed.getSelectorString();
            }

            @Override
            public String matcherDisplayName() {
                return "CssSelector";
            }
        });
    }

    public static FreudExtendedMatcher<CssSelector> classSelector() {
        return selectorTypeMatcher(CssSelector.Type.CLASS);
    }

    public static FreudExtendedMatcher<CssSelector> tagSelector() {
        return selectorTypeMatcher(CssSelector.Type.TAG);
    }

    public static FreudExtendedMatcher<CssSelector> idSelector() {
        return selectorTypeMatcher(CssSelector.Type.ID);
    }

    private static FreudExtendedMatcher<CssSelector> selectorTypeMatcher(final CssSelector.Type type) {
        return new FreudExtendedMatcher<CssSelector>() {
            @Override
            protected boolean matchesSafely(final CssSelector item) {
                return item.getType() == type;
            }

            @Override
            public void describeTo(final Description description) {
                description.appendText("CssSelector type [" + type + "]");
            }
        };
    }

}
// TODO hierarchy between selector --> declaration
//     public static FreudExtendedMatcher<CssSelector> hasDeclaration(String regex);
