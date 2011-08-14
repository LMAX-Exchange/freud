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

package org.langera.freud.util.parser;

import org.jdom.DataConversionException;
import org.jdom.Element;

import java.util.Comparator;

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

public final class JdomTreePositionComparator implements Comparator<Element>
{
    private static final JdomTreePositionComparator SINGLETON = new JdomTreePositionComparator();

    public static JdomTreePositionComparator getInstance()
    {
        return SINGLETON;
    }

    private JdomTreePositionComparator()
    {
    }

    public int compare(Element element1, Element element2)
    {
        try
        {
            int lineNumber1 = element1.getAttribute(JdomTreeAdaptor.LINE_NUMBER_ATTR).getIntValue();
            int lineNumber2 = element2.getAttribute(JdomTreeAdaptor.LINE_NUMBER_ATTR).getIntValue();
            if (lineNumber1 == lineNumber2)
            {
                int colNumber1 = element1.getAttribute(JdomTreeAdaptor.COL_NUMBER_ATTR).getIntValue();
                int colNumber2 = element2.getAttribute(JdomTreeAdaptor.COL_NUMBER_ATTR).getIntValue();
                return (colNumber1 < colNumber2) ? -1 : (colNumber1 == colNumber2) ? 0 : 1;
            }
            return (lineNumber1 < lineNumber2) ? -1 : 1;
        }
        catch (DataConversionException e)
        {
            throw new IllegalStateException("unexpected", e);
        }
    }
}
