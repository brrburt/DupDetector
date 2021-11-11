package edu.odu.cs.cs350;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import java.io.File;  // Import the File class
/*
 * @author cs_juman004
 * 
 */

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
	public void testaddExt()
	{
		String file_name = "src/test/java/edu/odu/cs/cs350/resources/properties.ini";
		File propFile = new File (file_name);
		propertiesFile p = new propertiesFile(propFile);
		assertThat( p.getFileExtensions().size(), is(0));
		p.addExt("test1");
		p.addExt("test2");
		assertThat (p.getFileExtensions().size(),is(2));
		assertThat(p.getFileExtensions().get(0),is("test1"));
		assertThat(p.getFileExtensions().get(1),is("test2"));
	}
	@Test 
	public void testReadProp() throws IOException
	{
		
		String emptyfile_name = "src/test/java/edu/odu/cs/cs350/resources/emptyproperties.ini";
		String file_name = "src/test/java/edu/odu/cs/cs350/resources/properties.ini";
		String testCase1 = "src/test/java/edu/odu/cs/cs350/resources/testcase1.ini";
		String testCase2 = "src/test/java/edu/odu/cs/cs350/resources/testcase2.ini";
		String testCase3 = "src/test/java/edu/odu/cs/cs350/resources/testcase3.ini";
		
		File emptypropFile = new File (emptyfile_name);
		File propFile = new File (file_name);
		File test_Case1 = new File (testCase1);
		File test_Case2 = new File (testCase2);
		File test_Case3 = new File (testCase3);
		
		
		// Testing Empty Properties File, Should set Values to Default
		propertiesFile p = new propertiesFile(emptypropFile);
		assertTrue(p.getpropertiesFile().exists());
		p.readPropertyFile();
		assertThat(p.getFileExtensions().get(0),is(".cpp"));
		assertThat(p.getFileExtensions().get(1),is(".h"));
		assertThat(p.getminSequnces(),is("10"));
		assertThat(p.getmaxSubs(),is("8"));
		
		// Testing Properties File with all Properties filled out 
		propertiesFile q = new propertiesFile(propFile);
		assertTrue(q.getpropertiesFile().exists());
		q.readPropertyFile();
		assertThat(q.getFileExtensions().size(),is(4));
		assertThat(q.getminSequnces(),is("4"));
		assertThat(q.getmaxSubs(),is("8"));
		
		// Testing Properties File with CppExtension keyword but empty(Defaults), MinSequnces is negative(Defaults 10), and No MaxSub (Defaults 8)
		propertiesFile x = new propertiesFile(test_Case1);
		assertTrue(x.getpropertiesFile().exists());
		x.readPropertyFile();
		assertThat(x.getFileExtensions().get(0),is(".cpp"));
		assertThat(x.getFileExtensions().get(1),is(".h"));
		assertThat(x.getminSequnces(),is("10"));
		assertThat(x.getmaxSubs(),is("8"));
		
		//Testing Prop File with list of Extensions, MinSeq keyword present but empty(Defaults), MaxSub is negative (Defaults) 
		propertiesFile y = new propertiesFile(test_Case2);
		assertTrue(y.getpropertiesFile().exists());
		y.readPropertyFile();
		assertThat(y.getFileExtensions().get(0),is(".java"));
		assertThat(y.getFileExtensions().get(1),is(".h"));
		assertThat(y.getFileExtensions().get(2),is(".cpp"));
		assertThat(y.getFileExtensions().get(3),is(".bmx"));
		assertThat(y.getFileExtensions().get(4),is(".bsv"));
		assertThat(y.getFileExtensions().get(5),is(".boo"));
		assertThat(y.getFileExtensions().size(),is(6));
		assertThat(y.getminSequnces(),is("10"));
		assertThat(y.getmaxSubs(),is("8"));
		
		//Testing Prop 	File with no CppExtensionKeyWord(Defaults), MinSequence is 9, MaxSub Keyword Present(Defaults) 
		propertiesFile z = new propertiesFile(test_Case3);
		assertTrue(z.getpropertiesFile().exists());
		z.readPropertyFile();
		assertThat(z.getFileExtensions().get(0),is(".cpp"));
		assertThat(z.getFileExtensions().get(1),is(".h"));
		assertThat(z.getminSequnces(),is("9"));
		assertThat(z.getmaxSubs(),is("8"));
	}
	
	
	

}
