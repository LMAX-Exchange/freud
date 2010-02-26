package org.langera.freud.javasource.method.assertion;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;
import org.langera.freud.AnalysisAssertion;
import org.langera.freud.javasource.annotation.Annotation;
import org.langera.freud.javasource.method.MethodDeclaration;

import java.util.Map;

import static org.hamcrest.Matchers.equalTo;

/**
 *   This file is part of "Freud".
 *
 *   Freud is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU Lesser General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   Freud is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with Freud.  If not, see <http://www.gnu.org/licenses/>.
 *
 *   @author Amir Langer  langera_at_gmail_dot_com
**/

public final class HasDeclaredAnnotationAssertion implements AnalysisAssertion<MethodDeclaration>
{
    private final String annotationName;
    private final Matcher<String> defaultValueMatcher;
    private final Matcher<String> keyMatcher;
    private final Matcher<String> valueMatcher;

    public HasDeclaredAnnotationAssertion(final String annotationName)
    {
        this(annotationName, null, null, null);
    }

    public HasDeclaredAnnotationAssertion(final String annotation, final String defaultValue)
    {
        this(annotation, equalTo(defaultValue), null, null);
    }

    public HasDeclaredAnnotationAssertion(final String annotation, final Matcher<String> defaultValueMatcher)
    {
        this(annotation, defaultValueMatcher, null, null);
    }

    public HasDeclaredAnnotationAssertion(final String annotation, final String key, final String value)
    {
        this(annotation, null, equalTo(key), equalTo(value));
    }

    public HasDeclaredAnnotationAssertion(final String annotation, final Matcher<String> keyMatcher, final Matcher<String> valueMatcher)
    {
        this(annotation, null, keyMatcher, valueMatcher);
    }

    private HasDeclaredAnnotationAssertion(String annotationName, Matcher defaultValueMatcher, Matcher keyMatcher, Matcher valueMatcher)
    {
        this.annotationName = annotationName;
        this.defaultValueMatcher = defaultValueMatcher;
        this.keyMatcher = keyMatcher;
        this.valueMatcher = valueMatcher;
    }

    public boolean analyse(MethodDeclaration toBeAnalysed)
    {
        for (Annotation declaredAnnotation : toBeAnalysed.getDeclaredMethodAnnotations())
        {
            if (annotationName.equals(declaredAnnotation.getName()))
            {
                if (defaultValueMatcher != null)
                {
                    return defaultValueMatcher.matches(declaredAnnotation.getDefaultParameter());                    
                }
                else if (keyMatcher != null || valueMatcher != null)
                {
                    for (Map.Entry<String, String> entry : declaredAnnotation.getParameterMap().entrySet())
                    {
                        if (keyMatcher == null || keyMatcher.matches(entry.getKey()))
                        {
                            if (valueMatcher == null || valueMatcher.matches(entry.getValue()))
                            {
                                return true;
                            }
                        }
                    }
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString()
    {
        Description description = new StringDescription();
        description.appendText("HasDeclaredAnnotation(").appendText(annotationName);
        if (defaultValueMatcher != null)
        {
            defaultValueMatcher.describeTo(description);
        }
        description.appendText(")");
        return description.toString();
    }
}
