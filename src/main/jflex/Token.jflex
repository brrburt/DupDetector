package edu.odu.cs.cs350;


//@SuppressWarnings("unused")

%%

%public
%class LexerAnalyzer

%unicode
%line
%column

%type Tokens

%{
  StringBuffer string = new StringBuffer();
  
  private Tokens symbol(TokenName type) {
    return new Tokens(type, yyline+1, yycolumn+1);
  }

  private Tokens symbol(TokenName type, String value) {
    return new Tokens(type, yyline+1, yycolumn+1, value);
  }
  
%}

/* main character classes */
LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]

WhiteSpace = {LineTerminator} | [ \t\f]

/* comments */
Comments = {TraditionalComment} | {EndOfLineComment} | {DocumentationComment}

TraditionalComment   = "/*" [^*] ~"*/" | "/*" "*"+ "/"
EndOfLineComment     = "//" {InputCharacter}* {LineTerminator}?
DocumentationComment = "/**" {CommentContent} "*"+ "/"
CommentContent       = ( [^*] | \*+ [^/*] )*

/* identifiers */
Identifier = [:jletter:][:jletterdigit:]*

/* integer literals */
ConstantNumbers = [0-9][0-9]* | [0-9][_0-9]*[0-9]

/* string literals */
StringCharacter = [^\r\n\"\\]

/* Strings */
%state STRING

/* Rules */
%%

<YYINITIAL> {
/* keywords */
"if"                             { return symbol(TokenName.IF); }
"break"                          { return symbol(TokenName.BREAK); }
"case"                           { return symbol(TokenName.CASE); }
"catch"                          { return symbol(TokenName.CATCH); }
"char"                           { return symbol(TokenName.CHAR); }
"class"                          { return symbol(TokenName.CLASS); }
"const"                          { return symbol(TokenName.CONST); }
"continue"                       { return symbol(TokenName.CONTINUE); }
"default"                        { return symbol(TokenName.DEFAUlT); }
"delete"                         { return symbol(TokenName.DELETE); }
"do"                             { return symbol(TokenName.DO); }
"double"                         { return symbol(TokenName.DOUBLE); }
"else"                           { return symbol(TokenName.ELSE); }
"enum"                           { return symbol(TokenName.ENUM); }
"explicit"                       { return symbol(TokenName.EXPLICIT); }
"false"                          { return symbol(TokenName.FALSE); }
"float"                          { return symbol(TokenName.FLOAT); }
"for"                            { return symbol(TokenName.FOR); }
"goto"                           { return symbol(TokenName.GOTO); }
"inline"                         { return symbol(TokenName.INLINE); }
"int"                            { return symbol(TokenName.INT); }
"long"                           { return symbol(TokenName.LONG); }
"namespace"                      { return symbol(TokenName.NAMESPACE); }
"new"                            { return symbol(TokenName.NEW); }
"not"                            { return symbol(TokenName.NOT); }
"nullptr"                        { return symbol(TokenName.NULLPTR); }
"operator"                       { return symbol(TokenName.OPERATOR); }
"or"                             { return symbol(TokenName.OR); }
"private"                        { return symbol(TokenName.PRIVATE); }
"protected"                      { return symbol(TokenName.PROTECTED); }
"public"                         { return symbol(TokenName.PUBLIC); }
"register"                       { return symbol(TokenName.REGISTER); }
"requires"                       { return symbol(TokenName.REQUIRES); }
"return"                         { return symbol(TokenName.RETURN); }
"short"                          { return symbol(TokenName.SHORT); }
"signed"                         { return symbol(TokenName.SIGNED); }
"sizeof"                         { return symbol(TokenName.SIZEOF); }
"static"                         { return symbol(TokenName.STATC); }
"static_case"                    { return symbol(TokenName.STACTIC_CAST); }
"struct"                         { return symbol(TokenName.STRUCT); }
"switch"                         { return symbol(TokenName.SWITCH); }
"template"                       { return symbol(TokenName.REGISTER); }
"this"                           { return symbol(TokenName.REQUIRES); }
"return"                         { return symbol(TokenName.RETURN); }
"throw"                          { return symbol(TokenName.THROW); }
"true"                           { return symbol(TokenName.TRUE); }
"try"                            { return symbol(TokenName.TRY); }
"typedef"                        { return symbol(TokenName.TYPEDEF); }
"typename"                       { return symbol(TokenName.TYPENAME); }
"while"                          { return symbol(TokenName.WHILE); }
"union"                          { return symbol(TokenName.UNION); }
"unsigned"                       { return symbol(TokenName.UNSIGNED); }
"using"                          { return symbol(TokenName.USING); }
"virtual"                        { return symbol(TokenName.VIRTUAL); }
"void"                           { return symbol(TokenName.VOID); }
"iostream"                       { return symbol(TokenName.IOSTREAM); }
"std"                            { return symbol(TokenName.STD); }
"bool"                           { return symbol(TokenName.BOOL); }



    /* null literal */
  "null"                         { return symbol(TokenName.NULL_LITERAL); }

 /* separators */
  "("                            { return symbol(TokenName.LPAREN); }
  ")"                            { return symbol(TokenName.RPAREN); }
  "{"                            { return symbol(TokenName.LBRACE); }
  "}"                            { return symbol(TokenName.RBRACE); }
  "["                            { return symbol(TokenName.LBRACK); }
  "]"                            { return symbol(TokenName.RBRACK); }
  ";"                            { return symbol(TokenName.SEMICOLON); }
  ","                            { return symbol(TokenName.COMMA); }
  "."                            { return symbol(TokenName.DOT); }

 /* operators */
  "="                            { return symbol(TokenName.EQ); }
  "@"                            { return symbol(TokenName.OP_AT); }
  "#"                            { return symbol(TokenName.POUND); }
  ">"                            { return symbol(TokenName.GT); }
  "<"                            { return symbol(TokenName.LT); }
  "!"                            { return symbol(TokenName.NOT); }
  "~"                            { return symbol(TokenName.COMP); }
  "?"                            { return symbol(TokenName.QUESTION); }
  ":"                            { return symbol(TokenName.COLON); }
  "=="                           { return symbol(TokenName.EQEQ); }
  "<="                           { return symbol(TokenName.LTEQ); }
  ">="                           { return symbol(TokenName.GTEQ); }
  "!="                           { return symbol(TokenName.NOTEQ); }
  "&&"                           { return symbol(TokenName.ANDAND); }
  "||"                           { return symbol(TokenName.OROR); }
  "++"                           { return symbol(TokenName.PLUSPLUS); }
  "--"                           { return symbol(TokenName.MINUSMINUS); }
  "+"                            { return symbol(TokenName.PLUS); }
  "-"                            { return symbol(TokenName.MINUS); }
  "*"                            { return symbol(TokenName.MULT); }
  "/"                            { return symbol(TokenName.DIV); }
  "&"                            { return symbol(TokenName.AND); }
  "|"                            { return symbol(TokenName.OR); }
  "^"                            { return symbol(TokenName.XOR); }
  "%"                            { return symbol(TokenName.MOD); }
  "<<"                           { return symbol(TokenName.LSHIFT); }
  ">>"                           { return symbol(TokenName.RSHIFT); }
  ">>>"                          { return symbol(TokenName.URSHIFT); }
  "+="                           { return symbol(TokenName.PLUSEQ); }
  "-="                           { return symbol(TokenName.MINUSEQ); }
  "*="                           { return symbol(TokenName.MULTEQ); }
  "/="                           { return symbol(TokenName.DIVEQ); }
  "&="                           { return symbol(TokenName.ANDEQ); }
  "|="                           { return symbol(TokenName.OREQ); }
  "^="                           { return symbol(TokenName.XOREQ); }
  "%="                           { return symbol(TokenName.MODEQ); }
  "<<="                          { return symbol(TokenName.LSHIFTEQ); }
  ">>="                          { return symbol(TokenName.RSHIFTEQ); }
  ">>>="                         { return symbol(TokenName.URSHIFTEQ); }

  /* string literal */
  \"                            { yybegin(STRING); string.setLength(0); }

  /* numeric literals */
  {ConstantNumbers}             { return symbol(TokenName.CONSTANT_NUMBERS, yytext()); }

  /* Identifiers */
  {Identifier}                  { return symbol(TokenName.IDENTIFIER, yytext()); } 
  
  /* whitespaces */
  {WhiteSpace}                  {/* Ignore */}  
  
  /* comments */
  {Comments}                    {/* Ignore */}
}

<STRING> {
 \"                             { yybegin(YYINITIAL); return symbol(TokenName.STRING_LITERAL, string.toString()); }

 {StringCharacter}+             { string.append( yytext() ); }

 "\\b"                          { string.append( '\b' ); }
 "\\t"                          { string.append( '\t' ); }
 "\\n"                          { string.append( '\n' ); }
 "\\f"                          { string.append( '\f' ); }
 "\\r"                          { string.append( '\r' ); }
 "\\\""                         { string.append( '\"' ); }
 "\\'"                          { string.append( '\'' ); }
 "\\\\"                         { string.append( '\\' ); }


 \\.                            { throw new RuntimeException("Illegal escape sequence \""+yytext()+"\""); }
 {LineTerminator}               { throw new RuntimeException("Unterminated string at end of line"); }
}


/* error fallback */
[^]                              { throw new RuntimeException("Illegal character \""+yytext()+
                                                              "\" at line "+yyline+", column "+yycolumn); }
<<EOF>>                          { return symbol(TokenName.EOF); }
