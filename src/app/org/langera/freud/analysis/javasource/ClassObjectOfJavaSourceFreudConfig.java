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

package org.langera.freud.analysis.javasource;

import org.langera.freud.core.FreudBuilderException;
import org.langera.freud.core.FreudConfig;
import org.langera.freud.core.iterator.AnalysedObjectIterator;
import org.langera.freud.core.iterator.SubTypeAnalysedObjectIterator;
import org.langera.freud.core.iterator.SubTypeIteratorBuilder;
import org.langera.freud.optional.javasource.JavaSource;
import org.langera.freud.optional.javasource.classdecl.ClassDeclaration;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public final class ClassObjectOfJavaSourceFreudConfig implements FreudConfig<Class>
{
    @Override
    public Class<?> supports()
    {
        return JavaSource.class;
    }


    // inner / anonymous classes?????? test

    @Override
    @SuppressWarnings("unchecked")
    public AnalysedObjectIterator<Class> iteratorAdapter(final AnalysedObjectIterator<?> superTypeIterator) throws FreudBuilderException
    {
        return new SubTypeAnalysedObjectIterator<JavaSource, Class>((AnalysedObjectIterator<JavaSource>) superTypeIterator,
                new SubTypeIteratorBuilder<JavaSource, Class>()
                {
                    @Override
                    public Iterable<Class> buildIterable(final JavaSource javaSource)
                    {
                        final String packageName = javaSource.getPackageDeclaration().getPackagePathAsString() + ".";
                        final List<ClassDeclaration> collector = new ArrayList<ClassDeclaration>();
                        int collectIndex = 0;
                        collector.add(javaSource.getClassDeclaration());
                        while (collectIndex < collector.size())
                        {
                            ClassDeclaration classDeclaration = collector.get(collectIndex++);
                            final Map<String,ClassDeclaration> innerClassDeclarationByNameMap = classDeclaration.getInnerClassDeclarationByNameMap();
                            collector.addAll(innerClassDeclarationByNameMap.values());
                        }
                        final List<Class> classes = new LinkedList<Class>();
                        for (ClassDeclaration classDeclaration : collector)
                        {
                            final String fullClassName = getFullClassName(packageName, classDeclaration);
                            classes.add(loadClass(fullClassName));
                        }
                        return classes;
                    }
                }, Class.class);
    }

    private String getFullClassName(final String packageName, final ClassDeclaration classDeclaration)
    {
        return (classDeclaration.getOuterClassDeclaration() == null)
                    ? topLevelClassName(packageName, classDeclaration) : innerClassName(packageName, classDeclaration);
    }

    private String innerClassName(final String packageName, final ClassDeclaration classDeclaration)
    {
        final String outerClassName = getFullClassName(packageName, classDeclaration.getOuterClassDeclaration());
        return outerClassName + "$" + classDeclaration.getName();
    }

    private String topLevelClassName(final String packageName, final ClassDeclaration classDeclaration)
    {
        return packageName + classDeclaration.getName();
    }

    private static Class loadClass(final String fullClassName)
    {
        try
        {
            return Class.forName(fullClassName);
        }
        catch (ClassNotFoundException e)
        {
            throw new RuntimeException("Failed to load class [" + fullClassName + "]", e);
        }
    }

}
