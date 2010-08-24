package org.langera.freud.dsl;

import org.hamcrest.Matcher;

public interface MatchingDsl<Dsl extends BooleanOperatorDsl, T>
{
    BooleanOperatorDsl<Dsl> have(Matcher<T> matcher);

    BooleanOperatorDsl<Dsl> has(Matcher<T> matcher);

    BooleanOperatorDsl<Dsl> a(Matcher<T> matcher);

    BooleanOperatorDsl<Dsl> an(Matcher<T> matcher);
}
