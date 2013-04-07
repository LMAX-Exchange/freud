package org.freud.analysed.css.jdom;

import org.freud.analysed.css.rule.CssRule;
import org.freud.analysed.css.rule.selector.CssSelector;
import org.jdom.Element;


final class CssSelectorJdom implements CssSelector {
    private final String selectorString;
    private final Type type;
    private final CssRule cssRule;
    private final Combinator combinator;

    CssSelectorJdom(final CssRule cssRule, final Element element, Combinator combinator) {
        this.cssRule = cssRule;
        type = Type.valueOf(element.getName());
        selectorString = element.getAttributeValue(JdomTreeAdaptor.ID_ATTR);
        this.combinator = combinator;
    }

    @Override
    public String getSelectorString() {
        return selectorString;
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public CssRule getCssRuleForSelector() {
        return cssRule;
    }

    @Override
    public Combinator getCombinator() {
        return combinator;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (combinator != Combinator.DESCENDANT) {
            sb.append(combinator).append(':');
        }
        sb.append(type);
        if (selectorString != null) {
            sb.append(':').append(selectorString);
        }
        return sb.toString();
    }
}
