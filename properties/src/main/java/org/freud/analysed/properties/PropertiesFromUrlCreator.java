package org.freud.analysed.properties;

import org.freud.core.Creator;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public final class PropertiesFromUrlCreator implements Creator<URL,Properties> {

    @Override
    public Properties create(final URL source) {
        try {
            final Properties properties = new Properties();
            properties.load(source.openStream());
            return properties;
        }
        catch (IOException e) {
            throw new IllegalArgumentException("Cannot create Text", e);
        }

    }
}
