package org.langera.examples.instance;

import org.langera.freud.Analysis;
import org.langera.freud.util.collection.AnalysedObjectIterator;
import org.langera.freudgenerated.instance.InstanceAnalysis;

public final class InstanceExamples
{
    private InstanceExamples()
    {
        // a class of static methods - should not be initialised        
    }


    public static Analysis toStringDoNotContainUpperCaseCharacters(final AnalysedObjectIterator<Object> iterator)
    {
        return new InstanceAnalysis(iterator)
        {
            {
                assertThat(no(instance().contains("[A-Z]")));
            }
        };
    }
}
