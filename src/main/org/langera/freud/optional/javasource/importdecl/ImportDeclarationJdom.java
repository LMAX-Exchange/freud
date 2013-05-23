package org.langera.freud.optional.javasource.importdecl;

import org.jdom.Element;
import org.langera.freud.optional.javasource.parser.JavaSourceTokenType;

import static org.langera.freud.optional.javasource.JavaSourceJdom.buildPackagePath;
import static org.langera.freud.optional.javasource.JavaSourceJdom.parsePackagePath;

public final class ImportDeclarationJdom implements ImportDeclaration
{
    private final String[] packagePath;
    private final boolean staticImport;
    private String packagePathAsStr;

    public ImportDeclarationJdom(final Element element)
    {
        staticImport = element.getChild(JavaSourceTokenType.STATIC.getName()) != null;
        packagePath = parsePackagePath(element);
    }

    @Override
    public String[] getImportDeclarationPath()
    {
        return packagePath;
    }

    @Override
    public String getImportDeclarationPathAsString()
    {
        if (packagePathAsStr == null)
        {
            packagePathAsStr = buildPackagePath(packagePath);
        }
        return packagePathAsStr;
    }

    @Override
    public boolean isStaticDeclaration()
    {
        return staticImport;
    }
}
