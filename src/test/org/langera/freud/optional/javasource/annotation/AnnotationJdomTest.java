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

package org.langera.freud.optional.javasource.annotation;

import org.jdom.Element;
import org.jdom.filter.ElementFilter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.langera.freud.optional.javasource.JavaSourceJdom;
import org.langera.freud.optional.javasource.parser.JavaSourceTokenType;

import java.io.StringReader;
import java.util.Iterator;

public final class AnnotationJdomTest
{
    private static final String SIMPLE_CLASS_EXAMPLE =
                "package org.langera.examples;\n" +
                        " \n" +
                        "@NoKey\n" +
                        "public class SimpleClass \n" +
                        "{ \n" +
                        "  @WithDefaultKey(\"value\")\n" +
                        "  @WithDefaultArray({\"value1\", \"value2\"})\n" +
                        "  @Key( key = \"keyedValue\")\n" +
                        "  @MultipleKey( key1 = \"keyedValue\", key2 = 17)\n" +
                        "  public String toString()\n" +
                        "  {\n" +
                        "       int a1 = 1;\n" +
                        "  }" +
                        "}";
    private Element noKeyAnnotationElement;
    private Element defaultKeyAnnotationElement;
    private Element arrayDefaultKeyAnnotationElement;
    private Element keyedAnnotationElement;
    private Element multipleKeyedAnnotationElement;

    @Test
    public void shouldParseAnnotationWithNoKey() throws Exception
    {
        AnnotationJdom annotationJdom = new AnnotationJdom(noKeyAnnotationElement);

        Assert.assertEquals("NoKey", annotationJdom.getName());
        Assert.assertNull(annotationJdom.getDefaultParameter());
        Assert.assertTrue(annotationJdom.getParameterMap().isEmpty());
    }


    @Test
    public void shouldParseAnnotationWithKey() throws Exception
    {
        AnnotationJdom annotationJdom = new AnnotationJdom(keyedAnnotationElement);

        Assert.assertEquals("Key", annotationJdom.getName());
        Assert.assertNull(annotationJdom.getDefaultParameter());
        Assert.assertEquals(1, annotationJdom.getParameterMap().size());
        Assert.assertEquals("\"keyedValue\"", annotationJdom.getParameterMap().get("key"));
    }

    @Test
    public void shouldParseAnnotationWithMultipleKeys() throws Exception
    {
        AnnotationJdom annotationJdom = new AnnotationJdom(multipleKeyedAnnotationElement);

        Assert.assertEquals("MultipleKey", annotationJdom.getName());
        Assert.assertNull(annotationJdom.getDefaultParameter());
        Assert.assertEquals(2, annotationJdom.getParameterMap().size());
        Assert.assertEquals("\"keyedValue\"", annotationJdom.getParameterMap().get("key1"));
        Assert.assertEquals("17", annotationJdom.getParameterMap().get("key2"));
    }

    @Test
    public void shouldParseAnnotationWithDefaultValue() throws Exception
    {
        AnnotationJdom annotationJdom = new AnnotationJdom(defaultKeyAnnotationElement);

        Assert.assertEquals("WithDefaultKey", annotationJdom.getName());
        Assert.assertEquals("\"value\"", annotationJdom.getDefaultParameter());
        Assert.assertTrue(annotationJdom.getParameterMap().isEmpty());
    }


    @Test
    public void shouldParseAnnotationWithDefaultArrayValue() throws Exception
    {
        AnnotationJdom annotationJdom = new AnnotationJdom(arrayDefaultKeyAnnotationElement);

        Assert.assertEquals("WithDefaultArray", annotationJdom.getName());
        Assert.assertEquals("{\"value1\",\"value2\"}", annotationJdom.getDefaultParameter());
        Assert.assertTrue(annotationJdom.getParameterMap().isEmpty());
    }

   @Before
    public void setUp() throws Exception
   {
       JavaSourceJdom javaSourceJdom = new JavaSourceJdom(new StringReader(SIMPLE_CLASS_EXAMPLE), "Name");


       Iterator iterator = javaSourceJdom.getDocument().
               getDescendants(new ElementFilter(JavaSourceTokenType.AT.getName()));

       while (iterator.hasNext())
       {
           Element element = (Element) iterator.next();
           if ("NoKey".equals(element.getAttributeValue("id")))
           {
               noKeyAnnotationElement = element;
           }
           else if ("WithDefaultKey".equals(element.getAttributeValue("id")))
           {
               defaultKeyAnnotationElement = element;
           }
           else if ("WithDefaultArray".equals(element.getAttributeValue("id")))
           {
               arrayDefaultKeyAnnotationElement = element;
           }
           else if ("Key".equals(element.getAttributeValue("id")))
           {
               keyedAnnotationElement = element;
           }
           else if ("MultipleKey".equals(element.getAttributeValue("id")))
           {
               multipleKeyedAnnotationElement = element;
           }
           else
           {
               throw new IllegalStateException(element.toString());
           }
       }

    }
}
