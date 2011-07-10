package org.langera.freud.optional.classfile.parser.asm;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.langera.freud.core.iterator.resource.FileResource;
import org.langera.freud.core.iterator.resource.ResourceParserException;
import org.langera.freud.optional.classfile.ClassFile;

import java.io.IOException;

public final class AsmClassFileParserTest
{

    private AsmClassFileParser parser;

    @Test
    public void shouldParseResource() throws IOException, ResourceParserException
    {
        final ClassFile classFile = parser.parseResource(getFileResourceIdentifier("org.langera.freud.optional.classfile.parser.asm.AsmClassFileParserTest"),
                FileResource.getInstance());

        Assert.assertEquals("org/langera/freud/optional/classfile/parser/asm/AsmClassFileParserTest", classFile.getName());
    }

    private String getFileResourceIdentifier(final String className)
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
