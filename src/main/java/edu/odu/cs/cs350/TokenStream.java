package edu.odu.cs.cs350;

import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.*;


public class TokenStream 
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
      GeneratedScanner scanner = new GeneratedScanner (input);
        try {
            Token token = scanner.yylex();
            while (token != null && token.getTokenType() != TokenName.EOF) {
                tokenBin.add(tokenBin);
                token = scanner.yylex();
            }
        } catch (IOException ex) {
            // Not necessarily a problem, depending on the input source
        }



  }
}
