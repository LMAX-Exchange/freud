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

package org.langera.freud.optional.javasource.block;

import org.langera.freud.core.FreudBuilderException;
import org.langera.freud.core.FreudConfig;
import org.langera.freud.core.iterator.AnalysedObjectIterator;
import org.langera.freud.core.iterator.SubTypeAnalysedObjectIterator;
import org.langera.freud.core.iterator.SubTypeIteratorBuilder;
import org.langera.freud.optional.javasource.classdecl.ClassDeclaration;
import org.langera.freud.optional.javasource.methoddecl.MethodDeclaration;

import java.util.ArrayList;
import java.util.List;

public class CodeBlockFreudConfig implements FreudConfig<CodeBlock>
{
    @Override
    public Class<?> supports()
    {
        return ClassDeclaration.class;
    }

    @Override
    @SuppressWarnings("unchecked")
    public AnalysedObjectIterator<CodeBlock> iteratorAdapter(final AnalysedObjectIterator<?> superTypeIterator) throws FreudBuilderException
    {
        return new SubTypeAnalysedObjectIterator<ClassDeclaration, CodeBlock>((AnalysedObjectIterator<ClassDeclaration>) superTypeIterator,
                new SubTypeIteratorBuilder<ClassDeclaration, CodeBlock>()
                {
                    @Override
                    public Iterable<CodeBlock> buildIterable(final ClassDeclaration superTypeItem)
                    {
                        List<CodeBlock> collector = new ArrayList<CodeBlock>();
                        collectCodeBlocksInsideClassDeclaration(superTypeItem, collector);
                        for (ClassDeclaration innerClassDecl : superTypeItem.getInnerClassDeclarationByNameMap().values())
                        {
                            collectCodeBlocksInsideClassDeclaration(innerClassDecl, collector);
                        }
                        return collector;
                    }
                }, CodeBlock.class);
    }

    private void collectCodeBlocksInsideClassDeclaration(final ClassDeclaration classDecl, final List<CodeBlock> collector)
    {
        for (List<MethodDeclaration> methodDeclarationList : classDecl.getMethodDeclarationListByNameMap().values())
        {
            for (MethodDeclaration methodDeclaration : methodDeclarationList)
            {
                collector.add(methodDeclaration.getImplementation());
            }
        }
    }
}
