// $ANTLR 3.2 Sep 23, 2009 12:02:23 /home/langera/dev/freud/src/grammar/Css.g 2010-07-23 21:37:10

package org.langera.freud.optional.css.parser;


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

public class CssLexer extends Lexer
{
    public static final int FUNCTION = 17;
    public static final int CLASS = 20;
    public static final int ATTRIB = 8;
    public static final int T__29 = 29;
    public static final int T__28 = 28;
    public static final int T__62 = 62;
    public static final int T__27 = 27;
    public static final int T__26 = 26;
    public static final int HASVALUE = 12;
    public static final int PSEUDO = 14;
    public static final int NEST = 6;
    public static final int T__61 = 61;
    public static final int ID = 19;
    public static final int T__60 = 60;
    public static final int ATTRIBEQUAL = 11;
    public static final int EOF = -1;
    public static final int T__55 = 55;
    public static final int T__56 = 56;
    public static final int T__57 = 57;
    public static final int T__58 = 58;
    public static final int IMPORT = 4;
    public static final int T__51 = 51;
    public static final int T__52 = 52;
    public static final int T__53 = 53;
    public static final int T__54 = 54;
    public static final int ADJACENT_SIBLING = 10;
    public static final int T__59 = 59;
    public static final int IDENT = 22;
    public static final int COLOUR = 16;
    public static final int COMMENT = 24;
    public static final int T__50 = 50;
    public static final int CHILD = 9;
    public static final int T__42 = 42;
    public static final int T__43 = 43;
    public static final int T__40 = 40;
    public static final int T__41 = 41;
    public static final int T__46 = 46;
    public static final int T__47 = 47;
    public static final int RULE = 7;
    public static final int T__44 = 44;
    public static final int T__45 = 45;
    public static final int BEGINSWITH = 13;
    public static final int T__48 = 48;
    public static final int T__49 = 49;
    public static final int TAG = 18;
    public static final int NESTED = 5;
    public static final int T__30 = 30;
    public static final int T__31 = 31;
    public static final int T__32 = 32;
    public static final int T__33 = 33;
    public static final int WS = 25;
    public static final int T__34 = 34;
    public static final int T__35 = 35;
    public static final int T__36 = 36;
    public static final int T__37 = 37;
    public static final int PROPERTY = 15;
    public static final int T__38 = 38;
    public static final int T__39 = 39;
    public static final int SL_COMMENT = 23;
    public static final int UNIVERSAL = 21;

    // delegates
    // delegators

    public CssLexer() {;}

    public CssLexer(CharStream input)
    {
        this(input, new RecognizerSharedState());
    }

    public CssLexer(CharStream input, RecognizerSharedState state)
    {
        super(input, state);

    }

    public String getGrammarFileName() { return "/home/langera/dev/freud/src/grammar/Css.g"; }

