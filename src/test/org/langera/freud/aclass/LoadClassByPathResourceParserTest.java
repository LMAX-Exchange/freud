package org.langera.freud.aclass;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.langera.freud.util.resource.ResourceParserException;

import java.io.IOException;

public final class LoadClassByPathResourceParserTest
{
    private LoadClassByPathResourceParser parserOfSrcPath;
    private LoadClassByPathResourceParser parserOfClassPath;

    @Test
    public void shouldLoadClassFromSrcPath() throws IOException, ResourceParserException
    {
        Class loadedClass = parserOfSrcPath.parseResource("src/test/org/langera/freud/aclass/LoadClassByPathResourceParserTest.java", null);
        Assert.assertEquals(LoadClassByPathResourceParserTest.class, loadedClass);
    }

   @Test
    public void shouldLoadClassFromClassPath() throws IOException, ResourceParserException
    {
        Class loadedClass = parserOfClassPath.parseResource("out/test/analysis/org/langera/freud/aclass/LoadClassByPathResourceParserTest.class", null);
        Assert.assertEquals(LoadClassByPathResourceParserTest.class, loadedClass);
    }
    
    @Before
    public void setUp() throws Exception
    {
        parserOfSrcPath = new LoadClassByPathResourceParser("src/test");
        parserOfClassPath = new LoadClassByPathResourceParser("out/test/analysis");
    }
}
