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

package org.langera.freud.optional.css;

import org.antlr.runtime.ANTLRReaderStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.apache.commons.jxpath.JXPathContext;
import org.jdom.Document;
import org.jdom.Element;
import org.langera.freud.core.iterator.resource.ResourceParser;
import org.langera.freud.optional.css.cssrule.CssRule;
import org.langera.freud.optional.css.cssrule.CssRuleJdom;
import org.langera.freud.optional.css.parser.CssLexer;
import org.langera.freud.optional.css.parser.CssParser;
import org.langera.freud.optional.css.parser.CssTokenType;
import org.langera.freud.util.parser.JdomResourceParser;
import org.langera.freud.util.parser.JdomTreeAdaptor;
import org.langera.freud.util.parser.TokenType;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;


public final class CssJdom implements Css
{
    public static final ResourceParser<Css> PARSER = new JdomResourceParser<Css>(CssJdom.class, Css.class);


    private final Document root;
    private final String fileName;
    private static final String CSS_ROOT_ELEMENT_NAME = "CSS";
    private static final TokenType[] CSS_TOKEN_TYPES = CssTokenType.values();

    public CssJdom(final Document root, final String fileName)
    {
        this.root = root;
        this.fileName = fileName;
    }


    public CssJdom(final Reader cssSourceReader, final String fileName) throws IOException, RecognitionException
    {
        this(parseCssToDocument(cssSourceReader), fileName);
    }

    @SuppressWarnings("unchecked")
    public List<CssRule> getCssRuleList()
    {
        List<CssRule> cssRuleList = new ArrayList<CssRule>();
        JXPathContext context = JXPathContext.newContext(root.getRootElement());
        List<Element> cssRuleElementList = (List<Element>) context.selectNodes("/RULE");
        for (Element element : cssRuleElementList)
        {
            final CssRuleJdom cssRuleJdom = new CssRuleJdom(element, 0);
            cssRuleList.add(cssRuleJdom);
            for (int i = 1; i < cssRuleJdom.getNumberOfCommaSeparatedSelectorLists(); i++)
            {
                cssRuleList.add(new CssRuleJdom(element, i));
            }
        }
        return cssRuleList;
    }


    public Document getDocument()
    {
        return root;
    }

    public String getFileName()
    {
        return fileName;
    }

    private static Document parseCssToDocument(final Reader cssReader) throws RecognitionException, IOException
    {
        CssParser parser = new CssParser(new CommonTokenStream(new CssLexer(new ANTLRReaderStream(cssReader))));
        final JdomTreeAdaptor treeAdaptor = new JdomTreeAdaptor(CSS_ROOT_ELEMENT_NAME, CSS_TOKEN_TYPES);
        parser.setTreeAdaptor(treeAdaptor);
        parser.stylesheet();
        return treeAdaptor.getDocument();
    }

    @Override
    public String toString()
    {
        return JdomTreeAdaptor.documentToString(root);
    }
}
