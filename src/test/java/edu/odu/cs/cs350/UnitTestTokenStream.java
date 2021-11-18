package edu.odu.cs.cs350;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Scanner;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class UnitTestTokenStream 
{
    
 @BeforeAll
 public static void setUp() throws Exception {
}

@Test
public final void testFunction()
{
    String test = "456 int tokens in a file";
    TokenStream to = new TokenStream();
    Reader source = new StringReader(test);
    TokenStream tokenSt = new TokenStream(source);
//Begin quoted code from  S Zeil at
   /* ArrayList<Tokens> token = new ArrayList<Tokens>();
    for (Tokens toke : tokenSt)
    {
        token.add(token);
    }*/
//End quote
  /*  assertEquals (6, token.size());


   // Tokens x = new Token();
    assertEquals(TokenName.INTEGER, x.getTokenType());
    assertEquals(1, x.getLine());
    assertEquals("456", x.getText());

    assertEquals(TokenName.KEYWORD, x.getTokenType());
    assertEquals("int", x.getText());

    assertEquals(TokenName.IDENTIFIER, x.getTokenType());
    assertEquals("token", x.getText());*/


}


}
