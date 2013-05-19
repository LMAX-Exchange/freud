package org.freud.core.iterator;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class AnalysedObjectBreadcrumbs {

    public static AnalysedObjectBreadcrumbs BREADCRUMBS = new AnalysedObjectBreadcrumbs();

    private ThreadLocal<List> breadcrumbs = new ThreadLocal<List>() {
        public List initialValue() {
            return new ArrayList();
        }
    };

    private AnalysedObjectBreadcrumbs() {
    }

    public Object get(int index) {
        return breadcrumbs.get().get(index);
    }

    @SuppressWarnings("unchecked")
    public <T> T get(Class<T> type) {
        if (type.isPrimitive()) {
            type = (Class<T>) PRIMITIVES_TO_WRAPPERS.get(type);
        }
        for (Object obj : breadcrumbs.get()) {
            if (type.isAssignableFrom(obj.getClass())) {
                return (T) obj;
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public Object[] copy() {
        final List list = breadcrumbs.get();
        Object[] copy = new Object[list.size()];
        return list.toArray(copy);
    }

    @SuppressWarnings("unchecked")
    public void add(Object obj) {
        breadcrumbs.get().add(obj);

    }

    @SuppressWarnings("unchecked")
    public void set(int index, Object obj) {
        breadcrumbs.get().set(index, obj);
    }

    public void remove(int index) {
        breadcrumbs.get().remove(index);
    }

    public void clear() {
        breadcrumbs.get().clear();
    }

    public int size() {
        return breadcrumbs.get().size();
    }

    @Override
    public String toString() {
        return "AnalysedObjectBreadcrumbs" + breadcrumbs.get();
    }

    private static final Map<Class<?>, Class<?>> PRIMITIVES_TO_WRAPPERS = new HashMap<Class<?>, Class<?>>();

    static {
        PRIMITIVES_TO_WRAPPERS.put(boolean.class, Boolean.class);
        PRIMITIVES_TO_WRAPPERS.put(byte.class, Byte.class);
        PRIMITIVES_TO_WRAPPERS.put(char.class, Character.class);
        PRIMITIVES_TO_WRAPPERS.put(double.class, Double.class);
        PRIMITIVES_TO_WRAPPERS.put(float.class, Float.class);
        PRIMITIVES_TO_WRAPPERS.put(int.class, Integer.class);
        PRIMITIVES_TO_WRAPPERS.put(long.class, Long.class);
        PRIMITIVES_TO_WRAPPERS.put(short.class, Short.class);
        PRIMITIVES_TO_WRAPPERS.put(void.class, Void.class);
    }
}
