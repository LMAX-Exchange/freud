package org.freud.analysed.javasource;


import java.util.List;
import java.util.Map;


public interface CodeBlock {

    boolean isStaticBlock();

    boolean isMethodImplementation();

    MethodDeclaration getMethodDeclaration();

    ClassDeclaration getClassDeclaration();

    List<MethodCall> getMethodCallListByMethodName(String methodName);

    int getNumberOfLines();

    @SuppressWarnings("unchecked")
    Map<String, List<MethodCall>> getMethodCallByMethodNameMap();
}
