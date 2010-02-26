package org.langera.freud.javasource.block;

import org.apache.commons.jxpath.JXPathContext;
import org.jdom.Attribute;
import org.jdom.DataConversionException;
import org.jdom.Element;
import org.langera.freud.javasource.classdecl.ClassDeclaration;
import org.langera.freud.javasource.method.MethodCall;
import org.langera.freud.javasource.method.MethodCallJdom;
import org.langera.freud.javasource.method.MethodDeclaration;
import org.langera.freud.util.parser.JdomTreeAdaptor;
import org.langera.freudgenerated.javasource.parser.JavaSourceTokenType;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *   This file is part of "Freud".
 *
 *   Freud is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU Lesser General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   Freud is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with Freud.  If not, see <http://www.gnu.org/licenses/>.
 *
 *   @author Amir Langer  langera_at_gmail_dot_com
**/

public final class CodeBlockJdom implements CodeBlock
{
    private MethodDeclaration methodDeclaration;
    private ClassDeclaration classDeclaration;
    private Type codeBlockType;
    private Element codeBlockElement;
    private Map<String, List<MethodCall>> methodCallByMethodNameMap;

    private enum Type
    {
        METHOD_IMPL,
        STATIC_BLOCK
    }

    public static CodeBlockJdom createStaticBlock(Element codeBlockElement, ClassDeclaration classDeclaration)
    {
        return new CodeBlockJdom(codeBlockElement, Type.STATIC_BLOCK, null, classDeclaration);
    }

    public static CodeBlockJdom createMethodImplementation(Element codeBlockElement,
                                                           MethodDeclaration methodDeclaration, ClassDeclaration classDeclaration)
    {
        return new CodeBlockJdom(codeBlockElement, Type.METHOD_IMPL, methodDeclaration, classDeclaration);
    }

    private CodeBlockJdom(Element codeBlockElement, Type codeBlockType,
                          MethodDeclaration methodDeclaration, ClassDeclaration classDeclaration)
    {
        this.codeBlockElement = codeBlockElement;
        this.codeBlockType = codeBlockType;
        this.methodDeclaration = methodDeclaration;
        this.classDeclaration = classDeclaration;
    }

    public boolean isStaticBlock()
    {
        return codeBlockType == Type.STATIC_BLOCK;
    }

    public boolean isMethodImplementation()
    {
        return codeBlockType == Type.METHOD_IMPL;
    }

    @SuppressWarnings("unchecked")
    public List<MethodCall> getMethodCallListByMethodName(String methodName)
    {
        if (methodCallByMethodNameMap == null)
        {
            methodCallByMethodNameMap = new HashMap<String, List<MethodCall>>();
            JXPathContext context = JXPathContext.newContext(codeBlockElement);
            List<Element> methodCallElementList =
                    context.selectNodes("//" + JavaSourceTokenType.METHOD_CALL.getName());
            for (Element methodCallElement : methodCallElementList)
            {
                final MethodCall methodCall = new MethodCallJdom(methodCallElement);
                List<MethodCall> methodCallList = methodCallByMethodNameMap.get(methodCall.getMethodName());
                if (methodCallList == null)
                {
                    methodCallList = new LinkedList<MethodCall>();
                    methodCallByMethodNameMap.put(methodCall.getMethodName(), methodCallList);
                }
                methodCallList.add(methodCall);
            }
        }
        return methodCallByMethodNameMap.get(methodName);
    }

    @SuppressWarnings("unchecked")
    public int getNumberOfLines()
    {
        Set<Integer> lineNumberSet = new HashSet<Integer>();
        countLines((List<Element>)codeBlockElement.getChildren(), lineNumberSet);
        return lineNumberSet.size();
    }

    public MethodDeclaration getMethodDeclaration()
    {
        return methodDeclaration;
    }

    public ClassDeclaration getClassDeclaration()
    {
        return classDeclaration;
    }

    public String toString()
    {
        String context = (isStaticBlock()) ?
                classDeclaration.toString() :
                methodDeclaration.toString();
        return codeBlockType + ":" + context;
    }

    @SuppressWarnings("unchecked")
    private static void countLines(final List<Element> elementList, Set<Integer> lineNumberSet)
    {
        for (Element element : elementList)
        {
            final Attribute lineNumberAttribute = element.getAttribute(JdomTreeAdaptor.LINE_NUMBER_ATTR);
            try
            {
                if (lineNumberAttribute != null)
                {
                    lineNumberSet.add(lineNumberAttribute.getIntValue());
                }
                countLines((List<Element>)element.getChildren(), lineNumberSet);
            } catch (DataConversionException e)
            {
                throw new IllegalStateException("LineNumber attribute value [" + lineNumberAttribute + "] not parsed to int");
            }
        }
    }
}
