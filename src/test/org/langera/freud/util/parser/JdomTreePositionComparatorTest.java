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
