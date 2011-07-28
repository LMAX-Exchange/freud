package org.langera.freud.optional.classfile.method.instruction;

import org.langera.freud.optional.classfile.method.ClassFileMethod;

import java.util.List;

public enum Opcode
{
    NOP
            {
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, OperandStack stack)
                {
                    return stack;
                }

            }, // visitInsn
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
    ISTORE
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return stack.next();
                }

                @Override
                public List<String> updateLocals(final List<String> locals, final Instruction instruction)
                {

                    locals.set(instruction.getVarIndex(), "I");
                    return locals;
                }
            }, // visitVarInsn
    LSTORE
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return stack.next();
                }

                @Override
                public List<String> updateLocals(final List<String> locals, final Instruction instruction)
                {
                    locals.set(instruction.getVarIndex(), "J");
                    return locals;
                }
            }, // -
    FSTORE
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return stack.next();
                }

                @Override
                public List<String> updateLocals(final List<String> locals, final Instruction instruction)
                {
                    locals.set(instruction.getVarIndex(), "F");
                    return locals;
                }
            }, // -
    DSTORE
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return stack.next();
                }

                @Override
                public List<String> updateLocals(final List<String> locals, final Instruction instruction)
                {
                    locals.set(instruction.getVarIndex(), "D");
                    return locals;
                }
            }, // -
    ASTORE
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return stack.next();
                }

                @Override
                public List<String> updateLocals(final List<String> locals, final Instruction instruction)
                {
                    locals.set(instruction.getVarIndex(), "Ljava/lang/Object;");
                    return locals;
                }
            }, // -
    ISTORE_0
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return stack.next();
                }

                @Override
                public List<String> updateLocals(final List<String> locals, final Instruction instruction)
                {
                    locals.set(0, "I");
                    return locals;
                }
            }, // -
    ISTORE_1
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return stack.next();
                }

                @Override
                public List<String> updateLocals(final List<String> locals, final Instruction instruction)
                {
                    locals.set(1, "I");
                    return locals;
                }
            }, // -
    ISTORE_2
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return stack.next();
                }

                @Override
                public List<String> updateLocals(final List<String> locals, final Instruction instruction)
                {
                    locals.set(2, "I");
                    return locals;
                }
            }, // -
    ISTORE_3
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return stack.next();
                }

                @Override
                public List<String> updateLocals(final List<String> locals, final Instruction instruction)
                {
                    locals.set(3, "I");
                    return locals;
                }
            }, // -
    LSTORE_0
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return stack.next();
                }

                @Override
                public List<String> updateLocals(final List<String> locals, final Instruction instruction)
                {
                    locals.set(0, "J");
                    return locals;
                }
            }, // -
    LSTORE_1
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return stack.next();
                }

                @Override
                public List<String> updateLocals(final List<String> locals, final Instruction instruction)
                {
                    locals.set(1, "J");
                    return locals;
                }
            }, // -
    LSTORE_2
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return stack.next();
                }

                @Override
                public List<String> updateLocals(final List<String> locals, final Instruction instruction)
                {
                    locals.set(2, "J");
                    return locals;
                }
            }, // -
    LSTORE_3
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return stack.next();
                }

                @Override
                public List<String> updateLocals(final List<String> locals, final Instruction instruction)
                {
                    locals.set(3, "J");
                    return locals;
                }
            }, // -
    FSTORE_0
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return stack.next();
                }
                @Override
                public List<String> updateLocals(final List<String> locals, final Instruction instruction)
                {
                    locals.set(0, "F");
                    return locals;
                }
            }, // -
    FSTORE_1
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return stack.next();
                }
                @Override
                public List<String> updateLocals(final List<String> locals, final Instruction instruction)
                {
                    locals.set(1, "F");
                    return locals;
                }
            }, // -
    FSTORE_2
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return stack.next();
                }
                @Override
                public List<String> updateLocals(final List<String> locals, final Instruction instruction)
                {
                    locals.set(2, "F");
                    return locals;
                }
            }, // -
    FSTORE_3
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return stack.next();
                }
                @Override
                public List<String> updateLocals(final List<String> locals, final Instruction instruction)
                {
                    locals.set(3, "F");
                    return locals;
                }
            }, // -
    DSTORE_0
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return stack.next();
                }
                @Override
                public List<String> updateLocals(final List<String> locals, final Instruction instruction)
                {
                    locals.set(0, "D");
                    return locals;
                }
            }, // -
    DSTORE_1
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return stack.next();
                }
                @Override
                public List<String> updateLocals(final List<String> locals, final Instruction instruction)
                {
                    locals.set(1, "D");
                    return locals;
                }

            }, // -
    DSTORE_2
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return stack.next();
                }
                @Override
                public List<String> updateLocals(final List<String> locals, final Instruction instruction)
                {
                    locals.set(2, "D");
                    return locals;
                }

            }, // -
    DSTORE_3
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return stack.next();
                }
                @Override
                public List<String> updateLocals(final List<String> locals, final Instruction instruction)
                {
                    locals.set(3, "D");
                    return locals;
                }
            }, // -
    ASTORE_0
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return stack.next();
                }

                @Override
                public List<String> updateLocals(final List<String> locals, final Instruction instruction)
                {
                    locals.set(0, "Ljava/lang/Object;");
                    return locals;
                }
            }, // -
    ASTORE_1
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return stack.next();
                }
                @Override
                public List<String> updateLocals(final List<String> locals, final Instruction instruction)
                {
                    locals.set(1, "Ljava/lang/Object;");
                    return locals;
                }
            }, // -
    ASTORE_2
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return stack.next();
                }
                @Override
                public List<String> updateLocals(final List<String> locals, final Instruction instruction)
                {
                    locals.set(2, "Ljava/lang/Object;");
                    return locals;
                }

            }, // -
    ASTORE_3
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return stack.next();
                }
                @Override
                public List<String> updateLocals(final List<String> locals, final Instruction instruction)
                {
                    locals.set(3, "Ljava/lang/Object;");
                    return locals;
                }
            }, // -
    IASTORE
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return stack.next().next().next();
                }

            }, // visitInsn
    LASTORE
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return stack.next().next().next();
                }
            }, // -
    FASTORE
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return stack.next().next().next();
                }
            }, // -
    DASTORE
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return stack.next().next().next();
                }
            }, // -
    AASTORE
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return stack.next().next().next();
                }
            }, // -
    BASTORE
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return stack.next().next().next();
                }
            }, // -
    CASTORE
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return stack.next().next().next();
                }
            }, // -
    SASTORE
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return stack.next().next().next();
                }
            }, // -
    POP
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return stack.next();
                }
            }, // -
    POP2
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    System.out.println("TODO");
                    return stack; // TODO
                }
            }, // -
    DUP
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return stack.dup(stack, this);
                }
            }, // -
    DUP_X1
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    final OperandStack second = stack.next();
                    final OperandStack duplicatedOperand = stack.dup(second.next(), this);
                    return stack.dup(second.dup(duplicatedOperand, second.getGeneratingOpcode()), stack.getGeneratingOpcode());
                }
            }, // -
    DUP_X2
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    final OperandStack second = stack.next();
                    if (second.getComputationalTypeCategory() == 2)
                    {
                        final OperandStack duplicatedOperand = stack.dup(second.next(), this);
                        return stack.dup(second.dup(duplicatedOperand, second.getGeneratingOpcode()), stack.getGeneratingOpcode());
                    }
                    else
                    {
                        final OperandStack third = second.next();
                        final OperandStack duplicatedOperand = stack.dup(third.next(), this);
                        return stack.dup(second.dup(third.dup(duplicatedOperand, third.getGeneratingOpcode()), second.getGeneratingOpcode()), stack.getGeneratingOpcode());
                    }
                }
            }, // -
    DUP2
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    if (stack.getComputationalTypeCategory() == 2)
                    {
                        return stack.dup(stack, this);
                    }
                    else
                    {
                        final OperandStack second = stack.next();
                        final OperandStack duplicatedOperand1 = second.dup(stack, this);
                        return stack.dup(duplicatedOperand1, this);
                    }
                }
            }, // -
    DUP2_X1
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    if (stack.getComputationalTypeCategory() == 2)
                    {
                        final OperandStack second = stack.next();
                        final OperandStack duplicatedOperand = second.dup(second.next(), this);
                        return stack.dup(second.dup(duplicatedOperand, second.getGeneratingOpcode()), stack.getGeneratingOpcode());
                    }
                    else
                    {
                        final OperandStack second = stack.next();
                        final OperandStack third = second.next();
                        final OperandStack duplicatedOperand1 = second.dup(third.next(), this);
                        final OperandStack duplicatedOperand2 = stack.dup(duplicatedOperand1, this);
                        return stack.dup(second.dup(third.dup(duplicatedOperand2, third.getGeneratingOpcode()), second.getGeneratingOpcode()), stack.getGeneratingOpcode());
                    }
                }
            }, // -
    DUP2_X2
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    final int topCategory = stack.getComputationalTypeCategory();
                    if (topCategory == 2)
                    {
                        final int secondCategory = stack.next().getComputationalTypeCategory();
                        if (secondCategory == 2)
                        {
                            final OperandStack second = stack.next();
                            final OperandStack duplicatedOperand = stack.dup(second.next(), this);
                            return stack.dup(second.dup(duplicatedOperand, second.getGeneratingOpcode()), stack.getGeneratingOpcode());
                        }
                        else
                        {
                            final OperandStack second = stack.next();
                            final OperandStack third = second.next();
                            final OperandStack duplicatedOperand = stack.dup(third.next(), this);
                            return stack.dup(second.dup(third.dup(duplicatedOperand, third.getGeneratingOpcode()), second.getGeneratingOpcode()), stack.getGeneratingOpcode());
                        }
                    }
                    else
                    {
                        final OperandStack second = stack.next();
                        final OperandStack third = second.next();
                        final int thirdCategory = third.getComputationalTypeCategory();
                        if (thirdCategory == 2)
                        {
                            final OperandStack duplicatedOperand1 = second.dup(third.next(), this);
                            final OperandStack duplicatedOperand2 = stack.dup(duplicatedOperand1, this);
                            return stack.dup(second.dup(third.dup(duplicatedOperand2, third.getGeneratingOpcode()), second.getGeneratingOpcode()), stack.getGeneratingOpcode());

                        }
                        else
                        {
                            final OperandStack fourth = second.next();
                            final OperandStack duplicatedOperand1 = second.dup(fourth.next(), this);
                            final OperandStack duplicatedOperand2 = stack.dup(duplicatedOperand1, this);
                            return stack.dup(second.dup(third.dup(fourth.dup(duplicatedOperand2, fourth.getGeneratingOpcode()), third.getGeneratingOpcode()), second.getGeneratingOpcode()), stack.getGeneratingOpcode());

                        }
                    }
                }
            }, // -
    SWAP
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    final OperandStack second = stack.next();
                    return second.dup(stack.dup(second.next(), stack.getGeneratingOpcode()), second.getGeneratingOpcode());
                }
            }, // -
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
    IREM
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new StaticOperandStack("I", stack.next().next(), this);
                }
            }, // -
    LREM
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new StaticOperandStack("J", stack.next().next(), this);
                }
            }, // -
    FREM
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new StaticOperandStack("F", stack.next().next(), this);
                }
            }, // -
    DREM
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new StaticOperandStack("D", stack.next().next(), this);
                }
            }, // -
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
    ISHL
            {
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, OperandStack stack)
                {
                    return new StaticOperandStack("I", stack.next().next(), this);
                }

            }, // -
    LSHL
            {
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, OperandStack stack)
                {
                    return new StaticOperandStack("J", stack.next().next(), this);
                }

            }, // -
    ISHR
            {
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, OperandStack stack)
                {
                    return new StaticOperandStack("I", stack.next().next(), this);
                }

            }, // -
    LSHR
            {
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, OperandStack stack)
                {
                    return new StaticOperandStack("J", stack.next().next(), this);
                }

            }, // -
    IUSHR
            {
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, OperandStack stack)
                {
                    return new StaticOperandStack("I", stack.next().next(), this);
                }

            }, // -
    LUSHR
            {
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, OperandStack stack)
                {
                    return new StaticOperandStack("J", stack.next().next(), this);
                }

            }, // -
    IAND
            {
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, OperandStack stack)
                {
                    return new StaticOperandStack("I", stack.next().next(), this);
                }

            }, // -
    LAND
            {
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, OperandStack stack)
                {
                    return new StaticOperandStack("J", stack.next().next(), this);
                }

            }, // -
    IOR
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new StaticOperandStack("I", stack.next().next(), this);
                }
            }, // -
    LOR
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new StaticOperandStack("J", stack.next().next(), this);
                }
            }, // -
    IXOR
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new StaticOperandStack("I", stack.next().next(), this);
                }
            }, // -
    LXOR
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new StaticOperandStack("J", stack.next().next(), this);
                }
            }, // -
    IINC
            {
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, OperandStack stack)
                {
                    return stack;
                }

            }, // visitIincInsn
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
    LCMP
            {
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, OperandStack stack)
                {
                    return new StaticOperandStack("I", stack.next().next(), this); // int is correct here
                }

            }, // -
    FCMPL
            {
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, OperandStack stack)
                {
                    return new StaticOperandStack("I", stack.next().next(), this); // int is correct here
                }

            }, // -
    FCMPG
            {
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, OperandStack stack)
                {
                    return new StaticOperandStack("I", stack.next().next(), this); // int is correct here
                }

            }, // -
    DCMPL
            {
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, OperandStack stack)
                {
                    return new StaticOperandStack("I", stack.next().next(), this); // int is correct here
                }

            }, // -
    DCMPG
            {
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, OperandStack stack)
                {
                    return new StaticOperandStack("I", stack.next().next(), this); // int is correct here
                }

            }, // -
    IFEQ
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return stack.next();
                }
            }, // visitJumpInsn
    IFNE
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return stack.next();
                }
            }, // -
    IFLT
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return stack.next();
                }
            }, // -
    IFGE
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return stack.next();
                }
            }, // -
    IFGT
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return stack.next();
                }
            }, // -
    IFLE
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return stack.next();
                }
            }, // -
    IF_ICMPEQ
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return stack.next().next();
                }
            }, // -
    IF_ICMPNE
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return stack.next().next();
                }
            }, // -
    IF_ICMPLT
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return stack.next().next();
                }
            }, // -
    IF_ICMPGE
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return stack.next().next();
                }
            }, // -
    IF_ICMPGT
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return stack.next().next();
                }
            }, // -
    IF_ICMPLE
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return stack.next().next();
                }
            }, // -
    IF_ACMPEQ
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return stack.next().next();
                }
            }, // -
    IF_ACMPNE
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return stack.next().next();
                }
            }, // -
    GOTO
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return stack;
                }
            }, // -
    JSR
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    System.out.println("TODO");
                    return stack; // TODO
                }
            }, // -
    RET
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return stack;
                }
            }, // visitVarInsn
    TABLESWITCH
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return stack.next();
                }
            }, // visiTableSwitchInsn
    LOOKUPSWITCH
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return stack.next();
                }
            }, // visitLookupSwitch
    IRETURN
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return stack.next();
                }
            }, // visitInsn
    LRETURN
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return stack.next();
                }
            }, // -
    FRETURN
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return stack.next();
                }
            }, // -
    DRETURN
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return stack.next();
                }
            }, // -
    ARETURN
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return stack.next();
                }
            }, // -
    RETURN
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return AbstractOperandStack.EMPTY_STACK;
                }
            }, // -
    GETSTATIC
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new StaticOperandStack(instruction.getDesc(), stack, this);
                }
            }, // visitFieldInsn
    PUTSTATIC
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return stack.next();
                }
            }, // -
    GETFIELD
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new StaticOperandStack(instruction.getDesc(), stack.next(), this);
                }
            }, // -
    PUTFIELD
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return stack.next().next();
                }
            }, // -
    INVOKEVIRTUAL
            {
                @Override
                @SuppressWarnings("unchecked")
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    final MethodInvocationInstruction methodInstruction = (MethodInvocationInstruction) instruction;
                    return ("V".equals(methodInstruction.getReturnType())) ?
                            MethodInvocationOperandStack.popStack(methodInstruction, stack) :
                            new MethodInvocationOperandStack(methodInstruction, stack, this);
                }
            }, // - visitMethod
    INVOKESPECIAL
            {
                @Override
                @SuppressWarnings("unchecked")
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    final MethodInvocationInstruction methodInstruction = (MethodInvocationInstruction) instruction;
                    return ("V".equals(methodInstruction.getReturnType())) ?
                            MethodInvocationOperandStack.popStack(methodInstruction, stack) :
                            new MethodInvocationOperandStack(methodInstruction, stack, this);
                }
            },
    INVOKESTATIC
            {
                @Override
                @SuppressWarnings("unchecked")
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    final MethodInvocationInstruction methodInstruction = (MethodInvocationInstruction) instruction;
                    return ("V".equals(methodInstruction.getReturnType())) ?
                            MethodInvocationOperandStack.popStack(methodInstruction, stack) :
                            new MethodInvocationOperandStack(methodInstruction, stack, this);
                }
            },
    INVOKEINTERFACE
            {
                @Override
                @SuppressWarnings("unchecked")
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    final MethodInvocationInstruction methodInstruction = (MethodInvocationInstruction) instruction;
                    return ("V".equals(methodInstruction.getReturnType())) ?
                            MethodInvocationOperandStack.popStack(methodInstruction, stack) :
                            new MethodInvocationOperandStack(methodInstruction, stack, this);
                }
            },
    INVOKEDYNAMIC
            {
                @Override
                @SuppressWarnings("unchecked")
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    final MethodInvocationInstruction methodInstruction = (MethodInvocationInstruction) instruction;
                    return ("V".equals(methodInstruction.getReturnType())) ?
                            MethodInvocationOperandStack.popStack(methodInstruction, stack) :
                            new MethodInvocationOperandStack(methodInstruction, stack, this);
                }
            },
    NEW
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new StaticOperandStack(instruction.getOperandType(), stack, this);
                }
            }, // visitTypeInsn
    NEWARRAY
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new StaticOperandStack("[" + instruction.getOperandType(), stack.next(), this);
                }
            }, // visitIntInsn
    ANEWARRAY
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new StaticOperandStack("[" + instruction.getOperandType(), stack.next(), this);
                }
            }, // visitTypeInsn
    ARRAYLENGTH
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new StaticOperandStack("I", stack.next(), this);
                }
            }, // visitInsn

    ATHROW
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return stack;
                }
            }, // -
    CHECKCAST
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return stack;
                }
            }, // visitTypeInsn
    INSTANCEOF
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return new StaticOperandStack("I", stack.next(), this);
                }
            }, // -
    MONITORENTER
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return stack.next();
                }
            }, // visitInsn
    MONITOREXIT
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return stack.next();
                }
            }, // -
    WIDE
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    System.out.println("TODO");
                    return stack; // TODO
                }
            }, // NOT VISITED
    MULTIANEWARRAY
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    final int dim = instruction.getIntOperand();
                    OperandStack next = stack;
                    for (int i = 0; i < dim; i++)
                    {
                        next = next.next();
                    }
                    return new StaticOperandStack(instruction.getOperandType(), next, this);  // TODO check whether getOperandType is array type or not
                }
            }, // visitMultiANewArrayInsn
    IFNULL
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return stack.next();
                }
            }, // visitJumpInsn
    IFNONNULL
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    return stack.next();
                }
            }, // -
    GOTO_W
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    System.out.println("TODO");
                    return stack; // TODO
                }
            }, // -
    JSR_W
            {
                @Override
                public OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, final OperandStack stack)
                {
                    System.out.println("TODO");
                    return stack; // TODO
                }
            }; //

    public abstract OperandStack updateOperandStack(final ClassFileMethod method, final Instruction instruction, OperandStack stack);

    public List<String> updateLocals(final List<String> locals, final Instruction instruction)
    {
        return locals;
    }

    public boolean isConstant()
    {
        return false;
    }
}
