package org.langera.freud.core.iterator.resource;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.langera.freud.util.io.IoUtil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileResourceTest
{
    private File testFile;
    private static final String FILE_CONTENT = "test test test\n";

    @Test
    public void shouldReturnResourceAsStream() throws Exception
    {
        FileResource resource = FileResource.getInstance();

        InputStream in = resource.getResource(testFile.getAbsolutePath());

        String data = IoUtil.readFully(new InputStreamReader(in));

        Assert.assertEquals(FILE_CONTENT, data);
    }

    @Test(expected = IOException.class)
    public void shouldNotFindResource() throws Exception
    {
        FileResource resource = FileResource.getInstance();

        resource.getResource("unknown.file");
    }

    @Before
    public void setUp() throws Exception
    {
        testFile = new File(System.getProperty("java.io.tmpdir"), "testfile");
        FileWriter w = new FileWriter(testFile);
        w.write(FILE_CONTENT);
        w.close();
    }

    @After
    public void tearDown()
    {
        testFile.delete();
    }
}
