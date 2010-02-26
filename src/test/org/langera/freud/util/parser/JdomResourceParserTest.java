package org.langera.freud.util.parser;

import org.junit.Assert;
import org.junit.Test;
import org.langera.freud.util.resource.ResourceParserException;
import org.langera.freud.util.resource.SelfResource;

import java.io.Reader;

public final class JdomResourceParserTest
{
    private static final String BLOW_UP = "BlowUp";

    @Test
    public void shouldParseResourceByDelegating() throws Exception
    {
        JdomResourceParser<ParsedObject> parser = new JdomResourceParser<ParsedObject>(ParsedObject.class);
        ParsedObject parsedObject = parser.parseResource("identifier", SelfResource.getInstance());

        Assert.assertNotNull(parsedObject);
    }

    @Test(expected = ResourceParserException.class)
    public void shouldBlowUpWhenFailingToConstructParser() throws Exception
    {
        JdomResourceParser<ParsedObject> parser = new JdomResourceParser<ParsedObject>(ParsedObject.class);
        parser.parseResource(BLOW_UP, SelfResource.getInstance());
    }

    private static final class ParsedObject
    {
        public ParsedObject(final Reader reader, final String identifier)
        {
            if (BLOW_UP.equals(identifier))
            {
                throw new IllegalStateException("boom");
            }
        }
    }
}
