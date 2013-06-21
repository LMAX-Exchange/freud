package org.freud.analysed.classbytecode.parser.asm;

import org.freud.analysed.classbytecode.ClassByteCode;
import org.freud.core.Creator;
import org.freud.core.util.IoUtil;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;

final class AsmClassByteCodeFromStreamCreator implements ClassVisitor, Creator<InputStream, ClassByteCode> {

    private final LinkedHashMap<String, AsmClassByteCode> classByNameCache;
    private AsmClassByteCode currentClassByteCode;

    public AsmClassByteCodeFromStreamCreator() {
        classByNameCache = new LinkedHashMap<String, AsmClassByteCode>();
    }

    @Override
    public ClassByteCode create(final InputStream source) {
        try {
            return parseClassInternal(source);
        }
        catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private ClassByteCode parseClassInternal(final InputStream source) throws IOException {
        try {
            final ClassReader reader = new ClassReader(source);
            currentClassByteCode = null;
            reader.accept(this, 0);
            return addToCacheAndReturn(currentClassByteCode);
        }
        finally {
            IoUtil.safeClose(source);
        }
    }

    @Override
    public void visit(final int version, final int access, final String name, final String signature, final String superName, final String[] interfaces) {
        final String className = name.replace('/', '.');
        final String superclassName = (superName == null) ? null : superName.replace('/', '.');
        for (int i = 0, size = interfaces.length; i < size; i++) {
            interfaces[i] = interfaces[i].replace('/', '.');

        }
        currentClassByteCode = new AsmClassByteCode(version, access, className, signature, superclassName, interfaces);
    }

    @Override
    public void visitSource(final String source, final String debug) {
    }

    @Override
    public void visitOuterClass(final String owner, final String name, final String desc) {
        currentClassByteCode.setOuterValues(name, desc);
    }

    @Override
    public AnnotationVisitor visitAnnotation(final String desc, final boolean visible) {
        return new AsmAnnotation(currentClassByteCode, desc, visible);
    }

    @Override
    public void visitAttribute(final Attribute attr) {
    }

    @Override
    public void visitInnerClass(final String name, final String outerName, final String innerName, final int access) {
        final String currentClassName = currentClassByteCode.getName();
        if (name.startsWith(currentClassName) && name.length() > currentClassName.length()) {
            final AsmClassByteCode enclosingClassByteCode = getClassByteCode(name, currentClassByteCode);
            currentClassByteCode.addInnerClass(enclosingClassByteCode, innerName, access);

        }
    }

    @Override
    public FieldVisitor visitField(final int access, final String name, final String desc, final String signature, final Object value) {
        return new AsmField(currentClassByteCode, access, name, desc, signature, value);
    }

    @Override
    public MethodVisitor visitMethod(final int access, final String name, final String desc, final String signature, final String[] exceptions) {
        return new AsmMethod(currentClassByteCode, access, name, desc, signature, exceptions);
    }

    @Override
    public void visitEnd() {
    }

    private AsmClassByteCode getClassByteCode(final String name, final AsmClassByteCode classByteCode) {
        AsmClassByteCode asmClassByteCode;
        try {
            asmClassByteCode = classByNameCache.get(name);
            if (asmClassByteCode == null) {
                asmClassByteCode = (AsmClassByteCode) parseClassInternal(null);// TODO inner class
            }
        }
        catch (IOException e) {
            throw new IllegalArgumentException("Failed to find class byte code " + name, e);
        }
        return asmClassByteCode;
    }

    private AsmClassByteCode addToCacheAndReturn(final AsmClassByteCode classByteCode) {
        classByNameCache.put(classByteCode.getName(), classByteCode);
        return classByteCode;
    }
}
