package org.freud.analysed.classbytecode;

import org.freud.analysed.classbytecode.method.ClassByteCodeMethod;
import org.freud.analysed.classbytecode.parser.asm.AsmClassByteCodeFromFileCreator;
import org.freud.analysed.classbytecode.parser.asm.AsmClassByteCodeFromNameCreator;
import org.freud.core.FreudSource;
import org.freud.core.iterator.AnalysedObjects;
import org.freud.core.iterator.SubTypeAnalysedObjects;

import java.io.File;

@SuppressWarnings("unchecked")
public final class ClassByteCodeDsl {

    private ClassByteCodeDsl() {
        // static utility
    }

    public static <T> Iterable<ClassByteCode> classOf(Iterable<T> iterable, Class<T> type) {
        return classOf(new FreudSource(iterable, type));
    }

    public static Iterable<ClassByteCode> classOf(FreudSource source) {
        if (File.class.equals(source.getType())) {
            return new AnalysedObjects<File, ClassByteCode>(new AsmClassByteCodeFromFileCreator(), source.getSources());
        }
        if (String.class.equals(source.getType())) {
            return new AnalysedObjects<String, ClassByteCode>(new AsmClassByteCodeFromNameCreator(), source.getSources());
        }
        throw new UnsupportedOperationException("Unsupported conversion " + source.getType() + " to ClassByteCode");
    }

    public static Iterable<ClassByteCode> classOf(FreudSource<String> source, ClassLoader classLoader) {
        return new AnalysedObjects<String, ClassByteCode>(new AsmClassByteCodeFromNameCreator(classLoader), source.getSources());
    }

    public static Iterable<ClassByteCodeMethod> methodsWithin(Iterable<ClassByteCode> classes) {
        return new SubTypeAnalysedObjects<ClassByteCode, ClassByteCodeMethod>(new MethodsOfClassByteCodeCreator(), classes);
    }

    public static Iterable<ClassByteCodeField> fieldsWithin(Iterable<ClassByteCode> classes) {
        return new SubTypeAnalysedObjects<ClassByteCode, ClassByteCodeField>(new FieldsOfClassByteCodeCreator(), classes);
    }
}
