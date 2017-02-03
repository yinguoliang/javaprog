// $ANTLR 3.5.1 /Users/yinguoliang/study/antlr/Hello.g 2017-01-21 18:29:06
package com.elva.antlr.basic.exam1;

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

import org.antlr.runtime.tree.*;


@SuppressWarnings("all")
public class HelloParser extends Parser {
	public static final String[] tokenNames = new String[] {
		"<invalid>", "<EOR>", "<DOWN>", "<UP>", "ID", "INT", "NEWLINE", "WS", 
		"'('", "')'", "'*'", "'+'", "'-'", "'/'"
	};
	public static final int EOF=-1;
	public static final int T__8=8;
	public static final int T__9=9;
	public static final int T__10=10;
	public static final int T__11=11;
	public static final int T__12=12;
	public static final int T__13=13;
	public static final int ID=4;
	public static final int INT=5;
	public static final int NEWLINE=6;
	public static final int WS=7;

	// delegates
	public Parser[] getDelegates() {
		return new Parser[] {};
	}

	// delegators


	public HelloParser(TokenStream input) {
		this(input, new RecognizerSharedState());
	}
	public HelloParser(TokenStream input, RecognizerSharedState state) {
		super(input, state);
	}

	protected TreeAdaptor adaptor = new CommonTreeAdaptor();

	public void setTreeAdaptor(TreeAdaptor adaptor) {
		this.adaptor = adaptor;
	}
	public TreeAdaptor getTreeAdaptor() {
		return adaptor;
	}
	@Override public String[] getTokenNames() { return HelloParser.tokenNames; }
	@Override public String getGrammarFileName() { return "/Users/yinguoliang/study/antlr/Hello.g"; }


