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

package org.langera.freud.optional.classfile.method;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.langera.freud.core.iterator.resource.FileResource;
import org.langera.freud.core.matcher.FreudMatcher;
import org.langera.freud.optional.classfile.ClassFile;
import org.langera.freud.optional.classfile.parser.asm.AsmClassFileParser;
import org.langera.freud.optional.classfile.parser.asm.AsmClassFileParserTest;

import static org.langera.freud.optional.classfile.method.ClassFileMethodDsl.aConstantOf;
import static org.langera.freud.optional.classfile.method.ClassFileMethodDsl.castOf;
import static org.langera.freud.optional.classfile.method.ClassFileMethodDsl.methodInvokedWithParams;
import static org.langera.freud.optional.classfile.method.ClassFileMethodDsl.a;

public final class ClassFileMethodDslTest
{

    private ClassFile testClass;

    @Test
    public void shouldMatchInvokedStaticMethodWithPrimitiveParam()
    {
        final FreudMatcher<ClassFileMethod> matcher =
                methodInvokedWithParams(String.class, "valueOf", a(int.class));

        Assert.assertTrue(matcher.matches(getMethod("staticMethodInvocationWithPrimitiveParam")));
    }


    @Test
    public void shouldMatchInvokedStaticMethodWithArrayParam()
    {
        final FreudMatcher<ClassFileMethod> matcher =
                methodInvokedWithParams(String.class, "valueOf", a(int[].class));

        Assert.assertTrue(matcher.matches(getMethod("staticMethodInvocationWithArrayParam")));
    }

    @Test
    public void shouldMatchInvokedStaticMethodWithArrayElement()
    {
        final FreudMatcher<ClassFileMethod> matcher =
                methodInvokedWithParams(String.class, "valueOf", a(String.class));

        Assert.assertTrue(matcher.matches(getMethod("methodInvocationWithArrayElement")));
    }

    @Test
    public void shouldMatchInvokedStaticMethodWithField()
    {
        final FreudMatcher<ClassFileMethod> matcher =
                methodInvokedWithParams(String.class, "valueOf", a(String.class));

        Assert.assertTrue(matcher.matches(getMethod("methodInvocationWithField")));
    }

    @Test
    public void shouldMatchInvokedStaticMethodWithObjectParam()
    {
        final FreudMatcher<ClassFileMethod> matcher =
                methodInvokedWithParams(String.class, "valueOf", a(Object.class));

        Assert.assertTrue(matcher.matches(getMethod("staticMethodInvocationWithObjectParam")));
    }


    @Test
    public void shouldMatchInvokedVirtualMethodWithIntConstant()
    {
        final FreudMatcher<ClassFileMethod> matcher =
                methodInvokedWithParams(String.class, "substring", aConstantOf(int.class));

        Assert.assertTrue(matcher.matches(getMethod("methodInvocationWithIntConstant")));
    }

    @Test
    public void shouldMatchInvokedVirtualMethodWithLongCastToInt()
    {
        final FreudMatcher<ClassFileMethod> matcher =
                methodInvokedWithParams(String.class, "substring", castOf(long.class));

        Assert.assertTrue(matcher.matches(getMethod("methodInvocationWithLongConstant")));
    }

    @Test
    public void shouldNotMatchInvokedMethod()
    {
        final FreudMatcher<ClassFileMethod> matcher =
                methodInvokedWithParams(String.class, "toString");

        Assert.assertFalse(matcher.matches(getMethod("<init>")));
    }

    @Before
    public void setUp() throws Exception
    {
        testClass = new AsmClassFileParser(null).parseResource(
                AsmClassFileParserTest.getFileResourceIdentifier("org.langera.freud.optional.classfile.method.ClassFileMethodDslTest"),
                FileResource.getInstance());
    }


    private ClassFileMethod getMethod(final String name)
    {
        for (ClassFileMethod method : testClass.getMethodList())
        {
            if (name.equals(method.getName()))
            {
                return method;
            }
        }
        return null;
    }

    /////////////////////////////////////////////////////
    // Methods used for testing

    private String staticMethodInvocationWithPrimitiveParam(final int i)
    {
        return String.valueOf(i);
    }

    private String staticMethodInvocationWithObjectParam(final Object o)
    {
        return String.valueOf(o);
    }

    private String staticMethodInvocationWithArrayParam(final int[] array)
    {
        return String.valueOf(array);
    }

    private String methodInvocationWithIntConstant()
    {
        return "xxx".substring(1);
    }

    private String methodInvocationWithLongConstant()
    {
        long l = 1L;
        return "xxx".substring((int)l);
    }

    private String methodInvocationWithArrayElement(final String[][] array, final int i)
    {
        return String.valueOf(array[7-i][8]);
    }

    private String methodInvocationWithField()
    {
        return String.valueOf(testClass.getName());
    }
}
