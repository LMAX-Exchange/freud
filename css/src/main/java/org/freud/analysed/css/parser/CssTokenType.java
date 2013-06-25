/*
 * Copyright 2013 LMAX Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.freud.analysed.css.parser;

import org.freud.core.parser.TokenType;

public enum CssTokenType implements TokenType {
    INVALID,
    EOR,
    DOWN,
    UP,
    IMPORT,
    NESTED,
    NEST,
    RULE,
    ATTRIB(true, true),
    CHILD,
    ADJACENT_SIBLING,
    ATTRIBEQUAL,
    HASVALUE,
    BEGINSWITH,
    PSEUDO(true, true),
    PROPERTY,
    COLOUR,
    FUNCTION,
    TAG(true, true),
    ID(true, true),
    CLASS(true, true),
    UNIVERSAL,
    IDENT,
    SL_COMMENT,
    COMMENT,
    WS,
    AT_IMPORT,
    AT_INCLUDE,
    AT_SIGN,
    LEFT_BRACE,
    RIGHT_BRACE,
    COMMA,
    GREATER_THAN,
    PLUS,
    SEMICOLON,
    ASTERIK,
    HASH,
    DOT,
    COLON,
    DOUBLE_COLON,
    LEFT_BRACKET,
    RIGHT_BRACKET,
    EQUAL,
    TILDE_EQUAL,
    VERTICAL_LINE_EQUAL,
    PERCENTAGE,
    PX,
    CM,
    MM,
    IN,
    PT,
    PC,
    EM,
    EX,
    DEG,
    RAD,
    GRAD,
    MS,
    S,
    HZ,
    KHZ,
    LEFT_PARENTHESIS,
    RIGHT_PARENTHESIS;


    private final boolean idRequired;
    private final boolean useIdLocation;

    CssTokenType() {
        this(false, false);
    }

    CssTokenType(boolean idRequired, boolean useIdLocation) {
        this.idRequired = idRequired;
        this.useIdLocation = useIdLocation;
    }

    public boolean isIdRequired() {
        return idRequired;
    }

    public boolean isUseIdLocation() {
        return useIdLocation;
    }

    public String getName() {
        return name();
    }

    public boolean isIdent() {
        return this == IDENT;
    }
}

