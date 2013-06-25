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

public final class FieldInstruction extends Instruction {

    public FieldInstruction(final int index,
                            final Opcode opcode, final int currentLineNumber,
                            final String owner, final String name, final String desc) {
        super(index, opcode, currentLineNumber, owner, name, null, null, -1, desc, -1, null, null, null);
    }

    @Override
    public String toString() {
        return "Instruction{" +
                "index=" + getInstructionIndex() +
                ", opcode=" + getOpcode() +
                ", lineNumber=" + getLineNumber() +
                ", owner='" + getOwner() + '\'' +
                ", name='" + getName() + '\'' +
                ", desc='" + getDesc() + '\'' +
                '}';
    }
}
