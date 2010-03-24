package org.langera.freud.javasource.methodcall;

import org.apache.commons.jxpath.JXPathContext;
import org.jdom.Attribute;
import org.jdom.Element;
import org.langera.freud.javasource.JavaSourceJdomElementComparator;
import org.langera.freud.util.parser.JdomTreeAdaptor;
import org.langera.freudgenerated.javasource.parser.JavaSourceTokenType;

import java.util.Collections;
import java.util.List;

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

public final class MethodCallJdom implements MethodCall
{
    private static final String[] EMPTY_ARRAY = new String[0];

    private Element methodCallElement;
    private String methodName;
    private String[] instanceReferences;

    public MethodCallJdom(Element methodCallElement)
    {
        this.methodCallElement = methodCallElement;
    }

    @SuppressWarnings("unchecked")
    public String getMethodName()
    {
        if (methodName == null)
        {
            final Attribute idAttr = methodCallElement.getAttribute(JdomTreeAdaptor.ID_ATTR);
            if (idAttr != null)
            {
                methodName = idAttr.getValue();
                instanceReferences = EMPTY_ARRAY;
            }
            else
            {
                JXPathContext context = JXPathContext.newContext(methodCallElement);
                final List<Element> identElementList = context.selectNodes("//" + JavaSourceTokenType.IDENT.getName());

                Collections.sort(identElementList, JavaSourceJdomElementComparator.getInstance());

                final int methodNameIndex = identElementList.size() - 1;
                methodName = identElementList.get(methodNameIndex).getText();
                instanceReferences = new String[methodNameIndex];
                for (int i = 0, size = instanceReferences.length; i < size; i++)
                {
                    instanceReferences[i] = identElementList.get(i).getText();
                }
            }
        }
        return methodName;
    }

    public String[] getInstanceReferences()
    {
        return instanceReferences;
    }
}
