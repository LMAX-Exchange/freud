package org.langera.freud.text;

import org.junit.Assert;
import org.junit.Test;
import org.langera.freud.text.line.TextLine;
import org.langera.freud.util.resource.SelfResource;

import java.util.List;

public final class TextTest
{
    private static final String TEXT_CONTENT = "This\n" +
            "is a\n" +
            "Test\n" +
            "File\n";

    @Test
    public void shouldParseTextIntoTextLines()
    {
        Text text = new Text(TEXT_CONTENT, "id", SelfResource.class);
        List<TextLine> textLineList = text.getTextLines();
        Assert.assertEquals(4, textLineList.size());
        Assert.assertEquals("This", textLineList.get(0).getLine());
        Assert.assertEquals("is a", textLineList.get(1).getLine());
        Assert.assertEquals("Test", textLineList.get(2).getLine());
        Assert.assertEquals("File", textLineList.get(3).getLine());
    }


    @Test
    public void shouldParseEmptyTextIntoZeroTextLines()
    {
        Text text = new Text("", "id", SelfResource.class);

        Assert.assertTrue(text.getTextLines().isEmpty());
    }
}
