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

public class Instruction
{
    private final int index;
    private final Opcode opcode;
    private final int lineNumber;
    private final int intOperand;
    private final int varIndex;
    private final String operandType;
    private final Object constant;
    private final String owner;
    private final String name;
    private final String desc;
    private final Label label;
    private final String returnType;
    private final String[] args;
    private OperandStack operandStack;

    public Instruction(final int index, final Opcode opcode, final int currentLineNumber)
    {
        this(index, opcode, currentLineNumber, null, null, null, null, -1, null, -1, null, null, null);
    }

    protected Instruction(final int index,
                          final Opcode opcode,
                          final int lineNumber,
                          final String owner,
                          final String name,
                          final String operandType,
                          final Object constant,
                          final int intOperand,
                          final String desc,
                          final int varIndex,
                          final Label label,
                          final String[] args,
                          final String returnType)
    {
        this.index = index;
        this.opcode = opcode;
        this.lineNumber = lineNumber;
        this.owner = owner;
        this.name = name;
        this.operandType = operandType;
        this.constant = constant;
        this.intOperand = intOperand;
        this.desc = desc;
        this.varIndex = varIndex;
        this.label = label;
        this.args = args;
        this.returnType = returnType;
    }

    public int getInstructionIndex()
    {
        return index;
    }

    public OperandStack getOperandStack()
    {
        return operandStack;
    }

    public void setOperandStack(final OperandStack operandStack)
    {
        this.operandStack = operandStack;
    }

    public String[] getArgs()
    {
        return args;
    }

    public Label getLabel()
    {
        return label;
    }

    public String getDesc()
    {
        return desc;
    }

    public int getIntOperand()
    {
        return intOperand;
    }

    public int getLineNumber()
    {
        return lineNumber;
    }

    public String getName()
    {
        return name;
    }

    public Opcode getOpcode()
    {
        return opcode;
    }

    public String getOperandType()
    {
        return operandType;
    }

    public Object getConstant()
    {
        return constant;
    }

    public String getOwner()
    {
        return owner;
    }

    public String getReturnType()
    {
        return returnType;
    }

    public int getVarIndex()
    {
        return varIndex;
    }

    public void visit(final InstructionVisitor instructionVisitor)
    {
        instructionVisitor.noArgInstruction(this);
    }

    @Override
    public String toString()
    {
        return "Instruction{" +
                "index=" + index +
                ", opcode=" + opcode +
                ", lineNumber=" + lineNumber +
                '}';
    }
}
