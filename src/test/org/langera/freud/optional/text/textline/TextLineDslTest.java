package org.langera.freud.optional.text.textline;

import org.junit.Assert;
import org.junit.Test;

import static org.langera.freud.core.matcher.FreudDsl.no;
import static org.langera.freud.optional.text.textline.TextLineDsl.line;

public final class TextLineDslTest
{
    @Test
    public void shouldReturnTrueToAMatchedRegex()
    {
        Assert.assertThat(new TextLine("12345678901234567", 19, ""), line().matches("\\d+"));
    }

    @Test
    public void shouldReturnFalseToANonMatchedRegex()
    {
        Assert.assertThat(new TextLine("12345678901234567", 19, ""), no(line().matches("a.*")));
    }

    @Test
    public void shouldReturnTrueToAContainsRegex()
    {
        Assert.assertThat(new TextLine("12345678901234567", 19, ""), line().contains("\\d"));
    }

    @Test
    public void shouldReturnFalseToANonContainsRegex()
    {
        Assert.assertThat(new TextLine("12345678901234567", 19, ""), no(line().contains("a")));
    }

    @Test
    public void shouldReturnTrueToAMatchedLineLength()
    {
        Assert.assertThat(new TextLine("12345678901234567", 19, ""), TextLineDsl.lineLength().equalTo(17));
    }

    @Test
    public void shouldReturnFalseToANonMatchedLineLength()
    {
        Assert.assertThat(new TextLine("12345678901234567", 19, ""), no(TextLineDsl.lineLength().equalTo(13)));
    }

    @Test
    public void shouldReturnTrueToAMatchedLineNumber()
    {
        Assert.assertThat(new TextLine("12345678901234567", 17, ""), TextLineDsl.lineNumber().equalTo(17));
    }

    @Test
    public void shouldReturnFalseToANonMatchedLineNumber()
    {
        Assert.assertThat(new TextLine("12345678901234567", 17, ""), no(TextLineDsl.lineNumber().equalTo(13)));
    }

}
