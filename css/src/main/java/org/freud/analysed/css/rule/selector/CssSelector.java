package org.freud.analysed.css.rule.selector;

import org.freud.analysed.css.rule.CssRule;

public interface CssSelector {
    public enum Type
    {
        CLASS, TAG, ID, UNIVERSAL, PSEUDO, ATTRIB;

        public static boolean isType(String name)
        {
            final Type[] types = Type.values();
            for (int i = 0; i < types.length; i++)
            {
                Type type = types[i];
                if (type.name().equals(name))
                {
                    return true;
                }
            }
            return false;
        }
    }

    public enum Combinator
    {
        ADJACENT_SIBLING, CHILD, DESCENDANT;

        public static boolean isCombinator(String name)
        {
            final Combinator[] combinators = Combinator.values();
            for (int i = 0; i < combinators.length; i++)
            {
                Combinator combinator = combinators[i];
                if (combinator.name().equals(name))
                {
                    return true;
                }
            }
            return false;
        }
    }

    String getSelectorString();

    Type getType();

    CssRule getCssRuleForSelector();

    Combinator getCombinator();
}
