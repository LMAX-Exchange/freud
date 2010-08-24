// Freud generated code [JavaSourceAnalysis] [2010-08-24 21:26:16]
package org.langera.freudgenerated.javasource;

import org.hamcrest.Matcher;
import org.langera.freud.AbstractAnalysis;
import org.langera.freud.Analysis;
import org.langera.freud.NestedTypeAnalysisAdapter;
import org.langera.freud.NestedTypeAnalysisAdapterChain;
import org.langera.freud.aclass.ClassAnalysisBuilder;
import org.langera.freud.aclass.ClassDsl;
import org.langera.freud.dsl.BooleanOperatorDsl;
import org.langera.freud.dsl.NumericOperatorDsl;
import org.langera.freud.dsl.ReadableDsl;
import org.langera.freud.javasource.JavaSource;
import org.langera.freud.javasource.JavaSourceAnalysisBuilder;
import org.langera.freud.javasource.JavaSourceDsl;
import org.langera.freud.javasource.apackage.PackageDeclaration;
import org.langera.freud.javasource.apackage.PackageDeclarationAnalysisBuilder;
import org.langera.freud.javasource.apackage.PackageDeclarationDsl;
import org.langera.freud.javasource.block.CodeBlock;
import org.langera.freud.javasource.block.CodeBlockAnalysisBuilder;
import org.langera.freud.javasource.block.CodeBlockDsl;
import org.langera.freud.javasource.classdecl.ClassDeclaration;
import org.langera.freud.javasource.classdecl.ClassDeclarationAnalysisBuilder;
import org.langera.freud.javasource.classdecl.ClassDeclarationDsl;
import org.langera.freud.javasource.methoddecl.MethodDeclaration;
import org.langera.freud.javasource.methoddecl.MethodDeclarationAnalysisBuilder;
import org.langera.freud.javasource.methoddecl.MethodDeclarationDsl;
import org.langera.freud.method.MethodAnalysisBuilder;
import org.langera.freud.method.MethodDsl;
import org.langera.freud.util.collection.AnalysedObjectIterator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class JavaSourceAnalysis extends AbstractAnalysis<JavaSource, JavaSourceAnalysis>
        implements
        MethodDsl,
        ClassDeclarationDsl,
        JavaSourceDsl,
        ClassDsl,
        PackageDeclarationDsl,
        CodeBlockDsl,
        MethodDeclarationDsl,
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

    public BooleanOperatorDsl<ClassDeclarationAnalysisBuilder> classDeclaration()
    {
        BooleanOperatorDsl<ClassDeclarationAnalysisBuilder> builder = new ClassDeclarationAnalysisBuilder().classDeclaration(
        );
        return builder;
    }

    public BooleanOperatorDsl<ClassDeclarationAnalysisBuilder> hasDeclaredMethod(String methodName)
    {
        BooleanOperatorDsl<ClassDeclarationAnalysisBuilder> builder = new ClassDeclarationAnalysisBuilder().hasDeclaredMethod(
                methodName
        );
        return builder;
    }

    public ReadableDsl<JavaSourceAnalysisBuilder> javaSource()
    {
        ReadableDsl<JavaSourceAnalysisBuilder> builder = new JavaSourceAnalysisBuilder().javaSource(
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

    public ReadableDsl<PackageDeclarationAnalysisBuilder> packageDeclaration()
    {
        ReadableDsl<PackageDeclarationAnalysisBuilder> builder = new PackageDeclarationAnalysisBuilder().packageDeclaration(
        );
        return builder;
    }

    public NumericOperatorDsl<PackageDeclarationAnalysisBuilder> packageDepth()
    {
        NumericOperatorDsl<PackageDeclarationAnalysisBuilder> builder = new PackageDeclarationAnalysisBuilder().packageDepth(
        );
        return builder;
    }

    public BooleanOperatorDsl<CodeBlockAnalysisBuilder> codeBlock()
    {
        BooleanOperatorDsl<CodeBlockAnalysisBuilder> builder = new CodeBlockAnalysisBuilder().codeBlock(
        );
        return builder;
    }

    public BooleanOperatorDsl<CodeBlockAnalysisBuilder> hasMethodCall(String methodCall)
    {
        BooleanOperatorDsl<CodeBlockAnalysisBuilder> builder = new CodeBlockAnalysisBuilder().hasMethodCall(
                methodCall
        );
        return builder;
    }

    public NumericOperatorDsl<CodeBlockAnalysisBuilder> codeBlockNumberOfLines()
    {
        NumericOperatorDsl<CodeBlockAnalysisBuilder> builder = new CodeBlockAnalysisBuilder().codeBlockNumberOfLines(
        );
        return builder;
    }

    public BooleanOperatorDsl<CodeBlockAnalysisBuilder> method(
            BooleanOperatorDsl<MethodDeclarationAnalysisBuilder> methodDeclarationDsl)
    {
        BooleanOperatorDsl<CodeBlockAnalysisBuilder> builder = new CodeBlockAnalysisBuilder().method(
                methodDeclarationDsl
        );
        return builder;
    }

    public BooleanOperatorDsl<MethodDeclarationAnalysisBuilder> methodDeclaration()
    {
        BooleanOperatorDsl<MethodDeclarationAnalysisBuilder> builder = new MethodDeclarationAnalysisBuilder().methodDeclaration(
        );
        return builder;
    }

    public BooleanOperatorDsl<MethodDeclarationAnalysisBuilder> hasDeclaredAnnotation(String annotation)
    {
        BooleanOperatorDsl<MethodDeclarationAnalysisBuilder> builder = new MethodDeclarationAnalysisBuilder().hasDeclaredAnnotation(
                annotation
        );
        return builder;
    }

    public BooleanOperatorDsl<MethodDeclarationAnalysisBuilder> hasDeclaredAnnotation(String annotation, String defaultValue)
    {
        BooleanOperatorDsl<MethodDeclarationAnalysisBuilder> builder = new MethodDeclarationAnalysisBuilder().hasDeclaredAnnotation(
                annotation
                ,
                defaultValue
        );
        return builder;
    }

    public BooleanOperatorDsl<MethodDeclarationAnalysisBuilder> hasDeclaredAnnotation(String annotation, String key, String value)
    {
        BooleanOperatorDsl<MethodDeclarationAnalysisBuilder> builder = new MethodDeclarationAnalysisBuilder().hasDeclaredAnnotation(
                annotation
                ,
                key
                ,
                value
        );
        return builder;
    }

    public BooleanOperatorDsl<MethodDeclarationAnalysisBuilder> hasDeclaredAnnotation(String annotation, Matcher<String> defaultValueMatcher)
    {
        BooleanOperatorDsl<MethodDeclarationAnalysisBuilder> builder = new MethodDeclarationAnalysisBuilder().hasDeclaredAnnotation(
                annotation
                ,
                defaultValueMatcher
        );
        return builder;
    }

    public BooleanOperatorDsl<MethodDeclarationAnalysisBuilder> hasDeclaredAnnotation(String annotation, Matcher<String> keyMatcher, Matcher<String> valueMatcher)
    {
        BooleanOperatorDsl<MethodDeclarationAnalysisBuilder> builder = new MethodDeclarationAnalysisBuilder().hasDeclaredAnnotation(
                annotation
                ,
                keyMatcher
                ,
                valueMatcher
        );
        return builder;
    }

    public BooleanOperatorDsl<MethodDeclarationAnalysisBuilder> methodName(String regex)
    {
        BooleanOperatorDsl<MethodDeclarationAnalysisBuilder> builder = new MethodDeclarationAnalysisBuilder().methodName(
                regex
        );
        return builder;
    }


}
