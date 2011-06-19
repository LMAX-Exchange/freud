package org.langera.freud.optional.classfile.parser.asm;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;

final class AsmClassFileFactory implements ClassVisitor
{
    private final AsmClassFileGetter classFileGetter;
    private AsmClassFile currentClassFile;

    public AsmClassFileFactory(final AsmClassFileGetter classFileGetter)
    {
        this.classFileGetter = classFileGetter;
    }

    @Override
    public void visit(final int version, final int access, final String name, final String signature, final String superName, final String[] interfaces)
    {
        currentClassFile = new AsmClassFile(version, access, name, signature, superName, interfaces);
    }

    @Override
    public void visitSource(final String source, final String debug)
    {
    }

    @Override
    public void visitOuterClass(final String owner, final String name, final String desc)
    {
        currentClassFile.setOuterValues(name, desc);
    }

    @Override
    public AnnotationVisitor visitAnnotation(final String desc, final boolean visible)
    {
        return new AsmAnnotation(currentClassFile, desc, visible);
    }

    @Override
    public void visitAttribute(final Attribute attr)
    {
    }

    @Override
    public void visitInnerClass(final String name, final String outerName, final String innerName, final int access)
    {
        final String currentClassFileName = currentClassFile.getName();
        if (name.startsWith(currentClassFileName) && name.length() > currentClassFileName.length())
        {
            final AsmClassFile enclosingClassFile = classFileGetter.getClassFile(name, currentClassFile);
            currentClassFile.addInnerClass(enclosingClassFile, innerName, access);

        }
    }

    @Override
    public FieldVisitor visitField(final int access, final String name, final String desc, final String signature, final Object value)
    {
        return new AsmField(currentClassFile, access, name, desc, signature, value);
    }

    @Override
    public MethodVisitor visitMethod(final int access, final String name, final String desc, final String signature, final String[] exceptions)
    {
        return new AsmMethod(currentClassFile, access, name, desc, signature, exceptions);
    }

    @Override
    public void visitEnd()
    {
    }

    public AsmClassFile getClassFile()
    {
        return currentClassFile;
    }

    public void clear()
    {
        currentClassFile = null;
    }
}
