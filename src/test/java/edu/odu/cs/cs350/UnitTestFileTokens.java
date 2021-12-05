package edu.odu.cs.cs350;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.IOException;
import java.util.Vector;


public class UnitTestFileTokens {
	
	// Generate objects
	CommandLine cli = new CommandLine();
	CountTokens counter = new CountTokens();
	Files fileObj = new Files();
	Vector<String> files = new Vector<String>();
	
	// Change output streams for CLI testing
	// Details for this were found at: https://stackoverflow.com/a/1119559
	private final ByteArrayOutputStream output = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	
	@Test
	public void testFileTokens() throws IOException {
		// Get files (On UNIX or Windows) from DupDetector/src/test/java/edu/odu/cs/cs350/examples"
		String dir 	= "src" + File.separator 
					+ "test" + File.separator 
					+ "java" + File.separator 
					+ "edu" + File.separator 
					+ "odu" + File.separator 
					+ "cs" + File.separator 
					+ "cs350" + File.separator 
					+ "examples";
		
		File exampleDir = new File(dir);
		
		files.add(exampleDir.getAbsolutePath() + File.separator + "example.h");
		files.add(exampleDir.getAbsolutePath() + File.separator + "example.cpp");
		files.add(exampleDir.getAbsolutePath() + File.separator + "main.cpp");
		
		// Sort strings in files (NULL means 'natural ordering', which will sort this alphabetically)
		files.sort(null);
		
		
		cli.findSourceFiles(dir);
		
		for(File file: cli.getSourceFiles()) {
			assertTrue(files.contains(file));
		}
		
		// Test for absolute path
		for(File file: cli.getSourceFiles()) {
			assertTrue(file.getPath().startsWith(":\\", 1) || file.getPath().startsWith("/"));
		}
		
		// Test output
		System.setOut(new PrintStream(output));
		
		String expected = "Files scanned:\n";
		for(String file: files) {
			expected += "    " + file + ", ";
			counter.setText(fileObj.contentToString(file));
			counter.Counting();
			expected += counter.getNumOfTokens() + "\n";	
		}
		
		cli.printSuggestions();
		
		assertThat(output.toString(), equalTo(expected));
		
		System.setOut(new PrintStream(originalOut));
	}
	
}
