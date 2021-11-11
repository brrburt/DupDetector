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

public class propertiesFile 
{
	private File propertiesFile; 
	private List<String> fileExtensions;
	private String minSequnces;
	private String maxSubs;
	
	public propertiesFile() 
	{
		propertiesFile = new File("properties.ini");
		fileExtensions = new ArrayList<String>();
		minSequnces = "";
		maxSubs = ""; 
	}
	public propertiesFile(File pFile)
	{
		propertiesFile = pFile;
		fileExtensions = new ArrayList<String>();
		minSequnces = "";
		maxSubs = "";
	}
	public File getpropertiesFile()
	{
		return propertiesFile;
	}
	public List<String> getFileExtensions()
	{
		return fileExtensions;
	}
	public String getminSequnces()
	{
		return minSequnces;
		
	}
	public String getmaxSubs()
	{
		return maxSubs;
	}
	
	public void setPropertyFile(String path)
	{
		propertiesFile = new File(path);
		
	}
	// used to be ArrayList
	public void setfileExtensions (List<String> ext)
	{
		fileExtensions = ext; 
	}
	public void setminSequnces(String min)
	{
		minSequnces = min;
		
	}
	public void setmaxDubs(String max)
	{
		maxSubs = max;
		
	}
	public void addExt (String extension)
	{
		if (!(fileExtensions.contains(extension)))
		fileExtensions.add(extension);
		
	}
	
	
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
			}
			else 
			{
				setmaxDubs(maxDef);
			}
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(fileExtensions, maxSubs, minSequnces, propertiesFile);
	}
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
	@Override
	public String toString() {
		return "propertiesFile [propertiesFile=" + propertiesFile + ", fileExtensions=" + fileExtensions
				+ ", minSequnces=" + minSequnces + ", maxSequnces=" + maxSubs + "]";
	}
	
	
	
	
}
