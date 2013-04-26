package org.freud.analysed.javasource;

import java.util.Map;

public interface Annotation {
    String getName();

    String getDefaultParameter();

    Map<String, String> getParameterMap();
}
