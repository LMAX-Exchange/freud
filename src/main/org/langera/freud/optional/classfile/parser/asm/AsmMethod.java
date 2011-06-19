package org.langera.freud.optional.classfile.parser.asm;

import org.langera.freud.optional.classfile.ClassFileInnerClass;
import org.langera.freud.optional.classfile.ClassFileMethod;
import org.langera.freud.optional.classfile.Instruction;
import org.langera.freud.optional.classfile.Opcode;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.objectweb.asm.util.AbstractVisitor.OPCODES;

final class AsmMethod extends AsmElement implements MethodVisitor, ClassFileMethod
{
    private static final Opcode[] OPCODES_ARRAY = Opcode.values();
    private static final Class[] NEWARRAY_TYPES =
            {
                    null, null, null, null,
                    boolean.class, char.class,
                    float.class, double.class,
                    byte.class, short.class,
                    int.class, long.class,
            };

    private final String name;
    private final String desc;
    private final String signature;
    private final String[] exceptions;
    private final List<ClassFileInnerClass> innerClassList;
    private final List<Instruction> instructionList;

    private int currentLineNumber;


    private StringBuilder buf = new StringBuilder();
    private List<String> text = new ArrayList<String>();
    private final Map<Label, String> labelNames = new HashMap();

    public AsmMethod(final AsmClassFile classFile, final int access, final String name, final String desc, final String signature, final String... exceptions)
    {
        super(access);
        this.signature = signature;
        this.exceptions = exceptions;
        this.name = name;
        this.desc = desc;
        this.innerClassList = new LinkedList<ClassFileInnerClass>();
        this.instructionList = new ArrayList<Instruction>();
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
    public List<ClassFileInnerClass> getAnonymousClassList()
    {
        return innerClassList;
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
    }


    public void visitCode()
    {
    }

    public void visitFrame(
            final int type,
            final int nLocal,
            final Object[] local,
            final int nStack,
            final Object[] stack)
    {
        buf.setLength(0);
        switch (type)
        {
            case Opcodes.F_NEW:
            case Opcodes.F_FULL:
                declareFrameTypes(nLocal, local);
                declareFrameTypes(nStack, stack);
                if (type == Opcodes.F_NEW)
                {
                    buf.append("mv.visitFrame(Opcodes.F_NEW, ");
                }
                else
                {
                    buf.append("mv.visitFrame(Opcodes.F_FULL, ");
                }
                buf.append(nLocal).append(", new Object[] {");
                appendFrameTypes(nLocal, local);
                buf.append("}, ").append(nStack).append(", new Object[] {");
                appendFrameTypes(nStack, stack);
                buf.append('}');
                break;
            case Opcodes.F_APPEND:
                declareFrameTypes(nLocal, local);
                buf.append("mv.visitFrame(Opcodes.F_APPEND,")
                        .append(nLocal)
                        .append(", new Object[] {");
                appendFrameTypes(nLocal, local);
                buf.append("}, 0, null");
                break;
            case Opcodes.F_CHOP:
                buf.append("mv.visitFrame(Opcodes.F_CHOP,")
                        .append(nLocal)
                        .append(", null, 0, null");
                break;
            case Opcodes.F_SAME:
                buf.append("mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null");
                break;
            case Opcodes.F_SAME1:
                declareFrameTypes(1, stack);
                buf.append("mv.visitFrame(Opcodes.F_SAME1, 0, null, 1, new Object[] {");
                appendFrameTypes(1, stack);
                buf.append('}');
                break;
        }
        buf.append(");\n");
        text.add(buf.toString());
    }

    public void visitInsn(final int opcode)
    {
        instructionList.add(AsmInstruction.getInstance(OPCODES_ARRAY[opcode], currentLineNumber));
    }

    public void visitIntInsn(final int opcodeUsed, final int operand)
    {
        final Opcode opcode = OPCODES_ARRAY[opcodeUsed];
        if (opcode == Opcode.NEWARRAY)
        {
            instructionList.add(AsmInstruction.getInstance(opcode, currentLineNumber, NEWARRAY_TYPES[operand], -1));
        }
        else
        {
            instructionList.add(AsmInstruction.getInstance(opcode, currentLineNumber, operand, -1));
        }
    }

    public void visitVarInsn(final int opcode, final int var)
    {
        instructionList.add(AsmInstruction.getInstance(OPCODES_ARRAY[opcode], currentLineNumber, -1, var));
    }

    public void visitTypeInsn(final int opcodeUsed, final String type)
    {
        final Opcode opcode = OPCODES_ARRAY[opcodeUsed];
        final Class clazz = safeGetClassForType(type);
        if (clazz != null)
        {
            instructionList.add(AsmInstruction.getInstance(opcode, currentLineNumber, clazz, -1));
        }
        else
        {
            instructionList.add(AsmInstruction.getInstance(opcode, currentLineNumber, type, -1));
        }
    }

    public void visitFieldInsn(
            final int opcode,
            final String owner,
            final String name,
            final String desc)
    {
        instructionList.add(AsmInstruction.getInstance(OPCODES_ARRAY[opcode], currentLineNumber, owner, name, desc));
    }

    public void visitMethodInsn(
            final int opcode,
            final String owner,
            final String name,
            final String desc)
    {
        instructionList.add(AsmInstruction.getInstance(OPCODES_ARRAY[opcode], currentLineNumber, owner, name, desc));
    }

    public void visitJumpInsn(final int opcode, final Label label)
    {
        buf.setLength(0);
        declareLabel(label);
        buf.append("mv.visitJumpInsn(").append(OPCODES[opcode]).append(", ");
        appendLabel(label);
        buf.append(");\n");
        text.add(buf.toString());
    }

    public void visitLabel(final Label label)
    {
        buf.setLength(0);
        declareLabel(label);
        buf.append("mv.visitLabel(");
        appendLabel(label);
        buf.append(");\n");
        text.add(buf.toString());
    }

    public void visitLdcInsn(final Object cst)
    {
        String constantStr = cst.toString();
        if (cst instanceof Type)
        {
            Class constantClass = safeGetClassForType(cst.toString());
            if  (constantClass != null)
            {
                instructionList.add(AsmInstruction.getInstance(Opcode.LDC, currentLineNumber, constantClass, -1));
            }
        }
        instructionList.add(AsmInstruction.getInstance(Opcode.LDC, currentLineNumber, constantStr, -1));
    }

    public void visitIincInsn(final int var, final int increment)
    {
        instructionList.add(AsmInstruction.getInstance(Opcode.IINC, currentLineNumber, increment, var));
    }

    public void visitTableSwitchInsn(
            final int min,
            final int max,
            final Label dflt,
            final Label[] labels)
    {
        buf.setLength(0);
        for (int i = 0; i < labels.length; ++i)
        {
            declareLabel(labels[i]);
        }
        declareLabel(dflt);

        buf.append("mv.visitTableSwitchInsn(")
                .append(min)
                .append(", ")
                .append(max)
                .append(", ");
        appendLabel(dflt);
        buf.append(", new Label[] {");
        for (int i = 0; i < labels.length; ++i)
        {
            buf.append(i == 0 ? " " : ", ");
            appendLabel(labels[i]);
        }
        buf.append(" });\n");
        text.add(buf.toString());
    }

    public void visitLookupSwitchInsn(
            final Label dflt,
            final int[] keys,
            final Label[] labels)
    {
        buf.setLength(0);
        for (int i = 0; i < labels.length; ++i)
        {
            declareLabel(labels[i]);
        }
        declareLabel(dflt);

        buf.append("mv.visitLookupSwitchInsn(");
        appendLabel(dflt);
        buf.append(", new int[] {");
        for (int i = 0; i < keys.length; ++i)
        {
            buf.append(i == 0 ? " " : ", ").append(keys[i]);
        }
        buf.append(" }, new Label[] {");
        for (int i = 0; i < labels.length; ++i)
        {
            buf.append(i == 0 ? " " : ", ");
            appendLabel(labels[i]);
        }
        buf.append(" });\n");
        text.add(buf.toString());
    }

    public void visitMultiANewArrayInsn(final String desc, final int dims)
    {
        Class type = safeGetClassForType(desc);
        if (type != null)
        {
            instructionList.add(AsmInstruction.getInstance(Opcode.MULTIANEWARRAY, currentLineNumber, type, dims));
        }
        instructionList.add(AsmInstruction.getInstance(Opcode.MULTIANEWARRAY, currentLineNumber, desc, dims));
    }

    public void visitTryCatchBlock(
            final Label start,
            final Label end,
            final Label handler,
            final String type)
    {
        buf.setLength(0);
        declareLabel(start);
        declareLabel(end);
        declareLabel(handler);
        buf.append("mv.visitTryCatchBlock(");
        appendLabel(start);
        buf.append(", ");
        appendLabel(end);
        buf.append(", ");
        appendLabel(handler);
        buf.append(", ");
        appendConstant(type);
        buf.append(");\n");
        text.add(buf.toString());
    }

    public void visitLocalVariable(
            final String name,
            final String desc,
            final String signature,
            final Label start,
            final Label end,
            final int index)
    {
        buf.setLength(0);
        buf.append("mv.visitLocalVariable(");
        appendConstant(name);
        buf.append(", ");
        appendConstant(desc);
        buf.append(", ");
        appendConstant(signature);
        buf.append(", ");
        appendLabel(start);
        buf.append(", ");
        appendLabel(end);
        buf.append(", ").append(index).append(");\n");
        text.add(buf.toString());
    }

    public void visitLineNumber(final int line, final Label start)
    {
        currentLineNumber = line;
    }

    public void visitMaxs(final int maxStack, final int maxLocals)
    {
        buf.setLength(0);
        buf.append("mv.visitMaxs(")
                .append(maxStack)
                .append(", ")
                .append(maxLocals)
                .append(");\n");
        text.add(buf.toString());
    }

    private void declareFrameTypes(final int n, final Object[] o)
    {
        for (int i = 0; i < n; ++i)
        {
            if (o[i] instanceof Label)
            {
                declareLabel((Label) o[i]);
            }
        }
    }

    private void appendFrameTypes(final int n, final Object[] o)
    {
        for (int i = 0; i < n; ++i)
        {
            if (i > 0)
            {
                buf.append(", ");
            }
            if (o[i] instanceof String)
            {
                appendConstant(o[i]);
            }
            else if (o[i] instanceof Integer)
            {
                switch (((Integer) o[i]).intValue())
                {
                    case 0:
                        buf.append("Opcodes.TOP");
                        break;
                    case 1:
                        buf.append("Opcodes.INTEGER");
                        break;
                    case 2:
                        buf.append("Opcodes.FLOAT");
                        break;
                    case 3:
                        buf.append("Opcodes.DOUBLE");
                        break;
                    case 4:
                        buf.append("Opcodes.LONG");
                        break;
                    case 5:
                        buf.append("Opcodes.NULL");
                        break;
                    case 6:
                        buf.append("Opcodes.UNINITIALIZED_THIS");
                        break;
                }
            }
            else
            {
                appendLabel((Label) o[i]);
            }
        }
    }

    /**
     * Appends a declaration of the given label to {@link #buf buf}. This
     * declaration is of the form "Label lXXX = new Label();". Does nothing if
     * the given label has already been declared.
     *
     * @param l a label.
     */
    private void declareLabel(final Label l)
    {
        String name = (String) labelNames.get(l);
        if (name == null)
        {
            name = "l" + labelNames.size();
            labelNames.put(l, name);
            buf.append("Label ").append(name).append(" = new Label();\n");
        }
    }

    /**
     * Appends the name of the given label to {@link #buf buf}. The given label
     * <i>must</i> already have a name. One way to ensure this is to always
     * call {@link #declareLabel declared} before calling this method.
     *
     * @param l a label.
     */
    private void appendLabel(final Label l)
    {
        buf.append((String) labelNames.get(l));
    }

    /**
     * Appends a string representation of the given constant to the given
     * buffer.
     *
     * @param cst an {@link Integer}, {@link Float}, {@link Long},
     *            {@link Double} or {@link String} object. May be <tt>null</tt>.
     */
    void appendConstant(final Object cst)
    {
        appendConstant(buf, cst);
    }

    /**
     * Appends a quoted string to a given buffer.
     *
     * @param buf the buffer where the string must be added.
     * @param s   the string to be added.
     */
    public static void appendString(final StringBuilder buf, final String s)
    {
        buf.append('\"');
        for (int i = 0; i < s.length(); ++i)
        {
            char c = s.charAt(i);
            if (c == '\n')
            {
                buf.append("\\n");
            }
            else if (c == '\r')
            {
                buf.append("\\r");
            }
            else if (c == '\\')
            {
                buf.append("\\\\");
            }
            else if (c == '"')
            {
                buf.append("\\\"");
            }
            else if (c < 0x20 || c > 0x7f)
            {
                buf.append("\\u");
                if (c < 0x10)
                {
                    buf.append("000");
                }
                else if (c < 0x100)
                {
                    buf.append("00");
                }
                else if (c < 0x1000)
                {
                    buf.append('0');
                }
                buf.append(Integer.toString(c, 16));
            }
            else
            {
                buf.append(c);
            }
        }
        buf.append('\"');
    }


    /**
     * Appends a string representation of the given constant to the given
     * buffer.
     *
     * @param buf a string buffer.
     * @param cst an {@link Integer}, {@link Float}, {@link Long},
     *            {@link Double} or {@link String} object. May be <tt>null</tt>.
     */
    static void appendConstant(final StringBuilder buf, final Object cst)
    {
        if (cst == null)
        {
            buf.append("null");
        }
        else if (cst instanceof String)
        {
            appendString(buf, (String) cst);
        }
        else if (cst instanceof Type)
        {
            buf.append("Type.getType(\"");
            buf.append(((Type) cst).getDescriptor());
            buf.append("\")");
        }
        else if (cst instanceof Byte)
        {
            buf.append("new Byte((byte)").append(cst).append(')');
        }
        else if (cst instanceof Boolean)
        {
            buf.append(((Boolean) cst).booleanValue() ? "Boolean.TRUE" : "Boolean.FALSE");
        }
        else if (cst instanceof Short)
        {
            buf.append("new Short((short)").append(cst).append(')');
        }
        else if (cst instanceof Character)
        {
            int c = ((Character) cst).charValue();
            buf.append("new Character((char)").append(c).append(')');
        }
        else if (cst instanceof Integer)
        {
            buf.append("new Integer(").append(cst).append(')');
        }
        else if (cst instanceof Float)
        {
            buf.append("new Float(\"").append(cst).append("\")");
        }
        else if (cst instanceof Long)
        {
            buf.append("new Long(").append(cst).append("L)");
        }
        else if (cst instanceof Double)
        {
            buf.append("new Double(\"").append(cst).append("\")");
        }
        else if (cst instanceof byte[])
        {
            byte[] v = (byte[]) cst;
            buf.append("new byte[] {");
            for (int i = 0; i < v.length; i++)
            {
                buf.append(i == 0 ? "" : ",").append(v[i]);
            }
            buf.append('}');
        }
        else if (cst instanceof boolean[])
        {
            boolean[] v = (boolean[]) cst;
            buf.append("new boolean[] {");
            for (int i = 0; i < v.length; i++)
            {
                buf.append(i == 0 ? "" : ",").append(v[i]);
            }
            buf.append('}');
        }
        else if (cst instanceof short[])
        {
            short[] v = (short[]) cst;
            buf.append("new short[] {");
            for (int i = 0; i < v.length; i++)
            {
                buf.append(i == 0 ? "" : ",").append("(short)").append(v[i]);
            }
            buf.append('}');
        }
        else if (cst instanceof char[])
        {
            char[] v = (char[]) cst;
            buf.append("new char[] {");
            for (int i = 0; i < v.length; i++)
            {
                buf.append(i == 0 ? "" : ",")
                        .append("(char)")
                        .append((int) v[i]);
            }
            buf.append('}');
        }
        else if (cst instanceof int[])
        {
            int[] v = (int[]) cst;
            buf.append("new int[] {");
            for (int i = 0; i < v.length; i++)
            {
                buf.append(i == 0 ? "" : ",").append(v[i]);
            }
            buf.append('}');
        }
        else if (cst instanceof long[])
        {
            long[] v = (long[]) cst;
            buf.append("new long[] {");
            for (int i = 0; i < v.length; i++)
            {
                buf.append(i == 0 ? "" : ",").append(v[i]).append('L');
            }
            buf.append('}');
        }
        else if (cst instanceof float[])
        {
            float[] v = (float[]) cst;
            buf.append("new float[] {");
            for (int i = 0; i < v.length; i++)
            {
                buf.append(i == 0 ? "" : ",").append(v[i]).append('f');
            }
            buf.append('}');
        }
        else if (cst instanceof double[])
        {
            double[] v = (double[]) cst;
            buf.append("new double[] {");
            for (int i = 0; i < v.length; i++)
            {
                buf.append(i == 0 ? "" : ",").append(v[i]).append('d');
            }
            buf.append('}');
        }
    }

    @Override
    public void visitEnd()
    {
        for (String s : text)
        {
            System.out.print(s);
        }
    }


    ////////////////////////////////////////////////////////////////////////

    private static Class safeGetClassForType(final String type)
    {
        try
        {
            return Class.forName(type.replace('/', '.'));
        }
        // yep - throwable is what's needed here
        catch (Throwable e)
        {
            return null;
        }
    }

}