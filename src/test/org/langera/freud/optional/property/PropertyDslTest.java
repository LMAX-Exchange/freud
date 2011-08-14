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
