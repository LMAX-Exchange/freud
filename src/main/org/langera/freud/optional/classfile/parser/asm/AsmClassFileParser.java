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

package org.langera.freud.optional.classfile.parser.asm;

import org.langera.freud.core.FreudBuilderException;
import org.langera.freud.core.iterator.resource.Resource;
import org.langera.freud.core.iterator.resource.ResourceParser;
import org.langera.freud.core.iterator.resource.ResourceParserException;
import org.langera.freud.optional.classfile.ClassFile;
import org.langera.freud.optional.classfile.parser.InnerClassFileResourceIdentifierGetter;
import org.langera.freud.util.io.IoUtil;
import org.objectweb.asm.ClassReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;

public final class AsmClassFileParser implements ResourceParser<ClassFile>, AsmClassFileGetter
{
    private static final int CACHE_SIZE = 5;
    private final LinkedHashMap<String, AsmClassFile> classByNameCache;
    private final InnerClassFileResourceIdentifierGetter innerClassFileResourceIdentifierGetter;
    private Resource currentResource;
    private String currentResourceIdentifier;

    public AsmClassFileParser(final InnerClassFileResourceIdentifierGetter innerClassFileResourceIdentifierGetter)
    {
        this.innerClassFileResourceIdentifierGetter = innerClassFileResourceIdentifierGetter;
        classByNameCache = new LinkedHashMap<String, AsmClassFile>();
    }

    @Override
    public Class<ClassFile> getType()
    {
        return ClassFile.class;
    }

    @Override
    public ClassFile parseResource(final String resourceIdentifier, final Resource resource) throws IOException, ResourceParserException
    {
        currentResource = resource;
        currentResourceIdentifier = resourceIdentifier;
        return parseClassFileInternal(resourceIdentifier, resource);
    }

    @Override
    public AsmClassFile getClassFile(final String name, final AsmClassFile currentClassFile)
    {
        AsmClassFile asmClassFile;
        try
        {
            asmClassFile = classByNameCache.get(name);
            if (asmClassFile == null)
            {
                asmClassFile = parseClassFileInternal(
                        innerClassFileResourceIdentifierGetter.getResourceIdentifier(name, currentClassFile, currentResourceIdentifier),
                        currentResource);
            }
        }
        catch (IOException e)
        {
            throw new FreudBuilderException("Failed to find class file " + name, e);
        }
        return asmClassFile;
    }

    private AsmClassFile parseClassFileInternal(final String resourceIdentifier, final Resource resource) throws IOException
    {
        final InputStream inputStream = resource.getResource(resourceIdentifier);
        try
        {
            final AsmClassFileFactory visitor = new AsmClassFileFactory(this);
            final ClassReader reader = new ClassReader(inputStream);
            visitor.clear();

//System.out.println("**********************************************************************");
//System.out.println(resourceIdentifier);
//System.out.println("**********************************************************************");

            reader.accept(visitor, 0);
            return addToCacheAndReturn(visitor.getClassFile());
        }
        finally
        {
            IoUtil.safeClose(inputStream);
        }
    }

    private AsmClassFile addToCacheAndReturn(final AsmClassFile classFile)
    {
        classByNameCache.put(classFile.getName(), classFile);
        if (classByNameCache.size() > CACHE_SIZE)
        {
            classByNameCache.remove(classByNameCache.values().iterator().next());
        }
        return classFile;
    }
}
