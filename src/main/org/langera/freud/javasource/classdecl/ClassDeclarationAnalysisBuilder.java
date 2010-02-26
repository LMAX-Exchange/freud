package org.langera.freud.javasource.classdecl;

import org.langera.freud.AbstractAnalysisBuilder;
import org.langera.freud.dsl.BooleanOperatorDsl;
import org.langera.freud.javasource.classdecl.assertion.HasMethodDeclarationAssertion;

/**
 *   This file is part of "Freud".
 *
 *   Freud is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU Lesser General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   Freud is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with Freud.  If not, see <http://www.gnu.org/licenses/>.
 *
 *   @author Amir Langer  langera_at_gmail_dot_com
**/

public final class
        ClassDeclarationAnalysisBuilder
        extends AbstractAnalysisBuilder<ClassDeclarationAnalysisBuilder, ClassDeclaration>
        implements ClassDeclarationDsl
{
    public BooleanOperatorDsl<ClassDeclarationAnalysisBuilder> classDeclaration()
    {
        return trueAssertion();
    }

    public BooleanOperatorDsl<ClassDeclarationAnalysisBuilder> hasDeclaredMethod(String methodName)
    {
        setAssertion(new HasMethodDeclarationAssertion(methodName));
        return this;
    }

    public Class<ClassDeclaration> getType()
    {
        return ClassDeclaration.class;
    }
}

