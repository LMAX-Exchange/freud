// $ANTLR 3.2 Sep 23, 2009 12:02:23 /home/langera/dev/freud/trunk/src/grammar/Java.g 2010-02-19 14:59:05

package org.langera.freud.optional.javasource.parser;


import org.antlr.runtime.BaseRecognizer;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.DFA;
import org.antlr.runtime.EarlyExitException;
import org.antlr.runtime.IntStream;
import org.antlr.runtime.Lexer;
import org.antlr.runtime.MismatchedSetException;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;

public class JavaLexer extends Lexer {
    public static final int PACKAGE=84;
    public static final int EXPONENT=173;
    public static final int STAR=49;
    public static final int WHILE=103;
    public static final int MOD=32;
    public static final int MOD_ASSIGN=33;
    public static final int CASE=58;
    public static final int CHAR=60;
    public static final int NEW=82;
    public static final int DO=64;
    public static final int GENERIC_TYPE_PARAM_LIST=138;
    public static final int CLASS_INSTANCE_INITIALIZER=121;
    public static final int ARRAY_ELEMENT_ACCESS=115;
    public static final int FOR_CONDITION=129;
    public static final int NOT=34;
    public static final int VAR_DECLARATION=160;
    public static final int ANNOTATION_METHOD_DECL=109;
    public static final int EOF=-1;
    public static final int DIV_ASSIGN=14;
    public static final int BREAK=56;
    public static final int LOGICAL_AND=26;
    public static final int BIT_SHIFT_RIGHT_ASSIGN=9;
    public static final int UNARY_PLUS=159;
    public static final int TYPE=157;
    public static final int FINAL=70;
    public static final int INC=21;
    public static final int RPAREN=43;
    public static final int IMPORT=78;
    public static final int STRING_LITERAL=170;
    public static final int FOR_UPDATE=132;
    public static final int FLOATING_POINT_LITERAL=168;
    public static final int CAST_EXPR=118;
    public static final int NOT_EQUAL=35;
    public static final int VOID_METHOD_DECL=163;
    public static final int RETURN=88;
    public static final int THIS=95;
    public static final int DOUBLE=65;
    public static final int BLOCK_COMMENT=181;
    public static final int VOID=101;
    public static final int ENUM_TOP_LEVEL_SCOPE=125;
    public static final int SUPER=92;
    public static final int ANNOTATION_INIT_KEY_LIST=107;
    public static final int JAVA_ID_START=178;
    public static final int FLOAT_TYPE_SUFFIX=174;
    public static final int PRE_DEC=149;
    public static final int RBRACK=41;
    public static final int IMPLEMENTS_CLAUSE=140;
    public static final int SWITCH_BLOCK_LABEL_LIST=154;
    public static final int LINE_COMMENT=183;
    public static final int PRIVATE=85;
    public static final int STATIC=90;
    public static final int BLOCK_SCOPE=117;
    public static final int ANNOTATION_INIT_DEFAULT_KEY=106;
    public static final int SWITCH=93;
    public static final int NULL=83;
    public static final int VAR_DECLARATOR=161;
    public static final int MINUS_ASSIGN=31;
    public static final int ELSE=66;
    public static final int STRICTFP=91;
    public static final int CHARACTER_LITERAL=169;
    public static final int PRE_INC=150;
    public static final int ANNOTATION_LIST=108;
    public static final int ELLIPSIS=17;
    public static final int NATIVE=81;
    public static final int OCTAL_ESCAPE=177;
    public static final int UNARY_MINUS=158;
    public static final int THROWS=97;
    public static final int LCURLY=23;
    public static final int INT=79;
    public static final int FORMAL_PARAM_VARARG_DECL=135;
    public static final int METHOD_CALL=144;
    public static final int ASSERT=54;
    public static final int TRY=100;
    public static final int INTERFACE_TOP_LEVEL_SCOPE=139;
    public static final int SHIFT_LEFT=45;
    public static final int WS=180;
    public static final int SHIFT_RIGHT=47;
    public static final int FORMAL_PARAM_STD_DECL=134;
    public static final int LOCAL_MODIFIER_LIST=142;
    public static final int OR=36;
    public static final int LESS_THAN=25;
    public static final int SHIFT_RIGHT_ASSIGN=48;
    public static final int EXTENDS_BOUND_LIST=127;
    public static final int JAVA_SOURCE=143;
    public static final int CATCH=59;
    public static final int FALSE=69;
    public static final int INTEGER_TYPE_SUFFIX=172;
    public static final int DECIMAL_LITERAL=167;
    public static final int THROW=96;
    public static final int FOR_INIT=131;
    public static final int PROTECTED=86;
    public static final int DEC=12;
    public static final int CLASS=61;
    public static final int LBRACK=22;
    public static final int BIT_SHIFT_RIGHT=8;
    public static final int THROWS_CLAUSE=156;
    public static final int GREATER_OR_EQUAL=19;
    public static final int FOR=73;
    public static final int LOGICAL_NOT=27;
    public static final int THIS_CONSTRUCTOR_CALL=155;
    public static final int FLOAT=72;
    public static final int JAVADOC_COMMENT=182;
    public static final int ABSTRACT=53;
    public static final int AND=4;
    public static final int POST_DEC=147;
    public static final int AND_ASSIGN=5;
    public static final int ANNOTATION_SCOPE=110;
    public static final int MODIFIER_LIST=145;
    public static final int STATIC_ARRAY_CREATOR=152;
    public static final int LPAREN=29;
    public static final int IF=74;
    public static final int AT=7;
    public static final int CONSTRUCTOR_DECL=124;
    public static final int ESCAPE_SEQUENCE=175;
    public static final int LABELED_STATEMENT=141;
    public static final int UNICODE_ESCAPE=176;
    public static final int BOOLEAN=55;
    public static final int SYNCHRONIZED=94;
    public static final int EXPR=126;
    public static final int CLASS_TOP_LEVEL_SCOPE=123;
    public static final int IMPLEMENTS=75;
    public static final int CONTINUE=62;
    public static final int COMMA=11;
    public static final int TRANSIENT=98;
    public static final int XOR_ASSIGN=52;
    public static final int EQUAL=18;
    public static final int LOGICAL_OR=28;
    public static final int ARGUMENT_LIST=112;
    public static final int QUALIFIED_TYPE_IDENT=151;
    public static final int IDENT=164;
    public static final int PLUS=38;
    public static final int ANNOTATION_INIT_BLOCK=105;
    public static final int HEX_LITERAL=165;
    public static final int DOT=15;
    public static final int SHIFT_LEFT_ASSIGN=46;
    public static final int FORMAL_PARAM_LIST=133;
    public static final int GENERIC_TYPE_ARG_LIST=137;
    public static final int DOTSTAR=16;
    public static final int ANNOTATION_TOP_LEVEL_SCOPE=111;
    public static final int BYTE=57;
    public static final int XOR=51;
    public static final int JAVA_ID_PART=179;
    public static final int GREATER_THAN=20;
    public static final int VOLATILE=102;
    public static final int PARENTESIZED_EXPR=146;
    public static final int LESS_OR_EQUAL=24;
    public static final int ARRAY_DECLARATOR_LIST=114;
    public static final int CLASS_STATIC_INITIALIZER=122;
    public static final int DEFAULT=63;
    public static final int OCTAL_LITERAL=166;
    public static final int HEX_DIGIT=171;
    public static final int SHORT=89;
    public static final int INSTANCEOF=76;
    public static final int MINUS=30;
    public static final int SEMI=44;
    public static final int TRUE=99;
    public static final int EXTENDS_CLAUSE=128;
    public static final int STAR_ASSIGN=50;
    public static final int VAR_DECLARATOR_LIST=162;
    public static final int COLON=10;
    public static final int ARRAY_DECLARATOR=113;
    public static final int OR_ASSIGN=37;
    public static final int ENUM=67;
    public static final int QUESTION=40;
    public static final int FINALLY=71;
    public static final int RCURLY=42;
    public static final int ASSIGN=6;
    public static final int PLUS_ASSIGN=39;
    public static final int ANNOTATION_INIT_ARRAY_ELEMENT=104;
    public static final int FUNCTION_METHOD_DECL=136;
    public static final int INTERFACE=77;
    public static final int DIV=13;
    public static final int POST_INC=148;
    public static final int LONG=80;
    public static final int CLASS_CONSTRUCTOR_CALL=120;
    public static final int PUBLIC=87;
    public static final int EXTENDS=68;
    public static final int FOR_EACH=130;
    public static final int ARRAY_INITIALIZER=116;
    public static final int CATCH_CLAUSE_LIST=119;
    public static final int SUPER_CONSTRUCTOR_CALL=153;


    /**
     *  If <code>true</code> whitespaces will be preserved within the hidden channel,
     *  otherwise the appropriate tokens will be thrown away.
     *  <p>
     *  The default value of this flag is <code>false</code>.
     */
    public boolean mPreserveWhitespaces = false;

    /**
     *  If <code>true</code> javadoc comments will be preserved within the hidden
     *  channel, otherwise the appropriate tokens will be thrown away.
     *  <p>
     *  The default value of this flag is <code>false</code>.
     */
    public boolean mPreserveJavaDocComments = false;

    /**
     *  If <code>true</code> (non javadoc) block comments will be preserved within the
     *  hidden channel, otherwise the appropriate tokens will be thrown away.
     *  <p>
     *  The default value of this flag is <code>false</code>.
     */
    public boolean mPreserveBlockComments = false;

    /**
     *  If <code>true</code> single line comments will be preserved within the hidden
     *  channel, otherwise the appropriate tokens will be thrown away.
     *  <p>
     *  The default value of this flag is <code>false</code>.
     */
    public boolean mPreserveLineComments = false;



    // delegates
    // delegators

