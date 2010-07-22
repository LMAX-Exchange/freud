package org.langera.freud.css;

import org.jdom.Document;
import org.jdom.input.SAXBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.langera.freud.css.cssrule.CssRule;

import java.io.InputStreamReader;
import java.util.List;

public final class CssJdomTest
{
    private CssJdom cssJdom;
    private static final String CSS_EXAMPLE = "css_example.css";
    private static final String PARSED_CSS_EXAMPLE_XML = "parsed_css_example.xml";

    @Test
    public void shouldParseCssFile() throws Exception
    {
        cssJdom = new CssJdom(new InputStreamReader(ClassLoader.getSystemResourceAsStream(CSS_EXAMPLE)), PARSED_CSS_EXAMPLE_XML);
        
        List<CssRule> cssRuleList = cssJdom.getCssRuleList();

//        System.out.println(cssJdom);

        Assert.assertEquals(10, cssRuleList.size());
    }


    @Test
    public void shouldReturnCssRuleList() throws Exception
    {
        final SAXBuilder saxBuilder = new SAXBuilder(false);
        Document document = saxBuilder.build(ClassLoader.getSystemResourceAsStream(PARSED_CSS_EXAMPLE_XML));
        cssJdom = new CssJdom(document, PARSED_CSS_EXAMPLE_XML);

        List<CssRule> cssRuleList = cssJdom.getCssRuleList();

        Assert.assertEquals(
                "[CSS Rule: [CLASS:shouldFail], " +
                 "CSS Rule: [TAG:d], " +
                 "CSS Rule: [TAG:e], " +
                 "CSS Rule: [TAG:f], " +
                 "CSS Rule: [TAG:a, ADJACENT_SIBLING:ID:my-link-id, CHILD:ID:myOtherLinkId], " +
                 "CSS Rule: [TAG:a, PSEUDO:hover], " +
                 "CSS Rule: [TAG:a, CHILD:TAG:b, CHILD:TAG:c], " +
                 "CSS Rule: [UNIVERSAL], " +
                 "CSS Rule: [TAG:a, ATTRIB:href], " +
                 "CSS Rule: [TAG:a, ATTRIB:href=\"foo.html\"]]", cssRuleList.toString());
    }
}
