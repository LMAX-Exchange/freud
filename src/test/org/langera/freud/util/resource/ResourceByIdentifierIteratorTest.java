package org.langera.freud.util.resource;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.langera.freud.TestAnalysisListenerStub;
import org.langera.freud.text.Text;
import org.langera.freud.text.TextResourceParser;

import java.io.IOException;
import java.util.Deque;

@RunWith(JMock.class)
public class ResourceByIdentifierIteratorTest
{
    private Mockery mockery = new JUnit4Mockery();
    private ResourceParser<Text> mockResourceParser;
    private TestAnalysisListenerStub listener;
    private static final IOException IO_EXCEPTION = new IOException("errIO");
    private static final ResourceParserException RESOURCE_PARSER_EXCEPTION = new ResourceParserException("errP", null);

    @Test
    public void testShouldIterateOverText() throws Exception
    {
        ResourceByIdentifierIterator<Text> iterator = new ResourceByIdentifierIterator<Text>(
                TextResourceParser.getInstance(), SelfResource.getInstance(), "a", "b");

        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals("a", iterator.next().getText());

        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals("b", iterator.next().getText());

        Assert.assertFalse(iterator.hasNext());
    }

    @Test
    public void testShouldFireUnexpectedExceptionEventWhileIterating() throws Exception
    {
        mockery.checking(new Expectations()
        {
            {
                one(mockResourceParser).parseResource("errIO", SelfResource.getInstance());
                will(throwException(IO_EXCEPTION));

                one(mockResourceParser).parseResource("errP", SelfResource.getInstance());
                will(throwException(RESOURCE_PARSER_EXCEPTION));

                one(mockResourceParser).parseResource("a", SelfResource.getInstance());
                will(returnValue(new Text("a", "a", SelfResource.class)));

                one(mockResourceParser).parseResource("b", SelfResource.getInstance());
                will(returnValue(new Text("b", "b", SelfResource.class)));
            }
        });

        ResourceByIdentifierIterator<Text> iterator = new ResourceByIdentifierIterator<Text>(
                mockResourceParser, SelfResource.getInstance(), "a", "errIO", "errP", "b");

        iterator.setListener(listener);

        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals("a", iterator.next().getText());

        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals("b", iterator.next().getText());

        Assert.assertFalse(iterator.hasNext());

        Assert.assertEquals(2, listener.getTotalObjectsAnalysed());
        Deque<TestAnalysisListenerStub.Event> unexpectedEvents = listener.getUnexpected();
        final IllegalArgumentException exception1 = (IllegalArgumentException) unexpectedEvents.getFirst().getAnalysed();
        Assert.assertEquals(IO_EXCEPTION, exception1.getCause());
        final IllegalArgumentException exception2 = (IllegalArgumentException) unexpectedEvents.getLast().getAnalysed();
        Assert.assertEquals(RESOURCE_PARSER_EXCEPTION, exception2.getCause());


    }

    @Before
    @SuppressWarnings("unchecked")
    public void setUp() throws Exception
    {
        listener = new TestAnalysisListenerStub();
        mockResourceParser = mockery.mock(ResourceParser.class);
    }
}
