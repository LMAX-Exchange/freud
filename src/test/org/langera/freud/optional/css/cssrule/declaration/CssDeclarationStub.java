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

import org.langera.freud.optional.css.cssrule.CssRule;

public final class CssDeclarationStub implements CssDeclaration
{
    private final String key;
    private final String value;
    private final CssRule rule;

    public CssDeclarationStub(final String key, final String value, final CssRule rule)
    {
        this.key = key;
        this.value = value;
        this.rule = rule;
    }

    @Override
    public String getKey()
    {
        return key;
    }

    @Override
    public String getValue()
    {
        return value;
    }

    @Override
    public CssRule getCssRuleForDeclaration()
    {
        return rule;
    }
}
