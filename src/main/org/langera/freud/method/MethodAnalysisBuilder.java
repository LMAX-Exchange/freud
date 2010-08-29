package org.langera.freud.method;

import org.langera.freud.AbstractAnalysisBuilder;
import org.langera.freud.annotatedelement.assertion.AnnotationAssertion;
import org.langera.freud.dsl.BooleanOperatorDsl;
import org.langera.freud.dsl.CommonDsl;
import org.langera.freud.method.assertion.DeclaredMethodAssertion;
import org.langera.freud.method.assertion.MethodModifierAssertion;
import org.langera.freud.method.assertion.MethodNameMatchAssertionAdapter;
import org.langera.freud.method.assertion.ThrowsExceptionAssertion;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

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

public final class MethodAnalysisBuilder extends AbstractAnalysisBuilder<MethodAnalysisBuilder, Method>
        implements MethodDsl
{
    public CommonDsl<MethodAnalysisBuilder, Method> method()
    {
        setRegexAssertionAdapterClass(MethodNameMatchAssertionAdapter.class);
        return (CommonDsl<MethodAnalysisBuilder, Method>) trueAssertion();
    }

    public BooleanOperatorDsl<MethodAnalysisBuilder> methodAnnotation(Class<? extends Annotation> annotationType)
    {
        setAssertion(new AnnotationAssertion<Method>(annotationType));
        return this;
    }

    public BooleanOperatorDsl<MethodAnalysisBuilder> throwsException(Class<? extends Exception> exceptionType)
    {
        setAssertion(new ThrowsExceptionAssertion(exceptionType));
        return this;
    }

    public BooleanOperatorDsl<MethodAnalysisBuilder> publicMethod()
    {
        setAssertion(new MethodModifierAssertion(Modifier.PUBLIC));
        return this;
    }

    public BooleanOperatorDsl<MethodAnalysisBuilder> staticMethod()
    {
        setAssertion(new MethodModifierAssertion(Modifier.STATIC));
        return this;
    }

    public BooleanOperatorDsl<MethodAnalysisBuilder> declaredMethod()
    {
        setAssertion(new DeclaredMethodAssertion());
        return this;
    }

    public Class<Method> getType()
    {
        return Method.class;
    }
}
