package org.langera.examples.instance;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.langera.freud.Analysis;
import org.langera.freud.TestAnalysisListenerStub;
import org.langera.freud.util.resource.AnalysisResource;
import org.langera.freud.util.resource.Resource;
import org.langera.freud.util.resource.ResourceParser;
import org.langera.freud.util.resource.ResourceParserException;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

public final class AnalyseFileNamesMustNotContainUpperCaseCharactersTest
{
    private TestAnalysisListenerStub listener;

    @Test
    public void testShouldAnalyseDefFilesNamingConvention() throws Exception
    {
        Analysis analysis = InstanceExamples.toStringDoNotContainUpperCaseCharacters(
                AnalysisResource.filesByPathResourceIterator(new ResourceParser<Object>()
                {
                    public Object parseResource(String resourceIdentifier, Resource resource) throws IOException, ResourceParserException
                    {
                        return resourceIdentifier.substring(resourceIdentifier.lastIndexOf(File.separatorChar) + 1);
                    }
                }, new FilenameFilter()
                {
                    public boolean accept(File dir, String name)
                    {
                        return name.endsWith(".properties");
                    }
                }, true, "src" + File.separatorChar + "def"));

        analysis.analyse(listener);


        Assert.assertEquals(16, listener.getTotalObjectsAnalysed());
        Assert.assertEquals(12, listener.getPassed().size());
        Assert.assertEquals(4, listener.getFailed().size());
    }

    @Before
    public void setUp() throws Exception
    {
        listener = new TestAnalysisListenerStub();
    }
}