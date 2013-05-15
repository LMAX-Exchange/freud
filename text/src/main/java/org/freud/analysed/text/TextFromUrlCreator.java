package org.freud.analysed.text;

import org.freud.core.Creator;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public final class TextFromUrlCreator implements Creator<URL, Text> {

    @Override
    public Text create(final URL source) {
        try {
            return new Text(new InputStreamReader(source.openStream()));
        }
        catch (IOException e) {
            throw new IllegalArgumentException("Cannot create Text", e);
        }
    }
}
