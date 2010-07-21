package org.langera.freud.css;

import org.jdom.Document;
import org.jdom.input.SAXBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.langera.freud.css.cssrule.CssRule;

import java.util.List;

public final class CssJdomTest
{
    private CssJdom cssJdom;
    private static final String PARSED_CSS_EXAMPLE_XML = "parsed_css_example.xml";

    @Test
    public void shouldReturnCssRuleList() throws Exception
    {
        List<CssRule> cssRuleList = cssJdom.getCssRuleList();

        Assert.assertEquals(5, cssRuleList.size());
    }

    @Before
    public void setUp() throws Exception
    {
        final SAXBuilder saxBuilder = new SAXBuilder(false);
        Document document = saxBuilder.build(ClassLoader.getSystemResourceAsStream(PARSED_CSS_EXAMPLE_XML));
        cssJdom = new CssJdom(document, PARSED_CSS_EXAMPLE_XML);
    }
}
