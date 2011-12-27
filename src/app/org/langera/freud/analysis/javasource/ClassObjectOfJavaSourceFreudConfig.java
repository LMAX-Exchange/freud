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
                            collector.addAll(classDeclaration.getInnerClassDeclarationByNameMap().values());
                        }
                        final List<Class> classes = new LinkedList<Class>();
                        for (ClassDeclaration classDeclaration : collector)
                        {
                            classes.add(loadClass(packageName + classDeclaration.getName()));
                        }
                        return classes;
                    }
                }, Class.class);
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
