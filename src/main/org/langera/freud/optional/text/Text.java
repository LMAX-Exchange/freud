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

import org.langera.freud.util.io.IoUtil;

import java.io.IOException;
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
    private String text;
    private String resourceIdentifier;

    public Text(String text, String resourceIdentifier)
    {
        this.text = text;
        this.resourceIdentifier = resourceIdentifier;
    }

    public List<org.langera.freud.optional.text.textline.TextLine> getTextLines()
    {
        try
        {
            String[] lines = IoUtil.readLines(new StringReader(text));
            List<org.langera.freud.optional.text.textline.TextLine> textLineList = new ArrayList<org.langera.freud.optional.text.textline.TextLine>(lines.length);
            for (int i = 0, size = lines.length; i < size; i++)
            {
                textLineList.add(new org.langera.freud.optional.text.textline.TextLine(lines[i], i, resourceIdentifier));
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
