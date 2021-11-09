package edu.odu.cs.cs350;

import java.util.HashMap;
import java.util.Map;



public class Token 
{
    private TokenName tokenType;
    private String text;
    private int linePos;
    private int columnPos; 
    private char chr;
    private String s;
    private int position;
    
    public String value;

    Map<String, TokenName> keywords = new HashMap<>();

    Token()
    {
        this.linePos =0;
        this.columnPos = 0; 
        this.position =0;
        this.s = " ";
        this.value ="";
        
    }
    public Token( TokenName token, String theText, int line, int column)
    {
        this.tokenType = token;
        this.text = theText;
        this.linePos = line;
        this.columnPos = column; 
    }

    public final String getText()
    {
        return text;
    }

    public int getLinePos()
    {
        return linePos;
    }
    
    public int getColumnPos()
    {
        return columnPos;
    }

    public final TokenName getTokenType()
    {
        return tokenType;
    }
    public int hashCode()
    {
        return text.hashCode();
    }
    public boolean equals(Object rhs)
    {
        if (!(rhs instanceof Token)) {
            return false;
        }
        return (this.text).equals(((Token) rhs).text);
    }

    @Override
    public  String toString() {
        if (getText().length() > 0) {
            return getLinePos() + " : " + getColumnPos() + " : " + getTokenType() + " : " + getText();
        } else {
            return getLinePos() + " : " + getColumnPos() + " : " + getTokenType().toString();
        }
    }

   public void error(int line, int pos, String msg) 
    {
        if (line > 0 && pos > 0) 
        {
            System.out.printf("%s in line %d, pos %d\n", msg, line, pos);
        } 
        else 
        {
            System.out.println(msg);
        }
            System.exit(1);
    }
//Begin quoted code for lexical analyzer at
//https://rosettacode.org/wiki/Compiler/lexical_analyzer#Java
//modified to work for the project 

    Token(String source) 
    {

        this.linePos = 1;
        this.columnPos = 0;
        this.position = 0;
        this.s = source;
        this.chr = this.s.charAt(0);
        this.keywords.put("if", TokenName.KEYWORD_IF);
        this.keywords.put("else", TokenName.KEYWORD_ELSE);
        this.keywords.put("print", TokenName.KEYWORD_PRINT);
        this.keywords.put("putc", TokenName.KEYWORD_PUTC);
        this.keywords.put("while", TokenName.KEYWORD_WHILE);
 
    }
    Token follow(char expect, TokenName ifyes, TokenName ifno, int line, int pos)
    {
        if (getNextChar() == expect) {
            getNextChar();
            return new Token(ifyes, "", line, pos);
        }
        if (ifno == TokenName.END_OF_INPUT) {
            error(line, pos, String.format("follow: unrecognized character: (%d) '%c'", (int)this.chr, this.chr));
        }
        return new Token(ifno, "", line, pos);
    }

    Token char_lit(int line, int pos) 
    {
        char c = getNextChar(); // skip opening quote
        int n = (int)c;
        if (c == '\'') {
            error(line, pos, "empty character constant");
        } else if (c == '\\') {
            c = getNextChar();
            if (c == 'n') {
                n = 10;
            } else if (c == '\\') {
                n = '\\';
            } else {
                error(line, pos, String.format("unknown escape sequence \\%c", c));
            }
        }
        if (getNextChar() != '\'') {
            error(line, pos, "multi-character constant");
        }
        getNextChar();
        return new Token(TokenName.INTEGER, "" + n, line, pos);
    }

    Token string_lit(char start, int line, int pos) 
    {
        String result = "";
        while (getNextChar() != start) {
            if (this.chr == '\u0000') {
                error(line, pos, "EOF while scanning string literal"); // end of file 
            }
            if (this.chr == '\n') {
                error(line, pos, "EOL while scanning string literal"); // end of line
            }
            result += this.chr;
        }
        getNextChar();
        return new Token(TokenName.STRING, result, line, pos);
    }

    ///Modified code to skip over comments
    Token div_or_comment(int line, int pos) 
    {
        if (getNextChar() != '*') {
            return new Token(TokenName.OP_DIVIDE, "", line, pos);
        }
        getNextChar();
        while (true) { 
            if (this.chr == '\u0000') 
            {
                error(line, pos, "EOF in comment"); // end of file
            } 
            else if (this.chr == '*')
            {
                if (getNextChar() == '/') 
                {
                    getNextChar();
                   return getToken(); 
                }
                else if(getNextChar() == '/')
                {
                    getNextChar();
                    return getToken();
                } 
            } 
            else if(this.chr == '/')
            {
                if(getNextChar() == '*')
                {
                    getNextChar();
                    return getToken();
                }
                
            }
            else 
            {
                getNextChar();
            }
        }
    }

