// Generated from /Users/hamodenajda/Desktop/projet GL/gl47/src/main/antlr4/fr/ensimag/deca/syntax/DecaParser.g4 by ANTLR 4.8

    import fr.ensimag.deca.tree.*;
    import java.io.PrintStream;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class DecaParser extends AbstractDecaParser {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		ASM=1, CLASS=2, EXTENDS=3, ELSE=4, FALSE=5, IF=6, INSTANCEOF=7, NEW=8, 
		NULL=9, READINT=10, READFLOAT=11, PRINT=12, PRINTLN=13, PRINTX=14, PRINTLNX=15, 
		PROTECTED=16, RETURN=17, THIS=18, TRUE=19, WHILE=20, IDENT=21, LT=22, 
		GT=23, EQUALS=24, PLUS=25, MINUS=26, TIMES=27, SLASH=28, PERCENT=29, DOT=30, 
		COMMA=31, OPARENT=32, CPARENT=33, OBRACE=34, CBRACE=35, EXCLAM=36, SEMI=37, 
		EQEQ=38, NEQ=39, GEQ=40, LEQ=41, AND=42, OR=43, INT=44, FLOAT=45, STRING=46, 
		MULTI_LINE_STRING=47, WS=48, INCLUDE=49;
	public static final int
		RULE_prog = 0, RULE_main = 1, RULE_block = 2, RULE_list_decl = 3, RULE_decl_var_set = 4, 
		RULE_list_decl_var = 5, RULE_decl_var = 6, RULE_list_inst = 7, RULE_inst = 8, 
		RULE_if_then_else = 9, RULE_list_expr = 10, RULE_expr = 11, RULE_assign_expr = 12, 
		RULE_or_expr = 13, RULE_and_expr = 14, RULE_eq_neq_expr = 15, RULE_inequality_expr = 16, 
		RULE_sum_expr = 17, RULE_mult_expr = 18, RULE_unary_expr = 19, RULE_select_expr = 20, 
		RULE_primary_expr = 21, RULE_type = 22, RULE_literal = 23, RULE_ident = 24, 
		RULE_list_classes = 25, RULE_class_decl = 26, RULE_class_extension = 27, 
		RULE_class_body = 28, RULE_decl_field_set = 29, RULE_visibility = 30, 
		RULE_list_decl_field = 31, RULE_decl_field = 32, RULE_decl_method = 33, 
		RULE_list_params = 34, RULE_multi_line_string = 35, RULE_param = 36;
	private static String[] makeRuleNames() {
		return new String[] {
			"prog", "main", "block", "list_decl", "decl_var_set", "list_decl_var", 
			"decl_var", "list_inst", "inst", "if_then_else", "list_expr", "expr", 
			"assign_expr", "or_expr", "and_expr", "eq_neq_expr", "inequality_expr", 
			"sum_expr", "mult_expr", "unary_expr", "select_expr", "primary_expr", 
			"type", "literal", "ident", "list_classes", "class_decl", "class_extension", 
			"class_body", "decl_field_set", "visibility", "list_decl_field", "decl_field", 
			"decl_method", "list_params", "multi_line_string", "param"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'asm'", "'class'", "'extends'", "'else'", "'false'", "'if'", "'instanceof'", 
			"'new'", "'null'", "'readint'", "'readfloat'", "'print'", "'println'", 
			"'printx'", "'printlnx'", "'protected'", "'return'", "'this'", "'true'", 
			"'while'", null, "'<'", "'>'", "'='", "'+'", "'-'", "'*'", "'/'", "'%'", 
			"'.'", "','", "'('", "')'", "'{'", "'}'", "'!'", "';'", "'=='", "'!='", 
			"'>='", "'<='", "'&&'", "'||'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "ASM", "CLASS", "EXTENDS", "ELSE", "FALSE", "IF", "INSTANCEOF", 
			"NEW", "NULL", "READINT", "READFLOAT", "PRINT", "PRINTLN", "PRINTX", 
			"PRINTLNX", "PROTECTED", "RETURN", "THIS", "TRUE", "WHILE", "IDENT", 
			"LT", "GT", "EQUALS", "PLUS", "MINUS", "TIMES", "SLASH", "PERCENT", "DOT", 
			"COMMA", "OPARENT", "CPARENT", "OBRACE", "CBRACE", "EXCLAM", "SEMI", 
			"EQEQ", "NEQ", "GEQ", "LEQ", "AND", "OR", "INT", "FLOAT", "STRING", "MULTI_LINE_STRING", 
			"WS", "INCLUDE"
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
	public String getGrammarFileName() { return "DecaParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


	    @Override
	    protected AbstractProgram parseProgram() {
	        return prog().tree;
	    }

	public DecaParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgContext extends ParserRuleContext {
		public AbstractProgram tree;
		public List_classesContext list_classes;
		public MainContext main;
		public List_classesContext list_classes() {
			return getRuleContext(List_classesContext.class,0);
		}
		public MainContext main() {
			return getRuleContext(MainContext.class,0);
		}
		public TerminalNode EOF() { return getToken(DecaParser.EOF, 0); }
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(74);
			((ProgContext)_localctx).list_classes = list_classes();
			setState(75);
			((ProgContext)_localctx).main = main();
			setState(76);
			match(EOF);

			            assert(((ProgContext)_localctx).list_classes.tree != null);
			            assert(((ProgContext)_localctx).main.tree != null);
			            ((ProgContext)_localctx).tree =  new Program(((ProgContext)_localctx).list_classes.tree, ((ProgContext)_localctx).main.tree);
			            setLocation(_localctx.tree, (((ProgContext)_localctx).list_classes!=null?(((ProgContext)_localctx).list_classes.start):null));
			        
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

	public static class MainContext extends ParserRuleContext {
		public AbstractMain tree;
		public BlockContext block;
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public MainContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_main; }
	}

	public final MainContext main() throws RecognitionException {
		MainContext _localctx = new MainContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_main);
		try {
			setState(83);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case EOF:
				enterOuterAlt(_localctx, 1);
				{

				            ((MainContext)_localctx).tree =  new EmptyMain();
				        
				}
				break;
			case OBRACE:
				enterOuterAlt(_localctx, 2);
				{
				setState(80);
				((MainContext)_localctx).block = block();

				            assert(((MainContext)_localctx).block.decls != null);
				            assert(((MainContext)_localctx).block.insts != null);
				            ((MainContext)_localctx).tree =  new Main(((MainContext)_localctx).block.decls, ((MainContext)_localctx).block.insts);
				            setLocation(_localctx.tree, (((MainContext)_localctx).block!=null?(((MainContext)_localctx).block.start):null));
				        
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class BlockContext extends ParserRuleContext {
		public ListDeclVar decls;
		public ListInst insts;
		public List_declContext list_decl;
		public List_instContext list_inst;
		public TerminalNode OBRACE() { return getToken(DecaParser.OBRACE, 0); }
		public List_declContext list_decl() {
			return getRuleContext(List_declContext.class,0);
		}
		public List_instContext list_inst() {
			return getRuleContext(List_instContext.class,0);
		}
		public TerminalNode CBRACE() { return getToken(DecaParser.CBRACE, 0); }
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_block);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(85);
			match(OBRACE);
			setState(86);
			((BlockContext)_localctx).list_decl = list_decl();
			setState(87);
			((BlockContext)_localctx).list_inst = list_inst();
			setState(88);
			match(CBRACE);

			            assert(((BlockContext)_localctx).list_decl.tree != null);
			            assert(((BlockContext)_localctx).list_inst.tree != null);
			            ((BlockContext)_localctx).decls =  ((BlockContext)_localctx).list_decl.tree;
			            ((BlockContext)_localctx).insts =  ((BlockContext)_localctx).list_inst.tree;
			            setLocation(((BlockContext)_localctx).list_inst.tree, (((BlockContext)_localctx).list_inst!=null?(((BlockContext)_localctx).list_inst.start):null));
			        
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

	public static class List_declContext extends ParserRuleContext {
		public ListDeclVar tree;
		public List<Decl_var_setContext> decl_var_set() {
			return getRuleContexts(Decl_var_setContext.class);
		}
		public Decl_var_setContext decl_var_set(int i) {
			return getRuleContext(Decl_var_setContext.class,i);
		}
		public List_declContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_list_decl; }
	}

	public final List_declContext list_decl() throws RecognitionException {
		List_declContext _localctx = new List_declContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_list_decl);

		            ((List_declContext)_localctx).tree =  new ListDeclVar();
		        
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(94);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(91);
					decl_var_set(_localctx.tree);
					}
					} 
				}
				setState(96);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
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

	public static class Decl_var_setContext extends ParserRuleContext {
		public ListDeclVar l;
		public TypeContext type;
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public List_decl_varContext list_decl_var() {
			return getRuleContext(List_decl_varContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(DecaParser.SEMI, 0); }
		public Decl_var_setContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public Decl_var_setContext(ParserRuleContext parent, int invokingState, ListDeclVar l) {
			super(parent, invokingState);
			this.l = l;
		}
		@Override public int getRuleIndex() { return RULE_decl_var_set; }
	}

	public final Decl_var_setContext decl_var_set(ListDeclVar l) throws RecognitionException {
		Decl_var_setContext _localctx = new Decl_var_setContext(_ctx, getState(), l);
		enterRule(_localctx, 8, RULE_decl_var_set);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(97);
			((Decl_var_setContext)_localctx).type = type();
			setState(98);
			list_decl_var(_localctx.l,((Decl_var_setContext)_localctx).type.tree);
			setState(99);
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

	public static class List_decl_varContext extends ParserRuleContext {
		public ListDeclVar l;
		public AbstractIdentifier t;
		public Decl_varContext dv1;
		public Decl_varContext dv2;
		public List<Decl_varContext> decl_var() {
			return getRuleContexts(Decl_varContext.class);
		}
		public Decl_varContext decl_var(int i) {
			return getRuleContext(Decl_varContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(DecaParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(DecaParser.COMMA, i);
		}
		public List_decl_varContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public List_decl_varContext(ParserRuleContext parent, int invokingState, ListDeclVar l, AbstractIdentifier t) {
			super(parent, invokingState);
			this.l = l;
			this.t = t;
		}
		@Override public int getRuleIndex() { return RULE_list_decl_var; }
	}

	public final List_decl_varContext list_decl_var(ListDeclVar l,AbstractIdentifier t) throws RecognitionException {
		List_decl_varContext _localctx = new List_decl_varContext(_ctx, getState(), l, t);
		enterRule(_localctx, 10, RULE_list_decl_var);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(102);
			((List_decl_varContext)_localctx).dv1 = decl_var(_localctx.t);

			        _localctx.l.add(((List_decl_varContext)_localctx).dv1.tree);
			        
			setState(110);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(104);
				match(COMMA);
				setState(105);
				((List_decl_varContext)_localctx).dv2 = decl_var(_localctx.t);

				        _localctx.l.add(((List_decl_varContext)_localctx).dv2.tree);
				        
				}
				}
				setState(112);
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

	public static class Decl_varContext extends ParserRuleContext {
		public AbstractIdentifier t;
		public AbstractDeclVar tree;
		public IdentContext i;
		public ExprContext e;
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public TerminalNode EQUALS() { return getToken(DecaParser.EQUALS, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Decl_varContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public Decl_varContext(ParserRuleContext parent, int invokingState, AbstractIdentifier t) {
			super(parent, invokingState);
			this.t = t;
		}
		@Override public int getRuleIndex() { return RULE_decl_var; }
	}

	public final Decl_varContext decl_var(AbstractIdentifier t) throws RecognitionException {
		Decl_varContext _localctx = new Decl_varContext(_ctx, getState(), t);
		enterRule(_localctx, 12, RULE_decl_var);

		            AbstractInitialization init;
		        
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(113);
			((Decl_varContext)_localctx).i = ident();

			            init = new NoInitialization();
			        
			setState(119);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==EQUALS) {
				{
				setState(115);
				match(EQUALS);
				setState(116);
				((Decl_varContext)_localctx).e = expr();

				            init = new Initialization(((Decl_varContext)_localctx).e.tree);
				        
				}
			}


			            /*setLocation(init, (((Decl_varContext)_localctx).i!=null?(((Decl_varContext)_localctx).i.start):null));
			            ((Decl_varContext)_localctx).tree =  new DeclVar(AbstractDeclVar, i.tree, init);
			            setLocation(_localctx.tree, (((Decl_varContext)_localctx).i!=null?(((Decl_varContext)_localctx).i.start):null));
			            setLocation(((Decl_varContext)_localctx).i.tree, (((Decl_varContext)_localctx).i!=null?(((Decl_varContext)_localctx).i.start):null));*/
			        
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

	public static class List_instContext extends ParserRuleContext {
		public ListInst tree;
		public InstContext inst;
		public List<InstContext> inst() {
			return getRuleContexts(InstContext.class);
		}
		public InstContext inst(int i) {
			return getRuleContext(InstContext.class,i);
		}
		public List_instContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_list_inst; }
	}

	public final List_instContext list_inst() throws RecognitionException {
		List_instContext _localctx = new List_instContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_list_inst);

		    ((List_instContext)_localctx).tree =  new ListInst();

		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(128);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << FALSE) | (1L << IF) | (1L << NEW) | (1L << NULL) | (1L << READINT) | (1L << READFLOAT) | (1L << PRINT) | (1L << PRINTLN) | (1L << PRINTX) | (1L << PRINTLNX) | (1L << RETURN) | (1L << THIS) | (1L << TRUE) | (1L << WHILE) | (1L << IDENT) | (1L << MINUS) | (1L << OPARENT) | (1L << EXCLAM) | (1L << SEMI) | (1L << INT) | (1L << FLOAT) | (1L << STRING))) != 0)) {
				{
				{
				setState(123);
				((List_instContext)_localctx).inst = inst();

				        _localctx.tree.add(((List_instContext)_localctx).inst.tree);
				        setLocation(((List_instContext)_localctx).inst.tree, (((List_instContext)_localctx).inst!=null?(((List_instContext)_localctx).inst.start):null));
				        
				}
				}
				setState(130);
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

	public static class InstContext extends ParserRuleContext {
		public AbstractInst tree;
		public ExprContext e1;
		public List_exprContext list_expr;
		public If_then_elseContext if_then_else;
		public ExprContext condition;
		public List_instContext body;
		public ExprContext expr;
		public TerminalNode SEMI() { return getToken(DecaParser.SEMI, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode PRINT() { return getToken(DecaParser.PRINT, 0); }
		public TerminalNode OPARENT() { return getToken(DecaParser.OPARENT, 0); }
		public List_exprContext list_expr() {
			return getRuleContext(List_exprContext.class,0);
		}
		public TerminalNode CPARENT() { return getToken(DecaParser.CPARENT, 0); }
		public TerminalNode PRINTLN() { return getToken(DecaParser.PRINTLN, 0); }
		public TerminalNode PRINTX() { return getToken(DecaParser.PRINTX, 0); }
		public TerminalNode PRINTLNX() { return getToken(DecaParser.PRINTLNX, 0); }
		public If_then_elseContext if_then_else() {
			return getRuleContext(If_then_elseContext.class,0);
		}
		public TerminalNode WHILE() { return getToken(DecaParser.WHILE, 0); }
		public TerminalNode OBRACE() { return getToken(DecaParser.OBRACE, 0); }
		public TerminalNode CBRACE() { return getToken(DecaParser.CBRACE, 0); }
		public List_instContext list_inst() {
			return getRuleContext(List_instContext.class,0);
		}
		public TerminalNode RETURN() { return getToken(DecaParser.RETURN, 0); }
		public InstContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_inst; }
	}

	public final InstContext inst() throws RecognitionException {
		InstContext _localctx = new InstContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_inst);
		try {
			setState(182);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FALSE:
			case NEW:
			case NULL:
			case READINT:
			case READFLOAT:
			case THIS:
			case TRUE:
			case IDENT:
			case MINUS:
			case OPARENT:
			case EXCLAM:
			case INT:
			case FLOAT:
			case STRING:
				enterOuterAlt(_localctx, 1);
				{
				setState(131);
				((InstContext)_localctx).e1 = expr();
				setState(132);
				match(SEMI);

				            assert(((InstContext)_localctx).e1.tree != null);
				            ((InstContext)_localctx).tree =  ((InstContext)_localctx).e1.tree;
				        
				}
				break;
			case SEMI:
				enterOuterAlt(_localctx, 2);
				{
				setState(135);
				match(SEMI);

				            ((InstContext)_localctx).tree =  new NoOperation();
				        
				}
				break;
			case PRINT:
				enterOuterAlt(_localctx, 3);
				{
				setState(137);
				match(PRINT);
				setState(138);
				match(OPARENT);
				setState(139);
				((InstContext)_localctx).list_expr = list_expr();
				setState(140);
				match(CPARENT);
				setState(141);
				match(SEMI);

				            assert(((InstContext)_localctx).list_expr.tree != null);
				            ((InstContext)_localctx).tree =  new Print(false, ((InstContext)_localctx).list_expr.tree);
				            setLocation(_localctx.tree, (((InstContext)_localctx).list_expr!=null?(((InstContext)_localctx).list_expr.start):null));
				        
				}
				break;
			case PRINTLN:
				enterOuterAlt(_localctx, 4);
				{
				setState(144);
				match(PRINTLN);
				setState(145);
				match(OPARENT);
				setState(146);
				((InstContext)_localctx).list_expr = list_expr();
				setState(147);
				match(CPARENT);
				setState(148);
				match(SEMI);

				            assert(((InstContext)_localctx).list_expr.tree != null);
				            ((InstContext)_localctx).tree =  new Println(false, ((InstContext)_localctx).list_expr.tree);
				            setLocation(_localctx.tree, (((InstContext)_localctx).list_expr!=null?(((InstContext)_localctx).list_expr.start):null));
				        
				}
				break;
			case PRINTX:
				enterOuterAlt(_localctx, 5);
				{
				setState(151);
				match(PRINTX);
				setState(152);
				match(OPARENT);
				setState(153);
				((InstContext)_localctx).list_expr = list_expr();
				setState(154);
				match(CPARENT);
				setState(155);
				match(SEMI);

				            assert(((InstContext)_localctx).list_expr.tree != null);
				            ((InstContext)_localctx).tree =  new Print(true, ((InstContext)_localctx).list_expr.tree);
				            setLocation(_localctx.tree, (((InstContext)_localctx).list_expr!=null?(((InstContext)_localctx).list_expr.start):null));

				        
				}
				break;
			case PRINTLNX:
				enterOuterAlt(_localctx, 6);
				{
				setState(158);
				match(PRINTLNX);
				setState(159);
				match(OPARENT);
				setState(160);
				((InstContext)_localctx).list_expr = list_expr();
				setState(161);
				match(CPARENT);
				setState(162);
				match(SEMI);

				            assert(((InstContext)_localctx).list_expr.tree != null);
				            ((InstContext)_localctx).tree =  new Println(true, ((InstContext)_localctx).list_expr.tree);
				            setLocation(_localctx.tree, (((InstContext)_localctx).list_expr!=null?(((InstContext)_localctx).list_expr.start):null));
				        
				}
				break;
			case IF:
				enterOuterAlt(_localctx, 7);
				{
				setState(165);
				((InstContext)_localctx).if_then_else = if_then_else();

				            assert(((InstContext)_localctx).if_then_else.tree != null);
				            ((InstContext)_localctx).tree =  ((InstContext)_localctx).if_then_else.tree;
				            setLocation(_localctx.tree, (((InstContext)_localctx).if_then_else!=null?(((InstContext)_localctx).if_then_else.start):null));
				        
				}
				break;
			case WHILE:
				enterOuterAlt(_localctx, 8);
				{
				setState(168);
				match(WHILE);
				setState(169);
				match(OPARENT);
				setState(170);
				((InstContext)_localctx).condition = expr();
				setState(171);
				match(CPARENT);
				setState(172);
				match(OBRACE);
				setState(173);
				((InstContext)_localctx).body = list_inst();
				setState(174);
				match(CBRACE);

				            assert(((InstContext)_localctx).condition.tree != null);
				            assert(((InstContext)_localctx).body.tree != null);
				            ((InstContext)_localctx).tree =  new While(((InstContext)_localctx).condition.tree , ((InstContext)_localctx).body.tree);
				            setLocation(_localctx.tree, (((InstContext)_localctx).condition!=null?(((InstContext)_localctx).condition.start):null));
				        
				}
				break;
			case RETURN:
				enterOuterAlt(_localctx, 9);
				{
				setState(177);
				match(RETURN);
				setState(178);
				((InstContext)_localctx).expr = expr();
				setState(179);
				match(SEMI);

				            assert(((InstContext)_localctx).expr.tree != null);
				        
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class If_then_elseContext extends ParserRuleContext {
		public IfThenElse tree;
		public Token if1;
		public ExprContext condition;
		public List_instContext li_if;
		public Token elsif;
		public ExprContext elsif_cond;
		public List_instContext elsif_li;
		public List_instContext li_else;
		public List<TerminalNode> OPARENT() { return getTokens(DecaParser.OPARENT); }
		public TerminalNode OPARENT(int i) {
			return getToken(DecaParser.OPARENT, i);
		}
		public List<TerminalNode> CPARENT() { return getTokens(DecaParser.CPARENT); }
		public TerminalNode CPARENT(int i) {
			return getToken(DecaParser.CPARENT, i);
		}
		public List<TerminalNode> OBRACE() { return getTokens(DecaParser.OBRACE); }
		public TerminalNode OBRACE(int i) {
			return getToken(DecaParser.OBRACE, i);
		}
		public List<TerminalNode> CBRACE() { return getTokens(DecaParser.CBRACE); }
		public TerminalNode CBRACE(int i) {
			return getToken(DecaParser.CBRACE, i);
		}
		public List<TerminalNode> IF() { return getTokens(DecaParser.IF); }
		public TerminalNode IF(int i) {
			return getToken(DecaParser.IF, i);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<List_instContext> list_inst() {
			return getRuleContexts(List_instContext.class);
		}
		public List_instContext list_inst(int i) {
			return getRuleContext(List_instContext.class,i);
		}
		public List<TerminalNode> ELSE() { return getTokens(DecaParser.ELSE); }
		public TerminalNode ELSE(int i) {
			return getToken(DecaParser.ELSE, i);
		}
		public If_then_elseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_if_then_else; }
	}

	public final If_then_elseContext if_then_else() throws RecognitionException {
		If_then_elseContext _localctx = new If_then_elseContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_if_then_else);


		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(184);
			((If_then_elseContext)_localctx).if1 = match(IF);
			setState(185);
			match(OPARENT);
			setState(186);
			((If_then_elseContext)_localctx).condition = expr();
			setState(187);
			match(CPARENT);
			setState(188);
			match(OBRACE);
			setState(189);
			((If_then_elseContext)_localctx).li_if = list_inst();
			setState(190);
			match(CBRACE);

			        
			setState(204);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(192);
					match(ELSE);
					setState(193);
					((If_then_elseContext)_localctx).elsif = match(IF);
					setState(194);
					match(OPARENT);
					setState(195);
					((If_then_elseContext)_localctx).elsif_cond = expr();
					setState(196);
					match(CPARENT);
					setState(197);
					match(OBRACE);
					setState(198);
					((If_then_elseContext)_localctx).elsif_li = list_inst();
					setState(199);
					match(CBRACE);

					        
					}
					} 
				}
				setState(206);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			}
			setState(213);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ELSE) {
				{
				setState(207);
				match(ELSE);
				setState(208);
				match(OBRACE);
				setState(209);
				((If_then_elseContext)_localctx).li_else = list_inst();
				setState(210);
				match(CBRACE);

				        
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

	public static class List_exprContext extends ParserRuleContext {
		public ListExpr tree;
		public ExprContext e1;
		public ExprContext e2;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(DecaParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(DecaParser.COMMA, i);
		}
		public List_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_list_expr; }
	}

	public final List_exprContext list_expr() throws RecognitionException {
		List_exprContext _localctx = new List_exprContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_list_expr);

		            ((List_exprContext)_localctx).tree =  new ListExpr();
		        
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(226);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << FALSE) | (1L << NEW) | (1L << NULL) | (1L << READINT) | (1L << READFLOAT) | (1L << THIS) | (1L << TRUE) | (1L << IDENT) | (1L << MINUS) | (1L << OPARENT) | (1L << EXCLAM) | (1L << INT) | (1L << FLOAT) | (1L << STRING))) != 0)) {
				{
				setState(215);
				((List_exprContext)_localctx).e1 = expr();

				            _localctx.tree.add(((List_exprContext)_localctx).e1.tree);
				        
				setState(223);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(217);
					match(COMMA);
					setState(218);
					((List_exprContext)_localctx).e2 = expr();

					            _localctx.tree.add(((List_exprContext)_localctx).e2.tree);
					        
					}
					}
					setState(225);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
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

	public static class ExprContext extends ParserRuleContext {
		public AbstractExpr tree;
		public Assign_exprContext assign_expr;
		public Assign_exprContext assign_expr() {
			return getRuleContext(Assign_exprContext.class,0);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_expr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(228);
			((ExprContext)_localctx).assign_expr = assign_expr();

			            assert(((ExprContext)_localctx).assign_expr.tree != null);
			            ((ExprContext)_localctx).tree =  ((ExprContext)_localctx).assign_expr.tree;
			            setLocation(_localctx.tree, (((ExprContext)_localctx).assign_expr!=null?(((ExprContext)_localctx).assign_expr.start):null));
			        
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

	public static class Assign_exprContext extends ParserRuleContext {
		public AbstractExpr tree;
		public Or_exprContext e;
		public Or_exprContext or_expr;
		public Assign_exprContext e2;
		public Assign_exprContext assign_expr;
		public Or_exprContext or_expr() {
			return getRuleContext(Or_exprContext.class,0);
		}
		public TerminalNode EQUALS() { return getToken(DecaParser.EQUALS, 0); }
		public Assign_exprContext assign_expr() {
			return getRuleContext(Assign_exprContext.class,0);
		}
		public Assign_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assign_expr; }
	}

	public final Assign_exprContext assign_expr() throws RecognitionException {
		Assign_exprContext _localctx = new Assign_exprContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_assign_expr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(231);
			((Assign_exprContext)_localctx).e = ((Assign_exprContext)_localctx).or_expr = or_expr(0);
			setState(238);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case EQUALS:
				{

				            if (! (((Assign_exprContext)_localctx).e.tree instanceof AbstractLValue)) {
				                throw new InvalidLValue(this, _localctx);
				            }
				            ((Assign_exprContext)_localctx).tree =  ((Assign_exprContext)_localctx).e.tree;
				            setLocation(_localctx.tree, (((Assign_exprContext)_localctx).or_expr!=null?(((Assign_exprContext)_localctx).or_expr.start):null));
				        
				setState(233);
				match(EQUALS);
				setState(234);
				((Assign_exprContext)_localctx).e2 = ((Assign_exprContext)_localctx).assign_expr = assign_expr();

				            assert(((Assign_exprContext)_localctx).e.tree != null);
				            assert(((Assign_exprContext)_localctx).e2.tree != null);
				            ((Assign_exprContext)_localctx).tree =  new Equals(((Assign_exprContext)_localctx).e.tree, ((Assign_exprContext)_localctx).e2.tree);
				            setLocation(_localctx.tree, (((Assign_exprContext)_localctx).assign_expr!=null?(((Assign_exprContext)_localctx).assign_expr.start):null));
				        
				}
				break;
			case COMMA:
			case CPARENT:
			case SEMI:
				{

				            assert(((Assign_exprContext)_localctx).e.tree != null);
				            ((Assign_exprContext)_localctx).tree =  ((Assign_exprContext)_localctx).e.tree;
				        
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class Or_exprContext extends ParserRuleContext {
		public AbstractExpr tree;
		public Or_exprContext e1;
		public And_exprContext e;
		public And_exprContext e2;
		public And_exprContext and_expr() {
			return getRuleContext(And_exprContext.class,0);
		}
		public TerminalNode OR() { return getToken(DecaParser.OR, 0); }
		public Or_exprContext or_expr() {
			return getRuleContext(Or_exprContext.class,0);
		}
		public Or_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_or_expr; }
	}

	public final Or_exprContext or_expr() throws RecognitionException {
		return or_expr(0);
	}

	private Or_exprContext or_expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Or_exprContext _localctx = new Or_exprContext(_ctx, _parentState);
		Or_exprContext _prevctx = _localctx;
		int _startState = 26;
		enterRecursionRule(_localctx, 26, RULE_or_expr, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(241);
			((Or_exprContext)_localctx).e = and_expr(0);

			            assert(((Or_exprContext)_localctx).e.tree != null);
			            ((Or_exprContext)_localctx).tree =  ((Or_exprContext)_localctx).e.tree;
			            setLocation(_localctx.tree, (((Or_exprContext)_localctx).e!=null?(((Or_exprContext)_localctx).e.start):null));
			        
			}
			_ctx.stop = _input.LT(-1);
			setState(251);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new Or_exprContext(_parentctx, _parentState);
					_localctx.e1 = _prevctx;
					_localctx.e1 = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_or_expr);
					setState(244);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(245);
					match(OR);
					setState(246);
					((Or_exprContext)_localctx).e2 = and_expr(0);

					                      assert(((Or_exprContext)_localctx).e1.tree != null);
					                      assert(((Or_exprContext)_localctx).e2.tree != null);
					                      ((Or_exprContext)_localctx).tree =  new Or(((Or_exprContext)_localctx).e1.tree, ((Or_exprContext)_localctx).e2.tree);
					                      setLocation(_localctx.tree, (((Or_exprContext)_localctx).e1!=null?(((Or_exprContext)_localctx).e1.start):null));
					                 
					}
					} 
				}
				setState(253);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
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

	public static class And_exprContext extends ParserRuleContext {
		public AbstractExpr tree;
		public And_exprContext e1;
		public Eq_neq_exprContext e;
		public Eq_neq_exprContext e2;
		public Eq_neq_exprContext eq_neq_expr() {
			return getRuleContext(Eq_neq_exprContext.class,0);
		}
		public TerminalNode AND() { return getToken(DecaParser.AND, 0); }
		public And_exprContext and_expr() {
			return getRuleContext(And_exprContext.class,0);
		}
		public And_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_and_expr; }
	}

	public final And_exprContext and_expr() throws RecognitionException {
		return and_expr(0);
	}

	private And_exprContext and_expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		And_exprContext _localctx = new And_exprContext(_ctx, _parentState);
		And_exprContext _prevctx = _localctx;
		int _startState = 28;
		enterRecursionRule(_localctx, 28, RULE_and_expr, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(255);
			((And_exprContext)_localctx).e = eq_neq_expr(0);

			            assert(((And_exprContext)_localctx).e.tree != null);
			            ((And_exprContext)_localctx).tree =  ((And_exprContext)_localctx).e.tree;
			            setLocation(_localctx.tree, (((And_exprContext)_localctx).e!=null?(((And_exprContext)_localctx).e.start):null));
			        
			}
			_ctx.stop = _input.LT(-1);
			setState(265);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new And_exprContext(_parentctx, _parentState);
					_localctx.e1 = _prevctx;
					_localctx.e1 = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_and_expr);
					setState(258);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(259);
					match(AND);
					setState(260);
					((And_exprContext)_localctx).e2 = eq_neq_expr(0);

					                      assert(((And_exprContext)_localctx).e1.tree != null);                         
					                      assert(((And_exprContext)_localctx).e2.tree != null);
					                      ((And_exprContext)_localctx).tree =  new And(((And_exprContext)_localctx).e1.tree, ((And_exprContext)_localctx).e2.tree);
					                      setLocation(_localctx.tree, (((And_exprContext)_localctx).e1!=null?(((And_exprContext)_localctx).e1.start):null));
					                  
					}
					} 
				}
				setState(267);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
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

	public static class Eq_neq_exprContext extends ParserRuleContext {
		public AbstractExpr tree;
		public Eq_neq_exprContext e1;
		public Inequality_exprContext e;
		public Inequality_exprContext e2;
		public Inequality_exprContext inequality_expr() {
			return getRuleContext(Inequality_exprContext.class,0);
		}
		public TerminalNode EQEQ() { return getToken(DecaParser.EQEQ, 0); }
		public Eq_neq_exprContext eq_neq_expr() {
			return getRuleContext(Eq_neq_exprContext.class,0);
		}
		public TerminalNode NEQ() { return getToken(DecaParser.NEQ, 0); }
		public Eq_neq_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_eq_neq_expr; }
	}

	public final Eq_neq_exprContext eq_neq_expr() throws RecognitionException {
		return eq_neq_expr(0);
	}

	private Eq_neq_exprContext eq_neq_expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Eq_neq_exprContext _localctx = new Eq_neq_exprContext(_ctx, _parentState);
		Eq_neq_exprContext _prevctx = _localctx;
		int _startState = 30;
		enterRecursionRule(_localctx, 30, RULE_eq_neq_expr, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(269);
			((Eq_neq_exprContext)_localctx).e = inequality_expr(0);

			            assert(((Eq_neq_exprContext)_localctx).e.tree != null);
			            ((Eq_neq_exprContext)_localctx).tree =  ((Eq_neq_exprContext)_localctx).e.tree;
			            setLocation(_localctx.tree, (((Eq_neq_exprContext)_localctx).e!=null?(((Eq_neq_exprContext)_localctx).e.start):null));
			        
			}
			_ctx.stop = _input.LT(-1);
			setState(284);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(282);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
					case 1:
						{
						_localctx = new Eq_neq_exprContext(_parentctx, _parentState);
						_localctx.e1 = _prevctx;
						_localctx.e1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_eq_neq_expr);
						setState(272);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(273);
						match(EQEQ);
						setState(274);
						((Eq_neq_exprContext)_localctx).e2 = inequality_expr(0);

						                      assert(((Eq_neq_exprContext)_localctx).e1.tree != null);
						                      assert(((Eq_neq_exprContext)_localctx).e2.tree != null);
						                  
						}
						break;
					case 2:
						{
						_localctx = new Eq_neq_exprContext(_parentctx, _parentState);
						_localctx.e1 = _prevctx;
						_localctx.e1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_eq_neq_expr);
						setState(277);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(278);
						match(NEQ);
						setState(279);
						((Eq_neq_exprContext)_localctx).e2 = inequality_expr(0);

						                      assert(((Eq_neq_exprContext)_localctx).e1.tree != null);
						                      assert(((Eq_neq_exprContext)_localctx).e2.tree != null);
						                      ((Eq_neq_exprContext)_localctx).tree =  new NotEquals(((Eq_neq_exprContext)_localctx).e1.tree, ((Eq_neq_exprContext)_localctx).e2.tree);
						                      setLocation(_localctx.tree, (((Eq_neq_exprContext)_localctx).e1!=null?(((Eq_neq_exprContext)_localctx).e1.start):null));
						                  
						}
						break;
					}
					} 
				}
				setState(286);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
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

	public static class Inequality_exprContext extends ParserRuleContext {
		public AbstractExpr tree;
		public Inequality_exprContext e1;
		public Sum_exprContext e;
		public Sum_exprContext e2;
		public TypeContext type;
		public Sum_exprContext sum_expr() {
			return getRuleContext(Sum_exprContext.class,0);
		}
		public TerminalNode LEQ() { return getToken(DecaParser.LEQ, 0); }
		public Inequality_exprContext inequality_expr() {
			return getRuleContext(Inequality_exprContext.class,0);
		}
		public TerminalNode GEQ() { return getToken(DecaParser.GEQ, 0); }
		public TerminalNode GT() { return getToken(DecaParser.GT, 0); }
		public TerminalNode LT() { return getToken(DecaParser.LT, 0); }
		public TerminalNode INSTANCEOF() { return getToken(DecaParser.INSTANCEOF, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public Inequality_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_inequality_expr; }
	}

	public final Inequality_exprContext inequality_expr() throws RecognitionException {
		return inequality_expr(0);
	}

	private Inequality_exprContext inequality_expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Inequality_exprContext _localctx = new Inequality_exprContext(_ctx, _parentState);
		Inequality_exprContext _prevctx = _localctx;
		int _startState = 32;
		enterRecursionRule(_localctx, 32, RULE_inequality_expr, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(288);
			((Inequality_exprContext)_localctx).e = sum_expr(0);

			            assert(((Inequality_exprContext)_localctx).e.tree != null);
			            ((Inequality_exprContext)_localctx).tree =  ((Inequality_exprContext)_localctx).e.tree;
			            setLocation(_localctx.tree, (((Inequality_exprContext)_localctx).e!=null?(((Inequality_exprContext)_localctx).e.start):null));
			        
			}
			_ctx.stop = _input.LT(-1);
			setState(318);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(316);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
					case 1:
						{
						_localctx = new Inequality_exprContext(_parentctx, _parentState);
						_localctx.e1 = _prevctx;
						_localctx.e1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_inequality_expr);
						setState(291);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(292);
						match(LEQ);
						setState(293);
						((Inequality_exprContext)_localctx).e2 = sum_expr(0);

						                      assert(((Inequality_exprContext)_localctx).e1.tree != null);
						                      assert(((Inequality_exprContext)_localctx).e2.tree != null);
						                      ((Inequality_exprContext)_localctx).tree =  new LowerOrEqual(((Inequality_exprContext)_localctx).e1.tree, ((Inequality_exprContext)_localctx).e2.tree);
						                      setLocation(_localctx.tree, (((Inequality_exprContext)_localctx).e1!=null?(((Inequality_exprContext)_localctx).e1.start):null));
						                  
						}
						break;
					case 2:
						{
						_localctx = new Inequality_exprContext(_parentctx, _parentState);
						_localctx.e1 = _prevctx;
						_localctx.e1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_inequality_expr);
						setState(296);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(297);
						match(GEQ);
						setState(298);
						((Inequality_exprContext)_localctx).e2 = sum_expr(0);

						                      assert(((Inequality_exprContext)_localctx).e1.tree != null);
						                      assert(((Inequality_exprContext)_localctx).e2.tree != null);
						                      ((Inequality_exprContext)_localctx).tree =  new GreaterOrEqual(((Inequality_exprContext)_localctx).e1.tree, ((Inequality_exprContext)_localctx).e2.tree);
						                      setLocation(_localctx.tree, (((Inequality_exprContext)_localctx).e1!=null?(((Inequality_exprContext)_localctx).e1.start):null));
						                  
						}
						break;
					case 3:
						{
						_localctx = new Inequality_exprContext(_parentctx, _parentState);
						_localctx.e1 = _prevctx;
						_localctx.e1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_inequality_expr);
						setState(301);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(302);
						match(GT);
						setState(303);
						((Inequality_exprContext)_localctx).e2 = sum_expr(0);

						                      assert(((Inequality_exprContext)_localctx).e1.tree != null);
						                      assert(((Inequality_exprContext)_localctx).e2.tree != null);
						                      ((Inequality_exprContext)_localctx).tree =  new Greater(((Inequality_exprContext)_localctx).e1.tree, ((Inequality_exprContext)_localctx).e2.tree);
						                      setLocation(_localctx.tree, (((Inequality_exprContext)_localctx).e1!=null?(((Inequality_exprContext)_localctx).e1.start):null));
						                  
						}
						break;
					case 4:
						{
						_localctx = new Inequality_exprContext(_parentctx, _parentState);
						_localctx.e1 = _prevctx;
						_localctx.e1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_inequality_expr);
						setState(306);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(307);
						match(LT);
						setState(308);
						((Inequality_exprContext)_localctx).e2 = sum_expr(0);

						                      assert(((Inequality_exprContext)_localctx).e1.tree != null);
						                      assert(((Inequality_exprContext)_localctx).e2.tree != null);
						                      ((Inequality_exprContext)_localctx).tree =  new Lower(((Inequality_exprContext)_localctx).e1.tree, ((Inequality_exprContext)_localctx).e2.tree);
						                      setLocation(_localctx.tree, (((Inequality_exprContext)_localctx).e1!=null?(((Inequality_exprContext)_localctx).e1.start):null));
						                  
						}
						break;
					case 5:
						{
						_localctx = new Inequality_exprContext(_parentctx, _parentState);
						_localctx.e1 = _prevctx;
						_localctx.e1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_inequality_expr);
						setState(311);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(312);
						match(INSTANCEOF);
						setState(313);
						((Inequality_exprContext)_localctx).type = type();

						                      assert(((Inequality_exprContext)_localctx).e1.tree != null);
						                      assert(((Inequality_exprContext)_localctx).type.tree != null);
						                  
						}
						break;
					}
					} 
				}
				setState(320);
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

	public static class Sum_exprContext extends ParserRuleContext {
		public AbstractExpr tree;
		public Sum_exprContext e1;
		public Mult_exprContext e;
		public Mult_exprContext e2;
		public Mult_exprContext mult_expr() {
			return getRuleContext(Mult_exprContext.class,0);
		}
		public TerminalNode PLUS() { return getToken(DecaParser.PLUS, 0); }
		public Sum_exprContext sum_expr() {
			return getRuleContext(Sum_exprContext.class,0);
		}
		public TerminalNode MINUS() { return getToken(DecaParser.MINUS, 0); }
		public Sum_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sum_expr; }
	}

	public final Sum_exprContext sum_expr() throws RecognitionException {
		return sum_expr(0);
	}

	private Sum_exprContext sum_expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Sum_exprContext _localctx = new Sum_exprContext(_ctx, _parentState);
		Sum_exprContext _prevctx = _localctx;
		int _startState = 34;
		enterRecursionRule(_localctx, 34, RULE_sum_expr, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(322);
			((Sum_exprContext)_localctx).e = mult_expr(0);

			            assert(((Sum_exprContext)_localctx).e.tree != null);
			            ((Sum_exprContext)_localctx).tree =  ((Sum_exprContext)_localctx).e.tree;
			            setLocation(_localctx.tree, (((Sum_exprContext)_localctx).e!=null?(((Sum_exprContext)_localctx).e.start):null));
			        
			}
			_ctx.stop = _input.LT(-1);
			setState(337);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(335);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
					case 1:
						{
						_localctx = new Sum_exprContext(_parentctx, _parentState);
						_localctx.e1 = _prevctx;
						_localctx.e1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_sum_expr);
						setState(325);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(326);
						match(PLUS);
						setState(327);
						((Sum_exprContext)_localctx).e2 = mult_expr(0);

						                      assert(((Sum_exprContext)_localctx).e1.tree != null);
						                      assert(((Sum_exprContext)_localctx).e2.tree != null);
						                      ((Sum_exprContext)_localctx).tree =  new Plus(((Sum_exprContext)_localctx).e1.tree, ((Sum_exprContext)_localctx).e2.tree);
						                      setLocation(_localctx.tree, (((Sum_exprContext)_localctx).e1!=null?(((Sum_exprContext)_localctx).e1.start):null));
						                  
						}
						break;
					case 2:
						{
						_localctx = new Sum_exprContext(_parentctx, _parentState);
						_localctx.e1 = _prevctx;
						_localctx.e1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_sum_expr);
						setState(330);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(331);
						match(MINUS);
						setState(332);
						((Sum_exprContext)_localctx).e2 = mult_expr(0);

						                      assert(((Sum_exprContext)_localctx).e1.tree != null);
						                      assert(((Sum_exprContext)_localctx).e2.tree != null);
						                      ((Sum_exprContext)_localctx).tree =  new Minus(((Sum_exprContext)_localctx).e1.tree, ((Sum_exprContext)_localctx).e2.tree);
						                      setLocation(_localctx.tree, (((Sum_exprContext)_localctx).e1!=null?(((Sum_exprContext)_localctx).e1.start):null));
						                  
						}
						break;
					}
					} 
				}
				setState(339);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
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

	public static class Mult_exprContext extends ParserRuleContext {
		public AbstractExpr tree;
		public Mult_exprContext e1;
		public Unary_exprContext e;
		public Unary_exprContext e2;
		public Unary_exprContext unary_expr() {
			return getRuleContext(Unary_exprContext.class,0);
		}
		public TerminalNode TIMES() { return getToken(DecaParser.TIMES, 0); }
		public Mult_exprContext mult_expr() {
			return getRuleContext(Mult_exprContext.class,0);
		}
		public TerminalNode SLASH() { return getToken(DecaParser.SLASH, 0); }
		public TerminalNode PERCENT() { return getToken(DecaParser.PERCENT, 0); }
		public Mult_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mult_expr; }
	}

	public final Mult_exprContext mult_expr() throws RecognitionException {
		return mult_expr(0);
	}

	private Mult_exprContext mult_expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Mult_exprContext _localctx = new Mult_exprContext(_ctx, _parentState);
		Mult_exprContext _prevctx = _localctx;
		int _startState = 36;
		enterRecursionRule(_localctx, 36, RULE_mult_expr, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(341);
			((Mult_exprContext)_localctx).e = unary_expr();

			            assert(((Mult_exprContext)_localctx).e.tree != null);
			            ((Mult_exprContext)_localctx).tree =  ((Mult_exprContext)_localctx).e.tree;
			            setLocation(_localctx.tree, (((Mult_exprContext)_localctx).e!=null?(((Mult_exprContext)_localctx).e.start):null));
			        
			}
			_ctx.stop = _input.LT(-1);
			setState(361);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(359);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
					case 1:
						{
						_localctx = new Mult_exprContext(_parentctx, _parentState);
						_localctx.e1 = _prevctx;
						_localctx.e1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_mult_expr);
						setState(344);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(345);
						match(TIMES);
						setState(346);
						((Mult_exprContext)_localctx).e2 = unary_expr();

						                      assert(((Mult_exprContext)_localctx).e1.tree != null);                                         
						                      assert(((Mult_exprContext)_localctx).e2.tree != null);
						                      ((Mult_exprContext)_localctx).tree =  new Multiply(((Mult_exprContext)_localctx).e1.tree, ((Mult_exprContext)_localctx).e2.tree);
						                      setLocation(_localctx.tree, (((Mult_exprContext)_localctx).e1!=null?(((Mult_exprContext)_localctx).e1.start):null));
						                  
						}
						break;
					case 2:
						{
						_localctx = new Mult_exprContext(_parentctx, _parentState);
						_localctx.e1 = _prevctx;
						_localctx.e1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_mult_expr);
						setState(349);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(350);
						match(SLASH);
						setState(351);
						((Mult_exprContext)_localctx).e2 = unary_expr();

						                      assert(((Mult_exprContext)_localctx).e1.tree != null);                                         
						                      assert(((Mult_exprContext)_localctx).e2.tree != null);
						                      ((Mult_exprContext)_localctx).tree =  new Divide(((Mult_exprContext)_localctx).e1.tree, ((Mult_exprContext)_localctx).e2.tree);
						                      setLocation(_localctx.tree, (((Mult_exprContext)_localctx).e1!=null?(((Mult_exprContext)_localctx).e1.start):null));
						                  
						}
						break;
					case 3:
						{
						_localctx = new Mult_exprContext(_parentctx, _parentState);
						_localctx.e1 = _prevctx;
						_localctx.e1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_mult_expr);
						setState(354);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(355);
						match(PERCENT);
						setState(356);
						((Mult_exprContext)_localctx).e2 = unary_expr();

						                      assert(((Mult_exprContext)_localctx).e1.tree != null);                                                                          
						                      assert(((Mult_exprContext)_localctx).e2.tree != null);
						                      ((Mult_exprContext)_localctx).tree =  new Modulo(((Mult_exprContext)_localctx).e1.tree, ((Mult_exprContext)_localctx).e2.tree);
						                      setLocation(_localctx.tree, (((Mult_exprContext)_localctx).e1!=null?(((Mult_exprContext)_localctx).e1.start):null));
						                  
						}
						break;
					}
					} 
				}
				setState(363);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
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

	public static class Unary_exprContext extends ParserRuleContext {
		public AbstractExpr tree;
		public Token op;
		public Unary_exprContext e;
		public Select_exprContext select_expr;
		public TerminalNode MINUS() { return getToken(DecaParser.MINUS, 0); }
		public Unary_exprContext unary_expr() {
			return getRuleContext(Unary_exprContext.class,0);
		}
		public TerminalNode EXCLAM() { return getToken(DecaParser.EXCLAM, 0); }
		public Select_exprContext select_expr() {
			return getRuleContext(Select_exprContext.class,0);
		}
		public Unary_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unary_expr; }
	}

	public final Unary_exprContext unary_expr() throws RecognitionException {
		Unary_exprContext _localctx = new Unary_exprContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_unary_expr);
		try {
			setState(375);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case MINUS:
				enterOuterAlt(_localctx, 1);
				{
				setState(364);
				((Unary_exprContext)_localctx).op = match(MINUS);
				setState(365);
				((Unary_exprContext)_localctx).e = unary_expr();

				            assert(((Unary_exprContext)_localctx).e.tree != null);
				        
				}
				break;
			case EXCLAM:
				enterOuterAlt(_localctx, 2);
				{
				setState(368);
				((Unary_exprContext)_localctx).op = match(EXCLAM);
				setState(369);
				((Unary_exprContext)_localctx).e = unary_expr();

				            assert(((Unary_exprContext)_localctx).e.tree != null);
				        
				}
				break;
			case FALSE:
			case NEW:
			case NULL:
			case READINT:
			case READFLOAT:
			case THIS:
			case TRUE:
			case IDENT:
			case OPARENT:
			case INT:
			case FLOAT:
			case STRING:
				enterOuterAlt(_localctx, 3);
				{
				setState(372);
				((Unary_exprContext)_localctx).select_expr = select_expr(0);

				            assert(((Unary_exprContext)_localctx).select_expr.tree != null);
				            ((Unary_exprContext)_localctx).tree =  ((Unary_exprContext)_localctx).select_expr.tree;
				            setLocation(_localctx.tree, (((Unary_exprContext)_localctx).select_expr!=null?(((Unary_exprContext)_localctx).select_expr.start):null));
				        
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class Select_exprContext extends ParserRuleContext {
		public AbstractExpr tree;
		public Select_exprContext e1;
		public Primary_exprContext e;
		public IdentContext i;
		public Token o;
		public List_exprContext args;
		public Primary_exprContext primary_expr() {
			return getRuleContext(Primary_exprContext.class,0);
		}
		public TerminalNode DOT() { return getToken(DecaParser.DOT, 0); }
		public Select_exprContext select_expr() {
			return getRuleContext(Select_exprContext.class,0);
		}
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public TerminalNode CPARENT() { return getToken(DecaParser.CPARENT, 0); }
		public TerminalNode OPARENT() { return getToken(DecaParser.OPARENT, 0); }
		public List_exprContext list_expr() {
			return getRuleContext(List_exprContext.class,0);
		}
		public Select_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_select_expr; }
	}

	public final Select_exprContext select_expr() throws RecognitionException {
		return select_expr(0);
	}

	private Select_exprContext select_expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Select_exprContext _localctx = new Select_exprContext(_ctx, _parentState);
		Select_exprContext _prevctx = _localctx;
		int _startState = 40;
		enterRecursionRule(_localctx, 40, RULE_select_expr, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(378);
			((Select_exprContext)_localctx).e = primary_expr();

			            assert(((Select_exprContext)_localctx).e.tree != null);
			            ((Select_exprContext)_localctx).tree =  ((Select_exprContext)_localctx).e.tree;
			            setLocation(_localctx.tree, (((Select_exprContext)_localctx).e!=null?(((Select_exprContext)_localctx).e.start):null));
			        
			}
			_ctx.stop = _input.LT(-1);
			setState(395);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new Select_exprContext(_parentctx, _parentState);
					_localctx.e1 = _prevctx;
					_localctx.e1 = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_select_expr);
					setState(381);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(382);
					match(DOT);
					setState(383);
					((Select_exprContext)_localctx).i = ident();

					                      assert(((Select_exprContext)_localctx).e1.tree != null);
					                      assert(((Select_exprContext)_localctx).i.tree != null);
					                  
					setState(391);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
					case 1:
						{
						setState(385);
						((Select_exprContext)_localctx).o = match(OPARENT);
						setState(386);
						((Select_exprContext)_localctx).args = list_expr();
						setState(387);
						match(CPARENT);

						                      // we matched "e1.i(args)"
						                      assert(((Select_exprContext)_localctx).args.tree != null);
						                  
						}
						break;
					case 2:
						{

						                      // we matched "e.i"
						                  
						}
						break;
					}
					}
					} 
				}
				setState(397);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
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

	public static class Primary_exprContext extends ParserRuleContext {
		public AbstractExpr tree;
		public IdentContext ident;
		public IdentContext m;
		public List_exprContext args;
		public ExprContext expr;
		public Token cast;
		public TypeContext type;
		public LiteralContext literal;
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public List<TerminalNode> OPARENT() { return getTokens(DecaParser.OPARENT); }
		public TerminalNode OPARENT(int i) {
			return getToken(DecaParser.OPARENT, i);
		}
		public List<TerminalNode> CPARENT() { return getTokens(DecaParser.CPARENT); }
		public TerminalNode CPARENT(int i) {
			return getToken(DecaParser.CPARENT, i);
		}
		public List_exprContext list_expr() {
			return getRuleContext(List_exprContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode READINT() { return getToken(DecaParser.READINT, 0); }
		public TerminalNode READFLOAT() { return getToken(DecaParser.READFLOAT, 0); }
		public TerminalNode NEW() { return getToken(DecaParser.NEW, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public Primary_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primary_expr; }
	}

	public final Primary_exprContext primary_expr() throws RecognitionException {
		Primary_exprContext _localctx = new Primary_exprContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_primary_expr);
		try {
			setState(437);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(398);
				((Primary_exprContext)_localctx).ident = ident();

				            assert(((Primary_exprContext)_localctx).ident.tree != null);
				            ((Primary_exprContext)_localctx).tree =  ((Primary_exprContext)_localctx).ident.tree;
				            setLocation(_localctx.tree, (((Primary_exprContext)_localctx).ident!=null?(((Primary_exprContext)_localctx).ident.start):null));
				        
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(401);
				((Primary_exprContext)_localctx).m = ident();
				setState(402);
				match(OPARENT);
				setState(403);
				((Primary_exprContext)_localctx).args = list_expr();
				setState(404);
				match(CPARENT);

				            assert(((Primary_exprContext)_localctx).args.tree != null);
				            assert(((Primary_exprContext)_localctx).m.tree != null);
				        
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(407);
				match(OPARENT);
				setState(408);
				((Primary_exprContext)_localctx).expr = expr();
				setState(409);
				match(CPARENT);

				            assert(((Primary_exprContext)_localctx).expr.tree != null);
				        
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(412);
				match(READINT);
				setState(413);
				match(OPARENT);
				setState(414);
				match(CPARENT);

				        
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(416);
				match(READFLOAT);
				setState(417);
				match(OPARENT);
				setState(418);
				match(CPARENT);

				        
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(420);
				match(NEW);
				setState(421);
				((Primary_exprContext)_localctx).ident = ident();
				setState(422);
				match(OPARENT);
				setState(423);
				match(CPARENT);

				            assert(((Primary_exprContext)_localctx).ident.tree != null);
				        
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(426);
				((Primary_exprContext)_localctx).cast = match(OPARENT);
				setState(427);
				((Primary_exprContext)_localctx).type = type();
				setState(428);
				match(CPARENT);
				setState(429);
				match(OPARENT);
				setState(430);
				((Primary_exprContext)_localctx).expr = expr();
				setState(431);
				match(CPARENT);

				            assert(((Primary_exprContext)_localctx).type.tree != null);
				            assert(((Primary_exprContext)_localctx).expr.tree != null);
				        
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(434);
				((Primary_exprContext)_localctx).literal = literal();

				            assert(((Primary_exprContext)_localctx).literal.tree != null);
				            ((Primary_exprContext)_localctx).tree =  ((Primary_exprContext)_localctx).literal.tree;
				            setLocation(_localctx.tree, (((Primary_exprContext)_localctx).literal!=null?(((Primary_exprContext)_localctx).literal.start):null));
				        
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

	public static class TypeContext extends ParserRuleContext {
		public AbstractIdentifier tree;
		public IdentContext ident;
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_type);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(439);
			((TypeContext)_localctx).ident = ident();

			            assert(((TypeContext)_localctx).ident.tree != null);
			            ((TypeContext)_localctx).tree =  ((TypeContext)_localctx).ident.tree;
			            setLocation(_localctx.tree, (((TypeContext)_localctx).ident!=null?(((TypeContext)_localctx).ident.start):null));
			        
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

	public static class LiteralContext extends ParserRuleContext {
		public AbstractExpr tree;
		public Token INT;
		public Token fd;
		public Token STRING;
		public TerminalNode INT() { return getToken(DecaParser.INT, 0); }
		public TerminalNode FLOAT() { return getToken(DecaParser.FLOAT, 0); }
		public TerminalNode STRING() { return getToken(DecaParser.STRING, 0); }
		public TerminalNode TRUE() { return getToken(DecaParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(DecaParser.FALSE, 0); }
		public TerminalNode THIS() { return getToken(DecaParser.THIS, 0); }
		public TerminalNode NULL() { return getToken(DecaParser.NULL, 0); }
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_literal);
		try {
			setState(456);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT:
				enterOuterAlt(_localctx, 1);
				{
				setState(442);
				((LiteralContext)_localctx).INT = match(INT);
				 ((LiteralContext)_localctx).tree =  new IntLiteral(Integer.parseInt(((LiteralContext)_localctx).INT.getText()));
				        
				}
				break;
			case FLOAT:
				enterOuterAlt(_localctx, 2);
				{
				setState(444);
				((LiteralContext)_localctx).fd = match(FLOAT);
				 ((LiteralContext)_localctx).tree =  new FloatLiteral(Float.parseFloat(((LiteralContext)_localctx).fd.getText()));
				        
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 3);
				{
				setState(446);
				((LiteralContext)_localctx).STRING = match(STRING);
				 ((LiteralContext)_localctx).tree =  new StringLiteral(((LiteralContext)_localctx).STRING.getText());
				        
				}
				break;
			case TRUE:
				enterOuterAlt(_localctx, 4);
				{
				setState(448);
				match(TRUE);
				 ((LiteralContext)_localctx).tree =  new BooleanLiteral(true);
				        
				}
				break;
			case FALSE:
				enterOuterAlt(_localctx, 5);
				{
				setState(450);
				match(FALSE);
				 ((LiteralContext)_localctx).tree =  new BooleanLiteral(false);
				        
				}
				break;
			case THIS:
				enterOuterAlt(_localctx, 6);
				{
				setState(452);
				match(THIS);

				        
				}
				break;
			case NULL:
				enterOuterAlt(_localctx, 7);
				{
				setState(454);
				match(NULL);

				        
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class IdentContext extends ParserRuleContext {
		public AbstractIdentifier tree;
		public TerminalNode IDENT() { return getToken(DecaParser.IDENT, 0); }
		public IdentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ident; }
	}

	public final IdentContext ident() throws RecognitionException {
		IdentContext _localctx = new IdentContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_ident);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(458);
			match(IDENT);

			        
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

	public static class List_classesContext extends ParserRuleContext {
		public ListDeclClass tree;
		public Class_declContext c1;
		public List<Class_declContext> class_decl() {
			return getRuleContexts(Class_declContext.class);
		}
		public Class_declContext class_decl(int i) {
			return getRuleContext(Class_declContext.class,i);
		}
		public List_classesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_list_classes; }
	}

	public final List_classesContext list_classes() throws RecognitionException {
		List_classesContext _localctx = new List_classesContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_list_classes);

		        ((List_classesContext)_localctx).tree =  new ListDeclClass();
		    
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(466);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==CLASS) {
				{
				{
				setState(461);
				((List_classesContext)_localctx).c1 = class_decl();

				        
				}
				}
				setState(468);
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

	public static class Class_declContext extends ParserRuleContext {
		public IdentContext name;
		public Class_extensionContext superclass;
		public TerminalNode CLASS() { return getToken(DecaParser.CLASS, 0); }
		public TerminalNode OBRACE() { return getToken(DecaParser.OBRACE, 0); }
		public Class_bodyContext class_body() {
			return getRuleContext(Class_bodyContext.class,0);
		}
		public TerminalNode CBRACE() { return getToken(DecaParser.CBRACE, 0); }
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public Class_extensionContext class_extension() {
			return getRuleContext(Class_extensionContext.class,0);
		}
		public Class_declContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_class_decl; }
	}

	public final Class_declContext class_decl() throws RecognitionException {
		Class_declContext _localctx = new Class_declContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_class_decl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(469);
			match(CLASS);
			setState(470);
			((Class_declContext)_localctx).name = ident();
			setState(471);
			((Class_declContext)_localctx).superclass = class_extension();
			setState(472);
			match(OBRACE);
			setState(473);
			class_body();
			setState(474);
			match(CBRACE);

			        
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

	public static class Class_extensionContext extends ParserRuleContext {
		public AbstractIdentifier tree;
		public TerminalNode EXTENDS() { return getToken(DecaParser.EXTENDS, 0); }
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public Class_extensionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_class_extension; }
	}

	public final Class_extensionContext class_extension() throws RecognitionException {
		Class_extensionContext _localctx = new Class_extensionContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_class_extension);
		try {
			setState(482);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case EXTENDS:
				enterOuterAlt(_localctx, 1);
				{
				setState(477);
				match(EXTENDS);
				setState(478);
				ident();

				        
				}
				break;
			case OBRACE:
				enterOuterAlt(_localctx, 2);
				{

				        
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class Class_bodyContext extends ParserRuleContext {
		public Decl_methodContext m;
		public List<Decl_field_setContext> decl_field_set() {
			return getRuleContexts(Decl_field_setContext.class);
		}
		public Decl_field_setContext decl_field_set(int i) {
			return getRuleContext(Decl_field_setContext.class,i);
		}
		public List<Decl_methodContext> decl_method() {
			return getRuleContexts(Decl_methodContext.class);
		}
		public Decl_methodContext decl_method(int i) {
			return getRuleContext(Decl_methodContext.class,i);
		}
		public Class_bodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_class_body; }
	}

	public final Class_bodyContext class_body() throws RecognitionException {
		Class_bodyContext _localctx = new Class_bodyContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_class_body);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(490);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==PROTECTED || _la==IDENT) {
				{
				setState(488);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
				case 1:
					{
					setState(484);
					((Class_bodyContext)_localctx).m = decl_method();

					        
					}
					break;
				case 2:
					{
					setState(487);
					decl_field_set();
					}
					break;
				}
				}
				setState(492);
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

	public static class Decl_field_setContext extends ParserRuleContext {
		public VisibilityContext v;
		public TypeContext t;
		public List_decl_fieldContext list_decl_field() {
			return getRuleContext(List_decl_fieldContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(DecaParser.SEMI, 0); }
		public VisibilityContext visibility() {
			return getRuleContext(VisibilityContext.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public Decl_field_setContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decl_field_set; }
	}

	public final Decl_field_setContext decl_field_set() throws RecognitionException {
		Decl_field_setContext _localctx = new Decl_field_setContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_decl_field_set);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(493);
			((Decl_field_setContext)_localctx).v = visibility();
			setState(494);
			((Decl_field_setContext)_localctx).t = type();
			setState(495);
			list_decl_field();
			setState(496);
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

	public static class VisibilityContext extends ParserRuleContext {
		public TerminalNode PROTECTED() { return getToken(DecaParser.PROTECTED, 0); }
		public VisibilityContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_visibility; }
	}

	public final VisibilityContext visibility() throws RecognitionException {
		VisibilityContext _localctx = new VisibilityContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_visibility);
		try {
			setState(501);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENT:
				enterOuterAlt(_localctx, 1);
				{

				        
				}
				break;
			case PROTECTED:
				enterOuterAlt(_localctx, 2);
				{
				setState(499);
				match(PROTECTED);

				        
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class List_decl_fieldContext extends ParserRuleContext {
		public Decl_fieldContext dv1;
		public Decl_fieldContext dv2;
		public List<Decl_fieldContext> decl_field() {
			return getRuleContexts(Decl_fieldContext.class);
		}
		public Decl_fieldContext decl_field(int i) {
			return getRuleContext(Decl_fieldContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(DecaParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(DecaParser.COMMA, i);
		}
		public List_decl_fieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_list_decl_field; }
	}

	public final List_decl_fieldContext list_decl_field() throws RecognitionException {
		List_decl_fieldContext _localctx = new List_decl_fieldContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_list_decl_field);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(503);
			((List_decl_fieldContext)_localctx).dv1 = decl_field();
			setState(508);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(504);
				match(COMMA);
				setState(505);
				((List_decl_fieldContext)_localctx).dv2 = decl_field();
				}
				}
				setState(510);
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

	public static class Decl_fieldContext extends ParserRuleContext {
		public IdentContext i;
		public ExprContext e;
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public TerminalNode EQUALS() { return getToken(DecaParser.EQUALS, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Decl_fieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decl_field; }
	}

	public final Decl_fieldContext decl_field() throws RecognitionException {
		Decl_fieldContext _localctx = new Decl_fieldContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_decl_field);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(511);
			((Decl_fieldContext)_localctx).i = ident();

			        
			setState(517);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==EQUALS) {
				{
				setState(513);
				match(EQUALS);
				setState(514);
				((Decl_fieldContext)_localctx).e = expr();

				        
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

	public static class Decl_methodContext extends ParserRuleContext {
		public List_paramsContext params;
		public Multi_line_stringContext code;
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public List<TerminalNode> OPARENT() { return getTokens(DecaParser.OPARENT); }
		public TerminalNode OPARENT(int i) {
			return getToken(DecaParser.OPARENT, i);
		}
		public List<TerminalNode> CPARENT() { return getTokens(DecaParser.CPARENT); }
		public TerminalNode CPARENT(int i) {
			return getToken(DecaParser.CPARENT, i);
		}
		public List_paramsContext list_params() {
			return getRuleContext(List_paramsContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode ASM() { return getToken(DecaParser.ASM, 0); }
		public TerminalNode SEMI() { return getToken(DecaParser.SEMI, 0); }
		public Multi_line_stringContext multi_line_string() {
			return getRuleContext(Multi_line_stringContext.class,0);
		}
		public Decl_methodContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decl_method; }
	}

	public final Decl_methodContext decl_method() throws RecognitionException {
		Decl_methodContext _localctx = new Decl_methodContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_decl_method);


		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(521);
			type();
			setState(522);
			ident();
			setState(523);
			match(OPARENT);
			setState(524);
			((Decl_methodContext)_localctx).params = list_params();
			setState(525);
			match(CPARENT);
			setState(536);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OBRACE:
				{
				setState(526);
				block();

				        
				}
				break;
			case ASM:
				{
				setState(529);
				match(ASM);
				setState(530);
				match(OPARENT);
				setState(531);
				((Decl_methodContext)_localctx).code = multi_line_string();
				setState(532);
				match(CPARENT);
				setState(533);
				match(SEMI);

				        
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class List_paramsContext extends ParserRuleContext {
		public ParamContext p1;
		public ParamContext p2;
		public List<ParamContext> param() {
			return getRuleContexts(ParamContext.class);
		}
		public ParamContext param(int i) {
			return getRuleContext(ParamContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(DecaParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(DecaParser.COMMA, i);
		}
		public List_paramsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_list_params; }
	}

	public final List_paramsContext list_params() throws RecognitionException {
		List_paramsContext _localctx = new List_paramsContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_list_params);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(551);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IDENT) {
				{
				setState(540);
				((List_paramsContext)_localctx).p1 = param();

				        
				setState(548);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(542);
					match(COMMA);
					setState(543);
					((List_paramsContext)_localctx).p2 = param();

					        
					}
					}
					setState(550);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
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

	public static class Multi_line_stringContext extends ParserRuleContext {
		public String text;
		public Location location;
		public Token s;
		public TerminalNode STRING() { return getToken(DecaParser.STRING, 0); }
		public TerminalNode MULTI_LINE_STRING() { return getToken(DecaParser.MULTI_LINE_STRING, 0); }
		public Multi_line_stringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multi_line_string; }
	}

	public final Multi_line_stringContext multi_line_string() throws RecognitionException {
		Multi_line_stringContext _localctx = new Multi_line_stringContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_multi_line_string);
		try {
			setState(557);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STRING:
				enterOuterAlt(_localctx, 1);
				{
				setState(553);
				((Multi_line_stringContext)_localctx).s = match(STRING);

				            ((Multi_line_stringContext)_localctx).text =  (((Multi_line_stringContext)_localctx).s!=null?((Multi_line_stringContext)_localctx).s.getText():null);
				            ((Multi_line_stringContext)_localctx).location =  tokenLocation(((Multi_line_stringContext)_localctx).s);
				        
				}
				break;
			case MULTI_LINE_STRING:
				enterOuterAlt(_localctx, 2);
				{
				setState(555);
				((Multi_line_stringContext)_localctx).s = match(MULTI_LINE_STRING);

				            ((Multi_line_stringContext)_localctx).text =  (((Multi_line_stringContext)_localctx).s!=null?((Multi_line_stringContext)_localctx).s.getText():null);
				            ((Multi_line_stringContext)_localctx).location =  tokenLocation(((Multi_line_stringContext)_localctx).s);
				        
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class ParamContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public ParamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_param; }
	}

	public final ParamContext param() throws RecognitionException {
		ParamContext _localctx = new ParamContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_param);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(559);
			type();
			setState(560);
			ident();

			        
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
		case 13:
			return or_expr_sempred((Or_exprContext)_localctx, predIndex);
		case 14:
			return and_expr_sempred((And_exprContext)_localctx, predIndex);
		case 15:
			return eq_neq_expr_sempred((Eq_neq_exprContext)_localctx, predIndex);
		case 16:
			return inequality_expr_sempred((Inequality_exprContext)_localctx, predIndex);
		case 17:
			return sum_expr_sempred((Sum_exprContext)_localctx, predIndex);
		case 18:
			return mult_expr_sempred((Mult_exprContext)_localctx, predIndex);
		case 20:
			return select_expr_sempred((Select_exprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean or_expr_sempred(Or_exprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean and_expr_sempred(And_exprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean eq_neq_expr_sempred(Eq_neq_exprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return precpred(_ctx, 2);
		case 3:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean inequality_expr_sempred(Inequality_exprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 4:
			return precpred(_ctx, 5);
		case 5:
			return precpred(_ctx, 4);
		case 6:
			return precpred(_ctx, 3);
		case 7:
			return precpred(_ctx, 2);
		case 8:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean sum_expr_sempred(Sum_exprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 9:
			return precpred(_ctx, 2);
		case 10:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean mult_expr_sempred(Mult_exprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 11:
			return precpred(_ctx, 3);
		case 12:
			return precpred(_ctx, 2);
		case 13:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean select_expr_sempred(Select_exprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 14:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\63\u0236\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3"+
		"\3\5\3V\n\3\3\4\3\4\3\4\3\4\3\4\3\4\3\5\7\5_\n\5\f\5\16\5b\13\5\3\6\3"+
		"\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\7\7o\n\7\f\7\16\7r\13\7\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\5\bz\n\b\3\b\3\b\3\t\3\t\3\t\7\t\u0081\n\t\f\t\16\t\u0084"+
		"\13\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\5\n\u00b9\n\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\7\13\u00cd\n\13\f\13\16\13\u00d0\13"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\13\5\13\u00d8\n\13\3\f\3\f\3\f\3\f\3\f"+
		"\3\f\7\f\u00e0\n\f\f\f\16\f\u00e3\13\f\5\f\u00e5\n\f\3\r\3\r\3\r\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\5\16\u00f1\n\16\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\7\17\u00fc\n\17\f\17\16\17\u00ff\13\17\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\7\20\u010a\n\20\f\20\16\20\u010d\13"+
		"\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3"+
		"\21\7\21\u011d\n\21\f\21\16\21\u0120\13\21\3\22\3\22\3\22\3\22\3\22\3"+
		"\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3"+
		"\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\7\22\u013f\n\22\f\22"+
		"\16\22\u0142\13\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3"+
		"\23\3\23\3\23\3\23\7\23\u0152\n\23\f\23\16\23\u0155\13\23\3\24\3\24\3"+
		"\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3"+
		"\24\3\24\3\24\7\24\u016a\n\24\f\24\16\24\u016d\13\24\3\25\3\25\3\25\3"+
		"\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\5\25\u017a\n\25\3\26\3\26\3\26"+
		"\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\5\26\u018a\n\26"+
		"\7\26\u018c\n\26\f\26\16\26\u018f\13\26\3\27\3\27\3\27\3\27\3\27\3\27"+
		"\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27"+
		"\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27"+
		"\3\27\3\27\3\27\3\27\3\27\5\27\u01b8\n\27\3\30\3\30\3\30\3\31\3\31\3\31"+
		"\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\5\31\u01cb\n\31"+
		"\3\32\3\32\3\32\3\33\3\33\3\33\7\33\u01d3\n\33\f\33\16\33\u01d6\13\33"+
		"\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35\5\35"+
		"\u01e5\n\35\3\36\3\36\3\36\3\36\7\36\u01eb\n\36\f\36\16\36\u01ee\13\36"+
		"\3\37\3\37\3\37\3\37\3\37\3 \3 \3 \5 \u01f8\n \3!\3!\3!\7!\u01fd\n!\f"+
		"!\16!\u0200\13!\3\"\3\"\3\"\3\"\3\"\3\"\5\"\u0208\n\"\3\"\3\"\3#\3#\3"+
		"#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\5#\u021b\n#\3#\3#\3$\3$\3$\3$\3"+
		"$\3$\7$\u0225\n$\f$\16$\u0228\13$\5$\u022a\n$\3%\3%\3%\3%\5%\u0230\n%"+
		"\3&\3&\3&\3&\3&\2\t\34\36 \"$&*\'\2\4\6\b\n\f\16\20\22\24\26\30\32\34"+
		"\36 \"$&(*,.\60\62\64\668:<>@BDFHJ\2\2\2\u024c\2L\3\2\2\2\4U\3\2\2\2\6"+
		"W\3\2\2\2\b`\3\2\2\2\nc\3\2\2\2\fh\3\2\2\2\16s\3\2\2\2\20\u0082\3\2\2"+
		"\2\22\u00b8\3\2\2\2\24\u00ba\3\2\2\2\26\u00e4\3\2\2\2\30\u00e6\3\2\2\2"+
		"\32\u00e9\3\2\2\2\34\u00f2\3\2\2\2\36\u0100\3\2\2\2 \u010e\3\2\2\2\"\u0121"+
		"\3\2\2\2$\u0143\3\2\2\2&\u0156\3\2\2\2(\u0179\3\2\2\2*\u017b\3\2\2\2,"+
		"\u01b7\3\2\2\2.\u01b9\3\2\2\2\60\u01ca\3\2\2\2\62\u01cc\3\2\2\2\64\u01d4"+
		"\3\2\2\2\66\u01d7\3\2\2\28\u01e4\3\2\2\2:\u01ec\3\2\2\2<\u01ef\3\2\2\2"+
		">\u01f7\3\2\2\2@\u01f9\3\2\2\2B\u0201\3\2\2\2D\u020b\3\2\2\2F\u0229\3"+
		"\2\2\2H\u022f\3\2\2\2J\u0231\3\2\2\2LM\5\64\33\2MN\5\4\3\2NO\7\2\2\3O"+
		"P\b\2\1\2P\3\3\2\2\2QV\b\3\1\2RS\5\6\4\2ST\b\3\1\2TV\3\2\2\2UQ\3\2\2\2"+
		"UR\3\2\2\2V\5\3\2\2\2WX\7$\2\2XY\5\b\5\2YZ\5\20\t\2Z[\7%\2\2[\\\b\4\1"+
		"\2\\\7\3\2\2\2]_\5\n\6\2^]\3\2\2\2_b\3\2\2\2`^\3\2\2\2`a\3\2\2\2a\t\3"+
		"\2\2\2b`\3\2\2\2cd\5.\30\2de\5\f\7\2ef\7\'\2\2fg\b\6\1\2g\13\3\2\2\2h"+
		"i\5\16\b\2ip\b\7\1\2jk\7!\2\2kl\5\16\b\2lm\b\7\1\2mo\3\2\2\2nj\3\2\2\2"+
		"or\3\2\2\2pn\3\2\2\2pq\3\2\2\2q\r\3\2\2\2rp\3\2\2\2st\5\62\32\2ty\b\b"+
		"\1\2uv\7\32\2\2vw\5\30\r\2wx\b\b\1\2xz\3\2\2\2yu\3\2\2\2yz\3\2\2\2z{\3"+
		"\2\2\2{|\b\b\1\2|\17\3\2\2\2}~\5\22\n\2~\177\b\t\1\2\177\u0081\3\2\2\2"+
		"\u0080}\3\2\2\2\u0081\u0084\3\2\2\2\u0082\u0080\3\2\2\2\u0082\u0083\3"+
		"\2\2\2\u0083\21\3\2\2\2\u0084\u0082\3\2\2\2\u0085\u0086\5\30\r\2\u0086"+
		"\u0087\7\'\2\2\u0087\u0088\b\n\1\2\u0088\u00b9\3\2\2\2\u0089\u008a\7\'"+
		"\2\2\u008a\u00b9\b\n\1\2\u008b\u008c\7\16\2\2\u008c\u008d\7\"\2\2\u008d"+
		"\u008e\5\26\f\2\u008e\u008f\7#\2\2\u008f\u0090\7\'\2\2\u0090\u0091\b\n"+
		"\1\2\u0091\u00b9\3\2\2\2\u0092\u0093\7\17\2\2\u0093\u0094\7\"\2\2\u0094"+
		"\u0095\5\26\f\2\u0095\u0096\7#\2\2\u0096\u0097\7\'\2\2\u0097\u0098\b\n"+
		"\1\2\u0098\u00b9\3\2\2\2\u0099\u009a\7\20\2\2\u009a\u009b\7\"\2\2\u009b"+
		"\u009c\5\26\f\2\u009c\u009d\7#\2\2\u009d\u009e\7\'\2\2\u009e\u009f\b\n"+
		"\1\2\u009f\u00b9\3\2\2\2\u00a0\u00a1\7\21\2\2\u00a1\u00a2\7\"\2\2\u00a2"+
		"\u00a3\5\26\f\2\u00a3\u00a4\7#\2\2\u00a4\u00a5\7\'\2\2\u00a5\u00a6\b\n"+
		"\1\2\u00a6\u00b9\3\2\2\2\u00a7\u00a8\5\24\13\2\u00a8\u00a9\b\n\1\2\u00a9"+
		"\u00b9\3\2\2\2\u00aa\u00ab\7\26\2\2\u00ab\u00ac\7\"\2\2\u00ac\u00ad\5"+
		"\30\r\2\u00ad\u00ae\7#\2\2\u00ae\u00af\7$\2\2\u00af\u00b0\5\20\t\2\u00b0"+
		"\u00b1\7%\2\2\u00b1\u00b2\b\n\1\2\u00b2\u00b9\3\2\2\2\u00b3\u00b4\7\23"+
		"\2\2\u00b4\u00b5\5\30\r\2\u00b5\u00b6\7\'\2\2\u00b6\u00b7\b\n\1\2\u00b7"+
		"\u00b9\3\2\2\2\u00b8\u0085\3\2\2\2\u00b8\u0089\3\2\2\2\u00b8\u008b\3\2"+
		"\2\2\u00b8\u0092\3\2\2\2\u00b8\u0099\3\2\2\2\u00b8\u00a0\3\2\2\2\u00b8"+
		"\u00a7\3\2\2\2\u00b8\u00aa\3\2\2\2\u00b8\u00b3\3\2\2\2\u00b9\23\3\2\2"+
		"\2\u00ba\u00bb\7\b\2\2\u00bb\u00bc\7\"\2\2\u00bc\u00bd\5\30\r\2\u00bd"+
		"\u00be\7#\2\2\u00be\u00bf\7$\2\2\u00bf\u00c0\5\20\t\2\u00c0\u00c1\7%\2"+
		"\2\u00c1\u00ce\b\13\1\2\u00c2\u00c3\7\6\2\2\u00c3\u00c4\7\b\2\2\u00c4"+
		"\u00c5\7\"\2\2\u00c5\u00c6\5\30\r\2\u00c6\u00c7\7#\2\2\u00c7\u00c8\7$"+
		"\2\2\u00c8\u00c9\5\20\t\2\u00c9\u00ca\7%\2\2\u00ca\u00cb\b\13\1\2\u00cb"+
		"\u00cd\3\2\2\2\u00cc\u00c2\3\2\2\2\u00cd\u00d0\3\2\2\2\u00ce\u00cc\3\2"+
		"\2\2\u00ce\u00cf\3\2\2\2\u00cf\u00d7\3\2\2\2\u00d0\u00ce\3\2\2\2\u00d1"+
		"\u00d2\7\6\2\2\u00d2\u00d3\7$\2\2\u00d3\u00d4\5\20\t\2\u00d4\u00d5\7%"+
		"\2\2\u00d5\u00d6\b\13\1\2\u00d6\u00d8\3\2\2\2\u00d7\u00d1\3\2\2\2\u00d7"+
		"\u00d8\3\2\2\2\u00d8\25\3\2\2\2\u00d9\u00da\5\30\r\2\u00da\u00e1\b\f\1"+
		"\2\u00db\u00dc\7!\2\2\u00dc\u00dd\5\30\r\2\u00dd\u00de\b\f\1\2\u00de\u00e0"+
		"\3\2\2\2\u00df\u00db\3\2\2\2\u00e0\u00e3\3\2\2\2\u00e1\u00df\3\2\2\2\u00e1"+
		"\u00e2\3\2\2\2\u00e2\u00e5\3\2\2\2\u00e3\u00e1\3\2\2\2\u00e4\u00d9\3\2"+
		"\2\2\u00e4\u00e5\3\2\2\2\u00e5\27\3\2\2\2\u00e6\u00e7\5\32\16\2\u00e7"+
		"\u00e8\b\r\1\2\u00e8\31\3\2\2\2\u00e9\u00f0\5\34\17\2\u00ea\u00eb\b\16"+
		"\1\2\u00eb\u00ec\7\32\2\2\u00ec\u00ed\5\32\16\2\u00ed\u00ee\b\16\1\2\u00ee"+
		"\u00f1\3\2\2\2\u00ef\u00f1\b\16\1\2\u00f0\u00ea\3\2\2\2\u00f0\u00ef\3"+
		"\2\2\2\u00f1\33\3\2\2\2\u00f2\u00f3\b\17\1\2\u00f3\u00f4\5\36\20\2\u00f4"+
		"\u00f5\b\17\1\2\u00f5\u00fd\3\2\2\2\u00f6\u00f7\f\3\2\2\u00f7\u00f8\7"+
		"-\2\2\u00f8\u00f9\5\36\20\2\u00f9\u00fa\b\17\1\2\u00fa\u00fc\3\2\2\2\u00fb"+
		"\u00f6\3\2\2\2\u00fc\u00ff\3\2\2\2\u00fd\u00fb\3\2\2\2\u00fd\u00fe\3\2"+
		"\2\2\u00fe\35\3\2\2\2\u00ff\u00fd\3\2\2\2\u0100\u0101\b\20\1\2\u0101\u0102"+
		"\5 \21\2\u0102\u0103\b\20\1\2\u0103\u010b\3\2\2\2\u0104\u0105\f\3\2\2"+
		"\u0105\u0106\7,\2\2\u0106\u0107\5 \21\2\u0107\u0108\b\20\1\2\u0108\u010a"+
		"\3\2\2\2\u0109\u0104\3\2\2\2\u010a\u010d\3\2\2\2\u010b\u0109\3\2\2\2\u010b"+
		"\u010c\3\2\2\2\u010c\37\3\2\2\2\u010d\u010b\3\2\2\2\u010e\u010f\b\21\1"+
		"\2\u010f\u0110\5\"\22\2\u0110\u0111\b\21\1\2\u0111\u011e\3\2\2\2\u0112"+
		"\u0113\f\4\2\2\u0113\u0114\7(\2\2\u0114\u0115\5\"\22\2\u0115\u0116\b\21"+
		"\1\2\u0116\u011d\3\2\2\2\u0117\u0118\f\3\2\2\u0118\u0119\7)\2\2\u0119"+
		"\u011a\5\"\22\2\u011a\u011b\b\21\1\2\u011b\u011d\3\2\2\2\u011c\u0112\3"+
		"\2\2\2\u011c\u0117\3\2\2\2\u011d\u0120\3\2\2\2\u011e\u011c\3\2\2\2\u011e"+
		"\u011f\3\2\2\2\u011f!\3\2\2\2\u0120\u011e\3\2\2\2\u0121\u0122\b\22\1\2"+
		"\u0122\u0123\5$\23\2\u0123\u0124\b\22\1\2\u0124\u0140\3\2\2\2\u0125\u0126"+
		"\f\7\2\2\u0126\u0127\7+\2\2\u0127\u0128\5$\23\2\u0128\u0129\b\22\1\2\u0129"+
		"\u013f\3\2\2\2\u012a\u012b\f\6\2\2\u012b\u012c\7*\2\2\u012c\u012d\5$\23"+
		"\2\u012d\u012e\b\22\1\2\u012e\u013f\3\2\2\2\u012f\u0130\f\5\2\2\u0130"+
		"\u0131\7\31\2\2\u0131\u0132\5$\23\2\u0132\u0133\b\22\1\2\u0133\u013f\3"+
		"\2\2\2\u0134\u0135\f\4\2\2\u0135\u0136\7\30\2\2\u0136\u0137\5$\23\2\u0137"+
		"\u0138\b\22\1\2\u0138\u013f\3\2\2\2\u0139\u013a\f\3\2\2\u013a\u013b\7"+
		"\t\2\2\u013b\u013c\5.\30\2\u013c\u013d\b\22\1\2\u013d\u013f\3\2\2\2\u013e"+
		"\u0125\3\2\2\2\u013e\u012a\3\2\2\2\u013e\u012f\3\2\2\2\u013e\u0134\3\2"+
		"\2\2\u013e\u0139\3\2\2\2\u013f\u0142\3\2\2\2\u0140\u013e\3\2\2\2\u0140"+
		"\u0141\3\2\2\2\u0141#\3\2\2\2\u0142\u0140\3\2\2\2\u0143\u0144\b\23\1\2"+
		"\u0144\u0145\5&\24\2\u0145\u0146\b\23\1\2\u0146\u0153\3\2\2\2\u0147\u0148"+
		"\f\4\2\2\u0148\u0149\7\33\2\2\u0149\u014a\5&\24\2\u014a\u014b\b\23\1\2"+
		"\u014b\u0152\3\2\2\2\u014c\u014d\f\3\2\2\u014d\u014e\7\34\2\2\u014e\u014f"+
		"\5&\24\2\u014f\u0150\b\23\1\2\u0150\u0152\3\2\2\2\u0151\u0147\3\2\2\2"+
		"\u0151\u014c\3\2\2\2\u0152\u0155\3\2\2\2\u0153\u0151\3\2\2\2\u0153\u0154"+
		"\3\2\2\2\u0154%\3\2\2\2\u0155\u0153\3\2\2\2\u0156\u0157\b\24\1\2\u0157"+
		"\u0158\5(\25\2\u0158\u0159\b\24\1\2\u0159\u016b\3\2\2\2\u015a\u015b\f"+
		"\5\2\2\u015b\u015c\7\35\2\2\u015c\u015d\5(\25\2\u015d\u015e\b\24\1\2\u015e"+
		"\u016a\3\2\2\2\u015f\u0160\f\4\2\2\u0160\u0161\7\36\2\2\u0161\u0162\5"+
		"(\25\2\u0162\u0163\b\24\1\2\u0163\u016a\3\2\2\2\u0164\u0165\f\3\2\2\u0165"+
		"\u0166\7\37\2\2\u0166\u0167\5(\25\2\u0167\u0168\b\24\1\2\u0168\u016a\3"+
		"\2\2\2\u0169\u015a\3\2\2\2\u0169\u015f\3\2\2\2\u0169\u0164\3\2\2\2\u016a"+
		"\u016d\3\2\2\2\u016b\u0169\3\2\2\2\u016b\u016c\3\2\2\2\u016c\'\3\2\2\2"+
		"\u016d\u016b\3\2\2\2\u016e\u016f\7\34\2\2\u016f\u0170\5(\25\2\u0170\u0171"+
		"\b\25\1\2\u0171\u017a\3\2\2\2\u0172\u0173\7&\2\2\u0173\u0174\5(\25\2\u0174"+
		"\u0175\b\25\1\2\u0175\u017a\3\2\2\2\u0176\u0177\5*\26\2\u0177\u0178\b"+
		"\25\1\2\u0178\u017a\3\2\2\2\u0179\u016e\3\2\2\2\u0179\u0172\3\2\2\2\u0179"+
		"\u0176\3\2\2\2\u017a)\3\2\2\2\u017b\u017c\b\26\1\2\u017c\u017d\5,\27\2"+
		"\u017d\u017e\b\26\1\2\u017e\u018d\3\2\2\2\u017f\u0180\f\3\2\2\u0180\u0181"+
		"\7 \2\2\u0181\u0182\5\62\32\2\u0182\u0189\b\26\1\2\u0183\u0184\7\"\2\2"+
		"\u0184\u0185\5\26\f\2\u0185\u0186\7#\2\2\u0186\u0187\b\26\1\2\u0187\u018a"+
		"\3\2\2\2\u0188\u018a\b\26\1\2\u0189\u0183\3\2\2\2\u0189\u0188\3\2\2\2"+
		"\u018a\u018c\3\2\2\2\u018b\u017f\3\2\2\2\u018c\u018f\3\2\2\2\u018d\u018b"+
		"\3\2\2\2\u018d\u018e\3\2\2\2\u018e+\3\2\2\2\u018f\u018d\3\2\2\2\u0190"+
		"\u0191\5\62\32\2\u0191\u0192\b\27\1\2\u0192\u01b8\3\2\2\2\u0193\u0194"+
		"\5\62\32\2\u0194\u0195\7\"\2\2\u0195\u0196\5\26\f\2\u0196\u0197\7#\2\2"+
		"\u0197\u0198\b\27\1\2\u0198\u01b8\3\2\2\2\u0199\u019a\7\"\2\2\u019a\u019b"+
		"\5\30\r\2\u019b\u019c\7#\2\2\u019c\u019d\b\27\1\2\u019d\u01b8\3\2\2\2"+
		"\u019e\u019f\7\f\2\2\u019f\u01a0\7\"\2\2\u01a0\u01a1\7#\2\2\u01a1\u01b8"+
		"\b\27\1\2\u01a2\u01a3\7\r\2\2\u01a3\u01a4\7\"\2\2\u01a4\u01a5\7#\2\2\u01a5"+
		"\u01b8\b\27\1\2\u01a6\u01a7\7\n\2\2\u01a7\u01a8\5\62\32\2\u01a8\u01a9"+
		"\7\"\2\2\u01a9\u01aa\7#\2\2\u01aa\u01ab\b\27\1\2\u01ab\u01b8\3\2\2\2\u01ac"+
		"\u01ad\7\"\2\2\u01ad\u01ae\5.\30\2\u01ae\u01af\7#\2\2\u01af\u01b0\7\""+
		"\2\2\u01b0\u01b1\5\30\r\2\u01b1\u01b2\7#\2\2\u01b2\u01b3\b\27\1\2\u01b3"+
		"\u01b8\3\2\2\2\u01b4\u01b5\5\60\31\2\u01b5\u01b6\b\27\1\2\u01b6\u01b8"+
		"\3\2\2\2\u01b7\u0190\3\2\2\2\u01b7\u0193\3\2\2\2\u01b7\u0199\3\2\2\2\u01b7"+
		"\u019e\3\2\2\2\u01b7\u01a2\3\2\2\2\u01b7\u01a6\3\2\2\2\u01b7\u01ac\3\2"+
		"\2\2\u01b7\u01b4\3\2\2\2\u01b8-\3\2\2\2\u01b9\u01ba\5\62\32\2\u01ba\u01bb"+
		"\b\30\1\2\u01bb/\3\2\2\2\u01bc\u01bd\7.\2\2\u01bd\u01cb\b\31\1\2\u01be"+
		"\u01bf\7/\2\2\u01bf\u01cb\b\31\1\2\u01c0\u01c1\7\60\2\2\u01c1\u01cb\b"+
		"\31\1\2\u01c2\u01c3\7\25\2\2\u01c3\u01cb\b\31\1\2\u01c4\u01c5\7\7\2\2"+
		"\u01c5\u01cb\b\31\1\2\u01c6\u01c7\7\24\2\2\u01c7\u01cb\b\31\1\2\u01c8"+
		"\u01c9\7\13\2\2\u01c9\u01cb\b\31\1\2\u01ca\u01bc\3\2\2\2\u01ca\u01be\3"+
		"\2\2\2\u01ca\u01c0\3\2\2\2\u01ca\u01c2\3\2\2\2\u01ca\u01c4\3\2\2\2\u01ca"+
		"\u01c6\3\2\2\2\u01ca\u01c8\3\2\2\2\u01cb\61\3\2\2\2\u01cc\u01cd\7\27\2"+
		"\2\u01cd\u01ce\b\32\1\2\u01ce\63\3\2\2\2\u01cf\u01d0\5\66\34\2\u01d0\u01d1"+
		"\b\33\1\2\u01d1\u01d3\3\2\2\2\u01d2\u01cf\3\2\2\2\u01d3\u01d6\3\2\2\2"+
		"\u01d4\u01d2\3\2\2\2\u01d4\u01d5\3\2\2\2\u01d5\65\3\2\2\2\u01d6\u01d4"+
		"\3\2\2\2\u01d7\u01d8\7\4\2\2\u01d8\u01d9\5\62\32\2\u01d9\u01da\58\35\2"+
		"\u01da\u01db\7$\2\2\u01db\u01dc\5:\36\2\u01dc\u01dd\7%\2\2\u01dd\u01de"+
		"\b\34\1\2\u01de\67\3\2\2\2\u01df\u01e0\7\5\2\2\u01e0\u01e1\5\62\32\2\u01e1"+
		"\u01e2\b\35\1\2\u01e2\u01e5\3\2\2\2\u01e3\u01e5\b\35\1\2\u01e4\u01df\3"+
		"\2\2\2\u01e4\u01e3\3\2\2\2\u01e59\3\2\2\2\u01e6\u01e7\5D#\2\u01e7\u01e8"+
		"\b\36\1\2\u01e8\u01eb\3\2\2\2\u01e9\u01eb\5<\37\2\u01ea\u01e6\3\2\2\2"+
		"\u01ea\u01e9\3\2\2\2\u01eb\u01ee\3\2\2\2\u01ec\u01ea\3\2\2\2\u01ec\u01ed"+
		"\3\2\2\2\u01ed;\3\2\2\2\u01ee\u01ec\3\2\2\2\u01ef\u01f0\5> \2\u01f0\u01f1"+
		"\5.\30\2\u01f1\u01f2\5@!\2\u01f2\u01f3\7\'\2\2\u01f3=\3\2\2\2\u01f4\u01f8"+
		"\b \1\2\u01f5\u01f6\7\22\2\2\u01f6\u01f8\b \1\2\u01f7\u01f4\3\2\2\2\u01f7"+
		"\u01f5\3\2\2\2\u01f8?\3\2\2\2\u01f9\u01fe\5B\"\2\u01fa\u01fb\7!\2\2\u01fb"+
		"\u01fd\5B\"\2\u01fc\u01fa\3\2\2\2\u01fd\u0200\3\2\2\2\u01fe\u01fc\3\2"+
		"\2\2\u01fe\u01ff\3\2\2\2\u01ffA\3\2\2\2\u0200\u01fe\3\2\2\2\u0201\u0202"+
		"\5\62\32\2\u0202\u0207\b\"\1\2\u0203\u0204\7\32\2\2\u0204\u0205\5\30\r"+
		"\2\u0205\u0206\b\"\1\2\u0206\u0208\3\2\2\2\u0207\u0203\3\2\2\2\u0207\u0208"+
		"\3\2\2\2\u0208\u0209\3\2\2\2\u0209\u020a\b\"\1\2\u020aC\3\2\2\2\u020b"+
		"\u020c\5.\30\2\u020c\u020d\5\62\32\2\u020d\u020e\7\"\2\2\u020e\u020f\5"+
		"F$\2\u020f\u021a\7#\2\2\u0210\u0211\5\6\4\2\u0211\u0212\b#\1\2\u0212\u021b"+
		"\3\2\2\2\u0213\u0214\7\3\2\2\u0214\u0215\7\"\2\2\u0215\u0216\5H%\2\u0216"+
		"\u0217\7#\2\2\u0217\u0218\7\'\2\2\u0218\u0219\b#\1\2\u0219\u021b\3\2\2"+
		"\2\u021a\u0210\3\2\2\2\u021a\u0213\3\2\2\2\u021b\u021c\3\2\2\2\u021c\u021d"+
		"\b#\1\2\u021dE\3\2\2\2\u021e\u021f\5J&\2\u021f\u0226\b$\1\2\u0220\u0221"+
		"\7!\2\2\u0221\u0222\5J&\2\u0222\u0223\b$\1\2\u0223\u0225\3\2\2\2\u0224"+
		"\u0220\3\2\2\2\u0225\u0228\3\2\2\2\u0226\u0224\3\2\2\2\u0226\u0227\3\2"+
		"\2\2\u0227\u022a\3\2\2\2\u0228\u0226\3\2\2\2\u0229\u021e\3\2\2\2\u0229"+
		"\u022a\3\2\2\2\u022aG\3\2\2\2\u022b\u022c\7\60\2\2\u022c\u0230\b%\1\2"+
		"\u022d\u022e\7\61\2\2\u022e\u0230\b%\1\2\u022f\u022b\3\2\2\2\u022f\u022d"+
		"\3\2\2\2\u0230I\3\2\2\2\u0231\u0232\5.\30\2\u0232\u0233\5\62\32\2\u0233"+
		"\u0234\b&\1\2\u0234K\3\2\2\2\'U`py\u0082\u00b8\u00ce\u00d7\u00e1\u00e4"+
		"\u00f0\u00fd\u010b\u011c\u011e\u013e\u0140\u0151\u0153\u0169\u016b\u0179"+
		"\u0189\u018d\u01b7\u01ca\u01d4\u01e4\u01ea\u01ec\u01f7\u01fe\u0207\u021a"+
		"\u0226\u0229\u022f";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}