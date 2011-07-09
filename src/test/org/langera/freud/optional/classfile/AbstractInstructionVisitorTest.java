package org.langera.freud.optional.classfile;

import org.junit.Assert;
import org.junit.Test;

public final class AbstractInstructionVisitorTest
{
    @Test
    public void shouldEncodeClass()
    {
        Assert.assertEquals("Ljava/lang/String;", AbstractInstructionVisitor.typeEncoding(String.class));
    }

    @Test
    public void shouldEncodePrimitiveType()
    {
        Assert.assertEquals("J", AbstractInstructionVisitor.typeEncoding(long.class));
    }


    @Test
    public void shouldEncodeArray()
    {
        Assert.assertEquals("[Ljava/lang/String;", AbstractInstructionVisitor.typeEncoding(String[].class));
    }

    @Test
    public void shouldEncodeArrayOfPrimitiveType()
    {
        Assert.assertEquals("[J", AbstractInstructionVisitor.typeEncoding(long[].class));
    }


    @Test
    public void shouldEncodeArrayOfArray()
    {
        Assert.assertEquals("[[Ljava/lang/String;", AbstractInstructionVisitor.typeEncoding(String[][].class));
    }

}
