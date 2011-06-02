package org.langera.freud.optional.javasource.block;

import org.hamcrest.Description;
import org.langera.freud.core.matcher.FreudMatcher;
import org.langera.freud.core.matcher.IntOperatorMatcherAdapter;
import org.langera.freud.core.matcher.IntOperatorMatcherBuilder;
import org.langera.freud.optional.javasource.methodcall.MethodCall;
import org.langera.freud.optional.javasource.methoddecl.MethodDeclaration;

import java.util.Arrays;
import java.util.List;

/**
 * This file is part of "Freud".
 * <p/>
 * Freud is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p/>
 * Freud is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU General Public License
 * along with Freud.  If not, see <http://www.gnu.org/licenses/>.
 *
 * @author Amir Langer  langera_at_gmail_dot_com
 */

public final class CodeBlockDsl
{
//    public static RegexMatcherBuilder<CodeBlock> codeBlock()
//    {
//TODO
//    }

    public static FreudMatcher<CodeBlock> hasMethodCall(final String methodCall)
    {

        return new FreudMatcher<CodeBlock>()
        {
            private final String methodName;
            private final String[] instanceReferences;

            {
                String[] values = methodCall.split("\\.");
                instanceReferences = new String[values.length - 1];
                System.arraycopy(values, 0, instanceReferences, 0, instanceReferences.length);
                methodName = values[values.length - 1];
            }

            @Override
            protected boolean matchesSafely(final CodeBlock toBeAnalysed)
            {
                List<MethodCall> methodCallList = toBeAnalysed.getMethodCallListByMethodName(methodName);
                if (methodCallList != null)
                {
                    for (MethodCall methodCall : methodCallList)
                    {
                        if (Arrays.equals(instanceReferences, methodCall.getInstanceReferences()))
                        {
                            return true;
                        }
                    }
                }
                return false;
            }

            @Override
            public void describeTo(final Description description)
            {
                description.appendText("HasMethodCall(" + methodCall + ")");
            }
        };
    }

    public static IntOperatorMatcherBuilder<CodeBlock> codeBlockLines()
    {
        return new IntOperatorMatcherBuilder<CodeBlock>(new IntOperatorMatcherAdapter<CodeBlock>()
        {
            @Override
            public int valueOf(final CodeBlock matched)
            {
                return matched.getNumberOfLines();
            }

            @Override
            public String matcherDisplayName()
            {
                return "CodeBlockNumberOfLines";
            }
        });
    }

    public static FreudMatcher<CodeBlock> method(final FreudMatcher<MethodDeclaration> methodMatcher)
    {
        return new FreudMatcher<CodeBlock>()
        {
            @Override
            protected boolean matchesSafely(final CodeBlock item)
            {
                return methodMatcher.matches(item.getMethodDeclaration());
            }

            @Override
            public void describeTo(final Description description)
            {
                description.appendText("CodeBlockMethodIs(");
                methodMatcher.describeTo(description);
                description.appendText(")");
            }
        };
    }
}
