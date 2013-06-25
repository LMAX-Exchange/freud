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

package examples.org.freud.matchers;


import org.freud.analysed.css.rule.declaration.CssDeclaration;
import org.freud.java.matcher.RegexMatcherAdapter;
import org.freud.java.matcher.StringMatcherBuilder;

public final class CssDeclarationMatchers {
    private CssDeclarationMatchers() {
        // static utility
    }

    public static StringMatcherBuilder<CssDeclaration> declarationKey() {
        return new StringMatcherBuilder<CssDeclaration>(new RegexMatcherAdapter<CssDeclaration>() {
            @Override
            public String getStringToMatch(final CssDeclaration toBeAnalysed) {
                return toBeAnalysed.getKey();
            }

            @Override
            public String matcherDisplayName() {
                return "CssDeclaration";
            }
        });
    }

    public static StringMatcherBuilder<CssDeclaration> declarationValue() {
        return new StringMatcherBuilder<CssDeclaration>(new RegexMatcherAdapter<CssDeclaration>() {
            @Override
            public String getStringToMatch(final CssDeclaration toBeAnalysed) {
                return toBeAnalysed.getValue();
            }

            @Override
            public String matcherDisplayName() {
                return "CssDeclaration";
            }
        });
    }
}
