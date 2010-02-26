package org.langera.freud.util.resource;

import org.langera.freud.util.collection.AnalysedObjectIterator;
import org.langera.freud.util.collection.GenericIterableAnalysedObjectIterator;

import java.io.FilenameFilter;
import java.util.Arrays;

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

public final class AnalysisResource
{
    private AnalysisResource()
    {
    }

    public static <T> AnalysedObjectIterator<T> filesByPathResourceIterator(
            ResourceParser<T> parser, FilenameFilter filenameFilter,
            boolean recursive, String... filenames)
    {
        return new ResourceDirectoryIterator<T>(parser, true, filenameFilter, recursive, filenames);
    }

    public static <T> AnalysedObjectIterator<T> fileResourceIterator(ResourceParser<T> parser, String... filepaths)
    {
        return new ResourceByIdentifierIterator<T>(parser, false, FileResource.getInstance(), filepaths);
    }

    public static <T> AnalysedObjectIterator<T> selfResourceIterator(ResourceParser<T> parser, String... contents)
    {
        return new ResourceByIdentifierIterator<T>(parser, false, SelfResource.getInstance(), contents);
    }

    public static <T> AnalysedObjectIterator<T> selfResourceIterator(T... contents)
    {
        return new GenericIterableAnalysedObjectIterator<T>(Arrays.asList(contents), false);
    }


    public static <T> AnalysedObjectIterator<T> classpathResourceIterator(ResourceParser<T> parser, String... names)
    {
        return new ResourceByIdentifierIterator<T>(parser, false, ClasspathResource.getInstance(), names);
    }
}
