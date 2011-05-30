package org.langera.freud.optional.text;

import org.junit.Assert;
import org.junit.Test;

import static org.langera.freud.core.matcher.FreudMatchers.no;
import static org.langera.freud.optional.text.TextMatchers.textMatches;

public final class TextMatchersTest
{
    @Test
    public void shouldReturnTrueToAMatchedRegex()
    {
        Assert.assertThat(new Text("12345678901234567", ""), textMatches("\\d+"));
    }

    @Test
    public void shouldReturnFalseToANonMatchedRegex()
    {
        Assert.assertThat(new Text("12345678901234567", ""), no(textMatches("a.*")));
    }
}
