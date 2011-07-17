package org.langera.freud.optional.classfile.method.instruction;

import org.langera.freud.optional.classfile.method.ClassFileMethod;
import org.langera.freud.optional.classfile.method.ref.CastLoadedReference;
import org.langera.freud.optional.classfile.method.ref.LoadedReference;
import org.langera.freud.optional.classfile.method.ref.StaticLoadedReference;
import org.langera.freud.optional.classfile.method.ref.VariableArrayElementLoadedReference;
import org.langera.freud.optional.classfile.method.ref.VariableLoadedReference;

import static org.langera.freud.optional.classfile.method.ref.StaticLoadedReference.Type.ARRAY_ELEMENT;
import static org.langera.freud.optional.classfile.method.ref.StaticLoadedReference.Type.CONSTANT;
import static org.langera.freud.optional.classfile.method.ref.StaticLoadedReference.Type.ARRAY_LENGTH;

public enum Opcode
{
    NOP, // visitInsn
    ACONST_NULL, // -
    ICONST_M1, // -
    ICONST_0
            {
                @Override
                public LoadedReference createLoadedReference(final ClassFileMethod method, final Instruction instruction)
                {
                    return new StaticLoadedReference("I", instruction.getInstructionIndex(), CONSTANT);
                }
            }, // -
    ICONST_1
            {
                @Override
                public LoadedReference createLoadedReference(final ClassFileMethod method, final Instruction instruction)
                {
                    return new StaticLoadedReference("I", instruction.getInstructionIndex(), CONSTANT);
                }
            }, // -
    ICONST_2
            {
                @Override
                public LoadedReference createLoadedReference(final ClassFileMethod method, final Instruction instruction)
                {
                    return new StaticLoadedReference("I", instruction.getInstructionIndex(), CONSTANT);
                }
            }, // -
    ICONST_3
            {
                @Override
                public LoadedReference createLoadedReference(final ClassFileMethod method, final Instruction instruction)
                {
                    return new StaticLoadedReference("I", instruction.getInstructionIndex(), CONSTANT);
                }
            }, // -
    ICONST_4
            {
                @Override
                public LoadedReference createLoadedReference(final ClassFileMethod method, final Instruction instruction)
                {
                    return new StaticLoadedReference("I", instruction.getInstructionIndex(), CONSTANT);
                }
            }, // -
    ICONST_5
            {
                @Override
                public LoadedReference createLoadedReference(final ClassFileMethod method, final Instruction instruction)
                {
                    return new StaticLoadedReference("I", instruction.getInstructionIndex(), CONSTANT);
                }
            }, // -
    LCONST_0
            {
                @Override
                public LoadedReference createLoadedReference(final ClassFileMethod method, final Instruction instruction)
                {
                    return new StaticLoadedReference("J", instruction.getInstructionIndex(), CONSTANT);
                }
            }, // -
    LCONST_1
            {
                @Override
                public LoadedReference createLoadedReference(final ClassFileMethod method, final Instruction instruction)
                {
                    return new StaticLoadedReference("J", instruction.getInstructionIndex(), CONSTANT);
                }
            }, // -
    FCONST_0
            {
                @Override
                public LoadedReference createLoadedReference(final ClassFileMethod method, final Instruction instruction)
                {
                    return new StaticLoadedReference("F", instruction.getInstructionIndex(), CONSTANT);
                }
            }, // -
    FCONST_1
            {
                @Override
                public LoadedReference createLoadedReference(final ClassFileMethod method, final Instruction instruction)
                {
                    return new StaticLoadedReference("F", instruction.getInstructionIndex(), CONSTANT);
                }
            }, // -
    FCONST_2
            {
                @Override
                public LoadedReference createLoadedReference(final ClassFileMethod method, final Instruction instruction)
                {
                    return new StaticLoadedReference("F", instruction.getInstructionIndex(), CONSTANT);
                }
            }, // -
    DCONST_0
            {
                @Override
                public LoadedReference createLoadedReference(final ClassFileMethod method, final Instruction instruction)
                {
                    return new StaticLoadedReference("D", instruction.getInstructionIndex(), CONSTANT);
                }
            }, // -
    DCONST_1
            {
                @Override
                public LoadedReference createLoadedReference(final ClassFileMethod method, final Instruction instruction)
                {
                    return new StaticLoadedReference("D", instruction.getInstructionIndex(), CONSTANT);
                }
            }, // -
    BIPUSH, // visitIntInsn
    SIPUSH, // -
    LDC, // visitLdcInsn
    LDC_W, // -
    LDC2_W, // -
    ILOAD
            {
                @Override
                public LoadedReference createLoadedReference(final ClassFileMethod method, final Instruction instruction)
                {
                    return new VariableLoadedReference(method, instruction.getVarIndex(), instruction.getInstructionIndex());
                }
            }, // visitVarInsn
    LLOAD
            {
                @Override
                public LoadedReference createLoadedReference(final ClassFileMethod method, final Instruction instruction)
                {
                    return new VariableLoadedReference(method, instruction.getVarIndex(), instruction.getInstructionIndex());
                }
            }, // -
    FLOAD
            {
                @Override
                public LoadedReference createLoadedReference(final ClassFileMethod method, final Instruction instruction)
                {
                    return new VariableLoadedReference(method, instruction.getVarIndex(), instruction.getInstructionIndex());
                }
            }, // -
    DLOAD
            {
                @Override
                public LoadedReference createLoadedReference(final ClassFileMethod method, final Instruction instruction)
                {
                    return new VariableLoadedReference(method, instruction.getVarIndex(), instruction.getInstructionIndex());
                }
            }, // -
    ALOAD
            {
                @Override
                public LoadedReference createLoadedReference(final ClassFileMethod method, final Instruction instruction)
                {
                    return new VariableLoadedReference(method, instruction.getVarIndex(), instruction.getInstructionIndex());
                }
            }, // -
    ILOAD_0
            {
                @Override
                public LoadedReference createLoadedReference(final ClassFileMethod method, final Instruction instruction)
                {
                    return new VariableLoadedReference(method, 0, instruction.getInstructionIndex());
                }
            }, // -
    ILOAD_1
            {
                @Override
                public LoadedReference createLoadedReference(final ClassFileMethod method, final Instruction instruction)
                {
                    return new VariableLoadedReference(method, 1, instruction.getInstructionIndex());
                }
            }, // -
    ILOAD_2
            {
                @Override
                public LoadedReference createLoadedReference(final ClassFileMethod method, final Instruction instruction)
                {
                    return new VariableLoadedReference(method, 2, instruction.getInstructionIndex());
                }
            }, // -
    ILOAD_3
            {
                @Override
                public LoadedReference createLoadedReference(final ClassFileMethod method, final Instruction instruction)
                {
                    return new VariableLoadedReference(method, 3, instruction.getInstructionIndex());
                }
            }, // -
    LLOAD_0
            {
                @Override
                public LoadedReference createLoadedReference(final ClassFileMethod method, final Instruction instruction)
                {
                    return new VariableLoadedReference(method, 0, instruction.getInstructionIndex());
                }
            }, // -
    LLOAD_1
            {
                @Override
                public LoadedReference createLoadedReference(final ClassFileMethod method, final Instruction instruction)
                {
                    return new VariableLoadedReference(method, 1, instruction.getInstructionIndex());
                }
            }, // -
    LLOAD_2
            {
                @Override
                public LoadedReference createLoadedReference(final ClassFileMethod method, final Instruction instruction)
                {
                    return new VariableLoadedReference(method, 2, instruction.getInstructionIndex());
                }
            }, // -
    LLOAD_3
            {
                @Override
                public LoadedReference createLoadedReference(final ClassFileMethod method, final Instruction instruction)
                {
                    return new VariableLoadedReference(method, 3, instruction.getInstructionIndex());
                }
            }, // -
    FLOAD_0
            {
                @Override
                public LoadedReference createLoadedReference(final ClassFileMethod method, final Instruction instruction)
                {
                    return new VariableLoadedReference(method, 0, instruction.getInstructionIndex());
                }
            }, // -
    FLOAD_1
            {
                @Override
                public LoadedReference createLoadedReference(final ClassFileMethod method, final Instruction instruction)
                {
                    return new VariableLoadedReference(method, 1, instruction.getInstructionIndex());
                }
            }, // -
    FLOAD_2
            {
                @Override
                public LoadedReference createLoadedReference(final ClassFileMethod method, final Instruction instruction)
                {
                    return new VariableLoadedReference(method, 2, instruction.getInstructionIndex());
                }
            }, // -
    FLOAD_3
            {
                @Override
                public LoadedReference createLoadedReference(final ClassFileMethod method, final Instruction instruction)
                {
                    return new VariableLoadedReference(method, 3, instruction.getInstructionIndex());
                }
            }, // -
    DLOAD_0
            {
                @Override
                public LoadedReference createLoadedReference(final ClassFileMethod method, final Instruction instruction)
                {
                    return new VariableLoadedReference(method, 0, instruction.getInstructionIndex());
                }
            }, // -
    DLOAD_1
            {
                @Override
                public LoadedReference createLoadedReference(final ClassFileMethod method, final Instruction instruction)
                {
                    return new VariableLoadedReference(method, 1, instruction.getInstructionIndex());
                }
            }, // -
    DLOAD_2
            {
                @Override
                public LoadedReference createLoadedReference(final ClassFileMethod method, final Instruction instruction)
                {
                    return new VariableLoadedReference(method, 2, instruction.getInstructionIndex());
                }
            }, // -
    DLOAD_3
            {
                @Override
                public LoadedReference createLoadedReference(final ClassFileMethod method, final Instruction instruction)
                {
                    return new VariableLoadedReference(method, 3, instruction.getInstructionIndex());
                }
            }, // -
    ALOAD_0
            {
                @Override
                public LoadedReference createLoadedReference(final ClassFileMethod method, final Instruction instruction)
                {
                    return new VariableLoadedReference(method, 0, instruction.getInstructionIndex());
                }
            }, // -
    ALOAD_1
            {
                @Override
                public LoadedReference createLoadedReference(final ClassFileMethod method, final Instruction instruction)
                {
                    return new VariableLoadedReference(method, 1, instruction.getInstructionIndex());
                }
            }, // -
    ALOAD_2
            {
                @Override
                public LoadedReference createLoadedReference(final ClassFileMethod method, final Instruction instruction)
                {
                    return new VariableLoadedReference(method, 2, instruction.getInstructionIndex());
                }
            }, // -
    ALOAD_3
            {
                @Override
                public LoadedReference createLoadedReference(final ClassFileMethod method, final Instruction instruction)
                {
                    return new VariableLoadedReference(method, 3, instruction.getInstructionIndex());
                }
            }, // -
    IALOAD
            {
                @Override
                public LoadedReference createLoadedReference(final ClassFileMethod method, final Instruction instruction)
                {
                    return new StaticLoadedReference("I", instruction.getInstructionIndex(), ARRAY_ELEMENT);
                }
            }, // visitInsn
    LALOAD
            {
                @Override
                public LoadedReference createLoadedReference(final ClassFileMethod method, final Instruction instruction)
                {
                    return new StaticLoadedReference("J", instruction.getInstructionIndex(), ARRAY_ELEMENT);
                }
            }, // -
    FALOAD
            {
                @Override
                public LoadedReference createLoadedReference(final ClassFileMethod method, final Instruction instruction)
                {
                    return new StaticLoadedReference("F", instruction.getInstructionIndex(), ARRAY_ELEMENT);
                }
            }, // -
    DALOAD
            {
                @Override
                public LoadedReference createLoadedReference(final ClassFileMethod method, final Instruction instruction)
                {
                    return new StaticLoadedReference("D", instruction.getInstructionIndex(), ARRAY_ELEMENT);
                }
            }, // -
    AALOAD
            {
                @Override
                public LoadedReference createLoadedReference(final ClassFileMethod method, final Instruction instruction)
                {
                    return new VariableArrayElementLoadedReference(method, instruction.getInstructionIndex()
                    );
                }
            }, // -
    BALOAD
            {
                @Override
                public LoadedReference createLoadedReference(final ClassFileMethod method, final Instruction instruction)
                {
                    return new StaticLoadedReference("B", instruction.getInstructionIndex(), ARRAY_ELEMENT);
                }
            }, // -
    CALOAD
            {
                @Override
                public LoadedReference createLoadedReference(final ClassFileMethod method, final Instruction instruction)
                {
                    return new StaticLoadedReference("C", instruction.getInstructionIndex(), ARRAY_ELEMENT);
                }
            }, // -
    SALOAD
            {
                @Override
                public LoadedReference createLoadedReference(final ClassFileMethod method, final Instruction instruction)
                {
                    return new StaticLoadedReference("S", instruction.getInstructionIndex(), ARRAY_ELEMENT);
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
    I2L
            {
                @Override
                public LoadedReference createLoadedReference(final ClassFileMethod method, final Instruction instruction)
                {
                    LoadedReference castRef = method.getInstruction(instruction.getInstructionIndex() - 1).getLoadedReference();
                    return new CastLoadedReference("J", castRef);
                }
            }, // visitInsn
    I2F
            {
                @Override
                public LoadedReference createLoadedReference(final ClassFileMethod method, final Instruction instruction)
                {
                    LoadedReference castRef = method.getInstruction(instruction.getInstructionIndex() - 1).getLoadedReference();
                    return new CastLoadedReference("F", castRef);
                }
            }, // -
    I2D
            {
                @Override
                public LoadedReference createLoadedReference(final ClassFileMethod method, final Instruction instruction)
                {
                    LoadedReference castRef = method.getInstruction(instruction.getInstructionIndex() - 1).getLoadedReference();
                    return new CastLoadedReference("D", castRef);
                }
            }, // -
    L2I
            {
                @Override
                public LoadedReference createLoadedReference(final ClassFileMethod method, final Instruction instruction)
                {
                    LoadedReference castRef = method.getInstruction(instruction.getInstructionIndex() - 1).getLoadedReference();
                    return new CastLoadedReference("I", castRef);
                }
            }, // -
    L2F
            {
                @Override
                public LoadedReference createLoadedReference(final ClassFileMethod method, final Instruction instruction)
                {
                    LoadedReference castRef = method.getInstruction(instruction.getInstructionIndex() - 1).getLoadedReference();
                    return new CastLoadedReference("F", castRef);
                }
            }, // -
    L2D
            {
                @Override
                public LoadedReference createLoadedReference(final ClassFileMethod method, final Instruction instruction)
                {
                    LoadedReference castRef = method.getInstruction(instruction.getInstructionIndex() - 1).getLoadedReference();
                    return new CastLoadedReference("D", castRef);
                }
            }, // -
    F2I
            {
                @Override
                public LoadedReference createLoadedReference(final ClassFileMethod method, final Instruction instruction)
                {
                    LoadedReference castRef = method.getInstruction(instruction.getInstructionIndex() - 1).getLoadedReference();
                    return new CastLoadedReference("I", castRef);
                }
            }, // -
    F2L
            {
                @Override
                public LoadedReference createLoadedReference(final ClassFileMethod method, final Instruction instruction)
                {
                    LoadedReference castRef = method.getInstruction(instruction.getInstructionIndex() - 1).getLoadedReference();
                    return new CastLoadedReference("J", castRef);
                }
            }, // -
    F2D
            {
                @Override
                public LoadedReference createLoadedReference(final ClassFileMethod method, final Instruction instruction)
                {
                    LoadedReference castRef = method.getInstruction(instruction.getInstructionIndex() - 1).getLoadedReference();
                    return new CastLoadedReference("D", castRef);
                }
            }, // -
    D2I
            {
                @Override
                public LoadedReference createLoadedReference(final ClassFileMethod method, final Instruction instruction)
                {
                    LoadedReference castRef = method.getInstruction(instruction.getInstructionIndex() - 1).getLoadedReference();
                    return new CastLoadedReference("I", castRef);
                }
            }, // -
    D2L
            {
                @Override
                public LoadedReference createLoadedReference(final ClassFileMethod method, final Instruction instruction)
                {
                    LoadedReference castRef = method.getInstruction(instruction.getInstructionIndex() - 1).getLoadedReference();
                    return new CastLoadedReference("J", castRef);
                }
            }, // -
    D2F
            {
                @Override
                public LoadedReference createLoadedReference(final ClassFileMethod method, final Instruction instruction)
                {
                    LoadedReference castRef = method.getInstruction(instruction.getInstructionIndex() - 1).getLoadedReference();
                    return new CastLoadedReference("F", castRef);
                }
            }, // -
    I2B
            {
                @Override
                public LoadedReference createLoadedReference(final ClassFileMethod method, final Instruction instruction)
                {
                    LoadedReference castRef = method.getInstruction(instruction.getInstructionIndex() - 1).getLoadedReference();
                    return new CastLoadedReference("B", castRef);
                }
            }, // -
    I2C
            {
                @Override
                public LoadedReference createLoadedReference(final ClassFileMethod method, final Instruction instruction)
                {
                    LoadedReference castRef = method.getInstruction(instruction.getInstructionIndex() - 1).getLoadedReference();
                    return new CastLoadedReference("C", castRef);
                }
            }, // -
    I2S
            {
                @Override
                public LoadedReference createLoadedReference(final ClassFileMethod method, final Instruction instruction)
                {
                    LoadedReference castRef = method.getInstruction(instruction.getInstructionIndex() - 1).getLoadedReference();
                    return new CastLoadedReference("S", castRef);
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
                public LoadedReference createLoadedReference(final ClassFileMethod method, final Instruction instruction)
                {
                    return new StaticLoadedReference("I", instruction.getInstructionIndex(), ARRAY_LENGTH);
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

    public LoadedReference createLoadedReference(final ClassFileMethod method, final Instruction instruction)
    {
        return null;
    }
}
