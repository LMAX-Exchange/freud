package org.langera.freud.optional.css.cssrule.declaration;

import org.apache.commons.jxpath.JXPathContext;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public final class CssDeclarationJdomTest
{
    private CssDeclarationJdom cssDeclarationJdom;

    @Test
    public void testGetKey() throws Exception
    {
        Assert.assertEquals("display", cssDeclarationJdom.getKey());
    }

    @Test
    public void testGetValues() throws Exception
    {
        Assert.assertEquals("none", cssDeclarationJdom.getValue());
    }

    @Before
    public void setUp() throws Exception
    {
        final SAXBuilder saxBuilder = new SAXBuilder(false);
        Document document = saxBuilder.build(ClassLoader.getSystemResourceAsStream("parsed_css_example.xml"));
        JXPathContext context = JXPathContext.newContext(document.getRootElement());
        cssDeclarationJdom = new CssDeclarationJdom(null, (Element) context.selectSingleNode("//PROPERTY"));

    }
}
