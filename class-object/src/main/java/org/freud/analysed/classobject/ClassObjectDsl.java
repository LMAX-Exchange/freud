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

    public static <T> Iterable<Class> classOf(Iterable<T> iterable, Class<T> type) {
        return classOf(new FreudSource(iterable, type));
    }

    public static Iterable<Class> classOf(FreudSource<String> source) {
        return new AnalysedObjects<String, Class>(new ClassFromNameCreator(), source.getSources());
    }

    public static Iterable<Class> classOf(FreudSource<String> source, ClassLoader classLoader) {
        return new AnalysedObjects<String, Class>(new ClassFromNameCreator(classLoader), source.getSources());
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

    public static boolean hasDeclaredFieldOfType(Class analysed, final Class type) {
        for (Field field : analysed.getDeclaredFields()) {
            if (field.getType() == type) {
                return true;
            }
        }
        return false;
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