    Token identifier_or_integer(int line, int pos) 
    {
        boolean is_number = true;
        String text = "";
 
        while (Character.isAlphabetic(this.chr) || Character.isDigit(this.chr) || this.chr == '_') {
            text += this.chr;
            if (!Character.isDigit(this.chr)) {
                is_number = false;
            }
            getNextChar();
        }
 
        if (text.equals("")) {
            error(line, pos, String.format("identifer_or_integer unrecopgnized character: (%d) %c", (int)this.chr, this.chr));
        }
 
        if (Character.isDigit(text.charAt(0))) {
            if (!is_number) {
                error(line, pos, String.format("invaslid number: %s", text));
            }
            return new Token(TokenName.INTEGER, text, line, pos);
        }
 
        if (this.keywords.containsKey(text)) {
            return new Token(this.keywords.get(text), "", line, pos);
        }
        return new Token(TokenName.IDENTIFIER, text, line, pos);
    }

    //Modified code to read C++ characters

    Token getToken() 
    {
        int line, pos;
        while (Character.isWhitespace(this.chr)) {
            getNextChar();
        }
        line = this.linePos;
        pos = this.columnPos;
    
        switch (this.chr) {
            case '\u0000': return new Token(TokenName.END_OF_INPUT, "", this.linePos, this.columnPos);
            case '/': return div_or_comment(line, pos);
            case '\'': return char_lit(line, pos);
            case '<': return follow('=', TokenName.OP_LESSEQUAL, TokenName.OP_LESS, line, pos);
            case '>': return follow('=', TokenName.OP_GREATEREQUAL, TokenName.OP_GREATER, line, pos);
            case '=': return follow('=', TokenName.OP_EQUAL, TokenName.OP_ASSIGN, line, pos);
            case '!': return follow('=', TokenName.OP_NOTEQUAL, TokenName.OP_NOT, line, pos);
            case '|': return follow('|', TokenName.OP_OR, TokenName.END_OF_INPUT, line, pos);
            case '"': return string_lit(this.chr, line, pos);
            case '{': getNextChar(); return new Token(TokenName.LEFTBRACE, "", line, pos);
            case '}': getNextChar(); return new Token(TokenName.RIGHTBRACE, "", line, pos);
            case '(': getNextChar(); return new Token(TokenName.LEFTPAREN, "", line, pos);
            case ')': getNextChar(); return new Token(TokenName.RIGHTPAREN, "", line, pos);
            case '+': getNextChar(); return new Token(TokenName.OP_ADD, "", line, pos);
            case '-': getNextChar(); return new Token(TokenName.OP_SUBTRACT, "", line, pos);
            case '*': getNextChar(); return new Token(TokenName.OP_MULTIPLY, "", line, pos);
            case '%': getNextChar(); return new Token(TokenName.OP_MOD, "", line, pos);
            case ';': getNextChar(); return new Token(TokenName.SEMICOLON, "", line, pos);
            case ',': getNextChar(); return new Token(TokenName.COMMA, "", line, pos);
            case '#': getNextChar(); return new Token(TokenName.POUND, "", line, pos);
            case '[': getNextChar(); return new Token(TokenName.LTBRACKET, "", line, pos);
            case ']': getNextChar(); return new Token(TokenName.RTBRACKET, "", line, pos);
            case ':': getNextChar(); return new Token(TokenName.SCOPE_OPERATER, "", line, pos);
            case '.': getNextChar(); return new Token(TokenName.PERIOD, "", line, pos);
            case '?': getNextChar(); return new Token(TokenName.QUESTION_MARK, "", line, pos);
            case '~': getNextChar(); return new Token(TokenName.DELETE_SYMBOL, "", line, pos);
            case '&': getNextChar(); return new Token(TokenName.OP_ADD, "", line, pos);

 
            default: return identifier_or_integer(line, pos);
        }


    }

    char getNextChar() 
    {
        this.columnPos++;
        this.position++;
        if (this.position >= this.s.length()) {
            this.chr = '\u0000';
            return this.chr;
        }
        this.chr = this.s.charAt(this.position);
        if (this.chr == '\n') {
            this.linePos++;
            this.columnPos = 0;
        }
        return this.chr;
    }

    void printTokens() 
    {
        Token t;
        int value =0;
        while ((t = getToken()).tokenType != TokenName.END_OF_INPUT) 
        {
            value ++;
        System.out.println(t);

        }

        System.out.println("Total number of tokens is " + value);
        

    }

    ///Modified to work for the project
    ///End quoted code
    
}
