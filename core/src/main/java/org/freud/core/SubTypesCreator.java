package org.freud.core;

import java.util.List;

public interface SubTypesCreator<S, A> {

    void create(S source, List<A> collector);
}
