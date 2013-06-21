package org.freud.core;

import java.util.NoSuchElementException;

public final class FreudSource<T> {

    private final Iterable<T> sources;
    private final Class<T> type;

    public FreudSource(final Iterable<T> sources, final Class<T> type) {
        this.sources = sources;
        this.type = type;
    }

    public Iterable<T> getSources() {
        return sources;
    }

    public Class<T> getType() {
        return type;
    }

    public static <T> Class typeOf(final Iterable<T> iterable) {
        Class type;
        try {
            type = iterable.iterator().next().getClass();
        }
        catch (NoSuchElementException e) {
            type = String.class; // empty array so any type will do
        }
        return type;
    }
}
