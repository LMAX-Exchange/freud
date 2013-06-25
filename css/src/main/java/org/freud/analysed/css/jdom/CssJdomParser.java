/*
 * Copyright 2013 LMAX Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.freud.analysed.css.jdom;

import org.antlr.runtime.ANTLRReaderStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.apache.commons.jxpath.JXPathContext;
import org.freud.analysed.css.parser.CssLexer;
import org.freud.analysed.css.parser.CssParser;
import org.freud.analysed.css.parser.CssTokenType;
import org.freud.analysed.css.rule.CssRule;
import org.freud.core.parser.JdomTreeAdaptor;
import org.freud.core.parser.TokenType;
import org.jdom.Document;
import org.jdom.Element;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

final class CssJdomParser {

    private static final String CSS_ROOT_ELEMENT_NAME = "CSS";
    private static final TokenType[] CSS_TOKEN_TYPES = CssTokenType.values();

    private CssJdomParser() {
        // static utility
    }

    @SuppressWarnings("unchecked")
    static Iterable<CssRule> parseCssRules(final Reader reader) throws RecognitionException, IOException {
        List<CssRule> cssRuleList = new ArrayList<CssRule>();
        Document root = parseCssToDocument(reader);
        JXPathContext context = JXPathContext.newContext(root.getRootElement());
        List<Element> cssRuleElementList = (List<Element>) context.selectNodes("/RULE");
        for (Element element : cssRuleElementList) {
            final CssRuleJdom cssRuleJdom = new CssRuleJdom(element, 0);
            cssRuleList.add(cssRuleJdom);
            for (int i = 1; i < cssRuleJdom.getNumberOfCommaSeparatedSelectorLists(); i++) {
                cssRuleList.add(new CssRuleJdom(element, i));
            }
        }
        return cssRuleList;
    }

    private static Document parseCssToDocument(final Reader reader) throws RecognitionException, IOException {
        CssParser parser = new CssParser(new CommonTokenStream(new CssLexer(new ANTLRReaderStream(reader))));
        final JdomTreeAdaptor treeAdaptor = new JdomTreeAdaptor(CSS_ROOT_ELEMENT_NAME, CSS_TOKEN_TYPES);
        parser.setTreeAdaptor(treeAdaptor);
        parser.stylesheet();
        return treeAdaptor.getDocument();
    }
}
