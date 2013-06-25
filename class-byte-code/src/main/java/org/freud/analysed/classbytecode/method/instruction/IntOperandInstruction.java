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

public final class IntOperandInstruction extends Instruction {

    public IntOperandInstruction(final int index, final Opcode opcode, final int currentLineNumber, final int intOperand) {
        super(index, opcode, currentLineNumber, null, null, null, null, intOperand, null, -1, null, null, null);
    }

    @Override
    public String toString() {
        return "Instruction{" +
                "index=" + getInstructionIndex() +
                ", opcode=" + getOpcode() +
                ", lineNumber=" + getLineNumber() +
                ", intOperand=" + getIntOperand() +
                '}';
    }
}
