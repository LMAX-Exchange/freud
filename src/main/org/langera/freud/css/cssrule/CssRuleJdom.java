package org.langera.freud.css.cssrule;

import org.apache.commons.jxpath.JXPathContext;
import org.jdom.Element;
import org.langera.freud.css.cssrule.declaration.CssDeclaration;
import org.langera.freud.css.cssrule.declaration.CssDeclarationJdom;
import org.langera.freud.css.cssrule.selector.CssSelector;
import org.langera.freud.css.cssrule.selector.CssSelectorJdom;
import org.langera.freudgenerated.css.parser.CssTokenType;

import java.util.ArrayList;
import java.util.List;

/**
 * This file is part of "Freud".
 * <p/>
 * Freud is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p/>
 * Freud is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU General Public License
 * along with Freud.  If not, see <http://www.gnu.org/licenses/>.
 *
 * @author Amir Langer  langera_at_gmail_dot_com
 */

public final class CssRuleJdom implements CssRule
{
    private List<CssSelector> cssSelectorList;
    private List<CssDeclaration> cssDeclarationList;
    private final Element ruleElement;
    private final int commaSeparatedSelectorListIndex;
    private final int numberOfCommaSeparatedSelectorLists;

    public CssRuleJdom(final Element ruleElement, final int commaSeparatedSelectorListIndex)
    {
        this.ruleElement = ruleElement;
        this.commaSeparatedSelectorListIndex = commaSeparatedSelectorListIndex;
        this.numberOfCommaSeparatedSelectorLists = ruleElement.getChildren(CssTokenType.COMMA.name()).size() + 1;
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
        final List<Element> children = ruleElement.getChildren();
        int index = 0;
        for (Element child : children)
        {
            if (CssTokenType.COMMA.name().equals(child.getName()))
            {
                index++;
            }
            else if (getCommaSeparatedSelectorListIndex() == index && isSelector(child))
            {
                cssSelectorList.add(new CssSelectorJdom(this, child));
            }
        }
    }

    public int getCommaSeparatedSelectorListIndex()
    {
        return commaSeparatedSelectorListIndex;
    }

    private boolean isSelector(Element element)
    {
        final String name = element.getName();
        return name.equals(CssSelector.Type.TAG.name()) ||
                name.equals(CssSelector.Type.CLASS.name()) ||
                name.equals(CssSelector.Type.ID.name());
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

    @Override
    public String toString()
    {
        return "CSS Rule: " + cssSelectorList.toString();
    }

    public int getNumberOfCommaSeparatedSelectorLists()
    {
        return numberOfCommaSeparatedSelectorLists;
    }
}
