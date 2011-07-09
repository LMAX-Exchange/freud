package org.langera.freud.optional.classfile;

public final class Instruction
{

    private final Opcode opcode;
    private final int lineNumber;
    private final int intOperand;
    private final int varIndex;
    private final String operand;
    private final String owner;
    private final String name;
    private final String desc;
    private final String returnType;
    private final String[] args;

    public static Instruction create(final Opcode opcode, final int currentLineNumber)
    {
        return new Instruction(opcode, currentLineNumber, null, null, null, -1, null, -1, null, null);
    }

    public static Instruction create(final Opcode opcode, final int currentLineNumber, final String operand)
    {
        return new Instruction(opcode, currentLineNumber, null, null, operand, -1, null, -1, null, null);
    }

    public static Instruction create(final Opcode opcode, final int currentLineNumber, final String operand, final int varIndex)
    {
        return new Instruction(opcode, currentLineNumber, null, null, operand, -1, null, varIndex, null, null);
    }

    public static Instruction create(final Opcode opcode, final int currentLineNumber, final int intOperand)
    {
        return new Instruction(opcode, currentLineNumber, null, null, null, intOperand, null, -1, null, null);
    }

    public static Instruction createVarInstruction(final Opcode opcode, final int currentLineNumber, final int varIndex)
    {
        return new Instruction(opcode, currentLineNumber, null, null, null, -1, null, varIndex, null, null);
    }

    public static Instruction createFieldInstruction(final Opcode opcode, final int currentLineNumber, final String owner, final String name, final String desc)
    {
        return new Instruction(opcode, currentLineNumber, owner, name, null, -1, desc, -1, null, null);
    }

    public static Instruction createMethodInstruction(final Opcode opcode, final int currentLineNumber, final String owner, final String name, final String[] args, final String returnType)
    {
        return new Instruction(opcode, currentLineNumber, owner, name, null, -1, null, -1, args, returnType);
    }

    private Instruction(final Opcode opcode,
                       final int lineNumber,
                       final String owner,
                       final String name,
                       final String operand,
                       final int intOperand,
                       final String desc,
                       final int varIndex,
                       final String[] args,
                       final String returnType)
    {
        this.opcode = opcode;
        this.lineNumber = lineNumber;
        this.owner = owner;
        this.name = name;
        this.operand = operand;
        this.intOperand = intOperand;
        this.desc = desc;
        this.varIndex = varIndex;
        this.args = args;
        this.returnType = returnType;
    }

    public String[] getArgs()
    {
        return args;
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

    public String getReturnType()
    {
        return returnType;
    }

    public int getVarIndex()
    {
        return varIndex;
    }
}
