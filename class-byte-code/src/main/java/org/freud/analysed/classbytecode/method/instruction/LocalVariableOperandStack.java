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

package org.freud.analysed.classbytecode.method.instruction;

import org.freud.analysed.classbytecode.method.ClassByteCodeMethod;
import org.freud.analysed.classbytecode.method.LocalVariable;

public final class LocalVariableOperandStack extends AbstractOperandStack {
    private final ClassByteCodeMethod method;
    private final int varIndex;
    private final int category;

    public LocalVariableOperandStack(final ClassByteCodeMethod method, final int varIndex, final OperandStack next, final Opcode opcode) {
        super(next, opcode);
        this.varIndex = varIndex;
        this.method = method;
        this.category = calculateComputationalTypeCategory(method.getLocalVariableType(varIndex));
    }

    @Override
    protected String getTypeForCurrentOperandStackItem() {
        return getLocalVariable().getDesc();
    }


    public LocalVariable getLocalVariable() {
        return method.getLocalVariable(varIndex);
    }

    @Override
    protected String toStringInternal() {
        return "local variable " + varIndex;
    }

    @Override
    public int getComputationalTypeCategory() {
        return category;
    }

    @Override
    public OperandStack dup(final OperandStack next, final Opcode opcode) {
        return new LocalVariableOperandStack(method, varIndex, next, opcode);
    }
}
