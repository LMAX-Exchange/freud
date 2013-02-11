package org.freud.analysed.text;

import org.freud.core.Creator;

public final class TextFromStringCreator implements Creator<String, Text> {

    @Override
    public Text create(final String source) {
        return new Text(source);
    }
}
