package org.langera.examples.properties;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.langera.freud.Analysis;
import org.langera.freud.TestAnalysisListenerStub;
import org.langera.freud.property.PropertyIterator;

import java.util.Properties;

import static org.langera.freud.property.PropertyMatchers.propertyKey;

public final class AnalysePropertiesThatContainMaxOrMinInTheirNameMustHaveAnIntegerValueTest
{
    private TestAnalysisListenerStub listener;
    private Properties properties;

    @Test
    public void testShouldAnalyseProperties() throws Exception
    {
        properties.setProperty("shouldPass.min.size", "17");
        properties.setProperty("shouldPass.max.size", "19");
        properties.setProperty("shouldFail.max.size", "NaN");
        properties.setProperty("unrelated", "unrelatedValue");


        Analysis analysis = PropertiesExamples.propertiesThatContainMaxOrMinInTheirNameMustHaveAnIntegerValue(
                new PropertyIterator(properties, false));

        analysis.analyse(listener);

        Assert.assertEquals(4, listener.getTotalObjectsAnalysed());

        listener.assertPassed(propertyKey("shouldPass.min.size"));
        listener.assertPassed(propertyKey("shouldPass.max.size"));
        listener.assertFailed(propertyKey("shouldFail.max.size"));
        listener.assertFiltered(propertyKey("unrelated"));
    }

    @Before
    public void setUp() throws Exception
    {
        listener = new TestAnalysisListenerStub();

        properties = new Properties();
    }
}
