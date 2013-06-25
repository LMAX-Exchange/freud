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

public final class MethodInvocationInstruction extends Instruction {
    public MethodInvocationInstruction(final int index, final Opcode opcode, final int currentLineNumber,
                                       final String owner, final String name, final String[] args, final String returnType) {
        super(index, opcode, currentLineNumber, owner, name, null, null, -1, null, -1, null, args, returnType);
    }

    public void visit(final InstructionVisitor instructionVisitor) {
        instructionVisitor.methodInvocation(this, getOwner(), getName(), getArgs());
    }


    @Override
    public String toString() {
        return "Instruction{" +
                "index=" + getInstructionIndex() +
                ", opcode=" + getOpcode() +
                ", lineNumber=" + getLineNumber() +
                ", owner='" + getOwner() + '\'' +
                ", name='" + getName() + '\'' +
                ", returnType='" + getReturnType() + '\'' +
                ", args=" + (getArgs() == null ? null : Arrays.asList(getArgs())) +
                '}';
    }
}
