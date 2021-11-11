package edu.odu.cs.cs350;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Vector;


/*
 * @author cs_alee035
 * 
 */

public class UnitTestCommandLine {
	
	@Test
	public void testConstructor() {
		CommandLine cli = new CommandLine();
		Refactoring ref = new Refactoring();
		propertiesFile file = new propertiesFile();
		CountTokens tokens = new CountTokens();
		int suggestions = 5;
		
		assertTrue(cli.getSourceFiles().isEmpty());
		assertTrue(cli.getRefactoring().equals(ref));
		assertTrue(cli.getPropertiesFile().equals(file));
		assertThat(cli.getNSuggestions(), is(suggestions));
		assertTrue(cli.getTokenizer().equals(tokens));
		} 
	
	@Test
	public void testFindSourceFiles() throws IOException {
		// test containers
		Vector<File> testFiles = new Vector<File>();
		Vector<File> fakeFiles = new Vector<File>();
		Vector<Files> filesList = new Vector<Files>();
		
		File pFile = new File("src/test/java/edu/odu/cs/cs350/resources/unitTestCommandLine/properties.ini");
		propertiesFile propFile = new propertiesFile(pFile);
		propFile.readPropertyFile();
		
		// Create test directories and files lists
		String  dir = "src/test/java/edu/odu/cs/cs350/resources/unitTestCommandLine";
		String dir2 = dir + "/Headers";
		String dir3 = dir + "/Other";
		File testDir = new File(dir);
		File testDir2 = new File(dir2);
		File testDir3 = new File(dir3);
		testFiles.add(new File(testDir.getAbsolutePath() + "/source1.cpp"));
		testFiles.add(new File(testDir.getAbsolutePath() + "/source2.C"));
		testFiles.add(new File(testDir2.getAbsolutePath() + "/header1.h"));
		testFiles.add(new File(testDir2.getAbsolutePath() + "/header.H"));
		testFiles.add(new File(testDir3.getAbsolutePath() + "/other.hpp"));
		fakeFiles.add(new File(testDir3.getAbsolutePath() + "/fake.txt"));
		fakeFiles.add(new File(testDir.getAbsolutePath() + "/properties.ini"));
		fakeFiles.add(new File(testDir2.getAbsolutePath() + "/trickFile.cpp.o"));
		
		for( File file: testFiles) {
			filesList.add(new Files(file.getAbsolutePath()));
		}

		// run findSourceFiles and check if all files are found
		CommandLine cli = new CommandLine();
		cli.setPropertiesFile(propFile);
		
		cli.findSourceFiles(dir);
		for( Files file: filesList) {
			assertTrue(cli.getSourceFiles().contains(file));
		}
		// check size of container to see if extra files were added
		assertThat(cli.getSourceFiles().size(), is(5));
	}
	
	@Test
	public void testAddSourceFiles() {
		Files source1 = new Files("path1.cpp");
		Files source2 = new Files("path2.h");
		Files source3 = new Files("path3.hpp");
		CommandLine cli = new CommandLine();
		cli.addSourceFile(source1);
		
		assertTrue(cli.getSourceFiles().contains(source1));
		assertThat(cli.getSourceFiles().size(), is(1));
		
		cli.addSourceFile(source2);
		cli.addSourceFile(source3);
		assertTrue(cli.getSourceFiles().contains(source2));
		assertTrue(cli.getSourceFiles().contains(source3));
		assertThat(cli.getSourceFiles().size(), is(3));
		
		cli.addSourceFile(source3);
		assertThat(cli.getSourceFiles().size(), is(3));
	}
	
	@Test
	public void testSetPropertiesFile() {
		File pFile = new File("");
		propertiesFile propFile = new propertiesFile(pFile);
		
		CommandLine cli = new CommandLine();
		cli.setPropertiesFile(propFile);
		
		assertTrue(cli.getPropertiesFile().equals(propFile));
	}
	
	@Test
	public void testSetRefactor() {
		Refactoring testRefactor = new Refactoring();
		
		
		CommandLine cli = new CommandLine();
		cli.setRefactoring(testRefactor);
		
		assertTrue(cli.getRefactoring().equals(testRefactor));
	}
	
	@Test
	public void testSetTokenizer() {
		CountTokens tokens = new CountTokens();
		
		CommandLine cli = new CommandLine();
		cli.setTokenizer(tokens);
		
		assertTrue(cli.getTokenizer().equals(tokens));
	}
	
	@Test 
	public void testParseParameters() throws IOException {
		String[] args = {"8", "src/test/java/edu/odu/cs/cs350/resources/unitTestCommandLine/properties.ini",
		                 "src/test/java/edu/odu/cs/cs350/resources/newFakeSource.C", 
		                 "src/test/java/edu/odu/cs/cs350/resources/unitTestCommandLine"};
		File pFile = new File("src/test/java/edu/odu/cs/cs350/resources/unitTestCommandLine/properties.ini");
		propertiesFile propFile = new propertiesFile(pFile);
		propFile.readPropertyFile();
		String  dir = "src/test/java/edu/odu/cs/cs350/resources/unitTestCommandLine";
		String dir2 = dir + "/Headers";
		String dir3 = dir + "/Other";
		File testDir = new File(dir);
		File testDir2 = new File(dir2);
		File testDir3 = new File(dir3);
		
		Vector<File> testFiles = new Vector<File>();
		Vector<Files> filesList = new Vector<Files>();
		
		testFiles.add(new File(testDir.getAbsolutePath() + "/source1.cpp"));
		testFiles.add(new File(testDir.getAbsolutePath() + "/source2.C"));
		testFiles.add(new File(testDir2.getAbsolutePath() + "/header1.h"));
		testFiles.add(new File(testDir2.getAbsolutePath() + "/header.H"));
		testFiles.add(new File(testDir3.getAbsolutePath() + "/other.hpp"));
		testFiles.add(new File(args[2]));
		
		for( File file: testFiles) {
			filesList.add(new Files(file.getAbsolutePath()));
		}
		
		CommandLine cli = new CommandLine();
		
		cli.parseParameters(args);
		
		assertThat(cli.getNSuggestions(), is(8));
		assertTrue(cli.getPropertiesFile().equals(propFile));
		for( Files file: filesList) {
			assertTrue(cli.getSourceFiles().contains(file));
		}
		
	}
}
