package org.langera.freud.css.cssrule;

import org.apache.commons.jxpath.JXPathContext;
import org.jdom.Element;
import org.langera.freud.css.cssrule.declaration.CssDeclaration;
import org.langera.freud.css.cssrule.declaration.CssDeclarationJdom;
import org.langera.freud.css.cssrule.selector.CssSelector;
import org.langera.freud.css.cssrule.selector.CssSelectorJdom;
import org.langera.freud.util.parser.TokenType;
import org.langera.freudgenerated.css.parser.CssTokenType;

import java.util.ArrayList;
import java.util.List;

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

public final class CssRuleJdom implements CssRule
{
    private static final TokenType[] CSS_SELECTOR_TYPES = new TokenType[]
            {
                    CssTokenType.CLASS,
                    CssTokenType.ID,
                    CssTokenType.TAG,
            };
    private List<CssSelector> cssSelectorList;
    private List<CssDeclaration> cssDeclarationList;
    private final Element ruleElement;

    public CssRuleJdom(final Element ruleElement)
    {
        this.ruleElement = ruleElement;
    }

    public List<CssSelector> getCssSelectorList()
    {
        if (cssSelectorList == null)
        {
            parseSelectors();
        }
        return cssSelectorList;
    }

    @SuppressWarnings("unchecked")
    private void parseSelectors()
    {
        cssSelectorList = new ArrayList<CssSelector>();
        JXPathContext context = JXPathContext.newContext(ruleElement);
        for (int i = 0; i < CSS_SELECTOR_TYPES.length; i++)
        {
            TokenType cssSelectorType = CSS_SELECTOR_TYPES[i];
            List<Element> cssSelectorElementList = (List<Element>) context.selectNodes("/" + cssSelectorType.getName());

            for (Element element : cssSelectorElementList)
            {
                cssSelectorList.add(new CssSelectorJdom(this, element));
            }
        }
    }

    public List<CssDeclaration> getCssDeclarationList()
    {
        if (cssDeclarationList == null)
        {
            parseDeclarations();
        }
        return cssDeclarationList;
    }

    @SuppressWarnings("unchecked")
    private void parseDeclarations()
    {
        cssDeclarationList = new ArrayList<CssDeclaration>();
        JXPathContext context = JXPathContext.newContext(ruleElement);
        List<Element> cssSelectorElementList = (List<Element>) context.selectNodes("/" + CssTokenType.PROPERTY.name());

        for (Element element : cssSelectorElementList)
        {
            cssDeclarationList.add(new CssDeclarationJdom(this, element));
        }
    }
}
