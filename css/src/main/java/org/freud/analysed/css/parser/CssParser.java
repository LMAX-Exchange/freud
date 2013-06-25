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

// $ANTLR 3.1.2 ./css/src/main/grammar/Css.g 2013-04-08 17:17:45

package org.freud.analysed.css.parser;

import org.antlr.runtime.BaseRecognizer;
import org.antlr.runtime.BitSet;
import org.antlr.runtime.DFA;
import org.antlr.runtime.EarlyExitException;
import org.antlr.runtime.MismatchedSetException;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.Parser;
import org.antlr.runtime.ParserRuleReturnScope;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeAdaptor;
import org.antlr.runtime.tree.RewriteEarlyExitException;
import org.antlr.runtime.tree.RewriteRuleSubtreeStream;
import org.antlr.runtime.tree.RewriteRuleTokenStream;
import org.antlr.runtime.tree.TreeAdaptor;

/**
 * Simplified version of grammar CSS grammar - http://www.antlr.org/grammar/1214945003224/csst3.g
 */
public class CssParser extends Parser {
    public static final String[] tokenNames = new String[]{
            "<invalid>", "<EOR>", "<DOWN>", "<UP>", "IMPORT", "NESTED", "NEST", "RULE", "ATTRIB", "CHILD", "ADJACENT_SIBLING", "ATTRIBEQUAL", "HASVALUE", "BEGINSWITH", "PSEUDO", "PROPERTY", "COLOUR", "FUNCTION", "TAG", "ID", "CLASS", "UNIVERSAL", "IDENT", "SL_COMMENT", "COMMENT", "WS", "'@import'", "'@include'", "'@'", "'{'", "'}'", "','", "'>'", "'+'", "';'", "'*'", "'#'", "'.'", "':'", "'::'", "'['", "']'", "'='", "'~='", "'|='", "'%'", "'px'", "'cm'", "'mm'", "'in'", "'pt'", "'pc'", "'em'", "'ex'", "'deg'", "'rad'", "'grad'", "'ms'", "'s'", "'hz'", "'khz'", "'('", "')'"
    };
    public static final int FUNCTION = 17;
    public static final int CLASS = 20;
    public static final int ATTRIB = 8;
    public static final int T__29 = 29;
    public static final int T__28 = 28;
    public static final int T__27 = 27;
    public static final int T__62 = 62;
    public static final int T__26 = 26;
    public static final int HASVALUE = 12;
    public static final int PSEUDO = 14;
    public static final int NEST = 6;
    public static final int ID = 19;
    public static final int T__61 = 61;
    public static final int ATTRIBEQUAL = 11;
    public static final int EOF = -1;
    public static final int T__60 = 60;
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
    public static final int BEGINSWITH = 13;
    public static final int T__45 = 45;
    public static final int T__48 = 48;
    public static final int T__49 = 49;
    public static final int TAG = 18;
    public static final int NESTED = 5;
    public static final int T__30 = 30;
    public static final int T__31 = 31;
    public static final int T__32 = 32;
    public static final int WS = 25;
    public static final int T__33 = 33;
    public static final int T__34 = 34;
    public static final int T__35 = 35;
    public static final int T__36 = 36;
    public static final int PROPERTY = 15;
    public static final int T__37 = 37;
    public static final int T__38 = 38;
    public static final int T__39 = 39;
    public static final int SL_COMMENT = 23;
    public static final int UNIVERSAL = 21;

    // delegates
    // delegators


    public CssParser(TokenStream input) {
        this(input, new RecognizerSharedState());
    }

    public CssParser(TokenStream input, RecognizerSharedState state) {
        super(input, state);

    }

    protected TreeAdaptor adaptor = new CommonTreeAdaptor();

    public void setTreeAdaptor(TreeAdaptor adaptor) {
        this.adaptor = adaptor;
    }

    public TreeAdaptor getTreeAdaptor() {
        return adaptor;
    }

    public String[] getTokenNames() {
        return CssParser.tokenNames;
    }

    public String getGrammarFileName() {
        return "/home/langera/dev/freud/src/grammar/Css.g";
    }


    public static class stylesheet_return extends ParserRuleReturnScope {
        CommonTree tree;

        public Object getTree() {
            return tree;
        }
    }

    ;

    // $ANTLR start "stylesheet"
    // /home/langera/dev/freud/src/grammar/Css.g:40:1: stylesheet : ( importRule )* ( nested | ruleset )+ ;

