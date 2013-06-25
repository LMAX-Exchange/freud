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

package org.freud.analysed.css.rule.selector;

import org.freud.analysed.css.rule.CssRule;

public interface CssSelector {
    public enum Type {
        CLASS, TAG, ID, UNIVERSAL, PSEUDO, ATTRIB;

        public static boolean isType(String name) {
            final Type[] types = Type.values();
            for (int i = 0; i < types.length; i++) {
                Type type = types[i];
                if (type.name().equals(name)) {
                    return true;
                }
            }
            return false;
        }
    }

    public enum Combinator {
        ADJACENT_SIBLING, CHILD, DESCENDANT, PSEUDO, ATTRIB;

        public static boolean isCombinator(String name) {
            final Combinator[] combinators = Combinator.values();
            for (int i = 0; i < combinators.length; i++) {
                Combinator combinator = combinators[i];
                if (combinator.name().equals(name)) {
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
