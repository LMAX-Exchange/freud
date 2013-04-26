package org.freud.analysed.javasource;


import java.util.List;


public interface CodeBlock {

    boolean isStaticBlock();

    boolean isMethodImplementation();

    MethodDeclaration getMethodDeclaration();

    ClassDeclaration getClassDeclaration();

    List<MethodCall> getMethodCallListByMethodName(String methodName);

    int getNumberOfLines();
}
