package edu.odu.cs.cs350;

public class Tokens 
{
    private TokenName tokenType;
    private String text;
    private int line;
    private int column;

    public Tokens(TokenName token, int lineNum, int columnNum)
    {
        this.tokenType = token;
        this.text = "";
        this.line = lineNum;
        this.column = columnNum; 
    }

    public Tokens(TokenName token, int lineNum, int columnNum, String theText)
    {
        this.tokenType = token;
        this.text = "";
        this.line = lineNum;
        this.column = columnNum; 
        this.text = theText;
    }
    
    public TokenName getTokenType()
    {
        return this.tokenType;
    }

    public String getText()
    {
        return this.text;
    }

    public String getLine()
    {
        return Integer.toString((this.line));
    }

    public String getColumn()
    {
        return Integer.toString(this.column);
    }

    public String toString()
    {
        if (getText().length() > 0) {
            return getLine() + " : " + getColumn() + " : " + getTokenType() + " : " + getText();
        } else {
            return getLine() + " : " + getColumn() + " : " + getTokenType().toString();
        }
    }

    public int hashCode()
    {
        return text.hashCode();
    }

    public boolean equals(Object rhs)
    {
        if (!(rhs instanceof Tokens)) {
            return false;
        }
        return (this.text).equals(((Tokens) rhs).text);
    }
    
    
}
