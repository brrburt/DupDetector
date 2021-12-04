package edu.odu.cs.cs350;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;  

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
public class UnitTestTokenStream 
{
    
 @BeforeAll
public static void setUp() throws Exception 
{
    
}

@Test 
public void testConstructor()
{
    String test = "Total number of tokens found: ";
    TokenStream st = new TokenStream();
    assertThat(st.getTokenCount(), equalTo(0));  
    assertThat(st.toString(), equalTo(test + "0"));
}

@Test
public final void testFunction()
{
    try
    {
        String testF = "Total number of tokens found: { 0  #";
        File file = new File("src/test/java/edu/odu/cs/cs350/examples/main.cpp");
        Scanner scan = new Scanner(file);
        String content = " ";
        while(scan.hasNext())
        {
            content += scan.nextLine() +"\n";
        }
        scan.close();
        ArrayList <Tokens> tokens = new ArrayList<Tokens>();
        Reader read = new StringReader(content);
        //Reader read = new StringReader(testF);
        TokenStream tokenStr = new TokenStream(read);
        tokenStr.readCode();
        for(Tokens to : tokenStr)
        {
            tokens.add(to);
        }
        

        assertThat(tokens.size() ,equalTo(58));

        Tokens to = tokens.get(0);
        assertThat(TokenName.POUND, equalTo(to.getTokenType()));
        assertEquals("", to.getLocation());
        assertEquals("1", to.getLine());
        assertEquals("2", to.getColumn());

        to = tokens.get(4);
        assertThat(TokenName.NAMESPACE, equalTo(to.getTokenType()));
        assertEquals("", to.getLocation());
        assertEquals("3",  to.getLine());
        assertEquals("7", to.getColumn());



        
      

    

    
    System.out.println(tokens);

    }
    catch(FileNotFoundException e)
    {
        System.out.println(e.getMessage());
    }



    
}


}
