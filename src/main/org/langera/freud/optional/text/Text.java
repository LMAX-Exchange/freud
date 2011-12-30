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

package org.langera.freud.optional.text;

import org.langera.freud.optional.text.textline.TextLine;
import org.langera.freud.util.io.IoUtil;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

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

public final class Text
{
    private Reader text;
    private final String resourceIdentifier;
    private String textAsString;
    private List<TextLine> textLineList;

    public Text(final Reader text, final String resourceIdentifier)
    {
        this.text = text;
        this.resourceIdentifier = resourceIdentifier;
    }

    public Text(final String textAsString, final String resourceIdentifier)
    {
        this(new StringReader(textAsString), resourceIdentifier);
        this.textAsString = textAsString;
    }


    public List<TextLine> getTextLines()
    {
        if (textLineList == null)
        {
            try
            {
                String[] lines = IoUtil.readLines(new StringReader(getText()));
                textLineList = new ArrayList<TextLine>(lines.length);
                for (int i = 0, size = lines.length; i < size; i++)
                {
                    textLineList.add(new TextLine(lines[i], i, resourceIdentifier));
                }
                text = new StringReader(textAsString);
            }
            catch (IOException e)
            {
                throw new IllegalStateException("Failed to parse text [" + resourceIdentifier + "] into lines");
            }
        }
        return textLineList;
    }

    public String getResourceIdentifier()
    {
        return resourceIdentifier;
    }

    public String getText()
    {
        if (textAsString == null)
        {
            try
            {
                textAsString = IoUtil.readFully(text);
                text = new StringReader(textAsString);
            }
            catch (IOException e)
            {
                throw new IllegalStateException("Failed to parse text [" + resourceIdentifier + "] into a string");
            }
        }
        return textAsString;
    }

    @Override
    public String toString()
    {
        return "Text[" + resourceIdentifier + "]:" + text;
    }
}
