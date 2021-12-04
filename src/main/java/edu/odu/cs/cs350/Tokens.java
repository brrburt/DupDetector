package edu.odu.cs.cs350;

public class Tokens 
{
    private TokenName tokenType;
    private String location;
    private int line;
    private int column;

    public Tokens(TokenName token, int lineNum, int columnNum)
    {
        this.tokenType = token;
        this.location = "";
        this.line = lineNum;
        this.column = columnNum; 
    }

    public Tokens(TokenName token, int lineNum, int columnNum, String position)
    {
        this.tokenType = token;
        this.location = position;
        this.line = lineNum;
        this.column = columnNum; 
    }
    
    public TokenName getTokenType()
    {
        return this.tokenType;
    }

    public String getLocation()
    {
        return this.location;
    }

    public void setLine(int lineNum)
    {
        this.line = lineNum;
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
        if (getLocation().length() > 0) 
        {
            return getLine() + " : " + getColumn() + " : " + getTokenType() + " : " + getLocation();
        } else {
            return getLine() + " : " + getColumn() + " : " + getTokenType().toString();
        }
    }

    public int hashCode()
    {
        return location.hashCode();
    }

    public boolean equals(Object rhs)
    {
        if (!(rhs instanceof Tokens)) {
            return false;
        }
        return (this.location).equals(((Tokens) rhs).location);
    }
    
    
}
