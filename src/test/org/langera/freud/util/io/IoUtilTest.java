package org.langera.freud.util.io;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class IoUtilTest 
{


    @Test
    public void shouldParseStreamToLines() throws Exception
    {
        Reader reader = new InputStreamReader(new ByteArrayInputStream("line1 \nline2\t\nline3\n\n".getBytes()));

        String[] lines = IoUtil.readLines(reader);

        Assert.assertEquals(4, lines.length);
        Assert.assertEquals("line1 ", lines[0]);
        Assert.assertEquals("line2\t", lines[1]);
        Assert.assertEquals("line3", lines[2]);
        Assert.assertEquals("", lines[3]);
    }

    @Test
    public void shouldReadStreamFully() throws Exception
    {
        Reader reader = new InputStreamReader(new ByteArrayInputStream("line1 \nline2\t\nline3\n\nline4".getBytes()));

        String text = IoUtil.readFully(reader);

        Assert.assertEquals("line1 \nline2\t\nline3\n\nline4", text);
    }

}
