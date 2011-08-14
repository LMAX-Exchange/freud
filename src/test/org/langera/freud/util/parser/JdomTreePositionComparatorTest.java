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

import org.jdom.Element;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public final class JdomTreePositionComparatorTest
{
    private JdomTreePositionComparator comparator;
    private Element element1;
    private Element element2;
    private Element element3;

    @Test
    public void shouldCompareElementsInDifferentLines()
    {
        Assert.assertEquals(-1, comparator.compare(element2, element3));
        Assert.assertEquals(1, comparator.compare(element3, element2));
    }

    @Test
    public void shouldCompareElementsInDifferentColumns()
    {
        Assert.assertEquals(-1, comparator.compare(element2, element3));
        Assert.assertEquals(1, comparator.compare(element3, element2));
    }

    @Test
    public void shouldCompareIdenticalElements()
    {
        Assert.assertEquals(0, comparator.compare(element1, element1));
    }

    @Before
    public void setUp()
    {
        comparator = JdomTreePositionComparator.getInstance();

        element1 = new Element("Name").
                setAttribute(JdomTreeAdaptor.LINE_NUMBER_ATTR, "17").
                setAttribute(JdomTreeAdaptor.COL_NUMBER_ATTR, "19");
        element2 = new Element("Name").
                setAttribute(JdomTreeAdaptor.LINE_NUMBER_ATTR, "19").
                setAttribute(JdomTreeAdaptor.COL_NUMBER_ATTR, "17");
        element3 = new Element("Name").
                setAttribute(JdomTreeAdaptor.LINE_NUMBER_ATTR, "19").
                setAttribute(JdomTreeAdaptor.COL_NUMBER_ATTR, "23");
    }
}
