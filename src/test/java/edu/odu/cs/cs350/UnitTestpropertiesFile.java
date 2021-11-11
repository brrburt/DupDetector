package edu.odu.cs.cs350;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import java.io.File;  // Import the File class

public class UnitTestpropertiesFile {
	
	@Test 
	public void testpropertiesFile() throws IOException 
	{
		propertiesFile p = new propertiesFile();
		File temp = p.getpropertiesFile();
		assertThat( p.getFileExtensions().size(), is(0));
		assertTrue(temp.createNewFile());
		assertThat(temp.getName(),is("properties.ini"));
		assertTrue(temp.exists());
		assertThat(p.getFileExtensions().size(), is(0));
		assertTrue(p.getminSequnces().isEmpty());
		assertTrue(p.getmaxSubs().isEmpty());
		temp.delete();
		
	}
	@Test 
	public void testParapropertiesFile() throws IOException 
	{
		
		String file_name = "src/test/java/edu/odu/cs/cs350/resources/properties.ini";
		File propFile = new File (file_name);
		propertiesFile p = new propertiesFile(propFile);
		assertThat( p.getFileExtensions().size(), is(0));
		assertTrue(p.getpropertiesFile().exists());
		assertThat(p.getFileExtensions().size(), is(0));
		assertTrue(p.getminSequnces().isEmpty());
		assertTrue(p.getmaxSubs().isEmpty());
	}
	@Test 
	public void testReadProp() throws IOException
	{
		
		String emptyfile_name = "src/test/java/edu/odu/cs/cs350/resources/emptyproperties.ini";
		String file_name = "src/test/java/edu/odu/cs/cs350/resources/properties.ini";
		
		File emptypropFile = new File (emptyfile_name);
		File propFile = new File (file_name);
		
		
		propertiesFile p = new propertiesFile(emptypropFile);
		assertTrue(p.getpropertiesFile().exists());
		p.readPropertyFile();
		assertThat(p.getFileExtensions().get(0),is(".cpp"));
		assertThat(p.getFileExtensions().get(1),is(".h"));
		assertThat(p.getminSequnces(),is("10"));
		assertThat(p.getmaxSubs(),is("8"));
		
		propertiesFile q = new propertiesFile(propFile);
		assertTrue(q.getpropertiesFile().exists());
		q.readPropertyFile();
		assertThat(q.getFileExtensions().size(),is(4));
		assertThat(q.getminSequnces(),is("4"));
		assertThat(q.getmaxSubs(),is("8"));
		
		
		
	}
	
	
	

}
