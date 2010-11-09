// Freud generated code [SpringBeanAnalysis] [2010-11-09 14:03:57]
package org.langera.freudgenerated.spring;

import org.langera.freud.AbstractAnalysis;
import org.langera.freud.Analysis;
import org.langera.freud.NestedTypeAnalysisAdapter;
import org.langera.freud.NestedTypeAnalysisAdapterChain;
import org.langera.freud.aclass.ClassAnalysisBuilder;
import org.langera.freud.aclass.ClassDsl;
import org.langera.freud.dsl.BooleanOperatorDsl;
import org.langera.freud.dsl.CommonDsl;
import org.langera.freud.instance.InstanceAnalysisBuilder;
import org.langera.freud.instance.InstanceDsl;
import org.langera.freud.method.MethodAnalysisBuilder;
import org.langera.freud.method.MethodDsl;
import org.langera.freud.spring.SpringBean;
import org.langera.freud.spring.SpringBeanAnalysisBuilder;
import org.langera.freud.spring.SpringBeanDsl;
import org.langera.freud.util.collection.AnalysedObjectIterator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class SpringBeanAnalysis extends AbstractAnalysis<SpringBean, SpringBeanAnalysis>
        implements
                    InstanceDsl,
                    MethodDsl,
                    SpringBeanDsl,
                    ClassDsl,
                    Analysis
{
    public SpringBeanAnalysis(AnalysedObjectIterator<SpringBean> springbeanAnalysedObjectIterator)
    {
        super(springbeanAnalysedObjectIterator, SpringBean.class);
    }

    protected NestedTypeAnalysisAdapter getAnalysisAdapter(final Class type, Class nestedType)
    {
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

            public CommonDsl<InstanceAnalysisBuilder, Object> instance()
        {
            return new InstanceAnalysisBuilder().instance(
                        );
        }
        
                public CommonDsl<MethodAnalysisBuilder, Method> method()
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
        
                public CommonDsl<SpringBeanAnalysisBuilder, SpringBean> springBean()
        {
            return new SpringBeanAnalysisBuilder().springBean(
                        );
        }
        
                public CommonDsl<ClassAnalysisBuilder, Class> aClass()
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
        
    
}
