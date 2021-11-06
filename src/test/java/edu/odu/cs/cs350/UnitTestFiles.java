package edu.odu.cs.cs350;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files



public class UnitTestFiles 
{

	@Test 
	public void testFiles()
	{
		Files p = new Files();
		assertThat( p.getFileExtensions().size(), is(0));
		assertFalse(p.getpropertiesFile().exists());
		assertTrue(p.getsourceFiles().isEmpty());
	}
	@Test
	public void testParaFiles()
	{
		
		String propFile = "properties.in"; /// this input(prop file) would come from commmand line 
		String source1 = "Random.cpp";
		String source2 = "Random.h";
		String source3 = "Util.cpp";
		String source4 = "Util.h";
		File pFile = new File (propFile);  
		File sFile1 = new File (source1); 
		File sFile2 = new File (source2); 
		File sFile3 = new File (source3); 
		File sFile4 = new File (source4); 
		List <File>sourceFiles = new ArrayList<File>();
		sourceFiles.add(sFile1);
		sourceFiles.add(sFile2);
		sourceFiles.add(sFile3);
		sourceFiles.add(sFile4);
		Files p = new Files(pFile,sourceFiles); 
		assertThat( p.getFileExtensions().size(), is(0));
		assertThat(p.getpropertiesFile().getName(),is(propFile));
		assertThat(p.getsourceFiles().size(),is(4));
	}
	@Test 
	public void testReadPropertiesFile() throws FileNotFoundException
	{
		File pFile = new File("src/test/resources/properties.ini");
		String path = pFile.getAbsolutePath();
		List <File>sourceFiles = new ArrayList<File>();
		//assertTrue (pFile.exists());
		Files p = new Files(pFile,sourceFiles); 
		//assertTrue(p.getpropertiesFile().canRead());
		//assertTrue(p.getpropertiesFile().equals(pFile));
		p.ReadPropertiesFile();   /*issue here */
	}

	
	
	

}
