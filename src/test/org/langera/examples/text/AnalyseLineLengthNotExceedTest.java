package org.langera.examples.text;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.langera.freud.Analysis;
import org.langera.freud.TestAnalysisListenerStub;
import org.langera.freud.text.TextResourceParser;
import org.langera.freud.util.resource.AnalysisResource;

import static org.langera.freud.text.TextMatchers.line;

/**
 * Created by IntelliJ IDEA.
 * User: langera
 * Date: 28-Oct-2008
 * Time: 00:04:55
 */
public class AnalyseLineLengthNotExceedTest
{
    private static final String PASSING_TEXT = "0\n1\n2\n3\n4\n5";
    private static final String FAILURE_TEXT = "12345678901";
    private TestAnalysisListenerStub listener;

    @Test
    public void testShouldAnalyseEqualsAlwaysGoesTogetherWithHashCode() throws Exception
    {

        Analysis analysis = TextExamples.lineLengthDoesNotExceed(10,
                AnalysisResource.selfResourceIterator(TextResourceParser.getInstance(),
                        PASSING_TEXT,
                        FAILURE_TEXT));


        analysis.analyse(listener);

                           
        Assert.assertEquals(7, listener.getTotalObjectsAnalysed());
        // first 6 lines passed
        for (int i=0; i <  6; i++)
        {
            listener.assertPassed(line(i, "" + i));
        }
        listener.assertFailed(line(0, FAILURE_TEXT));
    }

    @Before
    public void setUp() throws Exception
    {
        listener = new TestAnalysisListenerStub();

    }
}