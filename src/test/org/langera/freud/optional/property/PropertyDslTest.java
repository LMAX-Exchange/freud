package org.langera.freud.optional.property;

import org.junit.Assert;
import org.junit.Test;

import static org.langera.freud.core.matcher.FreudDsl.no;
import static org.langera.freud.optional.property.PropertyDsl.propertyKey;
import static org.langera.freud.optional.property.PropertyDsl.propertyValue;

public final class PropertyDslTest
{
    @Test
    public void shouldReturnTrueToAMatchedRegexOnKey()
    {
        Assert.assertThat(new Property("key", "value"), propertyKey().matches("k.+"));
    }

    @Test
    public void shouldReturnFalseToANonMatchedRegexOnKey()
    {
        Assert.assertThat(new Property("key", "value"), no(propertyKey().matches("v.*")));
    }

    @Test
    public void shouldReturnTrueToAMatchedRegexOnValue()
    {
        Assert.assertThat(new Property("key", "value"), propertyValue().matches("v.+"));
    }

    @Test
    public void shouldReturnFalseToANonMatchedRegexOnValue()
    {
        Assert.assertThat(new Property("key", "value"), no(propertyValue().matches("k.*")));
    }

    @Test
    public void shouldReturnTrueToAContainsRegexOnKey()
    {
        Assert.assertThat(new Property("key", "value"), propertyKey().contains("k"));
    }

    @Test
    public void shouldReturnFalseToANonContainsRegexOnKey()
    {
        Assert.assertThat(new Property("key", "value"), no(propertyKey().contains("v")));
    }

    @Test
    public void shouldReturnTrueToAContainsRegexOnValue()
    {
        Assert.assertThat(new Property("key", "value"), propertyValue().contains("v"));
    }

    @Test
    public void shouldReturnFalseToANonContainsRegexOnValue()
    {
        Assert.assertThat(new Property("key", "value"), no(propertyValue().contains("k")));
    }
}
