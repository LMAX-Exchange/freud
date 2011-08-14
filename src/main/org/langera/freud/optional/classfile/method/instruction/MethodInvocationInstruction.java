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

public final class MethodInvocationInstruction extends Instruction
{
    public MethodInvocationInstruction(final int index, final Opcode opcode, final int currentLineNumber,
                                       final String owner, final String name, final String[] args, final String returnType)
    {
        super(index, opcode, currentLineNumber, owner, name, null, null, -1, null, -1, null, args, returnType);
    }

    public void visit(final InstructionVisitor instructionVisitor)
    {
        instructionVisitor.methodInvocation(this, getOwner(), getName(), getArgs());
    }


    @Override
    public String toString()
    {
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
