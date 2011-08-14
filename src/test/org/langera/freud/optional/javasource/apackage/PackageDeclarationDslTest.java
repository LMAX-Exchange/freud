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

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.langera.freud.optional.javasource.JavaSourceJdom;

import java.io.StringReader;

import static org.langera.freud.core.matcher.FreudDsl.no;
import static org.langera.freud.optional.javasource.apackage.PackageDeclarationDsl.packageDeclaration;
import static org.langera.freud.optional.javasource.apackage.PackageDeclarationDsl.packageDepth;

public final class PackageDeclarationDslTest
{

    private static final String CLASS_EXAMPLE =
            "package org.langera.examples;" +
                    " " +
                    "public class SimpleClass " +
                    "{ " +
                    " " +
                    "  public String toString()" +
                    "  {" +
                    "       return \"\";" +
                    "  }" +
                    "}";

    private static final String NO_PACKAGE_CLASS_EXAMPLE =
            "public class NoPackageSimpleClass " +
                    "{ " +
                    " " +
                    "  public String toString()" +
                    "  {" +
                    "       return \"\";" +
                    "  }" +
                    "}";
    private PackageDeclaration packageDeclaration;
    private PackageDeclaration noPackagePackageDeclaration;

    @Test
    public void shouldReturnTrueToAMatchedRegex()
    {
        Assert.assertThat(packageDeclaration, packageDeclaration().matches("org.+"));
    }

    @Test
    public void shouldReturnFalseToANonMatchedRegex()
    {
        Assert.assertThat(packageDeclaration, no(packageDeclaration().matches("com.+")));
    }

    @Test
    public void shouldReturnTrueToAContainsRegex()
    {
        Assert.assertThat(packageDeclaration, packageDeclaration().contains("org"));
    }

    @Test
    public void shouldReturnFalseToANonContainsRegex()
    {
        Assert.assertThat(packageDeclaration, no(packageDeclaration().contains("com")));
    }


    @Test
    public void shouldMatchEmptyStringWhenNoPackageExists()
    {
        Assert.assertThat(noPackagePackageDeclaration, packageDeclaration().matches(""));
    }


    @Test
    public void shouldReturnDepthZeroWhenNoPackageExists()
    {
        Assert.assertThat(noPackagePackageDeclaration, packageDepth().equalTo(0));
    }

    @Test
    public void shouldReturnDepthForPackage()
    {
        Assert.assertThat(packageDeclaration, packageDepth().equalTo(3));
    }

    @Before
    public void setUp() throws Exception
    {
        packageDeclaration = new JavaSourceJdom(new StringReader(CLASS_EXAMPLE), "Name").getPackageDeclaration();
        noPackagePackageDeclaration = new JavaSourceJdom(new StringReader(NO_PACKAGE_CLASS_EXAMPLE), "Name").
                getPackageDeclaration();

    }
}
