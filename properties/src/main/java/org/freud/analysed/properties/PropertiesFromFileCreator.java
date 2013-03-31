package org.freud.analysed.properties;

import org.freud.core.Creator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public final class PropertiesFromFileCreator implements Creator<File, Properties> {

    @Override
    public Properties create(final File source) {
        Properties properties = new Properties();
        try {
            properties.load(new BufferedReader(new FileReader(source)));
        }
        catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        return properties;
    }
}
