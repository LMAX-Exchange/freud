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

import java.util.Arrays;

public final class MethodInvocationOperandStack extends AbstractOperandStack {
    private final MethodInvocationInstruction instruction;
    private final int category;

    public MethodInvocationOperandStack(final MethodInvocationInstruction instruction, final OperandStack next, final Opcode opcode) {
        super(popStack(instruction, next), opcode);
        this.instruction = instruction;
        this.category = calculateComputationalTypeCategory(instruction.getReturnType());
    }

    public static OperandStack popStack(final MethodInvocationInstruction instruction, final OperandStack next) {
        OperandStack stack = next;
        if (instruction.getOpcode() != Opcode.INVOKESTATIC) {
            stack = next.next(); // pop owner
        }
        return popArgs(instruction, stack);
    }

    private static OperandStack popArgs(final MethodInvocationInstruction instruction, final OperandStack next) {
        OperandStack stack = next;
        final int length = instruction.getArgs().length;
        for (int i = 0; i < length; i++) {
            stack = stack.next();
        }
        return stack;
    }

    @Override
    public int getComputationalTypeCategory() {
        return category;
    }

    @Override
    protected String getTypeForCurrentOperandStackItem() {
        return instruction.getReturnType();
    }

    @Override
    protected String toStringInternal() {
        return instruction.getReturnType() + " " + instruction.getName() + Arrays.toString(instruction.getArgs());
    }

    @Override
    public OperandStack dup(final OperandStack next, final Opcode opcode) {
        return new StaticOperandStack(instruction.getReturnType(), next, opcode);
    }
}
