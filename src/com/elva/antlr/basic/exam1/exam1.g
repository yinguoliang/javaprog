grammar Expr;

@header {package com.elva.antlr.basic.exam1;}
@lexer::header{package com.elva.antlr.basic.exam1;}

prog: stat  
;

stat : expr 
	  |NEWLINE
;

expr : multExpr (('+'|'-') multExpr)*
;

multExpr : atom (('*'|'/') atom)*
;

atom :   '(' expr ')' 
	   | INT  
	   | ID  	
;


ID : ('a'..'z' |'A'..'Z')+ ;
INT : '0'..'9' + ;
NEWLINE:'\r' ? '\n' ;
WS : (' ' |'\t' |'\n' |'\r' )+ {skip();} ;

