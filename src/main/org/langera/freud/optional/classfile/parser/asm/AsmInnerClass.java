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

import org.langera.freud.optional.classfile.ClassFileField;
import org.langera.freud.optional.classfile.ClassFileInnerClass;
import org.langera.freud.optional.classfile.method.ClassFileMethod;
import org.objectweb.asm.Opcodes;

import java.util.List;

final class AsmInnerClass extends AsmElement implements ClassFileInnerClass
{
    private final AsmClassFile classFile;
    private final String innerName;

    AsmInnerClass(final AsmClassFile classFile, final String innerName, final int access)
    {
        super(access);
        this.classFile = classFile;
        this.innerName = innerName;
    }

    @Override
    public boolean isAnonymous()
    {
        return innerName == null;
    }

    @Override
    public boolean isStatic()
    {
        return isAccessModifier(Opcodes.ACC_STATIC);
    }

    @Override
    public String getOuterName()
    {
        return classFile.getOuterName();
    }

    @Override
    public String getOuterDesc()
    {
        return classFile.getOuterDesc();
    }

    @Override
    public List<ClassFileInnerClass> getInnerClassList()
    {
        return classFile.getInnerClassList();
    }

    @Override
    public List<ClassFileField> getFieldList()
    {
        return classFile.getFieldList();
    }

    @Override
    public List<ClassFileMethod> getMethodList()
    {
        return classFile.getMethodList();
    }

    @Override
    public String getSuperName()
    {
        return classFile.getSuperName();
    }

    @Override
    public String getSignature()
    {
        return classFile.getSignature();
    }

    @Override
    public String[] getInterfaces()
    {
        return classFile.getInterfaces();
    }

    @Override
    public String getName()
    {
        return classFile.getName();
    }

    @Override
    public boolean isSuper()
    {
        return classFile.isSuper();
    }

    @Override
    public boolean isAbstract()
    {
        return classFile.isAbstract();
    }

    @Override
    public boolean isAnnotation()
    {
        return classFile.isAnnotation();
    }

    @Override
    public boolean isEnum()
    {
        return classFile.isEnum();
    }

    @Override
    public boolean isInterface()
    {
        return classFile.isInterface();
    }

    @Override
    public int getVersion()
    {
        return classFile.getVersion();
    }
}
