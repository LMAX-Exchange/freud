package org.freud.analysed.javasource;

public interface ImportDeclaration {
    String[] getImportDeclarationPath();

    String getImportDeclarationPathAsString();

    boolean isStaticDecalaration();
}
