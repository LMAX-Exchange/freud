package org.langera.freud.core.iterator.resource;

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

@RunWith(JMock.class)
public class ResourceDirectoryIteratorTest
{
    private final Mockery mockery = new JUnit4Mockery();

    private File tmpDir;
    private ResourceDirectoryIterator<Text> iterator;
    private File file2;
    private File file1;
    private ResourceParser parser;
    private AnalysisListener listener;


    @Test
    public void shouldIterateOverFilesInDirectories() throws Exception
    {
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
        Assert.assertTrue(iterator.hasNext());
        Text text1 = iterator.next();

        Assert.assertEquals(file1.getAbsolutePath(), text1.getResourceIdentifier());

        Assert.assertTrue(iterator.hasNext());
        Text text2 = iterator.next();

        Assert.assertEquals(file2.getAbsolutePath(), text2.getResourceIdentifier());

        Assert.assertFalse(iterator.hasNext());
    }

    @Test
    public void shouldIterateOverFilesEvenIfParserBlowsUp() throws Exception
    {
        mockery.checking(new Expectations()
        {
            {
                one(listener).unexpected(with(aNull(Object.class)), with(any(RuntimeException.class)));

                one(parser).parseResource(with(file1.getAbsolutePath()), with((any(Resource.class))));
                will(throwException(new ResourceParserException("boom", null)));
                one(parser).parseResource(with(file2.getAbsolutePath()), with((any(Resource.class))));
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

        Assert.assertTrue(iterator.hasNext());
        Text text2 = iterator.next();

        Assert.assertEquals(file2.getAbsolutePath(), text2.getResourceIdentifier());

        Assert.assertFalse(iterator.hasNext());
    }

    @Before
    public void setUp() throws Exception
    {
        tmpDir = new File(System.getProperty("java.io.tmpdir"));

        File dir = new File(tmpDir, "test " + System.currentTimeMillis());
        dir.mkdir();
        dir.deleteOnExit();

        final String tempFilename = "file" + System.currentTimeMillis() + ".tmp";

        file1 = new File(tmpDir, tempFilename);
        file1.createNewFile();
        file1.deleteOnExit();
        file2 = new File(dir, tempFilename);
        file2.createNewFile();
        file2.deleteOnExit();

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
                return name.equals(tempFilename);
            }
        }, true, System.getProperty("java.io.tmpdir"));
        listener = mockery.mock(AnalysisListener.class);
        iterator.setListener(listener);

    }
}
