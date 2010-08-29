package org.langera.freud.dsl;

import org.hamcrest.Matcher;

public interface MatchingDsl<DSL extends BooleanOperatorDsl, T> extends BooleanOperatorDsl<DSL>
{
    BooleanOperatorDsl<DSL> is(Matcher<T> matcher);
}
