package org.langera.freud.css.cssrule;

import org.apache.commons.jxpath.JXPathContext;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.langera.freud.css.cssrule.declaration.CssDeclaration;
import org.langera.freud.css.cssrule.selector.CssSelector;

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
        Assert.assertArrayEquals(new String[]{"none"}, cssDeclarationList.get(0).getValues());
        Assert.assertArrayEquals(new String[]{"red"}, cssDeclarationList.get(1).getValues());

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
