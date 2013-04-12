package org.freud.analysed.classbytecode.method.instruction;

public interface OperandStack {
    String getOperandType();

    OperandStack next();

    Opcode getGeneratingOpcode();

    int depth();

    OperandStack dup(OperandStack next, final Opcode opcode);

    int getComputationalTypeCategory();
}
