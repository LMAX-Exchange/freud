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

package org.langera.freud.optional.javasource.methoddecl;

import org.langera.freud.core.FreudBuilderException;
import org.langera.freud.core.FreudConfig;
import org.langera.freud.core.iterator.AnalysedObjectIterator;
import org.langera.freud.core.iterator.SubTypeAnalysedObjectIterator;
import org.langera.freud.core.iterator.SubTypeIteratorBuilder;
import org.langera.freud.optional.javasource.classdecl.ClassDeclaration;

import java.util.ArrayList;
import java.util.List;

public final class MethodDeclarationFreudConfig implements FreudConfig<MethodDeclaration>
{

    @Override
    public Class<?> supports()
    {
        return ClassDeclaration.class;
    }

    @Override
    @SuppressWarnings("unchecked")
    public AnalysedObjectIterator<MethodDeclaration> iteratorAdapter(final AnalysedObjectIterator<?> superTypeIterator) throws FreudBuilderException
    {
        return new SubTypeAnalysedObjectIterator<ClassDeclaration, MethodDeclaration>((AnalysedObjectIterator<ClassDeclaration>) superTypeIterator,
                new SubTypeIteratorBuilder<ClassDeclaration, MethodDeclaration>()
                {
                    @Override
                    public Iterable<MethodDeclaration> buildIterable(final ClassDeclaration superTypeItem)
                    {
                        List<MethodDeclaration> collector = new ArrayList<MethodDeclaration>();
                        collectMethodDeclarationsInsideClassDeclaration(superTypeItem, collector);
                        for (ClassDeclaration innerClassDecl : superTypeItem.getInnerClassDeclarationByNameMap().values())
                        {
                            collectMethodDeclarationsInsideClassDeclaration(innerClassDecl, collector);
                        }
                        return collector;
                    }
                }, MethodDeclaration.class);
    }

    private void collectMethodDeclarationsInsideClassDeclaration(final ClassDeclaration classDecl, final List<MethodDeclaration> collector)
    {
        for (List<MethodDeclaration> methodDeclarationList : classDecl.getMethodDeclarationListByNameMap().values())
        {
            collector.addAll(methodDeclarationList);
        }
    }
}
