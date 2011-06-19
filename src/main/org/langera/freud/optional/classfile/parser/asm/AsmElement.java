package org.langera.freud.optional.classfile.parser.asm;

import org.langera.freud.optional.classfile.ClassFileAnnotation;
import org.langera.freud.optional.classfile.ClassFileElement;
import org.objectweb.asm.Opcodes;

import java.util.LinkedList;
import java.util.List;

abstract class AsmElement implements ClassFileElement
{
    private final int access;
    private final List<ClassFileAnnotation> annotationList;

    protected AsmElement(final int access)
    {
        this.access = access;
        this.annotationList = new LinkedList<ClassFileAnnotation>();
    }

    @Override
    public boolean isPublic()
    {
        return isAccessModifier(Opcodes.ACC_PUBLIC);
    }

    @Override
    public boolean isPrivate()
    {
        return isAccessModifier(Opcodes.ACC_PRIVATE);
    }

    @Override
    public boolean isProtected()
    {
        return isAccessModifier(Opcodes.ACC_PROTECTED);
    }

    @Override
    public boolean isFinal()
    {
        return isAccessModifier(Opcodes.ACC_FINAL);
    }

    @Override
    public boolean isSynthetic()
    {
        return isAccessModifier(Opcodes.ACC_SYNTHETIC);
    }

    @Override
    public List<ClassFileAnnotation> getAnnotationList()
    {
        return annotationList;
    }

    protected boolean isAccessModifier(final int mask)
    {
        return (access & mask) != 0;
    }

    protected void addAnnotation(AsmAnnotation annotation)
    {
        annotationList.add(annotation);
    }
}
