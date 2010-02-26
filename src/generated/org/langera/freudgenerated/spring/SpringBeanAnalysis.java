// Freud generated code [SpringBeanAnalysis] [2010-02-14 19:03:25]
package org.langera.freudgenerated.spring;

import org.langera.freud.*;
import org.langera.freud.dsl.*;
import org.langera.freud.util.collection.AnalysedObjectIterator;
import org.langera.freud.spring.*;
import org.langera.freud.instance.*;
import org.langera.freud.aclass.*;
import org.langera.freud.method.*;
import java.lang.annotation.Annotation;
import org.langera.freud.dsl.ReadableDsl;
import java.lang.reflect.Method;
import org.langera.freud.dsl.BooleanOperatorDsl;

public class SpringBeanAnalysis extends AbstractAnalysis<SpringBean>
        implements
                    SpringBeanDsl,
                    InstanceDsl,
                    ClassDsl,
                    MethodDsl,
                    Analysis
{
    public SpringBeanAnalysis(AnalysedObjectIterator<SpringBean> springbeanAnalysedObjectIterator)
    {
        super(springbeanAnalysedObjectIterator, SpringBean.class);
    }

    protected NestedTypeAnalysisAdapter getAnalysisAdapter(final Class type, Class nestedType)
    {
                    if (type == SpringBean.class)
            {
                                    if (nestedType == Object.class)
                    {                                                
                        return SpringBeanToObjectAnalysisAdapter.getInstance();
                    }
                                    if (nestedType == Class.class)
                    {                                                
                        return SpringBeanToClassAnalysisAdapter.getInstance();
                    }
                                    if (nestedType == Method.class)
                    {                                                
                        return NestedTypeAnalysisAdapterChain.createChain(
			SpringBeanToClassAnalysisAdapter.getInstance(),
			ClassToMethodAnalysisAdapter.getInstance());
                    }
                                return null;
            }
                    if (type == Object.class)
            {
                                    if (nestedType == Class.class)
                    {                                                
                        return ObjectToClassAnalysisAdapter.getInstance();
                    }
                                    if (nestedType == Method.class)
                    {                                                
                        return NestedTypeAnalysisAdapterChain.createChain(
			ObjectToClassAnalysisAdapter.getInstance(),
			ClassToMethodAnalysisAdapter.getInstance());
                    }
                                return null;
            }
                    if (type == Class.class)
            {
                                    if (nestedType == Method.class)
                    {                                                
                        return ClassToMethodAnalysisAdapter.getInstance();
                    }
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

            public ReadableDsl<SpringBeanAnalysisBuilder> springBean()
        {

            return new SpringBeanAnalysisBuilder().springBean(
                        );
        }
        
                public BooleanOperatorDsl<InstanceAnalysisBuilder> instance()
        {

            return new InstanceAnalysisBuilder().instance(
                        );
        }
        
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
