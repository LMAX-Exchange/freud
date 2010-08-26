package org.langera.freud.dsl;

import org.hamcrest.Matcher;

public interface MatchingDsl<Dsl extends BooleanOperatorDsl, T> extends ReadableDsl<Dsl>
{
    BooleanOperatorDsl<Dsl> is(Matcher<T> matcher);
}
