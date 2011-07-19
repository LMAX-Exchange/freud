package org.langera.freud.optional.classfile.parser.asm;

import org.langera.freud.optional.classfile.ClassFileInnerClass;
import org.langera.freud.optional.classfile.method.ClassFileMethod;
import org.langera.freud.optional.classfile.method.LocalVariable;
import org.langera.freud.optional.classfile.method.instruction.AbstractOperandStack;
import org.langera.freud.optional.classfile.method.instruction.FieldInstruction;
import org.langera.freud.optional.classfile.method.instruction.Instruction;
import org.langera.freud.optional.classfile.method.instruction.InstructionVisitor;
import org.langera.freud.optional.classfile.method.instruction.IntOperandInstruction;
import org.langera.freud.optional.classfile.method.instruction.JumpInstruction;
import org.langera.freud.optional.classfile.method.instruction.Label;
import org.langera.freud.optional.classfile.method.instruction.MethodInvocationInstruction;
import org.langera.freud.optional.classfile.method.instruction.Opcode;
import org.langera.freud.optional.classfile.method.instruction.OperandStack;
import org.langera.freud.optional.classfile.method.instruction.ReferenceOperandInstruction;
import org.langera.freud.optional.classfile.method.instruction.ConstInstruction;
import org.langera.freud.optional.classfile.method.instruction.VarInstruction;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class AsmMethod extends AsmElement implements MethodVisitor, ClassFileMethod
{
    private static final Pattern METHOD_DESC_PATTERN = Pattern.compile("\\((.*)\\)(.+)");
    private static final Opcode[] OPCODES_ARRAY = Opcode.values();
    private static final String[] NEWARRAY_TYPES =
            {
                    null, null, null, null,
                    boolean.class.getCanonicalName(), char.class.getCanonicalName(),
                    float.class.getCanonicalName(), double.class.getCanonicalName(),
                    byte.class.getCanonicalName(), short.class.getCanonicalName(),
                    int.class.getCanonicalName(), long.class.getCanonicalName(),
            };

    private final String name;
    private final String desc;
    private final String signature;
    private final String[] exceptions;
    private final List<ClassFileInnerClass> innerClassList;
    private final List<Instruction> instructionList;
    private final LinkedHashMap<String, LocalVariable> variableByNameMap;
    private final Map<org.objectweb.asm.Label, Label> labelByAsmLabelMap;

    private int currentLineNumber;
    private OperandStack currentOperandStack = AbstractOperandStack.EMPTY_STACK;

    public AsmMethod(final AsmClassFile classFile, final int access, final String name, final String desc, final String signature, final String... exceptions)
    {
        super(access);
        this.signature = signature;
        this.exceptions = exceptions;
        this.name = name;
        this.desc = desc;
        this.innerClassList = new LinkedList<ClassFileInnerClass>();
        this.instructionList = new ArrayList<Instruction>();
        this.labelByAsmLabelMap = new HashMap<org.objectweb.asm.Label, Label>();
        this.variableByNameMap = new LinkedHashMap<String, LocalVariable>();
        for (ClassFileInnerClass innerClass : classFile.getInnerClassList())
        {
            if (innerClass.isAnonymous() && name.equals(innerClass.getOuterName()) && desc.equals(innerClass.getOuterDesc()))
            {
                innerClassList.add(innerClass);
            }
        }
        classFile.addMethod(this);
        this.currentLineNumber = -1;
    }

    @Override
    public void findInstruction(final InstructionVisitor instructionVisitor)
    {
        for (final Instruction instruction : instructionList)
        {
            instruction.visit(instructionVisitor);
        }
    }

    @Override
    public Instruction getInstruction(final int index)
    {
        return instructionList.get(index);
    }

    @Override
    public List<ClassFileInnerClass> getAnonymousClassList()
    {
        return innerClassList;
    }

    @Override
    public LocalVariable getLocalVariable(final String name)
    {
        return variableByNameMap.get(name);
    }

    @Override
    public LocalVariable getLocalVariable(final int index)
    {
        int i = 0;
        for (LocalVariable variable : variableByNameMap.values())
        {
            if (index == i++)
            {
                return variable;
            }
        }
        return null;
    }

    @Override
    public boolean isStatic()
    {
        return isAccessModifier(Opcodes.ACC_STATIC);
    }

    @Override
    public boolean isSynchronized()
    {
        return isAccessModifier(Opcodes.ACC_SYNCHRONIZED);
    }

    @Override
    public boolean isBridge()
    {
        return isAccessModifier(Opcodes.ACC_BRIDGE);
    }

    @Override
    public boolean isVarargs()
    {
        return isAccessModifier(Opcodes.ACC_VARARGS);
    }

    @Override
    public boolean isNative()
    {
        return isAccessModifier(Opcodes.ACC_NATIVE);
    }

    @Override
    public boolean isStrict()
    {
        return isAccessModifier(Opcodes.ACC_STRICT);
    }

    @Override
    public boolean isAbstract()
    {
        return isAccessModifier(Opcodes.ACC_ABSTRACT);
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public String getDesc()
    {
        return desc;
    }

    @Override
    public String getSignature()
    {
        return signature;
    }

    @Override
    public String[] getExceptions()
    {
        return exceptions;
    }

    @Override
    public AnnotationVisitor visitAnnotationDefault()
    {
        return new AsmAnnotation(this);
    }

    @Override
    public AnnotationVisitor visitAnnotation(final String desc, final boolean visible)
    {
        return new AsmAnnotation(this, desc, visible);
    }

    @Override
    public AnnotationVisitor visitParameterAnnotation(final int parameter, final String desc, final boolean visible)
    {
        return new AsmAnnotation(this, parameter, desc, visible);
    }

    @Override
    public void visitAttribute(final Attribute attr)
    {
        //  no op
    }


    public void visitCode()
    {
        // no op
    }

    public void visitInsn(final int opcode)
    {
        final Instruction instruction = new Instruction(this, currentOperandStack, instructionList.size(), OPCODES_ARRAY[opcode], currentLineNumber);
        instructionList.add(instruction);
        currentOperandStack = instruction.getOperandStack();
    }

    public void visitIntInsn(final int opcodeUsed, final int operand)
    {
        final Opcode opcode = OPCODES_ARRAY[opcodeUsed];
        final Instruction instruction;
        if (opcode == Opcode.NEWARRAY)
        {
            instruction = new ReferenceOperandInstruction(this, currentOperandStack, instructionList.size(), opcode, currentLineNumber, NEWARRAY_TYPES[operand]);
        }
        else
        {
            instruction = new IntOperandInstruction(this, currentOperandStack, instructionList.size(), opcode, currentLineNumber, operand);
        }
        instructionList.add(instruction);
        currentOperandStack = instruction.getOperandStack();
    }

    public void visitVarInsn(final int opcodeUsed, final int var)
    {
        final Instruction instruction = new VarInstruction(this, currentOperandStack, instructionList.size(), OPCODES_ARRAY[opcodeUsed], currentLineNumber, var);
        instructionList.add(instruction);
        currentOperandStack = instruction.getOperandStack();
    }

    public void visitTypeInsn(final int opcodeUsed, final String type)
    {
        final Opcode opcode = OPCODES_ARRAY[opcodeUsed];
        final Instruction instruction = new ReferenceOperandInstruction(this, currentOperandStack, instructionList.size(), opcode, currentLineNumber, type.toString());
        instructionList.add(instruction);
        currentOperandStack = instruction.getOperandStack();
    }

    public void visitFieldInsn(
            final int opcode,
            final String owner,
            final String name,
            final String desc)
    {
        final Instruction instruction = new FieldInstruction(this, currentOperandStack, instructionList.size(), OPCODES_ARRAY[opcode], currentLineNumber, owner, name, desc);
        instructionList.add(instruction);
        currentOperandStack = instruction.getOperandStack();
    }

    public void visitMethodInsn(
            final int opcode,
            final String owner,
            final String name,
            final String desc)
    {
        final Matcher matcher = METHOD_DESC_PATTERN.matcher(desc);
        if (matcher.matches())
        {
            String[] args = matcher.group(1).split(",");
            String returnType = matcher.group(2);
            final Instruction instruction = new MethodInvocationInstruction(this, currentOperandStack, instructionList.size(), OPCODES_ARRAY[opcode], currentLineNumber,
                    "L" + owner + ";", name, args, returnType);
            instructionList.add(instruction);
            currentOperandStack = instruction.getOperandStack();
        }
    }

    public void visitJumpInsn(final int opcode, final org.objectweb.asm.Label asmLabel)
    {
        Label label = declareLabel(asmLabel);
        final Instruction instruction = new JumpInstruction(this, currentOperandStack, instructionList.size(), OPCODES_ARRAY[opcode], currentLineNumber, label);
        instructionList.add(instruction);
        currentOperandStack = instruction.getOperandStack();
    }

    public void visitLabel(final org.objectweb.asm.Label asmLabel)
    {
        final Label label = declareLabel(asmLabel);
        label.declare(instructionList.size());
    }

    public void visitLdcInsn(final Object constant)
    {
        final Instruction instruction;
        if (constant instanceof Type)
        {
            instruction = new ReferenceOperandInstruction(this, currentOperandStack, instructionList.size(), Opcode.LDC, currentLineNumber, constant.toString());
        }
        else
        {
            instruction = new ConstInstruction(this, currentOperandStack, instructionList.size(), Opcode.LDC, currentLineNumber, constant);
        }
        instructionList.add(instruction);
        currentOperandStack = instruction.getOperandStack();
    }

    public void visitIincInsn(final int var, final int increment)
    {
        final Instruction instruction = new IntOperandInstruction(this, currentOperandStack, instructionList.size(), Opcode.IINC, currentLineNumber, increment);
        instructionList.add(instruction);
        currentOperandStack = instruction.getOperandStack();
    }

    public void visitTableSwitchInsn(
            final int min,
            final int max,
            final org.objectweb.asm.Label dflt,
            final org.objectweb.asm.Label[] labels)
    {
        for (int i = 0; i < labels.length; ++i)
        {
            declareLookupLabel(labels[i], min + i);
        }
        declareDefaultLookupLabel(dflt);
    }

    public void visitLookupSwitchInsn(
            final org.objectweb.asm.Label dflt,
            final int[] keys,
            final org.objectweb.asm.Label[] labels)
    {
        for (int i = 0; i < labels.length; ++i)
        {
            declareLookupLabel(labels[i], keys[i]);
        }
        declareDefaultLookupLabel(dflt);
    }

    public void visitTryCatchBlock(
            final org.objectweb.asm.Label start,
            final org.objectweb.asm.Label end,
            final org.objectweb.asm.Label handler,
            final String type)
    {
        declareLabel(start);
        declareLabel(end);
        if (type != null)
        {
            declareHandlerLabel(handler, type);
        }
    }

    public void visitFrame(
            final int type,
            final int nLocal,
            final Object[] local,
            final int nStack,
            final Object[] stack)
    {
        // TODO
    }

    public void visitMultiANewArrayInsn(final String desc, final int dims)
    {
        final Instruction instruction = new ReferenceOperandInstruction(this, currentOperandStack, instructionList.size(), Opcode.MULTIANEWARRAY, currentLineNumber, desc, dims);
        instructionList.add(instruction);
        currentOperandStack = instruction.getOperandStack();
    }

    public void visitLocalVariable(
            final String name,
            final String desc,
            final String signature,
            final org.objectweb.asm.Label start,
            final org.objectweb.asm.Label end,
            final int index)
    {
        variableByNameMap.put(name, new LocalVariable(name, desc, signature, declareLabel(start), declareLabel(end)));
    }

    public void visitLineNumber(final int line, final org.objectweb.asm.Label start)
    {
        if (currentLineNumber < line)
        {
            currentLineNumber = line;
        }
        labelByAsmLabelMap.get(start).setLineNumber(line);
    }

    public void visitMaxs(final int maxStack, final int maxLocals)
    {
        // TODO
    }

    @Override
    public void visitEnd()
    {
        // no op
    }

    @Override
    public String toString()
    {
        return "AsmMethod[" + name + "]";
    }

    ////////////////////////////////////////////////////////////////////////


    private Label declareLabel(final org.objectweb.asm.Label asmLabel)
    {
        final Label label = Label.create(instructionList.size());
        final Label oldLabel = labelByAsmLabelMap.put(asmLabel, label);
        return (oldLabel != null) ? oldLabel : label;
    }

    private Label declareHandlerLabel(final org.objectweb.asm.Label asmLabel, final String type)
    {
        final Label label = Label.createHandler(instructionList.size(), type);
        final Label oldLabel = labelByAsmLabelMap.put(asmLabel, label);
        return (oldLabel != null) ? oldLabel : label;
    }


    private Label declareLookupLabel(final org.objectweb.asm.Label asmLabel, final int key)
    {
        final Label label = Label.createLookupKey(instructionList.size(), key);
        final Label oldLabel = labelByAsmLabelMap.put(asmLabel, label);
        return (oldLabel != null) ? oldLabel : label;
    }

    private Label declareDefaultLookupLabel(final org.objectweb.asm.Label asmLabel)
    {
        final Label label = Label.createDefaultLookupKey(instructionList.size());
        final Label oldLabel = labelByAsmLabelMap.put(asmLabel, label);
        return (oldLabel != null) ? oldLabel : label;
    }
}