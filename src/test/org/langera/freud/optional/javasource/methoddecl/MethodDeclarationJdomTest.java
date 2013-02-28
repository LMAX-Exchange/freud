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

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.filter.ElementFilter;
import org.junit.Before;
import org.junit.Test;
import org.langera.freud.optional.javasource.JavaSourceJdom;
import org.langera.freud.optional.javasource.block.CodeBlock;
import org.langera.freud.optional.javasource.parser.JavaSourceTokenType;

import java.io.StringReader;
import java.util.Iterator;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MethodDeclarationJdomTest
{

    private static final String CLASS_EXAMPLE =
            "package org.langera.examples;" +
                    " " +
                    "public class SimpleClass " +
                    "{ " +
                    " " +
                    "  public String toString()" +
                    "  {" +
                    "       return this.getClass();" +
                    "  }" +
                    " " +
                    "  public byte primitiveByte()" +
                    "  {" +
                    "       return 0;" +
                    "  }" +
                    " " +
                    "  public short primitiveShort()" +
                    "  {" +
                    "       return 0;" +
                    "  }" +
                    " " +
                    "  public int primitiveInt()" +
                    "  {" +
                    "       return 0;" +
                    "  }" +
                    " " +
                    "  public long primitiveLong()" +
                    "  {" +
                    "       return 0;" +
                    "  }" +
                    " " +
                    "  public float primitiveFloat()" +
                    "  {" +
                    "       return 0;" +
                    "  }" +
                    " " +
                    "  public double primitiveDouble()" +
                    "  {" +
                    "       return 0;" +
                    "  }" +
                    " " +
                    "  public char primitiveChar()" +
                    "  {" +
                    "       return 'a';" +
                    "  }" +
                    " " +
                    "  public boolean primitiveBoolean()" +
                    "  {" +
                    "       return true;" +
                    "  }" +
                    "  public void voidMethod()" +
                    "  {" +
                    "       return true;" +
                    "  }" +
                    " " +
                    "}";
    private Element methodDeclToString;
    private Element methodDeclPrimitiveByte;
    private Element methodDeclPrimitiveShort;
    private Element methodDeclPrimitiveInt;
    private Element methodDeclPrimitiveLong;
    private Element methodDeclPrimitiveFloat;
    private Element methodDeclPrimitiveDouble;
    private Element methodDeclPrimitiveChar;
    private Element methodDeclPrimitiveBoolean;
    private Element methodDeclVoidMethod;
    
    @Test
    public void shouldParseMethodImplementation() throws Exception
    {
        MethodDeclarationJdom methodDeclarationJdom = new MethodDeclarationJdom(methodDeclToString, null);

        assertEquals("toString", methodDeclarationJdom.getName());
        assertEquals("String", methodDeclarationJdom.getReturnType());

        final CodeBlock impl = methodDeclarationJdom.getImplementation();
        assertNotNull(impl);
        assertEquals(1, impl.getMethodCallListByMethodName("getClass").size());
    }
    
    @Test
    public void shouldParseMethodImplementationWithPrimitiveReturnedTypes() throws Exception
    {
    	MethodDeclarationJdom primitiveByteMethodDeclaration = new MethodDeclarationJdom(methodDeclPrimitiveByte, null);
    	MethodDeclarationJdom primitiveShortMethodDeclaration = new MethodDeclarationJdom(methodDeclPrimitiveShort, null);
    	MethodDeclarationJdom primitiveIntMethodDeclaration = new MethodDeclarationJdom(methodDeclPrimitiveInt, null);
    	MethodDeclarationJdom primitiveLongMethodDeclaration = new MethodDeclarationJdom(methodDeclPrimitiveLong, null);
    	MethodDeclarationJdom primitiveFloatMethodDeclaration = new MethodDeclarationJdom(methodDeclPrimitiveFloat, null);
    	MethodDeclarationJdom primitiveDoubleMethodDeclaration = new MethodDeclarationJdom(methodDeclPrimitiveDouble, null);
    	MethodDeclarationJdom primitiveCharMethodDeclaration = new MethodDeclarationJdom(methodDeclPrimitiveChar, null);
        MethodDeclarationJdom primitiveBooleanMethodDeclaration = new MethodDeclarationJdom(methodDeclPrimitiveBoolean, null);

        assertEquals("primitiveByte", primitiveByteMethodDeclaration.getName());
        assertEquals("byte", primitiveByteMethodDeclaration.getReturnType());
        
        assertEquals("primitiveShort", primitiveShortMethodDeclaration.getName());
        assertEquals("short", primitiveShortMethodDeclaration.getReturnType());
        
        assertEquals("primitiveInt", primitiveIntMethodDeclaration.getName());
        assertEquals("int", primitiveIntMethodDeclaration.getReturnType());
        
        assertEquals("primitiveLong", primitiveLongMethodDeclaration.getName());
        assertEquals("long", primitiveLongMethodDeclaration.getReturnType());
        
        assertEquals("primitiveFloat", primitiveFloatMethodDeclaration.getName());
        assertEquals("float", primitiveFloatMethodDeclaration.getReturnType());
        
        assertEquals("primitiveDouble", primitiveDoubleMethodDeclaration.getName());
        assertEquals("double", primitiveDoubleMethodDeclaration.getReturnType());
        
        assertEquals("primitiveChar", primitiveCharMethodDeclaration.getName());
        assertEquals("char", primitiveCharMethodDeclaration.getReturnType());
        
        assertEquals("primitiveBoolean", primitiveBooleanMethodDeclaration.getName());
        assertEquals("boolean", primitiveBooleanMethodDeclaration.getReturnType());
    }
    
    @Test
    public void shouldParseMethodImplementationWithVoidReturn() throws Exception
    {
    	MethodDeclarationJdom voidMethodDeclaration = new MethodDeclarationJdom(methodDeclVoidMethod, null);
        assertEquals("voidMethod", voidMethodDeclaration.getName());
        assertEquals("void", voidMethodDeclaration.getReturnType());
    }

    @Before
    public void setUp() throws Exception
    {
        JavaSourceJdom javaSourceJdom = new JavaSourceJdom(new StringReader(CLASS_EXAMPLE), "Name");

        Document document = javaSourceJdom.getDocument();
        System.out.println(document);
		Iterator methodsWithReturnedType = document.
                getDescendants(new ElementFilter(JavaSourceTokenType.FUNCTION_METHOD_DECL.getName()));
		methodDeclToString = (Element) methodsWithReturnedType.next();
		methodDeclPrimitiveByte = (Element) methodsWithReturnedType.next();
		methodDeclPrimitiveShort = (Element) methodsWithReturnedType.next();
		methodDeclPrimitiveInt = (Element) methodsWithReturnedType.next();
		methodDeclPrimitiveLong = (Element) methodsWithReturnedType.next();
		methodDeclPrimitiveFloat = (Element) methodsWithReturnedType.next();
		methodDeclPrimitiveDouble = (Element) methodsWithReturnedType.next();
		methodDeclPrimitiveChar = (Element) methodsWithReturnedType.next();
		methodDeclPrimitiveBoolean = (Element) methodsWithReturnedType.next();
		
		methodDeclVoidMethod=(Element)	document.
        getDescendants(new ElementFilter(JavaSourceTokenType.VOID_METHOD_DECL.getName())).next();
    }

}
