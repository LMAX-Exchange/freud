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

import org.freud.analysed.classbytecode.ClassByteCodeAnnotation;
import org.freud.analysed.classbytecode.ClassByteCodeElement;
import org.objectweb.asm.Opcodes;

import java.util.LinkedList;
import java.util.List;

abstract class AsmElement implements ClassByteCodeElement {
    private final int access;
    private final List<ClassByteCodeAnnotation> annotationList;

    protected AsmElement(final int access) {
        this.access = access;
        this.annotationList = new LinkedList<ClassByteCodeAnnotation>();
    }

    @Override
    public boolean isPublic() {
        return isAccessModifier(Opcodes.ACC_PUBLIC);
    }

    @Override
    public boolean isPrivate() {
        return isAccessModifier(Opcodes.ACC_PRIVATE);
    }

    @Override
    public boolean isProtected() {
        return isAccessModifier(Opcodes.ACC_PROTECTED);
    }

    @Override
    public boolean isFinal() {
        return isAccessModifier(Opcodes.ACC_FINAL);
    }

    @Override
    public boolean isSynthetic() {
        return isAccessModifier(Opcodes.ACC_SYNTHETIC);
    }

    @Override
    public List<ClassByteCodeAnnotation> getAnnotationList() {
        return annotationList;
    }

    protected boolean isAccessModifier(final int mask) {
        return (access & mask) != 0;
    }

    protected void addAnnotation(AsmAnnotation annotation) {
        annotationList.add(annotation);
    }
}
