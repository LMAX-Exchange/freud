package org.langera.freud.x;

import org.hamcrest.Matcher;
import org.langera.freud.AnalysisListener;
import org.langera.freud.util.collection.AnalysedObjectIterator;

public final class Analysis<D extends Analysis.BooleanDsl>
{
    private AnalysisTest<D>[] tests;
    private AnalysisListener listener;

    public Analysis(AnalysisTest<D>... tests)
    {
        this.tests = tests;
    }

    public Analysis(AnalysisListener listener, AnalysisTest<D>... tests)
    {
        this(tests);
        this.listener = listener;
    }

    public void analyse(AnalysedObjectIterator<D> iterator)
    {

    }

    public void setListener(AnalysisListener listener)
    {
        this.listener = listener;
    }

    public interface AnalysisTest<Dsl extends BooleanDsl> extends AnalysisTestMinimal<Dsl>
    {
        AnalysisTestMinimal<Dsl> forEach(Dsl dsl);
    }

    public interface AnalysisTestMinimal<Dsl extends BooleanDsl>
    {
        Dsl assertThat();
    }

    public interface BooleanDsl<T>
    {
        BooleanDsl<T> and(BooleanDsl<T> dsl);

        BooleanDsl<T> or(BooleanDsl<T> dsl);

        BooleanDsl<T> no(BooleanDsl<T> dsl);

        BooleanDsl<T> not(BooleanDsl<T> dsl);

        BooleanDsl<T> have(Matcher<T> matcher);

        BooleanDsl<T> has(Matcher<T> matcher);

        BooleanDsl<T> a(Matcher<T> matcher);

        BooleanDsl<T> an(Matcher<T> matcher);

        BooleanDsl<T> matches(String regex);
    }


//    AnalysisTest<Css> test = forEach(declaration().matches("display")).
//    assertThat().declarationValue("none")).declarationValue("none");

//    AnalysisTest<Css> test = forEach().declaration().matches("display").
//    assertThat().no(declarationValue("none")).declarationValue("none"));
}
