package org.langera.freud.optional.text;

import org.langera.freud.core.iterator.resource.ResourceParserException;
import org.langera.freud.util.io.IoUtil;
import org.langera.freud.core.iterator.resource.Resource;
import org.langera.freud.core.iterator.resource.ResourceParser;

import java.io.IOException;
import java.io.InputStreamReader;

public final class TextResourceParser implements ResourceParser<Text>
{
    private static final TextResourceParser SINGLETON = new TextResourceParser();

    private TextResourceParser()
    {
        // singleton
    }

    public static TextResourceParser getInstance()
    {
        return SINGLETON;
    }

    @Override
    public Class<Text> getType()
    {
        return Text.class;
    }

    @Override
    public Text parseResource(final String resourceIdentifier, final Resource resource) throws IOException, ResourceParserException
    {
        return new Text(IoUtil.readFully(new InputStreamReader(resource.getResource(resourceIdentifier))),
                                    resourceIdentifier);
    }
}