    public final CssParser.stylesheet_return stylesheet() throws RecognitionException {
        CssParser.stylesheet_return retval = new CssParser.stylesheet_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        CssParser.importRule_return importRule1 = null;

        CssParser.nested_return nested2 = null;

        CssParser.ruleset_return ruleset3 = null;


        try {
            // /home/langera/dev/freud/src/grammar/Css.g:41:2: ( ( importRule )* ( nested | ruleset )+ )
            // /home/langera/dev/freud/src/grammar/Css.g:41:4: ( importRule )* ( nested | ruleset )+
            {
                root_0 = (CommonTree) adaptor.nil();

                // /home/langera/dev/freud/src/grammar/Css.g:41:4: ( importRule )*
                loop1:
                do {
                    int alt1 = 2;
                    int LA1_0 = input.LA(1);

                    if (((LA1_0 >= 26 && LA1_0 <= 27))) {
                        alt1 = 1;
                    }


                    switch (alt1) {
                        case 1:
                            // /home/langera/dev/freud/src/grammar/Css.g:41:4: importRule
                        {
                            pushFollow(FOLLOW_importRule_in_stylesheet131);
                            importRule1 = importRule();

                            state._fsp--;

                            adaptor.addChild(root_0, importRule1.getTree());

                        }
                        break;

                        default:
                            break loop1;
                    }
                }
                while (true);

                // /home/langera/dev/freud/src/grammar/Css.g:41:16: ( nested | ruleset )+
                int cnt2 = 0;
                loop2:
                do {
                    int alt2 = 3;
                    int LA2_0 = input.LA(1);

                    if ((LA2_0 == 28)) {
                        alt2 = 1;
                    }
                    else if ((LA2_0 == IDENT || (LA2_0 >= 35 && LA2_0 <= 37))) {
                        alt2 = 2;
                    }


                    switch (alt2) {
                        case 1:
                            // /home/langera/dev/freud/src/grammar/Css.g:41:17: nested
                        {
                            pushFollow(FOLLOW_nested_in_stylesheet135);
                            nested2 = nested();

                            state._fsp--;

                            adaptor.addChild(root_0, nested2.getTree());

                        }
                        break;
                        case 2:
                            // /home/langera/dev/freud/src/grammar/Css.g:41:26: ruleset
                        {
                            pushFollow(FOLLOW_ruleset_in_stylesheet139);
                            ruleset3 = ruleset();

                            state._fsp--;

                            adaptor.addChild(root_0, ruleset3.getTree());

                        }
                        break;

                        default:
                            if (cnt2 >= 1) {
                                break loop2;
                            }
                            EarlyExitException eee =
                                    new EarlyExitException(2, input);
                            throw eee;
                    }
                    cnt2++;
                }
                while (true);


            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (CommonTree) adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "stylesheet"

    public static class importRule_return extends ParserRuleReturnScope {
        CommonTree tree;

        public Object getTree() {
            return tree;
        }
    }

    ;

    // $ANTLR start "importRule"
    // /home/langera/dev/freud/src/grammar/Css.g:44:1: importRule : ( '@import' | '@include' ) IDENT -> ^( IMPORT IDENT ) ;

    public final CssParser.importRule_return importRule() throws RecognitionException {
        CssParser.importRule_return retval = new CssParser.importRule_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token string_literal4 = null;
        Token string_literal5 = null;
        Token IDENT6 = null;

        CommonTree string_literal4_tree = null;
        CommonTree string_literal5_tree = null;
        CommonTree IDENT6_tree = null;
        RewriteRuleTokenStream stream_IDENT = new RewriteRuleTokenStream(adaptor, "token IDENT");
        RewriteRuleTokenStream stream_26 = new RewriteRuleTokenStream(adaptor, "token 26");
        RewriteRuleTokenStream stream_27 = new RewriteRuleTokenStream(adaptor, "token 27");

        try {
            // /home/langera/dev/freud/src/grammar/Css.g:45:2: ( ( '@import' | '@include' ) IDENT -> ^( IMPORT IDENT ) )
            // /home/langera/dev/freud/src/grammar/Css.g:45:4: ( '@import' | '@include' ) IDENT
            {
                // /home/langera/dev/freud/src/grammar/Css.g:45:4: ( '@import' | '@include' )
                int alt3 = 2;
                int LA3_0 = input.LA(1);

                if ((LA3_0 == 26)) {
                    alt3 = 1;
                }
                else if ((LA3_0 == 27)) {
                    alt3 = 2;
                }
                else {
                    NoViableAltException nvae =
                            new NoViableAltException("", 3, 0, input);

                    throw nvae;
                }
                switch (alt3) {
                    case 1:
                        // /home/langera/dev/freud/src/grammar/Css.g:45:5: '@import'
                    {
                        string_literal4 = (Token) match(input, 26, FOLLOW_26_in_importRule153);
                        stream_26.add(string_literal4);


                    }
                    break;
                    case 2:
                        // /home/langera/dev/freud/src/grammar/Css.g:45:17: '@include'
                    {
                        string_literal5 = (Token) match(input, 27, FOLLOW_27_in_importRule157);
                        stream_27.add(string_literal5);


                    }
                    break;

                }

                IDENT6 = (Token) match(input, IDENT, FOLLOW_IDENT_in_importRule161);
                stream_IDENT.add(IDENT6);


                // AST REWRITE
                // elements: IDENT
                // token labels:
                // rule labels: retval
                // token list labels:
                // rule list labels:
                // wildcard labels:
                retval.tree = root_0;
                RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.tree : null);

                root_0 = (CommonTree) adaptor.nil();
                // 45:36: -> ^( IMPORT IDENT )
                {
                    // /home/langera/dev/freud/src/grammar/Css.g:45:39: ^( IMPORT IDENT )
                    {
                        CommonTree root_1 = (CommonTree) adaptor.nil();
                        root_1 = (CommonTree) adaptor.becomeRoot((CommonTree) adaptor.create(IMPORT, "IMPORT"), root_1);

                        adaptor.addChild(root_1, stream_IDENT.nextNode());

                        adaptor.addChild(root_0, root_1);
                    }

                }

                retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (CommonTree) adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "importRule"

    public static class nested_return extends ParserRuleReturnScope {
        CommonTree tree;

        public Object getTree() {
            return tree;
        }
    }

    ;

    // $ANTLR start "nested"
    // /home/langera/dev/freud/src/grammar/Css.g:48:1: nested : '@' nest '{' ( properties )? ( nested )* '}' -> ^( NESTED nest ( properties )* ( nested )* ) ;

    public final CssParser.nested_return nested() throws RecognitionException {
        CssParser.nested_return retval = new CssParser.nested_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token char_literal7 = null;
        Token char_literal9 = null;
        Token char_literal12 = null;
        CssParser.nest_return nest8 = null;

        CssParser.properties_return properties10 = null;

        CssParser.nested_return nested11 = null;


        CommonTree char_literal7_tree = null;
        CommonTree char_literal9_tree = null;
        CommonTree char_literal12_tree = null;
        RewriteRuleTokenStream stream_30 = new RewriteRuleTokenStream(adaptor, "token 30");
        RewriteRuleTokenStream stream_28 = new RewriteRuleTokenStream(adaptor, "token 28");
        RewriteRuleTokenStream stream_29 = new RewriteRuleTokenStream(adaptor, "token 29");
        RewriteRuleSubtreeStream stream_nested = new RewriteRuleSubtreeStream(adaptor, "rule nested");
        RewriteRuleSubtreeStream stream_nest = new RewriteRuleSubtreeStream(adaptor, "rule nest");
        RewriteRuleSubtreeStream stream_properties = new RewriteRuleSubtreeStream(adaptor, "rule properties");
        try {
            // /home/langera/dev/freud/src/grammar/Css.g:49:3: ( '@' nest '{' ( properties )? ( nested )* '}' -> ^( NESTED nest ( properties )* ( nested )* ) )
            // /home/langera/dev/freud/src/grammar/Css.g:49:5: '@' nest '{' ( properties )? ( nested )* '}'
            {
                char_literal7 = (Token) match(input, 28, FOLLOW_28_in_nested183);
                stream_28.add(char_literal7);

                pushFollow(FOLLOW_nest_in_nested185);
                nest8 = nest();

                state._fsp--;

                stream_nest.add(nest8.getTree());
                char_literal9 = (Token) match(input, 29, FOLLOW_29_in_nested187);
                stream_29.add(char_literal9);

                // /home/langera/dev/freud/src/grammar/Css.g:49:18: ( properties )?
                int alt4 = 2;
                int LA4_0 = input.LA(1);

                if ((LA4_0 == IDENT)) {
                    alt4 = 1;
                }
                switch (alt4) {
                    case 1:
                        // /home/langera/dev/freud/src/grammar/Css.g:49:18: properties
                    {
                        pushFollow(FOLLOW_properties_in_nested189);
                        properties10 = properties();

                        state._fsp--;

                        stream_properties.add(properties10.getTree());

                    }
                    break;

                }

                // /home/langera/dev/freud/src/grammar/Css.g:49:30: ( nested )*
                loop5:
                do {
                    int alt5 = 2;
                    int LA5_0 = input.LA(1);

                    if ((LA5_0 == 28)) {
                        alt5 = 1;
                    }


                    switch (alt5) {
                        case 1:
                            // /home/langera/dev/freud/src/grammar/Css.g:49:30: nested
                        {
                            pushFollow(FOLLOW_nested_in_nested192);
                            nested11 = nested();

                            state._fsp--;

                            stream_nested.add(nested11.getTree());

                        }
                        break;

                        default:
                            break loop5;
                    }
                }
                while (true);

                char_literal12 = (Token) match(input, 30, FOLLOW_30_in_nested195);
                stream_30.add(char_literal12);


                // AST REWRITE
                // elements: nested, nest, properties
                // token labels:
                // rule labels: retval
                // token list labels:
                // rule list labels:
                // wildcard labels:
                retval.tree = root_0;
                RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.tree : null);

                root_0 = (CommonTree) adaptor.nil();
                // 49:42: -> ^( NESTED nest ( properties )* ( nested )* )
                {
                    // /home/langera/dev/freud/src/grammar/Css.g:49:45: ^( NESTED nest ( properties )* ( nested )* )
                    {
                        CommonTree root_1 = (CommonTree) adaptor.nil();
                        root_1 = (CommonTree) adaptor.becomeRoot((CommonTree) adaptor.create(NESTED, "NESTED"), root_1);

                        adaptor.addChild(root_1, stream_nest.nextTree());
                        // /home/langera/dev/freud/src/grammar/Css.g:49:60: ( properties )*
                        while (stream_properties.hasNext()) {
                            adaptor.addChild(root_1, stream_properties.nextTree());

                        }
                        stream_properties.reset();
                        // /home/langera/dev/freud/src/grammar/Css.g:49:72: ( nested )*
                        while (stream_nested.hasNext()) {
                            adaptor.addChild(root_1, stream_nested.nextTree());

                        }
                        stream_nested.reset();

                        adaptor.addChild(root_0, root_1);
                    }

                }

                retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (CommonTree) adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "nested"

    public static class nest_return extends ParserRuleReturnScope {
        CommonTree tree;

        public Object getTree() {
            return tree;
        }
    }

    ;

    // $ANTLR start "nest"
    // /home/langera/dev/freud/src/grammar/Css.g:52:1: nest : IDENT ( IDENT )* ( pseudo )* -> ^( NEST IDENT ( IDENT )* ( pseudo )* ) ;

    public final CssParser.nest_return nest() throws RecognitionException {
        CssParser.nest_return retval = new CssParser.nest_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token IDENT13 = null;
        Token IDENT14 = null;
        CssParser.pseudo_return pseudo15 = null;


        CommonTree IDENT13_tree = null;
        CommonTree IDENT14_tree = null;
        RewriteRuleTokenStream stream_IDENT = new RewriteRuleTokenStream(adaptor, "token IDENT");
        RewriteRuleSubtreeStream stream_pseudo = new RewriteRuleSubtreeStream(adaptor, "rule pseudo");
        try {
            // /home/langera/dev/freud/src/grammar/Css.g:53:2: ( IDENT ( IDENT )* ( pseudo )* -> ^( NEST IDENT ( IDENT )* ( pseudo )* ) )
            // /home/langera/dev/freud/src/grammar/Css.g:53:4: IDENT ( IDENT )* ( pseudo )*
            {
                IDENT13 = (Token) match(input, IDENT, FOLLOW_IDENT_in_nest222);
                stream_IDENT.add(IDENT13);

                // /home/langera/dev/freud/src/grammar/Css.g:53:10: ( IDENT )*
                loop6:
                do {
                    int alt6 = 2;
                    int LA6_0 = input.LA(1);

                    if ((LA6_0 == IDENT)) {
                        alt6 = 1;
                    }


                    switch (alt6) {
                        case 1:
                            // /home/langera/dev/freud/src/grammar/Css.g:53:10: IDENT
                        {
                            IDENT14 = (Token) match(input, IDENT, FOLLOW_IDENT_in_nest224);
                            stream_IDENT.add(IDENT14);


                        }
                        break;

                        default:
                            break loop6;
                    }
                }
                while (true);

                // /home/langera/dev/freud/src/grammar/Css.g:53:17: ( pseudo )*
                loop7:
                do {
                    int alt7 = 2;
                    int LA7_0 = input.LA(1);

                    if (((LA7_0 >= 38 && LA7_0 <= 39))) {
                        alt7 = 1;
                    }


                    switch (alt7) {
                        case 1:
                            // /home/langera/dev/freud/src/grammar/Css.g:53:17: pseudo
                        {
                            pushFollow(FOLLOW_pseudo_in_nest227);
                            pseudo15 = pseudo();

                            state._fsp--;

                            stream_pseudo.add(pseudo15.getTree());

                        }
                        break;

                        default:
                            break loop7;
                    }
                }
                while (true);


                // AST REWRITE
                // elements: pseudo, IDENT, IDENT
                // token labels:
                // rule labels: retval
                // token list labels:
                // rule list labels:
                // wildcard labels:
                retval.tree = root_0;
                RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.tree : null);

                root_0 = (CommonTree) adaptor.nil();
                // 53:25: -> ^( NEST IDENT ( IDENT )* ( pseudo )* )
                {
                    // /home/langera/dev/freud/src/grammar/Css.g:53:28: ^( NEST IDENT ( IDENT )* ( pseudo )* )
                    {
                        CommonTree root_1 = (CommonTree) adaptor.nil();
                        root_1 = (CommonTree) adaptor.becomeRoot((CommonTree) adaptor.create(NEST, "NEST"), root_1);

                        adaptor.addChild(root_1, stream_IDENT.nextNode());
                        // /home/langera/dev/freud/src/grammar/Css.g:53:42: ( IDENT )*
                        while (stream_IDENT.hasNext()) {
                            adaptor.addChild(root_1, stream_IDENT.nextNode());

                        }
                        stream_IDENT.reset();
                        // /home/langera/dev/freud/src/grammar/Css.g:53:49: ( pseudo )*
                        while (stream_pseudo.hasNext()) {
                            adaptor.addChild(root_1, stream_pseudo.nextTree());

                        }
                        stream_pseudo.reset();

                        adaptor.addChild(root_0, root_1);
                    }

                }

                retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (CommonTree) adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "nest"

    public static class ruleset_return extends ParserRuleReturnScope {
        CommonTree tree;

        public Object getTree() {
            return tree;
        }
    }

    ;

    // $ANTLR start "ruleset"
    // /home/langera/dev/freud/src/grammar/Css.g:56:1: ruleset : selectors '{' ( properties )? '}' -> ^( RULE selectors ( properties )* ) ;

    public final CssParser.ruleset_return ruleset() throws RecognitionException {
        CssParser.ruleset_return retval = new CssParser.ruleset_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token char_literal17 = null;
        Token char_literal19 = null;
        CssParser.selectors_return selectors16 = null;

        CssParser.properties_return properties18 = null;


        CommonTree char_literal17_tree = null;
        CommonTree char_literal19_tree = null;
        RewriteRuleTokenStream stream_30 = new RewriteRuleTokenStream(adaptor, "token 30");
        RewriteRuleTokenStream stream_29 = new RewriteRuleTokenStream(adaptor, "token 29");
        RewriteRuleSubtreeStream stream_selectors = new RewriteRuleSubtreeStream(adaptor, "rule selectors");
        RewriteRuleSubtreeStream stream_properties = new RewriteRuleSubtreeStream(adaptor, "rule properties");
        try {
            // /home/langera/dev/freud/src/grammar/Css.g:57:3: ( selectors '{' ( properties )? '}' -> ^( RULE selectors ( properties )* ) )
            // /home/langera/dev/freud/src/grammar/Css.g:57:5: selectors '{' ( properties )? '}'
            {
                pushFollow(FOLLOW_selectors_in_ruleset257);
                selectors16 = selectors();

                state._fsp--;

                stream_selectors.add(selectors16.getTree());
                char_literal17 = (Token) match(input, 29, FOLLOW_29_in_ruleset259);
                stream_29.add(char_literal17);

                // /home/langera/dev/freud/src/grammar/Css.g:57:19: ( properties )?
                int alt8 = 2;
                int LA8_0 = input.LA(1);

                if ((LA8_0 == IDENT)) {
                    alt8 = 1;
                }
                switch (alt8) {
                    case 1:
                        // /home/langera/dev/freud/src/grammar/Css.g:57:19: properties
                    {
                        pushFollow(FOLLOW_properties_in_ruleset261);
                        properties18 = properties();

                        state._fsp--;

                        stream_properties.add(properties18.getTree());

                    }
                    break;

                }

                char_literal19 = (Token) match(input, 30, FOLLOW_30_in_ruleset264);
                stream_30.add(char_literal19);


                // AST REWRITE
                // elements: selectors, properties
                // token labels:
                // rule labels: retval
                // token list labels:
                // rule list labels:
                // wildcard labels:
                retval.tree = root_0;
                RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.tree : null);

                root_0 = (CommonTree) adaptor.nil();
                // 57:35: -> ^( RULE selectors ( properties )* )
                {
                    // /home/langera/dev/freud/src/grammar/Css.g:57:38: ^( RULE selectors ( properties )* )
                    {
                        CommonTree root_1 = (CommonTree) adaptor.nil();
                        root_1 = (CommonTree) adaptor.becomeRoot((CommonTree) adaptor.create(RULE, "RULE"), root_1);

                        adaptor.addChild(root_1, stream_selectors.nextTree());
                        // /home/langera/dev/freud/src/grammar/Css.g:57:56: ( properties )*
                        while (stream_properties.hasNext()) {
                            adaptor.addChild(root_1, stream_properties.nextTree());

                        }
                        stream_properties.reset();

                        adaptor.addChild(root_0, root_1);
                    }

                }

                retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (CommonTree) adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "ruleset"

    public static class selectors_return extends ParserRuleReturnScope {
        CommonTree tree;

        public Object getTree() {
            return tree;
        }
    }

    ;

    // $ANTLR start "selectors"
    // /home/langera/dev/freud/src/grammar/Css.g:60:1: selectors : selector ( ',' selector )* ;

    public final CssParser.selectors_return selectors() throws RecognitionException {
        CssParser.selectors_return retval = new CssParser.selectors_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token char_literal21 = null;
        CssParser.selector_return selector20 = null;

        CssParser.selector_return selector22 = null;


        CommonTree char_literal21_tree = null;

        try {
            // /home/langera/dev/freud/src/grammar/Css.g:61:2: ( selector ( ',' selector )* )
            // /home/langera/dev/freud/src/grammar/Css.g:61:4: selector ( ',' selector )*
            {
                root_0 = (CommonTree) adaptor.nil();

                pushFollow(FOLLOW_selector_in_selectors289);
                selector20 = selector();

                state._fsp--;

                adaptor.addChild(root_0, selector20.getTree());
                // /home/langera/dev/freud/src/grammar/Css.g:61:13: ( ',' selector )*
                loop9:
                do {
                    int alt9 = 2;
                    int LA9_0 = input.LA(1);

                    if ((LA9_0 == 31)) {
                        alt9 = 1;
                    }


                    switch (alt9) {
                        case 1:
                            // /home/langera/dev/freud/src/grammar/Css.g:61:14: ',' selector
                        {
                            char_literal21 = (Token) match(input, 31, FOLLOW_31_in_selectors292);
                            char_literal21_tree = (CommonTree) adaptor.create(char_literal21);
                            adaptor.addChild(root_0, char_literal21_tree);

                            pushFollow(FOLLOW_selector_in_selectors294);
                            selector22 = selector();

                            state._fsp--;

                            adaptor.addChild(root_0, selector22.getTree());

                        }
                        break;

                        default:
                            break loop9;
                    }
                }
                while (true);


            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (CommonTree) adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "selectors"

    public static class selector_return extends ParserRuleReturnScope {
        CommonTree tree;

        public Object getTree() {
            return tree;
        }
    }

    ;

    // $ANTLR start "selector"
    // /home/langera/dev/freud/src/grammar/Css.g:64:1: selector : elem ( selectorOperation )* ( attrib )* ( pseudo )? -> elem ( selectorOperation )* ( attrib )* ( pseudo )* ;

    public final CssParser.selector_return selector() throws RecognitionException {
        CssParser.selector_return retval = new CssParser.selector_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        CssParser.elem_return elem23 = null;

        CssParser.selectorOperation_return selectorOperation24 = null;

        CssParser.attrib_return attrib25 = null;

        CssParser.pseudo_return pseudo26 = null;


        RewriteRuleSubtreeStream stream_elem = new RewriteRuleSubtreeStream(adaptor, "rule elem");
        RewriteRuleSubtreeStream stream_pseudo = new RewriteRuleSubtreeStream(adaptor, "rule pseudo");
        RewriteRuleSubtreeStream stream_selectorOperation = new RewriteRuleSubtreeStream(adaptor, "rule selectorOperation");
        RewriteRuleSubtreeStream stream_attrib = new RewriteRuleSubtreeStream(adaptor, "rule attrib");
        try {
            // /home/langera/dev/freud/src/grammar/Css.g:65:2: ( elem ( selectorOperation )* ( attrib )* ( pseudo )? -> elem ( selectorOperation )* ( attrib )* ( pseudo )* )
            // /home/langera/dev/freud/src/grammar/Css.g:65:4: elem ( selectorOperation )* ( attrib )* ( pseudo )?
            {
                pushFollow(FOLLOW_elem_in_selector308);
                elem23 = elem();

                state._fsp--;

                stream_elem.add(elem23.getTree());
                // /home/langera/dev/freud/src/grammar/Css.g:65:9: ( selectorOperation )*
                loop10:
                do {
                    int alt10 = 2;
                    alt10 = dfa10.predict(input);
                    switch (alt10) {
                        case 1:
                            // /home/langera/dev/freud/src/grammar/Css.g:65:9: selectorOperation
                        {
                            pushFollow(FOLLOW_selectorOperation_in_selector310);
                            selectorOperation24 = selectorOperation();

                            state._fsp--;

                            stream_selectorOperation.add(selectorOperation24.getTree());

                        }
                        break;

                        default:
                            break loop10;
                    }
                }
                while (true);

                // /home/langera/dev/freud/src/grammar/Css.g:65:28: ( attrib )*
                loop11:
                do {
                    int alt11 = 2;
                    int LA11_0 = input.LA(1);

                    if ((LA11_0 == 40)) {
                        alt11 = 1;
                    }


                    switch (alt11) {
                        case 1:
                            // /home/langera/dev/freud/src/grammar/Css.g:65:28: attrib
                        {
                            pushFollow(FOLLOW_attrib_in_selector313);
                            attrib25 = attrib();

                            state._fsp--;

                            stream_attrib.add(attrib25.getTree());

                        }
                        break;

                        default:
                            break loop11;
                    }
                }
                while (true);

                // /home/langera/dev/freud/src/grammar/Css.g:65:36: ( pseudo )?
                int alt12 = 2;
                int LA12_0 = input.LA(1);

                if (((LA12_0 >= 38 && LA12_0 <= 39))) {
                    alt12 = 1;
                }
                switch (alt12) {
                    case 1:
                        // /home/langera/dev/freud/src/grammar/Css.g:65:36: pseudo
                    {
                        pushFollow(FOLLOW_pseudo_in_selector316);
                        pseudo26 = pseudo();

                        state._fsp--;

                        stream_pseudo.add(pseudo26.getTree());

                    }
                    break;

                }


                // AST REWRITE
                // elements: elem, pseudo, attrib, selectorOperation
                // token labels:
                // rule labels: retval
                // token list labels:
                // rule list labels:
                // wildcard labels:
                retval.tree = root_0;
                RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.tree : null);

                root_0 = (CommonTree) adaptor.nil();
                // 65:44: -> elem ( selectorOperation )* ( attrib )* ( pseudo )*
                {
                    adaptor.addChild(root_0, stream_elem.nextTree());
                    // /home/langera/dev/freud/src/grammar/Css.g:65:53: ( selectorOperation )*
                    while (stream_selectorOperation.hasNext()) {
                        adaptor.addChild(root_0, stream_selectorOperation.nextTree());

                    }
                    stream_selectorOperation.reset();
                    // /home/langera/dev/freud/src/grammar/Css.g:65:72: ( attrib )*
                    while (stream_attrib.hasNext()) {
                        adaptor.addChild(root_0, stream_attrib.nextTree());

                    }
                    stream_attrib.reset();
                    // /home/langera/dev/freud/src/grammar/Css.g:65:80: ( pseudo )*
                    while (stream_pseudo.hasNext()) {
                        adaptor.addChild(root_0, stream_pseudo.nextTree());

                    }
                    stream_pseudo.reset();

                }

                retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (CommonTree) adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "selector"

    public static class selectorOperation_return extends ParserRuleReturnScope {
        CommonTree tree;

        public Object getTree() {
            return tree;
        }
    }

    ;

    // $ANTLR start "selectorOperation"
    // /home/langera/dev/freud/src/grammar/Css.g:68:1: selectorOperation : ( selectop )? elem -> ( selectop )* elem ;

    public final CssParser.selectorOperation_return selectorOperation() throws RecognitionException {
        CssParser.selectorOperation_return retval = new CssParser.selectorOperation_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        CssParser.selectop_return selectop27 = null;

        CssParser.elem_return elem28 = null;


        RewriteRuleSubtreeStream stream_elem = new RewriteRuleSubtreeStream(adaptor, "rule elem");
        RewriteRuleSubtreeStream stream_selectop = new RewriteRuleSubtreeStream(adaptor, "rule selectop");
        try {
            // /home/langera/dev/freud/src/grammar/Css.g:69:2: ( ( selectop )? elem -> ( selectop )* elem )
            // /home/langera/dev/freud/src/grammar/Css.g:69:4: ( selectop )? elem
            {
                // /home/langera/dev/freud/src/grammar/Css.g:69:4: ( selectop )?
                int alt13 = 2;
                int LA13_0 = input.LA(1);

                if (((LA13_0 >= 32 && LA13_0 <= 33))) {
                    alt13 = 1;
                }
                switch (alt13) {
                    case 1:
                        // /home/langera/dev/freud/src/grammar/Css.g:69:4: selectop
                    {
                        pushFollow(FOLLOW_selectop_in_selectorOperation342);
                        selectop27 = selectop();

                        state._fsp--;

                        stream_selectop.add(selectop27.getTree());

                    }
                    break;

                }

                pushFollow(FOLLOW_elem_in_selectorOperation345);
                elem28 = elem();

                state._fsp--;

                stream_elem.add(elem28.getTree());


                // AST REWRITE
                // elements: selectop, elem
                // token labels:
                // rule labels: retval
                // token list labels:
                // rule list labels:
                // wildcard labels:
                retval.tree = root_0;
                RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.tree : null);

                root_0 = (CommonTree) adaptor.nil();
                // 69:19: -> ( selectop )* elem
                {
                    // /home/langera/dev/freud/src/grammar/Css.g:69:22: ( selectop )*
                    while (stream_selectop.hasNext()) {
                        adaptor.addChild(root_0, stream_selectop.nextTree());

                    }
                    stream_selectop.reset();
                    adaptor.addChild(root_0, stream_elem.nextTree());

                }

                retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (CommonTree) adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "selectorOperation"

    public static class selectop_return extends ParserRuleReturnScope {
        CommonTree tree;

        public Object getTree() {
            return tree;
        }
    }

    ;

    // $ANTLR start "selectop"
    // /home/langera/dev/freud/src/grammar/Css.g:72:1: selectop : ( '>' -> CHILD | '+' -> ADJACENT_SIBLING );

    public final CssParser.selectop_return selectop() throws RecognitionException {
        CssParser.selectop_return retval = new CssParser.selectop_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token char_literal29 = null;
        Token char_literal30 = null;

        CommonTree char_literal29_tree = null;
        CommonTree char_literal30_tree = null;
        RewriteRuleTokenStream stream_32 = new RewriteRuleTokenStream(adaptor, "token 32");
        RewriteRuleTokenStream stream_33 = new RewriteRuleTokenStream(adaptor, "token 33");

        try {
            // /home/langera/dev/freud/src/grammar/Css.g:73:2: ( '>' -> CHILD | '+' -> ADJACENT_SIBLING )
            int alt14 = 2;
            int LA14_0 = input.LA(1);

            if ((LA14_0 == 32)) {
                alt14 = 1;
            }
            else if ((LA14_0 == 33)) {
                alt14 = 2;
            }
            else {
                NoViableAltException nvae =
                        new NoViableAltException("", 14, 0, input);

                throw nvae;
            }
            switch (alt14) {
                case 1:
                    // /home/langera/dev/freud/src/grammar/Css.g:73:4: '>'
                {
                    char_literal29 = (Token) match(input, 32, FOLLOW_32_in_selectop363);
                    stream_32.add(char_literal29);


                    // AST REWRITE
                    // elements:
                    // token labels:
                    // rule labels: retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.tree : null);

                    root_0 = (CommonTree) adaptor.nil();
                    // 73:8: -> CHILD
                    {
                        adaptor.addChild(root_0, (CommonTree) adaptor.create(CHILD, "CHILD"));

                    }

                    retval.tree = root_0;
                }
                break;
                case 2:
                    // /home/langera/dev/freud/src/grammar/Css.g:74:11: '+'
                {
                    char_literal30 = (Token) match(input, 33, FOLLOW_33_in_selectop379);
                    stream_33.add(char_literal30);


                    // AST REWRITE
                    // elements:
                    // token labels:
                    // rule labels: retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.tree : null);

                    root_0 = (CommonTree) adaptor.nil();
                    // 74:16: -> ADJACENT_SIBLING
                    {
                        adaptor.addChild(root_0, (CommonTree) adaptor.create(ADJACENT_SIBLING, "ADJACENT_SIBLING"));

                    }

                    retval.tree = root_0;
                }
                break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (CommonTree) adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "selectop"

    public static class properties_return extends ParserRuleReturnScope {
        CommonTree tree;

        public Object getTree() {
            return tree;
        }
    }

    ;

    // $ANTLR start "properties"
    // /home/langera/dev/freud/src/grammar/Css.g:77:1: properties : declaration ( ';' ( declaration )? )* -> ( declaration )+ ;

    public final CssParser.properties_return properties() throws RecognitionException {
        CssParser.properties_return retval = new CssParser.properties_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token char_literal32 = null;
        CssParser.declaration_return declaration31 = null;

        CssParser.declaration_return declaration33 = null;


        CommonTree char_literal32_tree = null;
        RewriteRuleTokenStream stream_34 = new RewriteRuleTokenStream(adaptor, "token 34");
        RewriteRuleSubtreeStream stream_declaration = new RewriteRuleSubtreeStream(adaptor, "rule declaration");
        try {
            // /home/langera/dev/freud/src/grammar/Css.g:78:2: ( declaration ( ';' ( declaration )? )* -> ( declaration )+ )
            // /home/langera/dev/freud/src/grammar/Css.g:78:4: declaration ( ';' ( declaration )? )*
            {
                pushFollow(FOLLOW_declaration_in_properties395);
                declaration31 = declaration();

                state._fsp--;

                stream_declaration.add(declaration31.getTree());
                // /home/langera/dev/freud/src/grammar/Css.g:78:16: ( ';' ( declaration )? )*
                loop16:
                do {
                    int alt16 = 2;
                    int LA16_0 = input.LA(1);

                    if ((LA16_0 == 34)) {
                        alt16 = 1;
                    }


                    switch (alt16) {
                        case 1:
                            // /home/langera/dev/freud/src/grammar/Css.g:78:17: ';' ( declaration )?
                        {
                            char_literal32 = (Token) match(input, 34, FOLLOW_34_in_properties398);
                            stream_34.add(char_literal32);

                            // /home/langera/dev/freud/src/grammar/Css.g:78:21: ( declaration )?
                            int alt15 = 2;
                            int LA15_0 = input.LA(1);

                            if ((LA15_0 == IDENT)) {
                                alt15 = 1;
                            }
                            switch (alt15) {
                                case 1:
                                    // /home/langera/dev/freud/src/grammar/Css.g:78:21: declaration
                                {
                                    pushFollow(FOLLOW_declaration_in_properties400);
                                    declaration33 = declaration();

                                    state._fsp--;

                                    stream_declaration.add(declaration33.getTree());

                                }
                                break;

                            }


                        }
                        break;

                        default:
                            break loop16;
                    }
                }
                while (true);


                // AST REWRITE
                // elements: declaration
                // token labels:
                // rule labels: retval
                // token list labels:
                // rule list labels:
                // wildcard labels:
                retval.tree = root_0;
                RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.tree : null);

                root_0 = (CommonTree) adaptor.nil();
                // 78:36: -> ( declaration )+
                {
                    if (!(stream_declaration.hasNext())) {
                        throw new RewriteEarlyExitException();
                    }
                    while (stream_declaration.hasNext()) {
                        adaptor.addChild(root_0, stream_declaration.nextTree());

                    }
                    stream_declaration.reset();

                }

                retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (CommonTree) adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "properties"

    public static class elem_return extends ParserRuleReturnScope {
        CommonTree tree;

        public Object getTree() {
            return tree;
        }
    }

    ;

    // $ANTLR start "elem"
    // /home/langera/dev/freud/src/grammar/Css.g:81:1: elem : ( '*' -> ^( UNIVERSAL ) | '#' IDENT -> ^( ID IDENT ) | '.' IDENT -> ^( CLASS IDENT ) | IDENT -> ^( TAG IDENT ) );

    public final CssParser.elem_return elem() throws RecognitionException {
        CssParser.elem_return retval = new CssParser.elem_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token char_literal34 = null;
        Token char_literal35 = null;
        Token IDENT36 = null;
        Token char_literal37 = null;
        Token IDENT38 = null;
        Token IDENT39 = null;

        CommonTree char_literal34_tree = null;
        CommonTree char_literal35_tree = null;
        CommonTree IDENT36_tree = null;
        CommonTree char_literal37_tree = null;
        CommonTree IDENT38_tree = null;
        CommonTree IDENT39_tree = null;
        RewriteRuleTokenStream stream_IDENT = new RewriteRuleTokenStream(adaptor, "token IDENT");
        RewriteRuleTokenStream stream_35 = new RewriteRuleTokenStream(adaptor, "token 35");
        RewriteRuleTokenStream stream_36 = new RewriteRuleTokenStream(adaptor, "token 36");
        RewriteRuleTokenStream stream_37 = new RewriteRuleTokenStream(adaptor, "token 37");

        try {
            // /home/langera/dev/freud/src/grammar/Css.g:82:2: ( '*' -> ^( UNIVERSAL ) | '#' IDENT -> ^( ID IDENT ) | '.' IDENT -> ^( CLASS IDENT ) | IDENT -> ^( TAG IDENT ) )
            int alt17 = 4;
            switch (input.LA(1)) {
                case 35: {
                    alt17 = 1;
                }
                break;
                case 36: {
                    alt17 = 2;
                }
                break;
                case 37: {
                    alt17 = 3;
                }
                break;
                case IDENT: {
                    alt17 = 4;
                }
                break;
                default:
                    NoViableAltException nvae =
                            new NoViableAltException("", 17, 0, input);

                    throw nvae;
            }

            switch (alt17) {
                case 1:
                    // /home/langera/dev/freud/src/grammar/Css.g:82:4: '*'
                {
                    char_literal34 = (Token) match(input, 35, FOLLOW_35_in_elem421);
                    stream_35.add(char_literal34);


                    // AST REWRITE
                    // elements:
                    // token labels:
                    // rule labels: retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.tree : null);

                    root_0 = (CommonTree) adaptor.nil();
                    // 82:8: -> ^( UNIVERSAL )
                    {
                        // /home/langera/dev/freud/src/grammar/Css.g:82:11: ^( UNIVERSAL )
                        {
                            CommonTree root_1 = (CommonTree) adaptor.nil();
                            root_1 = (CommonTree) adaptor.becomeRoot((CommonTree) adaptor.create(UNIVERSAL, "UNIVERSAL"), root_1);

                            adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                }
                break;
                case 2:
                    // /home/langera/dev/freud/src/grammar/Css.g:83:4: '#' IDENT
                {
                    char_literal35 = (Token) match(input, 36, FOLLOW_36_in_elem434);
                    stream_36.add(char_literal35);

                    IDENT36 = (Token) match(input, IDENT, FOLLOW_IDENT_in_elem436);
                    stream_IDENT.add(IDENT36);


                    // AST REWRITE
                    // elements: IDENT
                    // token labels:
                    // rule labels: retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.tree : null);

                    root_0 = (CommonTree) adaptor.nil();
                    // 83:14: -> ^( ID IDENT )
                    {
                        // /home/langera/dev/freud/src/grammar/Css.g:83:17: ^( ID IDENT )
                        {
                            CommonTree root_1 = (CommonTree) adaptor.nil();
                            root_1 = (CommonTree) adaptor.becomeRoot((CommonTree) adaptor.create(ID, "ID"), root_1);

                            adaptor.addChild(root_1, stream_IDENT.nextNode());

                            adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                }
                break;
                case 3:
                    // /home/langera/dev/freud/src/grammar/Css.g:84:4: '.' IDENT
                {
                    char_literal37 = (Token) match(input, 37, FOLLOW_37_in_elem451);
                    stream_37.add(char_literal37);

                    IDENT38 = (Token) match(input, IDENT, FOLLOW_IDENT_in_elem453);
                    stream_IDENT.add(IDENT38);


                    // AST REWRITE
                    // elements: IDENT
                    // token labels:
                    // rule labels: retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.tree : null);

                    root_0 = (CommonTree) adaptor.nil();
                    // 84:14: -> ^( CLASS IDENT )
                    {
                        // /home/langera/dev/freud/src/grammar/Css.g:84:17: ^( CLASS IDENT )
                        {
                            CommonTree root_1 = (CommonTree) adaptor.nil();
                            root_1 = (CommonTree) adaptor.becomeRoot((CommonTree) adaptor.create(CLASS, "CLASS"), root_1);

                            adaptor.addChild(root_1, stream_IDENT.nextNode());

                            adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                }
                break;
                case 4:
                    // /home/langera/dev/freud/src/grammar/Css.g:85:4: IDENT
                {
                    IDENT39 = (Token) match(input, IDENT, FOLLOW_IDENT_in_elem471);
                    stream_IDENT.add(IDENT39);


                    // AST REWRITE
                    // elements: IDENT
                    // token labels:
                    // rule labels: retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.tree : null);

                    root_0 = (CommonTree) adaptor.nil();
                    // 85:10: -> ^( TAG IDENT )
                    {
                        // /home/langera/dev/freud/src/grammar/Css.g:85:13: ^( TAG IDENT )
                        {
                            CommonTree root_1 = (CommonTree) adaptor.nil();
                            root_1 = (CommonTree) adaptor.becomeRoot((CommonTree) adaptor.create(TAG, "TAG"), root_1);

                            adaptor.addChild(root_1, stream_IDENT.nextNode());

                            adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                }
                break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (CommonTree) adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "elem"

    public static class pseudo_return extends ParserRuleReturnScope {
        CommonTree tree;

        public Object getTree() {
            return tree;
        }
    }

    ;

    // $ANTLR start "pseudo"
    // /home/langera/dev/freud/src/grammar/Css.g:88:1: pseudo : ( ( ':' | '::' ) IDENT -> ^( PSEUDO IDENT ) | ( ':' | '::' ) function -> ^( PSEUDO function ) );

    public final CssParser.pseudo_return pseudo() throws RecognitionException {
        CssParser.pseudo_return retval = new CssParser.pseudo_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token char_literal40 = null;
        Token string_literal41 = null;
        Token IDENT42 = null;
        Token char_literal43 = null;
        Token string_literal44 = null;
        CssParser.function_return function45 = null;


        CommonTree char_literal40_tree = null;
        CommonTree string_literal41_tree = null;
        CommonTree IDENT42_tree = null;
        CommonTree char_literal43_tree = null;
        CommonTree string_literal44_tree = null;
        RewriteRuleTokenStream stream_IDENT = new RewriteRuleTokenStream(adaptor, "token IDENT");
        RewriteRuleTokenStream stream_39 = new RewriteRuleTokenStream(adaptor, "token 39");
        RewriteRuleTokenStream stream_38 = new RewriteRuleTokenStream(adaptor, "token 38");
        RewriteRuleSubtreeStream stream_function = new RewriteRuleSubtreeStream(adaptor, "rule function");
        try {
            // /home/langera/dev/freud/src/grammar/Css.g:89:2: ( ( ':' | '::' ) IDENT -> ^( PSEUDO IDENT ) | ( ':' | '::' ) function -> ^( PSEUDO function ) )
            int alt20 = 2;
            alt20 = dfa20.predict(input);
            switch (alt20) {
                case 1:
                    // /home/langera/dev/freud/src/grammar/Css.g:89:4: ( ':' | '::' ) IDENT
                {
                    // /home/langera/dev/freud/src/grammar/Css.g:89:4: ( ':' | '::' )
                    int alt18 = 2;
                    int LA18_0 = input.LA(1);

                    if ((LA18_0 == 38)) {
                        alt18 = 1;
                    }
                    else if ((LA18_0 == 39)) {
                        alt18 = 2;
                    }
                    else {
                        NoViableAltException nvae =
                                new NoViableAltException("", 18, 0, input);

                        throw nvae;
                    }
                    switch (alt18) {
                        case 1:
                            // /home/langera/dev/freud/src/grammar/Css.g:89:5: ':'
                        {
                            char_literal40 = (Token) match(input, 38, FOLLOW_38_in_pseudo493);
                            stream_38.add(char_literal40);


                        }
                        break;
                        case 2:
                            // /home/langera/dev/freud/src/grammar/Css.g:89:9: '::'
                        {
                            string_literal41 = (Token) match(input, 39, FOLLOW_39_in_pseudo495);
                            stream_39.add(string_literal41);


                        }
                        break;

                    }

                    IDENT42 = (Token) match(input, IDENT, FOLLOW_IDENT_in_pseudo498);
                    stream_IDENT.add(IDENT42);


                    // AST REWRITE
                    // elements: IDENT
                    // token labels:
                    // rule labels: retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.tree : null);

                    root_0 = (CommonTree) adaptor.nil();
                    // 89:21: -> ^( PSEUDO IDENT )
                    {
                        // /home/langera/dev/freud/src/grammar/Css.g:89:24: ^( PSEUDO IDENT )
                        {
                            CommonTree root_1 = (CommonTree) adaptor.nil();
                            root_1 = (CommonTree) adaptor.becomeRoot((CommonTree) adaptor.create(PSEUDO, "PSEUDO"), root_1);

                            adaptor.addChild(root_1, stream_IDENT.nextNode());

                            adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                }
                break;
                case 2:
                    // /home/langera/dev/freud/src/grammar/Css.g:90:4: ( ':' | '::' ) function
                {
                    // /home/langera/dev/freud/src/grammar/Css.g:90:4: ( ':' | '::' )
                    int alt19 = 2;
                    int LA19_0 = input.LA(1);

                    if ((LA19_0 == 38)) {
                        alt19 = 1;
                    }
                    else if ((LA19_0 == 39)) {
                        alt19 = 2;
                    }
                    else {
                        NoViableAltException nvae =
                                new NoViableAltException("", 19, 0, input);

                        throw nvae;
                    }
                    switch (alt19) {
                        case 1:
                            // /home/langera/dev/freud/src/grammar/Css.g:90:5: ':'
                        {
                            char_literal43 = (Token) match(input, 38, FOLLOW_38_in_pseudo514);
                            stream_38.add(char_literal43);


                        }
                        break;
                        case 2:
                            // /home/langera/dev/freud/src/grammar/Css.g:90:9: '::'
                        {
                            string_literal44 = (Token) match(input, 39, FOLLOW_39_in_pseudo516);
                            stream_39.add(string_literal44);


                        }
                        break;

                    }

                    pushFollow(FOLLOW_function_in_pseudo519);
                    function45 = function();

                    state._fsp--;

                    stream_function.add(function45.getTree());


                    // AST REWRITE
                    // elements: function
                    // token labels:
                    // rule labels: retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.tree : null);

                    root_0 = (CommonTree) adaptor.nil();
                    // 90:24: -> ^( PSEUDO function )
                    {
                        // /home/langera/dev/freud/src/grammar/Css.g:90:27: ^( PSEUDO function )
                        {
                            CommonTree root_1 = (CommonTree) adaptor.nil();
                            root_1 = (CommonTree) adaptor.becomeRoot((CommonTree) adaptor.create(PSEUDO, "PSEUDO"), root_1);

                            adaptor.addChild(root_1, stream_function.nextTree());

                            adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                }
                break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (CommonTree) adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "pseudo"

    public static class attrib_return extends ParserRuleReturnScope {
        CommonTree tree;

        public Object getTree() {
            return tree;
        }
    }

    ;

    // $ANTLR start "attrib"
    // /home/langera/dev/freud/src/grammar/Css.g:93:1: attrib : '[' IDENT ( attribRelate IDENT )? ']' -> ^( ATTRIB IDENT ( attribRelate ( IDENT )* )? ) ;

    public final CssParser.attrib_return attrib() throws RecognitionException {
        CssParser.attrib_return retval = new CssParser.attrib_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token char_literal46 = null;
        Token IDENT47 = null;
        Token IDENT49 = null;
        Token char_literal50 = null;
        CssParser.attribRelate_return attribRelate48 = null;


        CommonTree char_literal46_tree = null;
        CommonTree IDENT47_tree = null;
        CommonTree IDENT49_tree = null;
        CommonTree char_literal50_tree = null;
        RewriteRuleTokenStream stream_IDENT = new RewriteRuleTokenStream(adaptor, "token IDENT");
        RewriteRuleTokenStream stream_41 = new RewriteRuleTokenStream(adaptor, "token 41");
        RewriteRuleTokenStream stream_40 = new RewriteRuleTokenStream(adaptor, "token 40");
        RewriteRuleSubtreeStream stream_attribRelate = new RewriteRuleSubtreeStream(adaptor, "rule attribRelate");
        try {
            // /home/langera/dev/freud/src/grammar/Css.g:94:2: ( '[' IDENT ( attribRelate IDENT )? ']' -> ^( ATTRIB IDENT ( attribRelate ( IDENT )* )? ) )
            // /home/langera/dev/freud/src/grammar/Css.g:94:4: '[' IDENT ( attribRelate IDENT )? ']'
            {
                char_literal46 = (Token) match(input, 40, FOLLOW_40_in_attrib540);
                stream_40.add(char_literal46);

                IDENT47 = (Token) match(input, IDENT, FOLLOW_IDENT_in_attrib542);
                stream_IDENT.add(IDENT47);

                // /home/langera/dev/freud/src/grammar/Css.g:94:14: ( attribRelate IDENT )?
                int alt21 = 2;
                int LA21_0 = input.LA(1);

                if (((LA21_0 >= 42 && LA21_0 <= 44))) {
                    alt21 = 1;
                }
                switch (alt21) {
                    case 1:
                        // /home/langera/dev/freud/src/grammar/Css.g:94:15: attribRelate IDENT
                    {
                        pushFollow(FOLLOW_attribRelate_in_attrib545);
                        attribRelate48 = attribRelate();

                        state._fsp--;

                        stream_attribRelate.add(attribRelate48.getTree());
                        IDENT49 = (Token) match(input, IDENT, FOLLOW_IDENT_in_attrib547);
                        stream_IDENT.add(IDENT49);


                    }
                    break;

                }

                char_literal50 = (Token) match(input, 41, FOLLOW_41_in_attrib551);
                stream_41.add(char_literal50);


                // AST REWRITE
                // elements: attribRelate, IDENT, IDENT
                // token labels:
                // rule labels: retval
                // token list labels:
                // rule list labels:
                // wildcard labels:
                retval.tree = root_0;
                RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.tree : null);

                root_0 = (CommonTree) adaptor.nil();
                // 94:40: -> ^( ATTRIB IDENT ( attribRelate ( IDENT )* )? )
                {
                    // /home/langera/dev/freud/src/grammar/Css.g:94:43: ^( ATTRIB IDENT ( attribRelate ( IDENT )* )? )
                    {
                        CommonTree root_1 = (CommonTree) adaptor.nil();
                        root_1 = (CommonTree) adaptor.becomeRoot((CommonTree) adaptor.create(ATTRIB, "ATTRIB"), root_1);

                        adaptor.addChild(root_1, stream_IDENT.nextNode());
                        // /home/langera/dev/freud/src/grammar/Css.g:94:59: ( attribRelate ( IDENT )* )?
                        if (stream_attribRelate.hasNext() || stream_IDENT.hasNext()) {
                            adaptor.addChild(root_1, stream_attribRelate.nextTree());
                            // /home/langera/dev/freud/src/grammar/Css.g:94:73: ( IDENT )*
                            while (stream_IDENT.hasNext()) {
                                adaptor.addChild(root_1, stream_IDENT.nextNode());

                            }
                            stream_IDENT.reset();

                        }
                        stream_attribRelate.reset();
                        stream_IDENT.reset();

                        adaptor.addChild(root_0, root_1);
                    }

                }

                retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (CommonTree) adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "attrib"

    public static class attribRelate_return extends ParserRuleReturnScope {
        CommonTree tree;

        public Object getTree() {
            return tree;
        }
    }

    ;

    // $ANTLR start "attribRelate"
    // /home/langera/dev/freud/src/grammar/Css.g:97:1: attribRelate : ( '=' -> ATTRIBEQUAL | '~=' -> HASVALUE | '|=' -> BEGINSWITH );

    public final CssParser.attribRelate_return attribRelate() throws RecognitionException {
        CssParser.attribRelate_return retval = new CssParser.attribRelate_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token char_literal51 = null;
        Token string_literal52 = null;
        Token string_literal53 = null;

        CommonTree char_literal51_tree = null;
        CommonTree string_literal52_tree = null;
        CommonTree string_literal53_tree = null;
        RewriteRuleTokenStream stream_43 = new RewriteRuleTokenStream(adaptor, "token 43");
        RewriteRuleTokenStream stream_44 = new RewriteRuleTokenStream(adaptor, "token 44");
        RewriteRuleTokenStream stream_42 = new RewriteRuleTokenStream(adaptor, "token 42");

        try {
            // /home/langera/dev/freud/src/grammar/Css.g:98:2: ( '=' -> ATTRIBEQUAL | '~=' -> HASVALUE | '|=' -> BEGINSWITH )
            int alt22 = 3;
            switch (input.LA(1)) {
                case 42: {
                    alt22 = 1;
                }
                break;
                case 43: {
                    alt22 = 2;
                }
                break;
                case 44: {
                    alt22 = 3;
                }
                break;
                default:
                    NoViableAltException nvae =
                            new NoViableAltException("", 22, 0, input);

                    throw nvae;
            }

            switch (alt22) {
                case 1:
                    // /home/langera/dev/freud/src/grammar/Css.g:98:4: '='
                {
                    char_literal51 = (Token) match(input, 42, FOLLOW_42_in_attribRelate581);
                    stream_42.add(char_literal51);


                    // AST REWRITE
                    // elements:
                    // token labels:
                    // rule labels: retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.tree : null);

                    root_0 = (CommonTree) adaptor.nil();
                    // 98:9: -> ATTRIBEQUAL
                    {
                        adaptor.addChild(root_0, (CommonTree) adaptor.create(ATTRIBEQUAL, "ATTRIBEQUAL"));

                    }

                    retval.tree = root_0;
                }
                break;
                case 2:
                    // /home/langera/dev/freud/src/grammar/Css.g:99:4: '~='
                {
                    string_literal52 = (Token) match(input, 43, FOLLOW_43_in_attribRelate591);
                    stream_43.add(string_literal52);


                    // AST REWRITE
                    // elements:
                    // token labels:
                    // rule labels: retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.tree : null);

                    root_0 = (CommonTree) adaptor.nil();
                    // 99:9: -> HASVALUE
                    {
                        adaptor.addChild(root_0, (CommonTree) adaptor.create(HASVALUE, "HASVALUE"));

                    }

                    retval.tree = root_0;
                }
                break;
                case 3:
                    // /home/langera/dev/freud/src/grammar/Css.g:100:4: '|='
                {
                    string_literal53 = (Token) match(input, 44, FOLLOW_44_in_attribRelate600);
                    stream_44.add(string_literal53);


                    // AST REWRITE
                    // elements:
                    // token labels:
                    // rule labels: retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.tree : null);

                    root_0 = (CommonTree) adaptor.nil();
                    // 100:9: -> BEGINSWITH
                    {
                        adaptor.addChild(root_0, (CommonTree) adaptor.create(BEGINSWITH, "BEGINSWITH"));

                    }

                    retval.tree = root_0;
                }
                break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (CommonTree) adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "attribRelate"

    public static class declaration_return extends ParserRuleReturnScope {
        CommonTree tree;

        public Object getTree() {
            return tree;
        }
    }

    ;

    // $ANTLR start "declaration"
    // /home/langera/dev/freud/src/grammar/Css.g:103:1: declaration : IDENT ':' args -> ^( PROPERTY IDENT args ) ;

    public final CssParser.declaration_return declaration() throws RecognitionException {
        CssParser.declaration_return retval = new CssParser.declaration_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token IDENT54 = null;
        Token char_literal55 = null;
        CssParser.args_return args56 = null;


        CommonTree IDENT54_tree = null;
        CommonTree char_literal55_tree = null;
        RewriteRuleTokenStream stream_IDENT = new RewriteRuleTokenStream(adaptor, "token IDENT");
        RewriteRuleTokenStream stream_38 = new RewriteRuleTokenStream(adaptor, "token 38");
        RewriteRuleSubtreeStream stream_args = new RewriteRuleSubtreeStream(adaptor, "rule args");
        try {
            // /home/langera/dev/freud/src/grammar/Css.g:104:2: ( IDENT ':' args -> ^( PROPERTY IDENT args ) )
            // /home/langera/dev/freud/src/grammar/Css.g:104:4: IDENT ':' args
            {
                IDENT54 = (Token) match(input, IDENT, FOLLOW_IDENT_in_declaration618);
                stream_IDENT.add(IDENT54);

                char_literal55 = (Token) match(input, 38, FOLLOW_38_in_declaration620);
                stream_38.add(char_literal55);

                pushFollow(FOLLOW_args_in_declaration622);
                args56 = args();

                state._fsp--;

                stream_args.add(args56.getTree());


                // AST REWRITE
                // elements: IDENT, args
                // token labels:
                // rule labels: retval
                // token list labels:
                // rule list labels:
                // wildcard labels:
                retval.tree = root_0;
                RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.tree : null);

                root_0 = (CommonTree) adaptor.nil();
                // 104:19: -> ^( PROPERTY IDENT args )
                {
                    // /home/langera/dev/freud/src/grammar/Css.g:104:22: ^( PROPERTY IDENT args )
                    {
                        CommonTree root_1 = (CommonTree) adaptor.nil();
                        root_1 = (CommonTree) adaptor.becomeRoot((CommonTree) adaptor.create(PROPERTY, "PROPERTY"), root_1);

                        adaptor.addChild(root_1, stream_IDENT.nextNode());
                        adaptor.addChild(root_1, stream_args.nextTree());

                        adaptor.addChild(root_0, root_1);
                    }

                }

                retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (CommonTree) adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "declaration"

    public static class args_return extends ParserRuleReturnScope {
        CommonTree tree;

        public Object getTree() {
            return tree;
        }
    }

    ;

    // $ANTLR start "args"
    // /home/langera/dev/freud/src/grammar/Css.g:107:1: args : expr ( ( ',' )? expr )* -> ( expr )* ;

    public final CssParser.args_return args() throws RecognitionException {
        CssParser.args_return retval = new CssParser.args_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token char_literal58 = null;
        CssParser.expr_return expr57 = null;

        CssParser.expr_return expr59 = null;


        CommonTree char_literal58_tree = null;
        RewriteRuleTokenStream stream_31 = new RewriteRuleTokenStream(adaptor, "token 31");
        RewriteRuleSubtreeStream stream_expr = new RewriteRuleSubtreeStream(adaptor, "rule expr");
        try {
            // /home/langera/dev/freud/src/grammar/Css.g:108:2: ( expr ( ( ',' )? expr )* -> ( expr )* )
            // /home/langera/dev/freud/src/grammar/Css.g:108:4: expr ( ( ',' )? expr )*
            {
                pushFollow(FOLLOW_expr_in_args645);
                expr57 = expr();

                state._fsp--;

                stream_expr.add(expr57.getTree());
                // /home/langera/dev/freud/src/grammar/Css.g:108:9: ( ( ',' )? expr )*
                loop24:
                do {
                    int alt24 = 2;
                    int LA24_0 = input.LA(1);

                    if ((LA24_0 == IDENT || LA24_0 == 31 || (LA24_0 >= 36 && LA24_0 <= 37))) {
                        alt24 = 1;
                    }


                    switch (alt24) {
                        case 1:
                            // /home/langera/dev/freud/src/grammar/Css.g:108:10: ( ',' )? expr
                        {
                            // /home/langera/dev/freud/src/grammar/Css.g:108:10: ( ',' )?
                            int alt23 = 2;
                            int LA23_0 = input.LA(1);

                            if ((LA23_0 == 31)) {
                                alt23 = 1;
                            }
                            switch (alt23) {
                                case 1:
                                    // /home/langera/dev/freud/src/grammar/Css.g:108:10: ','
                                {
                                    char_literal58 = (Token) match(input, 31, FOLLOW_31_in_args648);
                                    stream_31.add(char_literal58);


                                }
                                break;

                            }

                            pushFollow(FOLLOW_expr_in_args651);
                            expr59 = expr();

                            state._fsp--;

                            stream_expr.add(expr59.getTree());

                        }
                        break;

                        default:
                            break loop24;
                    }
                }
                while (true);


                // AST REWRITE
                // elements: expr
                // token labels:
                // rule labels: retval
                // token list labels:
                // rule list labels:
                // wildcard labels:
                retval.tree = root_0;
                RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.tree : null);

                root_0 = (CommonTree) adaptor.nil();
                // 108:22: -> ( expr )*
                {
                    // /home/langera/dev/freud/src/grammar/Css.g:108:25: ( expr )*
                    while (stream_expr.hasNext()) {
                        adaptor.addChild(root_0, stream_expr.nextTree());

                    }
                    stream_expr.reset();

                }

                retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (CommonTree) adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "args"

    public static class expr_return extends ParserRuleReturnScope {
        CommonTree tree;

        public Object getTree() {
            return tree;
        }
    }

    ;

    // $ANTLR start "expr"
    // /home/langera/dev/freud/src/grammar/Css.g:111:1: expr : ( ( IDENT unit ) | IDENT | '#' IDENT -> ^( COLOUR '#' IDENT ) | '.' IDENT | function );

    public final CssParser.expr_return expr() throws RecognitionException {
        CssParser.expr_return retval = new CssParser.expr_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token IDENT60 = null;
        Token IDENT62 = null;
        Token char_literal63 = null;
        Token IDENT64 = null;
        Token char_literal65 = null;
        Token IDENT66 = null;
        CssParser.unit_return unit61 = null;

        CssParser.function_return function67 = null;


        CommonTree IDENT60_tree = null;
        CommonTree IDENT62_tree = null;
        CommonTree char_literal63_tree = null;
        CommonTree IDENT64_tree = null;
        CommonTree char_literal65_tree = null;
        CommonTree IDENT66_tree = null;
        RewriteRuleTokenStream stream_IDENT = new RewriteRuleTokenStream(adaptor, "token IDENT");
        RewriteRuleTokenStream stream_36 = new RewriteRuleTokenStream(adaptor, "token 36");

        try {
            // /home/langera/dev/freud/src/grammar/Css.g:112:2: ( ( IDENT unit ) | IDENT | '#' IDENT -> ^( COLOUR '#' IDENT ) | '.' IDENT | function )
            int alt25 = 5;
            alt25 = dfa25.predict(input);
            switch (alt25) {
                case 1:
                    // /home/langera/dev/freud/src/grammar/Css.g:112:4: ( IDENT unit )
                {
                    root_0 = (CommonTree) adaptor.nil();

                    // /home/langera/dev/freud/src/grammar/Css.g:112:4: ( IDENT unit )
                    // /home/langera/dev/freud/src/grammar/Css.g:112:5: IDENT unit
                    {
                        IDENT60 = (Token) match(input, IDENT, FOLLOW_IDENT_in_expr670);
                        IDENT60_tree = (CommonTree) adaptor.create(IDENT60);
                        adaptor.addChild(root_0, IDENT60_tree);

                        pushFollow(FOLLOW_unit_in_expr672);
                        unit61 = unit();

                        state._fsp--;

                        adaptor.addChild(root_0, unit61.getTree());

                    }


                }
                break;
                case 2:
                    // /home/langera/dev/freud/src/grammar/Css.g:113:4: IDENT
                {
                    root_0 = (CommonTree) adaptor.nil();

                    IDENT62 = (Token) match(input, IDENT, FOLLOW_IDENT_in_expr678);
                    IDENT62_tree = (CommonTree) adaptor.create(IDENT62);
                    adaptor.addChild(root_0, IDENT62_tree);


                }
                break;
                case 3:
                    // /home/langera/dev/freud/src/grammar/Css.g:114:4: '#' IDENT
                {
                    char_literal63 = (Token) match(input, 36, FOLLOW_36_in_expr683);
                    stream_36.add(char_literal63);

                    IDENT64 = (Token) match(input, IDENT, FOLLOW_IDENT_in_expr685);
                    stream_IDENT.add(IDENT64);


                    // AST REWRITE
                    // elements: 36, IDENT
                    // token labels:
                    // rule labels: retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.tree : null);

                    root_0 = (CommonTree) adaptor.nil();
                    // 114:14: -> ^( COLOUR '#' IDENT )
                    {
                        // /home/langera/dev/freud/src/grammar/Css.g:114:17: ^( COLOUR '#' IDENT )
                        {
                            CommonTree root_1 = (CommonTree) adaptor.nil();
                            root_1 = (CommonTree) adaptor.becomeRoot((CommonTree) adaptor.create(COLOUR, "COLOUR"), root_1);

                            adaptor.addChild(root_1, stream_36.nextNode());
                            adaptor.addChild(root_1, stream_IDENT.nextNode());

                            adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                }
                break;
                case 4:
                    // /home/langera/dev/freud/src/grammar/Css.g:115:4: '.' IDENT
                {
                    root_0 = (CommonTree) adaptor.nil();

                    char_literal65 = (Token) match(input, 37, FOLLOW_37_in_expr702);
                    char_literal65_tree = (CommonTree) adaptor.create(char_literal65);
                    adaptor.addChild(root_0, char_literal65_tree);

                    IDENT66 = (Token) match(input, IDENT, FOLLOW_IDENT_in_expr704);
                    IDENT66_tree = (CommonTree) adaptor.create(IDENT66);
                    adaptor.addChild(root_0, IDENT66_tree);


                }
                break;
                case 5:
                    // /home/langera/dev/freud/src/grammar/Css.g:116:4: function
                {
                    root_0 = (CommonTree) adaptor.nil();

                    pushFollow(FOLLOW_function_in_expr709);
                    function67 = function();

                    state._fsp--;

                    adaptor.addChild(root_0, function67.getTree());

                }
                break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (CommonTree) adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "expr"

    public static class unit_return extends ParserRuleReturnScope {
        CommonTree tree;

        public Object getTree() {
            return tree;
        }
    }

    ;

    // $ANTLR start "unit"
    // /home/langera/dev/freud/src/grammar/Css.g:119:1: unit : ( '%' | 'px' | 'cm' | 'mm' | 'in' | 'pt' | 'pc' | 'em' | 'ex' | 'deg' | 'rad' | 'grad' | 'ms' | 's' | 'hz' | 'khz' ) ;

    public final CssParser.unit_return unit() throws RecognitionException {
        CssParser.unit_return retval = new CssParser.unit_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token set68 = null;

        CommonTree set68_tree = null;

        try {
            // /home/langera/dev/freud/src/grammar/Css.g:120:2: ( ( '%' | 'px' | 'cm' | 'mm' | 'in' | 'pt' | 'pc' | 'em' | 'ex' | 'deg' | 'rad' | 'grad' | 'ms' | 's' | 'hz' | 'khz' ) )
            // /home/langera/dev/freud/src/grammar/Css.g:120:4: ( '%' | 'px' | 'cm' | 'mm' | 'in' | 'pt' | 'pc' | 'em' | 'ex' | 'deg' | 'rad' | 'grad' | 'ms' | 's' | 'hz' | 'khz' )
            {
                root_0 = (CommonTree) adaptor.nil();

                set68 = (Token) input.LT(1);
                if ((input.LA(1) >= 45 && input.LA(1) <= 60)) {
                    input.consume();
                    adaptor.addChild(root_0, (CommonTree) adaptor.create(set68));
                    state.errorRecovery = false;
                }
                else {
                    MismatchedSetException mse = new MismatchedSetException(null, input);
                    throw mse;
                }


            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (CommonTree) adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "unit"

    public static class function_return extends ParserRuleReturnScope {
        CommonTree tree;

        public Object getTree() {
            return tree;
        }
    }

    ;

    // $ANTLR start "function"
    // /home/langera/dev/freud/src/grammar/Css.g:123:1: function : IDENT '(' ( args )? ')' -> IDENT '(' ( args )* ')' ;

    public final CssParser.function_return function() throws RecognitionException {
        CssParser.function_return retval = new CssParser.function_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token IDENT69 = null;
        Token char_literal70 = null;
        Token char_literal72 = null;
        CssParser.args_return args71 = null;


        CommonTree IDENT69_tree = null;
        CommonTree char_literal70_tree = null;
        CommonTree char_literal72_tree = null;
        RewriteRuleTokenStream stream_IDENT = new RewriteRuleTokenStream(adaptor, "token IDENT");
        RewriteRuleTokenStream stream_62 = new RewriteRuleTokenStream(adaptor, "token 62");
        RewriteRuleTokenStream stream_61 = new RewriteRuleTokenStream(adaptor, "token 61");
        RewriteRuleSubtreeStream stream_args = new RewriteRuleSubtreeStream(adaptor, "rule args");
        try {
            // /home/langera/dev/freud/src/grammar/Css.g:124:2: ( IDENT '(' ( args )? ')' -> IDENT '(' ( args )* ')' )
            // /home/langera/dev/freud/src/grammar/Css.g:124:4: IDENT '(' ( args )? ')'
            {
                IDENT69 = (Token) match(input, IDENT, FOLLOW_IDENT_in_function767);
                stream_IDENT.add(IDENT69);

                char_literal70 = (Token) match(input, 61, FOLLOW_61_in_function769);
                stream_61.add(char_literal70);

                // /home/langera/dev/freud/src/grammar/Css.g:124:14: ( args )?
                int alt26 = 2;
                int LA26_0 = input.LA(1);

                if ((LA26_0 == IDENT || (LA26_0 >= 36 && LA26_0 <= 37))) {
                    alt26 = 1;
                }
                switch (alt26) {
                    case 1:
                        // /home/langera/dev/freud/src/grammar/Css.g:124:14: args
                    {
                        pushFollow(FOLLOW_args_in_function771);
                        args71 = args();

                        state._fsp--;

                        stream_args.add(args71.getTree());

                    }
                    break;

                }

                char_literal72 = (Token) match(input, 62, FOLLOW_62_in_function774);
                stream_62.add(char_literal72);


                // AST REWRITE
                // elements: 62, args, 61, IDENT
                // token labels:
                // rule labels: retval
                // token list labels:
                // rule list labels:
                // wildcard labels:
                retval.tree = root_0;
                RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.tree : null);

                root_0 = (CommonTree) adaptor.nil();
                // 124:24: -> IDENT '(' ( args )* ')'
                {
                    adaptor.addChild(root_0, stream_IDENT.nextNode());
                    adaptor.addChild(root_0, stream_61.nextNode());
                    // /home/langera/dev/freud/src/grammar/Css.g:124:37: ( args )*
                    while (stream_args.hasNext()) {
                        adaptor.addChild(root_0, stream_args.nextTree());

                    }
                    stream_args.reset();
                    adaptor.addChild(root_0, stream_62.nextNode());

                }

                retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (CommonTree) adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "function"

    // Delegated rules


    protected DFA10 dfa10 = new DFA10(this);
    protected DFA20 dfa20 = new DFA20(this);
    protected DFA25 dfa25 = new DFA25(this);
    static final String DFA10_eotS =
            "\14\uffff";
    static final String DFA10_eofS =
            "\14\uffff";
    static final String DFA10_minS =
            "\1\26\13\uffff";
    static final String DFA10_maxS =
            "\1\50\13\uffff";
    static final String DFA10_acceptS =
            "\1\uffff\1\2\4\uffff\1\1\5\uffff";
    static final String DFA10_specialS =
            "\14\uffff}>";
    static final String[] DFA10_transitionS = {
            "\1\6\6\uffff\1\1\1\uffff\1\1\2\6\1\uffff\3\6\3\1",
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
            ""
    };

    static final short[] DFA10_eot = DFA.unpackEncodedString(DFA10_eotS);
    static final short[] DFA10_eof = DFA.unpackEncodedString(DFA10_eofS);
    static final char[] DFA10_min = DFA.unpackEncodedStringToUnsignedChars(DFA10_minS);
    static final char[] DFA10_max = DFA.unpackEncodedStringToUnsignedChars(DFA10_maxS);
    static final short[] DFA10_accept = DFA.unpackEncodedString(DFA10_acceptS);
    static final short[] DFA10_special = DFA.unpackEncodedString(DFA10_specialS);
    static final short[][] DFA10_transition;

    static {
        int numStates = DFA10_transitionS.length;
        DFA10_transition = new short[numStates][];
        for (int i = 0; i < numStates; i++) {
            DFA10_transition[i] = DFA.unpackEncodedString(DFA10_transitionS[i]);
        }
    }

    class DFA10 extends DFA {

        public DFA10(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 10;
            this.eot = DFA10_eot;
            this.eof = DFA10_eof;
            this.min = DFA10_min;
            this.max = DFA10_max;
            this.accept = DFA10_accept;
            this.special = DFA10_special;
            this.transition = DFA10_transition;
        }

        public String getDescription() {
            return "()* loopback of 65:9: ( selectorOperation )*";
        }
    }

    static final String DFA20_eotS =
            "\17\uffff";
    static final String DFA20_eofS =
            "\17\uffff";
    static final String DFA20_minS =
            "\1\46\2\26\2\35\12\uffff";
    static final String DFA20_maxS =
            "\1\47\2\26\2\75\12\uffff";
    static final String DFA20_acceptS =
            "\5\uffff\1\2\1\1\10\uffff";
    static final String DFA20_specialS =
            "\17\uffff}>";
    static final String[] DFA20_transitionS = {
            "\1\1\1\2",
            "\1\3",
            "\1\4",
            "\1\6\1\uffff\1\6\6\uffff\2\6\25\uffff\1\5",
            "\1\6\1\uffff\1\6\6\uffff\2\6\25\uffff\1\5",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA20_eot = DFA.unpackEncodedString(DFA20_eotS);
    static final short[] DFA20_eof = DFA.unpackEncodedString(DFA20_eofS);
    static final char[] DFA20_min = DFA.unpackEncodedStringToUnsignedChars(DFA20_minS);
    static final char[] DFA20_max = DFA.unpackEncodedStringToUnsignedChars(DFA20_maxS);
    static final short[] DFA20_accept = DFA.unpackEncodedString(DFA20_acceptS);
    static final short[] DFA20_special = DFA.unpackEncodedString(DFA20_specialS);
    static final short[][] DFA20_transition;

    static {
        int numStates = DFA20_transitionS.length;
        DFA20_transition = new short[numStates][];
        for (int i = 0; i < numStates; i++) {
            DFA20_transition[i] = DFA.unpackEncodedString(DFA20_transitionS[i]);
        }
    }

    class DFA20 extends DFA {

        public DFA20(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 20;
            this.eot = DFA20_eot;
            this.eof = DFA20_eof;
            this.min = DFA20_min;
            this.max = DFA20_max;
            this.accept = DFA20_accept;
            this.special = DFA20_special;
            this.transition = DFA20_transition;
        }

        public String getDescription() {
            return "88:1: pseudo : ( ( ':' | '::' ) IDENT -> ^( PSEUDO IDENT ) | ( ':' | '::' ) function -> ^( PSEUDO function ) );";
        }
    }

    static final String DFA25_eotS =
            "\16\uffff";
    static final String DFA25_eofS =
            "\16\uffff";
    static final String DFA25_minS =
            "\2\26\14\uffff";
    static final String DFA25_maxS =
            "\1\45\1\76\14\uffff";
    static final String DFA25_acceptS =
            "\2\uffff\1\3\1\4\1\5\1\1\1\2\7\uffff";
    static final String DFA25_specialS =
            "\16\uffff}>";
    static final String[] DFA25_transitionS = {
            "\1\1\15\uffff\1\2\1\3",
            "\1\6\5\uffff\1\6\1\uffff\2\6\2\uffff\1\6\1\uffff\2\6\7\uffff" +
                    "\20\5\1\4\1\6",
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
            ""
    };

    static final short[] DFA25_eot = DFA.unpackEncodedString(DFA25_eotS);
    static final short[] DFA25_eof = DFA.unpackEncodedString(DFA25_eofS);
    static final char[] DFA25_min = DFA.unpackEncodedStringToUnsignedChars(DFA25_minS);
    static final char[] DFA25_max = DFA.unpackEncodedStringToUnsignedChars(DFA25_maxS);
    static final short[] DFA25_accept = DFA.unpackEncodedString(DFA25_acceptS);
    static final short[] DFA25_special = DFA.unpackEncodedString(DFA25_specialS);
    static final short[][] DFA25_transition;

    static {
        int numStates = DFA25_transitionS.length;
        DFA25_transition = new short[numStates][];
        for (int i = 0; i < numStates; i++) {
            DFA25_transition[i] = DFA.unpackEncodedString(DFA25_transitionS[i]);
        }
    }

    class DFA25 extends DFA {

        public DFA25(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 25;
            this.eot = DFA25_eot;
            this.eof = DFA25_eof;
            this.min = DFA25_min;
            this.max = DFA25_max;
            this.accept = DFA25_accept;
            this.special = DFA25_special;
            this.transition = DFA25_transition;
        }

        public String getDescription() {
            return "111:1: expr : ( ( IDENT unit ) | IDENT | '#' IDENT -> ^( COLOUR '#' IDENT ) | '.' IDENT | function );";
        }
    }


    public static final BitSet FOLLOW_importRule_in_stylesheet131 = new BitSet(new long[]{0x000000381C400000L});
    public static final BitSet FOLLOW_nested_in_stylesheet135 = new BitSet(new long[]{0x0000003810400002L});
    public static final BitSet FOLLOW_ruleset_in_stylesheet139 = new BitSet(new long[]{0x0000003810400002L});
    public static final BitSet FOLLOW_26_in_importRule153 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_27_in_importRule157 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_IDENT_in_importRule161 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_nested183 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_nest_in_nested185 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_29_in_nested187 = new BitSet(new long[]{0x0000000050400000L});
    public static final BitSet FOLLOW_properties_in_nested189 = new BitSet(new long[]{0x0000000050000000L});
    public static final BitSet FOLLOW_nested_in_nested192 = new BitSet(new long[]{0x0000000050000000L});
    public static final BitSet FOLLOW_30_in_nested195 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_nest222 = new BitSet(new long[]{0x000000C000400002L});
    public static final BitSet FOLLOW_IDENT_in_nest224 = new BitSet(new long[]{0x000000C000400002L});
    public static final BitSet FOLLOW_pseudo_in_nest227 = new BitSet(new long[]{0x000000C000000002L});
    public static final BitSet FOLLOW_selectors_in_ruleset257 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_29_in_ruleset259 = new BitSet(new long[]{0x0000000040400000L});
    public static final BitSet FOLLOW_properties_in_ruleset261 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_30_in_ruleset264 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_selector_in_selectors289 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_31_in_selectors292 = new BitSet(new long[]{0x0000003810400000L});
    public static final BitSet FOLLOW_selector_in_selectors294 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_elem_in_selector308 = new BitSet(new long[]{0x000001FB10400002L});
    public static final BitSet FOLLOW_selectorOperation_in_selector310 = new BitSet(new long[]{0x000001FB10400002L});
    public static final BitSet FOLLOW_attrib_in_selector313 = new BitSet(new long[]{0x000001C000000002L});
    public static final BitSet FOLLOW_pseudo_in_selector316 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_selectop_in_selectorOperation342 = new BitSet(new long[]{0x0000003810400000L});
    public static final BitSet FOLLOW_elem_in_selectorOperation345 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_selectop363 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_selectop379 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_declaration_in_properties395 = new BitSet(new long[]{0x0000000400000002L});
    public static final BitSet FOLLOW_34_in_properties398 = new BitSet(new long[]{0x0000000400400002L});
    public static final BitSet FOLLOW_declaration_in_properties400 = new BitSet(new long[]{0x0000000400000002L});
    public static final BitSet FOLLOW_35_in_elem421 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_36_in_elem434 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_IDENT_in_elem436 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_37_in_elem451 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_IDENT_in_elem453 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_elem471 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_38_in_pseudo493 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_39_in_pseudo495 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_IDENT_in_pseudo498 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_38_in_pseudo514 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_39_in_pseudo516 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_function_in_pseudo519 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_40_in_attrib540 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_IDENT_in_attrib542 = new BitSet(new long[]{0x00001E0000000000L});
    public static final BitSet FOLLOW_attribRelate_in_attrib545 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_IDENT_in_attrib547 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_41_in_attrib551 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_42_in_attribRelate581 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_43_in_attribRelate591 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_44_in_attribRelate600 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_declaration618 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_38_in_declaration620 = new BitSet(new long[]{0x0000003000400000L});
    public static final BitSet FOLLOW_args_in_declaration622 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_in_args645 = new BitSet(new long[]{0x0000003080400002L});
    public static final BitSet FOLLOW_31_in_args648 = new BitSet(new long[]{0x0000003000400000L});
    public static final BitSet FOLLOW_expr_in_args651 = new BitSet(new long[]{0x0000003080400002L});
    public static final BitSet FOLLOW_IDENT_in_expr670 = new BitSet(new long[]{0x1FFFE00000000000L});
    public static final BitSet FOLLOW_unit_in_expr672 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_expr678 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_36_in_expr683 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_IDENT_in_expr685 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_37_in_expr702 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_IDENT_in_expr704 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_function_in_expr709 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_unit723 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_function767 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_61_in_function769 = new BitSet(new long[]{0x4000003000400000L});
    public static final BitSet FOLLOW_args_in_function771 = new BitSet(new long[]{0x4000000000000000L});
    public static final BitSet FOLLOW_62_in_function774 = new BitSet(new long[]{0x0000000000000002L});

}
