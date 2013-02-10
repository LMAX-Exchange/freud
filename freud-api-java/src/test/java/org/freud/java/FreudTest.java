package org.freud.java;

import org.freud.core.Creator;
import org.freud.core.Filter;
import org.freud.core.SubTypeCreator;
import org.freud.core.iterator.AnalysedObjectIterator;
import org.freud.core.iterator.FilteredAnalysedObjectIterator;
import org.freud.core.iterator.SubTypeAnalysedObjectIterator;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

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
        Iterable<String> data = new AnalysedObjectIterator<String, String>(new ZCreator(),
                new SubTypeAnalysedObjectIterator<String, String>(new XYCreator(),
                    new FilteredAnalysedObjectIterator<String>(
                        new AnalysedObjectIterator<String, String>(new UnderscoreCreator(), source), new ThreeFilter())));
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
    private static class XYCreator implements SubTypeCreator<String, String> {
        @Override
        public void create(final String source, final List<String> collector) {
            collector.add("X" + source);
            collector.add("Y" + source);
        }
    }
    private static class ThreeFilter implements Filter<String> {
        @Override
        public boolean filter(final String analysedObject) {
            return analysedObject.contains("3");
        }
    }
}
