package org.langera.freud.text;

import org.langera.freud.util.io.IoUtil;
import org.langera.freud.util.resource.Resource;
import org.langera.freud.util.resource.ResourceParser;

import java.io.IOException;
import java.io.InputStreamReader;

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

public final class TextResourceParser implements ResourceParser<Text>
{
    private static final TextResourceParser SINGLETON = new TextResourceParser();

    public static TextResourceParser getInstance()
    {
        return SINGLETON;
    }

    private TextResourceParser()
    {
    }

    public Text parseResource(String resourceIdentifier, Resource resource) throws IOException
    {
        String text = IoUtil.readFully(new InputStreamReader(resource.getResource(resourceIdentifier)));
        return new Text(text, resourceIdentifier, resource.getClass());
    }
}
