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
import org.langera.freud.optional.javasource.JavaSourceTestMatchers;

public class AnalyseSuprressingAnalysisTest
{
    private AnalysisListenerStub listener;

    @Test
    public void shouldFailAnalysis() throws Exception
    {
        FreudAnalyser analysis = JavaSourceExamples.codeBlockSizeIsLimitedToOneLineIfFreudNotSuppressed(
                ResourceIterators.selfResourceIterator(JavaSourceJdom.PARSER,
                        "package org.langera.examples;\n" +
                                " \n" +
                                "public class SimpleClass \n" +
                                "{ \n" +
                                " \n" +
                                "  public String toFail()\n" +
                                "  {\n" +
                                "       return \"\" \n" +
                                "              + getClass();\n" +
                                "  }\n" +
                                " \n" +
                                "}"));

        analysis.analyse(listener);

        Assert.assertEquals(1, listener.getTotalObjectsAnalysed());
        listener.assertFailed(JavaSourceTestMatchers.codeBlockIn("SimpleClass"));
    }


    @Test
    public void shouldSuppressAnalysis() throws Exception
    {
        FreudAnalyser analysis = JavaSourceExamples.codeBlockSizeIsLimitedToOneLineIfFreudNotSuppressed(
                ResourceIterators.selfResourceIterator(JavaSourceJdom.PARSER,
                        "package org.langera.examples;\n" +
                                " \n" +
                                "public class SimpleClass \n" +
                                "{ \n" +
                                " \n" +
                                "  @SuppressWarnings(\"freud:CodeBlockLines() <= 1\")\n" +
                                "  public String toBeSuppressed()\n" +
                                "  {\n" +
                                "       int i = 1;\n" +
                                "       int i = 2;\n" +
                                "       int i = 3;\n" +
                                "       return \"\" + getClass();\n" +
                                "  }\n" +
                                " \n" +
                                "}"));

        analysis.analyse(listener);

        Assert.assertEquals(1, listener.getTotalObjectsAnalysed());
        listener.assertFiltered(JavaSourceTestMatchers.codeBlockIn("SimpleClass"));
    }

    @Before
    public void setUp() throws Exception
    {
        listener = new AnalysisListenerStub();

    }

}
