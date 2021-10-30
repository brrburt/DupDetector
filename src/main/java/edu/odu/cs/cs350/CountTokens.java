package edu.odu.cs.cs350;

import java.util.StringTokenizer;
import java.util.List;
import java.util.LinkedList;

/*
// author Gerhea Reed 
// cs_greed007
*/

public class CountTokens 
{
    
    private int count;
    private String text;
    private int numOfTokens;

    /* 
    //Default Constructor
    */
    public CountTokens()
    {
    
        this.numOfTokens = 0;
        this.text = "Let's see if we can count tokens";
    }
    /* 
    // Constructor
    */
    public CountTokens(int totalNumOfTokens, String text)
    {
        this.numOfTokens = totalNumOfTokens; 
        this.text = text;
    }
    /*
    // Copy Constructor
    */
    public CountTokens(CountTokens src)
    {
        this.count = src.count;
        this.text = src.text;
    }

    public void setNumOfTokens(int total)
    {
        this.numOfTokens = total;
    }

    public int getNumOfTokens()
    {
        return this.numOfTokens;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public String getText()
    {
        return this.text;
    }

    public boolean equals(Object rhs)
    {
        if (!(rhs instanceof CountTokens)) {
            return false;
        }

        return (this.text).equals(((CountTokens) rhs).text);
    }
    /*
    * Return a hashcode
    */
    public int hashCode()
    {
        return text.hashCode();
    }
    /*
    * Return a (deep) copy of this object.
    */
    public Object clone()
    {
        return new CountTokens(this);
    }
    
    public String toString()
    {
        return text;
    }

    public void Counting()
    {
        StringTokenizer st = new StringTokenizer(getText());
        numOfTokens = st.countTokens();
    }

    public void Display()
    {
        System.out.println("Total number of Tokens: " + getNumOfTokens());
    }

}

//https://www.geeksforgeeks.org/stringtokenizer-counttokens-method-in-java-with-examples/
