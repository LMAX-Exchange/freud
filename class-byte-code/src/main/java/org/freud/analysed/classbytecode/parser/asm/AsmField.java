/*
 * Copyright 2013 LMAX Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.freud.analysed.classbytecode.parser.asm;

import org.freud.analysed.classbytecode.ClassByteCodeField;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Opcodes;


final class AsmField extends AsmElement implements FieldVisitor, ClassByteCodeField {
    private final String name;
    private final String desc;
    private final String signature;
    private final Object value;

    public AsmField(final AsmClassByteCode classByteCode, final int access, final String name, final String desc, final String signature, final Object value) {
        super(access);
        this.name = name;
        this.desc = desc;
        this.signature = signature;
        this.value = value;
        classByteCode.addField(this);
    }

    @Override
    public boolean isEnumField() {
        return isAccessModifier(Opcodes.ACC_ENUM);
    }


    @Override
    public boolean isVolatile() {
        return isAccessModifier(Opcodes.ACC_VOLATILE);
    }


    @Override
    public boolean isTransient() {
        return isAccessModifier(Opcodes.ACC_TRANSIENT);
    }


    @Override
    public boolean isStatic() {
        return isAccessModifier(Opcodes.ACC_STATIC);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    @Override
    public String getSignature() {
        return signature;
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public AnnotationVisitor visitAnnotation(final String desc, final boolean visible) {
        return new AsmAnnotation(this, desc, visible);
    }

    @Override
    public void visitAttribute(final Attribute attr) {
    }

    @Override
    public void visitEnd() {
    }
}
