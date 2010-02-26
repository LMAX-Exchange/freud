package org.langera.freud.text;

import org.junit.Assert;
import org.junit.Test;
import org.langera.freud.util.resource.ClasspathResource;

public final class TextResourceParserTest
{
    private static final String TEST_TXT = "test.txt";
    private static final String TEXT_CONTENT = "This\n" +
            "is a\n" +
            "Test\n" +
            "File\n";

    @Test
    public void shouldParseResourceToText() throws Exception
    {
        TextResourceParser parser = TextResourceParser.getInstance();
        Text text = parser.parseResource(TEST_TXT, ClasspathResource.getInstance());


        Assert.assertEquals(TEST_TXT, text.getResourceIdentifier());
        Assert.assertEquals(TEXT_CONTENT, text.getText());
    }
}
