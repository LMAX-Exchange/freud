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
import org.freud.analysed.css.rule.declaration.CssDeclaration;
import org.jdom.Element;

import java.util.List;


final class CssDeclarationJdom implements CssDeclaration {
    private final CssRule cssRule;
    private final String key;
    private final String value;

    @SuppressWarnings("unchecked")
    CssDeclarationJdom(final CssRule cssRule, final Element element) {
        this.cssRule = cssRule;
        List<Element> declaration = (List<Element>) element.getChildren();
        int size = declaration.size();
        if (size > 0) {
            key = declaration.get(0).getText();
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i < size; i++) {
                if (i > 1) {
                    sb.append(' ');
                }
                sb.append(declaration.get(i).getText());
            }
            value = sb.toString();
        }
        else {
            key = null;
            value = "";
        }
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public CssRule getCssRuleForDeclaration() {
        return cssRule;
    }

    @Override
    public String toString() {
        return key + ": " + value;
    }
}
