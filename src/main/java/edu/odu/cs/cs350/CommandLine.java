package edu.odu.cs.cs350;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

/*
// @author cs_alee035
//
*/

public class CommandLine {
	
	private List<File> sourceFiles;
	private propertiesFile pFile;
	private List<Refactoring> refactor;
	private CountTokens tokenizer;
	private int nSuggestions;
	
	/* Constructors */
	
	/**
	 * Create Empty CommandLine object with no source files, 
	 * properties file, or Refactoring object
	 */
	CommandLine() {
		sourceFiles = new ArrayList<File>();
		pFile = new propertiesFile();
		refactor = new ArrayList<Refactoring>();
		tokenizer = new CountTokens();
		nSuggestions = 5;
	}
	
	/* Getters and Setters */
	
	/**
	 * returns a list of source files
	 * @return sourceFiles list of sourceFiles
	 */
	public List<File> getSourceFiles() {
		return sourceFiles;
	}
	
	/**
	 * Add source file to list if not already accounted for
	 * 
	 * @param file source file to add to sourceFiles
	 * 
	 * new source file is added to sourceFiles
	 */
	public void addSourceFile(File file) {
		if (!(sourceFiles.contains(file))) {
			sourceFiles.add(file);
		}
	}
	
	/**
	 *  retrieve properties file
	 * @return pFile the properties file 
	 */
	public propertiesFile getPropertiesFile() {
		return pFile;
	}
	
	/**
	 *  set user provided properties file as pFile
	 *  
	 *  set pFile as properties file
	 */
	public void setPropertiesFile(propertiesFile propFile) {
		pFile = propFile;
	}
	
	/**
	 * retrieve the Refactoring object
	 * @return refactor Refactoring object
	 */
	public List<Refactoring> getRefactoring( ) {
		return refactor;
	}
	
	public void addRefactoring(Refactoring refactoring) {
		//if (!(refactor.contains(refactoring))) {
			refactor.add(refactoring);
		//}
		
		//refactor.sort(null);
	}
	
	public void setNSuggestions(int number) {
		nSuggestions = number;
	}
	
	public int getNSuggestions() {
		return nSuggestions;
	}
	
	public void setTokenizer(CountTokens tokens) {
		tokenizer = tokens;
	}
	
	public CountTokens getTokenizer() {
		return tokenizer;
	}
	
	/* Command Line Interface operations*/
	
	/**
	 * Will print sections 1 and 2 of refactoring suggestions
	 * to the command line
	 * 
	 * 
	 * Refactoring suggestions printed to command line
	 */
	public void printSuggestions() {
		// Section 1 of refactoring suggestions
		System.out.println("Files scanned:");
		for(File file: this.getSourceFiles()) {
			tokenizer.setText(file.getAbsolutePath());
			tokenizer.Counting();
			System.out.println("    " + file.getPath() + ", " + tokenizer.getNumOfTokens());	
		}
	}
	
	/**
	 * Read the properties file for key-value pairs, and update 
	 * appropriate variable values within Refactoring.
	 * 
	 * Refactoring variables updated
	 */
	public void readPropertiesFile() { 
		
	}
	
	/**
	 * Read command line arguments passed from main and create Files 
	 * types from arguments.
	 * @throws IOException 
	 *  
	 * @param args command line arguments passed from main
	 * File objects added to sourceFiles list and pFile set
	 *  from command line arguments
	 */
	public void parseParameters(String[] args) throws IOException {
		
		// Get nSuggestions
		this.setNSuggestions(Integer.parseInt(args[0]));
		
		if(args.length > 1) {
			// Get property file
			File pFile = new File(args[1]);
			propertiesFile propFile = new propertiesFile(pFile);
			this.setPropertiesFile(propFile);
			this.getPropertiesFile().readPropertyFile();
		}
		
		// Get files/directories
		for (int argsIndex = 2; argsIndex < args.length; argsIndex++)
		{
			this.findSourceFiles(args[argsIndex]);
		}
	}
	
	/**
	 * Called by parseParameters, take command line argument and searches 
	 * for source files to add to list of source files.
	 * 
	 * @param argument file path String
	 * 
	 * recursively retrieves all source files within given directory
	 * and adds them to sourceFiles
	 */
	public void findSourceFiles(String argument) {
		File sourceDir = new File(argument);
		sourceDir = sourceDir.getAbsoluteFile();
		// Create collection of all files found
		Vector<File> allFiles = this.searchDirectories(sourceDir);
		
		// trim list to only include source files
		List<String> extensions = new ArrayList<String>();
		
		extensions = getPropertiesFile().getFileExtensions();
		
		for( File file: allFiles) {
			for( String ext: extensions) {
				if( file.getName().endsWith(ext)) {
					addSourceFile(file);
				}
			}
		}
	}
	
	/**
	 * Recursively searches directory structure to find all files
	 * 
	 * @param argument File/directory to search
	 * @return foundFiles array of found Files within directory
	 * and sub-directories
	 */
	public Vector<File> searchDirectories(File argument) {
		
		Vector<File> foundFiles = new Vector<File>();
		Vector<File> tempFiles = new Vector<File>();
		
		// Find all files within given directory and sub directory
		if( argument.isDirectory()) {
			String[] foundDir = argument.list();
			for(String path: foundDir) {
				String absPath = argument.getAbsolutePath() + '/' + path;
				File dir = new File(absPath);
				if(dir.isDirectory()) {
					tempFiles =  this.searchDirectories(dir);
					for( File file: tempFiles) {
						foundFiles.add(file);
					}
				}
			}
			for(File file: argument.listFiles()) {
				foundFiles.add(file);
			}
			return foundFiles;
		}
		else {
			foundFiles.add(argument);
		}
		
		return foundFiles;
	}
	
	/**
	 * Outputs Refactoring Suggestions to the command line interface
	 * 
	 * Sections 1 and 2 of output is displayed. 
	 */
	public void display() {
		// Section 1
		System.out.println("Files Scanned:");
		for( Refactoring r: refactor) {
			System.out.println("\t" + r.getSourceFile().getAbsolutePath() +
								   ", " + r.getTokenCount());
		}
	}
	
	/**
	 *  Compares command line objects (There shouldn't be another CommandLine
	 *  object)
	 *  
	 *  @return true if objects are equivalent
	 */
	@Override 
	public boolean equals(Object obj) {
		if (obj.hashCode() == this.hashCode())
		{		
			return true;
		}
		
	   	return false;
	}
	
	/**
	 * Renders the CommandLine object as a string containing all fields
	 * 
	 */
	@Override
	public String toString() {
		return "CommandLine [sourceFiles=" + sourceFiles + ", pFile=" + pFile +
				"," + " refactor=" + refactor + "]";
	}
	
	/**
	 * Returns the hashCode for this CommandLine
	 * 
	 * @return hashCode integer for CommandLine
	 */
	@Override
	public int hashCode() {
		int sourceFileHash = 0;
		
		for( File sourceFile: this.getSourceFiles()) {
			sourceFileHash = sourceFileHash + sourceFile.hashCode() * 11;
		}
		
		return sourceFileHash + pFile.hashCode() * 13 + refactor.hashCode() * 17;
	}
}