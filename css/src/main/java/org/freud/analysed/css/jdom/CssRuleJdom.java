
package org.freud.analysed.css.jdom;

import org.apache.commons.jxpath.JXPathContext;
import org.freud.analysed.css.parser.CssTokenType;
import org.freud.analysed.css.rule.CssRule;
import org.freud.analysed.css.rule.declaration.CssDeclaration;
import org.freud.analysed.css.rule.selector.CssSelector;
import org.jdom.Element;

import java.util.ArrayList;
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
    public List<CssSelector> getCssSelectorList() {
        if (cssSelectorList == null) {
            parseSelectors();
        }
        return cssSelectorList;
    }

    @Override
    public List<CssDeclaration> getCssDeclarationList() {
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
        CssSelector.Combinator combinator = CssSelector.Combinator.DESCENDANT;
        for (Element child : children) {
            if (CssTokenType.COMMA.name().equals(child.getName())) {
                index++;
            }
            else if (getCommaSeparatedSelectorListIndex() == index) {
                if (CssSelector.Type.isType(child.getName())) {
                    cssSelectorList.add(new CssSelectorJdom(this, child, combinator));
                    combinator = CssSelector.Combinator.DESCENDANT;
                }
                else if (CssSelector.Combinator.isCombinator(child.getName())) {
                    combinator = CssSelector.Combinator.valueOf(child.getName());
                }
            }
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
        return "CSS Rule: " + getCssSelectorList().toString();
    }

    public int getNumberOfCommaSeparatedSelectorLists() {
        return numberOfCommaSeparatedSelectorLists;
    }
}
