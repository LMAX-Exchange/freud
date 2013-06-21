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
                return new Property((String)entry.getKey(), (String)entry.getValue());
            }

            @Override
            public final void remove() {
                throw new UnsupportedOperationException();
            }
        }
    }
}
