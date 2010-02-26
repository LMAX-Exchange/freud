package org.langera.freud.css.cssrule.declaration;

import org.langera.freud.AbstractAnalysisBuilder;
import org.langera.freud.css.cssrule.declaration.assertion.CssDeclarationHasValueMatchAssertion;
import org.langera.freud.css.cssrule.declaration.assertion.CssDeclarationKeyMatchAssertionAdapter;
import org.langera.freud.dsl.ReadableDsl;

/**
 *   This file is part of "Freud".
 *
 *   Freud is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU Lesser General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   Freud is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with Freud.  If not, see <http://www.gnu.org/licenses/>.
 *
 *   @author Amir Langer  langera_at_gmail_dot_com
**/

public final class CssDeclarationAnalysisBuilder extends AbstractAnalysisBuilder<CssDeclarationAnalysisBuilder, CssDeclaration>
        implements CssDeclarationDsl
{
    public ReadableDsl<CssDeclarationAnalysisBuilder> declaration()
    {
        setRegexAssertionAdapterClass(CssDeclarationKeyMatchAssertionAdapter.class);
        return (ReadableDsl<CssDeclarationAnalysisBuilder>) trueAssertion();
    }

    public ReadableDsl<CssDeclarationAnalysisBuilder> declarationValue(final String regex)
    {
        setAssertion(new CssDeclarationHasValueMatchAssertion(regex));
        return this;
    }

    public Class<CssDeclaration> getType()
    {
        return CssDeclaration.class;
    }
}
