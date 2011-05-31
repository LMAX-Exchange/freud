package org.langera.freud.core.iterator.resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.langera.freud.optional.text.Text;
import org.langera.freud.optional.text.TextResourceParser;

import java.io.File;
import java.io.FilenameFilter;

public class ResourceDirectoryIteratorTest
{
    private File tmpDir;
    private ResourceDirectoryIterator<Text> iterator;
    private File file2;
    private File file1;


    @Test
    public void shouldIterateOverFilesInDirectories() throws Exception
    {
        Assert.assertTrue(iterator.hasNext());
        Text text1 = iterator.next();

        Assert.assertEquals(file1.getAbsolutePath(), text1.getResourceIdentifier());

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

        iterator = new ResourceDirectoryIterator<Text>(TextResourceParser.getInstance(),
                true, new FilenameFilter()
                {
                    public boolean accept(File dir, String name)
                    {
                        return name.equals(tempFilename);
                    }
                }, true, System.getProperty("java.io.tmpdir"));

    }
}
