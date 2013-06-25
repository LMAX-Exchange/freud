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

package org.freud.analysed.javasource.parser;


import org.freud.core.parser.TokenType;

public enum JavaSourceTokenType implements TokenType {
    INVALID,
    EOR,
    DOWN,
    UP,
    AND,
    AND_ASSIGN,
    ASSIGN,
    AT(true, true),
    BIT_SHIFT_RIGHT,
    BIT_SHIFT_RIGHT_ASSIGN,
    COLON,
    COMMA,
    DEC,
    DIV,
    DIV_ASSIGN,
    DOT(true, false),
    DOTSTAR,
    ELLIPSIS,
    EQUAL,
    GREATER_OR_EQUAL,
    GREATER_THAN,
    INC,
    LBRACK,
    LCURLY,
    LESS_OR_EQUAL,
    LESS_THAN,
    LOGICAL_AND,
    LOGICAL_NOT,
    LOGICAL_OR,
    LPAREN,
    MINUS,
    MINUS_ASSIGN,
    MOD,
    MOD_ASSIGN,
    NOT,
    NOT_EQUAL,
    OR,
    OR_ASSIGN,
    PLUS,
    PLUS_ASSIGN,
    QUESTION,
    RBRACK,
    RCURLY,
    RPAREN,
    SEMI,
    SHIFT_LEFT,
    SHIFT_LEFT_ASSIGN,
    SHIFT_RIGHT,
    SHIFT_RIGHT_ASSIGN,
    STAR,
    STAR_ASSIGN,
    XOR,
    XOR_ASSIGN,
    ABSTRACT,
    ASSERT,
    BOOLEAN,
    BREAK,
    BYTE,
    CASE,
    CATCH,
    CHAR,
    CLASS(true, false),
    CONTINUE,
    DEFAULT,
    DO,
    DOUBLE,
    ELSE,
    ENUM(true, false),
    EXTENDS,
    FALSE,
    FINAL,
    FINALLY,
    FLOAT,
    FOR,
    IF,
    IMPLEMENTS,
    INSTANCEOF,
    INTERFACE(true, false),
    IMPORT,
    INT,
    LONG,
    NATIVE,
    NEW,
    NULL,
    PACKAGE,
    PRIVATE,
    PROTECTED,
    PUBLIC,
    RETURN,
    SHORT,
    STATIC,
    STRICTFP,
    SUPER,
    SWITCH,
    SYNCHRONIZED,
    THIS,
    THROW,
    THROWS,
    TRANSIENT,
    TRUE,
    TRY,
    VOID,
    VOLATILE,
    WHILE,
    ANNOTATION_INIT_ARRAY_ELEMENT,
    ANNOTATION_INIT_BLOCK,
    ANNOTATION_INIT_DEFAULT_KEY,
    ANNOTATION_INIT_KEY_LIST,
    ANNOTATION_LIST(true, false),
    ANNOTATION_METHOD_DECL,
    ANNOTATION_SCOPE,
    ANNOTATION_TOP_LEVEL_SCOPE,
    ARGUMENT_LIST,
    ARRAY_DECLARATOR,
    ARRAY_DECLARATOR_LIST,
    ARRAY_ELEMENT_ACCESS,
    ARRAY_INITIALIZER,
    BLOCK_SCOPE,
    CAST_EXPR,
    CATCH_CLAUSE_LIST,
    CLASS_CONSTRUCTOR_CALL,
    CLASS_INSTANCE_INITIALIZER,
    CLASS_STATIC_INITIALIZER,
    CLASS_TOP_LEVEL_SCOPE,
    CONSTRUCTOR_DECL,
    ENUM_TOP_LEVEL_SCOPE,
    EXPR,
    EXTENDS_BOUND_LIST,
    EXTENDS_CLAUSE,
    FOR_CONDITION,
    FOR_EACH,
    FOR_INIT,
    FOR_UPDATE,
    FORMAL_PARAM_LIST,
    FORMAL_PARAM_STD_DECL,
    FORMAL_PARAM_VARARG_DECL,
    FUNCTION_METHOD_DECL(true, false),
    GENERIC_TYPE_ARG_LIST,
    GENERIC_TYPE_PARAM_LIST,
    INTERFACE_TOP_LEVEL_SCOPE,
    IMPLEMENTS_CLAUSE,
    LABELED_STATEMENT,
    LOCAL_MODIFIER_LIST,
    JAVA_SOURCE,
    METHOD_CALL,
    MODIFIER_LIST,
    PARENTESIZED_EXPR,
    POST_DEC,
    POST_INC,
    PRE_DEC,
    PRE_INC,
    QUALIFIED_TYPE_IDENT,
    STATIC_ARRAY_CREATOR,
    SUPER_CONSTRUCTOR_CALL,
    SWITCH_BLOCK_LABEL_LIST,
    THIS_CONSTRUCTOR_CALL,
    THROWS_CLAUSE,
    TYPE,
    UNARY_MINUS,
    UNARY_PLUS,
    VAR_DECLARATION,
    VAR_DECLARATOR,
    VAR_DECLARATOR_LIST,
    VOID_METHOD_DECL,
    IDENT,
    HEX_LITERAL,
    OCTAL_LITERAL,
    DECIMAL_LITERAL,
    FLOATING_POINT_LITERAL,
    CHARACTER_LITERAL,
    STRING_LITERAL,
    HEX_DIGIT,
    INTEGER_TYPE_SUFFIX,
    EXPONENT,
    FLOAT_TYPE_SUFFIX,
    ESCAPE_SEQUENCE,
    UNICODE_ESCAPE,
    OCTAL_ESCAPE,
    JAVA_ID_START,
    JAVA_ID_PART,
    WS,
    COMMENT,
    LINE_COMMENT;

    private final boolean idRequired;
    private final boolean useIdLocation;

    JavaSourceTokenType() {
        this(false, false);
    }

    JavaSourceTokenType(boolean idRequired, boolean useIdLocation) {
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