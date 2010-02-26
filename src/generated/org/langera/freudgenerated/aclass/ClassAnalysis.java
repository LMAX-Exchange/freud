// Freud generated code [ClassAnalysis] [2010-02-14 19:03:24]
package org.langera.freudgenerated.aclass;

import org.langera.freud.*;
import org.langera.freud.dsl.*;
import org.langera.freud.util.collection.AnalysedObjectIterator;
import org.langera.freud.aclass.*;
import org.langera.freud.method.*;
import java.lang.annotation.Annotation;
import org.langera.freud.dsl.ReadableDsl;
import java.lang.reflect.Method;
import org.langera.freud.dsl.BooleanOperatorDsl;

public class ClassAnalysis extends AbstractAnalysis<Class>
        implements
                    ClassDsl,
                    MethodDsl,
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

            public ReadableDsl<ClassAnalysisBuilder> aClass()
        {

            return new ClassAnalysisBuilder().aClass(
                        );
        }
        
            public BooleanOperatorDsl<ClassAnalysisBuilder> subTypeOf(Class type)
        {

            return new ClassAnalysisBuilder().subTypeOf(
                            type
                                        );
        }
        
            public BooleanOperatorDsl<ClassAnalysisBuilder> classAnnotation(Class<? extends Annotation> annotationType)
        {

            return new ClassAnalysisBuilder().classAnnotation(
                            annotationType
                                        );
        }
        
            public BooleanOperatorDsl<ClassAnalysisBuilder> classAnnotation(
                            Class<? extends Annotation> annotationType, Object annotationValue)
        {

            return new ClassAnalysisBuilder().classAnnotation(
                            annotationType
                                    ,
                                            annotationValue
                                        );
        }
        
            public BooleanOperatorDsl<ClassAnalysisBuilder> hasDeclaredMethod(String methodName, Class... parameterTypes)
        {

            return new ClassAnalysisBuilder().hasDeclaredMethod(
                            methodName
                                    ,
                                            parameterTypes
                                        );
        }
        
            public BooleanOperatorDsl<ClassAnalysisBuilder> hasDeclaredField(Class fieldType)
        {

            return new ClassAnalysisBuilder().hasDeclaredField(
                            fieldType
                                        );
        }
        
            public BooleanOperatorDsl<ClassAnalysisBuilder> hasPropertyOfType(Class type)
        {

            return new ClassAnalysisBuilder().hasPropertyOfType(
                            type
                                        );
        }
        
                public ReadableDsl<MethodAnalysisBuilder> method()
        {

            return new MethodAnalysisBuilder().method(
                        );
        }
        
            public BooleanOperatorDsl<MethodAnalysisBuilder> methodAnnotation(final Class<? extends Annotation> annotationType)
        {

            return new MethodAnalysisBuilder().methodAnnotation(
                            annotationType
                                        );
        }
        
            public BooleanOperatorDsl<MethodAnalysisBuilder> throwsException(final Class<? extends Exception> exceptionType)
        {

            return new MethodAnalysisBuilder().throwsException(
                            exceptionType
                                        );
        }
        
            public BooleanOperatorDsl<MethodAnalysisBuilder> publicMethod()
        {

            return new MethodAnalysisBuilder().publicMethod(
                        );
        }
        
            public BooleanOperatorDsl<MethodAnalysisBuilder> staticMethod()
        {

            return new MethodAnalysisBuilder().staticMethod(
                        );
        }
        
            public BooleanOperatorDsl<MethodAnalysisBuilder> declaredMethod()
        {

            return new MethodAnalysisBuilder().declaredMethod(
                        );
        }
        
    
}
