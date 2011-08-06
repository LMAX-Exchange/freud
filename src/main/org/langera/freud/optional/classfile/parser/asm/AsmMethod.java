package org.langera.freud.optional.classfile.parser.asm;

import org.langera.freud.optional.classfile.ClassFileInnerClass;
import org.langera.freud.optional.classfile.method.ClassFileMethod;
import org.langera.freud.optional.classfile.method.LocalVariable;
import org.langera.freud.optional.classfile.method.instruction.AbstractOperandStack;
import org.langera.freud.optional.classfile.method.instruction.ConstInstruction;
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
import org.langera.freud.optional.classfile.method.instruction.StaticOperandStack;
import org.langera.freud.optional.classfile.method.instruction.VarInstruction;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import java.util.ArrayList;
import java.util.Arrays;
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
    private List<String> currentLocals;
    private String returnType;

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
        currentLocals = new ArrayList<String>();
        initLocals(desc);
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
    public String getReturnType()
    {
        return returnType;
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
    public String getLocalVariableType(final int index)
    {
        return currentLocals.get(index);
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
        final Instruction instruction = new Instruction(instructionList.size(), OPCODES_ARRAY[opcode], currentLineNumber);
        updateCurrentState(instruction);
    }

    public void visitIntInsn(final int opcodeUsed, final int operand)
    {
        final Opcode opcode = OPCODES_ARRAY[opcodeUsed];
        final Instruction instruction;
        if (opcode == Opcode.NEWARRAY)
        {
            instruction = new ReferenceOperandInstruction(instructionList.size(), opcode, currentLineNumber, NEWARRAY_TYPES[operand]);
        }
        else
        {
            instruction = new IntOperandInstruction(instructionList.size(), opcode, currentLineNumber, operand);
        }
        updateCurrentState(instruction);
    }

    public void visitVarInsn(final int opcodeUsed, final int var)
    {
        final Instruction instruction = new VarInstruction(instructionList.size(), OPCODES_ARRAY[opcodeUsed], currentLineNumber, var);
        updateCurrentState(instruction);
    }

    public void visitTypeInsn(final int opcodeUsed, final String type)
    {
        final Opcode opcode = OPCODES_ARRAY[opcodeUsed];
        final String operandType = "L" + type + ";";
        final Instruction instruction = new ReferenceOperandInstruction(instructionList.size(), opcode, currentLineNumber, operandType);
        updateCurrentState(instruction);
    }

    public void visitFieldInsn(
            final int opcode,
            final String owner,
            final String name,
            final String desc)
    {
        final Instruction instruction = new FieldInstruction(instructionList.size(), OPCODES_ARRAY[opcode], currentLineNumber, owner, name, desc);
        updateCurrentState(instruction);
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
            final String argsAsString = matcher.group(1);
            final ArrayList<String> argsContainer = new ArrayList<String>();
            String returnType = matcher.group(2);
            parseArgs(argsAsString, argsContainer);
            String[] args = argsContainer.toArray(new String[argsContainer.size()]);
            final Instruction instruction = new MethodInvocationInstruction(instructionList.size(), OPCODES_ARRAY[opcode], currentLineNumber,
                    "L" + owner + ";", name, args, returnType);
            updateCurrentState(instruction);
        }
    }

    public void visitJumpInsn(final int opcode, final org.objectweb.asm.Label asmLabel)
    {
        Label label = declareLabel(asmLabel);
        final Instruction instruction = new JumpInstruction(instructionList.size(), OPCODES_ARRAY[opcode], currentLineNumber, label);
        updateCurrentState(instruction);
    }

    public void visitLabel(final org.objectweb.asm.Label asmLabel)
    {
        final Label label = declareLabel(asmLabel);
        label.declare(instructionList.size());
        final String handledType = label.getHandledType();
        if (handledType != null)
        {
            currentOperandStack = new StaticOperandStack(handledType, currentOperandStack, null);
        }
    }

    public void visitLdcInsn(final Object constant)
    {
        final Instruction instruction;
        if (constant instanceof Type)
        {
            instruction = new ReferenceOperandInstruction(instructionList.size(), Opcode.LDC, currentLineNumber, constant.toString());
        }
        else
        {
            instruction = new ConstInstruction(instructionList.size(), Opcode.LDC, currentLineNumber, constant);
        }
        updateCurrentState(instruction);
    }

    public void visitIincInsn(final int var, final int increment)
    {
        final Instruction instruction = new IntOperandInstruction(instructionList.size(), Opcode.IINC, currentLineNumber, increment);
        updateCurrentState(instruction);
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
        if (type != null)
        {
            declareHandlerLabel(handler, "L" + type + ";");
        }
        declareLabel(end);
    }

    public void visitFrame(
            final int type,
            final int nLocal,
            final Object[] local,
            final int nStack,
            final Object[] stack)
    {
        final FrameType frameType = FrameType.getFrameType(type);
System.out.println("FRAME: " + frameType.name() + " " + Arrays.toString(local) + " "  + Arrays.toString(stack));
        switch (frameType)
        {
            case F_SAME:
                currentOperandStack = AbstractOperandStack.EMPTY_STACK;
                break;
            case F_SAME1:
                currentOperandStack = new StaticOperandStack(getTypeFromFrame(stack[0]), AbstractOperandStack.EMPTY_STACK, null);
                break;
            case F_APPEND:
                currentOperandStack = AbstractOperandStack.EMPTY_STACK;
                for (int i = 0; i < nLocal; i++)
                {
                    currentLocals.add(getTypeFromFrame(local[i]));
                }
                break;
            case F_CHOP:
                currentOperandStack = AbstractOperandStack.EMPTY_STACK;
                currentLocals = currentLocals.subList(0, currentLocals.size() - nLocal);
                break;
            case F_FULL:
                currentLocals = new ArrayList<String>();
                for (int i = 0; i < nLocal; i++)
                {
                    currentLocals.add(getTypeFromFrame(local[i]));

                }
                currentOperandStack = AbstractOperandStack.EMPTY_STACK;
                for (int i = 0; i < nStack; i++)
                {
                    currentOperandStack = new StaticOperandStack(getTypeFromFrame(stack[i]), currentOperandStack, null);
                }
                break;
            case F_NEW:
                break;
        }
    }

    public void visitMultiANewArrayInsn(final String desc, final int dims)
    {
        final Instruction instruction = new ReferenceOperandInstruction(instructionList.size(), Opcode.MULTIANEWARRAY, currentLineNumber, desc, dims);
        updateCurrentState(instruction);
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

    private String getTypeFromFrame(final Object item)
    {
        if (item instanceof String)
        {
            final String strItem = (String) item;
            return (strItem.indexOf('/') > -1) ? "L" + strItem + ";" : strItem;
        }
        else if (item instanceof org.objectweb.asm.Label)
        {
            return "Ljava/lang/Object;";
        }
        else if (item instanceof Integer)
        {
            final FrameValueType valueType = FrameValueType.getFrameValueType((Integer) item);
            switch (valueType)
            {
                case TOP:
                case NULL:
                case UNINITIALIZED_THIS:
                    return "Ljava/lang/Object;";
                case INTEGER:
                    return "I";
                case FLOAT:
                    return "F";
                case LONG:
                    return "J";
                case DOUBLE:
                    return "D";
                default:
                    throw new IllegalArgumentException("frame item " + item);
            }
        }
        else
        {
            throw new IllegalArgumentException("frame item " + item);
        }
    }

    private void initLocals(final String desc)
    {
        final Matcher matcher = METHOD_DESC_PATTERN.matcher(desc);
        if (matcher.matches())
        {
            final String paramsAsString = matcher.group(1);
            parseArgs(paramsAsString, currentLocals);
            returnType = matcher.group(2);
        }
        else
        {
            throw new IllegalArgumentException("desc " + desc);
        }
    }

    @Override
    public String toString()
    {
        return "AsmMethod[" + name + "]";
    }

    ////////////////////////////////////////////////////////////////////////

    private void parseArgs(final String argsAsString, final List<String> args)
    {
        boolean start = true;
        final int len = argsAsString.length();
        int ptr = 0;
        for (int i = 0; i < len; i++)
        {
            final char c = argsAsString.charAt(i);
            switch (c)
            {
                case '[':
                case 'L':
                    start = false;
                    break;
                case ';':
                    args.add(argsAsString.substring(ptr, i + 1));
                    ptr = i + 1;
                    start = true;
                    break;
                case 'B':
                case 'C':
                case 'D':
                case 'F':
                case 'I':
                case 'J':
                case 'S':
                case 'Z':
                    if (start)
                    {
                        args.add(String.valueOf(c));
                        ptr = i + 1;
                    }
                    break;
            }
        }
        if (ptr < len)
        {
            args.add(argsAsString.substring(ptr));
        }
    }

    private Label declareLabel(final org.objectweb.asm.Label asmLabel)
    {
        return storeLabel(asmLabel, Label.create(instructionList.size()));
    }

    private Label declareHandlerLabel(final org.objectweb.asm.Label asmLabel, final String type)
    {
        return storeLabel(asmLabel, Label.createHandler(instructionList.size(), type));
    }

    private Label declareLookupLabel(final org.objectweb.asm.Label asmLabel, final int key)
    {
        return storeLabel(asmLabel, Label.createLookupKey(instructionList.size(), key));
    }


    private Label declareDefaultLookupLabel(final org.objectweb.asm.Label asmLabel)
    {
        return storeLabel(asmLabel, Label.createDefaultLookupKey(instructionList.size()));
    }

    private Label storeLabel(final org.objectweb.asm.Label asmLabel, final Label label)
    {
        final Label oldLabel = labelByAsmLabelMap.get(asmLabel);
        if (oldLabel != null)
        {
            return oldLabel;
        }
        else
        {
            labelByAsmLabelMap.put(asmLabel, label);
            return label;
        }
    }

    private void updateCurrentState(final Instruction instruction)
    {
        instructionList.add(instruction);
        final Opcode opcode = instruction.getOpcode();
        ensureCurrentLocalsSize(instruction.getVarIndex());
        currentLocals =  opcode.updateLocals(currentLocals, instruction);
        currentOperandStack = opcode.updateOperandStack(this, instruction, currentOperandStack);
        System.out.println(name + "#" + opcode + " : " + currentOperandStack + " $ " + currentLocals);
        System.out.println(instruction);
        instruction.setOperandStack(currentOperandStack);
    }

    private void ensureCurrentLocalsSize(final int varIndex)
    {
        while (varIndex >= currentLocals.size())
        {
            currentLocals.add("");
        }
    }

    private enum FrameValueType
    {
        TOP(Opcodes.TOP),
        INTEGER(Opcodes.INTEGER),
        FLOAT(Opcodes.FLOAT),
        LONG(Opcodes.LONG),
        DOUBLE(Opcodes.DOUBLE),
        NULL(Opcodes.NULL),
        UNINITIALIZED_THIS(Opcodes.UNINITIALIZED_THIS);

        private final int asmValue;

        FrameValueType(final int asmValue)
        {
            this.asmValue = asmValue;
        }

        public static FrameValueType getFrameValueType(final int asmValue)
        {
            for (FrameValueType valueType : FrameValueType.values())
            {
                if (valueType.asmValue == asmValue)
                {
                    return valueType;
                }
            }
            throw new IllegalArgumentException("Unknown frame value type " + asmValue);
        }
    }

    private enum FrameType
    {
        F_NEW(Opcodes.F_NEW),
        F_FULL(Opcodes.F_FULL),
        F_APPEND(Opcodes.F_APPEND),
        F_CHOP(Opcodes.F_CHOP),
        F_SAME(Opcodes.F_SAME),
        F_SAME1(Opcodes.F_SAME1);

        private final int asmValue;

        FrameType(final int asmValue)
        {
            this.asmValue = asmValue;
        }

        public static FrameType getFrameType(final int asmValue)
        {
            for (FrameType type : FrameType.values())
            {
                if (type.asmValue == asmValue)
                {
                    return type;
                }
            }
            throw new IllegalArgumentException("Unknown frame type " + asmValue);
        }

    }
}