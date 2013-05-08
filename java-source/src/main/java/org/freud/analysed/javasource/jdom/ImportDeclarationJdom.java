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
        return ((staticImport) ? "static " : "") +  getImportDeclarationPathAsString();
    }
}
