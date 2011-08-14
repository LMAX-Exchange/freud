/*
 * Copyright (c) 2011.
 * This file is part of "Freud".
 *
 * Freud is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Freud is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Freud.  If not, see <http://www.gnu.org/licenses/>.
 * @author Amir Langer  langera_at_gmail_dot_com
 */

package org.langera.freud.util.io;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.ArrayList;

/**
 * This file is part of "Freud".
 * <p/>
 * Freud is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p/>
 * Freud is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU General Public License
 * along with Freud.  If not, see <http://www.gnu.org/licenses/>.
 *
 * @author Amir Langer  langera_at_gmail_dot_com
 */

public final class IoUtil
{
    private static final int BUFFER_SIZE = 1024;

    private IoUtil()
    {
        // static utility
    }

    public static String readFully(Reader reader)
            throws IOException
    {
        final StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader = (BufferedReader) ((reader instanceof BufferedReader) ?
                reader : new BufferedReader(reader));
        try
        {
            char[] chars = new char[BUFFER_SIZE];
            int charsRead;
            while ((charsRead = bufferedReader.read(chars)) >= 0)
            {
                sb.append(chars, 0, charsRead);
            }
        }
        finally
        {
            safeClose(bufferedReader);
        }

        return sb.toString();
    }

    public static String[] readLines(Reader reader)
            throws IOException
    {
        final ArrayList<String> lines = new ArrayList<String>();
        LineNumberReader lnr = new LineNumberReader(reader);
        try
        {
            String line;
            while ((line = lnr.readLine()) != null)
            {
                lines.add(line);
            }
        }
        finally
        {
            safeClose(lnr);
        }

        return lines.toArray(new String[lines.size()]);
    }

    public static void safeClose(Closeable closeable)
    {
        try
        {
            closeable.close();
        }
        catch (final IOException e)
        {
            // ignore
        }
    }


}