    public JavaLexer() {;} 
    public JavaLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public JavaLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "/home/langera/dev/freud/trunk/src/grammar/Java.g"; }

    // $ANTLR start "AND"
    public final void mAND() throws RecognitionException {
        try {
            int _type = AND;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:42:5: ( '&' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:42:7: '&'
            {
            match('&'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "AND"

    // $ANTLR start "AND_ASSIGN"
    public final void mAND_ASSIGN() throws RecognitionException {
        try {
            int _type = AND_ASSIGN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:43:12: ( '&=' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:43:14: '&='
            {
            match("&="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "AND_ASSIGN"

    // $ANTLR start "ASSIGN"
    public final void mASSIGN() throws RecognitionException {
        try {
            int _type = ASSIGN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:44:8: ( '=' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:44:10: '='
            {
            match('='); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ASSIGN"

    // $ANTLR start "AT"
    public final void mAT() throws RecognitionException {
        try {
            int _type = AT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:45:4: ( '@' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:45:6: '@'
            {
            match('@'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "AT"

    // $ANTLR start "BIT_SHIFT_RIGHT"
    public final void mBIT_SHIFT_RIGHT() throws RecognitionException {
        try {
            int _type = BIT_SHIFT_RIGHT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:46:17: ( '>>>' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:46:19: '>>>'
            {
            match(">>>"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "BIT_SHIFT_RIGHT"

    // $ANTLR start "BIT_SHIFT_RIGHT_ASSIGN"
    public final void mBIT_SHIFT_RIGHT_ASSIGN() throws RecognitionException {
        try {
            int _type = BIT_SHIFT_RIGHT_ASSIGN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:47:24: ( '>>>=' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:47:26: '>>>='
            {
            match(">>>="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "BIT_SHIFT_RIGHT_ASSIGN"

    // $ANTLR start "COLON"
    public final void mCOLON() throws RecognitionException {
        try {
            int _type = COLON;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:48:7: ( ':' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:48:9: ':'
            {
            match(':'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "COLON"

    // $ANTLR start "COMMA"
    public final void mCOMMA() throws RecognitionException {
        try {
            int _type = COMMA;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:49:7: ( ',' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:49:9: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "COMMA"

    // $ANTLR start "DEC"
    public final void mDEC() throws RecognitionException {
        try {
            int _type = DEC;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:50:5: ( '--' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:50:7: '--'
            {
            match("--"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DEC"

    // $ANTLR start "DIV"
    public final void mDIV() throws RecognitionException {
        try {
            int _type = DIV;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:51:5: ( '/' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:51:7: '/'
            {
            match('/'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DIV"

    // $ANTLR start "DIV_ASSIGN"
    public final void mDIV_ASSIGN() throws RecognitionException {
        try {
            int _type = DIV_ASSIGN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:52:12: ( '/=' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:52:14: '/='
            {
            match("/="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DIV_ASSIGN"

    // $ANTLR start "DOT"
    public final void mDOT() throws RecognitionException {
        try {
            int _type = DOT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:53:5: ( '.' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:53:7: '.'
            {
            match('.'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DOT"

    // $ANTLR start "DOTSTAR"
    public final void mDOTSTAR() throws RecognitionException {
        try {
            int _type = DOTSTAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:54:9: ( '.*' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:54:11: '.*'
            {
            match(".*"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DOTSTAR"

    // $ANTLR start "ELLIPSIS"
    public final void mELLIPSIS() throws RecognitionException {
        try {
            int _type = ELLIPSIS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:55:10: ( '...' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:55:12: '...'
            {
            match("..."); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ELLIPSIS"

    // $ANTLR start "EQUAL"
    public final void mEQUAL() throws RecognitionException {
        try {
            int _type = EQUAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:56:7: ( '==' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:56:9: '=='
            {
            match("=="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "EQUAL"

    // $ANTLR start "GREATER_OR_EQUAL"
    public final void mGREATER_OR_EQUAL() throws RecognitionException {
        try {
            int _type = GREATER_OR_EQUAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:57:18: ( '>=' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:57:20: '>='
            {
            match(">="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "GREATER_OR_EQUAL"

    // $ANTLR start "GREATER_THAN"
    public final void mGREATER_THAN() throws RecognitionException {
        try {
            int _type = GREATER_THAN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:58:14: ( '>' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:58:16: '>'
            {
            match('>'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "GREATER_THAN"

    // $ANTLR start "INC"
    public final void mINC() throws RecognitionException {
        try {
            int _type = INC;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:59:5: ( '++' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:59:7: '++'
            {
            match("++"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "INC"

    // $ANTLR start "LBRACK"
    public final void mLBRACK() throws RecognitionException {
        try {
            int _type = LBRACK;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:60:8: ( '[' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:60:10: '['
            {
            match('['); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LBRACK"

    // $ANTLR start "LCURLY"
    public final void mLCURLY() throws RecognitionException {
        try {
            int _type = LCURLY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:61:8: ( '{' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:61:10: '{'
            {
            match('{'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LCURLY"

    // $ANTLR start "LESS_OR_EQUAL"
    public final void mLESS_OR_EQUAL() throws RecognitionException {
        try {
            int _type = LESS_OR_EQUAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:62:15: ( '<=' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:62:17: '<='
            {
            match("<="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LESS_OR_EQUAL"

    // $ANTLR start "LESS_THAN"
    public final void mLESS_THAN() throws RecognitionException {
        try {
            int _type = LESS_THAN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:63:11: ( '<' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:63:13: '<'
            {
            match('<'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LESS_THAN"

    // $ANTLR start "LOGICAL_AND"
    public final void mLOGICAL_AND() throws RecognitionException {
        try {
            int _type = LOGICAL_AND;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:64:13: ( '&&' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:64:15: '&&'
            {
            match("&&"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LOGICAL_AND"

    // $ANTLR start "LOGICAL_NOT"
    public final void mLOGICAL_NOT() throws RecognitionException {
        try {
            int _type = LOGICAL_NOT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:65:13: ( '!' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:65:15: '!'
            {
            match('!'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LOGICAL_NOT"

    // $ANTLR start "LOGICAL_OR"
    public final void mLOGICAL_OR() throws RecognitionException {
        try {
            int _type = LOGICAL_OR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:66:12: ( '||' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:66:14: '||'
            {
            match("||"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LOGICAL_OR"

    // $ANTLR start "LPAREN"
    public final void mLPAREN() throws RecognitionException {
        try {
            int _type = LPAREN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:67:8: ( '(' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:67:10: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LPAREN"

    // $ANTLR start "MINUS"
    public final void mMINUS() throws RecognitionException {
        try {
            int _type = MINUS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:68:7: ( '-' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:68:9: '-'
            {
            match('-'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "MINUS"

    // $ANTLR start "MINUS_ASSIGN"
    public final void mMINUS_ASSIGN() throws RecognitionException {
        try {
            int _type = MINUS_ASSIGN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:69:14: ( '-=' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:69:16: '-='
            {
            match("-="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "MINUS_ASSIGN"

    // $ANTLR start "MOD"
    public final void mMOD() throws RecognitionException {
        try {
            int _type = MOD;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:70:5: ( '%' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:70:7: '%'
            {
            match('%'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "MOD"

    // $ANTLR start "MOD_ASSIGN"
    public final void mMOD_ASSIGN() throws RecognitionException {
        try {
            int _type = MOD_ASSIGN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:71:12: ( '%=' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:71:14: '%='
            {
            match("%="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "MOD_ASSIGN"

    // $ANTLR start "NOT"
    public final void mNOT() throws RecognitionException {
        try {
            int _type = NOT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:72:5: ( '~' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:72:7: '~'
            {
            match('~'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NOT"

    // $ANTLR start "NOT_EQUAL"
    public final void mNOT_EQUAL() throws RecognitionException {
        try {
            int _type = NOT_EQUAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:73:11: ( '!=' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:73:13: '!='
            {
            match("!="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NOT_EQUAL"

    // $ANTLR start "OR"
    public final void mOR() throws RecognitionException {
        try {
            int _type = OR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:74:4: ( '|' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:74:6: '|'
            {
            match('|'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "OR"

    // $ANTLR start "OR_ASSIGN"
    public final void mOR_ASSIGN() throws RecognitionException {
        try {
            int _type = OR_ASSIGN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:75:11: ( '|=' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:75:13: '|='
            {
            match("|="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "OR_ASSIGN"

    // $ANTLR start "PLUS"
    public final void mPLUS() throws RecognitionException {
        try {
            int _type = PLUS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:76:6: ( '+' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:76:8: '+'
            {
            match('+'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PLUS"

    // $ANTLR start "PLUS_ASSIGN"
    public final void mPLUS_ASSIGN() throws RecognitionException {
        try {
            int _type = PLUS_ASSIGN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:77:13: ( '+=' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:77:15: '+='
            {
            match("+="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PLUS_ASSIGN"

    // $ANTLR start "QUESTION"
    public final void mQUESTION() throws RecognitionException {
        try {
            int _type = QUESTION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:78:10: ( '?' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:78:12: '?'
            {
            match('?'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "QUESTION"

    // $ANTLR start "RBRACK"
    public final void mRBRACK() throws RecognitionException {
        try {
            int _type = RBRACK;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:79:8: ( ']' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:79:10: ']'
            {
            match(']'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RBRACK"

    // $ANTLR start "RCURLY"
    public final void mRCURLY() throws RecognitionException {
        try {
            int _type = RCURLY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:80:8: ( '}' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:80:10: '}'
            {
            match('}'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RCURLY"

    // $ANTLR start "RPAREN"
    public final void mRPAREN() throws RecognitionException {
        try {
            int _type = RPAREN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:81:8: ( ')' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:81:10: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RPAREN"

    // $ANTLR start "SEMI"
    public final void mSEMI() throws RecognitionException {
        try {
            int _type = SEMI;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:82:6: ( ';' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:82:8: ';'
            {
            match(';'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SEMI"

    // $ANTLR start "SHIFT_LEFT"
    public final void mSHIFT_LEFT() throws RecognitionException {
        try {
            int _type = SHIFT_LEFT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:83:12: ( '<<' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:83:14: '<<'
            {
            match("<<"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SHIFT_LEFT"

    // $ANTLR start "SHIFT_LEFT_ASSIGN"
    public final void mSHIFT_LEFT_ASSIGN() throws RecognitionException {
        try {
            int _type = SHIFT_LEFT_ASSIGN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:84:19: ( '<<=' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:84:21: '<<='
            {
            match("<<="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SHIFT_LEFT_ASSIGN"

    // $ANTLR start "SHIFT_RIGHT"
    public final void mSHIFT_RIGHT() throws RecognitionException {
        try {
            int _type = SHIFT_RIGHT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:85:13: ( '>>' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:85:15: '>>'
            {
            match(">>"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SHIFT_RIGHT"

    // $ANTLR start "SHIFT_RIGHT_ASSIGN"
    public final void mSHIFT_RIGHT_ASSIGN() throws RecognitionException {
        try {
            int _type = SHIFT_RIGHT_ASSIGN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:86:20: ( '>>=' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:86:22: '>>='
            {
            match(">>="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SHIFT_RIGHT_ASSIGN"

    // $ANTLR start "STAR"
    public final void mSTAR() throws RecognitionException {
        try {
            int _type = STAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:87:6: ( '*' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:87:8: '*'
            {
            match('*'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "STAR"

    // $ANTLR start "STAR_ASSIGN"
    public final void mSTAR_ASSIGN() throws RecognitionException {
        try {
            int _type = STAR_ASSIGN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:88:13: ( '*=' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:88:15: '*='
            {
            match("*="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "STAR_ASSIGN"

    // $ANTLR start "XOR"
    public final void mXOR() throws RecognitionException {
        try {
            int _type = XOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:89:5: ( '^' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:89:7: '^'
            {
            match('^'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "XOR"

    // $ANTLR start "XOR_ASSIGN"
    public final void mXOR_ASSIGN() throws RecognitionException {
        try {
            int _type = XOR_ASSIGN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:90:12: ( '^=' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:90:14: '^='
            {
            match("^="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "XOR_ASSIGN"

    // $ANTLR start "ABSTRACT"
    public final void mABSTRACT() throws RecognitionException {
        try {
            int _type = ABSTRACT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:91:10: ( 'abstract' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:91:12: 'abstract'
            {
            match("abstract"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ABSTRACT"

    // $ANTLR start "ASSERT"
    public final void mASSERT() throws RecognitionException {
        try {
            int _type = ASSERT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:92:8: ( 'assert' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:92:10: 'assert'
            {
            match("assert"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ASSERT"

    // $ANTLR start "BOOLEAN"
    public final void mBOOLEAN() throws RecognitionException {
        try {
            int _type = BOOLEAN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:93:9: ( 'boolean' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:93:11: 'boolean'
            {
            match("boolean"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "BOOLEAN"

    // $ANTLR start "BREAK"
    public final void mBREAK() throws RecognitionException {
        try {
            int _type = BREAK;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:94:7: ( 'break' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:94:9: 'break'
            {
            match("break"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "BREAK"

    // $ANTLR start "BYTE"
    public final void mBYTE() throws RecognitionException {
        try {
            int _type = BYTE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:95:6: ( 'byte' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:95:8: 'byte'
            {
            match("byte"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "BYTE"

    // $ANTLR start "CASE"
    public final void mCASE() throws RecognitionException {
        try {
            int _type = CASE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:96:6: ( 'case' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:96:8: 'case'
            {
            match("case"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CASE"

    // $ANTLR start "CATCH"
    public final void mCATCH() throws RecognitionException {
        try {
            int _type = CATCH;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:97:7: ( 'catch' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:97:9: 'catch'
            {
            match("catch"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CATCH"

    // $ANTLR start "CHAR"
    public final void mCHAR() throws RecognitionException {
        try {
            int _type = CHAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:98:6: ( 'char' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:98:8: 'char'
            {
            match("char"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CHAR"

    // $ANTLR start "CLASS"
    public final void mCLASS() throws RecognitionException {
        try {
            int _type = CLASS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:99:7: ( 'class' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:99:9: 'class'
            {
            match("class"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CLASS"

    // $ANTLR start "CONTINUE"
    public final void mCONTINUE() throws RecognitionException {
        try {
            int _type = CONTINUE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:100:10: ( 'continue' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:100:12: 'continue'
            {
            match("continue"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CONTINUE"

    // $ANTLR start "DEFAULT"
    public final void mDEFAULT() throws RecognitionException {
        try {
            int _type = DEFAULT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:101:9: ( 'default' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:101:11: 'default'
            {
            match("default"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DEFAULT"

    // $ANTLR start "DO"
    public final void mDO() throws RecognitionException {
        try {
            int _type = DO;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:102:4: ( 'do' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:102:6: 'do'
            {
            match("do"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DO"

    // $ANTLR start "DOUBLE"
    public final void mDOUBLE() throws RecognitionException {
        try {
            int _type = DOUBLE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:103:8: ( 'double' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:103:10: 'double'
            {
            match("double"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DOUBLE"

    // $ANTLR start "ELSE"
    public final void mELSE() throws RecognitionException {
        try {
            int _type = ELSE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:104:6: ( 'else' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:104:8: 'else'
            {
            match("else"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ELSE"

    // $ANTLR start "ENUM"
    public final void mENUM() throws RecognitionException {
        try {
            int _type = ENUM;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:105:6: ( 'enum' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:105:8: 'enum'
            {
            match("enum"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ENUM"

    // $ANTLR start "EXTENDS"
    public final void mEXTENDS() throws RecognitionException {
        try {
            int _type = EXTENDS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:106:9: ( 'extends' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:106:11: 'extends'
            {
            match("extends"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "EXTENDS"

    // $ANTLR start "FALSE"
    public final void mFALSE() throws RecognitionException {
        try {
            int _type = FALSE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:107:7: ( 'false' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:107:9: 'false'
            {
            match("false"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "FALSE"

    // $ANTLR start "FINAL"
    public final void mFINAL() throws RecognitionException {
        try {
            int _type = FINAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:108:7: ( 'final' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:108:9: 'final'
            {
            match("final"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "FINAL"

    // $ANTLR start "FINALLY"
    public final void mFINALLY() throws RecognitionException {
        try {
            int _type = FINALLY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:109:9: ( 'finally' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:109:11: 'finally'
            {
            match("finally"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "FINALLY"

    // $ANTLR start "FLOAT"
    public final void mFLOAT() throws RecognitionException {
        try {
            int _type = FLOAT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:110:7: ( 'float' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:110:9: 'float'
            {
            match("float"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "FLOAT"

    // $ANTLR start "FOR"
    public final void mFOR() throws RecognitionException {
        try {
            int _type = FOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:111:5: ( 'for' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:111:7: 'for'
            {
            match("for"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "FOR"

    // $ANTLR start "IF"
    public final void mIF() throws RecognitionException {
        try {
            int _type = IF;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:112:4: ( 'if' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:112:6: 'if'
            {
            match("if"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "IF"

    // $ANTLR start "IMPLEMENTS"
    public final void mIMPLEMENTS() throws RecognitionException {
        try {
            int _type = IMPLEMENTS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:113:12: ( 'implements' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:113:14: 'implements'
            {
            match("implements"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "IMPLEMENTS"

    // $ANTLR start "INSTANCEOF"
    public final void mINSTANCEOF() throws RecognitionException {
        try {
            int _type = INSTANCEOF;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:114:12: ( 'instanceof' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:114:14: 'instanceof'
            {
            match("instanceof"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "INSTANCEOF"

    // $ANTLR start "INTERFACE"
    public final void mINTERFACE() throws RecognitionException {
        try {
            int _type = INTERFACE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:115:11: ( 'interface' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:115:13: 'interface'
            {
            match("interface"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "INTERFACE"

    // $ANTLR start "IMPORT"
    public final void mIMPORT() throws RecognitionException {
        try {
            int _type = IMPORT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:116:8: ( 'import' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:116:10: 'import'
            {
            match("import"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "IMPORT"

    // $ANTLR start "INT"
    public final void mINT() throws RecognitionException {
        try {
            int _type = INT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:117:5: ( 'int' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:117:7: 'int'
            {
            match("int"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "INT"

    // $ANTLR start "LONG"
    public final void mLONG() throws RecognitionException {
        try {
            int _type = LONG;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:118:6: ( 'long' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:118:8: 'long'
            {
            match("long"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LONG"

    // $ANTLR start "NATIVE"
    public final void mNATIVE() throws RecognitionException {
        try {
            int _type = NATIVE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:119:8: ( 'native' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:119:10: 'native'
            {
            match("native"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NATIVE"

    // $ANTLR start "NEW"
    public final void mNEW() throws RecognitionException {
        try {
            int _type = NEW;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:120:5: ( 'new' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:120:7: 'new'
            {
            match("new"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NEW"

    // $ANTLR start "NULL"
    public final void mNULL() throws RecognitionException {
        try {
            int _type = NULL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:121:6: ( 'null' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:121:8: 'null'
            {
            match("null"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NULL"

    // $ANTLR start "PACKAGE"
    public final void mPACKAGE() throws RecognitionException {
        try {
            int _type = PACKAGE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:122:9: ( 'package' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:122:11: 'package'
            {
            match("package"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PACKAGE"

    // $ANTLR start "PRIVATE"
    public final void mPRIVATE() throws RecognitionException {
        try {
            int _type = PRIVATE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:123:9: ( 'private' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:123:11: 'private'
            {
            match("private"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PRIVATE"

    // $ANTLR start "PROTECTED"
    public final void mPROTECTED() throws RecognitionException {
        try {
            int _type = PROTECTED;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:124:11: ( 'protected' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:124:13: 'protected'
            {
            match("protected"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PROTECTED"

    // $ANTLR start "PUBLIC"
    public final void mPUBLIC() throws RecognitionException {
        try {
            int _type = PUBLIC;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:125:8: ( 'public' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:125:10: 'public'
            {
            match("public"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PUBLIC"

    // $ANTLR start "RETURN"
    public final void mRETURN() throws RecognitionException {
        try {
            int _type = RETURN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:126:8: ( 'return' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:126:10: 'return'
            {
            match("return"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RETURN"

    // $ANTLR start "SHORT"
    public final void mSHORT() throws RecognitionException {
        try {
            int _type = SHORT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:127:7: ( 'short' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:127:9: 'short'
            {
            match("short"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SHORT"

    // $ANTLR start "STATIC"
    public final void mSTATIC() throws RecognitionException {
        try {
            int _type = STATIC;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:128:8: ( 'static' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:128:10: 'static'
            {
            match("static"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "STATIC"

    // $ANTLR start "STRICTFP"
    public final void mSTRICTFP() throws RecognitionException {
        try {
            int _type = STRICTFP;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:129:10: ( 'strictfp' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:129:12: 'strictfp'
            {
            match("strictfp"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "STRICTFP"

    // $ANTLR start "SUPER"
    public final void mSUPER() throws RecognitionException {
        try {
            int _type = SUPER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:130:7: ( 'super' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:130:9: 'super'
            {
            match("super"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SUPER"

    // $ANTLR start "SWITCH"
    public final void mSWITCH() throws RecognitionException {
        try {
            int _type = SWITCH;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:131:8: ( 'switch' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:131:10: 'switch'
            {
            match("switch"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SWITCH"

    // $ANTLR start "SYNCHRONIZED"
    public final void mSYNCHRONIZED() throws RecognitionException {
        try {
            int _type = SYNCHRONIZED;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:132:14: ( 'synchronized' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:132:16: 'synchronized'
            {
            match("synchronized"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SYNCHRONIZED"

    // $ANTLR start "THIS"
    public final void mTHIS() throws RecognitionException {
        try {
            int _type = THIS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:133:6: ( 'this' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:133:8: 'this'
            {
            match("this"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "THIS"

    // $ANTLR start "THROW"
    public final void mTHROW() throws RecognitionException {
        try {
            int _type = THROW;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:134:7: ( 'throw' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:134:9: 'throw'
            {
            match("throw"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "THROW"

    // $ANTLR start "THROWS"
    public final void mTHROWS() throws RecognitionException {
        try {
            int _type = THROWS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:135:8: ( 'throws' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:135:10: 'throws'
            {
            match("throws"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "THROWS"

    // $ANTLR start "TRANSIENT"
    public final void mTRANSIENT() throws RecognitionException {
        try {
            int _type = TRANSIENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:136:11: ( 'transient' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:136:13: 'transient'
            {
            match("transient"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "TRANSIENT"

    // $ANTLR start "TRUE"
    public final void mTRUE() throws RecognitionException {
        try {
            int _type = TRUE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:137:6: ( 'true' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:137:8: 'true'
            {
            match("true"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "TRUE"

    // $ANTLR start "TRY"
    public final void mTRY() throws RecognitionException {
        try {
            int _type = TRY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:138:5: ( 'try' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:138:7: 'try'
            {
            match("try"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "TRY"

    // $ANTLR start "VOID"
    public final void mVOID() throws RecognitionException {
        try {
            int _type = VOID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:139:6: ( 'void' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:139:8: 'void'
            {
            match("void"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "VOID"

    // $ANTLR start "VOLATILE"
    public final void mVOLATILE() throws RecognitionException {
        try {
            int _type = VOLATILE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:140:10: ( 'volatile' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:140:12: 'volatile'
            {
            match("volatile"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "VOLATILE"

    // $ANTLR start "WHILE"
    public final void mWHILE() throws RecognitionException {
        try {
            int _type = WHILE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:141:7: ( 'while' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:141:9: 'while'
            {
            match("while"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "WHILE"

    // $ANTLR start "HEX_LITERAL"
    public final void mHEX_LITERAL() throws RecognitionException {
        try {
            int _type = HEX_LITERAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:1170:13: ( '0' ( 'x' | 'X' ) ( HEX_DIGIT )+ ( INTEGER_TYPE_SUFFIX )? )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:1170:15: '0' ( 'x' | 'X' ) ( HEX_DIGIT )+ ( INTEGER_TYPE_SUFFIX )?
            {
            match('0'); 
            if ( input.LA(1)=='X'||input.LA(1)=='x' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // /home/langera/dev/freud/trunk/src/grammar/Java.g:1170:29: ( HEX_DIGIT )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>='0' && LA1_0<='9')||(LA1_0>='A' && LA1_0<='F')||(LA1_0>='a' && LA1_0<='f')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // /home/langera/dev/freud/trunk/src/grammar/Java.g:1170:29: HEX_DIGIT
            	    {
            	    mHEX_DIGIT(); 

            	    }
            	    break;

            	default :
            	    if ( cnt1 >= 1 ) break loop1;
                        EarlyExitException eee =
                            new EarlyExitException(1, input);
                        throw eee;
                }
                cnt1++;
            } while (true);

            // /home/langera/dev/freud/trunk/src/grammar/Java.g:1170:40: ( INTEGER_TYPE_SUFFIX )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0=='L'||LA2_0=='l') ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // /home/langera/dev/freud/trunk/src/grammar/Java.g:1170:40: INTEGER_TYPE_SUFFIX
                    {
                    mINTEGER_TYPE_SUFFIX(); 

                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "HEX_LITERAL"

    // $ANTLR start "DECIMAL_LITERAL"
    public final void mDECIMAL_LITERAL() throws RecognitionException {
        try {
            int _type = DECIMAL_LITERAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:1172:17: ( ( '0' | '1' .. '9' ( '0' .. '9' )* ) ( INTEGER_TYPE_SUFFIX )? )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:1172:19: ( '0' | '1' .. '9' ( '0' .. '9' )* ) ( INTEGER_TYPE_SUFFIX )?
            {
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:1172:19: ( '0' | '1' .. '9' ( '0' .. '9' )* )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0=='0') ) {
                alt4=1;
            }
            else if ( ((LA4_0>='1' && LA4_0<='9')) ) {
                alt4=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }
            switch (alt4) {
                case 1 :
                    // /home/langera/dev/freud/trunk/src/grammar/Java.g:1172:20: '0'
                    {
                    match('0'); 

                    }
                    break;
                case 2 :
                    // /home/langera/dev/freud/trunk/src/grammar/Java.g:1172:26: '1' .. '9' ( '0' .. '9' )*
                    {
                    matchRange('1','9'); 
                    // /home/langera/dev/freud/trunk/src/grammar/Java.g:1172:35: ( '0' .. '9' )*
                    loop3:
                    do {
                        int alt3=2;
                        int LA3_0 = input.LA(1);

                        if ( ((LA3_0>='0' && LA3_0<='9')) ) {
                            alt3=1;
                        }


                        switch (alt3) {
                    	case 1 :
                    	    // /home/langera/dev/freud/trunk/src/grammar/Java.g:1172:35: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    break loop3;
                        }
                    } while (true);


                    }
                    break;

            }

            // /home/langera/dev/freud/trunk/src/grammar/Java.g:1172:46: ( INTEGER_TYPE_SUFFIX )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0=='L'||LA5_0=='l') ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // /home/langera/dev/freud/trunk/src/grammar/Java.g:1172:46: INTEGER_TYPE_SUFFIX
                    {
                    mINTEGER_TYPE_SUFFIX(); 

                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DECIMAL_LITERAL"

    // $ANTLR start "OCTAL_LITERAL"
    public final void mOCTAL_LITERAL() throws RecognitionException {
        try {
            int _type = OCTAL_LITERAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:1174:15: ( '0' ( '0' .. '7' )+ ( INTEGER_TYPE_SUFFIX )? )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:1174:17: '0' ( '0' .. '7' )+ ( INTEGER_TYPE_SUFFIX )?
            {
            match('0'); 
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:1174:21: ( '0' .. '7' )+
            int cnt6=0;
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( ((LA6_0>='0' && LA6_0<='7')) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // /home/langera/dev/freud/trunk/src/grammar/Java.g:1174:22: '0' .. '7'
            	    {
            	    matchRange('0','7'); 

            	    }
            	    break;

            	default :
            	    if ( cnt6 >= 1 ) break loop6;
                        EarlyExitException eee =
                            new EarlyExitException(6, input);
                        throw eee;
                }
                cnt6++;
            } while (true);

            // /home/langera/dev/freud/trunk/src/grammar/Java.g:1174:33: ( INTEGER_TYPE_SUFFIX )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0=='L'||LA7_0=='l') ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // /home/langera/dev/freud/trunk/src/grammar/Java.g:1174:33: INTEGER_TYPE_SUFFIX
                    {
                    mINTEGER_TYPE_SUFFIX(); 

                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "OCTAL_LITERAL"

    // $ANTLR start "HEX_DIGIT"
    public final void mHEX_DIGIT() throws RecognitionException {
        try {
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:1177:11: ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:1177:13: ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' )
            {
            if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='F')||(input.LA(1)>='a' && input.LA(1)<='f') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "HEX_DIGIT"

    // $ANTLR start "INTEGER_TYPE_SUFFIX"
    public final void mINTEGER_TYPE_SUFFIX() throws RecognitionException {
        try {
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:1180:21: ( ( 'l' | 'L' ) )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:1180:23: ( 'l' | 'L' )
            {
            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "INTEGER_TYPE_SUFFIX"

    // $ANTLR start "FLOATING_POINT_LITERAL"
    public final void mFLOATING_POINT_LITERAL() throws RecognitionException {
        try {
            int _type = FLOATING_POINT_LITERAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:1183:5: ( ( '0' .. '9' )+ ( DOT ( '0' .. '9' )* ( EXPONENT )? ( FLOAT_TYPE_SUFFIX )? | EXPONENT ( FLOAT_TYPE_SUFFIX )? | FLOAT_TYPE_SUFFIX ) | DOT ( '0' .. '9' )+ ( EXPONENT )? ( FLOAT_TYPE_SUFFIX )? )
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( ((LA17_0>='0' && LA17_0<='9')) ) {
                alt17=1;
            }
            else if ( (LA17_0=='.') ) {
                alt17=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 17, 0, input);

                throw nvae;
            }
            switch (alt17) {
                case 1 :
                    // /home/langera/dev/freud/trunk/src/grammar/Java.g:1183:9: ( '0' .. '9' )+ ( DOT ( '0' .. '9' )* ( EXPONENT )? ( FLOAT_TYPE_SUFFIX )? | EXPONENT ( FLOAT_TYPE_SUFFIX )? | FLOAT_TYPE_SUFFIX )
                    {
                    // /home/langera/dev/freud/trunk/src/grammar/Java.g:1183:9: ( '0' .. '9' )+
                    int cnt8=0;
                    loop8:
                    do {
                        int alt8=2;
                        int LA8_0 = input.LA(1);

                        if ( ((LA8_0>='0' && LA8_0<='9')) ) {
                            alt8=1;
                        }


                        switch (alt8) {
                    	case 1 :
                    	    // /home/langera/dev/freud/trunk/src/grammar/Java.g:1183:10: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt8 >= 1 ) break loop8;
                                EarlyExitException eee =
                                    new EarlyExitException(8, input);
                                throw eee;
                        }
                        cnt8++;
                    } while (true);

                    // /home/langera/dev/freud/trunk/src/grammar/Java.g:1184:9: ( DOT ( '0' .. '9' )* ( EXPONENT )? ( FLOAT_TYPE_SUFFIX )? | EXPONENT ( FLOAT_TYPE_SUFFIX )? | FLOAT_TYPE_SUFFIX )
                    int alt13=3;
                    switch ( input.LA(1) ) {
                    case '.':
                        {
                        alt13=1;
                        }
                        break;
                    case 'E':
                    case 'e':
                        {
                        alt13=2;
                        }
                        break;
                    case 'D':
                    case 'F':
                    case 'd':
                    case 'f':
                        {
                        alt13=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 13, 0, input);

                        throw nvae;
                    }

                    switch (alt13) {
                        case 1 :
                            // /home/langera/dev/freud/trunk/src/grammar/Java.g:1185:13: DOT ( '0' .. '9' )* ( EXPONENT )? ( FLOAT_TYPE_SUFFIX )?
                            {
                            mDOT(); 
                            // /home/langera/dev/freud/trunk/src/grammar/Java.g:1185:17: ( '0' .. '9' )*
                            loop9:
                            do {
                                int alt9=2;
                                int LA9_0 = input.LA(1);

                                if ( ((LA9_0>='0' && LA9_0<='9')) ) {
                                    alt9=1;
                                }


                                switch (alt9) {
                            	case 1 :
                            	    // /home/langera/dev/freud/trunk/src/grammar/Java.g:1185:18: '0' .. '9'
                            	    {
                            	    matchRange('0','9'); 

                            	    }
                            	    break;

                            	default :
                            	    break loop9;
                                }
                            } while (true);

                            // /home/langera/dev/freud/trunk/src/grammar/Java.g:1185:29: ( EXPONENT )?
                            int alt10=2;
                            int LA10_0 = input.LA(1);

                            if ( (LA10_0=='E'||LA10_0=='e') ) {
                                alt10=1;
                            }
                            switch (alt10) {
                                case 1 :
                                    // /home/langera/dev/freud/trunk/src/grammar/Java.g:1185:29: EXPONENT
                                    {
                                    mEXPONENT(); 

                                    }
                                    break;

                            }

                            // /home/langera/dev/freud/trunk/src/grammar/Java.g:1185:39: ( FLOAT_TYPE_SUFFIX )?
                            int alt11=2;
                            int LA11_0 = input.LA(1);

                            if ( (LA11_0=='D'||LA11_0=='F'||LA11_0=='d'||LA11_0=='f') ) {
                                alt11=1;
                            }
                            switch (alt11) {
                                case 1 :
                                    // /home/langera/dev/freud/trunk/src/grammar/Java.g:1185:39: FLOAT_TYPE_SUFFIX
                                    {
                                    mFLOAT_TYPE_SUFFIX(); 

                                    }
                                    break;

                            }


                            }
                            break;
                        case 2 :
                            // /home/langera/dev/freud/trunk/src/grammar/Java.g:1186:13: EXPONENT ( FLOAT_TYPE_SUFFIX )?
                            {
                            mEXPONENT(); 
                            // /home/langera/dev/freud/trunk/src/grammar/Java.g:1186:22: ( FLOAT_TYPE_SUFFIX )?
                            int alt12=2;
                            int LA12_0 = input.LA(1);

                            if ( (LA12_0=='D'||LA12_0=='F'||LA12_0=='d'||LA12_0=='f') ) {
                                alt12=1;
                            }
                            switch (alt12) {
                                case 1 :
                                    // /home/langera/dev/freud/trunk/src/grammar/Java.g:1186:22: FLOAT_TYPE_SUFFIX
                                    {
                                    mFLOAT_TYPE_SUFFIX(); 

                                    }
                                    break;

                            }


                            }
                            break;
                        case 3 :
                            // /home/langera/dev/freud/trunk/src/grammar/Java.g:1187:13: FLOAT_TYPE_SUFFIX
                            {
                            mFLOAT_TYPE_SUFFIX(); 

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /home/langera/dev/freud/trunk/src/grammar/Java.g:1189:9: DOT ( '0' .. '9' )+ ( EXPONENT )? ( FLOAT_TYPE_SUFFIX )?
                    {
                    mDOT(); 
                    // /home/langera/dev/freud/trunk/src/grammar/Java.g:1189:13: ( '0' .. '9' )+
                    int cnt14=0;
                    loop14:
                    do {
                        int alt14=2;
                        int LA14_0 = input.LA(1);

                        if ( ((LA14_0>='0' && LA14_0<='9')) ) {
                            alt14=1;
                        }


                        switch (alt14) {
                    	case 1 :
                    	    // /home/langera/dev/freud/trunk/src/grammar/Java.g:1189:14: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt14 >= 1 ) break loop14;
                                EarlyExitException eee =
                                    new EarlyExitException(14, input);
                                throw eee;
                        }
                        cnt14++;
                    } while (true);

                    // /home/langera/dev/freud/trunk/src/grammar/Java.g:1189:25: ( EXPONENT )?
                    int alt15=2;
                    int LA15_0 = input.LA(1);

                    if ( (LA15_0=='E'||LA15_0=='e') ) {
                        alt15=1;
                    }
                    switch (alt15) {
                        case 1 :
                            // /home/langera/dev/freud/trunk/src/grammar/Java.g:1189:25: EXPONENT
                            {
                            mEXPONENT(); 

                            }
                            break;

                    }

                    // /home/langera/dev/freud/trunk/src/grammar/Java.g:1189:35: ( FLOAT_TYPE_SUFFIX )?
                    int alt16=2;
                    int LA16_0 = input.LA(1);

                    if ( (LA16_0=='D'||LA16_0=='F'||LA16_0=='d'||LA16_0=='f') ) {
                        alt16=1;
                    }
                    switch (alt16) {
                        case 1 :
                            // /home/langera/dev/freud/trunk/src/grammar/Java.g:1189:35: FLOAT_TYPE_SUFFIX
                            {
                            mFLOAT_TYPE_SUFFIX(); 

                            }
                            break;

                    }


                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "FLOATING_POINT_LITERAL"

    // $ANTLR start "EXPONENT"
    public final void mEXPONENT() throws RecognitionException {
        try {
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:1193:10: ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:1193:12: ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+
            {
            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // /home/langera/dev/freud/trunk/src/grammar/Java.g:1193:22: ( '+' | '-' )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0=='+'||LA18_0=='-') ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // /home/langera/dev/freud/trunk/src/grammar/Java.g:
                    {
                    if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
                        input.consume();

                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;}


                    }
                    break;

            }

            // /home/langera/dev/freud/trunk/src/grammar/Java.g:1193:33: ( '0' .. '9' )+
            int cnt19=0;
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( ((LA19_0>='0' && LA19_0<='9')) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // /home/langera/dev/freud/trunk/src/grammar/Java.g:1193:34: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt19 >= 1 ) break loop19;
                        EarlyExitException eee =
                            new EarlyExitException(19, input);
                        throw eee;
                }
                cnt19++;
            } while (true);


            }

        }
        finally {
        }
    }
    // $ANTLR end "EXPONENT"

    // $ANTLR start "FLOAT_TYPE_SUFFIX"
    public final void mFLOAT_TYPE_SUFFIX() throws RecognitionException {
        try {
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:1196:19: ( ( 'f' | 'F' | 'd' | 'D' ) )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:1196:21: ( 'f' | 'F' | 'd' | 'D' )
            {
            if ( input.LA(1)=='D'||input.LA(1)=='F'||input.LA(1)=='d'||input.LA(1)=='f' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "FLOAT_TYPE_SUFFIX"

    // $ANTLR start "CHARACTER_LITERAL"
    public final void mCHARACTER_LITERAL() throws RecognitionException {
        try {
            int _type = CHARACTER_LITERAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:1199:5: ( '\\'' ( ESCAPE_SEQUENCE | ~ ( '\\'' | '\\\\' ) ) '\\'' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:1199:9: '\\'' ( ESCAPE_SEQUENCE | ~ ( '\\'' | '\\\\' ) ) '\\''
            {
            match('\''); 
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:1199:14: ( ESCAPE_SEQUENCE | ~ ( '\\'' | '\\\\' ) )
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0=='\\') ) {
                alt20=1;
            }
            else if ( ((LA20_0>='\u0000' && LA20_0<='&')||(LA20_0>='(' && LA20_0<='[')||(LA20_0>=']' && LA20_0<='\uFFFF')) ) {
                alt20=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 20, 0, input);

                throw nvae;
            }
            switch (alt20) {
                case 1 :
                    // /home/langera/dev/freud/trunk/src/grammar/Java.g:1199:16: ESCAPE_SEQUENCE
                    {
                    mESCAPE_SEQUENCE(); 

                    }
                    break;
                case 2 :
                    // /home/langera/dev/freud/trunk/src/grammar/Java.g:1199:34: ~ ( '\\'' | '\\\\' )
                    {
                    if ( (input.LA(1)>='\u0000' && input.LA(1)<='&')||(input.LA(1)>='(' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFF') ) {
                        input.consume();

                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;}


                    }
                    break;

            }

            match('\''); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CHARACTER_LITERAL"

    // $ANTLR start "STRING_LITERAL"
    public final void mSTRING_LITERAL() throws RecognitionException {
        try {
            int _type = STRING_LITERAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:1203:5: ( '\"' ( ESCAPE_SEQUENCE | ~ ( '\\\\' | '\"' ) )* '\"' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:1203:8: '\"' ( ESCAPE_SEQUENCE | ~ ( '\\\\' | '\"' ) )* '\"'
            {
            match('\"'); 
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:1203:12: ( ESCAPE_SEQUENCE | ~ ( '\\\\' | '\"' ) )*
            loop21:
            do {
                int alt21=3;
                int LA21_0 = input.LA(1);

                if ( (LA21_0=='\\') ) {
                    alt21=1;
                }
                else if ( ((LA21_0>='\u0000' && LA21_0<='!')||(LA21_0>='#' && LA21_0<='[')||(LA21_0>=']' && LA21_0<='\uFFFF')) ) {
                    alt21=2;
                }


                switch (alt21) {
            	case 1 :
            	    // /home/langera/dev/freud/trunk/src/grammar/Java.g:1203:14: ESCAPE_SEQUENCE
            	    {
            	    mESCAPE_SEQUENCE(); 

            	    }
            	    break;
            	case 2 :
            	    // /home/langera/dev/freud/trunk/src/grammar/Java.g:1203:32: ~ ( '\\\\' | '\"' )
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='!')||(input.LA(1)>='#' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFF') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop21;
                }
            } while (true);

            match('\"'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "STRING_LITERAL"

    // $ANTLR start "ESCAPE_SEQUENCE"
    public final void mESCAPE_SEQUENCE() throws RecognitionException {
        try {
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:1208:5: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' ) | UNICODE_ESCAPE | OCTAL_ESCAPE )
            int alt22=3;
            int LA22_0 = input.LA(1);

            if ( (LA22_0=='\\') ) {
                switch ( input.LA(2) ) {
                case '\"':
                case '\'':
                case '\\':
                case 'b':
                case 'f':
                case 'n':
                case 'r':
                case 't':
                    {
                    alt22=1;
                    }
                    break;
                case 'u':
                    {
                    alt22=2;
                    }
                    break;
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                    {
                    alt22=3;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("", 22, 1, input);

                    throw nvae;
                }

            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 22, 0, input);

                throw nvae;
            }
            switch (alt22) {
                case 1 :
                    // /home/langera/dev/freud/trunk/src/grammar/Java.g:1208:9: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' )
                    {
                    match('\\'); 
                    if ( input.LA(1)=='\"'||input.LA(1)=='\''||input.LA(1)=='\\'||input.LA(1)=='b'||input.LA(1)=='f'||input.LA(1)=='n'||input.LA(1)=='r'||input.LA(1)=='t' ) {
                        input.consume();

                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;}


                    }
                    break;
                case 2 :
                    // /home/langera/dev/freud/trunk/src/grammar/Java.g:1209:9: UNICODE_ESCAPE
                    {
                    mUNICODE_ESCAPE(); 

                    }
                    break;
                case 3 :
                    // /home/langera/dev/freud/trunk/src/grammar/Java.g:1210:9: OCTAL_ESCAPE
                    {
                    mOCTAL_ESCAPE(); 

                    }
                    break;

            }
        }
        finally {
        }
    }
    // $ANTLR end "ESCAPE_SEQUENCE"

    // $ANTLR start "OCTAL_ESCAPE"
    public final void mOCTAL_ESCAPE() throws RecognitionException {
        try {
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:1215:5: ( '\\\\' ( '0' .. '3' ) ( '0' .. '7' ) ( '0' .. '7' ) | '\\\\' ( '0' .. '7' ) ( '0' .. '7' ) | '\\\\' ( '0' .. '7' ) )
            int alt23=3;
            int LA23_0 = input.LA(1);

            if ( (LA23_0=='\\') ) {
                int LA23_1 = input.LA(2);

                if ( ((LA23_1>='0' && LA23_1<='3')) ) {
                    int LA23_2 = input.LA(3);

                    if ( ((LA23_2>='0' && LA23_2<='7')) ) {
                        int LA23_4 = input.LA(4);

                        if ( ((LA23_4>='0' && LA23_4<='7')) ) {
                            alt23=1;
                        }
                        else {
                            alt23=2;}
                    }
                    else {
                        alt23=3;}
                }
                else if ( ((LA23_1>='4' && LA23_1<='7')) ) {
                    int LA23_3 = input.LA(3);

                    if ( ((LA23_3>='0' && LA23_3<='7')) ) {
                        alt23=2;
                    }
                    else {
                        alt23=3;}
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 23, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 23, 0, input);

                throw nvae;
            }
            switch (alt23) {
                case 1 :
                    // /home/langera/dev/freud/trunk/src/grammar/Java.g:1215:9: '\\\\' ( '0' .. '3' ) ( '0' .. '7' ) ( '0' .. '7' )
                    {
                    match('\\'); 
                    // /home/langera/dev/freud/trunk/src/grammar/Java.g:1215:14: ( '0' .. '3' )
                    // /home/langera/dev/freud/trunk/src/grammar/Java.g:1215:15: '0' .. '3'
                    {
                    matchRange('0','3'); 

                    }

                    // /home/langera/dev/freud/trunk/src/grammar/Java.g:1215:25: ( '0' .. '7' )
                    // /home/langera/dev/freud/trunk/src/grammar/Java.g:1215:26: '0' .. '7'
                    {
                    matchRange('0','7'); 

                    }

                    // /home/langera/dev/freud/trunk/src/grammar/Java.g:1215:36: ( '0' .. '7' )
                    // /home/langera/dev/freud/trunk/src/grammar/Java.g:1215:37: '0' .. '7'
                    {
                    matchRange('0','7'); 

                    }


                    }
                    break;
                case 2 :
                    // /home/langera/dev/freud/trunk/src/grammar/Java.g:1216:9: '\\\\' ( '0' .. '7' ) ( '0' .. '7' )
                    {
                    match('\\'); 
                    // /home/langera/dev/freud/trunk/src/grammar/Java.g:1216:14: ( '0' .. '7' )
                    // /home/langera/dev/freud/trunk/src/grammar/Java.g:1216:15: '0' .. '7'
                    {
                    matchRange('0','7'); 

                    }

                    // /home/langera/dev/freud/trunk/src/grammar/Java.g:1216:25: ( '0' .. '7' )
                    // /home/langera/dev/freud/trunk/src/grammar/Java.g:1216:26: '0' .. '7'
                    {
                    matchRange('0','7'); 

                    }


                    }
                    break;
                case 3 :
                    // /home/langera/dev/freud/trunk/src/grammar/Java.g:1217:9: '\\\\' ( '0' .. '7' )
                    {
                    match('\\'); 
                    // /home/langera/dev/freud/trunk/src/grammar/Java.g:1217:14: ( '0' .. '7' )
                    // /home/langera/dev/freud/trunk/src/grammar/Java.g:1217:15: '0' .. '7'
                    {
                    matchRange('0','7'); 

                    }


                    }
                    break;

            }
        }
        finally {
        }
    }
    // $ANTLR end "OCTAL_ESCAPE"

    // $ANTLR start "UNICODE_ESCAPE"
    public final void mUNICODE_ESCAPE() throws RecognitionException {
        try {
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:1222:5: ( '\\\\' 'u' HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:1222:9: '\\\\' 'u' HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT
            {
            match('\\'); 
            match('u'); 
            mHEX_DIGIT(); 
            mHEX_DIGIT(); 
            mHEX_DIGIT(); 
            mHEX_DIGIT(); 

            }

        }
        finally {
        }
    }
    // $ANTLR end "UNICODE_ESCAPE"

    // $ANTLR start "IDENT"
    public final void mIDENT() throws RecognitionException {
        try {
            int _type = IDENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:1226:5: ( JAVA_ID_START ( JAVA_ID_PART )* )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:1226:9: JAVA_ID_START ( JAVA_ID_PART )*
            {
            mJAVA_ID_START(); 
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:1226:23: ( JAVA_ID_PART )*
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( (LA24_0=='$'||(LA24_0>='0' && LA24_0<='9')||(LA24_0>='A' && LA24_0<='Z')||LA24_0=='_'||(LA24_0>='a' && LA24_0<='z')||(LA24_0>='\u00C0' && LA24_0<='\u00D6')||(LA24_0>='\u00D8' && LA24_0<='\u00F6')||(LA24_0>='\u00F8' && LA24_0<='\u1FFF')||(LA24_0>='\u3040' && LA24_0<='\u318F')||(LA24_0>='\u3300' && LA24_0<='\u337F')||(LA24_0>='\u3400' && LA24_0<='\u3D2D')||(LA24_0>='\u4E00' && LA24_0<='\u9FFF')||(LA24_0>='\uF900' && LA24_0<='\uFAFF')) ) {
                    alt24=1;
                }


                switch (alt24) {
            	case 1 :
            	    // /home/langera/dev/freud/trunk/src/grammar/Java.g:1226:24: JAVA_ID_PART
            	    {
            	    mJAVA_ID_PART(); 

            	    }
            	    break;

            	default :
            	    break loop24;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "IDENT"

    // $ANTLR start "JAVA_ID_START"
    public final void mJAVA_ID_START() throws RecognitionException {
        try {
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:1231:5: ( '\\u0024' | '\\u0041' .. '\\u005a' | '\\u005f' | '\\u0061' .. '\\u007a' | '\\u00c0' .. '\\u00d6' | '\\u00d8' .. '\\u00f6' | '\\u00f8' .. '\\u00ff' | '\\u0100' .. '\\u1fff' | '\\u3040' .. '\\u318f' | '\\u3300' .. '\\u337f' | '\\u3400' .. '\\u3d2d' | '\\u4e00' .. '\\u9fff' | '\\uf900' .. '\\ufaff' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:
            {
            if ( input.LA(1)=='$'||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z')||(input.LA(1)>='\u00C0' && input.LA(1)<='\u00D6')||(input.LA(1)>='\u00D8' && input.LA(1)<='\u00F6')||(input.LA(1)>='\u00F8' && input.LA(1)<='\u1FFF')||(input.LA(1)>='\u3040' && input.LA(1)<='\u318F')||(input.LA(1)>='\u3300' && input.LA(1)<='\u337F')||(input.LA(1)>='\u3400' && input.LA(1)<='\u3D2D')||(input.LA(1)>='\u4E00' && input.LA(1)<='\u9FFF')||(input.LA(1)>='\uF900' && input.LA(1)<='\uFAFF') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "JAVA_ID_START"

    // $ANTLR start "JAVA_ID_PART"
    public final void mJAVA_ID_PART() throws RecognitionException {
        try {
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:1248:5: ( JAVA_ID_START | '\\u0030' .. '\\u0039' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:
            {
            if ( input.LA(1)=='$'||(input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z')||(input.LA(1)>='\u00C0' && input.LA(1)<='\u00D6')||(input.LA(1)>='\u00D8' && input.LA(1)<='\u00F6')||(input.LA(1)>='\u00F8' && input.LA(1)<='\u1FFF')||(input.LA(1)>='\u3040' && input.LA(1)<='\u318F')||(input.LA(1)>='\u3300' && input.LA(1)<='\u337F')||(input.LA(1)>='\u3400' && input.LA(1)<='\u3D2D')||(input.LA(1)>='\u4E00' && input.LA(1)<='\u9FFF')||(input.LA(1)>='\uF900' && input.LA(1)<='\uFAFF') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "JAVA_ID_PART"

    // $ANTLR start "WS"
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:1252:5: ( ( ' ' | '\\r' | '\\t' | '\\u000C' | '\\n' ) )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:1252:8: ( ' ' | '\\r' | '\\t' | '\\u000C' | '\\n' )
            {
            if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||(input.LA(1)>='\f' && input.LA(1)<='\r')||input.LA(1)==' ' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


                    if (!mPreserveWhitespaces) {
                        skip();
                    } else {
                        _channel = HIDDEN;
                    }
                

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "WS"

    // $ANTLR start "BLOCK_COMMENT"
    public final void mBLOCK_COMMENT() throws RecognitionException {
        try {
            int _type = BLOCK_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:1263:5: ( ( '/*' ~ '*' ( options {greedy=false; } : . )* '*/' | '/**/' ) )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:1263:9: ( '/*' ~ '*' ( options {greedy=false; } : . )* '*/' | '/**/' )
            {
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:1263:9: ( '/*' ~ '*' ( options {greedy=false; } : . )* '*/' | '/**/' )
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0=='/') ) {
                int LA26_1 = input.LA(2);

                if ( (LA26_1=='*') ) {
                    int LA26_2 = input.LA(3);

                    if ( ((LA26_2>='\u0000' && LA26_2<=')')||(LA26_2>='+' && LA26_2<='\uFFFF')) ) {
                        alt26=1;
                    }
                    else if ( (LA26_2=='*') ) {
                        alt26=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 26, 2, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 26, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 26, 0, input);

                throw nvae;
            }
            switch (alt26) {
                case 1 :
                    // /home/langera/dev/freud/trunk/src/grammar/Java.g:1263:13: '/*' ~ '*' ( options {greedy=false; } : . )* '*/'
                    {
                    match("/*"); 

                    if ( (input.LA(1)>='\u0000' && input.LA(1)<=')')||(input.LA(1)>='+' && input.LA(1)<='\uFFFF') ) {
                        input.consume();

                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;}

                    // /home/langera/dev/freud/trunk/src/grammar/Java.g:1263:23: ( options {greedy=false; } : . )*
                    loop25:
                    do {
                        int alt25=2;
                        int LA25_0 = input.LA(1);

                        if ( (LA25_0=='*') ) {
                            int LA25_1 = input.LA(2);

                            if ( (LA25_1=='/') ) {
                                alt25=2;
                            }
                            else if ( ((LA25_1>='\u0000' && LA25_1<='.')||(LA25_1>='0' && LA25_1<='\uFFFF')) ) {
                                alt25=1;
                            }


                        }
                        else if ( ((LA25_0>='\u0000' && LA25_0<=')')||(LA25_0>='+' && LA25_0<='\uFFFF')) ) {
                            alt25=1;
                        }


                        switch (alt25) {
                    	case 1 :
                    	    // /home/langera/dev/freud/trunk/src/grammar/Java.g:1263:51: .
                    	    {
                    	    matchAny(); 

                    	    }
                    	    break;

                    	default :
                    	    break loop25;
                        }
                    } while (true);

                    match("*/"); 


                    }
                    break;
                case 2 :
                    // /home/langera/dev/freud/trunk/src/grammar/Java.g:1264:13: '/**/'
                    {
                    match("/**/"); 


                    }
                    break;

            }


                    if (!mPreserveBlockComments) {
                        skip();
                    } else {
                        _channel = HIDDEN;
                    }
                

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "BLOCK_COMMENT"

    // $ANTLR start "JAVADOC_COMMENT"
    public final void mJAVADOC_COMMENT() throws RecognitionException {
        try {
            int _type = JAVADOC_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:1276:5: ( '/**' ( options {greedy=false; } : . )* '*/' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:1276:9: '/**' ( options {greedy=false; } : . )* '*/'
            {
            match("/**"); 

            // /home/langera/dev/freud/trunk/src/grammar/Java.g:1276:15: ( options {greedy=false; } : . )*
            loop27:
            do {
                int alt27=2;
                int LA27_0 = input.LA(1);

                if ( (LA27_0=='*') ) {
                    int LA27_1 = input.LA(2);

                    if ( (LA27_1=='/') ) {
                        alt27=2;
                    }
                    else if ( ((LA27_1>='\u0000' && LA27_1<='.')||(LA27_1>='0' && LA27_1<='\uFFFF')) ) {
                        alt27=1;
                    }


                }
                else if ( ((LA27_0>='\u0000' && LA27_0<=')')||(LA27_0>='+' && LA27_0<='\uFFFF')) ) {
                    alt27=1;
                }


                switch (alt27) {
            	case 1 :
            	    // /home/langera/dev/freud/trunk/src/grammar/Java.g:1276:43: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop27;
                }
            } while (true);

            match("*/"); 


                    if (!mPreserveJavaDocComments) {
                        skip();
                    } else {
                        _channel = HIDDEN;
                    }
                

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "JAVADOC_COMMENT"

    // $ANTLR start "LINE_COMMENT"
    public final void mLINE_COMMENT() throws RecognitionException {
        try {
            int _type = LINE_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:1287:5: ( '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n' )
            // /home/langera/dev/freud/trunk/src/grammar/Java.g:1287:7: '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n'
            {
            match("//"); 

            // /home/langera/dev/freud/trunk/src/grammar/Java.g:1287:12: (~ ( '\\n' | '\\r' ) )*
            loop28:
            do {
                int alt28=2;
                int LA28_0 = input.LA(1);

                if ( ((LA28_0>='\u0000' && LA28_0<='\t')||(LA28_0>='\u000B' && LA28_0<='\f')||(LA28_0>='\u000E' && LA28_0<='\uFFFF')) ) {
                    alt28=1;
                }


                switch (alt28) {
            	case 1 :
            	    // /home/langera/dev/freud/trunk/src/grammar/Java.g:1287:12: ~ ( '\\n' | '\\r' )
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\t')||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||(input.LA(1)>='\u000E' && input.LA(1)<='\uFFFF') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop28;
                }
            } while (true);

            // /home/langera/dev/freud/trunk/src/grammar/Java.g:1287:26: ( '\\r' )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0=='\r') ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // /home/langera/dev/freud/trunk/src/grammar/Java.g:1287:26: '\\r'
                    {
                    match('\r'); 

                    }
                    break;

            }

            match('\n'); 

                    if (!mPreserveLineComments) {
                        skip();
                    } else {
                        _channel = HIDDEN;
                    }
                

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LINE_COMMENT"

    public void mTokens() throws RecognitionException {
        // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:8: ( AND | AND_ASSIGN | ASSIGN | AT | BIT_SHIFT_RIGHT | BIT_SHIFT_RIGHT_ASSIGN | COLON | COMMA | DEC | DIV | DIV_ASSIGN | DOT | DOTSTAR | ELLIPSIS | EQUAL | GREATER_OR_EQUAL | GREATER_THAN | INC | LBRACK | LCURLY | LESS_OR_EQUAL | LESS_THAN | LOGICAL_AND | LOGICAL_NOT | LOGICAL_OR | LPAREN | MINUS | MINUS_ASSIGN | MOD | MOD_ASSIGN | NOT | NOT_EQUAL | OR | OR_ASSIGN | PLUS | PLUS_ASSIGN | QUESTION | RBRACK | RCURLY | RPAREN | SEMI | SHIFT_LEFT | SHIFT_LEFT_ASSIGN | SHIFT_RIGHT | SHIFT_RIGHT_ASSIGN | STAR | STAR_ASSIGN | XOR | XOR_ASSIGN | ABSTRACT | ASSERT | BOOLEAN | BREAK | BYTE | CASE | CATCH | CHAR | CLASS | CONTINUE | DEFAULT | DO | DOUBLE | ELSE | ENUM | EXTENDS | FALSE | FINAL | FINALLY | FLOAT | FOR | IF | IMPLEMENTS | INSTANCEOF | INTERFACE | IMPORT | INT | LONG | NATIVE | NEW | NULL | PACKAGE | PRIVATE | PROTECTED | PUBLIC | RETURN | SHORT | STATIC | STRICTFP | SUPER | SWITCH | SYNCHRONIZED | THIS | THROW | THROWS | TRANSIENT | TRUE | TRY | VOID | VOLATILE | WHILE | HEX_LITERAL | DECIMAL_LITERAL | OCTAL_LITERAL | FLOATING_POINT_LITERAL | CHARACTER_LITERAL | STRING_LITERAL | IDENT | WS | BLOCK_COMMENT | JAVADOC_COMMENT | LINE_COMMENT )
        int alt30=111;
        alt30 = dfa30.predict(input);
        switch (alt30) {
            case 1 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:10: AND
                {
                mAND(); 

                }
                break;
            case 2 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:14: AND_ASSIGN
                {
                mAND_ASSIGN(); 

                }
                break;
            case 3 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:25: ASSIGN
                {
                mASSIGN(); 

                }
                break;
            case 4 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:32: AT
                {
                mAT(); 

                }
                break;
            case 5 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:35: BIT_SHIFT_RIGHT
                {
                mBIT_SHIFT_RIGHT(); 

                }
                break;
            case 6 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:51: BIT_SHIFT_RIGHT_ASSIGN
                {
                mBIT_SHIFT_RIGHT_ASSIGN(); 

                }
                break;
            case 7 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:74: COLON
                {
                mCOLON(); 

                }
                break;
            case 8 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:80: COMMA
                {
                mCOMMA(); 

                }
                break;
            case 9 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:86: DEC
                {
                mDEC(); 

                }
                break;
            case 10 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:90: DIV
                {
                mDIV(); 

                }
                break;
            case 11 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:94: DIV_ASSIGN
                {
                mDIV_ASSIGN(); 

                }
                break;
            case 12 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:105: DOT
                {
                mDOT(); 

                }
                break;
            case 13 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:109: DOTSTAR
                {
                mDOTSTAR(); 

                }
                break;
            case 14 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:117: ELLIPSIS
                {
                mELLIPSIS(); 

                }
                break;
            case 15 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:126: EQUAL
                {
                mEQUAL(); 

                }
                break;
            case 16 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:132: GREATER_OR_EQUAL
                {
                mGREATER_OR_EQUAL(); 

                }
                break;
            case 17 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:149: GREATER_THAN
                {
                mGREATER_THAN(); 

                }
                break;
            case 18 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:162: INC
                {
                mINC(); 

                }
                break;
            case 19 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:166: LBRACK
                {
                mLBRACK(); 

                }
                break;
            case 20 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:173: LCURLY
                {
                mLCURLY(); 

                }
                break;
            case 21 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:180: LESS_OR_EQUAL
                {
                mLESS_OR_EQUAL(); 

                }
                break;
            case 22 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:194: LESS_THAN
                {
                mLESS_THAN(); 

                }
                break;
            case 23 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:204: LOGICAL_AND
                {
                mLOGICAL_AND(); 

                }
                break;
            case 24 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:216: LOGICAL_NOT
                {
                mLOGICAL_NOT(); 

                }
                break;
            case 25 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:228: LOGICAL_OR
                {
                mLOGICAL_OR(); 

                }
                break;
            case 26 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:239: LPAREN
                {
                mLPAREN(); 

                }
                break;
            case 27 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:246: MINUS
                {
                mMINUS(); 

                }
                break;
            case 28 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:252: MINUS_ASSIGN
                {
                mMINUS_ASSIGN(); 

                }
                break;
            case 29 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:265: MOD
                {
                mMOD(); 

                }
                break;
            case 30 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:269: MOD_ASSIGN
                {
                mMOD_ASSIGN(); 

                }
                break;
            case 31 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:280: NOT
                {
                mNOT(); 

                }
                break;
            case 32 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:284: NOT_EQUAL
                {
                mNOT_EQUAL(); 

                }
                break;
            case 33 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:294: OR
                {
                mOR(); 

                }
                break;
            case 34 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:297: OR_ASSIGN
                {
                mOR_ASSIGN(); 

                }
                break;
            case 35 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:307: PLUS
                {
                mPLUS(); 

                }
                break;
            case 36 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:312: PLUS_ASSIGN
                {
                mPLUS_ASSIGN(); 

                }
                break;
            case 37 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:324: QUESTION
                {
                mQUESTION(); 

                }
                break;
            case 38 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:333: RBRACK
                {
                mRBRACK(); 

                }
                break;
            case 39 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:340: RCURLY
                {
                mRCURLY(); 

                }
                break;
            case 40 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:347: RPAREN
                {
                mRPAREN(); 

                }
                break;
            case 41 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:354: SEMI
                {
                mSEMI(); 

                }
                break;
            case 42 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:359: SHIFT_LEFT
                {
                mSHIFT_LEFT(); 

                }
                break;
            case 43 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:370: SHIFT_LEFT_ASSIGN
                {
                mSHIFT_LEFT_ASSIGN(); 

                }
                break;
            case 44 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:388: SHIFT_RIGHT
                {
                mSHIFT_RIGHT(); 

                }
                break;
            case 45 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:400: SHIFT_RIGHT_ASSIGN
                {
                mSHIFT_RIGHT_ASSIGN(); 

                }
                break;
            case 46 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:419: STAR
                {
                mSTAR(); 

                }
                break;
            case 47 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:424: STAR_ASSIGN
                {
                mSTAR_ASSIGN(); 

                }
                break;
            case 48 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:436: XOR
                {
                mXOR(); 

                }
                break;
            case 49 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:440: XOR_ASSIGN
                {
                mXOR_ASSIGN(); 

                }
                break;
            case 50 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:451: ABSTRACT
                {
                mABSTRACT(); 

                }
                break;
            case 51 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:460: ASSERT
                {
                mASSERT(); 

                }
                break;
            case 52 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:467: BOOLEAN
                {
                mBOOLEAN(); 

                }
                break;
            case 53 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:475: BREAK
                {
                mBREAK(); 

                }
                break;
            case 54 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:481: BYTE
                {
                mBYTE(); 

                }
                break;
            case 55 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:486: CASE
                {
                mCASE(); 

                }
                break;
            case 56 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:491: CATCH
                {
                mCATCH(); 

                }
                break;
            case 57 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:497: CHAR
                {
                mCHAR(); 

                }
                break;
            case 58 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:502: CLASS
                {
                mCLASS(); 

                }
                break;
            case 59 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:508: CONTINUE
                {
                mCONTINUE(); 

                }
                break;
            case 60 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:517: DEFAULT
                {
                mDEFAULT(); 

                }
                break;
            case 61 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:525: DO
                {
                mDO(); 

                }
                break;
            case 62 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:528: DOUBLE
                {
                mDOUBLE(); 

                }
                break;
            case 63 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:535: ELSE
                {
                mELSE(); 

                }
                break;
            case 64 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:540: ENUM
                {
                mENUM(); 

                }
                break;
            case 65 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:545: EXTENDS
                {
                mEXTENDS(); 

                }
                break;
            case 66 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:553: FALSE
                {
                mFALSE(); 

                }
                break;
            case 67 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:559: FINAL
                {
                mFINAL(); 

                }
                break;
            case 68 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:565: FINALLY
                {
                mFINALLY(); 

                }
                break;
            case 69 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:573: FLOAT
                {
                mFLOAT(); 

                }
                break;
            case 70 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:579: FOR
                {
                mFOR(); 

                }
                break;
            case 71 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:583: IF
                {
                mIF(); 

                }
                break;
            case 72 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:586: IMPLEMENTS
                {
                mIMPLEMENTS(); 

                }
                break;
            case 73 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:597: INSTANCEOF
                {
                mINSTANCEOF(); 

                }
                break;
            case 74 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:608: INTERFACE
                {
                mINTERFACE(); 

                }
                break;
            case 75 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:618: IMPORT
                {
                mIMPORT(); 

                }
                break;
            case 76 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:625: INT
                {
                mINT(); 

                }
                break;
            case 77 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:629: LONG
                {
                mLONG(); 

                }
                break;
            case 78 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:634: NATIVE
                {
                mNATIVE(); 

                }
                break;
            case 79 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:641: NEW
                {
                mNEW(); 

                }
                break;
            case 80 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:645: NULL
                {
                mNULL(); 

                }
                break;
            case 81 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:650: PACKAGE
                {
                mPACKAGE(); 

                }
                break;
            case 82 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:658: PRIVATE
                {
                mPRIVATE(); 

                }
                break;
            case 83 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:666: PROTECTED
                {
                mPROTECTED(); 

                }
                break;
            case 84 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:676: PUBLIC
                {
                mPUBLIC(); 

                }
                break;
            case 85 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:683: RETURN
                {
                mRETURN(); 

                }
                break;
            case 86 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:690: SHORT
                {
                mSHORT(); 

                }
                break;
            case 87 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:696: STATIC
                {
                mSTATIC(); 

                }
                break;
            case 88 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:703: STRICTFP
                {
                mSTRICTFP(); 

                }
                break;
            case 89 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:712: SUPER
                {
                mSUPER(); 

                }
                break;
            case 90 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:718: SWITCH
                {
                mSWITCH(); 

                }
                break;
            case 91 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:725: SYNCHRONIZED
                {
                mSYNCHRONIZED(); 

                }
                break;
            case 92 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:738: THIS
                {
                mTHIS(); 

                }
                break;
            case 93 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:743: THROW
                {
                mTHROW(); 

                }
                break;
            case 94 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:749: THROWS
                {
                mTHROWS(); 

                }
                break;
            case 95 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:756: TRANSIENT
                {
                mTRANSIENT(); 

                }
                break;
            case 96 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:766: TRUE
                {
                mTRUE(); 

                }
                break;
            case 97 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:771: TRY
                {
                mTRY(); 

                }
                break;
            case 98 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:775: VOID
                {
                mVOID(); 

                }
                break;
            case 99 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:780: VOLATILE
                {
                mVOLATILE(); 

                }
                break;
            case 100 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:789: WHILE
                {
                mWHILE(); 

                }
                break;
            case 101 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:795: HEX_LITERAL
                {
                mHEX_LITERAL(); 

                }
                break;
            case 102 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:807: DECIMAL_LITERAL
                {
                mDECIMAL_LITERAL(); 

                }
                break;
            case 103 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:823: OCTAL_LITERAL
                {
                mOCTAL_LITERAL(); 

                }
                break;
            case 104 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:837: FLOATING_POINT_LITERAL
                {
                mFLOATING_POINT_LITERAL(); 

                }
                break;
            case 105 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:860: CHARACTER_LITERAL
                {
                mCHARACTER_LITERAL(); 

                }
                break;
            case 106 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:878: STRING_LITERAL
                {
                mSTRING_LITERAL(); 

                }
                break;
            case 107 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:893: IDENT
                {
                mIDENT(); 

                }
                break;
            case 108 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:899: WS
                {
                mWS(); 

                }
                break;
            case 109 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:902: BLOCK_COMMENT
                {
                mBLOCK_COMMENT(); 

                }
                break;
            case 110 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:916: JAVADOC_COMMENT
                {
                mJAVADOC_COMMENT(); 

                }
                break;
            case 111 :
                // /home/langera/dev/freud/trunk/src/grammar/Java.g:1:932: LINE_COMMENT
                {
                mLINE_COMMENT(); 

                }
                break;

        }

    }


    protected DFA30 dfa30 = new DFA30(this);
    static final String DFA30_eotS =
        "\1\uffff\1\61\1\63\1\uffff\1\66\2\uffff\1\71\1\75\1\100\1\104\2"+
        "\uffff\1\107\1\111\1\114\1\uffff\1\116\6\uffff\1\120\1\122\17\55"+
        "\2\173\11\uffff\1\177\21\uffff\1\u0083\14\uffff\12\55\1\u0090\7"+
        "\55\1\u0098\23\55\1\uffff\1\u00b3\1\uffff\1\173\1\u00b5\6\uffff"+
        "\14\55\1\uffff\6\55\1\u00ca\1\uffff\2\55\1\u00cf\2\55\1\u00d2\20"+
        "\55\1\u00e3\3\55\3\uffff\1\u0080\1\uffff\4\55\1\u00eb\1\u00ec\1"+
        "\55\1\u00ee\4\55\1\u00f3\1\u00f4\4\55\1\uffff\4\55\1\uffff\1\u00fd"+
        "\1\55\1\uffff\1\u00ff\13\55\1\u010b\2\55\1\u010e\1\uffff\1\u010f"+
        "\5\55\1\u0115\2\uffff\1\u0116\1\uffff\1\u0117\3\55\2\uffff\1\55"+
        "\1\u011c\1\u011e\1\u011f\4\55\1\uffff\1\55\1\uffff\5\55\1\u012a"+
        "\2\55\1\u012d\2\55\1\uffff\1\u0131\1\55\2\uffff\1\55\1\u0134\1\55"+
        "\1\u0136\1\55\3\uffff\2\55\1\u013a\1\55\1\uffff\1\55\2\uffff\1\55"+
        "\1\u013e\2\55\1\u0141\3\55\1\u0145\1\u0146\1\uffff\1\u0147\1\55"+
        "\1\uffff\1\u0149\1\55\1\u014b\1\uffff\2\55\1\uffff\1\55\1\uffff"+
        "\1\u014f\1\55\1\u0151\1\uffff\1\u0152\1\u0153\1\55\1\uffff\2\55"+
        "\1\uffff\1\u0157\1\u0158\1\55\3\uffff\1\55\1\uffff\1\55\1\uffff"+
        "\2\55\1\u015e\1\uffff\1\u015f\3\uffff\3\55\2\uffff\1\55\1\u0164"+
        "\2\55\1\u0167\2\uffff\2\55\1\u016a\1\u016b\1\uffff\1\55\1\u016d"+
        "\1\uffff\1\u016e\1\u016f\2\uffff\1\55\3\uffff\1\55\1\u0172\1\uffff";
    static final String DFA30_eofS =
        "\u0173\uffff";
    static final String DFA30_minS =
        "\1\11\1\46\1\75\1\uffff\1\75\2\uffff\1\55\2\52\1\53\2\uffff\1\74"+
        "\2\75\1\uffff\1\75\6\uffff\2\75\1\142\1\157\1\141\1\145\1\154\1"+
        "\141\1\146\1\157\2\141\1\145\2\150\1\157\1\150\2\56\11\uffff\1\75"+
        "\6\uffff\1\0\12\uffff\1\75\14\uffff\2\163\1\157\1\145\1\164\1\163"+
        "\2\141\1\156\1\146\1\44\1\163\1\165\1\164\1\154\1\156\1\157\1\162"+
        "\1\44\1\160\1\163\1\156\1\164\1\167\1\154\1\143\1\151\1\142\1\164"+
        "\1\157\1\141\1\160\1\151\1\156\1\151\1\141\2\151\1\uffff\1\56\1"+
        "\uffff\1\56\1\75\3\uffff\1\0\2\uffff\1\164\1\145\1\154\1\141\2\145"+
        "\1\143\1\162\1\163\1\164\1\141\1\142\1\uffff\1\145\1\155\1\145\1"+
        "\163\2\141\1\44\1\uffff\1\154\1\164\1\44\1\147\1\151\1\44\1\154"+
        "\1\153\1\166\1\164\1\154\1\165\1\162\1\164\1\151\1\145\1\164\1\143"+
        "\1\163\1\157\1\156\1\145\1\44\1\144\1\141\1\154\3\uffff\1\0\1\uffff"+
        "\2\162\1\145\1\153\2\44\1\150\1\44\1\163\1\151\1\165\1\154\2\44"+
        "\1\156\1\145\1\154\1\164\1\uffff\1\145\1\162\1\141\1\162\1\uffff"+
        "\1\44\1\166\1\uffff\1\44\2\141\1\145\1\151\1\162\1\164\1\151\1\143"+
        "\1\162\1\143\1\150\1\44\1\167\1\163\1\44\1\uffff\1\44\1\164\1\145"+
        "\1\141\1\164\1\141\1\44\2\uffff\1\44\1\uffff\1\44\1\156\1\154\1"+
        "\145\2\uffff\1\144\3\44\1\155\1\164\1\156\1\146\1\uffff\1\145\1"+
        "\uffff\1\147\1\164\2\143\1\156\1\44\1\143\1\164\1\44\1\150\1\162"+
        "\1\uffff\1\44\1\151\2\uffff\1\151\1\44\1\143\1\44\1\156\3\uffff"+
        "\1\165\1\164\1\44\1\163\1\uffff\1\171\2\uffff\1\145\1\44\1\143\1"+
        "\141\1\44\2\145\1\164\2\44\1\uffff\1\44\1\146\1\uffff\1\44\1\157"+
        "\1\44\1\uffff\1\145\1\154\1\uffff\1\164\1\uffff\1\44\1\145\1\44"+
        "\1\uffff\2\44\1\156\1\uffff\1\145\1\143\1\uffff\2\44\1\145\3\uffff"+
        "\1\160\1\uffff\1\156\1\uffff\1\156\1\145\1\44\1\uffff\1\44\3\uffff"+
        "\1\164\1\157\1\145\2\uffff\1\144\1\44\1\151\1\164\1\44\2\uffff\1"+
        "\163\1\146\2\44\1\uffff\1\172\1\44\1\uffff\2\44\2\uffff\1\145\3"+
        "\uffff\1\144\1\44\1\uffff";
    static final String DFA30_maxS =
        "\1\ufaff\2\75\1\uffff\1\76\2\uffff\2\75\1\71\1\75\2\uffff\2\75\1"+
        "\174\1\uffff\1\75\6\uffff\2\75\1\163\1\171\2\157\1\170\1\157\1\156"+
        "\1\157\2\165\1\145\1\171\1\162\1\157\1\150\1\170\1\146\11\uffff"+
        "\1\76\6\uffff\1\uffff\12\uffff\1\75\14\uffff\2\163\1\157\1\145\2"+
        "\164\2\141\1\156\1\146\1\ufaff\1\163\1\165\1\164\1\154\1\156\1\157"+
        "\1\162\1\ufaff\1\160\1\164\1\156\1\164\1\167\1\154\1\143\1\157\1"+
        "\142\1\164\1\157\1\162\1\160\1\151\1\156\1\162\1\171\1\154\1\151"+
        "\1\uffff\1\146\1\uffff\1\146\1\75\3\uffff\1\uffff\2\uffff\1\164"+
        "\1\145\1\154\1\141\2\145\1\143\1\162\1\163\1\164\1\141\1\142\1\uffff"+
        "\1\145\1\155\1\145\1\163\2\141\1\ufaff\1\uffff\1\157\1\164\1\ufaff"+
        "\1\147\1\151\1\ufaff\1\154\1\153\1\166\1\164\1\154\1\165\1\162\1"+
        "\164\1\151\1\145\1\164\1\143\1\163\1\157\1\156\1\145\1\ufaff\1\144"+
        "\1\141\1\154\3\uffff\1\uffff\1\uffff\2\162\1\145\1\153\2\ufaff\1"+
        "\150\1\ufaff\1\163\1\151\1\165\1\154\2\ufaff\1\156\1\145\1\154\1"+
        "\164\1\uffff\1\145\1\162\1\141\1\162\1\uffff\1\ufaff\1\166\1\uffff"+
        "\1\ufaff\2\141\1\145\1\151\1\162\1\164\1\151\1\143\1\162\1\143\1"+
        "\150\1\ufaff\1\167\1\163\1\ufaff\1\uffff\1\ufaff\1\164\1\145\1\141"+
        "\1\164\1\141\1\ufaff\2\uffff\1\ufaff\1\uffff\1\ufaff\1\156\1\154"+
        "\1\145\2\uffff\1\144\3\ufaff\1\155\1\164\1\156\1\146\1\uffff\1\145"+
        "\1\uffff\1\147\1\164\2\143\1\156\1\ufaff\1\143\1\164\1\ufaff\1\150"+
        "\1\162\1\uffff\1\ufaff\1\151\2\uffff\1\151\1\ufaff\1\143\1\ufaff"+
        "\1\156\3\uffff\1\165\1\164\1\ufaff\1\163\1\uffff\1\171\2\uffff\1"+
        "\145\1\ufaff\1\143\1\141\1\ufaff\2\145\1\164\2\ufaff\1\uffff\1\ufaff"+
        "\1\146\1\uffff\1\ufaff\1\157\1\ufaff\1\uffff\1\145\1\154\1\uffff"+
        "\1\164\1\uffff\1\ufaff\1\145\1\ufaff\1\uffff\2\ufaff\1\156\1\uffff"+
        "\1\145\1\143\1\uffff\2\ufaff\1\145\3\uffff\1\160\1\uffff\1\156\1"+
        "\uffff\1\156\1\145\1\ufaff\1\uffff\1\ufaff\3\uffff\1\164\1\157\1"+
        "\145\2\uffff\1\144\1\ufaff\1\151\1\164\1\ufaff\2\uffff\1\163\1\146"+
        "\2\ufaff\1\uffff\1\172\1\ufaff\1\uffff\2\ufaff\2\uffff\1\145\3\uffff"+
        "\1\144\1\ufaff\1\uffff";
    static final String DFA30_acceptS =
        "\3\uffff\1\4\1\uffff\1\7\1\10\4\uffff\1\23\1\24\3\uffff\1\32\1\uffff"+
        "\1\37\1\45\1\46\1\47\1\50\1\51\23\uffff\1\151\1\152\1\153\1\154"+
        "\1\2\1\27\1\1\1\17\1\3\1\uffff\1\20\1\21\1\11\1\34\1\33\1\13\1\uffff"+
        "\1\157\1\12\1\15\1\16\1\14\1\150\1\22\1\44\1\43\1\25\1\uffff\1\26"+
        "\1\40\1\30\1\31\1\42\1\41\1\36\1\35\1\57\1\56\1\61\1\60\46\uffff"+
        "\1\145\1\uffff\1\146\2\uffff\1\55\1\54\1\155\1\uffff\1\53\1\52\14"+
        "\uffff\1\75\7\uffff\1\107\32\uffff\1\147\1\6\1\5\1\uffff\1\156\22"+
        "\uffff\1\106\4\uffff\1\114\2\uffff\1\117\20\uffff\1\141\7\uffff"+
        "\1\66\1\67\1\uffff\1\71\4\uffff\1\77\1\100\10\uffff\1\115\1\uffff"+
        "\1\120\13\uffff\1\134\2\uffff\1\140\1\142\5\uffff\1\65\1\70\1\72"+
        "\4\uffff\1\102\1\uffff\1\103\1\105\12\uffff\1\126\2\uffff\1\131"+
        "\3\uffff\1\135\2\uffff\1\144\1\uffff\1\63\3\uffff\1\76\3\uffff\1"+
        "\113\2\uffff\1\116\3\uffff\1\124\1\125\1\127\1\uffff\1\132\1\uffff"+
        "\1\136\3\uffff\1\64\1\uffff\1\74\1\101\1\104\3\uffff\1\121\1\122"+
        "\5\uffff\1\62\1\73\4\uffff\1\130\2\uffff\1\143\2\uffff\1\112\1\123"+
        "\1\uffff\1\137\1\110\1\111\2\uffff\1\133";
    static final String DFA30_specialS =
        "\73\uffff\1\2\105\uffff\1\0\64\uffff\1\1\u00bc\uffff}>";
    static final String[] DFA30_transitionS = {
            "\2\56\1\uffff\2\56\22\uffff\1\56\1\16\1\54\1\uffff\1\55\1\21"+
            "\1\1\1\53\1\20\1\26\1\30\1\12\1\6\1\7\1\11\1\10\1\51\11\52\1"+
            "\5\1\27\1\15\1\2\1\4\1\23\1\3\32\55\1\13\1\uffff\1\24\1\31\1"+
            "\55\1\uffff\1\32\1\33\1\34\1\35\1\36\1\37\2\55\1\40\2\55\1\41"+
            "\1\55\1\42\1\55\1\43\1\55\1\44\1\45\1\46\1\55\1\47\1\50\3\55"+
            "\1\14\1\17\1\25\1\22\101\uffff\27\55\1\uffff\37\55\1\uffff\u1f08"+
            "\55\u1040\uffff\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e"+
            "\55\u10d2\uffff\u5200\55\u5900\uffff\u0200\55",
            "\1\60\26\uffff\1\57",
            "\1\62",
            "",
            "\1\65\1\64",
            "",
            "",
            "\1\67\17\uffff\1\70",
            "\1\73\4\uffff\1\74\15\uffff\1\72",
            "\1\76\3\uffff\1\77\1\uffff\12\101",
            "\1\102\21\uffff\1\103",
            "",
            "",
            "\1\106\1\105",
            "\1\110",
            "\1\113\76\uffff\1\112",
            "",
            "\1\115",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\117",
            "\1\121",
            "\1\123\20\uffff\1\124",
            "\1\125\2\uffff\1\126\6\uffff\1\127",
            "\1\130\6\uffff\1\131\3\uffff\1\132\2\uffff\1\133",
            "\1\134\11\uffff\1\135",
            "\1\136\1\uffff\1\137\11\uffff\1\140",
            "\1\141\7\uffff\1\142\2\uffff\1\143\2\uffff\1\144",
            "\1\145\6\uffff\1\146\1\147",
            "\1\150",
            "\1\151\3\uffff\1\152\17\uffff\1\153",
            "\1\154\20\uffff\1\155\2\uffff\1\156",
            "\1\157",
            "\1\160\13\uffff\1\161\1\162\1\uffff\1\163\1\uffff\1\164",
            "\1\165\11\uffff\1\166",
            "\1\167",
            "\1\170",
            "\1\101\1\uffff\10\172\2\101\12\uffff\3\101\21\uffff\1\171\13"+
            "\uffff\3\101\21\uffff\1\171",
            "\1\101\1\uffff\12\174\12\uffff\3\101\35\uffff\3\101",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\176\1\175",
            "",
            "",
            "",
            "",
            "",
            "",
            "\52\u0080\1\u0081\uffd5\u0080",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\u0082",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\u0084",
            "\1\u0085",
            "\1\u0086",
            "\1\u0087",
            "\1\u0088",
            "\1\u0089\1\u008a",
            "\1\u008b",
            "\1\u008c",
            "\1\u008d",
            "\1\u008e",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\24"+
            "\55\1\u008f\5\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08"+
            "\55\u1040\uffff\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e"+
            "\55\u10d2\uffff\u5200\55\u5900\uffff\u0200\55",
            "\1\u0091",
            "\1\u0092",
            "\1\u0093",
            "\1\u0094",
            "\1\u0095",
            "\1\u0096",
            "\1\u0097",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u0099",
            "\1\u009a\1\u009b",
            "\1\u009c",
            "\1\u009d",
            "\1\u009e",
            "\1\u009f",
            "\1\u00a0",
            "\1\u00a1\5\uffff\1\u00a2",
            "\1\u00a3",
            "\1\u00a4",
            "\1\u00a5",
            "\1\u00a6\20\uffff\1\u00a7",
            "\1\u00a8",
            "\1\u00a9",
            "\1\u00aa",
            "\1\u00ab\10\uffff\1\u00ac",
            "\1\u00ad\23\uffff\1\u00ae\3\uffff\1\u00af",
            "\1\u00b0\2\uffff\1\u00b1",
            "\1\u00b2",
            "",
            "\1\101\1\uffff\10\172\2\101\12\uffff\3\101\35\uffff\3\101",
            "",
            "\1\101\1\uffff\12\174\12\uffff\3\101\35\uffff\3\101",
            "\1\u00b4",
            "",
            "",
            "",
            "\57\u00b7\1\u00b6\uffd0\u00b7",
            "",
            "",
            "\1\u00b8",
            "\1\u00b9",
            "\1\u00ba",
            "\1\u00bb",
            "\1\u00bc",
            "\1\u00bd",
            "\1\u00be",
            "\1\u00bf",
            "\1\u00c0",
            "\1\u00c1",
            "\1\u00c2",
            "\1\u00c3",
            "",
            "\1\u00c4",
            "\1\u00c5",
            "\1\u00c6",
            "\1\u00c7",
            "\1\u00c8",
            "\1\u00c9",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "",
            "\1\u00cb\2\uffff\1\u00cc",
            "\1\u00cd",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\4\55"+
            "\1\u00ce\25\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55"+
            "\u1040\uffff\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e"+
            "\55\u10d2\uffff\u5200\55\u5900\uffff\u0200\55",
            "\1\u00d0",
            "\1\u00d1",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u00d3",
            "\1\u00d4",
            "\1\u00d5",
            "\1\u00d6",
            "\1\u00d7",
            "\1\u00d8",
            "\1\u00d9",
            "\1\u00da",
            "\1\u00db",
            "\1\u00dc",
            "\1\u00dd",
            "\1\u00de",
            "\1\u00df",
            "\1\u00e0",
            "\1\u00e1",
            "\1\u00e2",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u00e4",
            "\1\u00e5",
            "\1\u00e6",
            "",
            "",
            "",
            "\0\u00b7",
            "",
            "\1\u00e7",
            "\1\u00e8",
            "\1\u00e9",
            "\1\u00ea",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u00ed",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u00ef",
            "\1\u00f0",
            "\1\u00f1",
            "\1\u00f2",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u00f5",
            "\1\u00f6",
            "\1\u00f7",
            "\1\u00f8",
            "",
            "\1\u00f9",
            "\1\u00fa",
            "\1\u00fb",
            "\1\u00fc",
            "",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u00fe",
            "",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u0100",
            "\1\u0101",
            "\1\u0102",
            "\1\u0103",
            "\1\u0104",
            "\1\u0105",
            "\1\u0106",
            "\1\u0107",
            "\1\u0108",
            "\1\u0109",
            "\1\u010a",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u010c",
            "\1\u010d",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u0110",
            "\1\u0111",
            "\1\u0112",
            "\1\u0113",
            "\1\u0114",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "",
            "",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u0118",
            "\1\u0119",
            "\1\u011a",
            "",
            "",
            "\1\u011b",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\13"+
            "\55\1\u011d\16\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08"+
            "\55\u1040\uffff\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e"+
            "\55\u10d2\uffff\u5200\55\u5900\uffff\u0200\55",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u0120",
            "\1\u0121",
            "\1\u0122",
            "\1\u0123",
            "",
            "\1\u0124",
            "",
            "\1\u0125",
            "\1\u0126",
            "\1\u0127",
            "\1\u0128",
            "\1\u0129",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u012b",
            "\1\u012c",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u012e",
            "\1\u012f",
            "",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\22"+
            "\55\1\u0130\7\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08"+
            "\55\u1040\uffff\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e"+
            "\55\u10d2\uffff\u5200\55\u5900\uffff\u0200\55",
            "\1\u0132",
            "",
            "",
            "\1\u0133",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u0135",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u0137",
            "",
            "",
            "",
            "\1\u0138",
            "\1\u0139",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u013b",
            "",
            "\1\u013c",
            "",
            "",
            "\1\u013d",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u013f",
            "\1\u0140",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u0142",
            "\1\u0143",
            "\1\u0144",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u0148",
            "",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u014a",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "",
            "\1\u014c",
            "\1\u014d",
            "",
            "\1\u014e",
            "",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u0150",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u0154",
            "",
            "\1\u0155",
            "\1\u0156",
            "",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u0159",
            "",
            "",
            "",
            "\1\u015a",
            "",
            "\1\u015b",
            "",
            "\1\u015c",
            "\1\u015d",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "",
            "",
            "",
            "\1\u0160",
            "\1\u0161",
            "\1\u0162",
            "",
            "",
            "\1\u0163",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u0165",
            "\1\u0166",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "",
            "",
            "\1\u0168",
            "\1\u0169",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "",
            "\1\u016c",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "",
            "",
            "\1\u0170",
            "",
            "",
            "",
            "\1\u0171",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            ""
    };

    static final short[] DFA30_eot = DFA.unpackEncodedString(DFA30_eotS);
    static final short[] DFA30_eof = DFA.unpackEncodedString(DFA30_eofS);
    static final char[] DFA30_min = DFA.unpackEncodedStringToUnsignedChars(DFA30_minS);
    static final char[] DFA30_max = DFA.unpackEncodedStringToUnsignedChars(DFA30_maxS);
    static final short[] DFA30_accept = DFA.unpackEncodedString(DFA30_acceptS);
    static final short[] DFA30_special = DFA.unpackEncodedString(DFA30_specialS);
    static final short[][] DFA30_transition;

    static {
        int numStates = DFA30_transitionS.length;
        DFA30_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA30_transition[i] = DFA.unpackEncodedString(DFA30_transitionS[i]);
        }
    }

    class DFA30 extends DFA {

        public DFA30(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 30;
            this.eot = DFA30_eot;
            this.eof = DFA30_eof;
            this.min = DFA30_min;
            this.max = DFA30_max;
            this.accept = DFA30_accept;
            this.special = DFA30_special;
            this.transition = DFA30_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( AND | AND_ASSIGN | ASSIGN | AT | BIT_SHIFT_RIGHT | BIT_SHIFT_RIGHT_ASSIGN | COLON | COMMA | DEC | DIV | DIV_ASSIGN | DOT | DOTSTAR | ELLIPSIS | EQUAL | GREATER_OR_EQUAL | GREATER_THAN | INC | LBRACK | LCURLY | LESS_OR_EQUAL | LESS_THAN | LOGICAL_AND | LOGICAL_NOT | LOGICAL_OR | LPAREN | MINUS | MINUS_ASSIGN | MOD | MOD_ASSIGN | NOT | NOT_EQUAL | OR | OR_ASSIGN | PLUS | PLUS_ASSIGN | QUESTION | RBRACK | RCURLY | RPAREN | SEMI | SHIFT_LEFT | SHIFT_LEFT_ASSIGN | SHIFT_RIGHT | SHIFT_RIGHT_ASSIGN | STAR | STAR_ASSIGN | XOR | XOR_ASSIGN | ABSTRACT | ASSERT | BOOLEAN | BREAK | BYTE | CASE | CATCH | CHAR | CLASS | CONTINUE | DEFAULT | DO | DOUBLE | ELSE | ENUM | EXTENDS | FALSE | FINAL | FINALLY | FLOAT | FOR | IF | IMPLEMENTS | INSTANCEOF | INTERFACE | IMPORT | INT | LONG | NATIVE | NEW | NULL | PACKAGE | PRIVATE | PROTECTED | PUBLIC | RETURN | SHORT | STATIC | STRICTFP | SUPER | SWITCH | SYNCHRONIZED | THIS | THROW | THROWS | TRANSIENT | TRUE | TRY | VOID | VOLATILE | WHILE | HEX_LITERAL | DECIMAL_LITERAL | OCTAL_LITERAL | FLOATING_POINT_LITERAL | CHARACTER_LITERAL | STRING_LITERAL | IDENT | WS | BLOCK_COMMENT | JAVADOC_COMMENT | LINE_COMMENT );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA30_129 = input.LA(1);

                        s = -1;
                        if ( (LA30_129=='/') ) {s = 182;}

                        else if ( ((LA30_129>='\u0000' && LA30_129<='.')||(LA30_129>='0' && LA30_129<='\uFFFF')) ) {s = 183;}

                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA30_182 = input.LA(1);

                        s = -1;
                        if ( ((LA30_182>='\u0000' && LA30_182<='\uFFFF')) ) {s = 183;}

                        else s = 128;

                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA30_59 = input.LA(1);

                        s = -1;
                        if ( ((LA30_59>='\u0000' && LA30_59<=')')||(LA30_59>='+' && LA30_59<='\uFFFF')) ) {s = 128;}

                        else if ( (LA30_59=='*') ) {s = 129;}

                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 30, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

}