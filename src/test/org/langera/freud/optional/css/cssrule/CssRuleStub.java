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

package org.langera.freud.optional.css.cssrule;

import org.langera.freud.optional.css.cssrule.declaration.CssDeclaration;
import org.langera.freud.optional.css.cssrule.selector.CssSelector;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class CssRuleStub implements CssRule
{
    private List<CssSelector> cssSelectorList = Collections.emptyList();
    private List<CssDeclaration> cssDeclarationList = Collections.emptyList();

    public List<CssSelector> getCssSelectorList()
    {
        return cssSelectorList;
    }

    public List<CssDeclaration> getCssDeclarationList()
    {
        return cssDeclarationList;
    }

    public CssRuleStub cssSelectors(CssSelector... cssSelectors)
    {
        this.cssSelectorList = Arrays.asList(cssSelectors);
        return this;
    }

    public CssRuleStub cssDeclarations(CssDeclaration... cssDeclarations)
    {
        this.cssDeclarationList = Arrays.asList(cssDeclarations);
        return this;
    }
}
