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

package org.langera.freud.core.iterator.resource;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.langera.freud.util.io.IoUtil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileResourceTest
{
    private File testFile;
    private static final String FILE_CONTENT = "test test test\n";

    @Test
    public void shouldReturnResourceAsStream() throws Exception
    {
        FileResource resource = FileResource.getInstance();

        InputStream in = resource.getResource(testFile.getAbsolutePath());

        String data = IoUtil.readFully(new InputStreamReader(in));

        Assert.assertEquals(FILE_CONTENT, data);
    }

    @Test(expected = IOException.class)
    public void shouldNotFindResource() throws Exception
    {
        FileResource resource = FileResource.getInstance();

        resource.getResource("unknown.file");
    }

    @Before
    public void setUp() throws Exception
    {
        testFile = new File(System.getProperty("java.io.tmpdir"), "testfile");
        FileWriter w = new FileWriter(testFile);
        w.write(FILE_CONTENT);
        w.close();
    }

    @After
    public void tearDown()
    {
        testFile.delete();
    }
}
