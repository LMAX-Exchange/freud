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

import org.apache.commons.jxpath.JXPathContext;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.langera.freud.optional.css.cssrule.declaration.CssDeclaration;
import org.langera.freud.optional.css.cssrule.selector.CssSelector;

import java.util.List;

public final class CssRuleJdomTest
{
    private CssRuleJdom cssRuleJdom;
    private CssRuleJdom cssRuleJdomSeparatedByCommas0;
    private CssRuleJdom cssRuleJdomSeparatedByCommas1;

    @Test
    public void testGetCssSelectorList() throws Exception
    {
        List<CssSelector> cssSelectorList = cssRuleJdom.getCssSelectorList();

        Assert.assertEquals(3, cssSelectorList.size());
        Assert.assertEquals("a", cssSelectorList.get(0).getSelectorString());
        Assert.assertEquals("my-link-id", cssSelectorList.get(1).getSelectorString());
        Assert.assertEquals("myOtherLinkId", cssSelectorList.get(2).getSelectorString());

    }

    @Test
    public void testGetCssDeclarationList() throws Exception
    {
        List<CssDeclaration> cssDeclarationList = cssRuleJdom.getCssDeclarationList();

        Assert.assertEquals(2, cssDeclarationList.size());
        Assert.assertEquals("display", cssDeclarationList.get(0).getKey());
        Assert.assertEquals("color", cssDeclarationList.get(1).getKey());
        Assert.assertEquals("none", cssDeclarationList.get(0).getValue());
        Assert.assertEquals("red", cssDeclarationList.get(1).getValue());

    }

    @Test
    public void shouldParseCommaSeparatedListOfSelectorsByIndex() throws Exception
    {
        Assert.assertEquals("d", cssRuleJdomSeparatedByCommas0.getCssSelectorList().get(0).getSelectorString());
        Assert.assertEquals("e", cssRuleJdomSeparatedByCommas1.getCssSelectorList().get(0).getSelectorString());
    }

    @Before
    public void setUp() throws Exception
    {
        final SAXBuilder saxBuilder = new SAXBuilder(false);
        Document document = saxBuilder.build(ClassLoader.getSystemResourceAsStream("parsed_css_example.xml"));
        JXPathContext context = JXPathContext.newContext(document.getRootElement());
        cssRuleJdom = new CssRuleJdom((Element) context.selectSingleNode("//RULE[3]"), 0);
        cssRuleJdomSeparatedByCommas0 = new CssRuleJdom((Element) context.selectSingleNode("//RULE[2]"), 0);
        cssRuleJdomSeparatedByCommas1 = new CssRuleJdom((Element) context.selectSingleNode("//RULE[2]"), 1);
    }
}
