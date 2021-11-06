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
	/*
	@Test
	public void testConstructor() {
		CommandLine cli = new CommandLine();
		Refactoring ref = new Refactoring();
		Files file = new Files();
		
		assertTrue(cli.getSourceFiles().isEmpty());
		assertTrue(cli.getRefactor().equals(ref));
		assertThat(cli.getPropertiesFile(), is(file));
	} */
	
	@Test
	public void testFindSourceFiles() throws IOException {
		// test containers
		Vector<File> testFiles = new Vector<File>();
		Vector<File> fakeFiles = new Vector<File>();
		Vector<Files> filesList = new Vector<Files>();
		
		FilesStub stubPFile = new FilesStub();
		
		
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
		cli.setPropertiesFile(stubPFile);
		
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
}