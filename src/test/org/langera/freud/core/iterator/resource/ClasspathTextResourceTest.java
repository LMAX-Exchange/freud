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

import org.junit.Assert;
import org.junit.Test;
import org.langera.freud.util.io.IoUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ClasspathTextResourceTest 
{
    @Test
    public void shouldReturnResourceAsStream() throws Exception
    {
        ClasspathResource resource = ClasspathResource.getInstance();


        InputStream in = resource.getResource("test.txt");

        String data = IoUtil.readFully(new InputStreamReader(in));        

        Assert.assertEquals(
                "This\n" +
                "is a\n" +
                "Test\n" +
                "File\n", data);
    }
    @Test
    public void shouldNotFindResource() throws Exception
    {
        ClasspathResource resource = ClasspathResource.getInstance();

        try
        {
            resource.getResource("unknown.file");
            Assert.fail("Expected IOException");
        } catch (IOException e)
        {
            Assert.assertEquals("Resource [unknown.file] not found.", e.getMessage());
        }
    }

}
