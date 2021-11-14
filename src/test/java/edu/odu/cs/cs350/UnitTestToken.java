package edu.odu.cs.cs350;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class UnitTestToken 
{
  
    Token to = new Token();

  

    @Test 
    public void testGetText()
    {
        String integ = "1234";
        String test = "#";
        to.setText(integ);
        assertThat(to.getText(), equalTo("1234"));
        assertThat(TokenName.OP_ADD, not("@"));
    }

    @Test
    public void testGetTokenType()
    {
        String test = "#";
        to.setText(test);
        assertThat(TokenName.OP_ADD, not("@"));
       // assertEquals(TokenName.POUND, equalTo('#');
        
    }
    @Test
    public void Token()
    {

        String integ = "1234";
        to.setText(integ);
        assertThat(to.getText(), equalTo("1234"));
    
    }

    
    @Test
    public void testSetNumOfTokens()
    {
        to.setNumOfTokens(10);
        assertThat(to.getNumOfTokens(),equalTo(10));
        assertFalse(to.getNumOfTokens() == 5);
    }

    @Test
    public void testgetNumOfTokens()
    {

       String source = "Let see < > , 34 ! ! ( )  count";
       Token te = new Token(source);
       assertThat(te.getNumOfTokens(), equalTo(0));
    }


    


    
}
