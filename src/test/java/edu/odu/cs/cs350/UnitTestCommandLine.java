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
import java.util.Vector;


/*
 * @author cs_alee035
 * 
 */

public class UnitTestCommandLine {
	
	@Test
	public void testFindSourceFiles() throws IOException {
		// test containers
		Vector<File> testFiles = new Vector<File>();
		Vector<File> fakeFiles = new Vector<File>();
		Vector<Files> filesList = new Vector<Files>();
		
		// Create test directories and files
		String  dir = "unitTestCommandLine";
		String dir2 = dir + "/Headers";
		String dir3 = dir + "/Other";
		File testDir = new File(dir);
		File testDir2 = new File(dir2);
		File testDir3 = new File(dir3);
		testFiles.add(new File(testDir.getAbsolutePath() + "/source1.cpp"));
		testFiles.add(new File(testDir.getAbsolutePath() + "/source2.c"));
		testFiles.add(new File(testDir2.getAbsolutePath() + "/header1.h"));
		testFiles.add(new File(testDir2.getAbsolutePath() + "/header.H"));
		testFiles.add(new File(testDir3.getAbsolutePath() + "/other.hpp"));
		fakeFiles.add(new File(testDir3.getAbsolutePath() + "/fake.txt"));
		fakeFiles.add(new File(testDir.getAbsolutePath() + "/properties.ini"));
		fakeFiles.add(new File(testDir2.getAbsolutePath() + "/trickFile.cpp.o"));
		testDir.mkdir();
		testDir2.mkdir();
		testDir3.mkdir();
		for( File file: testFiles) {
			file.createNewFile();
			filesList.add(new Files(file.getAbsolutePath()));
		}
		for( File file: fakeFiles) {
			file.createNewFile();
		}
		// run findSourceFiles and check if all files are found
		CommandLine cli = new CommandLine();
		cli.findSourceFiles(dir);
		for( Files file: filesList) {
			assertTrue(cli.getSourceFiles().contains(file));
		}
		// check size of container to see if extra files were added
		assertThat(cli.getSourceFiles().size(), is(5));
		
		// sanitize 
		for(File file: testFiles) {
			file.delete();
		}
		for(File file: fakeFiles) {
			file.delete();
		}
		testDir2.delete();
		testDir3.delete();
		testDir.delete();
		
	}
}