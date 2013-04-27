package org.freud.analysed.javasource;

import java.util.List;

public interface JavaSource {
    PackageDeclaration getPackageDeclaration();

    List<ImportDeclaration> getImportDeclarations();

    ClassDeclaration getClassDeclaration();
//    InterfaceDeclaration getInterfaceDeclaration();
//    EnumDeclaration getEnumDeclaration();
//    AnnotationDeclaration getAnnotationDeclaration();

    String getFullClassName();

    String getSimpleClassName();

    String getFileName();
}
