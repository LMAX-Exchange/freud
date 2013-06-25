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
