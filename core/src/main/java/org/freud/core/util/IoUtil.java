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

package org.freud.core.util;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.ArrayList;

public final class IoUtil {
    private static final int BUFFER_SIZE = 1024;

    private IoUtil() {
        // static utility
    }

    public static String readFully(Reader reader)
            throws IOException {
        final StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader = (BufferedReader) ((reader instanceof BufferedReader) ?
                                                          reader : new BufferedReader(reader));
        try {
            char[] chars = new char[BUFFER_SIZE];
            int charsRead;
            while ((charsRead = bufferedReader.read(chars)) >= 0) {
                sb.append(chars, 0, charsRead);
            }
        }
        finally {
            safeClose(bufferedReader);
        }

        return sb.toString();
    }

    public static String[] readLines(Reader reader)
            throws IOException {
        final ArrayList<String> lines = new ArrayList<String>();
        LineNumberReader lnr = new LineNumberReader(reader);
        try {
            String line;
            while ((line = lnr.readLine()) != null) {
                lines.add(line);
            }
        }
        finally {
            safeClose(lnr);
        }

        return lines.toArray(new String[lines.size()]);
    }

    public static void safeClose(Closeable closeable) {
        try {
            closeable.close();
        }
        catch (final IOException e) {
            // ignore
        }
    }
}
