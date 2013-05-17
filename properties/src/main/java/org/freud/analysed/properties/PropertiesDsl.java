package org.freud.analysed.properties;

import org.freud.core.FreudSource;
import org.freud.core.iterator.AnalysedObjects;
import org.freud.core.iterator.SubTypeAnalysedObjects;

import java.io.File;
import java.net.URL;
import java.util.Properties;

import static org.freud.core.FreudSource.typeOf;

public final class PropertiesDsl {

    private PropertiesDsl() {
        // static utility
    }

    @SuppressWarnings("unchecked")
    public static <T> Iterable<Property> propertyOf(Iterable<T> iterable) {
        Class type = typeOf(iterable);
        return propertyOf(new FreudSource(iterable, type));
    }

    @SuppressWarnings("unchecked")
    public static Iterable<Property> propertyOf(FreudSource source) {
        return new SubTypeAnalysedObjects<Properties, Property>(new PropertiesToPropertyCreator(), propertiesOf(source));
    }

    @SuppressWarnings("unchecked")
    public static Iterable<Properties> propertiesOf(FreudSource source) {
        if (File.class.equals(source.getType())) {
            return new AnalysedObjects<File, Properties>(new PropertiesFromFileCreator(), source.getSources());
        }
        if (URL.class.equals(source.getType())) {
            return new AnalysedObjects<URL, Properties>(new PropertiesFromUrlCreator(), source.getSources());
        }
        if (String.class.equals(source.getType())) {
            return new AnalysedObjects<String, Properties>(new PropertiesFromStringCreator(), source.getSources());
        }
        throw new UnsupportedOperationException("Unsupported conversion " + source.getType() + " to Properties");
    }
}
