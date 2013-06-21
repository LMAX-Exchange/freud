package examples.org.freud.matchers;


import org.freud.analysed.css.rule.declaration.CssDeclaration;
import org.freud.java.matcher.RegexMatcherAdapter;
import org.freud.java.matcher.StringMatcherBuilder;

public final class CssDeclarationMatchers {
    private CssDeclarationMatchers() {
        // static utility
    }

    public static StringMatcherBuilder<CssDeclaration> declarationKey() {
        return new StringMatcherBuilder<CssDeclaration>(new RegexMatcherAdapter<CssDeclaration>() {
            @Override
            public String getStringToMatch(final CssDeclaration toBeAnalysed) {
                return toBeAnalysed.getKey();
            }

            @Override
            public String matcherDisplayName() {
                return "CssDeclaration";
            }
        });
    }

    public static StringMatcherBuilder<CssDeclaration> declarationValue() {
        return new StringMatcherBuilder<CssDeclaration>(new RegexMatcherAdapter<CssDeclaration>() {
            @Override
            public String getStringToMatch(final CssDeclaration toBeAnalysed) {
                return toBeAnalysed.getValue();
            }

            @Override
            public String matcherDisplayName() {
                return "CssDeclaration";
            }
        });
    }
}
