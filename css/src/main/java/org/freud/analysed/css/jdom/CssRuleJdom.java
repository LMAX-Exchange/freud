package org.freud.analysed.css.jdom;

import org.apache.commons.jxpath.JXPathContext;
import org.freud.analysed.css.parser.CssTokenType;
import org.freud.analysed.css.rule.CssRule;
import org.freud.analysed.css.rule.declaration.CssDeclaration;
import org.freud.analysed.css.rule.selector.CssSelector;
import org.jdom.Element;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


final class CssRuleJdom implements CssRule {
    private List<CssSelector> cssSelectorList;
    private List<CssDeclaration> cssDeclarationList;
    private final Element ruleElement;
    private final int commaSeparatedSelectorListIndex;
    private final int numberOfCommaSeparatedSelectorLists;

    CssRuleJdom(final Element ruleElement, final int commaSeparatedSelectorListIndex) {
        this.ruleElement = ruleElement;
        this.commaSeparatedSelectorListIndex = commaSeparatedSelectorListIndex;
        this.numberOfCommaSeparatedSelectorLists = ruleElement.getChildren(CssTokenType.COMMA.name()).size() + 1;
    }

    @Override
    public List<CssSelector> getCssSelectors() {
        if (cssSelectorList == null) {
            parseSelectors();
        }
        return cssSelectorList;
    }

    @Override
    public List<CssDeclaration> getCssDeclarations() {
        if (cssDeclarationList == null) {
            parseDeclarations();
        }
        return cssDeclarationList;
    }

    @SuppressWarnings("unchecked")
    private void parseSelectors() {
        cssSelectorList = new ArrayList<CssSelector>();
        final List<Element> children = ruleElement.getChildren();
        int index = 0;
        CssSelector.Combinator combinator = null;
        for (Element child : children) {
            if (CssTokenType.COMMA.name().equals(child.getName())) {
                index++;
            }
            else if (getCommaSeparatedSelectorListIndex() == index) {
                if (CssSelector.Type.isType(child.getName())) {
                    final String selectorString = child.getAttributeValue(JdomTreeAdaptor.ID_ATTR);
                    CssSelector.Type selectorType = CssSelector.Type.valueOf(child.getName());
                    if (selectorString != null) {
                        Iterable<String> selectors = breakIdentToSelectors(selectorString);
                        for (String selector : selectors) {
                            cssSelectorList.add(new CssSelectorJdom(this, selector, selectorType, combinator));
                            combinator = CssSelector.Combinator.DESCENDANT;
                            selectorType = CssSelector.Type.CLASS;
                        }
                    }
                    else {
                        cssSelectorList.add(new CssSelectorJdom(this, null, selectorType, combinator));
                        combinator = CssSelector.Combinator.DESCENDANT;
                    }
                }
                else if (CssSelector.Combinator.isCombinator(child.getName())) {
                    combinator = CssSelector.Combinator.valueOf(child.getName());
                }
            }
        }
    }

    // antlr grammar should have solved it but doesn't
    private Iterable<String> breakIdentToSelectors(final String selectorString) {
        if (selectorString.contains(".")) {
            List<String> selectors = new LinkedList<String>();
            char insideQuote = 0;
            int start = 0;
            for (int i = 0, size = selectorString.length(); i < size; i++) {
                char c = selectorString.charAt(i);
                if (insideQuote != 0) {
                    if (c == insideQuote) {
                        insideQuote = 0;
                    }
                }
                else {
                    if (c == '\'' || c == '\"') {
                        insideQuote = c;
                    }
                    else if (c == '.') {
                        selectors.add(selectorString.substring(start, i));
                        start = i + 1;
                    }
                }
            }
            selectors.add(selectorString.substring(start, selectorString.length()));
            return selectors;
        }
        else {
            return Collections.singleton(selectorString);
        }
    }

    int getCommaSeparatedSelectorListIndex() {
        return commaSeparatedSelectorListIndex;
    }

    @SuppressWarnings("unchecked")
    private void parseDeclarations() {
        cssDeclarationList = new ArrayList<CssDeclaration>();
        JXPathContext context = JXPathContext.newContext(ruleElement);
        List<Element> cssSelectorElementList = (List<Element>) context.selectNodes("/" + CssTokenType.PROPERTY.name());

        for (Element element : cssSelectorElementList) {
            cssDeclarationList.add(new CssDeclarationJdom(this, element));
        }
    }

    @Override
    public String toString() {
        return "CSS Rule: " + getCssSelectors().toString();
    }

    public int getNumberOfCommaSeparatedSelectorLists() {
        return numberOfCommaSeparatedSelectorLists;
    }
}
