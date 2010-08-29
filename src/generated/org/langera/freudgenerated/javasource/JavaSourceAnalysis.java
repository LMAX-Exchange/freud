// Freud generated code [JavaSourceAnalysis] [2010-08-29 18:09:33]
package org.langera.freudgenerated.javasource;

import org.langera.freud.*;
import org.langera.freud.dsl.*;
import org.langera.freud.util.collection.AnalysedObjectIterator;
import org.langera.freud.javasource.apackage.*;
import org.langera.freud.method.*;
import org.langera.freud.javasource.block.*;
import org.langera.freud.javasource.*;
import org.langera.freud.javasource.methoddecl.*;
import org.langera.freud.aclass.*;
import org.langera.freud.javasource.classdecl.*;
import java.lang.annotation.Annotation;
import org.langera.freud.dsl.CommonDsl;
import org.langera.freud.dsl.NumericOperatorDsl;
import org.langera.freud.javasource.methoddecl.MethodDeclarationAnalysisBuilder;
import org.langera.freud.dsl.ReadableDsl;
import org.hamcrest.Matcher;
import java.lang.reflect.Method;
import org.langera.freud.dsl.MatchingDsl;
import org.langera.freud.dsl.BooleanOperatorDsl;

public class JavaSourceAnalysis extends AbstractAnalysis<JavaSource, JavaSourceAnalysis>
        implements
                    PackageDeclarationDsl,
                    MethodDsl,
                    CodeBlockDsl,
                    JavaSourceDsl,
                    MethodDeclarationDsl,
                    ClassDsl,
                    ClassDeclarationDsl,
                    Analysis
{
    public JavaSourceAnalysis(AnalysedObjectIterator<JavaSource> javasourceAnalysedObjectIterator)
    {
        super(javasourceAnalysedObjectIterator, JavaSource.class);
    }

    protected NestedTypeAnalysisAdapter getAnalysisAdapter(final Class type, Class nestedType)
    {
                    if (type == ClassDeclaration.class)
            {
                                    if (nestedType == CodeBlock.class)
                    {                                                
                        return ClassDeclarationToCodeBlockAnalysisAdapter.getInstance();
                    }
                                    if (nestedType == MethodDeclaration.class)
                    {                                                
                        return ClassDeclarationToMethodDeclarationAnalysisAdapter.getInstance();
                    }
                                return null;
            }
                    if (type == Class.class)
            {
                                    if (nestedType == Method.class)
                    {                                                
                        return ClassToMethodAnalysisAdapter.getInstance();
                    }
                                return null;
            }
                    if (type == JavaSource.class)
            {
                                    if (nestedType == ClassDeclaration.class)
                    {                                                
                        return JavaSourceToClassDeclarationAnalysisAdapter.getInstance();
                    }
                                    if (nestedType == PackageDeclaration.class)
                    {                                                
                        return JavaSourceToPackageDeclarationAnalysisAdapter.getInstance();
                    }
                                    if (nestedType == Class.class)
                    {                                                
                        return JavaSourceToClassAnalysisAdapter.getInstance();
                    }
                                    if (nestedType == CodeBlock.class)
                    {                                                
                        return NestedTypeAnalysisAdapterChain.createChain(
			JavaSourceToClassDeclarationAnalysisAdapter.getInstance(),
			ClassDeclarationToCodeBlockAnalysisAdapter.getInstance());
                    }
                                    if (nestedType == MethodDeclaration.class)
                    {                                                
                        return NestedTypeAnalysisAdapterChain.createChain(
			JavaSourceToClassDeclarationAnalysisAdapter.getInstance(),
			ClassDeclarationToMethodDeclarationAnalysisAdapter.getInstance());
                    }
                                    if (nestedType == Method.class)
                    {                                                
                        return NestedTypeAnalysisAdapterChain.createChain(
			JavaSourceToClassAnalysisAdapter.getInstance(),
			ClassToMethodAnalysisAdapter.getInstance());
                    }
                                return null;
            }
                return null;
    }

    //////////////////////////////////////////////////////////////////////////////////
    /// DSL

            public CommonDsl<PackageDeclarationAnalysisBuilder, PackageDeclaration> packageDeclaration()
        {
            return new PackageDeclarationAnalysisBuilder().packageDeclaration(
                        );
        }
        
            public NumericOperatorDsl<PackageDeclarationAnalysisBuilder> packageDepth()
        {
            return new PackageDeclarationAnalysisBuilder().packageDepth(
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
        
                public MatchingDsl<CodeBlockAnalysisBuilder, CodeBlock> codeBlock()
        {
            return new CodeBlockAnalysisBuilder().codeBlock(
                        );
        }
        
            public BooleanOperatorDsl<CodeBlockAnalysisBuilder> hasMethodCall(String methodCall)
        {
            return new CodeBlockAnalysisBuilder().hasMethodCall(
                            methodCall
                                        );
        }
        
            public NumericOperatorDsl<CodeBlockAnalysisBuilder> codeBlockLines()
        {
            return new CodeBlockAnalysisBuilder().codeBlockLines(
                        );
        }
        
            public BooleanOperatorDsl<CodeBlockAnalysisBuilder> method(
            BooleanOperatorDsl<MethodDeclarationAnalysisBuilder> methodDeclarationDsl)
        {
            return new CodeBlockAnalysisBuilder().method(
                            methodDeclarationDsl
                                        );
        }
        
                public CommonDsl<JavaSourceAnalysisBuilder, JavaSource> javaSource()
        {
            return new JavaSourceAnalysisBuilder().javaSource(
                        );
        }
        
                public CommonDsl<MethodDeclarationAnalysisBuilder, MethodDeclaration> methodDeclaration()
        {
            return new MethodDeclarationAnalysisBuilder().methodDeclaration(
                        );
        }
        
            public BooleanOperatorDsl<MethodDeclarationAnalysisBuilder> hasDeclaredAnnotation(String annotation)
        {
            return new MethodDeclarationAnalysisBuilder().hasDeclaredAnnotation(
                            annotation
                                        );
        }
        
            public BooleanOperatorDsl<MethodDeclarationAnalysisBuilder> hasDeclaredAnnotation(String annotation, String defaultValue)
        {
            return new MethodDeclarationAnalysisBuilder().hasDeclaredAnnotation(
                            annotation
                                    ,
                                            defaultValue
                                        );
        }
        
            public BooleanOperatorDsl<MethodDeclarationAnalysisBuilder> hasDeclaredAnnotation(String annotation, String key, String value)
        {
            return new MethodDeclarationAnalysisBuilder().hasDeclaredAnnotation(
                            annotation
                                    ,
                                            key
                                    ,
                                            value
                                        );
        }
        
            public BooleanOperatorDsl<MethodDeclarationAnalysisBuilder> hasDeclaredAnnotation(String annotation, Matcher<String> defaultValueMatcher)
        {
            return new MethodDeclarationAnalysisBuilder().hasDeclaredAnnotation(
                            annotation
                                    ,
                                            defaultValueMatcher
                                        );
        }
        
            public BooleanOperatorDsl<MethodDeclarationAnalysisBuilder> hasDeclaredAnnotation(String annotation, Matcher<String> keyMatcher, Matcher<String> valueMatcher)
        {
            return new MethodDeclarationAnalysisBuilder().hasDeclaredAnnotation(
                            annotation
                                    ,
                                            keyMatcher
                                    ,
                                            valueMatcher
                                        );
        }
        
            public BooleanOperatorDsl<MethodDeclarationAnalysisBuilder> methodName(String regex)
        {
            return new MethodDeclarationAnalysisBuilder().methodName(
                            regex
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
        
                public CommonDsl<ClassDeclarationAnalysisBuilder, ClassDeclaration> classDeclaration()
        {
            return new ClassDeclarationAnalysisBuilder().classDeclaration(
                        );
        }
        
            public BooleanOperatorDsl<ClassDeclarationAnalysisBuilder> hasDeclaredMethod(String methodName)
        {
            return new ClassDeclarationAnalysisBuilder().hasDeclaredMethod(
                            methodName
                                        );
        }
        
    
}
