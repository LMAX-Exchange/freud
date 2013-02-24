package org.freud.core.iterator;


import java.util.ArrayList;
import java.util.List;

public final class AnalysedObjectBreadcrumbs {

    public static AnalysedObjectBreadcrumbs BREADCRUMBS = new AnalysedObjectBreadcrumbs();

    private ThreadLocal<List> breadcrumbs = new ThreadLocal<List>() {
        public List initialValue() {
            return new ArrayList();
        }
    };

    private AnalysedObjectBreadcrumbs() {}

    public Object get(int index) {
        return breadcrumbs.get().get(index);
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
}
