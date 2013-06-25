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

package org.freud.analysed.javasource.jdom;

import org.freud.analysed.javasource.PackageDeclaration;
import org.jdom.Element;

final class PackageDeclarationJdom implements PackageDeclaration {
    private final String[] packagePath;
    private String packagePathAsStr;

    public PackageDeclarationJdom() {
        packagePath = new String[]{};
    }

    public PackageDeclarationJdom(Element element) {
        packagePath = JavaSourceJdom.parsePackagePath(element);
    }

    public String[] getPackagePath() {
        return packagePath;
    }

    public String getPackagePathAsString() {
        if (packagePathAsStr == null) {
            packagePathAsStr = JavaSourceJdom.buildPackagePath(packagePath);
        }
        return packagePathAsStr;
    }

}
