package org.freud.analysed.text;

import org.freud.core.Creator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public final class TextFromFileCreator implements Creator<File, Text> {

    @Override
    public Text create(final File source) {
        try {
            return new Text(new FileReader(source));
        }
        catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Cannot create Text", e);
        }
    }
}
