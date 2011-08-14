/*
 * Copyright (c) 2011.
 * This file is part of "Freud".
 *
 * Freud is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Freud is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Freud.  If not, see <http://www.gnu.org/licenses/>.
 * @author Amir Langer  langera_at_gmail_dot_com
 */

package org.langera.freud.core.iterator.resource;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.langera.freud.core.listener.AnalysisListenerStub;
import org.langera.freud.optional.text.Text;
import org.langera.freud.optional.text.TextResourceParser;

import java.io.IOException;
import java.util.Deque;

@RunWith(JMock.class)
public class ResourceByIdentifierIteratorTest
{
    private final Mockery mockery = new JUnit4Mockery();
    private ResourceParser<Text> mockResourceParser;
    private AnalysisListenerStub listener;
    private static final IOException IO_EXCEPTION = new IOException("errIO");
    private static final ResourceParserException RESOURCE_PARSER_EXCEPTION = new ResourceParserException("errP", null);

    @Test
    public void shouldIterateOverText() throws Exception
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
    public void shouldFireUnexpectedExceptionEventWhileIterating() throws Exception
    {
        mockery.checking(new Expectations()
        {
            {
                one(mockResourceParser).parseResource("errIO", SelfResource.getInstance());
                will(throwException(IO_EXCEPTION));

                one(mockResourceParser).parseResource("errP", SelfResource.getInstance());
                will(throwException(RESOURCE_PARSER_EXCEPTION));

                one(mockResourceParser).parseResource("a", SelfResource.getInstance());
                will(returnValue(new Text("a", "a")));

                one(mockResourceParser).parseResource("b", SelfResource.getInstance());
                will(returnValue(new Text("b", "b")));
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
        Deque<Exception> unexpected = listener.getUnexpected();
        final IllegalArgumentException exception1 = (IllegalArgumentException) unexpected.getFirst();
        Assert.assertEquals(IO_EXCEPTION, exception1.getCause());
        final IllegalArgumentException exception2 = (IllegalArgumentException) unexpected.getLast();
        Assert.assertEquals(RESOURCE_PARSER_EXCEPTION, exception2.getCause());


    }

    @Before
    @SuppressWarnings("unchecked")
    public void setUp() throws Exception
    {
        listener = new AnalysisListenerStub();
        mockResourceParser = mockery.mock(ResourceParser.class);

        mockery.checking(new Expectations()
        {
            {
                allowing(mockResourceParser).getType();
                will(returnValue(Text.class));
            }
        });
    }
}