    // $ANTLR start "T__26"
    public final void mT__26() throws RecognitionException
    {
        try
        {
            int _type = T__26;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/src/grammar/Css.g:7:7: ( '@import' )
            // /home/langera/dev/freud/src/grammar/Css.g:7:9: '@import'
            {
                match("@import");


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally
        {
        }
    }
    // $ANTLR end "T__26"

    // $ANTLR start "T__27"
    public final void mT__27() throws RecognitionException
    {
        try
        {
            int _type = T__27;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/src/grammar/Css.g:8:7: ( '@include' )
            // /home/langera/dev/freud/src/grammar/Css.g:8:9: '@include'
            {
                match("@include");


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally
        {
        }
    }
    // $ANTLR end "T__27"

    // $ANTLR start "T__28"
    public final void mT__28() throws RecognitionException
    {
        try
        {
            int _type = T__28;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/src/grammar/Css.g:9:7: ( '@' )
            // /home/langera/dev/freud/src/grammar/Css.g:9:9: '@'
            {
                match('@');

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally
        {
        }
    }
    // $ANTLR end "T__28"

    // $ANTLR start "T__29"
    public final void mT__29() throws RecognitionException
    {
        try
        {
            int _type = T__29;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/src/grammar/Css.g:10:7: ( '{' )
            // /home/langera/dev/freud/src/grammar/Css.g:10:9: '{'
            {
                match('{');

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally
        {
        }
    }
    // $ANTLR end "T__29"

    // $ANTLR start "T__30"
    public final void mT__30() throws RecognitionException
    {
        try
        {
            int _type = T__30;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/src/grammar/Css.g:11:7: ( '}' )
            // /home/langera/dev/freud/src/grammar/Css.g:11:9: '}'
            {
                match('}');

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally
        {
        }
    }
    // $ANTLR end "T__30"

    // $ANTLR start "T__31"
    public final void mT__31() throws RecognitionException
    {
        try
        {
            int _type = T__31;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/src/grammar/Css.g:12:7: ( ',' )
            // /home/langera/dev/freud/src/grammar/Css.g:12:9: ','
            {
                match(',');

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally
        {
        }
    }
    // $ANTLR end "T__31"

    // $ANTLR start "T__32"
    public final void mT__32() throws RecognitionException
    {
        try
        {
            int _type = T__32;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/src/grammar/Css.g:13:7: ( '>' )
            // /home/langera/dev/freud/src/grammar/Css.g:13:9: '>'
            {
                match('>');

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally
        {
        }
    }
    // $ANTLR end "T__32"

    // $ANTLR start "T__33"
    public final void mT__33() throws RecognitionException
    {
        try
        {
            int _type = T__33;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/src/grammar/Css.g:14:7: ( '+' )
            // /home/langera/dev/freud/src/grammar/Css.g:14:9: '+'
            {
                match('+');

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally
        {
        }
    }
    // $ANTLR end "T__33"

    // $ANTLR start "T__34"
    public final void mT__34() throws RecognitionException
    {
        try
        {
            int _type = T__34;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/src/grammar/Css.g:15:7: ( ';' )
            // /home/langera/dev/freud/src/grammar/Css.g:15:9: ';'
            {
                match(';');

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally
        {
        }
    }
    // $ANTLR end "T__34"

    // $ANTLR start "T__35"
    public final void mT__35() throws RecognitionException
    {
        try
        {
            int _type = T__35;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/src/grammar/Css.g:16:7: ( '*' )
            // /home/langera/dev/freud/src/grammar/Css.g:16:9: '*'
            {
                match('*');

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally
        {
        }
    }
    // $ANTLR end "T__35"

    // $ANTLR start "T__36"
    public final void mT__36() throws RecognitionException
    {
        try
        {
            int _type = T__36;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/src/grammar/Css.g:17:7: ( '#' )
            // /home/langera/dev/freud/src/grammar/Css.g:17:9: '#'
            {
                match('#');

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally
        {
        }
    }
    // $ANTLR end "T__36"

    // $ANTLR start "T__37"
    public final void mT__37() throws RecognitionException
    {
        try
        {
            int _type = T__37;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/src/grammar/Css.g:18:7: ( '.' )
            // /home/langera/dev/freud/src/grammar/Css.g:18:9: '.'
            {
                match('.');

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally
        {
        }
    }
    // $ANTLR end "T__37"

    // $ANTLR start "T__38"
    public final void mT__38() throws RecognitionException
    {
        try
        {
            int _type = T__38;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/src/grammar/Css.g:19:7: ( ':' )
            // /home/langera/dev/freud/src/grammar/Css.g:19:9: ':'
            {
                match(':');

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally
        {
        }
    }
    // $ANTLR end "T__38"

    // $ANTLR start "T__39"
    public final void mT__39() throws RecognitionException
    {
        try
        {
            int _type = T__39;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/src/grammar/Css.g:20:7: ( '::' )
            // /home/langera/dev/freud/src/grammar/Css.g:20:9: '::'
            {
                match("::");


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally
        {
        }
    }
    // $ANTLR end "T__39"

    // $ANTLR start "T__40"
    public final void mT__40() throws RecognitionException
    {
        try
        {
            int _type = T__40;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/src/grammar/Css.g:21:7: ( '[' )
            // /home/langera/dev/freud/src/grammar/Css.g:21:9: '['
            {
                match('[');

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally
        {
        }
    }
    // $ANTLR end "T__40"

    // $ANTLR start "T__41"
    public final void mT__41() throws RecognitionException
    {
        try
        {
            int _type = T__41;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/src/grammar/Css.g:22:7: ( ']' )
            // /home/langera/dev/freud/src/grammar/Css.g:22:9: ']'
            {
                match(']');

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally
        {
        }
    }
    // $ANTLR end "T__41"

    // $ANTLR start "T__42"
    public final void mT__42() throws RecognitionException
    {
        try
        {
            int _type = T__42;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/src/grammar/Css.g:23:7: ( '=' )
            // /home/langera/dev/freud/src/grammar/Css.g:23:9: '='
            {
                match('=');

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally
        {
        }
    }
    // $ANTLR end "T__42"

    // $ANTLR start "T__43"
    public final void mT__43() throws RecognitionException
    {
        try
        {
            int _type = T__43;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/src/grammar/Css.g:24:7: ( '~=' )
            // /home/langera/dev/freud/src/grammar/Css.g:24:9: '~='
            {
                match("~=");


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally
        {
        }
    }
    // $ANTLR end "T__43"

    // $ANTLR start "T__44"
    public final void mT__44() throws RecognitionException
    {
        try
        {
            int _type = T__44;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/src/grammar/Css.g:25:7: ( '|=' )
            // /home/langera/dev/freud/src/grammar/Css.g:25:9: '|='
            {
                match("|=");


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally
        {
        }
    }
    // $ANTLR end "T__44"

    // $ANTLR start "T__45"
    public final void mT__45() throws RecognitionException
    {
        try
        {
            int _type = T__45;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/src/grammar/Css.g:26:7: ( '%' )
            // /home/langera/dev/freud/src/grammar/Css.g:26:9: '%'
            {
                match('%');

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally
        {
        }
    }
    // $ANTLR end "T__45"

    // $ANTLR start "T__46"
    public final void mT__46() throws RecognitionException
    {
        try
        {
            int _type = T__46;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/src/grammar/Css.g:27:7: ( 'px' )
            // /home/langera/dev/freud/src/grammar/Css.g:27:9: 'px'
            {
                match("px");


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally
        {
        }
    }
    // $ANTLR end "T__46"

    // $ANTLR start "T__47"
    public final void mT__47() throws RecognitionException
    {
        try
        {
            int _type = T__47;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/src/grammar/Css.g:28:7: ( 'cm' )
            // /home/langera/dev/freud/src/grammar/Css.g:28:9: 'cm'
            {
                match("cm");


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally
        {
        }
    }
    // $ANTLR end "T__47"

    // $ANTLR start "T__48"
    public final void mT__48() throws RecognitionException
    {
        try
        {
            int _type = T__48;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/src/grammar/Css.g:29:7: ( 'mm' )
            // /home/langera/dev/freud/src/grammar/Css.g:29:9: 'mm'
            {
                match("mm");


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally
        {
        }
    }
    // $ANTLR end "T__48"

    // $ANTLR start "T__49"
    public final void mT__49() throws RecognitionException
    {
        try
        {
            int _type = T__49;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/src/grammar/Css.g:30:7: ( 'in' )
            // /home/langera/dev/freud/src/grammar/Css.g:30:9: 'in'
            {
                match("in");


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally
        {
        }
    }
    // $ANTLR end "T__49"

    // $ANTLR start "T__50"
    public final void mT__50() throws RecognitionException
    {
        try
        {
            int _type = T__50;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/src/grammar/Css.g:31:7: ( 'pt' )
            // /home/langera/dev/freud/src/grammar/Css.g:31:9: 'pt'
            {
                match("pt");


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally
        {
        }
    }
    // $ANTLR end "T__50"

    // $ANTLR start "T__51"
    public final void mT__51() throws RecognitionException
    {
        try
        {
            int _type = T__51;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/src/grammar/Css.g:32:7: ( 'pc' )
            // /home/langera/dev/freud/src/grammar/Css.g:32:9: 'pc'
            {
                match("pc");


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally
        {
        }
    }
    // $ANTLR end "T__51"

    // $ANTLR start "T__52"
    public final void mT__52() throws RecognitionException
    {
        try
        {
            int _type = T__52;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/src/grammar/Css.g:33:7: ( 'em' )
            // /home/langera/dev/freud/src/grammar/Css.g:33:9: 'em'
            {
                match("em");


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally
        {
        }
    }
    // $ANTLR end "T__52"

    // $ANTLR start "T__53"
    public final void mT__53() throws RecognitionException
    {
        try
        {
            int _type = T__53;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/src/grammar/Css.g:34:7: ( 'ex' )
            // /home/langera/dev/freud/src/grammar/Css.g:34:9: 'ex'
            {
                match("ex");


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally
        {
        }
    }
    // $ANTLR end "T__53"

    // $ANTLR start "T__54"
    public final void mT__54() throws RecognitionException
    {
        try
        {
            int _type = T__54;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/src/grammar/Css.g:35:7: ( 'deg' )
            // /home/langera/dev/freud/src/grammar/Css.g:35:9: 'deg'
            {
                match("deg");


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally
        {
        }
    }
    // $ANTLR end "T__54"

    // $ANTLR start "T__55"
    public final void mT__55() throws RecognitionException
    {
        try
        {
            int _type = T__55;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/src/grammar/Css.g:36:7: ( 'rad' )
            // /home/langera/dev/freud/src/grammar/Css.g:36:9: 'rad'
            {
                match("rad");


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally
        {
        }
    }
    // $ANTLR end "T__55"

    // $ANTLR start "T__56"
    public final void mT__56() throws RecognitionException
    {
        try
        {
            int _type = T__56;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/src/grammar/Css.g:37:7: ( 'grad' )
            // /home/langera/dev/freud/src/grammar/Css.g:37:9: 'grad'
            {
                match("grad");


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally
        {
        }
    }
    // $ANTLR end "T__56"

    // $ANTLR start "T__57"
    public final void mT__57() throws RecognitionException
    {
        try
        {
            int _type = T__57;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/src/grammar/Css.g:38:7: ( 'ms' )
            // /home/langera/dev/freud/src/grammar/Css.g:38:9: 'ms'
            {
                match("ms");


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally
        {
        }
    }
    // $ANTLR end "T__57"

    // $ANTLR start "T__58"
    public final void mT__58() throws RecognitionException
    {
        try
        {
            int _type = T__58;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/src/grammar/Css.g:39:7: ( 's' )
            // /home/langera/dev/freud/src/grammar/Css.g:39:9: 's'
            {
                match('s');

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally
        {
        }
    }
    // $ANTLR end "T__58"

    // $ANTLR start "T__59"
    public final void mT__59() throws RecognitionException
    {
        try
        {
            int _type = T__59;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/src/grammar/Css.g:40:7: ( 'hz' )
            // /home/langera/dev/freud/src/grammar/Css.g:40:9: 'hz'
            {
                match("hz");


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally
        {
        }
    }
    // $ANTLR end "T__59"

    // $ANTLR start "T__60"
    public final void mT__60() throws RecognitionException
    {
        try
        {
            int _type = T__60;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/src/grammar/Css.g:41:7: ( 'khz' )
            // /home/langera/dev/freud/src/grammar/Css.g:41:9: 'khz'
            {
                match("khz");


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally
        {
        }
    }
    // $ANTLR end "T__60"

    // $ANTLR start "T__61"
    public final void mT__61() throws RecognitionException
    {
        try
        {
            int _type = T__61;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/src/grammar/Css.g:42:7: ( '(' )
            // /home/langera/dev/freud/src/grammar/Css.g:42:9: '('
            {
                match('(');

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally
        {
        }
    }
    // $ANTLR end "T__61"

    // $ANTLR start "T__62"
    public final void mT__62() throws RecognitionException
    {
        try
        {
            int _type = T__62;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/src/grammar/Css.g:43:7: ( ')' )
            // /home/langera/dev/freud/src/grammar/Css.g:43:9: ')'
            {
                match(')');

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally
        {
        }
    }
    // $ANTLR end "T__62"

    // $ANTLR start "IDENT"
    public final void mIDENT() throws RecognitionException
    {
        try
        {
            int _type = IDENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/src/grammar/Css.g:128:2: ( ( '_' | '-' | 'a' .. 'z' | 'A' .. 'Z' | '\\u0100' .. '\\ufffe' | '0' .. '9' | '\"' | '\\'' | '/' | '=' | '!' ) ( options {greedy=true; } : ( '_' | '-' | 'a' .. 'z' | 'A' .. 'Z' | '\\u0100' .. '\\ufffe' | '0' .. '9' | '\"' | '\\'' | '/' | '.' | '=' ) )* )
            // /home/langera/dev/freud/src/grammar/Css.g:128:4: ( '_' | '-' | 'a' .. 'z' | 'A' .. 'Z' | '\\u0100' .. '\\ufffe' | '0' .. '9' | '\"' | '\\'' | '/' | '=' | '!' ) ( options {greedy=true; } : ( '_' | '-' | 'a' .. 'z' | 'A' .. 'Z' | '\\u0100' .. '\\ufffe' | '0' .. '9' | '\"' | '\\'' | '/' | '.' | '=' ) )*
            {
                if ((input.LA(1) >= '!' && input.LA(1) <= '\"') || input.LA(1) == '\'' || input.LA(1) == '-' || (input.LA(1) >= '/' && input.LA(1) <= '9') || input.LA(1) == '=' || (input.LA(1) >= 'A' && input.LA(1) <= 'Z') || input.LA(1) == '_' || (input.LA(1) >= 'a' && input.LA(1) <= 'z') || (input.LA(1) >= '\u0100' && input.LA(1) <= '\uFFFE'))
                {
                    input.consume();

                }
                else
                {
                    MismatchedSetException mse = new MismatchedSetException(null, input);
                    recover(mse);
                    throw mse;
                }

                // /home/langera/dev/freud/src/grammar/Css.g:129:4: ( options {greedy=true; } : ( '_' | '-' | 'a' .. 'z' | 'A' .. 'Z' | '\\u0100' .. '\\ufffe' | '0' .. '9' | '\"' | '\\'' | '/' | '.' | '=' ) )*
                loop1:
                do
                {
                    int alt1 = 2;
                    int LA1_0 = input.LA(1);

                    if ((LA1_0 == '\"' || LA1_0 == '\'' || (LA1_0 >= '-' && LA1_0 <= '9') || LA1_0 == '=' || (LA1_0 >= 'A' && LA1_0 <= 'Z') || LA1_0 == '_' || (LA1_0 >= 'a' && LA1_0 <= 'z') || (LA1_0 >= '\u0100' && LA1_0 <= '\uFFFE')))
                    {
                        alt1 = 1;
                    }


                    switch (alt1)
                    {
                        case 1:
                            // /home/langera/dev/freud/src/grammar/Css.g:129:34: ( '_' | '-' | 'a' .. 'z' | 'A' .. 'Z' | '\\u0100' .. '\\ufffe' | '0' .. '9' | '\"' | '\\'' | '/' | '.' | '=' )
                        {
                            if (input.LA(1) == '\"' || input.LA(1) == '\'' || (input.LA(1) >= '-' && input.LA(1) <= '9') || input.LA(1) == '=' || (input.LA(1) >= 'A' && input.LA(1) <= 'Z') || input.LA(1) == '_' || (input.LA(1) >= 'a' && input.LA(1) <= 'z') || (input.LA(1) >= '\u0100' && input.LA(1) <= '\uFFFE'))
                            {
                                input.consume();

                            }
                            else
                            {
                                MismatchedSetException mse = new MismatchedSetException(null, input);
                                recover(mse);
                                throw mse;
                            }


                        }
                        break;

                        default:
                            break loop1;
                    }
                }
                while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally
        {
        }
    }
    // $ANTLR end "IDENT"

    // $ANTLR start "SL_COMMENT"
    public final void mSL_COMMENT() throws RecognitionException
    {
        try
        {
            int _type = SL_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/src/grammar/Css.g:135:2: ( '//' (~ ( '\\n' | '\\r' ) )* ( '\\n' | '\\r' ( '\\n' )? ) )
            // /home/langera/dev/freud/src/grammar/Css.g:135:4: '//' (~ ( '\\n' | '\\r' ) )* ( '\\n' | '\\r' ( '\\n' )? )
            {
                match("//");

                // /home/langera/dev/freud/src/grammar/Css.g:136:3: (~ ( '\\n' | '\\r' ) )*
                loop2:
                do
                {
                    int alt2 = 2;
                    int LA2_0 = input.LA(1);

                    if (((LA2_0 >= '\u0000' && LA2_0 <= '\t') || (LA2_0 >= '\u000B' && LA2_0 <= '\f') || (LA2_0 >= '\u000E' && LA2_0 <= '\uFFFF')))
                    {
                        alt2 = 1;
                    }


                    switch (alt2)
                    {
                        case 1:
                            // /home/langera/dev/freud/src/grammar/Css.g:136:4: ~ ( '\\n' | '\\r' )
                        {
                            if ((input.LA(1) >= '\u0000' && input.LA(1) <= '\t') || (input.LA(1) >= '\u000B' && input.LA(1) <= '\f') || (input.LA(1) >= '\u000E' && input.LA(1) <= '\uFFFF'))
                            {
                                input.consume();

                            }
                            else
                            {
                                MismatchedSetException mse = new MismatchedSetException(null, input);
                                recover(mse);
                                throw mse;
                            }


                        }
                        break;

                        default:
                            break loop2;
                    }
                }
                while (true);

                // /home/langera/dev/freud/src/grammar/Css.g:136:19: ( '\\n' | '\\r' ( '\\n' )? )
                int alt4 = 2;
                int LA4_0 = input.LA(1);

                if ((LA4_0 == '\n'))
                {
                    alt4 = 1;
                }
                else if ((LA4_0 == '\r'))
                {
                    alt4 = 2;
                }
                else
                {
                    NoViableAltException nvae =
                            new NoViableAltException("", 4, 0, input);

                    throw nvae;
                }
                switch (alt4)
                {
                    case 1:
                        // /home/langera/dev/freud/src/grammar/Css.g:136:20: '\\n'
                    {
                        match('\n');

                    }
                    break;
                    case 2:
                        // /home/langera/dev/freud/src/grammar/Css.g:136:25: '\\r' ( '\\n' )?
                    {
                        match('\r');
                        // /home/langera/dev/freud/src/grammar/Css.g:136:29: ( '\\n' )?
                        int alt3 = 2;
                        int LA3_0 = input.LA(1);

                        if ((LA3_0 == '\n'))
                        {
                            alt3 = 1;
                        }
                        switch (alt3)
                        {
                            case 1:
                                // /home/langera/dev/freud/src/grammar/Css.g:136:30: '\\n'
                            {
                                match('\n');

                            }
                            break;

                        }


                    }
                    break;

                }

                _channel = HIDDEN;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally
        {
        }
    }
    // $ANTLR end "SL_COMMENT"

    // $ANTLR start "COMMENT"
    public final void mCOMMENT() throws RecognitionException
    {
        try
        {
            int _type = COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/src/grammar/Css.g:142:2: ( '/*' ( . )* '*/' )
            // /home/langera/dev/freud/src/grammar/Css.g:142:4: '/*' ( . )* '*/'
            {
                match("/*");

                // /home/langera/dev/freud/src/grammar/Css.g:142:9: ( . )*
                loop5:
                do
                {
                    int alt5 = 2;
                    int LA5_0 = input.LA(1);

                    if ((LA5_0 == '*'))
                    {
                        int LA5_1 = input.LA(2);

                        if ((LA5_1 == '/'))
                        {
                            alt5 = 2;
                        }
                        else if (((LA5_1 >= '\u0000' && LA5_1 <= '.') || (LA5_1 >= '0' && LA5_1 <= '\uFFFF')))
                        {
                            alt5 = 1;
                        }


                    }
                    else if (((LA5_0 >= '\u0000' && LA5_0 <= ')') || (LA5_0 >= '+' && LA5_0 <= '\uFFFF')))
                    {
                        alt5 = 1;
                    }


                    switch (alt5)
                    {
                        case 1:
                            // /home/langera/dev/freud/src/grammar/Css.g:142:9: .
                        {
                            matchAny();

                        }
                        break;

                        default:
                            break loop5;
                    }
                }
                while (true);

                match("*/");

                _channel = HIDDEN;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally
        {
        }
    }
    // $ANTLR end "COMMENT"

    // $ANTLR start "WS"
    public final void mWS() throws RecognitionException
    {
        try
        {
            int _type = WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/langera/dev/freud/src/grammar/Css.g:146:4: ( ( ' ' | '\\t' | '\\r' | '\\n' | '\\f' )+ )
            // /home/langera/dev/freud/src/grammar/Css.g:146:6: ( ' ' | '\\t' | '\\r' | '\\n' | '\\f' )+
            {
                // /home/langera/dev/freud/src/grammar/Css.g:146:6: ( ' ' | '\\t' | '\\r' | '\\n' | '\\f' )+
                int cnt6 = 0;
                loop6:
                do
                {
                    int alt6 = 2;
                    int LA6_0 = input.LA(1);

                    if (((LA6_0 >= '\t' && LA6_0 <= '\n') || (LA6_0 >= '\f' && LA6_0 <= '\r') || LA6_0 == ' '))
                    {
                        alt6 = 1;
                    }


                    switch (alt6)
                    {
                        case 1:
                            // /home/langera/dev/freud/src/grammar/Css.g:
                        {
                            if ((input.LA(1) >= '\t' && input.LA(1) <= '\n') || (input.LA(1) >= '\f' && input.LA(1) <= '\r') || input.LA(1) == ' ')
                            {
                                input.consume();

                            }
                            else
                            {
                                MismatchedSetException mse = new MismatchedSetException(null, input);
                                recover(mse);
                                throw mse;
                            }


                        }
                        break;

                        default:
                            if (cnt6 >= 1)
                            {
                                break loop6;
                            }
                            EarlyExitException eee =
                                    new EarlyExitException(6, input);
                            throw eee;
                    }
                    cnt6++;
                }
                while (true);

                _channel = HIDDEN;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally
        {
        }
    }
    // $ANTLR end "WS"

    public void mTokens() throws RecognitionException
    {
        // /home/langera/dev/freud/src/grammar/Css.g:1:8: ( T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | T__38 | T__39 | T__40 | T__41 | T__42 | T__43 | T__44 | T__45 | T__46 | T__47 | T__48 | T__49 | T__50 | T__51 | T__52 | T__53 | T__54 | T__55 | T__56 | T__57 | T__58 | T__59 | T__60 | T__61 | T__62 | IDENT | SL_COMMENT | COMMENT | WS )
        int alt7 = 41;
        alt7 = dfa7.predict(input);
        switch (alt7)
        {
            case 1:
                // /home/langera/dev/freud/src/grammar/Css.g:1:10: T__26
            {
                mT__26();

            }
            break;
            case 2:
                // /home/langera/dev/freud/src/grammar/Css.g:1:16: T__27
            {
                mT__27();

            }
            break;
            case 3:
                // /home/langera/dev/freud/src/grammar/Css.g:1:22: T__28
            {
                mT__28();

            }
            break;
            case 4:
                // /home/langera/dev/freud/src/grammar/Css.g:1:28: T__29
            {
                mT__29();

            }
            break;
            case 5:
                // /home/langera/dev/freud/src/grammar/Css.g:1:34: T__30
            {
                mT__30();

            }
            break;
            case 6:
                // /home/langera/dev/freud/src/grammar/Css.g:1:40: T__31
            {
                mT__31();

            }
            break;
            case 7:
                // /home/langera/dev/freud/src/grammar/Css.g:1:46: T__32
            {
                mT__32();

            }
            break;
            case 8:
                // /home/langera/dev/freud/src/grammar/Css.g:1:52: T__33
            {
                mT__33();

            }
            break;
            case 9:
                // /home/langera/dev/freud/src/grammar/Css.g:1:58: T__34
            {
                mT__34();

            }
            break;
            case 10:
                // /home/langera/dev/freud/src/grammar/Css.g:1:64: T__35
            {
                mT__35();

            }
            break;
            case 11:
                // /home/langera/dev/freud/src/grammar/Css.g:1:70: T__36
            {
                mT__36();

            }
            break;
            case 12:
                // /home/langera/dev/freud/src/grammar/Css.g:1:76: T__37
            {
                mT__37();

            }
            break;
            case 13:
                // /home/langera/dev/freud/src/grammar/Css.g:1:82: T__38
            {
                mT__38();

            }
            break;
            case 14:
                // /home/langera/dev/freud/src/grammar/Css.g:1:88: T__39
            {
                mT__39();

            }
            break;
            case 15:
                // /home/langera/dev/freud/src/grammar/Css.g:1:94: T__40
            {
                mT__40();

            }
            break;
            case 16:
                // /home/langera/dev/freud/src/grammar/Css.g:1:100: T__41
            {
                mT__41();

            }
            break;
            case 17:
                // /home/langera/dev/freud/src/grammar/Css.g:1:106: T__42
            {
                mT__42();

            }
            break;
            case 18:
                // /home/langera/dev/freud/src/grammar/Css.g:1:112: T__43
            {
                mT__43();

            }
            break;
            case 19:
                // /home/langera/dev/freud/src/grammar/Css.g:1:118: T__44
            {
                mT__44();

            }
            break;
            case 20:
                // /home/langera/dev/freud/src/grammar/Css.g:1:124: T__45
            {
                mT__45();

            }
            break;
            case 21:
                // /home/langera/dev/freud/src/grammar/Css.g:1:130: T__46
            {
                mT__46();

            }
            break;
            case 22:
                // /home/langera/dev/freud/src/grammar/Css.g:1:136: T__47
            {
                mT__47();

            }
            break;
            case 23:
                // /home/langera/dev/freud/src/grammar/Css.g:1:142: T__48
            {
                mT__48();

            }
            break;
            case 24:
                // /home/langera/dev/freud/src/grammar/Css.g:1:148: T__49
            {
                mT__49();

            }
            break;
            case 25:
                // /home/langera/dev/freud/src/grammar/Css.g:1:154: T__50
            {
                mT__50();

            }
            break;
            case 26:
                // /home/langera/dev/freud/src/grammar/Css.g:1:160: T__51
            {
                mT__51();

            }
            break;
            case 27:
                // /home/langera/dev/freud/src/grammar/Css.g:1:166: T__52
            {
                mT__52();

            }
            break;
            case 28:
                // /home/langera/dev/freud/src/grammar/Css.g:1:172: T__53
            {
                mT__53();

            }
            break;
            case 29:
                // /home/langera/dev/freud/src/grammar/Css.g:1:178: T__54
            {
                mT__54();

            }
            break;
            case 30:
                // /home/langera/dev/freud/src/grammar/Css.g:1:184: T__55
            {
                mT__55();

            }
            break;
            case 31:
                // /home/langera/dev/freud/src/grammar/Css.g:1:190: T__56
            {
                mT__56();

            }
            break;
            case 32:
                // /home/langera/dev/freud/src/grammar/Css.g:1:196: T__57
            {
                mT__57();

            }
            break;
            case 33:
                // /home/langera/dev/freud/src/grammar/Css.g:1:202: T__58
            {
                mT__58();

            }
            break;
            case 34:
                // /home/langera/dev/freud/src/grammar/Css.g:1:208: T__59
            {
                mT__59();

            }
            break;
            case 35:
                // /home/langera/dev/freud/src/grammar/Css.g:1:214: T__60
            {
                mT__60();

            }
            break;
            case 36:
                // /home/langera/dev/freud/src/grammar/Css.g:1:220: T__61
            {
                mT__61();

            }
            break;
            case 37:
                // /home/langera/dev/freud/src/grammar/Css.g:1:226: T__62
            {
                mT__62();

            }
            break;
            case 38:
                // /home/langera/dev/freud/src/grammar/Css.g:1:232: IDENT
            {
                mIDENT();

            }
            break;
            case 39:
                // /home/langera/dev/freud/src/grammar/Css.g:1:238: SL_COMMENT
            {
                mSL_COMMENT();

            }
            break;
            case 40:
                // /home/langera/dev/freud/src/grammar/Css.g:1:249: COMMENT
            {
                mCOMMENT();

            }
            break;
            case 41:
                // /home/langera/dev/freud/src/grammar/Css.g:1:257: WS
            {
                mWS();

            }
            break;

        }

    }


    protected DFA7 dfa7 = new DFA7(this);
    static final String DFA7_eotS =
            "\1\uffff\1\43\11\uffff\1\45\2\uffff\1\46\3\uffff\10\40\1\63\2\40" +
                    "\2\uffff\1\40\7\uffff\1\72\1\73\1\74\1\75\1\76\1\77\1\100\1\101" +
                    "\1\102\3\40\1\uffff\1\106\2\40\14\uffff\1\112\1\113\1\40\1\uffff" +
                    "\1\115\1\40\3\uffff\1\116\2\uffff";
    static final String DFA7_eofS =
            "\117\uffff";
    static final String DFA7_minS =
            "\1\11\1\151\11\uffff\1\72\2\uffff\1\42\3\uffff\1\143\2\155\1\156" +
                    "\1\155\1\145\1\141\1\162\1\42\1\172\1\150\2\uffff\1\52\2\uffff\1" +
                    "\155\4\uffff\11\42\1\147\1\144\1\141\1\uffff\1\42\1\172\1\0\14\uffff" +
                    "\2\42\1\144\1\uffff\1\42\1\0\3\uffff\1\42\2\uffff";
    static final String DFA7_maxS =
            "\1\ufffe\1\151\11\uffff\1\72\2\uffff\1\ufffe\3\uffff\1\170\1\155" +
                    "\1\163\1\156\1\170\1\145\1\141\1\162\1\ufffe\1\172\1\150\2\uffff" +
                    "\1\57\2\uffff\1\156\4\uffff\11\ufffe\1\147\1\144\1\141\1\uffff\1" +
                    "\ufffe\1\172\1\uffff\14\uffff\2\ufffe\1\144\1\uffff\1\ufffe\1\uffff" +
                    "\3\uffff\1\ufffe\2\uffff";
    static final String DFA7_acceptS =
            "\2\uffff\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14\1\uffff\1\17\1" +
                    "\20\1\uffff\1\22\1\23\1\24\13\uffff\1\44\1\45\1\uffff\1\46\1\51" +
                    "\1\uffff\1\3\1\16\1\15\1\21\14\uffff\1\41\3\uffff\1\50\1\1\1\2\1" +
                    "\25\1\31\1\32\1\26\1\27\1\40\1\30\1\33\1\34\3\uffff\1\42\2\uffff" +
                    "\1\47\1\35\1\36\1\uffff\1\43\1\37";
    static final String DFA7_specialS =
            "\66\uffff\1\0\21\uffff\1\1\6\uffff}>";
    static final String[] DFA7_transitionS = {
            "\2\41\1\uffff\2\41\22\uffff\1\41\2\40\1\11\1\uffff\1\21\1\uffff" +
                    "\1\40\1\35\1\36\1\10\1\6\1\4\1\40\1\12\1\37\12\40\1\13\1\7\1" +
                    "\uffff\1\16\1\5\1\uffff\1\1\32\40\1\14\1\uffff\1\15\1\uffff" +
                    "\1\40\1\uffff\2\40\1\23\1\27\1\26\1\40\1\31\1\33\1\25\1\40\1" +
                    "\34\1\40\1\24\2\40\1\22\1\40\1\30\1\32\7\40\1\2\1\20\1\3\1\17" +
                    "\u0081\uffff\ufeff\40",
            "\1\42",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\44",
            "",
            "",
            "\1\40\4\uffff\1\40\5\uffff\15\40\3\uffff\1\40\3\uffff\32\40" +
                    "\4\uffff\1\40\1\uffff\32\40\u0085\uffff\ufeff\40",
            "",
            "",
            "",
            "\1\51\20\uffff\1\50\3\uffff\1\47",
            "\1\52",
            "\1\53\5\uffff\1\54",
            "\1\55",
            "\1\56\12\uffff\1\57",
            "\1\60",
            "\1\61",
            "\1\62",
            "\1\40\4\uffff\1\40\5\uffff\15\40\3\uffff\1\40\3\uffff\32\40" +
                    "\4\uffff\1\40\1\uffff\32\40\u0085\uffff\ufeff\40",
            "\1\64",
            "\1\65",
            "",
            "",
            "\1\67\4\uffff\1\66",
            "",
            "",
            "\1\70\1\71",
            "",
            "",
            "",
            "",
            "\1\40\4\uffff\1\40\5\uffff\15\40\3\uffff\1\40\3\uffff\32\40" +
                    "\4\uffff\1\40\1\uffff\32\40\u0085\uffff\ufeff\40",
            "\1\40\4\uffff\1\40\5\uffff\15\40\3\uffff\1\40\3\uffff\32\40" +
                    "\4\uffff\1\40\1\uffff\32\40\u0085\uffff\ufeff\40",
            "\1\40\4\uffff\1\40\5\uffff\15\40\3\uffff\1\40\3\uffff\32\40" +
                    "\4\uffff\1\40\1\uffff\32\40\u0085\uffff\ufeff\40",
            "\1\40\4\uffff\1\40\5\uffff\15\40\3\uffff\1\40\3\uffff\32\40" +
                    "\4\uffff\1\40\1\uffff\32\40\u0085\uffff\ufeff\40",
            "\1\40\4\uffff\1\40\5\uffff\15\40\3\uffff\1\40\3\uffff\32\40" +
                    "\4\uffff\1\40\1\uffff\32\40\u0085\uffff\ufeff\40",
            "\1\40\4\uffff\1\40\5\uffff\15\40\3\uffff\1\40\3\uffff\32\40" +
                    "\4\uffff\1\40\1\uffff\32\40\u0085\uffff\ufeff\40",
            "\1\40\4\uffff\1\40\5\uffff\15\40\3\uffff\1\40\3\uffff\32\40" +
                    "\4\uffff\1\40\1\uffff\32\40\u0085\uffff\ufeff\40",
            "\1\40\4\uffff\1\40\5\uffff\15\40\3\uffff\1\40\3\uffff\32\40" +
                    "\4\uffff\1\40\1\uffff\32\40\u0085\uffff\ufeff\40",
            "\1\40\4\uffff\1\40\5\uffff\15\40\3\uffff\1\40\3\uffff\32\40" +
                    "\4\uffff\1\40\1\uffff\32\40\u0085\uffff\ufeff\40",
            "\1\103",
            "\1\104",
            "\1\105",
            "",
            "\1\40\4\uffff\1\40\5\uffff\15\40\3\uffff\1\40\3\uffff\32\40" +
                    "\4\uffff\1\40\1\uffff\32\40\u0085\uffff\ufeff\40",
            "\1\107",
            "\42\111\1\110\4\111\1\110\5\111\15\110\3\111\1\110\3\111\32" +
                    "\110\4\111\1\110\1\111\32\110\u0085\111\ufeff\110\1\111",
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
            "\1\40\4\uffff\1\40\5\uffff\15\40\3\uffff\1\40\3\uffff\32\40" +
                    "\4\uffff\1\40\1\uffff\32\40\u0085\uffff\ufeff\40",
            "\1\40\4\uffff\1\40\5\uffff\15\40\3\uffff\1\40\3\uffff\32\40" +
                    "\4\uffff\1\40\1\uffff\32\40\u0085\uffff\ufeff\40",
            "\1\114",
            "",
            "\1\40\4\uffff\1\40\5\uffff\15\40\3\uffff\1\40\3\uffff\32\40" +
                    "\4\uffff\1\40\1\uffff\32\40\u0085\uffff\ufeff\40",
            "\42\111\1\110\4\111\1\110\5\111\15\110\3\111\1\110\3\111\32" +
                    "\110\4\111\1\110\1\111\32\110\u0085\111\ufeff\110\1\111",
            "",
            "",
            "",
            "\1\40\4\uffff\1\40\5\uffff\15\40\3\uffff\1\40\3\uffff\32\40" +
                    "\4\uffff\1\40\1\uffff\32\40\u0085\uffff\ufeff\40",
            "",
            ""
    };

    static final short[] DFA7_eot = DFA.unpackEncodedString(DFA7_eotS);
    static final short[] DFA7_eof = DFA.unpackEncodedString(DFA7_eofS);
    static final char[] DFA7_min = DFA.unpackEncodedStringToUnsignedChars(DFA7_minS);
    static final char[] DFA7_max = DFA.unpackEncodedStringToUnsignedChars(DFA7_maxS);
    static final short[] DFA7_accept = DFA.unpackEncodedString(DFA7_acceptS);
    static final short[] DFA7_special = DFA.unpackEncodedString(DFA7_specialS);
    static final short[][] DFA7_transition;

    static
    {
        int numStates = DFA7_transitionS.length;
        DFA7_transition = new short[numStates][];
        for (int i = 0; i < numStates; i++)
        {
            DFA7_transition[i] = DFA.unpackEncodedString(DFA7_transitionS[i]);
        }
    }

    class DFA7 extends DFA
    {

        public DFA7(BaseRecognizer recognizer)
        {
            this.recognizer = recognizer;
            this.decisionNumber = 7;
            this.eot = DFA7_eot;
            this.eof = DFA7_eof;
            this.min = DFA7_min;
            this.max = DFA7_max;
            this.accept = DFA7_accept;
            this.special = DFA7_special;
            this.transition = DFA7_transition;
        }

        public String getDescription()
        {
            return "1:1: Tokens : ( T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | T__38 | T__39 | T__40 | T__41 | T__42 | T__43 | T__44 | T__45 | T__46 | T__47 | T__48 | T__49 | T__50 | T__51 | T__52 | T__53 | T__54 | T__55 | T__56 | T__57 | T__58 | T__59 | T__60 | T__61 | T__62 | IDENT | SL_COMMENT | COMMENT | WS );";
        }

        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException
        {
            IntStream input = _input;
            int _s = s;
            switch (s)
            {
                case 0:
                    int LA7_54 = input.LA(1);

                    s = -1;
                    if ((LA7_54 == '\"' || LA7_54 == '\'' || (LA7_54 >= '-' && LA7_54 <= '9') || LA7_54 == '=' || (LA7_54 >= 'A' && LA7_54 <= 'Z') || LA7_54 == '_' || (LA7_54 >= 'a' && LA7_54 <= 'z') || (LA7_54 >= '\u0100' && LA7_54 <= '\uFFFE')))
                    {s = 72;}

                    else if (((LA7_54 >= '\u0000' && LA7_54 <= '!') || (LA7_54 >= '#' && LA7_54 <= '&') || (LA7_54 >= '(' && LA7_54 <= ',') || (LA7_54 >= ':' && LA7_54 <= '<') || (LA7_54 >= '>' && LA7_54 <= '@') || (LA7_54 >= '[' && LA7_54 <= '^') || LA7_54 == '`' || (LA7_54 >= '{' && LA7_54 <= '\u00FF') || LA7_54 == '\uFFFF'))
                    {s = 73;}

                    else
                    {
                        s = 32;
                    }

                    if (s >= 0)
                    {
                        return s;
                    }
                    break;
                case 1:
                    int LA7_72 = input.LA(1);

                    s = -1;
                    if (((LA7_72 >= '\u0000' && LA7_72 <= '!') || (LA7_72 >= '#' && LA7_72 <= '&') || (LA7_72 >= '(' && LA7_72 <= ',') || (LA7_72 >= ':' && LA7_72 <= '<') || (LA7_72 >= '>' && LA7_72 <= '@') || (LA7_72 >= '[' && LA7_72 <= '^') || LA7_72 == '`' || (LA7_72 >= '{' && LA7_72 <= '\u00FF') || LA7_72 == '\uFFFF'))
                    {s = 73;}

                    else if ((LA7_72 == '\"' || LA7_72 == '\'' || (LA7_72 >= '-' && LA7_72 <= '9') || LA7_72 == '=' || (LA7_72 >= 'A' && LA7_72 <= 'Z') || LA7_72 == '_' || (LA7_72 >= 'a' && LA7_72 <= 'z') || (LA7_72 >= '\u0100' && LA7_72 <= '\uFFFE')))
                    {s = 72;}

                    else
                    {
                        s = 32;
                    }

                    if (s >= 0)
                    {
                        return s;
                    }
                    break;
            }
            NoViableAltException nvae =
                    new NoViableAltException(getDescription(), 7, _s, input);
            error(nvae);
            throw nvae;
        }
    }


}