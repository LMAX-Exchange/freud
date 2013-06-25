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

package org.freud.analysed.properties;

import org.freud.core.Creator;

import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

public final class PropertiesToPropertyCreator implements Creator<Properties, Iterable<Property>> {

    @Override
    public Iterable<Property> create(final Properties source) {
        return new PropertyIterable(source);
    }


    private static class PropertyIterable implements Iterable<Property> {

        private final Properties properties;

        private PropertyIterable(final Properties source) {
            this.properties = source;
        }

        @Override
        public Iterator<Property> iterator() {
            return new InternalIterator();
        }

        private class InternalIterator implements Iterator<Property> {

            Iterator<Map.Entry<Object, Object>> propertiesIterator;

            private InternalIterator() {
                propertiesIterator = properties.entrySet().iterator();
            }

            @Override
            public boolean hasNext() {
                return propertiesIterator.hasNext();
            }

            @Override
            public Property next() {
                Map.Entry entry = propertiesIterator.next();
                return new Property((String) entry.getKey(), (String) entry.getValue());
            }

            @Override
            public final void remove() {
                throw new UnsupportedOperationException();
            }
        }
    }
}
