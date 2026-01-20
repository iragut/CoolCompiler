parser grammar CoolParser;

options {
    tokenVocab = CoolLexer;
}

@header{
    package cool.parser;
}

program
    :   (class SEMI)+
    ;

class
    : CLASS TYPE (INHERITS TYPE)? LBRACE (feature SEMI)* RBRACE
    ;

feature
    : ID LPAREN (formal arguments)? RPAREN COLON TYPE LBRACE expr RBRACE
    | assign
    ;

arguments
    :(COMMA formal)*
    ;

formal
    : ID COLON TYPE
    ;

expr
    : expr (AT TYPE)? DOT ID LPAREN (expr (COMMA expr)*)? RPAREN       # func_call_class
    | ID LPAREN (expr (COMMA expr)*)? RPAREN                           # func_call
    | IF expr THEN expr ELSE expr FI                                   # if_then_else
    | WHILE expr LOOP expr POOL                                        # while_loop
    | LBRACE (expr SEMI)+ RBRACE                                       # block
    | LET assign (COMMA assign)* IN expr                               # local_vars
    | CASE expr OF case_method+ ESAC                                   # case
    | NEW TYPE                                                         # new_type
    | ISVOID expr                                                      # isvoid
    | TILDE expr                                                       # negate
    | expr (MULT | DIV) expr                                           # mul_div
    | expr (PLUS | MINUS) expr                                         # add_sub
    | expr LT expr                                                     # lt
    | expr LE expr                                                     # le
    | expr EQUAL expr                                                  # eq
    | NOT expr                                                         # not
    | LPAREN expr RPAREN                                               # paren
    | ID                                                               # id
    | INT                                                              # int
    | STRING                                                           # string
    | TRUE                                                             # true
    | FALSE                                                            # false
    | ID ASSIGN expr                                                   # assig_expresion
    ;

assign
    : ID COLON TYPE (ASSIGN expr)?
    ;

case_method
    : ID COLON TYPE EL expr SEMI
    ;