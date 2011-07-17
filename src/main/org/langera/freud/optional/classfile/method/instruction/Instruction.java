package org.langera.freud.optional.classfile.method.instruction;

import org.langera.freud.optional.classfile.method.ClassFileMethod;
import org.langera.freud.optional.classfile.method.ref.LoadedReference;

public class Instruction
{
    private final int index;
    private final Opcode opcode;
    private final int lineNumber;
    private final int intOperand;
    private final int varIndex;
    private final String operand;
    private final String owner;
    private final String name;
    private final String desc;
    private final Label label;
    private final String returnType;
    private final String[] args;
    private final ClassFileMethod method;
    private final LoadedReference loadedReference;

    public Instruction(final ClassFileMethod method, final int index, final Opcode opcode, final int currentLineNumber)
    {
        this(method, index, opcode, currentLineNumber, null, null, null, -1, null, -1, null, null, null);
    }

    protected Instruction(final ClassFileMethod method,
                          final int index,
                          final Opcode opcode,
                          final int lineNumber,
                          final String owner,
                          final String name,
                          final String operand,
                          final int intOperand,
                          final String desc,
                          final int varIndex,
                          final Label label,
                          final String[] args,
                          final String returnType)
    {
        this.method = method;
        this.index = index;
        this.opcode = opcode;
        this.lineNumber = lineNumber;
        this.owner = owner;
        this.name = name;
        this.operand = operand;
        this.intOperand = intOperand;
        this.desc = desc;
        this.varIndex = varIndex;
        this.label = label;
        this.args = args;
        this.returnType = returnType;
        loadedReference = opcode.createLoadedReference(method, this);
    }

    public int getInstructionIndex()
    {
        return index;
    }

    public LoadedReference getLoadedReference()
    {
        return loadedReference;
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

    public void visit(final InstructionVisitor instructionVisitor)
    {
        instructionVisitor.noArgInstruction(this);
    }
}
