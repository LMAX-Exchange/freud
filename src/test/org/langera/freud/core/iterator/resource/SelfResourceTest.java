package org.langera.freud.core.iterator.resource;

import org.junit.Assert;
import org.junit.Test;
import org.langera.freud.util.io.IoUtil;

import java.io.InputStream;
import java.io.InputStreamReader;

public class SelfResourceTest
{
    @Test
    public void shouldReturnResourceAsText() throws Exception
    {
        SelfResource resource = SelfResource.getInstance();

        final String input = "abcd";
        InputStream in = resource.getResource(input);

                String data = IoUtil.readFully(new InputStreamReader(in));

        Assert.assertEquals(input, data);
    }
}
