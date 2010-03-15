package org.langera.freud.instance.assertion;

import org.langera.freud.util.regex.RegexMatchAnalysisAssertionAdapter;

public final class ToStringRegexMatchAnalysisAssertionAdapter implements RegexMatchAnalysisAssertionAdapter<Object>
{
    public String getStringToMatch(Object toBeAnalysed)
    {
        return toBeAnalysed.toString();
    }

    public String assertionDisplayName()
    {
        return "toString";
    }
}
