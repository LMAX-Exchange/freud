package org.langera.freud.optional.text;

import org.junit.Assert;
import org.junit.Test;

import static org.langera.freud.core.matcher.FreudDsl.no;
import static org.langera.freud.optional.text.TextDsl.text;

public final class TextDslTest
{
    @Test
    public void shouldReturnTrueToAMatchedRegex()
    {
        Assert.assertThat(new Text("12345678901234567", ""), text().matches("\\d+"));
    }

    @Test
    public void shouldReturnFalseToANonMatchedRegex()
    {
        Assert.assertThat(new Text("12345678901234567", ""), no(text().matches("a.*")));
    }

    @Test
    public void shouldReturnTrueToAContainsRegex()
    {
        Assert.assertThat(new Text("12345678901234567", ""), text().contains("\\d"));
    }

    @Test
    public void shouldReturnFalseToANonContainsRegex()
    {
        Assert.assertThat(new Text("12345678901234567", ""), no(text().contains("a")));
    }
}
