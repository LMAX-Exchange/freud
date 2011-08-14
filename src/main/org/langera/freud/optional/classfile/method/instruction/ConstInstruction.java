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

public final class ConstInstruction extends Instruction
{
    public ConstInstruction(final int index, final Opcode opcode, final int currentLineNumber, final Object constant)
    {
        super(index, opcode, currentLineNumber, null, null,
                AbstractInstructionVisitor.typeEncoding(constant.getClass()), constant, -1, null, -1, null, null, null);

    }

    @Override
    public String toString()
    {
        return "ConstInstruction{" +
                "index=" + getInstructionIndex() +
                ", opcode=" + getOpcode() +
                ", lineNumber=" + getLineNumber() +
                ", operandType='" + getOperandType() + '\'' +
                ", constant=" + getConstant() +
                '}';
    }
}
