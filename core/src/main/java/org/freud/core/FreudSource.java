package org.freud.core;

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
}
