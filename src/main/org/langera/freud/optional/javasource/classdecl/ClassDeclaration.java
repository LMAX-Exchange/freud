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

package org.langera.freud.optional.javasource.classdecl;


import org.langera.freud.optional.javasource.methoddecl.MethodDeclaration;

import java.util.List;
import java.util.Map;


public interface ClassDeclaration
{
    public enum DeclarationType
    {
        CLASS, INTERFACE, ENUM, AT
    }

    String[] getDeclaredClassAnnotations();

    DeclarationType getDeclarationType();

    String getName();

    String getSuperClassName();

    String[] getDeclaredImplementedInterfaceNames();

    long getModifierMask();

    //    CodeBlock getStaticBlock();

    Map<String, List<MethodDeclaration>> getMethodDeclarationListByNameMap();

    //    VarDeclaration[] getFieldDeclarations();

    Map<String, ClassDeclaration> getInnerClassDeclarationByNameMap();

    ClassDeclaration getOuterClassDeclaration();
//    AnnotationDeclaration[] getInnerAnnotationDeclarations();    
}
