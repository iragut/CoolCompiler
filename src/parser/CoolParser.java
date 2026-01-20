// Generated from D:/Facultate/Anul-IV/Comp/tema3/src/cool/parser/CoolParser.g4 by ANTLR 4.13.2

    package cool.parser;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class CoolParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		ERROR=1, LINE_COMMENT=2, BlOCK_COMMENT_EROR=3, BLOCK_COMMENT=4, WS=5, 
		IF=6, THEN=7, ELSE=8, FI=9, TRUE=10, FALSE=11, BOOL=12, CLASS=13, INHERITS=14, 
		AT=15, NEW=16, ISVOID=17, NOT=18, WHILE=19, LOOP=20, POOL=21, LET=22, 
		IN=23, CASE=24, OF=25, ESAC=26, TYPE=27, ID=28, INT=29, STRING=30, EOF_LINE_ERROR=31, 
		NEW_LINE_ERROR=32, SEMI=33, COMMA=34, DOT=35, COLON=36, ASSIGN=37, LPAREN=38, 
		RPAREN=39, LBRACE=40, RBRACE=41, PLUS=42, MINUS=43, MULT=44, DIV=45, EQUAL=46, 
		TILDE=47, LT=48, LE=49, EL=50, ERROR_CHAR=51;
	public static final int
		RULE_program = 0, RULE_class = 1, RULE_feature = 2, RULE_arguments = 3, 
		RULE_formal = 4, RULE_expr = 5, RULE_assign = 6, RULE_case_method = 7;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "class", "feature", "arguments", "formal", "expr", "assign", 
			"case_method"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, "'*)'", null, null, "'if'", "'then'", "'else'", "'fi'", 
			"'true'", "'false'", null, "'class'", "'inherits'", "'@'", "'new'", "'isvoid'", 
			"'not'", "'while'", "'loop'", "'pool'", "'let'", "'in'", "'case'", "'of'", 
			"'esac'", null, null, null, null, null, null, "';'", "','", "'.'", "':'", 
			"'<-'", "'('", "')'", "'{'", "'}'", "'+'", "'-'", "'*'", "'/'", "'='", 
			"'~'", "'<'", "'<='", "'=>'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "ERROR", "LINE_COMMENT", "BlOCK_COMMENT_EROR", "BLOCK_COMMENT", 
			"WS", "IF", "THEN", "ELSE", "FI", "TRUE", "FALSE", "BOOL", "CLASS", "INHERITS", 
			"AT", "NEW", "ISVOID", "NOT", "WHILE", "LOOP", "POOL", "LET", "IN", "CASE", 
			"OF", "ESAC", "TYPE", "ID", "INT", "STRING", "EOF_LINE_ERROR", "NEW_LINE_ERROR", 
			"SEMI", "COMMA", "DOT", "COLON", "ASSIGN", "LPAREN", "RPAREN", "LBRACE", 
			"RBRACE", "PLUS", "MINUS", "MULT", "DIV", "EQUAL", "TILDE", "LT", "LE", 
			"EL", "ERROR_CHAR"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "CoolParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public CoolParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramContext extends ParserRuleContext {
		public List<ClassContext> class_() {
			return getRuleContexts(ClassContext.class);
		}
		public ClassContext class_(int i) {
			return getRuleContext(ClassContext.class,i);
		}
		public List<TerminalNode> SEMI() { return getTokens(CoolParser.SEMI); }
		public TerminalNode SEMI(int i) {
			return getToken(CoolParser.SEMI, i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(19); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(16);
				class_();
				setState(17);
				match(SEMI);
				}
				}
				setState(21); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==CLASS );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ClassContext extends ParserRuleContext {
		public TerminalNode CLASS() { return getToken(CoolParser.CLASS, 0); }
		public List<TerminalNode> TYPE() { return getTokens(CoolParser.TYPE); }
		public TerminalNode TYPE(int i) {
			return getToken(CoolParser.TYPE, i);
		}
		public TerminalNode LBRACE() { return getToken(CoolParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(CoolParser.RBRACE, 0); }
		public TerminalNode INHERITS() { return getToken(CoolParser.INHERITS, 0); }
		public List<FeatureContext> feature() {
			return getRuleContexts(FeatureContext.class);
		}
		public FeatureContext feature(int i) {
			return getRuleContext(FeatureContext.class,i);
		}
		public List<TerminalNode> SEMI() { return getTokens(CoolParser.SEMI); }
		public TerminalNode SEMI(int i) {
			return getToken(CoolParser.SEMI, i);
		}
		public ClassContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_class; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterClass(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitClass(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitClass(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassContext class_() throws RecognitionException {
		ClassContext _localctx = new ClassContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_class);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(23);
			match(CLASS);
			setState(24);
			match(TYPE);
			setState(27);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==INHERITS) {
				{
				setState(25);
				match(INHERITS);
				setState(26);
				match(TYPE);
				}
			}

			setState(29);
			match(LBRACE);
			setState(35);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ID) {
				{
				{
				setState(30);
				feature();
				setState(31);
				match(SEMI);
				}
				}
				setState(37);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(38);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FeatureContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(CoolParser.ID, 0); }
		public TerminalNode LPAREN() { return getToken(CoolParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(CoolParser.RPAREN, 0); }
		public TerminalNode COLON() { return getToken(CoolParser.COLON, 0); }
		public TerminalNode TYPE() { return getToken(CoolParser.TYPE, 0); }
		public TerminalNode LBRACE() { return getToken(CoolParser.LBRACE, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RBRACE() { return getToken(CoolParser.RBRACE, 0); }
		public FormalContext formal() {
			return getRuleContext(FormalContext.class,0);
		}
		public ArgumentsContext arguments() {
			return getRuleContext(ArgumentsContext.class,0);
		}
		public AssignContext assign() {
			return getRuleContext(AssignContext.class,0);
		}
		public FeatureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_feature; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterFeature(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitFeature(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitFeature(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FeatureContext feature() throws RecognitionException {
		FeatureContext _localctx = new FeatureContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_feature);
		int _la;
		try {
			setState(55);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(40);
				match(ID);
				setState(41);
				match(LPAREN);
				setState(45);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ID) {
					{
					setState(42);
					formal();
					setState(43);
					arguments();
					}
				}

				setState(47);
				match(RPAREN);
				setState(48);
				match(COLON);
				setState(49);
				match(TYPE);
				setState(50);
				match(LBRACE);
				setState(51);
				expr(0);
				setState(52);
				match(RBRACE);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(54);
				assign();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArgumentsContext extends ParserRuleContext {
		public List<TerminalNode> COMMA() { return getTokens(CoolParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CoolParser.COMMA, i);
		}
		public List<FormalContext> formal() {
			return getRuleContexts(FormalContext.class);
		}
		public FormalContext formal(int i) {
			return getRuleContext(FormalContext.class,i);
		}
		public ArgumentsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arguments; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterArguments(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitArguments(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitArguments(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgumentsContext arguments() throws RecognitionException {
		ArgumentsContext _localctx = new ArgumentsContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_arguments);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(61);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(57);
				match(COMMA);
				setState(58);
				formal();
				}
				}
				setState(63);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FormalContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(CoolParser.ID, 0); }
		public TerminalNode COLON() { return getToken(CoolParser.COLON, 0); }
		public TerminalNode TYPE() { return getToken(CoolParser.TYPE, 0); }
		public FormalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterFormal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitFormal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitFormal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FormalContext formal() throws RecognitionException {
		FormalContext _localctx = new FormalContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_formal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(64);
			match(ID);
			setState(65);
			match(COLON);
			setState(66);
			match(TYPE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprContext extends ParserRuleContext {
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	 
		public ExprContext() { }
		public void copyFrom(ExprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class New_typeContext extends ExprContext {
		public TerminalNode NEW() { return getToken(CoolParser.NEW, 0); }
		public TerminalNode TYPE() { return getToken(CoolParser.TYPE, 0); }
		public New_typeContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterNew_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitNew_type(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitNew_type(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StringContext extends ExprContext {
		public TerminalNode STRING() { return getToken(CoolParser.STRING, 0); }
		public StringContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitString(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitString(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class While_loopContext extends ExprContext {
		public TerminalNode WHILE() { return getToken(CoolParser.WHILE, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode LOOP() { return getToken(CoolParser.LOOP, 0); }
		public TerminalNode POOL() { return getToken(CoolParser.POOL, 0); }
		public While_loopContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterWhile_loop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitWhile_loop(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitWhile_loop(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IsvoidContext extends ExprContext {
		public TerminalNode ISVOID() { return getToken(CoolParser.ISVOID, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public IsvoidContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterIsvoid(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitIsvoid(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitIsvoid(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FalseContext extends ExprContext {
		public TerminalNode FALSE() { return getToken(CoolParser.FALSE, 0); }
		public FalseContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterFalse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitFalse(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitFalse(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class LtContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode LT() { return getToken(CoolParser.LT, 0); }
		public LtContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterLt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitLt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitLt(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class EqContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode EQUAL() { return getToken(CoolParser.EQUAL, 0); }
		public EqContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterEq(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitEq(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitEq(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class Func_call_classContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode DOT() { return getToken(CoolParser.DOT, 0); }
		public TerminalNode ID() { return getToken(CoolParser.ID, 0); }
		public TerminalNode LPAREN() { return getToken(CoolParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(CoolParser.RPAREN, 0); }
		public TerminalNode AT() { return getToken(CoolParser.AT, 0); }
		public TerminalNode TYPE() { return getToken(CoolParser.TYPE, 0); }
		public List<TerminalNode> COMMA() { return getTokens(CoolParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CoolParser.COMMA, i);
		}
		public Func_call_classContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterFunc_call_class(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitFunc_call_class(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitFunc_call_class(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class Func_callContext extends ExprContext {
		public TerminalNode ID() { return getToken(CoolParser.ID, 0); }
		public TerminalNode LPAREN() { return getToken(CoolParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(CoolParser.RPAREN, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(CoolParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CoolParser.COMMA, i);
		}
		public Func_callContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterFunc_call(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitFunc_call(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitFunc_call(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class Add_subContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode PLUS() { return getToken(CoolParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(CoolParser.MINUS, 0); }
		public Add_subContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterAdd_sub(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitAdd_sub(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitAdd_sub(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IntContext extends ExprContext {
		public TerminalNode INT() { return getToken(CoolParser.INT, 0); }
		public IntContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterInt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitInt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitInt(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NotContext extends ExprContext {
		public TerminalNode NOT() { return getToken(CoolParser.NOT, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public NotContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterNot(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitNot(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitNot(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ParenContext extends ExprContext {
		public TerminalNode LPAREN() { return getToken(CoolParser.LPAREN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(CoolParser.RPAREN, 0); }
		public ParenContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterParen(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitParen(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitParen(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class Assig_expresionContext extends ExprContext {
		public TerminalNode ID() { return getToken(CoolParser.ID, 0); }
		public TerminalNode ASSIGN() { return getToken(CoolParser.ASSIGN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Assig_expresionContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterAssig_expresion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitAssig_expresion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitAssig_expresion(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class Local_varsContext extends ExprContext {
		public TerminalNode LET() { return getToken(CoolParser.LET, 0); }
		public List<AssignContext> assign() {
			return getRuleContexts(AssignContext.class);
		}
		public AssignContext assign(int i) {
			return getRuleContext(AssignContext.class,i);
		}
		public TerminalNode IN() { return getToken(CoolParser.IN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public List<TerminalNode> COMMA() { return getTokens(CoolParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CoolParser.COMMA, i);
		}
		public Local_varsContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterLocal_vars(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitLocal_vars(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitLocal_vars(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NegateContext extends ExprContext {
		public TerminalNode TILDE() { return getToken(CoolParser.TILDE, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public NegateContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterNegate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitNegate(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitNegate(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class If_then_elseContext extends ExprContext {
		public TerminalNode IF() { return getToken(CoolParser.IF, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode THEN() { return getToken(CoolParser.THEN, 0); }
		public TerminalNode ELSE() { return getToken(CoolParser.ELSE, 0); }
		public TerminalNode FI() { return getToken(CoolParser.FI, 0); }
		public If_then_elseContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterIf_then_else(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitIf_then_else(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitIf_then_else(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class TrueContext extends ExprContext {
		public TerminalNode TRUE() { return getToken(CoolParser.TRUE, 0); }
		public TrueContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterTrue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitTrue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitTrue(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class LeContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode LE() { return getToken(CoolParser.LE, 0); }
		public LeContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterLe(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitLe(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitLe(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BlockContext extends ExprContext {
		public TerminalNode LBRACE() { return getToken(CoolParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(CoolParser.RBRACE, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> SEMI() { return getTokens(CoolParser.SEMI); }
		public TerminalNode SEMI(int i) {
			return getToken(CoolParser.SEMI, i);
		}
		public BlockContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitBlock(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class Mul_divContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode MULT() { return getToken(CoolParser.MULT, 0); }
		public TerminalNode DIV() { return getToken(CoolParser.DIV, 0); }
		public Mul_divContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterMul_div(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitMul_div(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitMul_div(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IdContext extends ExprContext {
		public TerminalNode ID() { return getToken(CoolParser.ID, 0); }
		public IdContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterId(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitId(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitId(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class CaseContext extends ExprContext {
		public TerminalNode CASE() { return getToken(CoolParser.CASE, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode OF() { return getToken(CoolParser.OF, 0); }
		public TerminalNode ESAC() { return getToken(CoolParser.ESAC, 0); }
		public List<Case_methodContext> case_method() {
			return getRuleContexts(Case_methodContext.class);
		}
		public Case_methodContext case_method(int i) {
			return getRuleContext(Case_methodContext.class,i);
		}
		public CaseContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterCase(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitCase(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitCase(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 10;
		enterRecursionRule(_localctx, 10, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(148);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				{
				_localctx = new Func_callContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(69);
				match(ID);
				setState(70);
				match(LPAREN);
				setState(79);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 142113778895936L) != 0)) {
					{
					setState(71);
					expr(0);
					setState(76);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(72);
						match(COMMA);
						setState(73);
						expr(0);
						}
						}
						setState(78);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(81);
				match(RPAREN);
				}
				break;
			case 2:
				{
				_localctx = new If_then_elseContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(82);
				match(IF);
				setState(83);
				expr(0);
				setState(84);
				match(THEN);
				setState(85);
				expr(0);
				setState(86);
				match(ELSE);
				setState(87);
				expr(0);
				setState(88);
				match(FI);
				}
				break;
			case 3:
				{
				_localctx = new While_loopContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(90);
				match(WHILE);
				setState(91);
				expr(0);
				setState(92);
				match(LOOP);
				setState(93);
				expr(0);
				setState(94);
				match(POOL);
				}
				break;
			case 4:
				{
				_localctx = new BlockContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(96);
				match(LBRACE);
				setState(100); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(97);
					expr(0);
					setState(98);
					match(SEMI);
					}
					}
					setState(102); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 142113778895936L) != 0) );
				setState(104);
				match(RBRACE);
				}
				break;
			case 5:
				{
				_localctx = new Local_varsContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(106);
				match(LET);
				setState(107);
				assign();
				setState(112);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(108);
					match(COMMA);
					setState(109);
					assign();
					}
					}
					setState(114);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(115);
				match(IN);
				setState(116);
				expr(18);
				}
				break;
			case 6:
				{
				_localctx = new CaseContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(118);
				match(CASE);
				setState(119);
				expr(0);
				setState(120);
				match(OF);
				setState(122); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(121);
					case_method();
					}
					}
					setState(124); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==ID );
				setState(126);
				match(ESAC);
				}
				break;
			case 7:
				{
				_localctx = new New_typeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(128);
				match(NEW);
				setState(129);
				match(TYPE);
				}
				break;
			case 8:
				{
				_localctx = new IsvoidContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(130);
				match(ISVOID);
				setState(131);
				expr(15);
				}
				break;
			case 9:
				{
				_localctx = new NegateContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(132);
				match(TILDE);
				setState(133);
				expr(14);
				}
				break;
			case 10:
				{
				_localctx = new NotContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(134);
				match(NOT);
				setState(135);
				expr(8);
				}
				break;
			case 11:
				{
				_localctx = new ParenContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(136);
				match(LPAREN);
				setState(137);
				expr(0);
				setState(138);
				match(RPAREN);
				}
				break;
			case 12:
				{
				_localctx = new IdContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(140);
				match(ID);
				}
				break;
			case 13:
				{
				_localctx = new IntContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(141);
				match(INT);
				}
				break;
			case 14:
				{
				_localctx = new StringContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(142);
				match(STRING);
				}
				break;
			case 15:
				{
				_localctx = new TrueContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(143);
				match(TRUE);
				}
				break;
			case 16:
				{
				_localctx = new FalseContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(144);
				match(FALSE);
				}
				break;
			case 17:
				{
				_localctx = new Assig_expresionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(145);
				match(ID);
				setState(146);
				match(ASSIGN);
				setState(147);
				expr(1);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(186);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(184);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
					case 1:
						{
						_localctx = new Mul_divContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(150);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(151);
						_la = _input.LA(1);
						if ( !(_la==MULT || _la==DIV) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(152);
						expr(14);
						}
						break;
					case 2:
						{
						_localctx = new Add_subContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(153);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(154);
						_la = _input.LA(1);
						if ( !(_la==PLUS || _la==MINUS) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(155);
						expr(13);
						}
						break;
					case 3:
						{
						_localctx = new LtContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(156);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(157);
						match(LT);
						setState(158);
						expr(12);
						}
						break;
					case 4:
						{
						_localctx = new LeContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(159);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(160);
						match(LE);
						setState(161);
						expr(11);
						}
						break;
					case 5:
						{
						_localctx = new EqContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(162);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(163);
						match(EQUAL);
						setState(164);
						expr(10);
						}
						break;
					case 6:
						{
						_localctx = new Func_call_classContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(165);
						if (!(precpred(_ctx, 23))) throw new FailedPredicateException(this, "precpred(_ctx, 23)");
						setState(168);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==AT) {
							{
							setState(166);
							match(AT);
							setState(167);
							match(TYPE);
							}
						}

						setState(170);
						match(DOT);
						setState(171);
						match(ID);
						setState(172);
						match(LPAREN);
						setState(181);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 142113778895936L) != 0)) {
							{
							setState(173);
							expr(0);
							setState(178);
							_errHandler.sync(this);
							_la = _input.LA(1);
							while (_la==COMMA) {
								{
								{
								setState(174);
								match(COMMA);
								setState(175);
								expr(0);
								}
								}
								setState(180);
								_errHandler.sync(this);
								_la = _input.LA(1);
							}
							}
						}

						setState(183);
						match(RPAREN);
						}
						break;
					}
					} 
				}
				setState(188);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AssignContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(CoolParser.ID, 0); }
		public TerminalNode COLON() { return getToken(CoolParser.COLON, 0); }
		public TerminalNode TYPE() { return getToken(CoolParser.TYPE, 0); }
		public TerminalNode ASSIGN() { return getToken(CoolParser.ASSIGN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public AssignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assign; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterAssign(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitAssign(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitAssign(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignContext assign() throws RecognitionException {
		AssignContext _localctx = new AssignContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_assign);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(189);
			match(ID);
			setState(190);
			match(COLON);
			setState(191);
			match(TYPE);
			setState(194);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ASSIGN) {
				{
				setState(192);
				match(ASSIGN);
				setState(193);
				expr(0);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Case_methodContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(CoolParser.ID, 0); }
		public TerminalNode COLON() { return getToken(CoolParser.COLON, 0); }
		public TerminalNode TYPE() { return getToken(CoolParser.TYPE, 0); }
		public TerminalNode EL() { return getToken(CoolParser.EL, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(CoolParser.SEMI, 0); }
		public Case_methodContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_case_method; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterCase_method(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitCase_method(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitCase_method(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Case_methodContext case_method() throws RecognitionException {
		Case_methodContext _localctx = new Case_methodContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_case_method);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(196);
			match(ID);
			setState(197);
			match(COLON);
			setState(198);
			match(TYPE);
			setState(199);
			match(EL);
			setState(200);
			expr(0);
			setState(201);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 5:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 13);
		case 1:
			return precpred(_ctx, 12);
		case 2:
			return precpred(_ctx, 11);
		case 3:
			return precpred(_ctx, 10);
		case 4:
			return precpred(_ctx, 9);
		case 5:
			return precpred(_ctx, 23);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u00013\u00cc\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0004\u0000\u0014\b\u0000\u000b\u0000\f"+
		"\u0000\u0015\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0003\u0001"+
		"\u001c\b\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0005\u0001"+
		"\"\b\u0001\n\u0001\f\u0001%\t\u0001\u0001\u0001\u0001\u0001\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002.\b\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0003\u00028\b\u0002\u0001\u0003\u0001\u0003"+
		"\u0005\u0003<\b\u0003\n\u0003\f\u0003?\t\u0003\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0005\u0005K\b\u0005\n\u0005\f\u0005N\t\u0005"+
		"\u0003\u0005P\b\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0004\u0005e\b\u0005\u000b\u0005"+
		"\f\u0005f\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0005\u0005o\b\u0005\n\u0005\f\u0005r\t\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0004\u0005{\b\u0005\u000b\u0005\f\u0005|\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0003\u0005\u0095\b\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0003\u0005\u00a9\b\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0005"+
		"\u0005\u00b1\b\u0005\n\u0005\f\u0005\u00b4\t\u0005\u0003\u0005\u00b6\b"+
		"\u0005\u0001\u0005\u0005\u0005\u00b9\b\u0005\n\u0005\f\u0005\u00bc\t\u0005"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0003\u0006"+
		"\u00c3\b\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0000\u0001\n\b\u0000\u0002\u0004"+
		"\u0006\b\n\f\u000e\u0000\u0002\u0001\u0000,-\u0001\u0000*+\u00e8\u0000"+
		"\u0013\u0001\u0000\u0000\u0000\u0002\u0017\u0001\u0000\u0000\u0000\u0004"+
		"7\u0001\u0000\u0000\u0000\u0006=\u0001\u0000\u0000\u0000\b@\u0001\u0000"+
		"\u0000\u0000\n\u0094\u0001\u0000\u0000\u0000\f\u00bd\u0001\u0000\u0000"+
		"\u0000\u000e\u00c4\u0001\u0000\u0000\u0000\u0010\u0011\u0003\u0002\u0001"+
		"\u0000\u0011\u0012\u0005!\u0000\u0000\u0012\u0014\u0001\u0000\u0000\u0000"+
		"\u0013\u0010\u0001\u0000\u0000\u0000\u0014\u0015\u0001\u0000\u0000\u0000"+
		"\u0015\u0013\u0001\u0000\u0000\u0000\u0015\u0016\u0001\u0000\u0000\u0000"+
		"\u0016\u0001\u0001\u0000\u0000\u0000\u0017\u0018\u0005\r\u0000\u0000\u0018"+
		"\u001b\u0005\u001b\u0000\u0000\u0019\u001a\u0005\u000e\u0000\u0000\u001a"+
		"\u001c\u0005\u001b\u0000\u0000\u001b\u0019\u0001\u0000\u0000\u0000\u001b"+
		"\u001c\u0001\u0000\u0000\u0000\u001c\u001d\u0001\u0000\u0000\u0000\u001d"+
		"#\u0005(\u0000\u0000\u001e\u001f\u0003\u0004\u0002\u0000\u001f \u0005"+
		"!\u0000\u0000 \"\u0001\u0000\u0000\u0000!\u001e\u0001\u0000\u0000\u0000"+
		"\"%\u0001\u0000\u0000\u0000#!\u0001\u0000\u0000\u0000#$\u0001\u0000\u0000"+
		"\u0000$&\u0001\u0000\u0000\u0000%#\u0001\u0000\u0000\u0000&\'\u0005)\u0000"+
		"\u0000\'\u0003\u0001\u0000\u0000\u0000()\u0005\u001c\u0000\u0000)-\u0005"+
		"&\u0000\u0000*+\u0003\b\u0004\u0000+,\u0003\u0006\u0003\u0000,.\u0001"+
		"\u0000\u0000\u0000-*\u0001\u0000\u0000\u0000-.\u0001\u0000\u0000\u0000"+
		"./\u0001\u0000\u0000\u0000/0\u0005\'\u0000\u000001\u0005$\u0000\u0000"+
		"12\u0005\u001b\u0000\u000023\u0005(\u0000\u000034\u0003\n\u0005\u0000"+
		"45\u0005)\u0000\u000058\u0001\u0000\u0000\u000068\u0003\f\u0006\u0000"+
		"7(\u0001\u0000\u0000\u000076\u0001\u0000\u0000\u00008\u0005\u0001\u0000"+
		"\u0000\u00009:\u0005\"\u0000\u0000:<\u0003\b\u0004\u0000;9\u0001\u0000"+
		"\u0000\u0000<?\u0001\u0000\u0000\u0000=;\u0001\u0000\u0000\u0000=>\u0001"+
		"\u0000\u0000\u0000>\u0007\u0001\u0000\u0000\u0000?=\u0001\u0000\u0000"+
		"\u0000@A\u0005\u001c\u0000\u0000AB\u0005$\u0000\u0000BC\u0005\u001b\u0000"+
		"\u0000C\t\u0001\u0000\u0000\u0000DE\u0006\u0005\uffff\uffff\u0000EF\u0005"+
		"\u001c\u0000\u0000FO\u0005&\u0000\u0000GL\u0003\n\u0005\u0000HI\u0005"+
		"\"\u0000\u0000IK\u0003\n\u0005\u0000JH\u0001\u0000\u0000\u0000KN\u0001"+
		"\u0000\u0000\u0000LJ\u0001\u0000\u0000\u0000LM\u0001\u0000\u0000\u0000"+
		"MP\u0001\u0000\u0000\u0000NL\u0001\u0000\u0000\u0000OG\u0001\u0000\u0000"+
		"\u0000OP\u0001\u0000\u0000\u0000PQ\u0001\u0000\u0000\u0000Q\u0095\u0005"+
		"\'\u0000\u0000RS\u0005\u0006\u0000\u0000ST\u0003\n\u0005\u0000TU\u0005"+
		"\u0007\u0000\u0000UV\u0003\n\u0005\u0000VW\u0005\b\u0000\u0000WX\u0003"+
		"\n\u0005\u0000XY\u0005\t\u0000\u0000Y\u0095\u0001\u0000\u0000\u0000Z["+
		"\u0005\u0013\u0000\u0000[\\\u0003\n\u0005\u0000\\]\u0005\u0014\u0000\u0000"+
		"]^\u0003\n\u0005\u0000^_\u0005\u0015\u0000\u0000_\u0095\u0001\u0000\u0000"+
		"\u0000`d\u0005(\u0000\u0000ab\u0003\n\u0005\u0000bc\u0005!\u0000\u0000"+
		"ce\u0001\u0000\u0000\u0000da\u0001\u0000\u0000\u0000ef\u0001\u0000\u0000"+
		"\u0000fd\u0001\u0000\u0000\u0000fg\u0001\u0000\u0000\u0000gh\u0001\u0000"+
		"\u0000\u0000hi\u0005)\u0000\u0000i\u0095\u0001\u0000\u0000\u0000jk\u0005"+
		"\u0016\u0000\u0000kp\u0003\f\u0006\u0000lm\u0005\"\u0000\u0000mo\u0003"+
		"\f\u0006\u0000nl\u0001\u0000\u0000\u0000or\u0001\u0000\u0000\u0000pn\u0001"+
		"\u0000\u0000\u0000pq\u0001\u0000\u0000\u0000qs\u0001\u0000\u0000\u0000"+
		"rp\u0001\u0000\u0000\u0000st\u0005\u0017\u0000\u0000tu\u0003\n\u0005\u0012"+
		"u\u0095\u0001\u0000\u0000\u0000vw\u0005\u0018\u0000\u0000wx\u0003\n\u0005"+
		"\u0000xz\u0005\u0019\u0000\u0000y{\u0003\u000e\u0007\u0000zy\u0001\u0000"+
		"\u0000\u0000{|\u0001\u0000\u0000\u0000|z\u0001\u0000\u0000\u0000|}\u0001"+
		"\u0000\u0000\u0000}~\u0001\u0000\u0000\u0000~\u007f\u0005\u001a\u0000"+
		"\u0000\u007f\u0095\u0001\u0000\u0000\u0000\u0080\u0081\u0005\u0010\u0000"+
		"\u0000\u0081\u0095\u0005\u001b\u0000\u0000\u0082\u0083\u0005\u0011\u0000"+
		"\u0000\u0083\u0095\u0003\n\u0005\u000f\u0084\u0085\u0005/\u0000\u0000"+
		"\u0085\u0095\u0003\n\u0005\u000e\u0086\u0087\u0005\u0012\u0000\u0000\u0087"+
		"\u0095\u0003\n\u0005\b\u0088\u0089\u0005&\u0000\u0000\u0089\u008a\u0003"+
		"\n\u0005\u0000\u008a\u008b\u0005\'\u0000\u0000\u008b\u0095\u0001\u0000"+
		"\u0000\u0000\u008c\u0095\u0005\u001c\u0000\u0000\u008d\u0095\u0005\u001d"+
		"\u0000\u0000\u008e\u0095\u0005\u001e\u0000\u0000\u008f\u0095\u0005\n\u0000"+
		"\u0000\u0090\u0095\u0005\u000b\u0000\u0000\u0091\u0092\u0005\u001c\u0000"+
		"\u0000\u0092\u0093\u0005%\u0000\u0000\u0093\u0095\u0003\n\u0005\u0001"+
		"\u0094D\u0001\u0000\u0000\u0000\u0094R\u0001\u0000\u0000\u0000\u0094Z"+
		"\u0001\u0000\u0000\u0000\u0094`\u0001\u0000\u0000\u0000\u0094j\u0001\u0000"+
		"\u0000\u0000\u0094v\u0001\u0000\u0000\u0000\u0094\u0080\u0001\u0000\u0000"+
		"\u0000\u0094\u0082\u0001\u0000\u0000\u0000\u0094\u0084\u0001\u0000\u0000"+
		"\u0000\u0094\u0086\u0001\u0000\u0000\u0000\u0094\u0088\u0001\u0000\u0000"+
		"\u0000\u0094\u008c\u0001\u0000\u0000\u0000\u0094\u008d\u0001\u0000\u0000"+
		"\u0000\u0094\u008e\u0001\u0000\u0000\u0000\u0094\u008f\u0001\u0000\u0000"+
		"\u0000\u0094\u0090\u0001\u0000\u0000\u0000\u0094\u0091\u0001\u0000\u0000"+
		"\u0000\u0095\u00ba\u0001\u0000\u0000\u0000\u0096\u0097\n\r\u0000\u0000"+
		"\u0097\u0098\u0007\u0000\u0000\u0000\u0098\u00b9\u0003\n\u0005\u000e\u0099"+
		"\u009a\n\f\u0000\u0000\u009a\u009b\u0007\u0001\u0000\u0000\u009b\u00b9"+
		"\u0003\n\u0005\r\u009c\u009d\n\u000b\u0000\u0000\u009d\u009e\u00050\u0000"+
		"\u0000\u009e\u00b9\u0003\n\u0005\f\u009f\u00a0\n\n\u0000\u0000\u00a0\u00a1"+
		"\u00051\u0000\u0000\u00a1\u00b9\u0003\n\u0005\u000b\u00a2\u00a3\n\t\u0000"+
		"\u0000\u00a3\u00a4\u0005.\u0000\u0000\u00a4\u00b9\u0003\n\u0005\n\u00a5"+
		"\u00a8\n\u0017\u0000\u0000\u00a6\u00a7\u0005\u000f\u0000\u0000\u00a7\u00a9"+
		"\u0005\u001b\u0000\u0000\u00a8\u00a6\u0001\u0000\u0000\u0000\u00a8\u00a9"+
		"\u0001\u0000\u0000\u0000\u00a9\u00aa\u0001\u0000\u0000\u0000\u00aa\u00ab"+
		"\u0005#\u0000\u0000\u00ab\u00ac\u0005\u001c\u0000\u0000\u00ac\u00b5\u0005"+
		"&\u0000\u0000\u00ad\u00b2\u0003\n\u0005\u0000\u00ae\u00af\u0005\"\u0000"+
		"\u0000\u00af\u00b1\u0003\n\u0005\u0000\u00b0\u00ae\u0001\u0000\u0000\u0000"+
		"\u00b1\u00b4\u0001\u0000\u0000\u0000\u00b2\u00b0\u0001\u0000\u0000\u0000"+
		"\u00b2\u00b3\u0001\u0000\u0000\u0000\u00b3\u00b6\u0001\u0000\u0000\u0000"+
		"\u00b4\u00b2\u0001\u0000\u0000\u0000\u00b5\u00ad\u0001\u0000\u0000\u0000"+
		"\u00b5\u00b6\u0001\u0000\u0000\u0000\u00b6\u00b7\u0001\u0000\u0000\u0000"+
		"\u00b7\u00b9\u0005\'\u0000\u0000\u00b8\u0096\u0001\u0000\u0000\u0000\u00b8"+
		"\u0099\u0001\u0000\u0000\u0000\u00b8\u009c\u0001\u0000\u0000\u0000\u00b8"+
		"\u009f\u0001\u0000\u0000\u0000\u00b8\u00a2\u0001\u0000\u0000\u0000\u00b8"+
		"\u00a5\u0001\u0000\u0000\u0000\u00b9\u00bc\u0001\u0000\u0000\u0000\u00ba"+
		"\u00b8\u0001\u0000\u0000\u0000\u00ba\u00bb\u0001\u0000\u0000\u0000\u00bb"+
		"\u000b\u0001\u0000\u0000\u0000\u00bc\u00ba\u0001\u0000\u0000\u0000\u00bd"+
		"\u00be\u0005\u001c\u0000\u0000\u00be\u00bf\u0005$\u0000\u0000\u00bf\u00c2"+
		"\u0005\u001b\u0000\u0000\u00c0\u00c1\u0005%\u0000\u0000\u00c1\u00c3\u0003"+
		"\n\u0005\u0000\u00c2\u00c0\u0001\u0000\u0000\u0000\u00c2\u00c3\u0001\u0000"+
		"\u0000\u0000\u00c3\r\u0001\u0000\u0000\u0000\u00c4\u00c5\u0005\u001c\u0000"+
		"\u0000\u00c5\u00c6\u0005$\u0000\u0000\u00c6\u00c7\u0005\u001b\u0000\u0000"+
		"\u00c7\u00c8\u00052\u0000\u0000\u00c8\u00c9\u0003\n\u0005\u0000\u00c9"+
		"\u00ca\u0005!\u0000\u0000\u00ca\u000f\u0001\u0000\u0000\u0000\u0012\u0015"+
		"\u001b#-7=LOfp|\u0094\u00a8\u00b2\u00b5\u00b8\u00ba\u00c2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}