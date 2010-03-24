package org.langera.freud.javasource.methoddecl;

import org.hamcrest.Matcher;
import org.langera.freud.AbstractAnalysisBuilder;
import org.langera.freud.dsl.BooleanOperatorDsl;
import org.langera.freud.javasource.methoddecl.assertion.HasDeclaredAnnotationAssertion;
import org.langera.freud.javasource.methoddecl.assertion.MethodDeclNameMatchAssertionAdapter;
import org.langera.freud.util.regex.RegexMatchAnalysisAssertion;

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

public final class MethodDeclarationAnalysisBuilder
        extends AbstractAnalysisBuilder<MethodDeclarationAnalysisBuilder, MethodDeclaration>
        implements MethodDeclarationDsl
{
    public BooleanOperatorDsl<MethodDeclarationAnalysisBuilder> methodDeclaration()
    {
        return trueAssertion();
    }

    public BooleanOperatorDsl<MethodDeclarationAnalysisBuilder> hasDeclaredAnnotation(String annotation)
    {
        setAssertion(new HasDeclaredAnnotationAssertion(annotation));
        return this;
    }

    public BooleanOperatorDsl<MethodDeclarationAnalysisBuilder> hasDeclaredAnnotation(String annotation, String defaultValue)
    {
        setAssertion(new HasDeclaredAnnotationAssertion(annotation, defaultValue));
        return this;
    }

    public BooleanOperatorDsl<MethodDeclarationAnalysisBuilder> hasDeclaredAnnotation(String annotation, String key, String value)
    {
        setAssertion(new HasDeclaredAnnotationAssertion(annotation, key, value));
        return this;
    }

    public BooleanOperatorDsl<MethodDeclarationAnalysisBuilder> hasDeclaredAnnotation(String annotation, Matcher<String> defaultValueMatcher)
    {
        setAssertion(new HasDeclaredAnnotationAssertion(annotation, defaultValueMatcher));
        return this;
    }

    public BooleanOperatorDsl<MethodDeclarationAnalysisBuilder> hasDeclaredAnnotation(String annotation, Matcher<String> keyMatcher, Matcher<String> valueMatcher)
    {
        setAssertion(new HasDeclaredAnnotationAssertion(annotation, keyMatcher, valueMatcher));
        return this;
    }

    public BooleanOperatorDsl<MethodDeclarationAnalysisBuilder> methodName(String regex)
    {
        setAssertion(new RegexMatchAnalysisAssertion<MethodDeclaration>(regex, true, new MethodDeclNameMatchAssertionAdapter()));
        return this;
    }

    public Class<MethodDeclaration> getType()
    {
        return MethodDeclaration.class;
    }
}
