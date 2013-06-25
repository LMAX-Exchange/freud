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

package org.freud.analysed.css;

import org.freud.analysed.css.jdom.CssRulesJdomFromFileCreator;
import org.freud.analysed.css.jdom.CssRulesJdomFromStringCreator;
import org.freud.analysed.css.jdom.CssRulesJdomFromUrlCreator;
import org.freud.analysed.css.rule.CssRule;
import org.freud.analysed.css.rule.declaration.CssDeclaration;
import org.freud.analysed.css.rule.selector.CssSelector;
import org.freud.core.FreudSource;
import org.freud.core.iterator.SubTypeAnalysedObjects;

import java.io.File;
import java.net.URL;

import static org.freud.core.FreudSource.typeOf;

public final class CssDsl {

    private CssDsl() {
        // static utility
    }

    @SuppressWarnings("unchecked")
    public static <T> Iterable<CssRule> cssRulesOf(Iterable<T> iterable) {
        Class type = typeOf(iterable);
        return cssRulesOf(new FreudSource(iterable, type));
    }

    @SuppressWarnings("unchecked")
    public static Iterable<CssRule> cssRulesOf(FreudSource source) {
        if (File.class.equals(source.getType())) {
            return new SubTypeAnalysedObjects<File, CssRule>(new CssRulesJdomFromFileCreator(), source.getSources());
        }
        if (URL.class.equals(source.getType())) {
            return new SubTypeAnalysedObjects<URL, CssRule>(new CssRulesJdomFromUrlCreator(), source.getSources());
        }
        if (String.class.equals(source.getType())) {
            return new SubTypeAnalysedObjects<String, CssRule>(new CssRulesJdomFromStringCreator(), source.getSources());
        }
        throw new UnsupportedOperationException("Unsupported conversion " + source.getType() + " to Css");
    }

    public static Iterable<CssSelector> cssSelectorsWithin(Iterable<CssRule> cssRules) {
        return new SubTypeAnalysedObjects<CssRule, CssSelector>(new CssRuleToSelectorsCreator(), cssRules);
    }

    public static Iterable<CssDeclaration> cssDeclarationsWithin(Iterable<CssRule> cssRules) {
        return new SubTypeAnalysedObjects<CssRule, CssDeclaration>(new CssRuleToDeclarationsCreator(), cssRules);
    }

}
