// Generated from /user/9/lavalj/Documents/gl47/src/main/antlr4/fr/ensimag/deca/syntax/DecaLexer.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class DecaLexer extends AbstractDecaLexer {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

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
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"ASM", "CLASS", "EXTENDS", "ELSE", "FALSE", "IF", "INSTANCEOF", "NEW", 
			"NULL", "READINT", "READFLOAT", "PRINT", "PRINTLN", "PRINTX", "PRINTLNX", 
			"PROTECTED", "RETURN", "THIS", "TRUE", "WHILE", "LETTER", "NON_ZERO_DIGIT", 
			"DIGIT", "IDENT_CHAR", "IDENT", "LT", "GT", "EQUALS", "PLUS", "MINUS", 
			"TIMES", "SLASH", "PERCENT", "DOT", "COMMA", "OPARENT", "CPARENT", "OBRACE", 
			"CBRACE", "EXCLAM", "SEMI", "EQEQ", "NEQ", "GEQ", "LEQ", "AND", "OR", 
			"INT", "NUM", "SIGN", "EXP", "DEC", "FLOAT_DEC", "DIGIT_HEX", "NUM_HEX", 
			"FLOAT_HEX", "FLOAT", "EOL", "STRING_CHAR", "STRING", "MULTI_LINE_STRING", 
			"SINGLE_LINE_COMMENT", "MULTI_LINE_COMMENT", "COMMENT", "SPACE", "TAB", 
			"NL", "CR", "WS", "FILENAME", "INCLUDE"
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




	public DecaLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "DecaLexer.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 68:
			WS_action((RuleContext)_localctx, actionIndex);
			break;
		case 70:
			INCLUDE_action((RuleContext)_localctx, actionIndex);
			break;
		}
	}
	private void WS_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0:
			 skip(); 
			break;
		}
	}
	private void INCLUDE_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1:
			 doInclude(getText()); 
			break;
		}
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\63\u0215\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64"+
		"\t\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t"+
		"=\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\3"+
		"\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22"+
		"\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24"+
		"\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\5\30"+
		"\u011d\n\30\3\31\3\31\5\31\u0121\n\31\3\32\3\32\3\32\7\32\u0126\n\32\f"+
		"\32\16\32\u0129\13\32\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36\3\37\3\37"+
		"\3 \3 \3!\3!\3\"\3\"\3#\3#\3$\3$\3%\3%\3&\3&\3\'\3\'\3(\3(\3)\3)\3*\3"+
		"*\3+\3+\3+\3,\3,\3,\3-\3-\3-\3.\3.\3.\3/\3/\3/\3\60\3\60\3\60\3\61\3\61"+
		"\3\61\7\61\u0160\n\61\f\61\16\61\u0163\13\61\5\61\u0165\n\61\3\62\6\62"+
		"\u0168\n\62\r\62\16\62\u0169\3\63\3\63\3\64\3\64\5\64\u0170\n\64\3\64"+
		"\3\64\3\65\3\65\3\65\3\65\3\66\3\66\5\66\u017a\n\66\3\66\6\66\u017d\n"+
		"\66\r\66\16\66\u017e\3\66\5\66\u0182\n\66\3\67\3\67\5\67\u0186\n\67\3"+
		"8\68\u0189\n8\r8\168\u018a\39\39\39\39\59\u0191\n9\39\39\39\39\39\39\3"+
		"9\69\u019a\n9\r9\169\u019b\39\59\u019f\n9\3:\3:\5:\u01a3\n:\3;\3;\3<\3"+
		"<\3=\3=\3=\3=\3=\3=\7=\u01af\n=\f=\16=\u01b2\13=\3=\3=\3>\3>\3>\3>\3>"+
		"\3>\3>\7>\u01bd\n>\f>\16>\u01c0\13>\3>\3>\3?\3?\3?\3?\7?\u01c8\n?\f?\16"+
		"?\u01cb\13?\3@\3@\3@\3@\7@\u01d1\n@\f@\16@\u01d4\13@\3@\3@\3@\3A\3A\5"+
		"A\u01db\nA\3B\6B\u01de\nB\rB\16B\u01df\3C\6C\u01e3\nC\rC\16C\u01e4\3D"+
		"\6D\u01e8\nD\rD\16D\u01e9\3E\6E\u01ed\nE\rE\16E\u01ee\3F\3F\3F\3F\3F\6"+
		"F\u01f6\nF\rF\16F\u01f7\3F\3F\3G\3G\3G\6G\u01ff\nG\rG\16G\u0200\3H\3H"+
		"\3H\3H\3H\3H\3H\3H\3H\3H\6H\u020d\nH\rH\16H\u020e\3H\3H\3H\3H\3H\3\u01d2"+
		"\2I\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35"+
		"\20\37\21!\22#\23%\24\'\25)\26+\2-\2/\2\61\2\63\27\65\30\67\319\32;\33"+
		"=\34?\35A\36C\37E G!I\"K#M$O%Q&S\'U(W)Y*[+],_-a.c\2e\2g\2i\2k\2m\2o\2"+
		"q\2s/u\2w\2y\60{\61}\2\177\2\u0081\2\u0083\2\u0085\2\u0087\2\u0089\2\u008b"+
		"\62\u008d\2\u008f\63\3\2\n\4\2C\\c|\4\2&&aa\4\2--//\4\2GGgg\4\2RRrr\5"+
		"\2\13\f$$^^\4\2\f\f\17\17\4\2/\60aa\2\u0226\2\3\3\2\2\2\2\5\3\2\2\2\2"+
		"\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2"+
		"\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2"+
		"\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2"+
		"\2\2)\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2"+
		"\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2"+
		"\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U"+
		"\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2"+
		"\2\2\2s\3\2\2\2\2y\3\2\2\2\2{\3\2\2\2\2\u008b\3\2\2\2\2\u008f\3\2\2\2"+
		"\3\u0091\3\2\2\2\5\u0095\3\2\2\2\7\u009b\3\2\2\2\t\u00a3\3\2\2\2\13\u00a8"+
		"\3\2\2\2\r\u00ae\3\2\2\2\17\u00b1\3\2\2\2\21\u00bc\3\2\2\2\23\u00c0\3"+
		"\2\2\2\25\u00c5\3\2\2\2\27\u00cd\3\2\2\2\31\u00d7\3\2\2\2\33\u00dd\3\2"+
		"\2\2\35\u00e5\3\2\2\2\37\u00ec\3\2\2\2!\u00f5\3\2\2\2#\u00ff\3\2\2\2%"+
		"\u0106\3\2\2\2\'\u010b\3\2\2\2)\u0110\3\2\2\2+\u0116\3\2\2\2-\u0118\3"+
		"\2\2\2/\u011c\3\2\2\2\61\u0120\3\2\2\2\63\u0122\3\2\2\2\65\u012a\3\2\2"+
		"\2\67\u012c\3\2\2\29\u012e\3\2\2\2;\u0130\3\2\2\2=\u0132\3\2\2\2?\u0134"+
		"\3\2\2\2A\u0136\3\2\2\2C\u0138\3\2\2\2E\u013a\3\2\2\2G\u013c\3\2\2\2I"+
		"\u013e\3\2\2\2K\u0140\3\2\2\2M\u0142\3\2\2\2O\u0144\3\2\2\2Q\u0146\3\2"+
		"\2\2S\u0148\3\2\2\2U\u014a\3\2\2\2W\u014d\3\2\2\2Y\u0150\3\2\2\2[\u0153"+
		"\3\2\2\2]\u0156\3\2\2\2_\u0159\3\2\2\2a\u0164\3\2\2\2c\u0167\3\2\2\2e"+
		"\u016b\3\2\2\2g\u016d\3\2\2\2i\u0173\3\2\2\2k\u0177\3\2\2\2m\u0185\3\2"+
		"\2\2o\u0188\3\2\2\2q\u0190\3\2\2\2s\u01a2\3\2\2\2u\u01a4\3\2\2\2w\u01a6"+
		"\3\2\2\2y\u01a8\3\2\2\2{\u01b5\3\2\2\2}\u01c3\3\2\2\2\177\u01cc\3\2\2"+
		"\2\u0081\u01da\3\2\2\2\u0083\u01dd\3\2\2\2\u0085\u01e2\3\2\2\2\u0087\u01e7"+
		"\3\2\2\2\u0089\u01ec\3\2\2\2\u008b\u01f5\3\2\2\2\u008d\u01fe\3\2\2\2\u008f"+
		"\u0202\3\2\2\2\u0091\u0092\7c\2\2\u0092\u0093\7u\2\2\u0093\u0094\7o\2"+
		"\2\u0094\4\3\2\2\2\u0095\u0096\7e\2\2\u0096\u0097\7n\2\2\u0097\u0098\7"+
		"c\2\2\u0098\u0099\7u\2\2\u0099\u009a\7u\2\2\u009a\6\3\2\2\2\u009b\u009c"+
		"\7g\2\2\u009c\u009d\7z\2\2\u009d\u009e\7v\2\2\u009e\u009f\7g\2\2\u009f"+
		"\u00a0\7p\2\2\u00a0\u00a1\7f\2\2\u00a1\u00a2\7u\2\2\u00a2\b\3\2\2\2\u00a3"+
		"\u00a4\7g\2\2\u00a4\u00a5\7n\2\2\u00a5\u00a6\7u\2\2\u00a6\u00a7\7g\2\2"+
		"\u00a7\n\3\2\2\2\u00a8\u00a9\7h\2\2\u00a9\u00aa\7c\2\2\u00aa\u00ab\7n"+
		"\2\2\u00ab\u00ac\7u\2\2\u00ac\u00ad\7g\2\2\u00ad\f\3\2\2\2\u00ae\u00af"+
		"\7k\2\2\u00af\u00b0\7h\2\2\u00b0\16\3\2\2\2\u00b1\u00b2\7k\2\2\u00b2\u00b3"+
		"\7p\2\2\u00b3\u00b4\7u\2\2\u00b4\u00b5\7v\2\2\u00b5\u00b6\7c\2\2\u00b6"+
		"\u00b7\7p\2\2\u00b7\u00b8\7e\2\2\u00b8\u00b9\7g\2\2\u00b9\u00ba\7q\2\2"+
		"\u00ba\u00bb\7h\2\2\u00bb\20\3\2\2\2\u00bc\u00bd\7p\2\2\u00bd\u00be\7"+
		"g\2\2\u00be\u00bf\7y\2\2\u00bf\22\3\2\2\2\u00c0\u00c1\7p\2\2\u00c1\u00c2"+
		"\7w\2\2\u00c2\u00c3\7n\2\2\u00c3\u00c4\7n\2\2\u00c4\24\3\2\2\2\u00c5\u00c6"+
		"\7t\2\2\u00c6\u00c7\7g\2\2\u00c7\u00c8\7c\2\2\u00c8\u00c9\7f\2\2\u00c9"+
		"\u00ca\7k\2\2\u00ca\u00cb\7p\2\2\u00cb\u00cc\7v\2\2\u00cc\26\3\2\2\2\u00cd"+
		"\u00ce\7t\2\2\u00ce\u00cf\7g\2\2\u00cf\u00d0\7c\2\2\u00d0\u00d1\7f\2\2"+
		"\u00d1\u00d2\7h\2\2\u00d2\u00d3\7n\2\2\u00d3\u00d4\7q\2\2\u00d4\u00d5"+
		"\7c\2\2\u00d5\u00d6\7v\2\2\u00d6\30\3\2\2\2\u00d7\u00d8\7r\2\2\u00d8\u00d9"+
		"\7t\2\2\u00d9\u00da\7k\2\2\u00da\u00db\7p\2\2\u00db\u00dc\7v\2\2\u00dc"+
		"\32\3\2\2\2\u00dd\u00de\7r\2\2\u00de\u00df\7t\2\2\u00df\u00e0\7k\2\2\u00e0"+
		"\u00e1\7p\2\2\u00e1\u00e2\7v\2\2\u00e2\u00e3\7n\2\2\u00e3\u00e4\7p\2\2"+
		"\u00e4\34\3\2\2\2\u00e5\u00e6\7r\2\2\u00e6\u00e7\7t\2\2\u00e7\u00e8\7"+
		"k\2\2\u00e8\u00e9\7p\2\2\u00e9\u00ea\7v\2\2\u00ea\u00eb\7z\2\2\u00eb\36"+
		"\3\2\2\2\u00ec\u00ed\7r\2\2\u00ed\u00ee\7t\2\2\u00ee\u00ef\7k\2\2\u00ef"+
		"\u00f0\7p\2\2\u00f0\u00f1\7v\2\2\u00f1\u00f2\7n\2\2\u00f2\u00f3\7p\2\2"+
		"\u00f3\u00f4\7z\2\2\u00f4 \3\2\2\2\u00f5\u00f6\7r\2\2\u00f6\u00f7\7t\2"+
		"\2\u00f7\u00f8\7q\2\2\u00f8\u00f9\7v\2\2\u00f9\u00fa\7g\2\2\u00fa\u00fb"+
		"\7e\2\2\u00fb\u00fc\7v\2\2\u00fc\u00fd\7g\2\2\u00fd\u00fe\7f\2\2\u00fe"+
		"\"\3\2\2\2\u00ff\u0100\7t\2\2\u0100\u0101\7g\2\2\u0101\u0102\7v\2\2\u0102"+
		"\u0103\7w\2\2\u0103\u0104\7t\2\2\u0104\u0105\7p\2\2\u0105$\3\2\2\2\u0106"+
		"\u0107\7v\2\2\u0107\u0108\7j\2\2\u0108\u0109\7k\2\2\u0109\u010a\7u\2\2"+
		"\u010a&\3\2\2\2\u010b\u010c\7v\2\2\u010c\u010d\7t\2\2\u010d\u010e\7w\2"+
		"\2\u010e\u010f\7g\2\2\u010f(\3\2\2\2\u0110\u0111\7y\2\2\u0111\u0112\7"+
		"j\2\2\u0112\u0113\7k\2\2\u0113\u0114\7n\2\2\u0114\u0115\7g\2\2\u0115*"+
		"\3\2\2\2\u0116\u0117\t\2\2\2\u0117,\3\2\2\2\u0118\u0119\4\63;\2\u0119"+
		".\3\2\2\2\u011a\u011d\7\62\2\2\u011b\u011d\5-\27\2\u011c\u011a\3\2\2\2"+
		"\u011c\u011b\3\2\2\2\u011d\60\3\2\2\2\u011e\u0121\5+\26\2\u011f\u0121"+
		"\t\3\2\2\u0120\u011e\3\2\2\2\u0120\u011f\3\2\2\2\u0121\62\3\2\2\2\u0122"+
		"\u0127\5\61\31\2\u0123\u0126\5\61\31\2\u0124\u0126\5/\30\2\u0125\u0123"+
		"\3\2\2\2\u0125\u0124\3\2\2\2\u0126\u0129\3\2\2\2\u0127\u0125\3\2\2\2\u0127"+
		"\u0128\3\2\2\2\u0128\64\3\2\2\2\u0129\u0127\3\2\2\2\u012a\u012b\7>\2\2"+
		"\u012b\66\3\2\2\2\u012c\u012d\7@\2\2\u012d8\3\2\2\2\u012e\u012f\7?\2\2"+
		"\u012f:\3\2\2\2\u0130\u0131\7-\2\2\u0131<\3\2\2\2\u0132\u0133\7/\2\2\u0133"+
		">\3\2\2\2\u0134\u0135\7,\2\2\u0135@\3\2\2\2\u0136\u0137\7\61\2\2\u0137"+
		"B\3\2\2\2\u0138\u0139\7\'\2\2\u0139D\3\2\2\2\u013a\u013b\7\60\2\2\u013b"+
		"F\3\2\2\2\u013c\u013d\7.\2\2\u013dH\3\2\2\2\u013e\u013f\7*\2\2\u013fJ"+
		"\3\2\2\2\u0140\u0141\7+\2\2\u0141L\3\2\2\2\u0142\u0143\7}\2\2\u0143N\3"+
		"\2\2\2\u0144\u0145\7\177\2\2\u0145P\3\2\2\2\u0146\u0147\7#\2\2\u0147R"+
		"\3\2\2\2\u0148\u0149\7=\2\2\u0149T\3\2\2\2\u014a\u014b\7?\2\2\u014b\u014c"+
		"\7?\2\2\u014cV\3\2\2\2\u014d\u014e\7#\2\2\u014e\u014f\7?\2\2\u014fX\3"+
		"\2\2\2\u0150\u0151\7@\2\2\u0151\u0152\7?\2\2\u0152Z\3\2\2\2\u0153\u0154"+
		"\7>\2\2\u0154\u0155\7?\2\2\u0155\\\3\2\2\2\u0156\u0157\7(\2\2\u0157\u0158"+
		"\7(\2\2\u0158^\3\2\2\2\u0159\u015a\7~\2\2\u015a\u015b\7~\2\2\u015b`\3"+
		"\2\2\2\u015c\u0165\7\62\2\2\u015d\u0161\5-\27\2\u015e\u0160\5/\30\2\u015f"+
		"\u015e\3\2\2\2\u0160\u0163\3\2\2\2\u0161\u015f\3\2\2\2\u0161\u0162\3\2"+
		"\2\2\u0162\u0165\3\2\2\2\u0163\u0161\3\2\2\2\u0164\u015c\3\2\2\2\u0164"+
		"\u015d\3\2\2\2\u0165b\3\2\2\2\u0166\u0168\5/\30\2\u0167\u0166\3\2\2\2"+
		"\u0168\u0169\3\2\2\2\u0169\u0167\3\2\2\2\u0169\u016a\3\2\2\2\u016ad\3"+
		"\2\2\2\u016b\u016c\t\4\2\2\u016cf\3\2\2\2\u016d\u016f\t\5\2\2\u016e\u0170"+
		"\5e\63\2\u016f\u016e\3\2\2\2\u016f\u0170\3\2\2\2\u0170\u0171\3\2\2\2\u0171"+
		"\u0172\5c\62\2\u0172h\3\2\2\2\u0173\u0174\5c\62\2\u0174\u0175\7\60\2\2"+
		"\u0175\u0176\5c\62\2\u0176j\3\2\2\2\u0177\u0179\5i\65\2\u0178\u017a\5"+
		"g\64\2\u0179\u0178\3\2\2\2\u0179\u017a\3\2\2\2\u017a\u0181\3\2\2\2\u017b"+
		"\u017d\7H\2\2\u017c\u017b\3\2\2\2\u017d\u017e\3\2\2\2\u017e\u017c\3\2"+
		"\2\2\u017e\u017f\3\2\2\2\u017f\u0180\3\2\2\2\u0180\u0182\7h\2\2\u0181"+
		"\u017c\3\2\2\2\u0181\u0182\3\2\2\2\u0182l\3\2\2\2\u0183\u0186\5/\30\2"+
		"\u0184\u0186\t\2\2\2\u0185\u0183\3\2\2\2\u0185\u0184\3\2\2\2\u0186n\3"+
		"\2\2\2\u0187\u0189\5m\67\2\u0188\u0187\3\2\2\2\u0189\u018a\3\2\2\2\u018a"+
		"\u0188\3\2\2\2\u018a\u018b\3\2\2\2\u018bp\3\2\2\2\u018c\u018d\7\62\2\2"+
		"\u018d\u0191\7z\2\2\u018e\u018f\7\62\2\2\u018f\u0191\7Z\2\2\u0190\u018c"+
		"\3\2\2\2\u0190\u018e\3\2\2\2\u0191\u0192\3\2\2\2\u0192\u0193\5o8\2\u0193"+
		"\u0194\7\60\2\2\u0194\u0195\5o8\2\u0195\u0196\t\6\2\2\u0196\u0197\5e\63"+
		"\2\u0197\u019e\5c\62\2\u0198\u019a\7H\2\2\u0199\u0198\3\2\2\2\u019a\u019b"+
		"\3\2\2\2\u019b\u0199\3\2\2\2\u019b\u019c\3\2\2\2\u019c\u019d\3\2\2\2\u019d"+
		"\u019f\7h\2\2\u019e\u0199\3\2\2\2\u019e\u019f\3\2\2\2\u019fr\3\2\2\2\u01a0"+
		"\u01a3\5k\66\2\u01a1\u01a3\5q9\2\u01a2\u01a0\3\2\2\2\u01a2\u01a1\3\2\2"+
		"\2\u01a3t\3\2\2\2\u01a4\u01a5\4\13\f\2\u01a5v\3\2\2\2\u01a6\u01a7\n\7"+
		"\2\2\u01a7x\3\2\2\2\u01a8\u01b0\7$\2\2\u01a9\u01af\5w<\2\u01aa\u01ab\7"+
		"^\2\2\u01ab\u01af\7$\2\2\u01ac\u01ad\7^\2\2\u01ad\u01af\7^\2\2\u01ae\u01a9"+
		"\3\2\2\2\u01ae\u01aa\3\2\2\2\u01ae\u01ac\3\2\2\2\u01af\u01b2\3\2\2\2\u01b0"+
		"\u01ae\3\2\2\2\u01b0\u01b1\3\2\2\2\u01b1\u01b3\3\2\2\2\u01b2\u01b0\3\2"+
		"\2\2\u01b3\u01b4\7$\2\2\u01b4z\3\2\2\2\u01b5\u01be\7$\2\2\u01b6\u01bd"+
		"\5w<\2\u01b7\u01bd\5u;\2\u01b8\u01b9\7^\2\2\u01b9\u01bd\7$\2\2\u01ba\u01bb"+
		"\7^\2\2\u01bb\u01bd\7^\2\2\u01bc\u01b6\3\2\2\2\u01bc\u01b7\3\2\2\2\u01bc"+
		"\u01b8\3\2\2\2\u01bc\u01ba\3\2\2\2\u01bd\u01c0\3\2\2\2\u01be\u01bc\3\2"+
		"\2\2\u01be\u01bf\3\2\2\2\u01bf\u01c1\3\2\2\2\u01c0\u01be\3\2\2\2\u01c1"+
		"\u01c2\7$\2\2\u01c2|\3\2\2\2\u01c3\u01c4\7\61\2\2\u01c4\u01c5\7\61\2\2"+
		"\u01c5\u01c9\3\2\2\2\u01c6\u01c8\n\b\2\2\u01c7\u01c6\3\2\2\2\u01c8\u01cb"+
		"\3\2\2\2\u01c9\u01c7\3\2\2\2\u01c9\u01ca\3\2\2\2\u01ca~\3\2\2\2\u01cb"+
		"\u01c9\3\2\2\2\u01cc\u01cd\7\61\2\2\u01cd\u01ce\7,\2\2\u01ce\u01d2\3\2"+
		"\2\2\u01cf\u01d1\13\2\2\2\u01d0\u01cf\3\2\2\2\u01d1\u01d4\3\2\2\2\u01d2"+
		"\u01d3\3\2\2\2\u01d2\u01d0\3\2\2\2\u01d3\u01d5\3\2\2\2\u01d4\u01d2\3\2"+
		"\2\2\u01d5\u01d6\7,\2\2\u01d6\u01d7\7\61\2\2\u01d7\u0080\3\2\2\2\u01d8"+
		"\u01db\5}?\2\u01d9\u01db\5\177@\2\u01da\u01d8\3\2\2\2\u01da\u01d9\3\2"+
		"\2\2\u01db\u0082\3\2\2\2\u01dc\u01de\7\"\2\2\u01dd\u01dc\3\2\2\2\u01de"+
		"\u01df\3\2\2\2\u01df\u01dd\3\2\2\2\u01df\u01e0\3\2\2\2\u01e0\u0084\3\2"+
		"\2\2\u01e1\u01e3\7\13\2\2\u01e2\u01e1\3\2\2\2\u01e3\u01e4\3\2\2\2\u01e4"+
		"\u01e2\3\2\2\2\u01e4\u01e5\3\2\2\2\u01e5\u0086\3\2\2\2\u01e6\u01e8\7\f"+
		"\2\2\u01e7\u01e6\3\2\2\2\u01e8\u01e9\3\2\2\2\u01e9\u01e7\3\2\2\2\u01e9"+
		"\u01ea\3\2\2\2\u01ea\u0088\3\2\2\2\u01eb\u01ed\7\17\2\2\u01ec\u01eb\3"+
		"\2\2\2\u01ed\u01ee\3\2\2\2\u01ee\u01ec\3\2\2\2\u01ee\u01ef\3\2\2\2\u01ef"+
		"\u008a\3\2\2\2\u01f0\u01f6\5\u0083B\2\u01f1\u01f6\5\u0085C\2\u01f2\u01f6"+
		"\5\u0087D\2\u01f3\u01f6\5\u0089E\2\u01f4\u01f6\5\u0081A\2\u01f5\u01f0"+
		"\3\2\2\2\u01f5\u01f1\3\2\2\2\u01f5\u01f2\3\2\2\2\u01f5\u01f3\3\2\2\2\u01f5"+
		"\u01f4\3\2\2\2\u01f6\u01f7\3\2\2\2\u01f7\u01f5\3\2\2\2\u01f7\u01f8\3\2"+
		"\2\2\u01f8\u01f9\3\2\2\2\u01f9\u01fa\bF\2\2\u01fa\u008c\3\2\2\2\u01fb"+
		"\u01ff\5+\26\2\u01fc\u01ff\5/\30\2\u01fd\u01ff\t\t\2\2\u01fe\u01fb\3\2"+
		"\2\2\u01fe\u01fc\3\2\2\2\u01fe\u01fd\3\2\2\2\u01ff\u0200\3\2\2\2\u0200"+
		"\u01fe\3\2\2\2\u0200\u0201\3\2\2\2\u0201\u008e\3\2\2\2\u0202\u0203\7%"+
		"\2\2\u0203\u0204\7k\2\2\u0204\u0205\7p\2\2\u0205\u0206\7e\2\2\u0206\u0207"+
		"\7n\2\2\u0207\u0208\7w\2\2\u0208\u0209\7f\2\2\u0209\u020a\7g\2\2\u020a"+
		"\u020c\3\2\2\2\u020b\u020d\7\"\2\2\u020c\u020b\3\2\2\2\u020d\u020e\3\2"+
		"\2\2\u020e\u020c\3\2\2\2\u020e\u020f\3\2\2\2\u020f\u0210\3\2\2\2\u0210"+
		"\u0211\7$\2\2\u0211\u0212\5\u008dG\2\u0212\u0213\7$\2\2\u0213\u0214\b"+
		"H\3\2\u0214\u0090\3\2\2\2$\2\u011c\u0120\u0125\u0127\u0161\u0164\u0169"+
		"\u016f\u0179\u017e\u0181\u0185\u018a\u0190\u019b\u019e\u01a2\u01ae\u01b0"+
		"\u01bc\u01be\u01c9\u01d2\u01da\u01df\u01e4\u01e9\u01ee\u01f5\u01f7\u01fe"+
		"\u0200\u020e\4\3F\2\3H\3";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}