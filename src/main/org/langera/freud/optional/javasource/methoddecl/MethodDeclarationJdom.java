/*
 * Copyright (c) 2011.
 * This file is part of "Freud".
 *
 * Freud is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Freud is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Freud.  If not, see <http://www.gnu.org/licenses/>.
 * @author Amir Langer  langera_at_gmail_dot_com
 */

package org.langera.freud.optional.javasource.methoddecl;

import org.apache.commons.jxpath.JXPathContext;
import org.apache.commons.jxpath.JXPathException;
import org.jdom.Element;
import org.langera.freud.optional.javasource.JavaSourceJdom;
import org.langera.freud.optional.javasource.annotation.Annotation;
import org.langera.freud.optional.javasource.block.CodeBlock;
import org.langera.freud.optional.javasource.block.CodeBlockJdom;
import org.langera.freud.optional.javasource.classdecl.ClassDeclaration;
import org.langera.freud.optional.javasource.parser.JavaSourceTokenType;

import java.util.List;


public final class MethodDeclarationJdom implements MethodDeclaration
{
    private final Element methodDeclElement;
    private final String name;
    private CodeBlock methodCodeBlock;
    private Annotation[] annotations;
    private ClassDeclaration classDeclaration;
    private final String returnType;
    private ParamDeclaration[] paramDeclarations;

    public MethodDeclarationJdom(Element methodDeclElement, ClassDeclaration classDeclaration)
    {
        this.methodDeclElement = methodDeclElement;
        this.name = JavaSourceJdom.parseName(methodDeclElement);
        this.returnType = JavaSourceJdom.parseType(methodDeclElement);
        this.classDeclaration = classDeclaration;
    }

    @Override
    public String getReturnType()
    {
        return returnType;
    }

    public String getName()
    {
        return name;
    }

    public CodeBlock getImplementation()
    {
        if (methodCodeBlock == null)
        {
            try
            {
                JXPathContext context = JXPathContext.newContext(methodDeclElement);
                Element codeBlockElement = (Element) context.selectSingleNode("/" + JavaSourceTokenType.BLOCK_SCOPE.getName());
                methodCodeBlock = CodeBlockJdom.createMethodImplementation(codeBlockElement, this, classDeclaration);
            }
            catch (JXPathException e)
            {
                return null;
            }
        }
        return methodCodeBlock;
    }

    @Override
    @SuppressWarnings("unchecked")
    public ParamDeclaration[] getParametersDeclarations()
    {
        if (paramDeclarations == null)
        {
            final List<Element> paramListChildren =
                    methodDeclElement.getChild(JavaSourceTokenType.FORMAL_PARAM_LIST.getName()).getChildren();
            paramDeclarations = new ParamDeclaration[paramListChildren.size()];
            int i = 0;
            for (Element paramDecl : paramListChildren)
            {
                paramDeclarations[i++] = new ParamDeclarationJdom(paramDecl);
            }
        }
        return paramDeclarations;
    }

    public Annotation[] getDeclaredAnnotations()
    {
        if (annotations == null)
        {
            annotations = JavaSourceJdom.parseAnnotations(methodDeclElement);
        }
        return annotations;

    }

    public long getModifierMask()
    {
        // TODO
        return 0;
    }

    public String toString()
    {
        return classDeclaration + "." + name;
    }
}
