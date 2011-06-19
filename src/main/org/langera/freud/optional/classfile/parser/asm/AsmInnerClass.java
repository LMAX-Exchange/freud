package org.langera.freud.optional.classfile.parser.asm;

import org.langera.freud.optional.classfile.ClassFileField;
import org.langera.freud.optional.classfile.ClassFileInnerClass;
import org.langera.freud.optional.classfile.ClassFileMethod;
import org.objectweb.asm.Opcodes;

import java.util.List;

final class AsmInnerClass extends AsmElement  implements ClassFileInnerClass
{
    private final AsmClassFile classFile;
    private final String innerName;

    AsmInnerClass(final AsmClassFile classFile, final String innerName, final int access)
    {
        super(access);
        this.classFile = classFile;
        this.innerName = innerName;
    }

    @Override
    public boolean isAnonymous()
    {
        return innerName == null;
    }

    @Override
    public boolean isStatic()
    {
        return isAccessModifier(Opcodes.ACC_STATIC);
    }

    @Override
    public String getOuterName()
    {
        return classFile.getOuterName();
    }

    @Override
    public String getOuterDesc()
    {
        return classFile.getOuterDesc();
    }

    @Override
    public List<ClassFileInnerClass> getInnerClassList()
    {
        return classFile.getInnerClassList();
    }

    @Override
    public List<ClassFileField> getFieldList()
    {
        return classFile.getFieldList();
    }

    @Override
    public List<ClassFileMethod> getMethodList()
    {
        return classFile.getMethodList();
    }

    @Override
    public String getSuperName()
    {
        return classFile.getSuperName();
    }

    @Override
    public String getSignature()
    {
        return classFile.getSignature();
    }

    @Override
    public String[] getInterfaces()
    {
        return classFile.getInterfaces();
    }

    @Override
    public String getName()
    {
        return classFile.getName();
    }

    @Override
    public boolean isSuper()
    {
        return classFile.isSuper();
    }

    @Override
    public boolean isAbstract()
    {
        return classFile.isAbstract();
    }

    @Override
    public boolean isAnnotation()
    {
        return classFile.isAnnotation();
    }

    @Override
    public boolean isEnum()
    {
        return classFile.isEnum();
    }

    @Override
    public boolean isInterface()
    {
        return classFile.isInterface();
    }

    @Override
    public int getVersion()
    {
        return classFile.getVersion();
    }
}
