package org.freud.core;

import java.util.List;

public interface SubTypeCreator<S, A> {

    void create(S source, List<A> collector);
}
