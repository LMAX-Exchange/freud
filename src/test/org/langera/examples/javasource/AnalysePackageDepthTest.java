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

package org.langera.examples.javasource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.langera.freud.core.FreudAnalyser;
import org.langera.freud.core.iterator.resource.ResourceIterators;
import org.langera.freud.core.listener.AnalysisListenerStub;
import org.langera.freud.optional.javasource.JavaSourceJdom;

import static org.langera.freud.optional.javasource.JavaSourceTestMatchers.packagePath;

public class AnalysePackageDepthTest
{
    private AnalysisListenerStub listener;

    @Test
    public void shouldFindPackageDepthBetween3And7() throws Exception
    {

        FreudAnalyser analysis = JavaSourceExamples.packageDepthMustBeBetween3And7(
                ResourceIterators.selfResourceIterator(JavaSourceJdom.PARSER,
                        "package org.langera.examples.a;" +
                                " " +
                                "public class SimpleClass " +
                                "{ " +
                                " " +
                                "  public String toString()" +
                                "  {" +
                                "       return \"\" + getClass();" +
                                "  }" +
                                "}"));

        analysis.analyse(listener);
        
        Assert.assertEquals(1, listener.getTotalObjectsAnalysed());
        listener.assertPassed(packagePath("org.langera.examples.a"));

    }


    @Test
    public void shouldNotFindPackageDepthBetween3And7() throws Exception
    {

        FreudAnalyser analysis = JavaSourceExamples.packageDepthMustBeBetween3And7(
                ResourceIterators.selfResourceIterator(JavaSourceJdom.PARSER,
                "package org.langera.examples.a.b.c.d.e;" +
                        " " +
                        "public class SimpleClass " +
                        "{ " +
                        " " +
                        "  public String toString()" +
                        "  {" +
                        "       System.out.println(\"print something\");"+
                        "       return \"\";" +
                        "  }" +
                        "}"));

        analysis.analyse(listener);


        Assert.assertEquals(1, listener.getTotalObjectsAnalysed());
        listener.assertFailed(packagePath("org.langera.examples.a.b.c.d.e"));

    }

    @Before
    public void setUp() throws Exception
    {
        listener = new AnalysisListenerStub();
    }
}