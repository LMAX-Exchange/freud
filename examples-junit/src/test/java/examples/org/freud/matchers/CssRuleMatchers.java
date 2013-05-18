package examples.org.freud.matchers;

import org.freud.analysed.css.rule.CssRule;
import org.freud.analysed.css.rule.selector.CssSelector;
import org.freud.java.matcher.FreudExtendedMatcher;
import org.freud.java.matcher.IntOperatorMatcherAdapter;
import org.freud.java.matcher.IntOperatorMatcherBuilder;

import java.util.List;


public final class CssRuleMatchers {
    private CssRuleMatchers() {
        // static utility
    }

    public static FreudExtendedMatcher<CssRule> containsSelector(final CssSelector.Type selectorType) {
        return selectors(selectorType).greaterThan(0);
    }

    public static IntOperatorMatcherBuilder<CssRule> selectors(final CssSelector.Type selectorType) {
        return new IntOperatorMatcherBuilder<CssRule>(new IntOperatorMatcherAdapter<CssRule>() {
            @Override
            public int valueOf(final CssRule matched) {
                int counter = 0;
                for (CssSelector selector : matched.getCssSelectors()) {
                    if (selector.getType() == selectorType) {
                        counter++;
                    }
                }
                return counter;
            }

            @Override
            public String matcherDisplayName() {
                return "CssSelectorsCount(" + selectorType.name() + ")";
            }
        });
    }

    public static IntOperatorMatcherBuilder<CssRule> selectors() {
        return new IntOperatorMatcherBuilder<CssRule>(new IntOperatorMatcherAdapter<CssRule>() {
            @Override
            public int valueOf(final CssRule matched) {
                return matched.getCssSelectors().size();
            }

            @Override
            public String matcherDisplayName() {
                return "CssSelectorsCount()";
            }
        });
    }

    public static IntOperatorMatcherBuilder<CssRule> lastIndexOfSelector(final CssSelector.Type selectorType) {
        return new IntOperatorMatcherBuilder<CssRule>(new IntOperatorMatcherAdapter<CssRule>() {
            @Override
            public int valueOf(final CssRule matched) {
                final List<CssSelector> selectorList = matched.getCssSelectors();
                for (int i = selectorList.size() - 1; i >= 0; i--) {
                    CssSelector selector = selectorList.get(i);
                    if (selector.getType() == selectorType) {
                        return i;
                    }
                }
                return -1;
            }

            @Override
            public String matcherDisplayName() {
                return "LastIndexOfCssSelectorType(" + selectorType.name() + ")";
            }
        });
    }
}
