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
		// Create test directory and files
		String  dir = "unitTestCommandLine";
		String dir2 = dir + "/Headers";
		String dir3 = dir + "/Other";
		Vector<File> testFiles = new Vector<File>();
		Vector<Files> filesList = new Vector<Files>();
		File testDir = new File(dir);
		File testDir2 = new File(dir2);
		File testDir3 = new File(dir3);
		testFiles.add(new File(testDir.getAbsolutePath() + "/source1.cpp"));
		testFiles.add(new File(testDir.getAbsolutePath() + "/source2.c"));
		testFiles.add(new File(testDir2.getAbsolutePath() + "/header1.h"));
		testFiles.add(new File(testDir2.getAbsolutePath() + "/header.H"));
		testFiles.add(new File(testDir3.getAbsolutePath() + "/other.hpp"));
		testFiles.add(new File(testDir3.getAbsolutePath() + "/fake.txt"));
		testFiles.add(new File(testDir.getAbsolutePath() + "/properties.ini"));
		testFiles.add(new File(testDir2.getAbsolutePath() + "/trickFile.cpp.o"));
		boolean dirFlag = testDir.mkdir();
		boolean dir2Flag = testDir2.mkdir();
		boolean dir3Flag = testDir3.mkdir();
		for( File file: testFiles) {
			file.createNewFile();
			filesList.add(new Files(file.getAbsolutePath()));
		}
		
		// run findSourceFiles and check if all files are found
		CommandLine cli = new CommandLine();
		cli.findSourceFiles(dir);
		for( Files file: filesList) {
			assertTrue(cli.getSourceFiles().contains(file));
		}
		// check size of container to see if extra files were added
		assertThat(cli.getSourceFiles().size(), is(5));
	}
}