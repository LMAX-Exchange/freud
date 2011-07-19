package org.langera.freud.optional.classfile.method.instruction;

public interface OperandStack
{
    String getOperandType(int index);

    OperandStack next();

    Opcode generatingOpcode();
}
