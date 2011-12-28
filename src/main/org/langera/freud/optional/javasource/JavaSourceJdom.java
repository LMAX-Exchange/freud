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
import org.jdom.filter.ElementFilter;
import org.langera.freud.core.iterator.resource.ResourceParser;
import org.langera.freud.optional.javasource.classdecl.ClassDeclaration;
import org.langera.freud.optional.javasource.classdecl.ClassDeclarationJdom;
import org.langera.freud.optional.javasource.importdecl.ImportDeclaration;
import org.langera.freud.optional.javasource.importdecl.ImportDeclarationJdom;
import org.langera.freud.optional.javasource.packagedecl.PackageDeclaration;
import org.langera.freud.optional.javasource.packagedecl.PackageDeclarationJdom;
import org.langera.freud.optional.javasource.parser.JavaLexer;
import org.langera.freud.optional.javasource.parser.JavaParser;
import org.langera.freud.optional.javasource.parser.JavaSourceTokenType;
import org.langera.freud.util.parser.JdomResourceParser;
import org.langera.freud.util.parser.JdomTreeAdaptor;
import org.langera.freud.util.parser.JdomTreePositionComparator;

import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

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
    private ImportDeclaration[] importDeclarations;

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

    public Document getDocument()
    {
        return root;
    }

    /////////////////////////////////////////////////////////////////////////////////////

    @Override
    public PackageDeclaration getPackageDeclaration()
    {
        return (packageDeclaration == null) ? parsePackageDeclaration() : packageDeclaration;
    }

    @Override
    public ImportDeclaration[] getImportDeclarations()
    {
        return (importDeclarations == null) ? parseImportDeclaration() : importDeclarations;
    }

    @Override
    public ClassDeclaration getClassDeclaration()
    {
        return (classDeclaration == null) ? parseClassDeclaration() : classDeclaration;
    }

/////////////////////////////////////////////////////////////////////////////////////

    public String getFileName()
    {
        return fileName;
    }

    @Override
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

    @Override
    public String toString()
    {
        return JdomTreeAdaptor.documentToString(root);
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////

    public static String[] parsePackagePath(final Element element)
    {
        SortedSet<Element> packagePathElementSortedSet = new TreeSet<Element>(JdomTreePositionComparator.getInstance());

        for (Iterator iterator = element.getDescendants(new ElementFilter(JavaSourceTokenType.IDENT.getName()));
             iterator.hasNext(); )
        {
            packagePathElementSortedSet.add((Element) iterator.next());

        }

        boolean endsWithDotStar =  (element.getChild(JavaSourceTokenType.DOTSTAR.getName()) != null);
        final String[] packagePath = new String[(endsWithDotStar) ? packagePathElementSortedSet.size()  + 1 :
                                                                    packagePathElementSortedSet.size()];
        int i = 0;
        for (Element pathElement : packagePathElementSortedSet)
        {
            packagePath[i++] = pathElement.getTextTrim();
        }
        if (endsWithDotStar)
        {
            packagePath[i] = "*";
        }
        return packagePath;
    }

    public static String buildPackagePath(final String[] packagePath)
    {
        StringBuilder packagePathStrBuilder = new StringBuilder();
        for (int i = 0, size = packagePath.length; i < size; i++)
        {
            if (i > 0)
            {
                packagePathStrBuilder.append('.');
            }
            packagePathStrBuilder.append(packagePath[i]);
        }
        return packagePathStrBuilder.toString();
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////

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
                    classDeclaration = new ClassDeclarationJdom(element, getDeclarationType(tokenType), null);
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
            packageDeclaration = new PackageDeclarationJdom();
        }

        return packageDeclaration;
    }

    private ImportDeclaration[] parseImportDeclaration()
    {
        try
        {
            final JXPathContext context = JXPathContext.newContext(root);
            final List importNodes = context.selectNodes("/" + JAVA_SOURCE_ROOT_ELEMENT_NAME + "/" +
                    JavaSourceTokenType.IMPORT.name());
            importDeclarations = new ImportDeclaration[importNodes.size()];
            int i = 0;
            for (Object importNode : importNodes)
            {
                importDeclarations[i++] = new ImportDeclarationJdom((Element) importNode);
            }
        }
        catch (JXPathException e)
        {
            importDeclarations = new ImportDeclaration[0];
        }
        return importDeclarations;
    }

    private static Document parseJavaSourceToDocument(final Reader javaSourceReader) throws RecognitionException, IOException
    {
        JavaParser parser = new JavaParser(new CommonTokenStream(new JavaLexer(new ANTLRReaderStream(javaSourceReader))));
        final JdomTreeAdaptor treeAdaptor = new JdomTreeAdaptor(JAVA_SOURCE_ROOT_ELEMENT_NAME, JAVA_SOURCE_TOKEN_TYPES);
        parser.setTreeAdaptor(treeAdaptor);
        parser.compilationUnit();
        return treeAdaptor.getDocument();
    }
}
