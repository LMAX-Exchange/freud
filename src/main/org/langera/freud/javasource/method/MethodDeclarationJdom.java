package org.langera.freud.javasource.method;

import org.apache.commons.jxpath.JXPathContext;
import org.apache.commons.jxpath.JXPathException;
import org.jdom.Element;
import org.langera.freud.javasource.annotation.Annotation;
import org.langera.freud.javasource.annotation.AnnotationJdom;
import org.langera.freud.javasource.block.CodeBlock;
import org.langera.freud.javasource.block.CodeBlockJdom;
import org.langera.freud.javasource.classdecl.ClassDeclaration;
import org.langera.freud.util.parser.JdomTreeAdaptor;
import org.langera.freudgenerated.javasource.parser.JavaSourceTokenType;

import java.util.List;

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

public final class MethodDeclarationJdom implements MethodDeclaration
{
    private final Element methodDeclElement;
    private String name;
    private CodeBlock methodCodeBlock;
    private Annotation[] annotations;
    private ClassDeclaration classDeclaration;

    public MethodDeclarationJdom(Element methodDeclElement, ClassDeclaration classDeclaration)
    {
        this.methodDeclElement = methodDeclElement;
        this.name = methodDeclElement.getAttribute(JdomTreeAdaptor.ID_ATTR).getValue();
        this.classDeclaration = classDeclaration;
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

    public Annotation[] getDeclaredMethodAnnotations()
    {
        if (annotations == null)
        {
            JXPathContext context = JXPathContext.newContext(methodDeclElement);
            List annotationList = context.selectNodes("/" + JavaSourceTokenType.MODIFIER_LIST.getName() +
                                                "/" + JavaSourceTokenType.AT.getName());
            annotations = new Annotation[annotationList.size()];
            int i = 0;
            for (Object annotationElement : annotationList)
            {
                annotations[i++] = new AnnotationJdom((Element) annotationElement);


            }
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
