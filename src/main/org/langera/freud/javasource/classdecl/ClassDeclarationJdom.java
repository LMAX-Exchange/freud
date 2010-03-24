package org.langera.freud.javasource.classdecl;

import org.apache.commons.jxpath.JXPathContext;
import org.jdom.Element;
import org.langera.freud.javasource.JavaSourceJdom;
import org.langera.freud.javasource.methoddecl.MethodDeclaration;
import org.langera.freud.javasource.methoddecl.MethodDeclarationJdom;
import org.langera.freud.util.parser.JdomTreeAdaptor;
import org.langera.freudgenerated.javasource.parser.JavaSourceTokenType;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * This file is part of "Freud".
 * <p/>
 * Freud is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p/>
 * Freud is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU General Public License
 * along with Freud.  If not, see <http://www.gnu.org/licenses/>.
 *
 * @author Amir Langer  langera_at_gmail_dot_com
 */

public final class ClassDeclarationJdom implements ClassDeclaration
{
    private final Element classDeclElement;
    private String name;
    private Map<String, List<MethodDeclaration>> methodDeclarationListByNameMap;
    private Map<String, ClassDeclaration> innerClassDeclarationByNameMap;
    private DeclarationType declarationType;

    public ClassDeclarationJdom(Element classDeclElement, DeclarationType declarationType)
    {
        this.classDeclElement = classDeclElement;
        this.declarationType = declarationType;
    }

    public String[] getDeclaredClassAnnotations()
    {
        // TODO
        return new String[0];
    }

    public DeclarationType getDeclarationType()
    {
        return declarationType;
    }

    public String[] getDeclaredImplementedInterfaceNames()
    {
        // TODO
        return new String[0];
    }

    //  TODO  VarDeclaration[] getFieldDeclarations();

    @SuppressWarnings("unchecked")
    public Map<String, ClassDeclaration> getInnerClassDeclarationByNameMap()
    {
        if (innerClassDeclarationByNameMap == null)
        {
            JXPathContext context = JXPathContext.newContext(classDeclElement);
            for (JavaSourceTokenType tokenType : JavaSourceJdom.POSSIBLE_CLASS_DECLARATION_TYPES)
            {
                final String tokenName = tokenType.getName();
                List<Element> innerClassElementList =
                        context.selectNodes("/" + JavaSourceTokenType.CLASS.getName() + "/" + tokenName);
                innerClassDeclarationByNameMap = new HashMap<String, ClassDeclaration>();
                for (Element innerClassElement : innerClassElementList)
                {
                    ClassDeclaration innerClass = new ClassDeclarationJdom(innerClassElement, DeclarationType.valueOf(tokenName));
                    innerClassDeclarationByNameMap.put(innerClass.getName(), innerClass);
                }
            }
        }
        return innerClassDeclarationByNameMap;
    }

    // TODO   Block getStaticBlock();


    @SuppressWarnings("unchecked")
    public Map<String, List<MethodDeclaration>> getMethodDeclarationListByNameMap()
    {
        if (methodDeclarationListByNameMap == null)
        {
            JXPathContext context = JXPathContext.newContext(classDeclElement);
            List<Element> methodDeclElementList =
                    context.selectNodes("//" + JavaSourceTokenType.FUNCTION_METHOD_DECL.getName());
            methodDeclarationListByNameMap = new HashMap<String, List<MethodDeclaration>>();
            for (Element methodElement : methodDeclElementList)
            {
                MethodDeclaration methodDeclaration = new MethodDeclarationJdom(methodElement, this);
                final String name = methodDeclaration.getName();
                List<MethodDeclaration> methodDeclarationList = methodDeclarationListByNameMap.get(name);
                if (methodDeclarationList == null)
                {
                    methodDeclarationList = new LinkedList<MethodDeclaration>();
                    methodDeclarationListByNameMap.put(name, methodDeclarationList);
                }
                methodDeclarationList.add(methodDeclaration);

            }
        }
        return methodDeclarationListByNameMap;
    }

    public long getModifierMask()
    {
        // TODO
        return 0;
    }

    public String getName()
    {
        if (name == null)
        {
            name = classDeclElement.getAttribute(JdomTreeAdaptor.ID_ATTR).getValue();
        }
        return name;
    }

    public String getSuperclassName()
    {
        // TODO        
        return null;
    }

    @Override
    public String toString()
    {
        return getName();
    }
}
