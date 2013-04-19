package org.freud.analysed.classbytecode.parser

import org.objectweb.asm.ClassReader

class Debugger {

    public static void main(String[] args) throws  Exception {
        final InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(
                'org/freud/analysed/classbytecode/parser/asm/AsmClassByteCodeFromNameCreatorSpec.class');
        final ClassReader reader = new ClassReader(inputStream);
        reader.accept(new DebugVisitor(), 0);
    }
}
