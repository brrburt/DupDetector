package edu.odu.cs.cs350;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UnitTestCountTokens 
{
    
    final private String words = "this is a test";
    CountTokens ct = new CountTokens();

    @BeforeEach
    public void setUp() throws Exception {
    }

    @Test
    public void CountTokens()
    {
        assertThat(ct.getText(), equalTo("Let's see if we can count tokens"));
        assertThat(ct.getText(), not(""));
        assertThat(ct.getNumOfTokens(),equalTo(0));
        
    }

   
    @Test
    public void testSetText() 
    {
        CountTokens c = new CountTokens();

        ct.setText(words);
        assertThat(ct.getText(), equalTo(words));
        assertFalse(c.equals(ct));
        assertThat(ct.getText().toString(), equalTo(words));
        assertTrue(ct.toString().contains(words));

    }

    @Test
    public void testSetNumOfTokens()
    {
        assertThat(ct.getNumOfTokens(),equalTo(0));
        assertFalse(ct.getNumOfTokens() == 5);
    }

    @Test
    public void Counting() 
    {
        assertThat(ct.getText(), equalTo("Let's see if we can count tokens"));
    }

    @Test
    public void testClone()
    {
        CountTokens co = new CountTokens();

        assertThat(co.getNumOfTokens(), equalTo(0));
        assertThat(ct, equalTo(co));
        assertThat(co.toString(), equalTo(co.toString()));

    }

}
