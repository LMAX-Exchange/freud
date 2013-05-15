package org.freud.java.rule.iterator;

import org.freud.core.listener.AnalysisListener;

import java.util.Iterator;

public interface AnalysedObjectIterator<T> extends Iterable<T>, Iterator<T> {
    boolean isAlertOnEmptyIterator();

    T current();

    Class<T> analysedObjectType();

    void setListener(AnalysisListener listener);

}
