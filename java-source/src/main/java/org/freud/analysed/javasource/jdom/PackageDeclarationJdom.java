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
