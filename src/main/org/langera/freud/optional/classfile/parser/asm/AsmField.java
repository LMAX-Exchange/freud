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
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Opcodes;


final class AsmField extends AsmElement implements FieldVisitor, ClassFileField
{
    private final String name;
    private final String desc;
    private final String signature;
    private final Object value;

    public AsmField(final AsmClassFile classFile, final int access, final String name, final String desc, final String signature, final Object value)
    {
        super(access);
        this.name = name;
        this.desc = desc;
        this.signature = signature;
        this.value = value;
        classFile.addField(this);
    }

    @Override
    public boolean isEnumField()
    {
        return isAccessModifier(Opcodes.ACC_ENUM);
    }


    @Override
    public boolean isVolatile()
    {
        return isAccessModifier(Opcodes.ACC_VOLATILE);
    }


    @Override
    public boolean isTransient()
    {
        return isAccessModifier(Opcodes.ACC_TRANSIENT);
    }


    @Override
    public boolean isStatic()
    {
        return isAccessModifier(Opcodes.ACC_STATIC);
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public String getDesc()
    {
        return desc;
    }

    @Override
    public String getSignature()
    {
        return signature;
    }

    @Override
    public Object getValue()
    {
        return value;
    }

    @Override
    public AnnotationVisitor visitAnnotation(final String desc, final boolean visible)
    {
        return new AsmAnnotation(this, desc, visible);
    }

    @Override
    public void visitAttribute(final Attribute attr)
    {
    }

    @Override
    public void visitEnd()
    {
    }
}
