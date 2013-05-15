package org.freud.java.matcher;


public interface RegexMatcherAdapter<T> {

    String getStringToMatch(T toBeAnalysed);

    String matcherDisplayName();
}
