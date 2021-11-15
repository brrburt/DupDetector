package edu.odu.cs.cs350;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/*
 * @author cs_juman004
 * 
 */

public class propertiesFile 
{
	private File propertiesFile; 
	private List<String> fileExtensions;
	private String minSequnces;
	private String maxSubs;
	
	/* Constructors */
	
	/**
	 * Create Empty propertiesFile object with an empty property file, empty fileExt list,
	 * empty minSeq string, and empty maxSub string. Keep in mind that if this constructor and there exist no file "properties.ini" 
	 * then one will be created in the project directory 
	 * 
	 */
	public propertiesFile() 
	{
		propertiesFile = new File("properties.ini");
		fileExtensions = new ArrayList<String>();
		minSequnces = "";
		maxSubs = ""; 
	}
	
	 /**
     * Creates a properties file with a userDefined property file, empty fileExt list, 
     * empty minSeq string, and empty maxSub string 
     * 
     * @param pFile property File (this input should be accesible from CL ADT)
     *    
     */
	public propertiesFile(File pFile)
	{
		propertiesFile = pFile;
		fileExtensions = new ArrayList<String>();
		minSequnces = "";
		maxSubs = "";
	}
	
	/**
     * Retrieve the property file of this object, as type File 
     * 
     * @return propertyFile
     */
	
			/* Getters */
	public File getpropertiesFile()
	{
		return propertiesFile;
	}
	
	/**
     * Retrieve the File Extensions of PropFile , as a List of Stringss
     * 
     * @return fileExtensions 
     */
	public List<String> getFileExtensions()
	{
		return fileExtensions;
	}
	
	/**
     * Retrieve the number of MinSequces
     * 
     * @return minSequnces as a string 
     */
	public String getminSequnces()
	{
		return minSequnces;
		
	}
	
	/**
     * Retrieve the number of MaxSubs
     * 
     * @return MaxSubs as a string  
     */
	public String getmaxSubs()
	{
		return maxSubs;
	}
	
			/* Setters */
	
	/**
     * Set a new propertyFile if necessary
     * 
     * @param path the path to a Property File
     *  
     */
	
	
	public void setPropertyFile(String path)
	{
		propertiesFile = new File(path);
		
	}
	
	/**
     * Set a new fileExt list if necessary
     * 
     * @param ext a list of strings 
     *  
     */
	
	
	public void setfileExtensions (List<String> ext)
	{
		fileExtensions = ext; 
	}
	
	/**
     * Set minSequnces 
     * 
     * @param min minSequnces String 
     *  
     */
	
	public void setminSequnces(String min)
	{
		minSequnces = min;
		
	}
	/**
     * Set maxDubuplicats  
     * 
     * @param max maxDubuplicate string 
     *  
     */
	public void setmaxDubs(String max)
	{
		maxSubs = max;
		
	}
		/* General Functions */
	/**
     * Adds a file extension to the list of File Extension 
     * 
     * @param extension an extension expressed as a string 
     *  
     */
	public void addExt (String extension)
	{
		if (!(fileExtensions.contains(extension)))
		fileExtensions.add(extension);
		
	}
	/**
     * Reads the Property File. Looks for the allowed Properties: CppExtensions,MinSequenceLength, and MaxSubstitutions. 
     * Ensures that the property is in the file and contains a value that is not empty. If so get those values and update ADT 
     * Else update data members to corresponding defaults values.
     *  
     * 
     * @throws IOException If File cannot be read
     *  
     */
	
	public void readPropertyFile() throws IOException 
	{
		String extKey = "CppExtensions";
		String minKey = "MinSequenceLength";
		String maxkey = "MaxSubstitutions";
		
		String expDef1 = ".cpp";
		String expDef2 = ".h";
		String minDef = "10";
		String maxDef = "8";
		
		Properties dupProp = new Properties();
		dupProp.load(new FileInputStream( getpropertiesFile().getAbsolutePath()));
			if (dupProp.getProperty(extKey) != null && !(dupProp.getProperty(extKey).isEmpty()))
			{
				String temp = dupProp.getProperty(extKey);
				if (temp.contains(","));
				{
					List<String> j= new ArrayList<String>(Arrays.asList(temp.split(",")));
					setfileExtensions(j);
				}
				if (!(temp.contains(",")))
				{
					addExt (temp);
				}
			}
			else 
			{
				addExt(expDef1);
				addExt(expDef2);
			}
			if (dupProp.getProperty(minKey) != null && !(dupProp.getProperty(minKey).isEmpty()))
			{
				String temp = dupProp.getProperty(minKey).strip();
				if(Integer.parseInt(temp) >= 0)
				{
				setminSequnces(temp);
				}
				else 
				{
					setminSequnces(minDef);
				}
			}
			else 
			{
				setminSequnces(minDef);
			}
			if (dupProp.getProperty(maxkey) != null && !(dupProp.getProperty(maxkey).isEmpty()))
			{
				String temp = dupProp.getProperty(maxkey).strip();
				if(Integer.parseInt(temp) >= 0)
				{
				setmaxDubs(temp);
				}
				else 
				{
					setmaxDubs(maxDef);
				}
			}
			else 
			{
				setmaxDubs(maxDef);
			}
	}
	
	/**
	 * Returns the hashCode for this propFile
	 * 
	 * @return hashCode integer for propFile
	 */
	@Override
	public int hashCode() {
		return Objects.hash(fileExtensions, maxSubs, minSequnces, propertiesFile);
	}
	
	/**
	 *  Compares propFile objects (There shouldn't be another propFile object)
	 *  
	 *  
	 *  @return true if objects are equivalent
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		propertiesFile other = (propertiesFile) obj;
		return Objects.equals(fileExtensions, other.fileExtensions) && Objects.equals(maxSubs, other.maxSubs)
				&& Objects.equals(minSequnces, other.minSequnces)
				&& Objects.equals(propertiesFile, other.propertiesFile);
	}
	
	/**
	 * Renders the propFile object as a string containing all fields
	 * 
	 */
	@Override
	public String toString() {
		return "propertiesFile [propertiesFile=" + propertiesFile + ", fileExtensions=" + fileExtensions
				+ ", minSequnces=" + minSequnces + ", maxSequnces=" + maxSubs + "]";
	}
	
	
	
	
}
