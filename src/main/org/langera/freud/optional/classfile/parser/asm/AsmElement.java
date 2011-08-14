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

package org.langera.freud.optional.classfile.parser.asm;

import org.langera.freud.optional.classfile.ClassFileAnnotation;
import org.langera.freud.optional.classfile.ClassFileElement;
import org.objectweb.asm.Opcodes;

import java.util.LinkedList;
import java.util.List;

abstract class AsmElement implements ClassFileElement
{
    private final int access;
    private final List<ClassFileAnnotation> annotationList;

    protected AsmElement(final int access)
    {
        this.access = access;
        this.annotationList = new LinkedList<ClassFileAnnotation>();
    }

    @Override
    public boolean isPublic()
    {
        return isAccessModifier(Opcodes.ACC_PUBLIC);
    }

    @Override
    public boolean isPrivate()
    {
        return isAccessModifier(Opcodes.ACC_PRIVATE);
    }

    @Override
    public boolean isProtected()
    {
        return isAccessModifier(Opcodes.ACC_PROTECTED);
    }

    @Override
    public boolean isFinal()
    {
        return isAccessModifier(Opcodes.ACC_FINAL);
    }

    @Override
    public boolean isSynthetic()
    {
        return isAccessModifier(Opcodes.ACC_SYNTHETIC);
    }

    @Override
    public List<ClassFileAnnotation> getAnnotationList()
    {
        return annotationList;
    }

    protected boolean isAccessModifier(final int mask)
    {
        return (access & mask) != 0;
    }

    protected void addAnnotation(AsmAnnotation annotation)
    {
        annotationList.add(annotation);
    }
}
