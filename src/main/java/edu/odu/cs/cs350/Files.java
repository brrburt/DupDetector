package edu.odu.cs.cs350;

/*
//author Jose Umana 
//cs_juman004
*/
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files


public class Files {
	
	private File propertiesFile;
	private List<String> fileExtensions;
	private List<File> sourceFiles;
	
	private String path; 
	
	/* Constructors */
	
	/**
	 * Create Empty File object with no propertyFile, 
	 * empty fileExtensions String, and empty list of strings containing each source file path
	 */
	
	public Files() 
	{
		propertiesFile = new File("");
		fileExtensions = new ArrayList<String>();
		sourceFiles = new ArrayList<File>(); 
		
	}
	/**
	 * This constructor here is for that Command Line Class Compiles, need to meet and discuss
	 * 
	 */
	public Files(String path)
	{
		this.path = path;
	}
	
	/**
	 * Create a Parameterized File object that will be passed propFilepPath (Could be empty if no file exist) and list
	 * that will have all the paths to each source file. 
	 * Empty fileExtensions list 
	 */
	public Files(File pFile, List<File> sFiles )
	{
		propertiesFile = pFile;
		sourceFiles= sFiles; 
		fileExtensions = new ArrayList<String>(); 
	}
	/* Getters and Setters */
	public void setPropertyFile(String path)
	{
		propertiesFile = new File(path);
		
	}
	public void setfileExtensions (ArrayList<String> ext)
	{
		fileExtensions = ext; 
	}
	/** 
	 *  
	 */
	
	public File getpropertiesFile()
	{
		return propertiesFile;
	}
	
	public List<String> getFileExtensions()
	{
		return fileExtensions;
	}
	
	public List<File> getsourceFiles()
	{
		return sourceFiles; 
	}
	/**
	 * Read the Property File, See if file had a line that contains CppExtensions 
	 * If so store that line has string 
	 * Take string and split up into an array of strings 
	 * If the any of the strings begin with . then store in Extension List 
	 * This function can be extended to read the other Properties in the Properties File 
	 */
	
	public void ReadPropertiesFile() throws FileNotFoundException
	{
		Scanner scan = new Scanner(propertiesFile); /// more proper would be getPropertyFile instead of propertyFile
		String temp=""; 
		while (scan.hasNextLine())    /// issue in this while loop goes for ever 
		{
			if (scan.nextLine().contains("CppExtensions"));
			{
				temp = scan.nextLine();
			}
		}
		
		scan.close();
	
	String[] tokens = temp.split(" ");
	for (int i = 0; i < tokens.length; i++)
	{
		if (tokens[i].startsWith("."))
		{
			fileExtensions.add(tokens[i]);
		}
	}
	
	}
	public boolean equals(Object obj) 
	{
		if (obj.hashCode() == this.hashCode())
		{		
			return true;
		}
		
	   	return false;
	}
	public int hashCode() 
	{
		return path.hashCode() * 27 << 3;
	}
	
	@Override
	  public Object clone()  
	  {
	    return this;
	  }
	
}
