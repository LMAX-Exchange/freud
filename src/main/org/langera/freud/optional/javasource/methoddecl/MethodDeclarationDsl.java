/*
 * Copyright (c) 2011.
 * This file is part of "Freud".
 *
 * Freud is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Freud is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Freud.  If not, see <http://www.gnu.org/licenses/>.
 * @author Amir Langer  langera_at_gmail_dot_com
 */

package org.langera.freud.optional.javasource.methoddecl;

import org.hamcrest.Matcher;
import org.langera.freud.core.matcher.FreudMatcher;
import org.langera.freud.core.matcher.RegexMatcherAdapter;
import org.langera.freud.core.matcher.StringMatcherBuilder;
import org.langera.freud.optional.javasource.annotation.AnnotatedElementDeclarationMatchers;


public final class MethodDeclarationDsl
{
    public static StringMatcherBuilder<MethodDeclaration> methodName()
    {
        return new StringMatcherBuilder<MethodDeclaration>(new RegexMatcherAdapter<MethodDeclaration>()
        {
            @Override
            public String getStringToMatch(final MethodDeclaration toBeAnalysed)
            {
                return toBeAnalysed.getName();
            }

            @Override
            public String matcherDisplayName()
            {
                return "MethodDeclarationName";
            }
        });
    }

    public static FreudMatcher<MethodDeclaration> hasDeclaredAnnotation(final String annotationName)
    {
        return AnnotatedElementDeclarationMatchers.<MethodDeclaration>getInstance().hasDeclaredAnnotation(annotationName);
    }

    @SuppressWarnings("unchecked")
    public static FreudMatcher<MethodDeclaration> hasDeclaredAnnotation(final String annotationName, final String defaultValue)
    {
        return AnnotatedElementDeclarationMatchers.<MethodDeclaration>getInstance().hasDeclaredAnnotation(annotationName, defaultValue);
    }

    @SuppressWarnings("unchecked")
    public static FreudMatcher<MethodDeclaration> hasDeclaredAnnotation(final String annotationName, final String key, final String value)
    {
        return AnnotatedElementDeclarationMatchers.<MethodDeclaration>getInstance().hasDeclaredAnnotation(annotationName, key, value);
    }

    public static FreudMatcher<MethodDeclaration> hasDeclaredAnnotation(final String annotationName,
                                                                        final Matcher<String> defaultValueMatcher)
    {
        return AnnotatedElementDeclarationMatchers.<MethodDeclaration>getInstance().hasDeclaredAnnotation(annotationName, defaultValueMatcher);

    }

    public static FreudMatcher<MethodDeclaration> hasDeclaredAnnotation(final String annotationName,
                                                                        final Matcher<String> keyMatcher,
                                                                        final Matcher<String> valueMatcher)
    {
        return AnnotatedElementDeclarationMatchers.<MethodDeclaration>getInstance().hasDeclaredAnnotation(annotationName, keyMatcher, valueMatcher);
    }
}