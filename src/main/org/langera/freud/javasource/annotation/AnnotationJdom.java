package org.langera.freud.javasource.annotation;

import org.apache.commons.jxpath.JXPathContext;
import org.jdom.Element;
import org.jdom.filter.ElementFilter;
import org.langera.freud.util.parser.JdomTreeAdaptor;
import org.langera.freudgenerated.javasource.parser.JavaSourceTokenType;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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

public final class AnnotationJdom implements Annotation
{
    private final Element annotationElement;
    private final String name;
    private final String defaultParameter;
    private final Map<String, String> parameterMap;

    @SuppressWarnings("unchecked")
    public AnnotationJdom(Element annotationElement)
    {
        this.annotationElement = annotationElement;
        this.name = annotationElement.getAttribute(JdomTreeAdaptor.ID_ATTR).getValue();
        final Iterator<Element> iterator = annotationElement.getDescendants(
                new ElementFilter(JavaSourceTokenType.ANNOTATION_INIT_DEFAULT_KEY.name()));
        if (iterator.hasNext())
        {
            defaultParameter = getAnnotationValueForElement(iterator.next());
            parameterMap = Collections.emptyMap();
        }
        else
        {
            defaultParameter = null;
            final Iterator<Element> keysIterator = annotationElement.getDescendants(
                            new ElementFilter(JavaSourceTokenType.ANNOTATION_INIT_KEY_LIST.name()));
            if (keysIterator.hasNext())
            {
                final List<Element> elementList = keysIterator.next().getChildren(JavaSourceTokenType.IDENT.name());
                parameterMap = new HashMap<String, String>((int)(elementList.size() * 1.5), 0.9f);
                for (Element element : elementList)
                {
                    String value = getAnnotationValueForElement(element);
                    final String key = element.getTextTrim();
                    parameterMap.put(key, value);
                }
            }
            else
            {
                parameterMap = Collections.emptyMap();
            }
        }
    }

    private String getAnnotationValueForElement(final Element element)
    {
        final JXPathContext context = JXPathContext.newContext(element);
        final Element expr = (Element) context.selectSingleNode("/" + JavaSourceTokenType.EXPR.name() + "/*");
        if (expr != null)
        {
            return expr.getText();
        }
        else
        {
            final List<Element> exprList = context.selectNodes("//" + JavaSourceTokenType.EXPR.name() + "/*");
            StringBuilder sb = new StringBuilder("{");
            for (Element item : exprList)
            {
                sb.append(item.getText()).append(",");
            }
            sb.setCharAt(sb.length() - 1, '}');
            return sb.toString();
        }
    }

    public String getName()
    {
        return name;
    }

    public String getDefaultParameter()
    {
        return defaultParameter;
    }

    public Map<String, String> getParameterMap()
    {
        return parameterMap;
    }
}
