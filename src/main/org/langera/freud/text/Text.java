package org.langera.freud.text;

import org.langera.freud.text.line.TextLine;
import org.langera.freud.util.io.IoUtil;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

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

public final class Text
{
    private String text;
    private String resourceIdentifier;
    private Class resourceType;

    public Text(String text, String resourceIdentifier, Class resourceType)
    {
        this.text = text;
        this.resourceIdentifier = resourceIdentifier;
        this.resourceType = resourceType;
    }

    public List<TextLine> getTextLines()
    {
        try
        {
            String[] lines = IoUtil.readLines(new StringReader(text));
            List<TextLine> textLineList = new ArrayList<TextLine>(lines.length);
            for (int i = 0, size = lines.length; i < size; i++)
            {
                textLineList.add(new TextLine(lines[i], i, resourceIdentifier, resourceType));
            }
            return textLineList;
        }
        catch (IOException e)
        {
            throw new IllegalStateException("Failed to parse text [" + text + "] into lines");
        }
    }

    public String getResourceIdentifier()
    {
        return resourceIdentifier;
    }

    public Class getResourceType()
    {
        return resourceType;
    }

    public String getText()
    {
        return text;
    }

    @Override
    public String toString()
    {
        return "Text[" + resourceIdentifier + "]:" + text;
    }
}
