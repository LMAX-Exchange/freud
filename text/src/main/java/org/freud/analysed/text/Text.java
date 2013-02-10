package org.freud.analysed.text;

import org.freud.core.util.IoUtil;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

public final class Text {

    private Reader text;
    private String textAsString;

    public Text(final Reader text) {
        this.text = text;
    }

    public Text(final String textAsString) {
        this(new StringReader(textAsString));
        this.textAsString = textAsString;
    }

    public String getText() {
        if (textAsString == null) {
            try {
                textAsString = IoUtil.readFully(text);
                text = new StringReader(textAsString);
            }
            catch (IOException e) {
                throw new IllegalStateException("Failed to parse text into a string", e);
            }
        }
        return textAsString;
    }

    Reader getTextAsStream() {
        return text;
    }

    String getTextAsString() {
        return textAsString;
    }
}
