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
