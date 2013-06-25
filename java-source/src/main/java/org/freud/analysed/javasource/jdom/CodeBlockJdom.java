/*
 * Copyright 2013 LMAX Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.freud.analysed.javasource.jdom;

import org.apache.commons.jxpath.JXPathContext;
import org.freud.analysed.javasource.ClassDeclaration;
import org.freud.analysed.javasource.CodeBlock;
import org.freud.analysed.javasource.MethodCall;
import org.freud.analysed.javasource.MethodDeclaration;
import org.freud.analysed.javasource.parser.JavaSourceTokenType;
import org.jdom.Attribute;
import org.jdom.DataConversionException;
import org.jdom.Element;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.freud.core.parser.JdomTreeAdaptor.LINE_NUMBER_ATTR;


final class CodeBlockJdom implements CodeBlock {
    private MethodDeclaration methodDeclaration;
    private ClassDeclaration classDeclaration;
    private Type codeBlockType;
    private Element codeBlockElement;
    private Map<String, List<MethodCall>> methodCallByMethodNameMap;

    private enum Type {
        METHOD_IMPL,
        STATIC_BLOCK
    }

    public static CodeBlockJdom createStaticBlock(Element codeBlockElement, ClassDeclaration classDeclaration) {
        return new CodeBlockJdom(codeBlockElement, Type.STATIC_BLOCK, null, classDeclaration);
    }

    public static CodeBlockJdom createMethodImplementation(Element codeBlockElement,
                                                           MethodDeclaration methodDeclaration, ClassDeclaration classDeclaration) {
        return new CodeBlockJdom(codeBlockElement, Type.METHOD_IMPL, methodDeclaration, classDeclaration);
    }

    private CodeBlockJdom(Element codeBlockElement, Type codeBlockType,
                          MethodDeclaration methodDeclaration, ClassDeclaration classDeclaration) {
        this.codeBlockElement = codeBlockElement;
        this.codeBlockType = codeBlockType;
        this.methodDeclaration = methodDeclaration;
        this.classDeclaration = classDeclaration;
    }

    public boolean isStaticBlock() {
        return codeBlockType == Type.STATIC_BLOCK;
    }

    public boolean isMethodImplementation() {
        return codeBlockType == Type.METHOD_IMPL;
    }

    public List<MethodCall> getMethodCallListByMethodName(String methodName) {
        return getMethodCallByMethodNameMap().get(methodName);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Map<String, List<MethodCall>> getMethodCallByMethodNameMap() {
        if (methodCallByMethodNameMap == null) {
            methodCallByMethodNameMap = new HashMap<String, List<MethodCall>>();
            JXPathContext context = JXPathContext.newContext(codeBlockElement);
            List<Element> methodCallElementList =
                    context.selectNodes("//" + JavaSourceTokenType.METHOD_CALL.getName());
            for (Element methodCallElement : methodCallElementList) {
                final MethodCall methodCall = new MethodCallJdom(methodCallElement);
                List<MethodCall> methodCallList = methodCallByMethodNameMap.get(methodCall.getMethodName());
                if (methodCallList == null) {
                    methodCallList = new LinkedList<MethodCall>();
                    methodCallByMethodNameMap.put(methodCall.getMethodName(), methodCallList);
                }
                methodCallList.add(methodCall);
            }
        }
        return methodCallByMethodNameMap;
    }

    @SuppressWarnings("unchecked")
    public int getNumberOfLines() {
        Set<Integer> lineNumberSet = new HashSet<Integer>();
        countLines((List<Element>) codeBlockElement.getChildren(), lineNumberSet);
        return lineNumberSet.size();
    }

    public MethodDeclaration getMethodDeclaration() {
        return methodDeclaration;
    }

    public ClassDeclaration getClassDeclaration() {
        return classDeclaration;
    }

    public String toString() {
        String context = (isStaticBlock()) ?
                         classDeclaration.toString() :
                         methodDeclaration.toString();
        return codeBlockType + ":" + context;
    }

    @SuppressWarnings("unchecked")
    private static void countLines(final List<Element> elementList, Set<Integer> lineNumberSet) {
        for (Element element : elementList) {
            final Attribute lineNumberAttribute = element.getAttribute(LINE_NUMBER_ATTR);
            try {
                if (lineNumberAttribute != null) {
                    lineNumberSet.add(lineNumberAttribute.getIntValue());
                }
                countLines((List<Element>) element.getChildren(), lineNumberSet);
            }
            catch (DataConversionException e) {
                throw new IllegalStateException("LineNumber attribute value [" + lineNumberAttribute + "] not parsed to int");
            }
        }
    }
}
