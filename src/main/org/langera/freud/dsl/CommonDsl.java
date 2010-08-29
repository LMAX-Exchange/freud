package org.langera.freud.dsl;

public interface CommonDsl<DSL extends BooleanOperatorDsl, T> extends MatchingDsl<DSL, T>, ReadableDsl<DSL>
{
}
