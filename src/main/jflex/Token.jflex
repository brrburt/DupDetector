package edu.odu.cs.cs350;

//@SuppressWArning("unused")

%%

%public
%class Lexer Analysis


%unicode
%line
%column

%type Tokens

%cup
%cupdebug

%{
  StringBuilder string = new StringBuilder();
  
  private Tokens symbol(TokenName tokenType) {
    return new Tokens(type, yyline+1, yycolumn+1);
  }

  private Tokens symbol(TokenName tokenType, String value) {
    return new Tokens(type, yyline+1, yycolumn+1, value);
  }

  /** 
   * assumes correct representation of a long value for 
   * specified radix in scanner buffer from <code>start</code> 
   * to <code>end</code> 
   */
  private long parseLong(int start, int end, int radix) {
    long result = 0;
    long digit;

    for (int i = start; i < end; i++) {
      digit  = Character.digit(yycharat(i),radix);
      result*= radix;
      result+= digit;
    }

    return result;
  }

%}

/* main character classes */
LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]

WhiteSpace = {LineTerminator} | [ \t\f]

/* comments */
Comment = {TraditionalComment} | {EndOfLineComment} | 
          {DocumentationComment}

TraditionalComment = "/*" [^*] ~"*/" | "/*" "*"+ "/"
EndOfLineComment = "//" {InputCharacter}* {LineTerminator}?
DocumentationComment = "/*" "*"+ [^/*] ~"*/"

/* identifiers */
Identifier = [:jletter:][:jletterdigit:]*

/* integer literals */
DecIntegerLiteral = 0 | [1-9][0-9]*
DecLongLiteral    = {DecIntegerLiteral} [lL]

HexIntegerLiteral = 0 [xX] 0* {HexDigit} {1,8}
HexLongLiteral    = 0 [xX] 0* {HexDigit} {1,16} [lL]
HexDigit          = [0-9a-fA-F]

OctIntegerLiteral = 0+ [1-3]? {OctDigit} {1,15}
OctLongLiteral    = 0+ 1? {OctDigit} {1,21} [lL]
OctDigit          = [0-7]
    
/* floating point literals */        
FloatLiteral  = ({FLit1}|{FLit2}|{FLit3}) {Exponent}? [fF]
DoubleLiteral = ({FLit1}|{FLit2}|{FLit3}) {Exponent}?

FLit1    = [0-9]+ \. [0-9]* 
FLit2    = \. [0-9]+ 
FLit3    = [0-9]+ 
Exponent = [eE] [+-]? [0-9]+

/* string and character literals */
StringCharacter = [^\r\n\"\\]
SingleCharacter = [^\r\n\'\\]

%state STRING, CHARLITERAL

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

  /* boolean literals */
  "true"                         { return symbol(TokenName.BOOLEAN_LITERAL, true); }
  "false"                        { return symbol(TokenName.BOOLEAN_LITERAL, false); }

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
  \"                             { yybegin(STRING); string.setLength(0); }

    /* character literal */
  \'                             { yybegin(CHARLITERAL); }

  /* numeric literals */
   /* This is matched together with the minus, because the number is too big to 
     be represented by a positive integer. */
  "-2147483648"                  { return symbol(TokenName.INTEGER_LITERAL, Integer.valueOf(Integer.MIN_VALUE)); }
  
  {DecIntegerLiteral}            { return symbol(TokenName.INTEGER_LITERAL, Integer.valueOf(yytext())); }
  {DecLongLiteral}               { return symbol(TokenName.INTEGER_LITERAL, new Long(yytext().substring(0,yylength()-1))); }
  
  {HexIntegerLiteral}            { return symbol(TokenName.INTEGER_LITERAL, Integer.valueOf((int) parseLong(2, yylength(), 16))); }
  {HexLongLiteral}               { return symbol(TokenName.INTEGER_LITERAL, new Long(parseLong(2, yylength()-1, 16))); }
 
  {OctIntegerLiteral}            { return symbol(TokenName.INTEGER_LITERAL, Integer.valueOf((int) parseLong(0, yylength(), 8))); }
  {OctLongLiteral}               { return symbol(TokenName.INTEGER_LITERAL, new Long(parseLong(0, yylength()-1, 8))); }
  
  {FloatLiteral}                 { return symbol(TokenName.FLOATING_POINT_LITERAL, new Float(yytext().substring(0,yylength()-1))); }
  {DoubleLiteral}                { return symbol(TokenName.FLOATING_POINT_LITERAL, new Double(yytext())); }
  {DoubleLiteral}[dD]            { return symbol(TokenName.FLOATING_POINT_LITERAL, new Double(yytext().substring(0,yylength()-1))); }
  
  /* comments */
  {Comment}                      { /* ignore */ }

  /* whitespace */
  {WhiteSpace}                   { /* ignore */ }

  /* identifiers */ 
  {Identifier}                   { return symbol(TokenName.IDENTIFIER, yytext()); }  
}

<STRING> {
  \"                             { yybegin(YYINITIAL); return symbol(TokenName.STRING_LITERAL, string.toString()); }

  {StringCharacter}+             { string.append( yytext() ); }
  
  /* escape sequences */
  "\\b"                          { string.append( '\b' ); }
  "\\t"                          { string.append( '\t' ); }
  "\\n"                          { string.append( '\n' ); }
  "\\f"                          { string.append( '\f' ); }
  "\\r"                          { string.append( '\r' ); }
  "\\\""                         { string.append( '\"' ); }
  "\\'"                          { string.append( '\'' ); }
  "\\\\"                         { string.append( '\\' ); }
  \\[0-3]?{OctDigit}?{OctDigit}  { char val = (char) Integer.parseInt(yytext().substring(1),8);
                        				   string.append( val ); }
  
  /* error cases */
  \\.                            { throw new RuntimeException("Illegal escape sequence \""+yytext()+"\""); }
  {LineTerminator}               { throw new RuntimeException("Unterminated string at end of line"); }
}

<CHARLITERAL> {
  {SingleCharacter}\'            { yybegin(YYINITIAL); return symbol(TokenName.CHARACTER_LITERAL, yytext().charAt(0)); }
  
  /* escape sequences */
  "\\b"\'                        { yybegin(YYINITIAL); return symbol(TokenName.CHARACTER_LITERAL, '\b');}
  "\\t"\'                        { yybegin(YYINITIAL); return symbol(TokenName.CHARACTER_LITERAL, '\t');}
  "\\n"\'                        { yybegin(YYINITIAL); return symbol(TokenName.CHARACTER_LITERAL, '\n');}
  "\\f"\'                        { yybegin(YYINITIAL); return symbol(TokenName.CHARACTER_LITERAL, '\f');}
  "\\r"\'                        { yybegin(YYINITIAL); return symbol(TokenName.CHARACTER_LITERAL, '\r');}
  "\\\""\'                       { yybegin(YYINITIAL); return symbol(TokenName.CHARACTER_LITERAL, '\"');}
  "\\'"\'                        { yybegin(YYINITIAL); return symbol(TokenName.CHARACTER_LITERAL, '\'');}
  "\\\\"\'                       { yybegin(YYINITIAL); return symbol(TokenName.CHARACTER_LITERAL, '\\'); }
  \\[0-3]?{OctDigit}?{OctDigit}\' { yybegin(YYINITIAL); 
			                              int val = Integer.parseInt(yytext().substring(1,yylength()-1),8);
			                            return symbol(CHARACTER_LITERAL, (char)val); }
  
  /* error cases */
  \\.                            { throw new RuntimeException("Illegal escape sequence \""+yytext()+"\""); }
  {LineTerminator}               { throw new RuntimeException("Unterminated character literal at end of line"); }
}

/* error fallback */
[^]                              { throw new RuntimeException("Illegal character \""+yytext()+
                                                              "\" at line "+yyline+", column "+yycolumn); }
<<EOF>>                          { return symbol(EOF); }

