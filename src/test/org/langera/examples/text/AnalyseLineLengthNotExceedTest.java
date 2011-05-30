package org.langera.examples.text;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.langera.freud.core.FreudAnalyser;
import org.langera.freud.core.iterator.resource.ResourceIterators;
import org.langera.freud.core.listener.AnalysisListenerStub;
import org.langera.freud.optional.text.TextResourceParser;

import static org.langera.examples.text.TextTestMatchers.line;

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
    private AnalysisListenerStub listener;

    @Test
    public void shouldAnalyseTextLinesForLineLength() throws Exception
    {

        FreudAnalyser analyser = TextExamples.lineLengthDoesNotExceed(10,
                ResourceIterators.selfResourceIterator(TextResourceParser.getInstance(),
                        PASSING_TEXT,
                        FAILURE_TEXT));


        analyser.analyse(listener);

                           
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
        listener = new AnalysisListenerStub();

    }
}