package org.langera.freud.util.resource;

import org.junit.Assert;
import org.junit.Test;
import org.langera.freud.util.io.IoUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ClasspathTextResourceTest 
{
    @Test
    public void testShouldReturnResourceAsStream() throws Exception
    {
        ClasspathResource resource = ClasspathResource.getInstance();


        InputStream in = resource.getResource("test.txt");

        String data = IoUtil.readFully(new InputStreamReader(in));        

        Assert.assertEquals(
                "This\n" +
                "is a\n" +
                "Test\n" +
                "File\n", data);
    }
    @Test
    public void testShouldNotFindResource() throws Exception
    {
        ClasspathResource resource = ClasspathResource.getInstance();

        try
        {
            resource.getResource("unknown.file");
            Assert.fail("Expected IOException");
        } catch (IOException e)
        {
            Assert.assertEquals("Resource [unknown.file] not found.", e.getMessage());
        }
    }

}
