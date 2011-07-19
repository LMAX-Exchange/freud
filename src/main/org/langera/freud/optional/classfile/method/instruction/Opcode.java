package org.langera.freud.optional.classfile.method.instruction;

import org.langera.freud.optional.classfile.method.ClassFileMethod;

public enum Opcode
{
    NOP, // visitInsn
    ACONST_NULL
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new StaticOperandStack(null, stack, this);
                }
            }, // -
    ICONST_M1
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new StaticOperandStack("I", stack, this);
                }

                @Override
                public boolean isConstant()
                {
                    return true;
                }
            }, // -
    ICONST_0
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new StaticOperandStack("I", stack, this);
                }

                @Override
                public boolean isConstant()
                {
                    return true;
                }
            }, // -
    ICONST_1
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new StaticOperandStack("I", stack, this);
                }

                @Override
                public boolean isConstant()
                {
                    return true;
                }
            }, // -
    ICONST_2
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new StaticOperandStack("I", stack, this);
                }

                @Override
                public boolean isConstant()
                {
                    return true;
                }
            }, // -
    ICONST_3
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new StaticOperandStack("I", stack, this);
                }

                @Override
                public boolean isConstant()
                {
                    return true;
                }
            }, // -
    ICONST_4
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new StaticOperandStack("I", stack, this);
                }

                @Override
                public boolean isConstant()
                {
                    return true;
                }
            }, // -
    ICONST_5
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new StaticOperandStack("I", stack, this);
                }

                @Override
                public boolean isConstant()
                {
                    return true;
                }
            }, // -
    LCONST_0
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new StaticOperandStack("J", stack, this);
                }

                @Override
                public boolean isConstant()
                {
                    return true;
                }
            }, // -
    LCONST_1
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new StaticOperandStack("J", stack, this);
                }

                @Override
                public boolean isConstant()
                {
                    return true;
                }
            }, // -
    FCONST_0
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new StaticOperandStack("F", stack, this);
                }

                @Override
                public boolean isConstant()
                {
                    return true;
                }
            }, // -
    FCONST_1
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new StaticOperandStack("F", stack, this);
                }

                @Override
                public boolean isConstant()
                {
                    return true;
                }
            }, // -
    FCONST_2
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new StaticOperandStack("F", stack, this);
                }

                @Override
                public boolean isConstant()
                {
                    return true;
                }
            }, // -
    DCONST_0
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new StaticOperandStack("D", stack, this);
                }

                @Override
                public boolean isConstant()
                {
                    return true;
                }
            }, // -
    DCONST_1
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new StaticOperandStack("D", stack, this);
                }

                @Override
                public boolean isConstant()
                {
                    return true;
                }
            }, // -
    BIPUSH
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new StaticOperandStack("I", stack, this);
                }
            }, // visitIntInsn
    SIPUSH
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new StaticOperandStack("I", stack, this);
                }
            }, // -
    LDC
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new StaticOperandStack(instruction.getOperandType(), stack, this);
                }

                @Override
                public boolean isConstant()
                {
                    return true;
                }
            }, // visitLdcInsn
    LDC_W
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new StaticOperandStack(instruction.getOperandType(), stack, this);
                }

                @Override
                public boolean isConstant()
                {
                    return true;
                }
            }, // -
    LDC2_W
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new StaticOperandStack(instruction.getOperandType(), stack, this);
                }

                @Override
                public boolean isConstant()
                {
                    return true;
                }
            }, // -
    ILOAD
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new LocalVariableOperandStack(method, instruction.getVarIndex(), stack, this);
                }
            }, // visitVarInsn
    LLOAD
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new LocalVariableOperandStack(method, instruction.getVarIndex(), stack, this);
                }
            }, // -
    FLOAD
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new LocalVariableOperandStack(method, instruction.getVarIndex(), stack, this);
                }
            }, // -
    DLOAD
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new LocalVariableOperandStack(method, instruction.getVarIndex(), stack, this);
                }
            }, // -
    ALOAD
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new LocalVariableOperandStack(method, instruction.getVarIndex(), stack, this);
                }
            }, // -
    ILOAD_0
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new LocalVariableOperandStack(method, 0, stack, this);
                }
            }, // -
    ILOAD_1
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new LocalVariableOperandStack(method, 1, stack, this);
                }
            }, // -
    ILOAD_2
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new LocalVariableOperandStack(method, 2, stack, this);
                }
            }, // -
    ILOAD_3
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new LocalVariableOperandStack(method, 3, stack, this);
                }
            }, // -
    LLOAD_0
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new LocalVariableOperandStack(method, 0, stack, this);
                }
            }, // -
    LLOAD_1
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new LocalVariableOperandStack(method, 1, stack, this);
                }
            }, // -
    LLOAD_2
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new LocalVariableOperandStack(method, 2, stack, this);
                }
            }, // -
    LLOAD_3
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new LocalVariableOperandStack(method, 3, stack, this);
                }
            }, // -
    FLOAD_0
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new LocalVariableOperandStack(method, 0, stack, this);
                }
            }, // -
    FLOAD_1
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new LocalVariableOperandStack(method, 1, stack, this);
                }
            }, // -
    FLOAD_2
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new LocalVariableOperandStack(method, 2, stack, this);
                }
            }, // -
    FLOAD_3
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new LocalVariableOperandStack(method, 3, stack, this);
                }
            }, // -
    DLOAD_0
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new LocalVariableOperandStack(method, 0, stack, this);
                }
            }, // -
    DLOAD_1
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new LocalVariableOperandStack(method, 1, stack, this);
                }
            }, // -
    DLOAD_2
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new LocalVariableOperandStack(method, 2, stack, this);
                }
            }, // -
    DLOAD_3
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new LocalVariableOperandStack(method, 3, stack, this);
                }
            }, // -
    ALOAD_0
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new LocalVariableOperandStack(method, 0, stack, this);
                }
            }, // -
    ALOAD_1
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new LocalVariableOperandStack(method, 1, stack, this);
                }
            }, // -
    ALOAD_2
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new LocalVariableOperandStack(method, 2, stack, this);
                }
            }, // -
    ALOAD_3
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new LocalVariableOperandStack(method, 3, stack, this);
                }
            }, // -
    IALOAD
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new StaticOperandStack("I", stack.next().next(), this);
                }
            }, // visitInsn
    LALOAD
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new StaticOperandStack("J", stack.next().next(), this);
                }
            }, // -
    FALOAD
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new StaticOperandStack("F", stack.next().next(), this);
                }
            }, // -
    DALOAD
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new StaticOperandStack("D", stack.next().next(), this);
                }
            }, // -
    AALOAD
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new VariableArrayElementOperandStack(stack.next(), stack.next().next(), this);
                }
            }, // -
    BALOAD
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new StaticOperandStack("B", stack.next().next(), this);
                }
            }, // -
    CALOAD
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new StaticOperandStack("C", stack.next().next(), this);
                }
            }, // -
    SALOAD
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new StaticOperandStack("S", stack.next().next(), this);
                }
            }, // -
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
    IADD
                {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new StaticOperandStack("I", stack.next().next(), this);
                }
            }, // -
    LADD
                {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new StaticOperandStack("J", stack.next().next(), this);
                }
            }, // -
    FADD
                {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new StaticOperandStack("F", stack.next().next(), this);
                }
            }, // -
    DADD
                {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new StaticOperandStack("D", stack.next().next(), this);
                }
            }, // -
    ISUB
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new StaticOperandStack("I", stack.next().next(), this);
                }
            }, // -
    LSUB
                {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new StaticOperandStack("J", stack.next().next(), this);
                }
            }, // -
    FSUB
                {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new StaticOperandStack("F", stack.next().next(), this);
                }
            }, // -
    DSUB
                {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new StaticOperandStack("D", stack.next().next(), this);
                }
            }, // -
    IMUL
                {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new StaticOperandStack("I", stack.next().next(), this);
                }
            }, // -
    LMUL
                {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new StaticOperandStack("J", stack.next().next(), this);
                }
            }, // -
    FMUL
                {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new StaticOperandStack("F", stack.next().next(), this);
                }
            }, // -
    DMUL
                {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new StaticOperandStack("D", stack.next().next(), this);
                }
            }, // -
    IDIV
                {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new StaticOperandStack("I", stack.next().next(), this);
                }
            }, // -
    LDIV
                {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new StaticOperandStack("J", stack.next().next(), this);
                }
            }, // -
    FDIV
                {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new StaticOperandStack("F", stack.next().next(), this);
                }
            }, // -
    DDIV
                {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new StaticOperandStack("D", stack.next().next(), this);
                }
            }, // -
    IREM, // -
    LREM, // -
    FREM, // -
    DREM, // -
    INEG
                {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new StaticOperandStack("I", stack.next(), this);
                }
            }, // -
    LNEG
                {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new StaticOperandStack("L", stack.next(), this);
                }
            }, // -
    FNEG
                {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new StaticOperandStack("F", stack.next(), this);
                }
            }, // -
    DNEG
                {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new StaticOperandStack("D", stack.next(), this);
                }
            }, // -
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
    I2L
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new CastOperandStack("I", "J", stack.next(), this);
                }
            }, // visitInsn
    I2F
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new CastOperandStack("I", "F", stack.next(), this);
                }
            }, // -
    I2D
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new CastOperandStack("I", "D", stack.next(), this);
                }
            }, // -
    L2I
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new CastOperandStack("J", "I", stack.next(), this);
                }
            }, // -
    L2F
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new CastOperandStack("J", "F", stack.next(), this);
                }
            }, // -
    L2D
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new CastOperandStack("J", "D", stack.next(), this);
                }
            }, // -
    F2I
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new CastOperandStack("F", "I", stack.next(), this);
                }
            }, // -
    F2L
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new CastOperandStack("F", "J", stack.next(), this);
                }
            }, // -
    F2D
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new CastOperandStack("F", "D", stack.next(), this);
                }
            }, // -
    D2I
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new CastOperandStack("D", "I", stack.next(), this);
                }
            }, // -
    D2L
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new CastOperandStack("D", "J", stack.next(), this);
                }
            }, // -
    D2F
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new CastOperandStack("D", "F", stack.next(), this);
                }
            }, // -
    I2B
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new CastOperandStack("I", "B", stack.next(), this);
                }
            }, // -
    I2C
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new CastOperandStack("I", "C", stack.next(), this);
                }
            }, // -
    I2S
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new CastOperandStack("I", "S", stack.next(), this);
                }
            }, // -
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
    INVOKEVIRTUAL, // - visitMethod
    INVOKESPECIAL,
    INVOKESTATIC,
    INVOKEINTERFACE,
    INVOKEDYNAMIC,
    NEW, // visitTypeInsn
    NEWARRAY, // visitIntInsn
    ANEWARRAY, // visitTypeInsn
    ARRAYLENGTH
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new StaticOperandStack("I", stack.next(), this);
                }
            }, // visitInsn

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

    public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, OperandStack stack)
    {
        return stack;
    }

    public boolean isConstant()
    {
        return false;
    }
}
