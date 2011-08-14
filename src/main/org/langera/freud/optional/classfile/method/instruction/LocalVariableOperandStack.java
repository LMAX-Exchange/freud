/*
 * Copyright (c) 2011.
 * This file is part of "Freud".
 *
 * Freud is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Freud is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Freud.  If not, see <http://www.gnu.org/licenses/>.
 * @author Amir Langer  langera_at_gmail_dot_com
 */

package org.langera.freud.optional.classfile.method.instruction;

import org.langera.freud.optional.classfile.method.ClassFileMethod;
import org.langera.freud.optional.classfile.method.LocalVariable;

public final class LocalVariableOperandStack extends AbstractOperandStack
{
    private final ClassFileMethod method;
    private final int varIndex;
    private final int category;

    public LocalVariableOperandStack(final ClassFileMethod method, final int varIndex, final OperandStack next, final Opcode opcode)
    {
        super(next, opcode);
        this.varIndex = varIndex;
        this.method = method;
        this.category = calculateComputationalTypeCategory(method.getLocalVariableType(varIndex));
    }

    @Override
    protected String getTypeForCurrentOperandStackItem()
    {
        return getLocalVariable().getDesc();
    }


    public LocalVariable getLocalVariable()
    {
        return method.getLocalVariable(varIndex);
    }

    @Override
    protected String toStringInternal()
    {
        return "local variable " + varIndex;
    }

    @Override
    public int getComputationalTypeCategory()
    {
        return category;
    }

    @Override
    public OperandStack dup(final OperandStack next, final Opcode opcode)
    {
        return new LocalVariableOperandStack(method, varIndex, next, opcode);
    }
}
