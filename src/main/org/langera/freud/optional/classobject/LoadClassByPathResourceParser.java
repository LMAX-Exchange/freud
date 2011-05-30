package org.langera.freud.optional.classobject;

import org.langera.freud.core.iterator.resource.Resource;
import org.langera.freud.core.iterator.resource.ResourceParser;
import org.langera.freud.core.iterator.resource.ResourceParserException;

import java.io.IOException;

/**
 *   This file is part of "Freud".
 *
 *   Freud is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU Lesser General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   Freud is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with Freud.  If not, see <http://www.gnu.org/licenses/>.
 *
 *   @author Amir Langer  langera_at_gmail_dot_com
**/

public final class LoadClassByPathResourceParser implements ResourceParser<Class>
{
    private String pathToBaseDir;

    public LoadClassByPathResourceParser(String pathToBaseDir)
    {
        this.pathToBaseDir = pathToBaseDir;
    }

    @Override
    public Class<Class> getType()
    {
        return Class.class;
    }

    public Class parseResource(String resourceIdentifier, Resource resource) throws IOException, ResourceParserException
    {
        if (resourceIdentifier.startsWith(pathToBaseDir))
        {
            int nameEndIndex = resourceIdentifier.length();
            if (resourceIdentifier.endsWith(".class"))
            {
                nameEndIndex -= ".class".length();
            }
            if (resourceIdentifier.endsWith(".java"))
            {
                nameEndIndex -= ".java".length();
            }
            int startNameIndex = pathToBaseDir.length();
            if (resourceIdentifier.charAt(startNameIndex) == '/')
            {
                startNameIndex += 1;
            }
            String classNameFromPath = resourceIdentifier.substring(startNameIndex, nameEndIndex).replace("/", ".");

            try
            {
                    return Class.forName(classNameFromPath);
            }
            catch (Throwable e)
            {
                throw new ResourceParserException("Failed to load java class for resource [" + resourceIdentifier + "]", e);

            }
        } else
        {
            throw new IllegalArgumentException("identifier [" + resourceIdentifier + "] is not in base dir [" + pathToBaseDir + "]");
        }
    }
}
