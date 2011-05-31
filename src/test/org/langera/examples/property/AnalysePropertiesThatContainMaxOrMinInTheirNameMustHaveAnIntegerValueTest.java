package org.langera.examples.property;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.langera.freud.core.FreudAnalyser;
import org.langera.freud.core.listener.AnalysisListenerStub;
import org.langera.freud.optional.property.Property;
import org.langera.freud.optional.property.PropertyIterator;

import java.util.Properties;

public final class AnalysePropertiesThatContainMaxOrMinInTheirNameMustHaveAnIntegerValueTest
{
    private AnalysisListenerStub listener;
    private Properties properties;

    @Test
    public void shouldAnalyseProperties() throws Exception
    {
        properties.setProperty("shouldPass.min.size", "17");
        properties.setProperty("shouldPass.max.size", "19");
        properties.setProperty("shouldFail.max.size", "NaN");
        properties.setProperty("unrelated", "unrelatedValue");


        FreudAnalyser analyser = PropertyExamples.propertiesThatContainMaxOrMinInTheirNameMustHaveAnIntegerValue(
                new PropertyIterator(properties, false));

        analyser.analyse(listener);

        Assert.assertEquals(4, listener.getTotalObjectsAnalysed());

        listener.assertPassed(new Property("shouldPass.min.size", "17"));
        listener.assertPassed(new Property("shouldPass.max.size", "19"));
        listener.assertFailed(new Property("shouldFail.max.size", "NaN"));
        listener.assertFiltered(new Property("unrelated", "unrelatedValue"));
    }

    @Before
    public void setUp() throws Exception
    {
        listener = new AnalysisListenerStub();

        properties = new Properties();
    }

}
