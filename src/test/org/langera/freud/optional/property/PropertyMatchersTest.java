package org.langera.freud.optional.property;

import org.junit.Assert;
import org.junit.Test;

import static org.langera.freud.core.matcher.FreudMatchers.no;
import static org.langera.freud.optional.property.PropertyMatchers.propertyKeyContains;
import static org.langera.freud.optional.property.PropertyMatchers.propertyKeyMatches;
import static org.langera.freud.optional.property.PropertyMatchers.propertyValueContains;
import static org.langera.freud.optional.property.PropertyMatchers.propertyValueMatches;

public final class PropertyMatchersTest
{
    @Test
    public void shouldReturnTrueToAMatchedRegexOnKey()
    {
        Assert.assertThat(new Property("key", "value"), propertyKeyMatches("k.+"));
    }

    @Test
    public void shouldReturnFalseToANonMatchedRegexOnKey()
    {
        Assert.assertThat(new Property("key", "value"), no(propertyKeyMatches("v.*")));
    }

    @Test
    public void shouldReturnTrueToAMatchedRegexOnValue()
    {
        Assert.assertThat(new Property("key", "value"), propertyValueMatches("v.+"));
    }

    @Test
    public void shouldReturnFalseToANonMatchedRegexOnValue()
    {
        Assert.assertThat(new Property("key", "value"), no(propertyValueMatches("k.*")));
    }

    @Test
    public void shouldReturnTrueToAContainedRegexOnKey()
    {
        Assert.assertThat(new Property("key", "value"), propertyKeyContains("k"));
    }

    @Test
    public void shouldReturnFalseToANonContainedRegexOnKey()
    {
        Assert.assertThat(new Property("key", "value"), no(propertyKeyContains("v")));
    }

    @Test
    public void shouldReturnTrueToAContainedRegexOnValue()
    {
        Assert.assertThat(new Property("key", "value"), propertyValueContains("v"));
    }

    @Test
    public void shouldReturnFalseToANonContainedRegexOnValue()
    {
        Assert.assertThat(new Property("key", "value"), no(propertyValueContains("k")));
    }
}
