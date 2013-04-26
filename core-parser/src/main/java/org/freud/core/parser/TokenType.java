package org.freud.core.parser;

public interface TokenType {

    boolean isIdRequired();

    boolean isUseIdLocation();

    String getName();

    boolean isIdent();
}
