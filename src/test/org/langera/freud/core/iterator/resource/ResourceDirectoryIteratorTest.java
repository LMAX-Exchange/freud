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

package org.langera.freud.core.iterator.resource;

import org.hamcrest.Matchers;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.api.Invocation;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.action.CustomAction;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.langera.freud.core.listener.AnalysisListener;
import org.langera.freud.optional.text.Text;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RunWith(JMock.class)
public class ResourceDirectoryIteratorTest
{
    private static final String FILE_PREFIX = "Freud.ResourceDirectoryIteratorTest_";
    private final Mockery mockery = new JUnit4Mockery();

    private File tmpDir;
    private ResourceDirectoryIterator<Text> iterator;
    private File file3;
    private File file2;
    private File file1;
    private File file0;
    private ResourceParser parser;
    private AnalysisListener listener;


    @Test
    public void shouldIterateOverFilesInDirectories() throws Exception
    {
        List<String> resourcesIterated = new ArrayList<String>();
        mockery.checking(new Expectations()
        {
            {
                allowing(parser).parseResource(with(any(String.class)), with((any(Resource.class))));
                will(new CustomAction("fake parsing")
                {
                    @Override
                    public Object invoke(final Invocation invocation) throws Throwable
                    {
                        return new Text("", (String) invocation.getParameter(0));
                    }
                });
            }
        });


        while (iterator.hasNext())
        {
            resourcesIterated.add(iterator.next().getResourceIdentifier());
        }


        Assert.assertEquals(4, resourcesIterated.size());
        Assert.assertThat(resourcesIterated, Matchers.hasItems(file0.getAbsolutePath(),
                                                               file1.getAbsolutePath(),
                                                               file2.getAbsolutePath(),
                                                               file3.getAbsolutePath()));
    }

    @Test
    public void shouldIterateOverFilesEvenIfParserBlowsUp() throws Exception
    {
        List<String> resourcesIterated = new ArrayList<String>();

        expectParsing(file1);
        expectParsing(file2);
        expectParsing(file3);

        mockery.checking(new Expectations()
        {
            {
                one(listener).unexpected(with(aNull(Object.class)), with(any(RuntimeException.class)));

                one(parser).parseResource(with(file0.getAbsolutePath()), with((any(Resource.class))));
                will(throwException(new ResourceParserException("boom", null)));

            }
        });


        while (iterator.hasNext())
        {
            resourcesIterated.add(iterator.next().getResourceIdentifier());
        }


        Assert.assertEquals(3, resourcesIterated.size());
        Assert.assertThat(resourcesIterated, Matchers.hasItems(file1.getAbsolutePath(),
                                                               file2.getAbsolutePath(),
                                                               file3.getAbsolutePath()));
    }

    @Before
    public void setUp() throws Exception
    {
        tmpDir = new File(System.getProperty("java.io.tmpdir"));

        clean();

        File dir = new File(tmpDir, FILE_PREFIX + System.currentTimeMillis());
        dir.mkdir();
        dir.deleteOnExit();

        final String tempFilename = FILE_PREFIX + System.currentTimeMillis() + ".tmp";

        file0 = new File(tmpDir, tempFilename);
        file0.createNewFile();
        file0.deleteOnExit();
        file1 = new File(dir, tempFilename + ".1");
        file1.createNewFile();
        file1.deleteOnExit();
        file2 = new File(dir, tempFilename + ".2");
        file2.createNewFile();
        file2.deleteOnExit();
        file3 = new File(dir, tempFilename + ".3");
        file3.createNewFile();
        file3.deleteOnExit();

        parser = mockery.mock(ResourceParser.class);

        mockery.checking(new Expectations()
        {
            {
                allowing(parser).getType();
                will(returnValue(Text.class));
            }
        });
        iterator = new ResourceDirectoryIterator<Text>(parser,
                                                       true, new FilenameFilter()
        {
            public boolean accept(File dir, String name)
            {
                return name.startsWith(tempFilename);
            }
        }, true, System.getProperty("java.io.tmpdir"));
        listener = mockery.mock(AnalysisListener.class);
        iterator.setListener(listener);

    }

    private void expectParsing(final File file) throws IOException, ResourceParserException
    {
        mockery.checking(new Expectations()
        {
            {
                one(parser).parseResource(with(file.getAbsolutePath()), with((any(Resource.class))));
                will(new CustomAction("fake parsing")
                {
                    @Override
                    public Object invoke(final Invocation invocation) throws Throwable
                    {
                        return new Text("", (String) invocation.getParameter(0));
                    }
                });
            }
        });
    }

    private void clean()
    {
        File[] testFiles = tmpDir.listFiles(new FilenameFilter()
        {
            @Override
            public boolean accept(final File dir, final String name)
            {
                return name.startsWith(FILE_PREFIX);
            }
        });

        for (int i = 0, size = testFiles.length; i < size; i++)
        {
            File file = testFiles[i];
            if (file.isDirectory())
            {
                File[] files = file.listFiles();
                for (int j = 0, size2 = files.length; j < size2; j++)
                {
                    files[j].delete();
                }
            }
            file.delete();
        }
    }
}
