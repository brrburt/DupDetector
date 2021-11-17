package edu.odu.cs.cs350;

import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.*;

//Begin quoted code from  S Zeil at
//https://git.cs.odu.edu/zeil/jflexdemo/-/blob/master/src/main/java/edu/odu/cs/cs350/jflexdemo/TokenStream.java

public class TokenStream implements Iterable<Tokens>
{
  private List<Tokens> tokenBin;
  private Tokens to;


  public TokenStream()
  {
    tokenBin = new LinkedList<Tokens>();
    

  }

  public TokenStream(final Reader input)
  {
      tokenBin = new LinkedList<Tokens>();
     /* GeneratedScanner scanner = new GeneratedScanner (input);
        try {
            Token token = scanner.yylex();
            while (token != null && token.getTokenType() != TokenName.EOF) {
                tokenBin.add(tokenBin);
                token = scanner.yylex();
            }
        } catch (IOException ex) {
            // Not necessarily a problem, depending on the input source
        }*/
  }

  // End quoted code


  //return the number of tokens in files
  /*public String toString()
  {
   return tokenBin.size();

  }*/
  /// want to read the total amount of tokens
  /*public final int getTokenCount()
  {

  }*/
  /// iterate over the list of tokens 
  public final Iterator<Tokens> iterator() 
  {
    return tokenBin.iterator();
  }




}
