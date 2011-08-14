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

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class IoUtilTest 
{


    @Test
    public void shouldParseStreamToLines() throws Exception
    {
        Reader reader = new InputStreamReader(new ByteArrayInputStream("line1 \nline2\t\nline3\n\n".getBytes()));

        String[] lines = IoUtil.readLines(reader);

        Assert.assertEquals(4, lines.length);
        Assert.assertEquals("line1 ", lines[0]);
        Assert.assertEquals("line2\t", lines[1]);
        Assert.assertEquals("line3", lines[2]);
        Assert.assertEquals("", lines[3]);
    }

    @Test
    public void shouldReadStreamFully() throws Exception
    {
        Reader reader = new InputStreamReader(new ByteArrayInputStream("line1 \nline2\t\nline3\n\nline4".getBytes()));

        String text = IoUtil.readFully(reader);

        Assert.assertEquals("line1 \nline2\t\nline3\n\nline4", text);
    }

}
