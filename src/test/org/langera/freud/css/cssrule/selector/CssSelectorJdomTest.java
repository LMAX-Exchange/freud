package org.langera.freud.css.cssrule.selector;

import org.apache.commons.jxpath.JXPathContext;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public final class CssSelectorJdomTest
{
    private CssSelectorJdom cssSelectorJdom;

    @Test
    public void testGetSelectorString() throws Exception
    {
        Assert.assertEquals("shouldFail", cssSelectorJdom.getSelectorString());
    }

    @Test
    public void testGetType() throws Exception
    {
        Assert.assertEquals(CssSelector.Type.CLASS, cssSelectorJdom.getType());        
    }

    @Before
    public void setUp() throws Exception
    {
        final SAXBuilder saxBuilder = new SAXBuilder(false);
        Document document = saxBuilder.build(ClassLoader.getSystemResourceAsStream("parsed_css_example.xml"));
        JXPathContext context = JXPathContext.newContext(document.getRootElement());
        cssSelectorJdom = new CssSelectorJdom(null, (Element) context.selectSingleNode("//CLASS"), CssSelector.Combinator.DESCENDANT);

    }
}
