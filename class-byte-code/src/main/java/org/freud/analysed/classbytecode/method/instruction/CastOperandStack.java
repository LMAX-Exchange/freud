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

public final class CastOperandStack extends AbstractOperandStack {
    private final String fromType;
    private final String toType;
    private final int category;

    public CastOperandStack(final String fromType, final String toType, final OperandStack next, final Opcode opcode) {
        super(next, opcode);
        this.fromType = fromType;
        this.toType = toType;
        this.category = calculateComputationalTypeCategory(toType);
    }

    @Override
    protected String getTypeForCurrentOperandStackItem() {
        return fromType;
    }

    @Override
    protected String toStringInternal() {
        return "cast to " + toType;
    }

    public String getFromType() {
        return fromType;
    }

    @Override
    public OperandStack dup(final OperandStack next, final Opcode opcode) {
        return new CastOperandStack(fromType, toType, next, opcode);
    }

    @Override
    public int getComputationalTypeCategory() {
        return category;
    }
}
