/**
 * Simplified version of grammar CSS grammar - http://www.antlr.org/grammar/1214945003224/csst3.g
**/
grammar Css;
options {	
	output=AST;
	ASTLabelType=CommonTree;	
	k=4;	
	}

tokens {
	IMPORT;
	NESTED;
	NEST;
	RULE;
	ATTRIB;
	CHILD;
	ADJACENT_SIBLING;
	ATTRIBEQUAL;
	HASVALUE;
	BEGINSWITH;
	PSEUDO;
	PROPERTY;
	COLOUR;
	FUNCTION;
	TAG;
	ID;
	CLASS;
	UNIVERSAL;
}

@header {
package org.langera.freud.optional.css.parser;
}

@lexer::header {
package org.langera.freud.optional.css.parser;
}

stylesheet
	: importRule* (nested | ruleset)+
	;

importRule
	: ('@import' | '@include')  IDENT -> ^( IMPORT IDENT )
	;

nested
 	: '@' nest '{' properties? nested* '}' -> ^( NESTED nest properties* nested* )
	;

nest
	: IDENT IDENT* pseudo* -> ^( NEST IDENT IDENT* pseudo* )
	;
	
ruleset
 	: selectors '{' properties? '}' -> ^( RULE selectors properties* )
	;
	
selectors
	: selector (',' selector)*
	;
	
selector
	: elem selectorOperation* attrib* pseudo? ->  elem selectorOperation* attrib* pseudo*
	;

selectorOperation
	: selectop? elem -> selectop* elem
	;

selectop
	: '>' -> CHILD
        | '+'  -> ADJACENT_SIBLING
	;

properties
	: declaration (';' declaration?)* ->  declaration+
	;
	
elem
	: '*' -> ^( UNIVERSAL )
	| '#' IDENT -> ^( ID IDENT )
	| '.' IDENT -> ^( CLASS IDENT )			
	| IDENT -> ^( TAG IDENT )
	;

pseudo
	: (':'|'::') IDENT -> ^( PSEUDO IDENT )
	| (':'|'::') function -> ^( PSEUDO function )
	;

attrib
	: '[' IDENT (attribRelate IDENT)? ']' -> ^( ATTRIB IDENT (attribRelate IDENT*)? )
	;
	
attribRelate
	: '='  -> ATTRIBEQUAL
	| '~=' -> HASVALUE
	| '|=' -> BEGINSWITH
	;	
  
declaration
	: IDENT ':' args -> ^( PROPERTY IDENT args )
	;

args
	: expr (','? expr)* -> expr*
	;

expr
	: (IDENT unit)
	| IDENT
	| '#' IDENT -> ^( COLOUR '#' IDENT )
	| '.' IDENT
	| function
	;		
	
unit
	: ('%'|'px'|'cm'|'mm'|'in'|'pt'|'pc'|'em'|'ex'|'deg'|'rad'|'grad'|'ms'|'s'|'hz'|'khz')
	;
	
function
	: IDENT '(' args? ')' -> IDENT '(' args* ')'
	;
		
IDENT	
	:	('_' | '-' | 'a'..'z'| 'A'..'Z' | '\u0100'..'\ufffe' | '0'..'9' | '"' | '\'' | '/' | '=' | '!')
		 (options { greedy = true; } : ('_' | '-' | 'a'..'z'| 'A'..'Z' | '\u0100'..'\ufffe' | '0'..'9' | '"' | '\'' | '/' | '.' | '='))* 
	;	


// Single-line comments
SL_COMMENT
	:	'//'
		(~('\n'|'\r'))* ('\n'|'\r'('\n')?)
		{$channel=HIDDEN;}
	;
	
// multiple-line comments
COMMENT
	:	'/*' .* '*/' { $channel = HIDDEN; }
	;

// Whitespace -- ignored
WS	: ( ' ' | '\t' | '\r' | '\n' | '\f' )+ { $channel = HIDDEN; }
	;

