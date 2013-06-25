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

import org.freud.analysed.classbytecode.ClassByteCode;
import org.freud.analysed.classbytecode.ClassByteCodeField;
import org.freud.analysed.classbytecode.ClassByteCodeInnerClass;
import org.freud.analysed.classbytecode.method.ClassByteCodeMethod;
import org.objectweb.asm.Opcodes;

import java.util.LinkedList;
import java.util.List;

final class AsmClassByteCode extends AsmElement implements ClassByteCode {
    private final int version;
    private final String name;
    private final String signature;
    private final String superName;
    private final String[] interfaces;
    private final List<ClassByteCodeField> fieldList;
    private final List<ClassByteCodeMethod> methodList;
    private final List<ClassByteCodeInnerClass> innerClassList;
    private String outerDesc;
    private String outerName;

    public AsmClassByteCode(final int version, final int access,
                            final String name, final String signature, final String superName,
                            final String... interfaces) {
        super(access);
        this.version = version;
        this.name = name;
        this.signature = signature;
        this.superName = superName;
        this.interfaces = interfaces;
        this.fieldList = new LinkedList<ClassByteCodeField>();
        this.methodList = new LinkedList<ClassByteCodeMethod>();
        this.innerClassList = new LinkedList<ClassByteCodeInnerClass>();
    }

    @Override
    public int getVersion() {
        return version;
    }

    @Override
    public boolean isInterface() {
        return isAccessModifier(Opcodes.ACC_INTERFACE);
    }

    @Override
    public boolean isEnum() {
        return isAccessModifier(Opcodes.ACC_ENUM);
    }

    @Override
    public boolean isAnnotation() {
        return isAccessModifier(Opcodes.ACC_ANNOTATION);
    }

    @Override
    public boolean isAbstract() {
        return isAccessModifier(Opcodes.ACC_ABSTRACT);
    }

    @Override
    public boolean isSuper() {
        return isAccessModifier(Opcodes.ACC_SUPER);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String[] getInterfaces() {
        return interfaces;
    }

    @Override
    public String getSignature() {
        return signature;
    }

    @Override
    public String getSuperName() {
        return superName;
    }

    @Override
    public List<ClassByteCodeField> getFieldList() {
        return fieldList;
    }

    @Override
    public List<ClassByteCodeMethod> getMethodList() {
        return methodList;
    }

    @Override
    public List<ClassByteCodeInnerClass> getInnerClassList() {
        return innerClassList;
    }

    protected String getOuterDesc() {
        return outerDesc;
    }

    protected String getOuterName() {
        return outerName;
    }

    protected void addField(final AsmField field) {
        fieldList.add(field);
    }

    protected void addMethod(final AsmMethod method) {
        methodList.add(method);
    }

    protected void addInnerClass(final AsmClassByteCode classByteCode, final String innerName, final int access) {
        final AsmInnerClass innerClass = new AsmInnerClass(classByteCode, innerName, access);
        innerClassList.add(innerClass);
        for (ClassByteCodeMethod method : methodList) {
            if (innerClass.isAnonymous() &&
                    method.getName().equals(innerClass.getOuterName()) && method.getDesc().equals(innerClass.getOuterDesc())) {
                method.getAnonymousClassList().add(innerClass);
            }
        }
    }

    protected void setOuterValues(final String name, final String desc) {
        outerName = name;
        outerDesc = desc;
    }
}
