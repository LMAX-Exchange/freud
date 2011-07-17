package org.langera.freud.optional.classfile.method;

import org.junit.Assert;
import org.junit.Test;
import org.langera.freud.optional.classfile.method.instruction.AbstractInstructionVisitor;

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
