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

package org.langera.freud.optional.css.cssrule.declaration;

import org.junit.Assert;
import org.junit.Test;

import static org.langera.freud.core.matcher.FreudDsl.no;
import static org.langera.freud.optional.css.cssrule.declaration.CssDeclarationDsl.declarationKey;
import static org.langera.freud.optional.css.cssrule.declaration.CssDeclarationDsl.declarationValue;

public final class CssDeclarationDslTest
{
    @Test
    public void shouldReturnTrueToAMatchedRegexForAKey()
    {
        Assert.assertThat(new CssDeclarationStub("display", "none", null), declarationKey().matches("d.+"));
    }

    @Test
    public void shouldReturnFalseToANonMatchedRegexForAKey()
    {
        Assert.assertThat(new CssDeclarationStub("display", "none", null), no(declarationKey().matches("n.+")));
    }

    @Test
    public void shouldReturnTrueToAContainsRegexForAKey()
    {
        Assert.assertThat(new CssDeclarationStub("display", "none", null), declarationKey().contains("d"));
    }

    @Test
    public void shouldReturnFalseToANonContainsRegexForAKey()
    {
        Assert.assertThat(new CssDeclarationStub("display", "none", null), no(declarationKey().contains("n")));
    }

    @Test
    public void shouldReturnTrueToAMatchedRegexForAValue()
    {
        Assert.assertThat(new CssDeclarationStub("display", "none", null), declarationValue().matches("n.+"));
    }

    @Test
    public void shouldReturnFalseToANonMatchedRegexForAValue()
    {
        Assert.assertThat(new CssDeclarationStub("display", "none", null), no(declarationValue().matches("d.+")));
    }


    @Test
    public void shouldReturnTrueToAContainsRegexForAValue()
    {
        Assert.assertThat(new CssDeclarationStub("display", "none", null), declarationValue().contains("n"));
    }

    @Test
    public void shouldReturnFalseToANonContainsRegexForAValue()
    {
        Assert.assertThat(new CssDeclarationStub("display", "none", null), no(declarationValue().contains("d")));
    }
}
