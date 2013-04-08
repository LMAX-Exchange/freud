package org.freud.analysed.css.jdom;

import org.freud.analysed.css.rule.CssRule;
import org.freud.analysed.css.rule.selector.CssSelector;


final class CssSelectorJdom implements CssSelector {
    private final String selectorString;
    private final Type type;
    private final CssRule cssRule;
    private final Combinator combinator;

    CssSelectorJdom(final CssRule cssRule, final String selectorString, final Type type, final Combinator combinator) {
        this.cssRule = cssRule;
        this.selectorString = selectorString;
        this.type = type;
        this.combinator = (type == Type.PSEUDO) ? Combinator.PSEUDO :
                (type == Type.ATTRIB) ? Combinator.ATTRIB : combinator;
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
