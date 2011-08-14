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

package org.langera.freud.optional.classobject;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.langera.freud.core.iterator.resource.ResourceParserException;

import java.io.IOException;

public final class LoadClassByPathResourceParserTest
{
    private LoadClassByPathResourceParser parserOfSrcPath;
    private LoadClassByPathResourceParser parserOfClassPath;

    @Test
    public void shouldLoadClassFromSrcPath() throws IOException, ResourceParserException
    {
        Class loadedClass = parserOfSrcPath.parseResource("src/test/org/langera/freud/optional/classobject/LoadClassByPathResourceParserTest.java", null);
        Assert.assertEquals(LoadClassByPathResourceParserTest.class, loadedClass);
    }

   @Test
    public void shouldLoadClassFromClassPath() throws IOException, ResourceParserException
    {
        Class loadedClass = parserOfClassPath.parseResource("out/test/analysis/org/langera/freud/optional/classobject/LoadClassByPathResourceParserTest.class", null);
        Assert.assertEquals(LoadClassByPathResourceParserTest.class, loadedClass);
    }
    
    @Before
    public void setUp() throws Exception
    {
        parserOfSrcPath = new LoadClassByPathResourceParser("src/test");
        parserOfClassPath = new LoadClassByPathResourceParser("out/test/analysis");
    }
}
