package org.langera.freud.util.parser;

import org.langera.freud.util.resource.Resource;
import org.langera.freud.util.resource.ResourceParser;
import org.langera.freud.util.resource.ResourceParserException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

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

public final class JdomResourceParser<Type> implements ResourceParser<Type>
{
    private final Constructor<? extends Type> jdomImplConstructor;
    private static final Class[] PARAMS = {Reader.class, String.class};

    public JdomResourceParser(final Class<? extends Type> jdomImplClass)
    {
        try
        {
            this.jdomImplConstructor = jdomImplClass.getConstructor(PARAMS);
        }
        catch (NoSuchMethodException e)
        {
            throw new IllegalArgumentException("[" + jdomImplClass + "] unsupported Jdom impl class", e);
        }
    }

    public Type parseResource(String resourceIdentifier, Resource resource) throws IOException, ResourceParserException
    {
        try
        {
            return jdomImplConstructor.newInstance(new InputStreamReader(resource.getResource(resourceIdentifier)), resourceIdentifier);
        }
        catch (InvocationTargetException e)
        {
            throw new ResourceParserException(createErrorMessage(resourceIdentifier), e.getTargetException());
        }
        catch (InstantiationException e)
        {
            throw new ResourceParserException(createErrorMessage(resourceIdentifier), e);
        }
        catch (IllegalAccessException e)
        {
            throw new ResourceParserException(createErrorMessage(resourceIdentifier), e);
        }
    }

    private String createErrorMessage(String resourceIdentifier)
    {
        return "Failed to parse source [" + resourceIdentifier +
                    "] to [" + jdomImplConstructor.getDeclaringClass() + "]";
    }
}
