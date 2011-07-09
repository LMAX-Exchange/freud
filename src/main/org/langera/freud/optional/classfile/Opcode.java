package org.langera.freud.optional.classfile;

public enum Opcode
{
    NOP, // visitInsn
    ACONST_NULL, // -
    ICONST_M1, // -
    ICONST_0, // -
    ICONST_1, // -
    ICONST_2, // -
    ICONST_3, // -
    ICONST_4, // -
    ICONST_5, // -
    LCONST_0, // -
    LCONST_1, // -
    FCONST_0, // -
    FCONST_1, // -
    FCONST_2, // -
    DCONST_0, // -
    DCONST_1, // -
    BIPUSH, // visitIntInsn
    SIPUSH, // -
    LDC, // visitLdcInsn
    LDC_W, // -
    LDC2_W, // -
    ILOAD, // visitVarInsn
    LLOAD, // -
    FLOAD, // -
    DLOAD, // -
    ALOAD, // -
    ILOAD_0, // -
    ILOAD_1, // -
    ILOAD_2, // -
    ILOAD_3, // -
    LLOAD_0, // -
    LLOAD_1, // -
    LLOAD_2, // -
    LLOAD_3, // -
    FLOAD_0, // -
    FLOAD_1, // -
    FLOAD_2, // -
    FLOAD_3, // -
    DLOAD_0, // -
    DLOAD_1, // -
    DLOAD_2, // -
    DLOAD_3, // -
    ALOAD_0, // -
    ALOAD_1, // -
    ALOAD_2, // -
    ALOAD_3, // -
    IALOAD, // visitInsn
    LALOAD, // -
    FALOAD, // -
    DALOAD, // -
    AALOAD, // -
    BALOAD, // -
    CALOAD, // -
    SALOAD, // -
    ISTORE, // visitVarInsn
    LSTORE, // -
    FSTORE, // -
    DSTORE, // -
    ASTORE, // -
    ISTORE_0, // -
    ISTORE_1, // -
    ISTORE_2, // -
    ISTORE_3, // -
    LSTORE_0, // -
    LSTORE_1, // -
    LSTORE_2, // -
    LSTORE_3, // -
    FSTORE_0, // -
    FSTORE_1, // -
    FSTORE_2, // -
    FSTORE_3, // -
    DSTORE_0, // -
    DSTORE_1, // -
    DSTORE_2, // -
    DSTORE_3, // -
    ASTORE_0, // -
    ASTORE_1, // -
    ASTORE_2, // -
    ASTORE_3, // -
    IASTORE, // visitInsn
    LASTORE, // -
    FASTORE, // -
    DASTORE, // -
    AASTORE, // -
    BASTORE, // -
    CASTORE, // -
    SASTORE, // -
    POP, // -
    POP2, // -
    DUP, // -
    DUP_X1, // -
    DUP_X2, // -
    DUP2, // -
    DUP2_X1, // -
    DUP2_X2, // -
    SWAP, // -
    IADD, // -
    LADD, // -
    FADD, // -
    DADD, // -
    ISUB, // -
    LSUB, // -
    FSUB, // -
    DSUB, // -
    IMUL, // -
    LMUL, // -
    FMUL, // -
    DMUL, // -
    IDIV, // -
    LDIV, // -
    FDIV, // -
    DDIV, // -
    IREM, // -
    LREM, // -
    FREM, // -
    DREM, // -
    INEG, // -
    LNEG, // -
    FNEG, // -
    DNEG, // -
    ISHL, // -
    LSHL, // -
    ISHR, // -
    LSHR, // -
    IUSHR, // -
    LUSHR, // -
    IAND, // -
    LAND, // -
    IOR, // -
    LOR, // -
    IXOR, // -
    LXOR, // -
    IINC, // visitIincInsn
    I2L, // visitInsn
    I2F, // -
    I2D, // -
    L2I, // -
    L2F, // -
    L2D, // -
    F2I, // -
    F2L, // -
    F2D, // -
    D2I, // -
    D2L, // -
    D2F, // -
    I2B, // -
    I2C, // -
    I2S, // -
    LCMP, // -
    FCMPL, // -
    FCMPG, // -
    DCMPL, // -
    DCMPG, // -
    IFEQ, // visitJumpInsn
    IFNE, // -
    IFLT, // -
    IFGE, // -
    IFGT, // -
    IFLE, // -
    IF_ICMPEQ, // -
    IF_ICMPNE, // -
    IF_ICMPLT, // -
    IF_ICMPGE, // -
    IF_ICMPGT, // -
    IF_ICMPLE, // -
    IF_ACMPEQ, // -
    IF_ACMPNE, // -
    GOTO, // -
    JSR, // -
    RET, // visitVarInsn
    TABLESWITCH, // visiTableSwitchInsn
    LOOKUPSWITCH, // visitLookupSwitch
    IRETURN, // visitInsn
    LRETURN, // -
    FRETURN, // -
    DRETURN, // -
    ARETURN, // -
    RETURN, // -
    GETSTATIC, // visitFieldInsn
    PUTSTATIC, // -
    GETFIELD, // -
    PUTFIELD, // -
    INVOKEVIRTUAL
            {
                @Override
                public void visit(final Instruction instruction, final InstructionVisitor visitor)
                {
                    visitor.methodInvocation(this, instruction.getLineNumber(), instruction.getOwner(),
                            instruction.getName(), instruction.getArgs());
                }
            },
    INVOKESPECIAL
            {
                @Override
                public void visit(final Instruction instruction, final InstructionVisitor visitor)
                {
                    visitor.methodInvocation(this, instruction.getLineNumber(), instruction.getOwner(),
                            instruction.getOperand(), instruction.getName());
                }
            },
    INVOKESTATIC
            {
                @Override
                public void visit(final Instruction instruction, final InstructionVisitor visitor)
                {
                    visitor.methodInvocation(this, instruction.getLineNumber(), instruction.getOwner(),
                            instruction.getOperand(), instruction.getName());
                }
            },

    INVOKEINTERFACE
            {
                @Override
                public void visit(final Instruction instruction, final InstructionVisitor visitor)
                {
                    visitor.methodInvocation(this, instruction.getLineNumber(), instruction.getOwner(),
                            instruction.getOperand(), instruction.getName());
                }
            },
    INVOKEDYNAMIC
            {
                @Override
                public void visit(final Instruction instruction, final InstructionVisitor visitor)
                {
                    visitor.methodInvocation(this, instruction.getLineNumber(), instruction.getOwner(),
                            instruction.getOperand(), instruction.getName());
                }
            },
    NEW, // visitTypeInsn
    NEWARRAY, // visitIntInsn
    ANEWARRAY, // visitTypeInsn
    ARRAYLENGTH, // visitInsn
    ATHROW, // -
    CHECKCAST, // visitTypeInsn
    INSTANCEOF, // -
    MONITORENTER, // visitInsn
    MONITOREXIT, // -
    WIDE, // NOT VISITED
    MULTIANEWARRAY, // visitMultiANewArrayInsn
    IFNULL, // visitJumpInsn
    IFNONNULL, // -
    GOTO_W, // -
    JSR_W; //

    public void visit(Instruction thisInstruction, final InstructionVisitor visitor)
    {
        visitor.instruction(this);
    }
}
