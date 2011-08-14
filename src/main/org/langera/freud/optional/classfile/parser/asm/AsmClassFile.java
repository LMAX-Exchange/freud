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

import org.langera.freud.optional.classfile.ClassFile;
import org.langera.freud.optional.classfile.ClassFileField;
import org.langera.freud.optional.classfile.ClassFileInnerClass;
import org.langera.freud.optional.classfile.method.ClassFileMethod;
import org.objectweb.asm.Opcodes;

import java.util.LinkedList;
import java.util.List;

final class AsmClassFile extends AsmElement implements ClassFile
{
    private final int version;
    private final String name;
    private final String signature;
    private final String superName;
    private final String[] interfaces;
    private final List<ClassFileField> fieldList;
    private final List<ClassFileMethod> methodList;
    private final List<ClassFileInnerClass> innerClassList;
    private String outerDesc;
    private String outerName;

    public AsmClassFile(final int version, final int access,
                        final String name, final String signature, final String superName,
                        final String... interfaces)
    {
        super(access);
        this.version = version;
        this.name = name;
        this.signature = signature;
        this.superName = superName;
        this.interfaces = interfaces;
        this.fieldList = new LinkedList<ClassFileField>();
        this.methodList = new LinkedList<ClassFileMethod>();
        this.innerClassList = new LinkedList<ClassFileInnerClass>();
    }

    @Override
    public int getVersion()
    {
        return version;
    }

    @Override
    public boolean isInterface()
    {
        return isAccessModifier(Opcodes.ACC_INTERFACE);
    }

    @Override
    public boolean isEnum()
    {
        return isAccessModifier(Opcodes.ACC_ENUM);
    }

    @Override
    public boolean isAnnotation()
    {
        return isAccessModifier(Opcodes.ACC_ANNOTATION);
    }

    @Override
    public boolean isAbstract()
    {
        return isAccessModifier(Opcodes.ACC_ABSTRACT);
    }

    @Override
    public boolean isSuper()
    {
        return isAccessModifier(Opcodes.ACC_SUPER);
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public String[] getInterfaces()
    {
        return interfaces;
    }

    @Override
    public String getSignature()
    {
        return signature;
    }

    @Override
    public String getSuperName()
    {
        return superName;
    }

    @Override
    public List<ClassFileField> getFieldList()
    {
        return fieldList;
    }

    @Override
    public List<ClassFileMethod> getMethodList()
    {
        return methodList;
    }

    @Override
    public List<ClassFileInnerClass> getInnerClassList()
    {
        return innerClassList;
    }

    protected String getOuterDesc()
    {
        return outerDesc;
    }

    protected String getOuterName()
    {
        return outerName;
    }

    protected void addField(final AsmField field)
    {
        fieldList.add(field);
    }

    protected void addMethod(final AsmMethod method)
    {
        methodList.add(method);
    }

    protected void addInnerClass(final AsmClassFile classFile, final String innerName, final int access)
    {
        final AsmInnerClass innerClass = new AsmInnerClass(classFile, innerName, access);
        innerClassList.add(innerClass);
        for (ClassFileMethod method : methodList)
        {
            if (innerClass.isAnonymous() &&
                    method.getName().equals(innerClass.getOuterName()) && method.getDesc().equals(innerClass.getOuterDesc()))
            {
                method.getAnonymousClassList().add(innerClass);
            }
        }
    }

    protected void setOuterValues(final String name, final String desc)
    {
        outerName = name;
        outerDesc = desc;
    }
}
