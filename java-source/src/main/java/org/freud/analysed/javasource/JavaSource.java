package org.freud.analysed.javasource;

public interface JavaSource {
    PackageDeclaration getPackageDeclaration();

    ImportDeclaration[] getImportDeclarations();

    ClassDeclaration getClassDeclaration();
//    InterfaceDeclaration getInterfaceDeclaration();
//    EnumDeclaration getEnumDeclaration();
//    AnnotationDeclaration getAnnotationDeclaration();

    String getFullClassName();

    String getSimpleClassName();

    String getFileName();
}
