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

import org.freud.analysed.javasource.ImportDeclaration;
import org.freud.analysed.javasource.parser.JavaSourceTokenType;
import org.jdom.Element;

final class ImportDeclarationJdom implements ImportDeclaration {
    private final String[] packagePath;
    private final boolean staticImport;
    private String packagePathAsStr;

    public ImportDeclarationJdom(final Element element) {
        staticImport = element.getChild(JavaSourceTokenType.STATIC.getName()) != null;
        packagePath = JavaSourceJdom.parsePackagePath(element);
    }

    @Override
    public String[] getImportDeclarationPath() {
        return packagePath;
    }

    @Override
    public String getImportDeclarationPathAsString() {
        if (packagePathAsStr == null) {
            packagePathAsStr = JavaSourceJdom.buildPackagePath(packagePath);
        }
        return packagePathAsStr;
    }

    @Override
    public boolean isStaticDecalaration() {
        return staticImport;
    }

    @Override
    public String toString() {
        return ((staticImport) ? "static " : "") + getImportDeclarationPathAsString();
    }
}
