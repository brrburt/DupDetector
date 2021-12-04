package edu.odu.cs.cs350;

import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


//Begin quoted code from  S Zeil at
//https://git.cs.odu.edu/zeil/jflexdemo/-/blob/master/src/main/java/edu/odu/cs/cs350/jflexdemo/TokenStream.java

public class TokenStream implements Iterable<Tokens>
{
  private List<Tokens> tokenBin;
  private LexerAnalyzer scanner;
 


  public TokenStream()
  {
    tokenBin = new LinkedList<Tokens>();
    scanner = new LexerAnalyzer(null);
  
    
  }

  public TokenStream(final Reader input)
  {
    tokenBin = new LinkedList<Tokens>();
    scanner = new LexerAnalyzer(input);
  
    
  }

  public void readCode()
  {
     // tokenBin = new LinkedList<Tokens>();
     // scanner = new LexerAnalyzer  (input);
        try 
        {
          Tokens token = scanner.yylex();
          while (token != null && token.getTokenType() != TokenName.EOF)
          {
            tokenBin.add(token);
            token = scanner.yylex();
          }
        } 
        catch (IOException ex)
        {
          System.out.println(ex);
        }
  }
 
  // End quoted code


  //return the number of tokens in files
  @Override
  public String toString()
  {
    return "Total number of tokens found: " + Integer.toString(getTokenCount());
  }
  /// want to read the total amount of tokens
  public final int getTokenCount()
  {
    return tokenBin.size();
  }
  /// iterate over the list of tokens 
  public final Iterator<Tokens> iterator() 
  {
    return tokenBin.iterator();
  }
  





}
