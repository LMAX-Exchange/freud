package org.langera.freud.dsl;

public interface MatchableDsl<Dsl extends BooleanOperatorDsl>
{
    BooleanOperatorDsl<Dsl> have(BooleanOperatorDsl<Dsl> dsl);
}
