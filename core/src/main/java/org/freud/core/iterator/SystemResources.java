/*
 * Copyright 2013 LMAX Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
