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

package org.langera.freud.optional.javasource.apackage;

import org.jdom.Element;
import org.jdom.filter.ElementFilter;
import org.langera.freud.optional.javasource.parser.JavaSourceTokenType;
import org.langera.freud.util.parser.JdomTreePositionComparator;

import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

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

public final class PackageDeclarationJdom implements PackageDeclaration
{
    private String[] packagePath;
    private String packagePathAsStr;

    public PackageDeclarationJdom()
    {
        packagePath = new String[]{};
    }

    public PackageDeclarationJdom(Element element)
    {
        SortedSet<Element> packagePathElementSortedSet = new TreeSet<Element>(JdomTreePositionComparator.getInstance());

        for (Iterator iterator = element.getDescendants(new ElementFilter(JavaSourceTokenType.IDENT.getName()));
             iterator.hasNext(); )
        {
            packagePathElementSortedSet.add((Element) iterator.next());

        }
        packagePath = new String[packagePathElementSortedSet.size()];
        int i = 0;
        for (Element pathElement : packagePathElementSortedSet)
        {
            packagePath[i++] = pathElement.getTextTrim();
        }
    }

    public String[] getPackagePath()
    {
        return packagePath;
    }

    public String getPackagePathAsString()
    {
        if (packagePathAsStr == null)
        {
            StringBuilder packagePathStrBuilder = new StringBuilder();
            for (int i = 0, size = packagePath.length; i < size; i++)
            {
                if (i > 0)
                {
                    packagePathStrBuilder.append('.');
                }
                packagePathStrBuilder.append(packagePath[i]);
            }
            packagePathAsStr = packagePathStrBuilder.toString();
        }
        return packagePathAsStr;
    }
}
