package edu.odu.cs.cs350;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class UnitTestToken {

   Token to = new Token();
    String input = "456/ # count4 KEYWORD  @   22&";
    Token t = new Token(input);

    @BeforeAll
    public void setUp() throws Exception 
    {
        
    }

    @Test
    public void Token()
    {


    }

    @Test
    public void testSetNumOfTokens()
    {
        assertThat(to.getNumOfTokens(),equalTo(0));
        assertFalse(to.getNumOfTokens() == 5);
    }

    @Test 
    public void testGetNumOfToken()
    {
        assertThat(t.getNumOfTokens(),equalTo("456/ # count4 KEYWORD  @   22&"));
    }


    
}
