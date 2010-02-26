package org.langera.freud.aclass;

import org.langera.freud.AbstractAnalysisBuilder;
import org.langera.freud.AnalysisUtils;
import org.langera.freud.aclass.assertion.ClassSimpleNameMatchAssertionAdapter;
import org.langera.freud.aclass.assertion.HasConstructorArgOfTypeAssertion;
import org.langera.freud.aclass.assertion.HasDeclaredFieldAssertion;
import org.langera.freud.aclass.assertion.HasDeclaredMethodAssertion;
import org.langera.freud.aclass.assertion.HasSetterForTypeAssertion;
import org.langera.freud.aclass.assertion.SubTypeOfAssertion;
import org.langera.freud.annotatedelement.assertion.AnnotationAssertion;
import org.langera.freud.dsl.BooleanOperatorDsl;
import org.langera.freud.dsl.ReadableDsl;

import java.lang.annotation.Annotation;

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

public final class ClassAnalysisBuilder extends AbstractAnalysisBuilder<ClassAnalysisBuilder, Class>
        implements ClassDsl
{
    public ClassAnalysisBuilder subTypeOf(Class type)
    {
        setAssertion(new SubTypeOfAssertion(type));
        return this;
    }

    public BooleanOperatorDsl<ClassAnalysisBuilder> hasPropertyOfType(Class type)
    {
        setAssertion(AnalysisUtils.orOperatorAssertion(
                    new HasSetterForTypeAssertion(type), new HasConstructorArgOfTypeAssertion(type)));
        return this;
    }

    public BooleanOperatorDsl<ClassAnalysisBuilder> classAnnotation(Class<? extends Annotation> annotationType)
    {
        setAssertion(new AnnotationAssertion<Class>(annotationType));
        return this;
    }

    public BooleanOperatorDsl<ClassAnalysisBuilder> classAnnotation(Class<? extends Annotation> annotationType, Object annotationValue)
    {
        setAssertion(new AnnotationAssertion<Class>(annotationType, annotationValue));
        return this;
    }

    public BooleanOperatorDsl<ClassAnalysisBuilder> hasDeclaredMethod(String methodName, Class... parameterTypes)
    {
        setAssertion(new HasDeclaredMethodAssertion(methodName, parameterTypes));
        return this;
    }

    public BooleanOperatorDsl<ClassAnalysisBuilder> hasDeclaredField(Class type)
    {
        setAssertion(new HasDeclaredFieldAssertion(type));
        return this;
    }

    public ReadableDsl<ClassAnalysisBuilder> aClass()
    {
        setRegexAssertionAdapterClass(ClassSimpleNameMatchAssertionAdapter.class);
        return (ReadableDsl<ClassAnalysisBuilder>) trueAssertion();
    }

    public Class<Class> getType()
    {
        return Class.class;
    }
}
