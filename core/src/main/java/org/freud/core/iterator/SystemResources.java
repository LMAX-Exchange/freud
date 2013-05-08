package org.freud.core.iterator;

import java.net.URL;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

import static java.lang.ClassLoader.getSystemClassLoader;

public final class SystemResources implements Iterable<URL> {

    private final Deque<URL> resources = new LinkedList<URL>();


    public SystemResources(final Collection<String> paths) {
        this(paths, getSystemClassLoader());
    }

    public SystemResources(final Collection<String> paths, final ClassLoader classLoader) {
        for (String path : paths) {
            this.resources.add(toUrl(classLoader, path));
        }
    }

    @Override
    public Iterator<URL> iterator() {
        return resources.iterator();
    }

    static URL toUrl(final ClassLoader classLoader, final String path) {
        final URL resource = classLoader.getResource(path);
        if (resource == null) {
            throw new IllegalArgumentException("Failed to find resource " + path);
        }
        return resource;
    }
}
