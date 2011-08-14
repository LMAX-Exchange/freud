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

import java.util.Arrays;

public final class MethodInvocationOperandStack extends AbstractOperandStack
{
    private final MethodInvocationInstruction instruction;
    private final int category;

    public MethodInvocationOperandStack(final MethodInvocationInstruction instruction, final OperandStack next, final Opcode opcode)
    {
        super(popStack(instruction, next), opcode);
        this.instruction = instruction;
        this.category = calculateComputationalTypeCategory(instruction.getReturnType());
    }

    public static OperandStack popStack(final MethodInvocationInstruction instruction, final OperandStack next)
    {
        OperandStack stack = next;
        if (instruction.getOpcode() != Opcode.INVOKESTATIC)
        {
            stack = next.next(); // pop owner
        }
        return popArgs(instruction, stack);
    }

    private static OperandStack popArgs(final MethodInvocationInstruction instruction, final OperandStack next)
    {
        OperandStack stack = next;
        final int length = instruction.getArgs().length;
        for (int i = 0; i < length; i++)
        {
            stack = stack.next();
        }
        return stack;
    }

    @Override
    public int getComputationalTypeCategory()
    {
        return category;
    }

    @Override
    protected String getTypeForCurrentOperandStackItem()
    {
        return instruction.getReturnType();
    }

    @Override
    protected String toStringInternal()
    {
        return instruction.getReturnType() + " " + instruction.getName() + Arrays.toString(instruction.getArgs());
    }

    @Override
    public OperandStack dup(final OperandStack next, final Opcode opcode)
    {
        return new StaticOperandStack(instruction.getReturnType(), next, opcode);
    }
}
