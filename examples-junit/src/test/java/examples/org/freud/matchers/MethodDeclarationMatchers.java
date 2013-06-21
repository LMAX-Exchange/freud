package examples.org.freud.matchers;

import org.freud.analysed.javasource.MethodDeclaration;
import org.freud.java.matcher.FreudExtendedMatcher;
import org.freud.java.matcher.RegexMatcherAdapter;
import org.freud.java.matcher.StringMatcherBuilder;
import org.hamcrest.Matcher;


public final class MethodDeclarationMatchers {
    public static StringMatcherBuilder<MethodDeclaration> methodName() {
        return new StringMatcherBuilder<MethodDeclaration>(new RegexMatcherAdapter<MethodDeclaration>() {
            @Override
            public String getStringToMatch(final MethodDeclaration toBeAnalysed) {
                return toBeAnalysed.getName();
            }

            @Override
            public String matcherDisplayName() {
                return "MethodDeclarationName";
            }
        });
    }

    public static FreudExtendedMatcher<MethodDeclaration> hasDeclaredAnnotation(final String annotationName) {
        return AnnotatedElementDeclarationMatchers.<MethodDeclaration>getInstance().hasDeclaredAnnotation(annotationName);
    }

    @SuppressWarnings("unchecked")
    public static FreudExtendedMatcher<MethodDeclaration> hasDeclaredAnnotation(final String annotationName, final String defaultValue) {
        return AnnotatedElementDeclarationMatchers.<MethodDeclaration>getInstance().hasDeclaredAnnotation(annotationName, defaultValue);
    }

    @SuppressWarnings("unchecked")
    public static FreudExtendedMatcher<MethodDeclaration> hasDeclaredAnnotation(final String annotationName, final String key, final String value) {
        return AnnotatedElementDeclarationMatchers.<MethodDeclaration>getInstance().hasDeclaredAnnotation(annotationName, key, value);
    }

    public static FreudExtendedMatcher<MethodDeclaration> hasDeclaredAnnotation(final String annotationName,
                                                                                final Matcher<String> defaultValueMatcher) {
        return AnnotatedElementDeclarationMatchers.<MethodDeclaration>getInstance().hasDeclaredAnnotation(annotationName, defaultValueMatcher);

    }

    public static FreudExtendedMatcher<MethodDeclaration> hasDeclaredAnnotation(final String annotationName,
                                                                                final Matcher<String> keyMatcher,
                                                                                final Matcher<String> valueMatcher) {
        return AnnotatedElementDeclarationMatchers.<MethodDeclaration>getInstance().hasDeclaredAnnotation(annotationName, keyMatcher, valueMatcher);
    }
}