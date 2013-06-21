package examples.org.freud.matchers;

import org.freud.analysed.javasource.CodeBlock;
import org.freud.analysed.javasource.MethodCall;
import org.freud.analysed.javasource.MethodDeclaration;
import org.freud.java.matcher.FreudExtendedMatcher;
import org.freud.java.matcher.IntOperatorMatcherAdapter;
import org.freud.java.matcher.IntOperatorMatcherBuilder;
import org.hamcrest.Description;

import java.util.Arrays;
import java.util.List;

public final class CodeBlockMatchers {

    private CodeBlockMatchers() {
        // static utility
    }

    public static FreudExtendedMatcher<CodeBlock> hasMethodCall(final String methodCall) {

        return new FreudExtendedMatcher<CodeBlock>() {
            private final String methodName;
            private final String[] instanceReferences;

            {
                String[] values = methodCall.split("\\.");
                instanceReferences = new String[values.length - 1];
                System.arraycopy(values, 0, instanceReferences, 0, instanceReferences.length);
                methodName = values[values.length - 1];
            }

            @Override
            protected boolean matchesSafely(final CodeBlock toBeAnalysed) {
                List<MethodCall> methodCallList = toBeAnalysed.getMethodCallListByMethodName(methodName);
                if (methodCallList != null) {
                    for (MethodCall methodCall : methodCallList) {
                        if (Arrays.equals(instanceReferences, methodCall.getInstanceReferences())) {
                            return true;
                        }
                    }
                }
                return false;
            }

            @Override
            public void describeTo(final Description description) {
                description.appendText("HasMethodCall(" + methodCall + ")");
            }
        };
    }

    public static IntOperatorMatcherBuilder<CodeBlock> codeBlockLines() {
        return new IntOperatorMatcherBuilder<CodeBlock>(new IntOperatorMatcherAdapter<CodeBlock>() {
            @Override
            public int valueOf(final CodeBlock matched) {
                return matched.getNumberOfLines();
            }

            @Override
            public String matcherDisplayName() {
                return "CodeBlockNumberOfLines";
            }
        });
    }

    public static FreudExtendedMatcher<CodeBlock> method(final FreudExtendedMatcher<MethodDeclaration> methodMatcher) {
        return new FreudExtendedMatcher<CodeBlock>() {
            @Override
            protected boolean matchesSafely(final CodeBlock item) {
                return methodMatcher.matches(item.getMethodDeclaration());
            }

            @Override
            public void describeTo(final Description description) {
                description.appendText("CodeBlockMethodIs(");
                methodMatcher.describeTo(description);
                description.appendText(")");
            }
        };
    }
}
