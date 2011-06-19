package org.langera.freud.optional.classfile.parser.asm;

import org.langera.freud.optional.classfile.Instruction;
import org.langera.freud.optional.classfile.Opcode;

final class AsmInstruction implements Instruction
{

    private final Opcode opcode;
    private final int lineNumber;
    private final int intOperand;
    private final int varIndex;
    private final String operand;
    private final Class typeOperand;
    private final String owner;
    private final String name;
    private final String desc;

    static AsmInstruction getInstance(final Opcode opcode, final int lineNumber)
    {
        return new AsmInstruction(opcode, lineNumber, -1, -1, null, null, null, null, null);
    }

    static AsmInstruction getInstance(final Opcode opcode, final int lineNumber, final int operand, final int varIndex)
    {
        return new AsmInstruction(opcode, lineNumber, operand, varIndex, null, null, null, null, null);
    }

    static AsmInstruction getInstance(final Opcode opcode, final int lineNumber, final String operand, final int intOperand)
    {
        return new AsmInstruction(opcode, lineNumber, intOperand, -1, operand, null, null, null, null);
    }

    static AsmInstruction getInstance(final Opcode opcode, final int lineNumber, final Class operand, final int intOperand)
    {
        return new AsmInstruction(opcode, lineNumber, intOperand, -1, null, operand, null, null, null);
    }

    static AsmInstruction getInstance(final Opcode opcode, final int lineNumber, final String owner, final String name, final String desc)
    {
        return new AsmInstruction(opcode, lineNumber, -1, -1, null, null, owner, name, desc);
    }

    private AsmInstruction(final Opcode opcode, final int lineNumber,
                           final int intOperand, final int varIndex, final String operand, final Class typeOperand,
                           final String owner, final String name, final String desc)
    {
        this.opcode = opcode;
        this.lineNumber = lineNumber;
        this.intOperand = intOperand;
        this.varIndex = varIndex;
        this.operand = operand;
        this.typeOperand = typeOperand;
        this.owner = owner;
        this.name = name;
        this.desc = desc;

        System.out.println(opcode);
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

    public String getOperand()
    {
        return operand;
    }

    public String getOwner()
    {
        return owner;
    }

    public Class getTypeOperand()
    {
        return typeOperand;
    }
}
