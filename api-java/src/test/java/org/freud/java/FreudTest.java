package org.freud.java;

import org.freud.core.Creator;
import org.freud.core.Filter;
import org.freud.core.iterator.AnalysedObjects;
import org.freud.core.iterator.FilteredAnalysedObjects;
import org.freud.core.iterator.SubTypeAnalysedObjects;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public final class FreudTest {

    private final Object analysed;

    public FreudTest(Object analysed) {
        this.analysed = analysed;
    }

    @Test
    public void usageExampleWithJunitDataDrivenTest() {
        assertTrue(Freud.analyse(analysed, CoreMatchers.startsWith("Z")));
    }

    @Parameterized.Parameters
    public static Collection<Object[]> generateData() {
        Collection<String> source = Arrays.asList("1", "2", "3");
        Iterable<String> data = new AnalysedObjects<String, String>(new ZCreator(), new SubTypeAnalysedObjects<String, String>(new XYCreator(),
            new FilteredAnalysedObjects<String>(
                new AnalysedObjects<String, String>(new UnderscoreCreator(), source), new ThreeFilter()))
        );
        Collection<Object[]> parametersData = new ArrayList<Object[]>();
        for (String element : data) {
            parametersData.add(new Object[] { element });
        }
        return parametersData;
    }

    private static class ZCreator implements Creator<String, String> {
        @Override
        public String create(final String source) {
            return "Z" + source;
        }
    }
    private static class UnderscoreCreator implements Creator<String, String> {
        @Override
        public String create(final String source) {
            return "_" + source;
        }
    }
    private static class XYCreator implements Creator<String, Iterable<String>> {
        @Override
        public Iterable<String> create(final String source) {
            return Arrays.asList("X" + source,"Y" + source);
        }
    }
    private static class ThreeFilter implements Filter<String> {
        @Override
        public boolean filter(final String analysedObject) {
            return analysedObject.contains("3");
        }
    }
}
