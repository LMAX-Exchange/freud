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

package org.freud.analysed.classbytecode.method;


import org.freud.analysed.classbytecode.ClassByteCodeElement;
import org.freud.analysed.classbytecode.ClassByteCodeInnerClass;
import org.freud.analysed.classbytecode.method.instruction.Instruction;
import org.freud.analysed.classbytecode.method.instruction.InstructionVisitor;

import java.util.List;

public interface ClassByteCodeMethod extends ClassByteCodeElement {
    String getName();

    String getDesc();

    String getSignature();

    String[] getExceptions();

    boolean isStatic();

    boolean isSynchronized();

    boolean isBridge();

    boolean isVarargs();

    boolean isNative();

    boolean isStrict();

    boolean isAbstract();

    List<ClassByteCodeInnerClass> getAnonymousClassList();

    LocalVariable getLocalVariable(String name);

    LocalVariable getLocalVariable(int index);

    void findInstruction(final InstructionVisitor instructionVisitor);

    Instruction getInstruction(int index);

    String getReturnType();

    String getLocalVariableType(int index);
}
