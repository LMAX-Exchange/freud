package org.langera.freud.binary.parser;

import org.langera.freud.binary.BinaryContent;
import org.langera.freud.util.resource.Resource;
import org.langera.freud.util.resource.ResourceParser;
import org.langera.freud.util.resource.ResourceParserException;

import java.io.IOException;

public final class BinaryContentParser implements ResourceParser<BinaryContent>
{
    public static final BinaryContentParser SINGLETON = new BinaryContentParser();

    public static final BinaryContentParser getInstance()
    {
        return SINGLETON;
    }

    private BinaryContentParser()
    {
    }

    public BinaryContent parseResource(String resourceIdentifier, Resource resource) throws IOException, ResourceParserException
    {
        return new BinaryContent(resource, resourceIdentifier);
    }
}
