package org.langera.freud.text.line;

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

public final class TextLine
{
    private final String line;
    private final int lineNumber;
    private final String resource;
    private final Class resourceType;

    public TextLine(String line, int lineNumber, String resource, Class resourceType)
    {
        this.line = line;
        this.lineNumber = lineNumber;
        this.resource = resource;
        this.resourceType = resourceType;
    }

    public String getLine()
    {
        return line;
    }

    public int getLineNumber()
    {
        return lineNumber;
    }

    public String getResource()
    {
        return resource;
    }

    public Class getResourceType()
    {
        return resourceType;
    }

    @Override
    public String toString()
    {
        return "Text[" + resource + "]:line[" + lineNumber + "]:" + line;
    }
}
