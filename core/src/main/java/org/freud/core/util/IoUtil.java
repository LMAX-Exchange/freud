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
