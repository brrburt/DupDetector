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
import java.util.Collections;
import java.util.Vector;
import java.io.PrintStream;
import java.util.Scanner;



/*
 * @author cs_alee035
 * 
 */

public class UnitTestCommandLine {
	
	@Test
	public void testConstructor() {
		CommandLine cli = new CommandLine();
		ArrayList<Refactoring> ref = new ArrayList<Refactoring>();
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
		Vector<File> filesList = new Vector<File>();
		
		File pFile = new File("src/test/resources/unitTestCommandLine/properties.ini");
		propertiesFile propFile = new propertiesFile(pFile);
		propFile.readPropertyFile();
		
		// Create test directories and files lists
		String  dir = "src/test/resources/unitTestCommandLine";
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
			filesList.add(file);
		}

		// run findSourceFiles and check if all files are found
		CommandLine cli = new CommandLine();
		cli.setPropertiesFile(propFile);
		
		cli.findSourceFiles(dir);
		for( File file: filesList) {
			assertTrue(cli.getSourceFiles().contains(file));
		}
		// check size of container to see if extra files were added
		assertThat(cli.getSourceFiles().size(), is(5));
	}
	
	@Test
	public void testAddSourceFiles() {
		File source1 = new File("path1.cpp");
		File source2 = new File("path2.h");
		File source3 = new File("path3.hpp");
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
	public void testAddRefactor() {
		File testFile = new File("src/resources/newFakeSource.C");
		Refactoring testRefactor = new Refactoring(testFile);
		
		
		CommandLine cli = new CommandLine();
		cli.addRefactoring(testRefactor);
		
		assertTrue(cli.getRefactoring().contains(testRefactor));
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
		String[] args = {"8", "src/test/resources/unitTestCommandLine/properties.ini",
		                 "src/test/resources/newFakeSource.C", 
		                 "src/test/resources/unitTestCommandLine"};
		File pFile = new File("src/test/resources/unitTestCommandLine/properties.ini");
		propertiesFile propFile = new propertiesFile(pFile);
		propFile.readPropertyFile();
		String  dir = "src/test/resources/unitTestCommandLine";
		String dir2 = dir + "/Headers";
		String dir3 = dir + "/Other";
		File testDir = new File(dir);
		File testDir2 = new File(dir2);
		File testDir3 = new File(dir3);
		File extraSource = new File(args[2]);
		
		Vector<File> testFiles = new Vector<File>();
		
		testFiles.add(new File(testDir.getAbsolutePath() + "/source1.cpp"));
		testFiles.add(new File(testDir.getAbsolutePath() + "/source2.C"));
		testFiles.add(new File(testDir2.getAbsolutePath() + "/header1.h"));
		testFiles.add(new File(testDir2.getAbsolutePath() + "/header.H"));
		testFiles.add(new File(testDir3.getAbsolutePath() + "/other.hpp"));
		testFiles.add((extraSource.getAbsoluteFile()));
		
		CommandLine cli = new CommandLine();
		
		cli.parseParameters(args);
		
		assertThat(cli.getNSuggestions(), is(8));
		assertTrue(cli.getPropertiesFile().equals(propFile));
		for( File file: testFiles) {
			assertTrue(cli.getSourceFiles().contains(file));
		}
		assertThat(cli.getSourceFiles().size(), is(6));
	}
	
	@Test
	/**
	 * Tests the first section of the display.
	 * @throws IOException
	 */
	public void testDisplay() throws IOException {
		File expected = new File("src/test/resources/expectedOutput.txt");
		expected.createNewFile();
		PrintStream oStream = new PrintStream(expected);
		System.setOut(oStream);
		String[] args = {"8", "src/test/resources/unitTestCommandLine/properties.ini",
                "src/test/resources/newFakeSource.C", 
                "src/test/resources/unitTestCommandLine"}; 
		CommandLine cli = new CommandLine();
		cli.parseParameters(args);
		// Generate Refactoring suggestion for use with display.
		RefactoringStub rStub = new RefactoringStub(cli.getSourceFiles().get(0));
		RefactoringStub rStub2 = new RefactoringStub(cli.getSourceFiles().get(1));
		RefactoringStub rStub3 = new RefactoringStub(cli.getSourceFiles().get(2));
		RefactoringStub rStub4 = new RefactoringStub(cli.getSourceFiles().get(3));
		RefactoringStub rStub5 = new RefactoringStub(cli.getSourceFiles().get(4));
		RefactoringStub rStub6 = new RefactoringStub(cli.getSourceFiles().get(5));
		List<RefactoringStub> rStubs = new ArrayList<RefactoringStub>();
		rStubs.add(rStub);
		rStubs.add(rStub2);
		rStubs.add(rStub3);
		rStubs.add(rStub4);
		rStubs.add(rStub5);
		rStubs.add(rStub6);
		for( RefactoringStub rs: rStubs) {
			rs.setTokenCount(0);
			rs.setValidSuggestion();
		}
		cli.addRefactoring(rStub);
		cli.addRefactoring(rStub2);
		cli.addRefactoring(rStub3);
		cli.addRefactoring(rStub4);
		cli.addRefactoring(rStub5);
		cli.addRefactoring(rStub6);
		
		// Generate expectedOutput.txt contents
		Vector<File> testFiles = new Vector<File>();
		String  dir = "src/test/resources/unitTestCommandLine";
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
		testFiles.add(new File("src/test/resources/newFakeSource.C"));
		List<String> absPaths = new ArrayList<String>();
		for( File file: testFiles) {
			absPaths.add(file.getAbsolutePath());
		}
		absPaths.sort(null);
		System.out.println("Files Scanned:");
		for( RefactoringStub rs: rStubs) {
			System.out.println("\t" + rs.getSourceFile().getAbsolutePath()
								+ ", " + rs.getTokenCount());
		}
		System.out.println();
		for( RefactoringStub rs: rStubs) {
			System.out.println("Opportunity " + rs.getImprovement() +
					", " + rs.getRefactorTokens() + " tokens");
			System.out.println(rs.getSourceFile().getAbsolutePath() + ":" 
					 + rs.getRefactorRow() + ":" 
					 + rs.getRefactorColumn());
		}
		
		// Set up output stream to file
		File output = new File("src/test/resources/displayOutput.txt");
		output.createNewFile();
		PrintStream oStream2 = new PrintStream(output);
		System.setOut(oStream2);
		cli.display();
		
		// Compare expectedOutput.txt and displayOutput.txt 
		boolean filesEqual = true;
		Scanner scannerOutput = new Scanner(output);
		Scanner scannerExpected = new Scanner(expected);
		int count = 0;
		while (scannerExpected.hasNext() && count < 5) {
	        String str1 = scannerOutput.next();
	        String str2 = scannerExpected.next();
	        count++;
	        if (!str1.equals(str2))
	            filesEqual = false;
	    }
		scannerOutput.close();
		scannerExpected.close();
		//output.delete();
		//expected.delete();
		
		assertTrue(filesEqual);
	} 
	
	@Test
	public void testDisplay2() {
		boolean pass = true;
		assertTrue(pass);
	}
}
