lexer grammar DecaLexer;

options {
   language=Java;
   // Tell ANTLR to make the generated lexer class extend the
   // the named class, which is where any supporting code and
   // variables will be placed.
   superClass = AbstractDecaLexer;
}

@members {
}

// NOTE: Only the non-fragment rules produce tokens.

// Deca keywords
ASM: 'asm';
CLASS: 'class';
EXTENDS: 'extends';
ELSE: 'else';
FALSE: 'false';
IF: 'if';
INSTANCEOF: 'instanceof';
NEW: 'new';
NULL: 'null';
READINT: 'readInt';
READFLOAT: 'readFloat';
PRINT: 'print';
PRINTLN: 'println';
PRINTX: 'printx';
PRINTLNX: 'printlnx';
PROTECTED: 'protected';
RETURN: 'return';
THIS: 'this';
TRUE: 'true';
WHILE: 'while';

// Deca identifier.
// TODO: See why we allow '$' in here?
fragment LETTER: 'a' .. 'z' | 'A' .. 'Z';
fragment NON_ZERO_DIGIT: '1' .. '9';
fragment DIGIT: '0' | NON_ZERO_DIGIT;
fragment IDENT_CHAR: LETTER | '$' | '_';
IDENT: IDENT_CHAR (IDENT_CHAR | DIGIT)*;

// Deca symbols.
LT: '<';
GT: '>';
EQUALS: '=';
PLUS: '+';
MINUS: '-';
TIMES: '*';
SLASH: '/';
PERCENT: '%';
DOT: '.';
COMMA: ',';
OPARENT: '(';
CPARENT: ')';
OBRACE: '{';
CBRACE: '}';
EXCLAM: '!';
SEMI: ';';
EQEQ: '==';
NEQ: '!=';
GEQ: '>=';
LEQ: '<=';
AND: '&&';
OR: '||';

// Deca integer literals
INT: '0' | NON_ZERO_DIGIT DIGIT*;

// Deca float literals
fragment NUM: DIGIT+;
fragment SIGN: '+' | '-';
fragment EXP: ('E' | 'e') SIGN? NUM;
fragment DEC: NUM '.' NUM;
fragment FLOAT_DEC: DEC EXP? ('F' + 'f')?;
fragment DIGIT_HEX: DIGIT | 'a' .. 'f' | 'A' .. 'F';
fragment NUM_HEX: DIGIT_HEX+;
fragment FLOAT_HEX: ('0x' | '0X') NUM_HEX '.' NUM_HEX ('p' | 'P') SIGN NUM ('F' + 'f')?;
FLOAT: FLOAT_DEC | FLOAT_HEX;

// Deca string literals
fragment EOL: '\n' | '\t';
fragment STRING_CHAR: ~ ('"' | '\\' | '\n' | '\t');
STRING: '"' (STRING_CHAR | '\\"' | '\\\\')* '"';
MULTI_LINE_STRING: '"' (STRING_CHAR | EOL | '\\"' | '\\\\')* '"';

// Deca whitespace

// Deca comment
fragment SINGLE_LINE_COMMENT: '//' ~( '\n' | '\r' )*;
fragment MULTI_LINE_COMMENT: '/*' .*? '*/';
fragment COMMENT: SINGLE_LINE_COMMENT | MULTI_LINE_COMMENT;

// NOTE: this avoids jumping in between rules
fragment SPACE: ' '+;
fragment TAB: '\t'+;
fragment NL: '\n'+;
fragment CR: '\r'+;

WS: (SPACE | TAB | NL | CR | COMMENT)+ { skip(); };

// Deca #include's
fragment FILENAME: (LETTER | DIGIT | '.' | '-' | '_')+;
INCLUDE: '#include' ' '+ '"' FILENAME '"' { doInclude(getText()); };