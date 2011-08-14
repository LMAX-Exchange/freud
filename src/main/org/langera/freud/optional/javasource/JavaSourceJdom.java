/*
 * Copyright (c) 2011.
 * This file is part of "Freud".
 *
 * Freud is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Freud is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Freud.  If not, see <http://www.gnu.org/licenses/>.
 * @author Amir Langer  langera_at_gmail_dot_com
 */

package org.langera.freud.optional.javasource;

import org.antlr.runtime.ANTLRReaderStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.apache.commons.jxpath.JXPathContext;
import org.apache.commons.jxpath.JXPathException;
import org.jdom.Document;
import org.jdom.Element;
import org.langera.freud.core.iterator.resource.ResourceParser;
import org.langera.freud.optional.javasource.apackage.PackageDeclaration;
import org.langera.freud.optional.javasource.apackage.PackageDeclarationJdom;
import org.langera.freud.optional.javasource.classdecl.ClassDeclaration;
import org.langera.freud.optional.javasource.classdecl.ClassDeclarationJdom;
import org.langera.freud.optional.javasource.parser.JavaLexer;
import org.langera.freud.optional.javasource.parser.JavaParser;
import org.langera.freud.optional.javasource.parser.JavaSourceTokenType;
import org.langera.freud.util.parser.JdomResourceParser;
import org.langera.freud.util.parser.JdomTreeAdaptor;

import java.io.IOException;
import java.io.Reader;

/**
 * This file is part of "Freud".
 * <p/>
 * Freud is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p/>
 * Freud is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU General Public License
 * along with Freud.  If not, see <http://www.gnu.org/licenses/>.
 *
 * @author Amir Langer  langera_at_gmail_dot_com
 */

public final class JavaSourceJdom implements JavaSource
{
    public static final ResourceParser<JavaSource> PARSER = new JdomResourceParser<JavaSource>(JavaSourceJdom.class, JavaSource.class);

    public static final JavaSourceTokenType[] POSSIBLE_CLASS_DECLARATION_TYPES =
            {
                    JavaSourceTokenType.CLASS,
                    JavaSourceTokenType.INTERFACE,
                    JavaSourceTokenType.ENUM,
                    JavaSourceTokenType.AT,
            };

    private static final JavaSourceTokenType[] JAVA_SOURCE_TOKEN_TYPES = JavaSourceTokenType.values();
    private static final String JAVA_SOURCE_ROOT_ELEMENT_NAME = "JAVA_SOURCE";

    private final String fileName;
    private final Document root;
    private ClassDeclaration classDeclaration;
    private PackageDeclaration packageDeclaration;

    public JavaSourceJdom(final Document root, final String fileName)
    {
        this.root = root;
        this.fileName = fileName;
    }

    public JavaSourceJdom(
            final Reader javaSourceReader,
            final String fileName)
            throws IOException, RecognitionException
    {
        this(parseJavaSourceToDocument(javaSourceReader), fileName);
    }

    private ClassDeclaration parseClassDeclaration()
    {

        JXPathContext context = JXPathContext.newContext(root);
        for (JavaSourceTokenType tokenType : POSSIBLE_CLASS_DECLARATION_TYPES)
        {
            try
            {
                final String tokenName = tokenType.name();
                final Element element = (Element) context.selectSingleNode("/" + JAVA_SOURCE_ROOT_ELEMENT_NAME + "/" + tokenName);
                if (element != null)
                {
                    classDeclaration = new ClassDeclarationJdom(element, getDeclarationType(tokenType));
                }
            }
            catch (JXPathException e)
            {
                // ignore and try another path
            }
        }
        if (classDeclaration == null)
        {
            throw new IllegalStateException("Internal: could not find class declaration in: " + this);
        }

        return classDeclaration;
    }

    private ClassDeclaration.DeclarationType getDeclarationType(JavaSourceTokenType tokenType)
    {
        switch (tokenType)
        {
            case CLASS:
                return ClassDeclaration.DeclarationType.CLASS;
            case INTERFACE:
                return ClassDeclaration.DeclarationType.INTERFACE;
            case ENUM:
                return ClassDeclaration.DeclarationType.ENUM;
            case AT:
                return ClassDeclaration.DeclarationType.ANNOTATION;
            default:
                throw new IllegalStateException("internal. unsupported Class decl. token type [" + tokenType + "]");
        }
    }

    private PackageDeclaration parsePackageDeclaration()
    {
        try
        {
            JXPathContext context = JXPathContext.newContext(root);
            packageDeclaration = new PackageDeclarationJdom((Element)
                    context.selectSingleNode("/" + JAVA_SOURCE_ROOT_ELEMENT_NAME + "/" +
                            JavaSourceTokenType.PACKAGE.name()));
        }
        catch (JXPathException e)
        {
            return new PackageDeclarationJdom();
        }

        return packageDeclaration;
    }

    public Document getDocument()
    {
        return root;
    }

    private static Document parseJavaSourceToDocument(final Reader javaSourceReader) throws RecognitionException, IOException
    {
        JavaParser parser = new JavaParser(new CommonTokenStream(new JavaLexer(new ANTLRReaderStream(javaSourceReader))));
        final JdomTreeAdaptor treeAdaptor = new JdomTreeAdaptor(JAVA_SOURCE_ROOT_ELEMENT_NAME, JAVA_SOURCE_TOKEN_TYPES);
        parser.setTreeAdaptor(treeAdaptor);
        parser.compilationUnit();
        return treeAdaptor.getDocument();
    }

    /////////////////////////////////////////////////////////////////////////////////////

    //    ImportDeclaration[] getImportDeclarations();

    public PackageDeclaration getPackageDeclaration()
    {
        return (packageDeclaration == null) ? parsePackageDeclaration() : packageDeclaration;
    }

    public ClassDeclaration getClassDeclaration()
    {
        return (classDeclaration == null) ? parseClassDeclaration() : classDeclaration;
    }

/////////////////////////////////////////////////////////////////////////////////////

    public String getFileName()
    {
        return fileName;
    }

    public String getFullClassName()
    {

        final String packagePath = getPackageDeclaration().getPackagePathAsString();
        final String className = getClassDeclaration().getName();
        return (packagePath.length() > 0) ? packagePath + "." + className : className;
    }

    @Override
    public String getSimpleClassName()
    {
        return getClassDeclaration().getName();
    }

    public Class loadClass()
    {
        final String fullClassName = getFullClassName();
        try
        {
            return Class.forName(fullClassName);
        }
        catch (ClassNotFoundException e)
        {
            throw new RuntimeException("Failed to load class [" + fullClassName + "]", e);
        }
    }


    @Override
    public String toString()
    {
        return JdomTreeAdaptor.documentToString(root);
    }

}