	public static class prog_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "prog"
	// /Users/yinguoliang/study/antlr/Hello.g:6:1: prog : stat ;
	public final HelloParser.prog_return prog() throws RecognitionException {
		HelloParser.prog_return retval = new HelloParser.prog_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		ParserRuleReturnScope stat1 =null;


		try {
			// /Users/yinguoliang/study/antlr/Hello.g:6:5: ( stat )
			// /Users/yinguoliang/study/antlr/Hello.g:6:7: stat
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_stat_in_prog30);
			stat1=stat();
			state._fsp--;

			adaptor.addChild(root_0, stat1.getTree());

			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "prog"


	public static class stat_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "stat"
	// /Users/yinguoliang/study/antlr/Hello.g:9:1: stat : ( expr | NEWLINE );
	public final HelloParser.stat_return stat() throws RecognitionException {
		HelloParser.stat_return retval = new HelloParser.stat_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token NEWLINE3=null;
		ParserRuleReturnScope expr2 =null;

		Object NEWLINE3_tree=null;

		try {
			// /Users/yinguoliang/study/antlr/Hello.g:9:6: ( expr | NEWLINE )
			int alt1=2;
			switch ( input.LA(1) ) {
			case ID:
			case INT:
			case 8:
				{
				alt1=1;
				}
				break;
			case NEWLINE:
				{
				alt1=2;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 1, 0, input);
				throw nvae;
			}
			switch (alt1) {
				case 1 :
					// /Users/yinguoliang/study/antlr/Hello.g:9:8: expr
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_expr_in_stat41);
					expr2=expr();
					state._fsp--;

					adaptor.addChild(root_0, expr2.getTree());

					}
					break;
				case 2 :
					// /Users/yinguoliang/study/antlr/Hello.g:10:5: NEWLINE
					{
					root_0 = (Object)adaptor.nil();


					NEWLINE3=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_stat48); 
					NEWLINE3_tree = (Object)adaptor.create(NEWLINE3);
					adaptor.addChild(root_0, NEWLINE3_tree);

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "stat"


	public static class expr_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "expr"
	// /Users/yinguoliang/study/antlr/Hello.g:13:1: expr : multExpr ( ( '+' ^| '-' ^) multExpr )* ;
	public final HelloParser.expr_return expr() throws RecognitionException {
		HelloParser.expr_return retval = new HelloParser.expr_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token char_literal5=null;
		Token char_literal6=null;
		ParserRuleReturnScope multExpr4 =null;
		ParserRuleReturnScope multExpr7 =null;

		Object char_literal5_tree=null;
		Object char_literal6_tree=null;

		try {
			// /Users/yinguoliang/study/antlr/Hello.g:13:6: ( multExpr ( ( '+' ^| '-' ^) multExpr )* )
			// /Users/yinguoliang/study/antlr/Hello.g:13:8: multExpr ( ( '+' ^| '-' ^) multExpr )*
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_multExpr_in_expr57);
			multExpr4=multExpr();
			state._fsp--;

			adaptor.addChild(root_0, multExpr4.getTree());

			// /Users/yinguoliang/study/antlr/Hello.g:13:17: ( ( '+' ^| '-' ^) multExpr )*
			loop3:
			while (true) {
				int alt3=2;
				switch ( input.LA(1) ) {
				case 11:
				case 12:
					{
					alt3=1;
					}
					break;
				}
				switch (alt3) {
				case 1 :
					// /Users/yinguoliang/study/antlr/Hello.g:13:18: ( '+' ^| '-' ^) multExpr
					{
					// /Users/yinguoliang/study/antlr/Hello.g:13:18: ( '+' ^| '-' ^)
					int alt2=2;
					switch ( input.LA(1) ) {
					case 11:
						{
						alt2=1;
						}
						break;
					case 12:
						{
						alt2=2;
						}
						break;
					default:
						NoViableAltException nvae =
							new NoViableAltException("", 2, 0, input);
						throw nvae;
					}
					switch (alt2) {
						case 1 :
							// /Users/yinguoliang/study/antlr/Hello.g:13:19: '+' ^
							{
							char_literal5=(Token)match(input,11,FOLLOW_11_in_expr61); 
							char_literal5_tree = (Object)adaptor.create(char_literal5);
							root_0 = (Object)adaptor.becomeRoot(char_literal5_tree, root_0);

							}
							break;
						case 2 :
							// /Users/yinguoliang/study/antlr/Hello.g:13:24: '-' ^
							{
							char_literal6=(Token)match(input,12,FOLLOW_12_in_expr64); 
							char_literal6_tree = (Object)adaptor.create(char_literal6);
							root_0 = (Object)adaptor.becomeRoot(char_literal6_tree, root_0);

							}
							break;

					}

					pushFollow(FOLLOW_multExpr_in_expr68);
					multExpr7=multExpr();
					state._fsp--;

					adaptor.addChild(root_0, multExpr7.getTree());

					}
					break;

				default :
					break loop3;
				}
			}

			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "expr"


	public static class multExpr_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "multExpr"
	// /Users/yinguoliang/study/antlr/Hello.g:16:1: multExpr : atom ( ( '*' ^| '/' ^) atom )* ;
	public final HelloParser.multExpr_return multExpr() throws RecognitionException {
		HelloParser.multExpr_return retval = new HelloParser.multExpr_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token char_literal9=null;
		Token char_literal10=null;
		ParserRuleReturnScope atom8 =null;
		ParserRuleReturnScope atom11 =null;

		Object char_literal9_tree=null;
		Object char_literal10_tree=null;

		try {
			// /Users/yinguoliang/study/antlr/Hello.g:16:10: ( atom ( ( '*' ^| '/' ^) atom )* )
			// /Users/yinguoliang/study/antlr/Hello.g:16:12: atom ( ( '*' ^| '/' ^) atom )*
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_atom_in_multExpr79);
			atom8=atom();
			state._fsp--;

			adaptor.addChild(root_0, atom8.getTree());

			// /Users/yinguoliang/study/antlr/Hello.g:16:17: ( ( '*' ^| '/' ^) atom )*
			loop5:
			while (true) {
				int alt5=2;
				switch ( input.LA(1) ) {
				case 10:
				case 13:
					{
					alt5=1;
					}
					break;
				}
				switch (alt5) {
				case 1 :
					// /Users/yinguoliang/study/antlr/Hello.g:16:18: ( '*' ^| '/' ^) atom
					{
					// /Users/yinguoliang/study/antlr/Hello.g:16:18: ( '*' ^| '/' ^)
					int alt4=2;
					switch ( input.LA(1) ) {
					case 10:
						{
						alt4=1;
						}
						break;
					case 13:
						{
						alt4=2;
						}
						break;
					default:
						NoViableAltException nvae =
							new NoViableAltException("", 4, 0, input);
						throw nvae;
					}
					switch (alt4) {
						case 1 :
							// /Users/yinguoliang/study/antlr/Hello.g:16:19: '*' ^
							{
							char_literal9=(Token)match(input,10,FOLLOW_10_in_multExpr83); 
							char_literal9_tree = (Object)adaptor.create(char_literal9);
							root_0 = (Object)adaptor.becomeRoot(char_literal9_tree, root_0);

							}
							break;
						case 2 :
							// /Users/yinguoliang/study/antlr/Hello.g:16:24: '/' ^
							{
							char_literal10=(Token)match(input,13,FOLLOW_13_in_multExpr86); 
							char_literal10_tree = (Object)adaptor.create(char_literal10);
							root_0 = (Object)adaptor.becomeRoot(char_literal10_tree, root_0);

							}
							break;

					}

					pushFollow(FOLLOW_atom_in_multExpr90);
					atom11=atom();
					state._fsp--;

					adaptor.addChild(root_0, atom11.getTree());

					}
					break;

				default :
					break loop5;
				}
			}

			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "multExpr"


	public static class atom_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "atom"
	// /Users/yinguoliang/study/antlr/Hello.g:19:1: atom : ( '(' ! expr ')' !| INT | ID );
	public final HelloParser.atom_return atom() throws RecognitionException {
		HelloParser.atom_return retval = new HelloParser.atom_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token char_literal12=null;
		Token char_literal14=null;
		Token INT15=null;
		Token ID16=null;
		ParserRuleReturnScope expr13 =null;

		Object char_literal12_tree=null;
		Object char_literal14_tree=null;
		Object INT15_tree=null;
		Object ID16_tree=null;

		try {
			// /Users/yinguoliang/study/antlr/Hello.g:19:6: ( '(' ! expr ')' !| INT | ID )
			int alt6=3;
			switch ( input.LA(1) ) {
			case 8:
				{
				alt6=1;
				}
				break;
			case INT:
				{
				alt6=2;
				}
				break;
			case ID:
				{
				alt6=3;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 6, 0, input);
				throw nvae;
			}
			switch (alt6) {
				case 1 :
					// /Users/yinguoliang/study/antlr/Hello.g:19:10: '(' ! expr ')' !
					{
					root_0 = (Object)adaptor.nil();


					char_literal12=(Token)match(input,8,FOLLOW_8_in_atom103); 
					pushFollow(FOLLOW_expr_in_atom106);
					expr13=expr();
					state._fsp--;

					adaptor.addChild(root_0, expr13.getTree());

					char_literal14=(Token)match(input,9,FOLLOW_9_in_atom108); 
					}
					break;
				case 2 :
					// /Users/yinguoliang/study/antlr/Hello.g:20:7: INT
					{
					root_0 = (Object)adaptor.nil();


					INT15=(Token)match(input,INT,FOLLOW_INT_in_atom118); 
					INT15_tree = (Object)adaptor.create(INT15);
					adaptor.addChild(root_0, INT15_tree);

					}
					break;
				case 3 :
					// /Users/yinguoliang/study/antlr/Hello.g:21:7: ID
					{
					root_0 = (Object)adaptor.nil();


					ID16=(Token)match(input,ID,FOLLOW_ID_in_atom128); 
					ID16_tree = (Object)adaptor.create(ID16);
					adaptor.addChild(root_0, ID16_tree);

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "atom"

	// Delegated rules



	public static final BitSet FOLLOW_stat_in_prog30 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_expr_in_stat41 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NEWLINE_in_stat48 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_multExpr_in_expr57 = new BitSet(new long[]{0x0000000000001802L});
	public static final BitSet FOLLOW_11_in_expr61 = new BitSet(new long[]{0x0000000000000130L});
	public static final BitSet FOLLOW_12_in_expr64 = new BitSet(new long[]{0x0000000000000130L});
	public static final BitSet FOLLOW_multExpr_in_expr68 = new BitSet(new long[]{0x0000000000001802L});
	public static final BitSet FOLLOW_atom_in_multExpr79 = new BitSet(new long[]{0x0000000000002402L});
	public static final BitSet FOLLOW_10_in_multExpr83 = new BitSet(new long[]{0x0000000000000130L});
	public static final BitSet FOLLOW_13_in_multExpr86 = new BitSet(new long[]{0x0000000000000130L});
	public static final BitSet FOLLOW_atom_in_multExpr90 = new BitSet(new long[]{0x0000000000002402L});
	public static final BitSet FOLLOW_8_in_atom103 = new BitSet(new long[]{0x0000000000000130L});
	public static final BitSet FOLLOW_expr_in_atom106 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_atom108 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INT_in_atom118 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_atom128 = new BitSet(new long[]{0x0000000000000002L});
}
