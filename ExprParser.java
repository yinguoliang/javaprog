// $ANTLR 3.2 Sep 23, 2009 14:05:07 d:/antlr/first/Expr.g 2017-01-20 16:24:14
package expr;

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

import org.antlr.runtime.debug.*;
import java.io.IOException;
public class ExprParser extends DebugParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "NEWLINE", "INT", "ID", "WS", "'+'", "'-'", "'*'", "'/'", "'('", "')'"
    };
    public static final int WS=7;
    public static final int NEWLINE=4;
    public static final int T__12=12;
    public static final int T__11=11;
    public static final int T__13=13;
    public static final int T__10=10;
    public static final int INT=5;
    public static final int ID=6;
    public static final int EOF=-1;
    public static final int T__9=9;
    public static final int T__8=8;

    // delegates
    // delegators

    public static final String[] ruleNames = new String[] {
        "invalidRule", "atom", "expr", "prog", "multExpr", "stat"
    };
     
        public int ruleLevel = 0;
        public int getRuleLevel() { return ruleLevel; }
        public void incRuleLevel() { ruleLevel++; }
        public void decRuleLevel() { ruleLevel--; }
        public ExprParser(TokenStream input) {
            this(input, new Profiler(null), new RecognizerSharedState());
        }
        public ExprParser(TokenStream input, DebugEventListener dbg, RecognizerSharedState state) {
            super(input, dbg, state);
            Profiler p = (Profiler)dbg;
            p.setParser(this);
        }

    public ExprParser(TokenStream input, DebugEventListener dbg) {
        super(input, dbg, new RecognizerSharedState());
        Profiler p = (Profiler)dbg;
        p.setParser(this);
    }
    public boolean alreadyParsedRule(IntStream input, int ruleIndex) {
        ((Profiler)dbg).examineRuleMemoization(input, ruleIndex, ExprParser.ruleNames[ruleIndex]);
        return super.alreadyParsedRule(input, ruleIndex);
    }

    public void memoize(IntStream input,
                        int ruleIndex,
                        int ruleStartIndex)
    {
        ((Profiler)dbg).memoize(input, ruleIndex, ruleStartIndex, ExprParser.ruleNames[ruleIndex]);
        super.memoize(input, ruleIndex, ruleStartIndex);
    }
    protected boolean evalPredicate(boolean result, String predicate) {
        dbg.semanticPredicate(result, predicate);
        return result;
    }


    public String[] getTokenNames() { return ExprParser.tokenNames; }
    public String getGrammarFileName() { return "d:/antlr/first/Expr.g"; }



    // $ANTLR start "prog"
    // d:/antlr/first/Expr.g:6:1: prog : stat ;
    public final void prog() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "prog");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(6, 1);

        try {
            // d:/antlr/first/Expr.g:6:5: ( stat )
            dbg.enterAlt(1);

            // d:/antlr/first/Expr.g:6:7: stat
            {
            dbg.location(6,7);
            pushFollow(FOLLOW_stat_in_prog22);
            stat();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(7, 1);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "prog");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "prog"


    // $ANTLR start "stat"
    // d:/antlr/first/Expr.g:9:1: stat : ( expr | NEWLINE );
    public final void stat() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "stat");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(9, 1);

        try {
            // d:/antlr/first/Expr.g:9:6: ( expr | NEWLINE )
            int alt1=2;
            try { dbg.enterDecision(1);

            int LA1_0 = input.LA(1);

            if ( ((LA1_0>=INT && LA1_0<=ID)||LA1_0==12) ) {
                alt1=1;
            }
            else if ( (LA1_0==NEWLINE) ) {
                alt1=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                dbg.recognitionException(nvae);
                throw nvae;
            }
            } finally {dbg.exitDecision(1);}

            switch (alt1) {
                case 1 :
                    dbg.enterAlt(1);

                    // d:/antlr/first/Expr.g:9:8: expr
                    {
                    dbg.location(9,8);
                    pushFollow(FOLLOW_expr_in_stat33);
                    expr();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // d:/antlr/first/Expr.g:10:5: NEWLINE
                    {
                    dbg.location(10,5);
                    match(input,NEWLINE,FOLLOW_NEWLINE_in_stat40); 

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(11, 1);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "stat");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "stat"


    // $ANTLR start "expr"
    // d:/antlr/first/Expr.g:13:1: expr : multExpr ( ( '+' | '-' ) multExpr )* ;
    public final void expr() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "expr");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(13, 1);

        try {
            // d:/antlr/first/Expr.g:13:6: ( multExpr ( ( '+' | '-' ) multExpr )* )
            dbg.enterAlt(1);

            // d:/antlr/first/Expr.g:13:8: multExpr ( ( '+' | '-' ) multExpr )*
            {
            dbg.location(13,8);
            pushFollow(FOLLOW_multExpr_in_expr49);
            multExpr();

            state._fsp--;

            dbg.location(13,17);
            // d:/antlr/first/Expr.g:13:17: ( ( '+' | '-' ) multExpr )*
            try { dbg.enterSubRule(2);

            loop2:
            do {
                int alt2=2;
                try { dbg.enterDecision(2);

                int LA2_0 = input.LA(1);

                if ( ((LA2_0>=8 && LA2_0<=9)) ) {
                    alt2=1;
                }


                } finally {dbg.exitDecision(2);}

                switch (alt2) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // d:/antlr/first/Expr.g:13:18: ( '+' | '-' ) multExpr
            	    {
            	    dbg.location(13,18);
            	    if ( (input.LA(1)>=8 && input.LA(1)<=9) ) {
            	        input.consume();
            	        state.errorRecovery=false;
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        dbg.recognitionException(mse);
            	        throw mse;
            	    }

            	    dbg.location(13,28);
            	    pushFollow(FOLLOW_multExpr_in_expr58);
            	    multExpr();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);
            } finally {dbg.exitSubRule(2);}


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(14, 1);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "expr");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "expr"


    // $ANTLR start "multExpr"
    // d:/antlr/first/Expr.g:16:1: multExpr : atom ( ( '*' | '/' ) atom )* ;
    public final void multExpr() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "multExpr");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(16, 1);

        try {
            // d:/antlr/first/Expr.g:16:10: ( atom ( ( '*' | '/' ) atom )* )
            dbg.enterAlt(1);

            // d:/antlr/first/Expr.g:16:12: atom ( ( '*' | '/' ) atom )*
            {
            dbg.location(16,12);
            pushFollow(FOLLOW_atom_in_multExpr69);
            atom();

            state._fsp--;

            dbg.location(16,17);
            // d:/antlr/first/Expr.g:16:17: ( ( '*' | '/' ) atom )*
            try { dbg.enterSubRule(3);

            loop3:
            do {
                int alt3=2;
                try { dbg.enterDecision(3);

                int LA3_0 = input.LA(1);

                if ( ((LA3_0>=10 && LA3_0<=11)) ) {
                    alt3=1;
                }


                } finally {dbg.exitDecision(3);}

                switch (alt3) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // d:/antlr/first/Expr.g:16:18: ( '*' | '/' ) atom
            	    {
            	    dbg.location(16,18);
            	    if ( (input.LA(1)>=10 && input.LA(1)<=11) ) {
            	        input.consume();
            	        state.errorRecovery=false;
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        dbg.recognitionException(mse);
            	        throw mse;
            	    }

            	    dbg.location(16,28);
            	    pushFollow(FOLLOW_atom_in_multExpr78);
            	    atom();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);
            } finally {dbg.exitSubRule(3);}


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(17, 1);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "multExpr");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "multExpr"


    // $ANTLR start "atom"
    // d:/antlr/first/Expr.g:19:1: atom : ( '(' expr ')' | INT | ID );
    public final void atom() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "atom");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(19, 1);

        try {
            // d:/antlr/first/Expr.g:19:6: ( '(' expr ')' | INT | ID )
            int alt4=3;
            try { dbg.enterDecision(4);

            switch ( input.LA(1) ) {
            case 12:
                {
                alt4=1;
                }
                break;
            case INT:
                {
                alt4=2;
                }
                break;
            case ID:
                {
                alt4=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                dbg.recognitionException(nvae);
                throw nvae;
            }

            } finally {dbg.exitDecision(4);}

            switch (alt4) {
                case 1 :
                    dbg.enterAlt(1);

                    // d:/antlr/first/Expr.g:19:10: '(' expr ')'
                    {
                    dbg.location(19,10);
                    match(input,12,FOLLOW_12_in_atom91); 
                    dbg.location(19,14);
                    pushFollow(FOLLOW_expr_in_atom93);
                    expr();

                    state._fsp--;

                    dbg.location(19,19);
                    match(input,13,FOLLOW_13_in_atom95); 

                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // d:/antlr/first/Expr.g:20:7: INT
                    {
                    dbg.location(20,7);
                    match(input,INT,FOLLOW_INT_in_atom104); 

                    }
                    break;
                case 3 :
                    dbg.enterAlt(3);

                    // d:/antlr/first/Expr.g:21:7: ID
                    {
                    dbg.location(21,7);
                    match(input,ID,FOLLOW_ID_in_atom114); 

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(22, 1);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "atom");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "atom"

    // Delegated rules


 

    public static final BitSet FOLLOW_stat_in_prog22 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_in_stat33 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NEWLINE_in_stat40 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_multExpr_in_expr49 = new BitSet(new long[]{0x0000000000000302L});
    public static final BitSet FOLLOW_set_in_expr52 = new BitSet(new long[]{0x0000000000001060L});
    public static final BitSet FOLLOW_multExpr_in_expr58 = new BitSet(new long[]{0x0000000000000302L});
    public static final BitSet FOLLOW_atom_in_multExpr69 = new BitSet(new long[]{0x0000000000000C02L});
    public static final BitSet FOLLOW_set_in_multExpr72 = new BitSet(new long[]{0x0000000000001060L});
    public static final BitSet FOLLOW_atom_in_multExpr78 = new BitSet(new long[]{0x0000000000000C02L});
    public static final BitSet FOLLOW_12_in_atom91 = new BitSet(new long[]{0x0000000000001060L});
    public static final BitSet FOLLOW_expr_in_atom93 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_atom95 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INT_in_atom104 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_atom114 = new BitSet(new long[]{0x0000000000000002L});

}