package org.freud.analysed.javasource;

import org.freud.analysed.javasource.jdom.JavaSourceJdomFromFileCreator;
import org.freud.core.FreudSource;
import org.freud.core.iterator.AnalysedObjects;
import org.freud.core.iterator.SubTypeAnalysedObjects;

import java.io.File;

public final class JavaSourceDsl {

    private JavaSourceDsl() {
        // static utility
    }

    public static Iterable<JavaSource> javaSourceOf(Iterable<File> iterable) {
        return javaSourceOf(new FreudSource<File>(iterable, File.class));
    }

    public static Iterable<JavaSource> javaSourceOf(FreudSource<File> source) {
        return new AnalysedObjects<File, JavaSource>(new JavaSourceJdomFromFileCreator(), source.getSources());
    }

    public static Iterable<ClassDeclaration> classDeclarationsWithin(Iterable<JavaSource> javaSources) {
        return new SubTypeAnalysedObjects<JavaSource, ClassDeclaration>(new JavaSourceToClassDeclarationsCreator(), javaSources);
    }

    public static Iterable<ImportDeclaration> importDeclarationsWithin(Iterable<JavaSource> javaSources) {
        return new SubTypeAnalysedObjects<JavaSource, ImportDeclaration>(new JavaSourceToImportDeclarationsCreator(), javaSources);
    }

    public static Iterable<PackageDeclaration> packageDeclarationsWithin(Iterable<JavaSource> javaSources) {
        return new AnalysedObjects<JavaSource, PackageDeclaration>(new JavaSourceToPackageDeclarationCreator(), javaSources);
    }

    public static Iterable<MethodDeclaration> methodDeclarationsWithin(Iterable<ClassDeclaration> classDeclarations) {
        return new SubTypeAnalysedObjects<ClassDeclaration, MethodDeclaration>(new ClassDeclarationToMethodDeclarationsCreator(), classDeclarations);
    }

    public static Iterable<VarDeclaration> fieldDeclarationsWithin(Iterable<ClassDeclaration> classDeclarations) {
        return new SubTypeAnalysedObjects<ClassDeclaration, VarDeclaration>(new ClassDeclarationToFieldDeclarationsCreator(), classDeclarations);
    }

    // TODO - code blocks within classes (methods + static blocks) etc.

    public static Iterable<CodeBlock> codeBlocksWithin(Iterable<MethodDeclaration> methodDeclarations) {
        return new AnalysedObjects<MethodDeclaration, CodeBlock>(new MethodDeclarationToCodeBlockCreator(), methodDeclarations);
    }

    public static Iterable<ParamDeclaration> paramDeclarationsOf(Iterable<MethodDeclaration> methodDeclarations) {
        return new SubTypeAnalysedObjects<MethodDeclaration, ParamDeclaration>(new MethodDeclarationToParamDeclarationsCreator(), methodDeclarations);
    }

    public static Iterable<MethodCall> methodCallsWithin(Iterable<CodeBlock> codeBlocks) {
        return new SubTypeAnalysedObjects<CodeBlock, MethodCall>(new CodeBlockToMethodCallsCreator(), codeBlocks);
    }

}
