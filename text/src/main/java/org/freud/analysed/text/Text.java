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
