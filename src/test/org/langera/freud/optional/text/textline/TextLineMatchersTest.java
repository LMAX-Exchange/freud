package org.langera.freud.optional.text.textline;

import org.junit.Assert;
import org.junit.Test;

import static org.langera.freud.core.matcher.FreudMatchers.no;

public final class TextLineMatchersTest
{
    @Test
    public void shouldReturnTrueToAMatchedRegex()
    {
        Assert.assertThat(new TextLine("12345678901234567", 19, ""), TextLineMatchers.lineMatches("\\d+"));
    }

    @Test
    public void shouldReturnFalseToANonMatchedRegex()
    {
        Assert.assertThat(new TextLine("12345678901234567", 19, ""), no(TextLineMatchers.lineMatches("a.*")));
    }

    @Test
    public void shouldReturnTrueToAContainedRegex()
    {
        Assert.assertThat(new TextLine("12345678901234567", 19, ""), TextLineMatchers.lineContains("\\d"));
    }

    @Test
    public void shouldReturnFalseToANonContainedRegex()
    {
        Assert.assertThat(new TextLine("12345678901234567", 19, ""), no(TextLineMatchers.lineContains("a")));
    }

    @Test
    public void shouldReturnTrueToAMatchedLineLength()
    {
        Assert.assertThat(new TextLine("12345678901234567", 19, ""), TextLineMatchers.lineLength().equalTo(17));
    }

    @Test
    public void shouldReturnFalseToANonMatchedLineLength()
    {
        Assert.assertThat(new TextLine("12345678901234567", 19, ""), no(TextLineMatchers.lineLength().equalTo(13)));
    }

    @Test
    public void shouldReturnTrueToAMatchedLineNumber()
    {
        Assert.assertThat(new TextLine("12345678901234567", 17, ""), TextLineMatchers.lineNumber().equalTo(17));
    }

    @Test
    public void shouldReturnFalseToANonMatchedLineNumber()
    {
        Assert.assertThat(new TextLine("12345678901234567", 17, ""), no(TextLineMatchers.lineNumber().equalTo(13)));
    }

}
