package org.freud.analysed.classobject;

import org.freud.core.FreudSource;
import org.freud.core.iterator.AnalysedObjects;
import org.freud.core.iterator.SubTypeAnalysedObjects;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@SuppressWarnings("unchecked")
public final class ClassObjectDsl {

    private ClassObjectDsl() {
        // static utility
    }

    public static Iterable<Class> classOf(FreudSource<String> source) {
        return new AnalysedObjects<String, Class>(source.getSources(), new ClassFromNameCreator());
    }

    public static Iterable<Class> classOf(FreudSource<String> source, ClassLoader classLoader) {
        return new AnalysedObjects<String, Class>(source.getSources(), new ClassFromNameCreator(classLoader));
    }

    public static Iterable<Method> methodsWithin(Iterable<Class> classes) {
        return new SubTypeAnalysedObjects<Class, Method>(new MethodsOfClassCreator(), classes);
    }

    public static Iterable<Method> declaredMethodsWithin(Iterable<Class> classes) {
        return new SubTypeAnalysedObjects<Class, Method>(new MethodsOfClassCreator(), classes);
    }

    public static Iterable<Field> fieldsWithin(Iterable<Class> classes) {
        return new SubTypeAnalysedObjects<Class, Field>(new FieldsOfClassCreator(), classes);
    }

    public static Iterable<Field> declaredFieldsWithin(Iterable<Class> classes) {
        return new SubTypeAnalysedObjects<Class, Field>(new FieldsOfClassCreator(), classes);
    }

    public static boolean hasMethod(Class analysed, final String name, final Class... parameterTypes) {
        try {
            analysed.getMethod(name, parameterTypes);
            return true;
        }
        catch (NoSuchMethodException e) {
            return false;
        }
    }

    public static boolean hasDeclaredMethod(Class analysed, final String name, final Class... parameterTypes) {
        try {
            analysed.getDeclaredMethod(name, parameterTypes);
            return true;
        }
        catch (NoSuchMethodException e) {
            return false;
        }
    }

    public static boolean hasField(Class analysed, final String name) {
        try {
            analysed.getField(name);
            return true;
        }
        catch (NoSuchFieldException e) {
            return false;
        }
    }

    public static boolean hasDeclaredField(Class analysed, final String name) {
        try {
            analysed.getDeclaredField(name);
            return true;
        }
        catch (NoSuchFieldException e) {
            return false;
        }
    }
}
