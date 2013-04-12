package org.freud.analysed.classbytecode.parser.asm;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;

final class AsmClassByteCodeFactory implements ClassVisitor
{
    private final AsmClassByteCodeGetter classByteCodeGetter;
    private AsmClassByteCode currentClassByteCode;

    public AsmClassByteCodeFactory(final AsmClassByteCodeGetter classByteCodeGetter)
    {
        this.classByteCodeGetter = classByteCodeGetter;
    }

    @Override
    public void visit(final int version, final int access, final String name, final String signature, final String superName, final String[] interfaces)
    {
        final String className = name.replace('/', '.');
        final String superclassName = (superName == null) ? null : superName.replace('/', '.');
        for (int i = 0, size = interfaces.length; i < size; i++)
        {
            interfaces[i] = interfaces[i].replace('/', '.');

        }
        currentClassByteCode = new AsmClassByteCode(version, access, className, signature, superclassName, interfaces);
    }

    @Override
    public void visitSource(final String source, final String debug)
    {
    }

    @Override
    public void visitOuterClass(final String owner, final String name, final String desc)
    {
        currentClassByteCode.setOuterValues(name, desc);
    }

    @Override
    public AnnotationVisitor visitAnnotation(final String desc, final boolean visible)
    {
        return new AsmAnnotation(currentClassByteCode, desc, visible);
    }

    @Override
    public void visitAttribute(final Attribute attr)
    {
    }

    @Override
    public void visitInnerClass(final String name, final String outerName, final String innerName, final int access)
    {
        final String currentClassName = currentClassByteCode.getName();
        if (name.startsWith(currentClassName) && name.length() > currentClassName.length())
        {
            final AsmClassByteCode enclosingClassByteCode = classByteCodeGetter.getClassByteCode(name, currentClassByteCode);
            currentClassByteCode.addInnerClass(enclosingClassByteCode, innerName, access);

        }
    }

    @Override
    public FieldVisitor visitField(final int access, final String name, final String desc, final String signature, final Object value)
    {
        return new AsmField(currentClassByteCode, access, name, desc, signature, value);
    }

    @Override
    public MethodVisitor visitMethod(final int access, final String name, final String desc, final String signature, final String[] exceptions)
    {
        return new AsmMethod(currentClassByteCode, access, name, desc, signature, exceptions);
    }

    @Override
    public void visitEnd()
    {
    }

    public AsmClassByteCode getClassByteCode()
    {
        return currentClassByteCode;
    }

    public void clear()
    {
        currentClassByteCode = null;
    }
}
