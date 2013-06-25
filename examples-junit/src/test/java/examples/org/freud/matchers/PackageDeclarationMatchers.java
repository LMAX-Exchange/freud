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

import org.freud.analysed.javasource.PackageDeclaration;
import org.freud.java.matcher.IntOperatorMatcherAdapter;
import org.freud.java.matcher.IntOperatorMatcherBuilder;
import org.freud.java.matcher.RegexMatcherAdapter;
import org.freud.java.matcher.StringMatcherBuilder;


public final class PackageDeclarationMatchers {
    private PackageDeclarationMatchers() {
        // static utility
    }

    public static StringMatcherBuilder<PackageDeclaration> packageDeclaration() {
        return new StringMatcherBuilder<PackageDeclaration>(new RegexMatcherAdapter<PackageDeclaration>() {
            @Override
            public String getStringToMatch(final PackageDeclaration toBeAnalysed) {
                return toBeAnalysed.getPackagePathAsString();
            }

            @Override
            public String matcherDisplayName() {
                return "PackageDeclaration";
            }
        });
    }

    public static StringMatcherBuilder<PackageDeclaration> packageDeclarationLastElement() {
        return new StringMatcherBuilder<PackageDeclaration>(new RegexMatcherAdapter<PackageDeclaration>() {
            @Override
            public String getStringToMatch(final PackageDeclaration toBeAnalysed) {
                final String[] path = toBeAnalysed.getPackagePath();
                return (path.length == 0) ? null : path[path.length - 1];
            }

            @Override
            public String matcherDisplayName() {
                return "PackageDeclarationLastElement";
            }
        });
    }

    public static IntOperatorMatcherBuilder<PackageDeclaration> packageDepth() {
        return new IntOperatorMatcherBuilder<PackageDeclaration>(new IntOperatorMatcherAdapter<PackageDeclaration>() {
            @Override
            public int valueOf(final PackageDeclaration matched) {
                return matched.getPackagePath().length;
            }

            @Override
            public String matcherDisplayName() {
                return "PackageDepth";
            }
        });
    }
}
