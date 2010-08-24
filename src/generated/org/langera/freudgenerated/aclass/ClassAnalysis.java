// Freud generated code [ClassAnalysis] [2010-08-24 21:26:15]
package org.langera.freudgenerated.aclass;

import org.langera.freud.AbstractAnalysis;
import org.langera.freud.Analysis;
import org.langera.freud.NestedTypeAnalysisAdapter;
import org.langera.freud.aclass.ClassAnalysisBuilder;
import org.langera.freud.aclass.ClassDsl;
import org.langera.freud.dsl.BooleanOperatorDsl;
import org.langera.freud.dsl.ReadableDsl;
import org.langera.freud.method.MethodAnalysisBuilder;
import org.langera.freud.method.MethodDsl;
import org.langera.freud.util.collection.AnalysedObjectIterator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class ClassAnalysis extends AbstractAnalysis<Class, ClassAnalysis>
        implements
        MethodDsl,
        ClassDsl,
        Analysis
{
    public ClassAnalysis(AnalysedObjectIterator<Class> classAnalysedObjectIterator)
    {
        super(classAnalysedObjectIterator, Class.class);
    }

    protected NestedTypeAnalysisAdapter getAnalysisAdapter(final Class type, Class nestedType)
    {
        if (type == Class.class)
        {
            if (nestedType == Method.class)
            {
                return ClassToMethodAnalysisAdapter.getInstance();
            }
            return null;
        }
        return null;
    }

    //////////////////////////////////////////////////////////////////////////////////
    /// DSL

    public ReadableDsl<MethodAnalysisBuilder> method()
    {
        ReadableDsl<MethodAnalysisBuilder> builder = new MethodAnalysisBuilder().method(
        );
        return builder;
    }

    public BooleanOperatorDsl<MethodAnalysisBuilder> methodAnnotation(final Class<? extends Annotation> annotationType)
    {
        BooleanOperatorDsl<MethodAnalysisBuilder> builder = new MethodAnalysisBuilder().methodAnnotation(
                annotationType
        );
        return builder;
    }

    public BooleanOperatorDsl<MethodAnalysisBuilder> throwsException(final Class<? extends Exception> exceptionType)
    {
        BooleanOperatorDsl<MethodAnalysisBuilder> builder = new MethodAnalysisBuilder().throwsException(
                exceptionType
        );
        return builder;
    }

    public BooleanOperatorDsl<MethodAnalysisBuilder> publicMethod()
    {
        BooleanOperatorDsl<MethodAnalysisBuilder> builder = new MethodAnalysisBuilder().publicMethod(
        );
        return builder;
    }

    public BooleanOperatorDsl<MethodAnalysisBuilder> staticMethod()
    {
        BooleanOperatorDsl<MethodAnalysisBuilder> builder = new MethodAnalysisBuilder().staticMethod(
        );
        return builder;
    }

    public BooleanOperatorDsl<MethodAnalysisBuilder> declaredMethod()
    {
        BooleanOperatorDsl<MethodAnalysisBuilder> builder = new MethodAnalysisBuilder().declaredMethod(
        );
        return builder;
    }

    public ReadableDsl<ClassAnalysisBuilder> aClass()
    {
        ReadableDsl<ClassAnalysisBuilder> builder = new ClassAnalysisBuilder().aClass(
        );
        return builder;
    }

    public BooleanOperatorDsl<ClassAnalysisBuilder> subTypeOf(Class type)
    {
        BooleanOperatorDsl<ClassAnalysisBuilder> builder = new ClassAnalysisBuilder().subTypeOf(
                type
        );
        return builder;
    }

    public BooleanOperatorDsl<ClassAnalysisBuilder> classAnnotation(Class<? extends Annotation> annotationType)
    {
        BooleanOperatorDsl<ClassAnalysisBuilder> builder = new ClassAnalysisBuilder().classAnnotation(
                annotationType
        );
        return builder;
    }

    public BooleanOperatorDsl<ClassAnalysisBuilder> classAnnotation(
            Class<? extends Annotation> annotationType, Object annotationValue)
    {
        BooleanOperatorDsl<ClassAnalysisBuilder> builder = new ClassAnalysisBuilder().classAnnotation(
                annotationType
                ,
                annotationValue
        );
        return builder;
    }

    public BooleanOperatorDsl<ClassAnalysisBuilder> hasDeclaredMethod(String methodName, Class... parameterTypes)
    {
        BooleanOperatorDsl<ClassAnalysisBuilder> builder = new ClassAnalysisBuilder().hasDeclaredMethod(
                methodName
                ,
                parameterTypes
        );
        return builder;
    }

    public BooleanOperatorDsl<ClassAnalysisBuilder> hasDeclaredField(Class fieldType)
    {
        BooleanOperatorDsl<ClassAnalysisBuilder> builder = new ClassAnalysisBuilder().hasDeclaredField(
                fieldType
        );
        return builder;
    }

    public BooleanOperatorDsl<ClassAnalysisBuilder> hasPropertyOfType(Class type)
    {
        BooleanOperatorDsl<ClassAnalysisBuilder> builder = new ClassAnalysisBuilder().hasPropertyOfType(
                type
        );
        return builder;
    }


}
