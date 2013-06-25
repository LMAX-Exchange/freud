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

public abstract class AbstractOperandStack implements OperandStack {
    public static final OperandStack EMPTY_STACK = new AbstractOperandStack(null, null) {
        @Override
        protected String getTypeForCurrentOperandStackItem() {
            return null;
        }

        @Override
        public OperandStack next() {
            return null;
        }

        @Override
        public OperandStack dup(final OperandStack next, final Opcode opcode) {
            throw new UnsupportedOperationException("Cannot duplicate empty stack");
        }

        @Override
        public int depth() {
            return 0;
        }

        @Override
        public int getComputationalTypeCategory() {
            return 0;
        }

        @Override
        protected String toStringInternal() {
            return "";
        }
    };

    private final OperandStack next;
    private final Opcode opcode;


    public AbstractOperandStack(final OperandStack next, final Opcode opcode) {
        this.next = next;
        this.opcode = opcode;
    }

    protected static int calculateComputationalTypeCategory(String type) {
        return ("J".equals(type) || "D".equals(type)) ? 2 : 1;
    }

    protected abstract String getTypeForCurrentOperandStackItem();

    @Override
    public String getOperandType() {
        return getTypeForCurrentOperandStackItem();
    }

    @Override
    public OperandStack next() {
        return next;
    }

    @Override
    public Opcode getGeneratingOpcode() {
        return opcode;
    }

    @Override
    public int depth() {
        return 1 + next.depth();
    }

    @Override
    public final String toString() {
        return toStringInternal() + "|" + (next == null ? "" : next);
    }

    protected abstract String toStringInternal();
}
