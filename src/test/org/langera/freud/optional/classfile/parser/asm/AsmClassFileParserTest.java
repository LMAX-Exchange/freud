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

package org.langera.freud.optional.classfile.parser.asm;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.langera.freud.core.iterator.resource.FileResource;
import org.langera.freud.core.iterator.resource.ResourceParserException;
import org.langera.freud.optional.classfile.ClassFile;
import org.langera.freud.optional.classfile.ClassFileField;
import org.langera.freud.optional.classfile.method.ClassFileMethod;

import java.io.IOException;
import java.io.Serializable;

public final class AsmClassFileParserTest implements Serializable
{

    private AsmClassFileParser parser;

    @Test
    public void shouldParseResource() throws IOException, ResourceParserException
    {
        final ClassFile classFile = parser.parseResource(getFileResourceIdentifier("org.langera.freud.optional.classfile.parser.asm.AsmClassFileParserTest"),
                FileResource.getInstance());

        Assert.assertEquals("org.langera.freud.optional.classfile.parser.asm.AsmClassFileParserTest", classFile.getName());
        Assert.assertEquals("java.lang.Object", classFile.getSuperName());
        Assert.assertArrayEquals(new String[]{"java.io.Serializable"}, classFile.getInterfaces());

        Assert.assertEquals(1, classFile.getFieldList().size());
        final ClassFileField field = classFile.getFieldList().get(0);
        Assert.assertEquals("parser", field.getName());
        Assert.assertEquals("Lorg/langera/freud/optional/classfile/parser/asm/AsmClassFileParser;", field.getDesc());

        Assert.assertEquals(4, classFile.getMethodList().size());
        ClassFileMethod method = classFile.getMethodList().get(0);
        Assert.assertEquals("<init>", method.getName());
        method = classFile.getMethodList().get(1);
        Assert.assertEquals("shouldParseResource", method.getName());
        Assert.assertEquals("()V", method.getDesc());
        method = classFile.getMethodList().get(2);
        Assert.assertEquals("getFileResourceIdentifier", method.getName());
        Assert.assertEquals("(Ljava/lang/String;)Ljava/lang/String;", method.getDesc());
    }

    public static String getFileResourceIdentifier(final String className)
    {
        return ClassLoader.getSystemClassLoader().
                getResource(className.replace(".", "/") + ".class").toExternalForm().substring("file:".length());
    }

    @Before
    public void setUp()
    {
        parser = new AsmClassFileParser(null);
    }
}
