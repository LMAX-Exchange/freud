package org.langera.freud.util.resource;

import org.langera.freud.util.collection.AbstractAnalysedObjectIterator;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Deque;
import java.util.LinkedList;

/**
 *   This file is part of "Freud".
 *
 *   Freud is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU Lesser General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   Freud is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with Freud.  If not, see <http://www.gnu.org/licenses/>.
 *
 *   @author Amir Langer  langera_at_gmail_dot_com
**/

public final class ResourceDirectoryIterator<T> extends AbstractAnalysedObjectIterator<T>
{
    private final ResourceParser<T> resourceParser;
    private final Deque<String> resourcePathList;
    private final FilenameFilter filenameFilter;
    private final boolean recursive;
    private transient int filesPtr = 0;
    private transient File[] files;

    public ResourceDirectoryIterator(final ResourceParser<T> resourceParser,
                                     final FilenameFilter filenameFilter,
                                     final boolean recursive,
                                     final String... resourceIdentfiers)
    {
            this(resourceParser, true, filenameFilter, recursive, resourceIdentfiers);
    }

    public ResourceDirectoryIterator(final ResourceParser<T> resourceParser,
                                     final boolean alertOnEmptyIterator, 
                                     final FilenameFilter filenameFilter,
                                     final boolean recursive,
                                     final String... resourceIdentfiers)
    {
        super(alertOnEmptyIterator);        
        this.filenameFilter = filenameFilter;
        this.resourceParser = resourceParser;
        this.recursive = recursive;
        this.resourcePathList = new LinkedList<String>();
        for (int i = 0, size = resourceIdentfiers.length; i < size; i++)
        {
            resourcePathList.add(resourceIdentfiers[i]);
        }
    }

    @SuppressWarnings({"ThrowableInstanceNeverThrown"})
    @Override
    protected T generateNextItem()
    {
        if (files != null && filesPtr < files.length)
        {
            File file = files[filesPtr++];
            if (file.isDirectory())
            {
                if (recursive)
                {
                    resourcePathList.addFirst(file.getAbsolutePath());
                }
            }
            else
            {
                if (filenameFilter == null ||
                        filenameFilter.accept(file.getParentFile(), file.getName()))
                {
                    try
                    {
                        T resource = resourceParser.parseResource(file.getAbsolutePath(), FileResource.getInstance());
                        return (resource == null) ? generateNextItem() : resource;
                    }
                    catch (IOException e)
                    {
                        getListener().unexpected(null, new IllegalArgumentException("Failed to retrieve resource [" + file.getAbsolutePath() + "]", e));
                    }
                    catch (ResourceParserException e)
                    {
                        getListener().unexpected(null, new IllegalArgumentException("Failed to parse resource [" + file.getAbsolutePath() + "]", e));
                    }
                }
            }
        }
        else
        {
            if (!resourcePathList.isEmpty())
            {
                final File resourceDir = new File(resourcePathList.removeFirst());
                if (resourceDir.isDirectory())
                {
                    files = resourceDir.listFiles();
                    filesPtr = 0;
                }
                return generateNextItem();
            }
            else
            {
                return null;
            }
        }
        return generateNextItem();
    }
}
