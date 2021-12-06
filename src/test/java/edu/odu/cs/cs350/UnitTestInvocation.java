package edu.odu.cs.cs350;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.IOException;
import java.util.Vector;

public class UnitTestInvocation extends DupDetector {
	// Generate objects
	CommandLine cli = new CommandLine();
	
	// Change output streams
	private final ByteArrayOutputStream output = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	String[] arg1 = {"-h"};
	String[] arg2 = {"--help"};
	String[] arg3 = {"-h", "12345", "Quick", "Brown", "Fox"};
	String[] arg4 = {"-help"};
	String[] arg5 = {"forty", "two"};
	String[] arg6 = {"42"};
	String[] arg7 = {"10", "10"};
	
	@BeforeEach
	public void setOutputStream()
	{
		System.setOut(new PrintStream(output));
	}
	
	@Test
	public void testTerseHelpFlag()
	{			
		// Test '-h' flag
		try{
			main(arg1);
			assertTrue(output.toString().contains("Team Project T7-1"));
			assertFalse(output.toString().contains("None or too few arguments"));
		}
		catch(Exception e) {
			fail("Exception thrown");
		}
	}
	
	@Test
	public void testVerboseHelpFlag()
	{
		// Test '--help' flag
		try {
			main(arg2);
			assertTrue(output.toString().contains("Team Project T7-1"));
			assertFalse(output.toString().contains("None or too few arguments"));
		}
		catch(Exception e) {
			fail("Exception thrown");
		}
	}
	
	@Test
	public void testMultipleHelpFlag()
	{
		/**
		 * Test help flag when followed by other parameters
		 * The help flag should take precedence over any other command line arguments
		 */
		try {
			main(arg3);
			assertTrue(output.toString().contains("Team Project T7-1"));
			assertFalse(output.toString().contains("None or too few arguments"));
		}
		catch(Exception e) {
			fail("Exception thrown");
		}
	}
	
	@Test 
	public void testParameterCount() 
	{
		// Test output when less than 2 paramaters are given
		try {
			main(arg4);
			assertTrue(output.toString().contains("None or too few arguments"));
			assertFalse(output.toString().contains("Team Project"));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testIntegerArgument()
	{
		// Test output when args[0] is does not represent a number
		try {
			main(arg5);
			assertTrue(output.toString().contains("nSuggestions must be an integer"));
			assertFalse(output.toString().contains("None or too few arguments"));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testNSuggestions() throws IOException
	{
		// Test that nSuggestions parameter from invocation is stored
		
		CommandLine cli = new CommandLine();
		cli.parseParameters(arg6);
		assertThat(cli.getNSuggestions(), equalTo(42));
	}
	
	@Test
	public void testPaths()
	{
		// Test that the second parameter given through the commandline is a string
		try {
			main(arg7);
			assertTrue(output.toString().contains("paths must be strings"));
			assertFalse(output.toString().contains("None or too few arguments"));
		}
		catch(Exception e) {
			fail("Exception thrown");
		}
	}
	
	@AfterEach
	public void resetOutputStream()
	{
		System.setOut(new PrintStream(originalOut));
	}
	
}