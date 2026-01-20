lexer grammar CoolLexer;

@header{
    package cool.lexer;
}

tokens { ERROR }

@members{
    private void raiseError(String msg) {
        setText(msg);
        setType(ERROR);
    }
}

LINE_COMMENT
    : '--' ~[\r\n]* -> skip
    ;

BlOCK_COMMENT_EROR : '*)'
    {
        raiseError("Unmatched *)");
    };

BLOCK_COMMENT: '(*' (BLOCK_COMMENT | .)*? ('*)' {
    skip();
    }
    | EOF
    {
        raiseError("EOF in comment");
    });

WS
    :   [ \n\f\r\t]+ -> skip
    ;

IF : 'if';
THEN : 'then';
ELSE : 'else';
FI: 'fi';

TRUE : 'true';
FALSE : 'false';
BOOL : TRUE | FALSE;

CLASS : 'class';
INHERITS : 'inherits';
AT : '@';
NEW: 'new';
ISVOID : 'isvoid';
NOT : 'not';

WHILE : 'while';
LOOP : 'loop';
POOL : 'pool';

LET : 'let';
IN : 'in';

CASE : 'case';
OF : 'of';
ESAC : 'esac';

TYPE : [A-Z][a-zA-Z0-9_]*;
ID : [a-z][a-zA-Z0-9_]*;

INT : [0-9]+;

STRING : '"' ( '\\' . | ~["\\\r\n] )* '"'
   {
        String text = getText();

        if (text.length() > 1026) {
            raiseError("String constant too long");
            return;
        }

        text = text.substring(1, text.length() - 1);

        if (text.indexOf('\0') != -1) {
            raiseError("String contains null character");
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c == '\\' && i + 1 < text.length()) {
                char next = text.charAt(i + 1);
                if (next == 'n') {
                    sb.append('\n');
                } else if (next == 't') {
                    sb.append('\t');
                } else if (next == 'b') {
                    sb.append('\b');
                } else if (next == 'f') {
                    sb.append('\f');
                } else {
                    sb.append(next);
                }
                i++;
            } else {
                sb.append(c);
            }
        }

        setText(sb.toString());
    }
;

EOF_LINE_ERROR : '"' ( '\\' . | ~["\\\r\n] )* EOF
    {
        raiseError("EOF in string constant");
   }
;

NEW_LINE_ERROR : '"' ( '\\' . | ~["\\\r\n] )*
   {
        raiseError("Unterminated string constant");
   }
;

SEMI : ';';
COMMA : ',';
DOT : '.';
COLON : ':';
ASSIGN : '<-';

LPAREN : '(';
RPAREN : ')';
LBRACE : '{';
RBRACE : '}';

PLUS : '+';
MINUS : '-';
MULT : '*';
DIV : '/';

EQUAL : '=';
TILDE : '~';
LT : '<';
LE : '<=';
EL : '=>';

ERROR_CHAR : . {
    raiseError("Invalid character: " + getText());
};