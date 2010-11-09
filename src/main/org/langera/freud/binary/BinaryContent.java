package org.langera.freud.binary;

import org.langera.freud.util.resource.Resource;

import java.io.IOException;
import java.io.InputStream;

public final class BinaryContent
{
    private final Resource resource;
    private final String resourceIdentifier;

    public BinaryContent(Resource resource, String resourceIdentifier)
    {
        this.resource = resource;
        this.resourceIdentifier = resourceIdentifier;
    }

    public InputStream getInputStream() throws IOException
    {
        return resource.getResource(resourceIdentifier);
    }

    @Override
    public String toString()
    {
        return "BinaryContent: [" + resourceIdentifier + "]";
    }


    public String getResourceIdentifier()
    {
        return resourceIdentifier;
    }
}
