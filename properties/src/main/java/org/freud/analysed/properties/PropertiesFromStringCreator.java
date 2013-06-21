package org.freud.analysed.properties;

import org.freud.core.Creator;

import java.io.IOException;
import java.io.StringReader;
import java.util.Properties;

public final class PropertiesFromStringCreator implements Creator<String, Properties> {

    @Override
    public Properties create(final String source) {
        Properties properties = new Properties();
        try {
            properties.load(new StringReader(source));
        }
        catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        return properties;
    }
}
