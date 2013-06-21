package org.freud.analysed.javasource;


import java.util.List;
import java.util.Map;


public interface ClassDeclaration {
    public enum DeclarationType {
        CLASS, INTERFACE, ENUM, ANNOTATION
    }

    List<String> getDeclaredClassAnnotations();

    DeclarationType getDeclarationType();

    String getName();

    String getSuperClassName();

    String[] getDeclaredImplementedInterfaceNames();

    long getModifierMask();

    //    CodeBlock getStaticBlock();

    Map<String, List<MethodDeclaration>> getMethodDeclarationListByNameMap();

    List<VarDeclaration> getFieldDeclarations();

    Map<String, ClassDeclaration> getInnerClassDeclarationByNameMap();

    ClassDeclaration getOuterClassDeclaration();
//    AnnotationDeclaration[] getInnerAnnotationDeclarations();    
}
