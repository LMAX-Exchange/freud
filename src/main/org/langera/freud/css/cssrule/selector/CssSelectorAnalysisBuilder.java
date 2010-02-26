package org.langera.freud.css.cssrule.selector;

import org.langera.freud.AbstractAnalysisBuilder;
import org.langera.freud.css.cssrule.selector.assertion.CssSelectorMatchAssertionAdapter;
import org.langera.freud.css.cssrule.selector.assertion.CssSelectorTypeAssertion;
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

public final class CssSelectorAnalysisBuilder extends AbstractAnalysisBuilder<CssSelectorAnalysisBuilder, CssSelector>
        implements CssSelectorDsl
{
    public ReadableDsl<CssSelectorAnalysisBuilder> selector()
    {
        setRegexAssertionAdapterClass(CssSelectorMatchAssertionAdapter.class);
        return (ReadableDsl<CssSelectorAnalysisBuilder>) trueAssertion();
    }

    public ReadableDsl<CssSelectorAnalysisBuilder> classSelector()
    {
        setRegexAssertionAdapterClass(CssSelectorMatchAssertionAdapter.class);
        setAssertion(new CssSelectorTypeAssertion(CssSelector.Type.CLASS));
        return this;
    }

    public ReadableDsl<CssSelectorAnalysisBuilder> tagSelector()
    {
        setRegexAssertionAdapterClass(CssSelectorMatchAssertionAdapter.class);                
        setAssertion(new CssSelectorTypeAssertion(CssSelector.Type.TAG));
        return this;
    }

    public ReadableDsl<CssSelectorAnalysisBuilder> idSelector()
    {
        setRegexAssertionAdapterClass(CssSelectorMatchAssertionAdapter.class);               
        setAssertion(new CssSelectorTypeAssertion(CssSelector.Type.ID));
        return this;
    }

    public Class<CssSelector> getType()
    {
        return CssSelector.class;
    }


}
