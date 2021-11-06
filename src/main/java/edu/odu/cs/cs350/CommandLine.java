package edu.odu.cs.cs350;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

import java.io.File;
import java.io.FilenameFilter;

/*
// @author cs_alee035
//
*/

public class CommandLine {
	
	private List<Files> sourceFiles;
	private Files pFile;
	private Refactoring refactor;
	private CountTokens tokenizer;
	
	/* Constructors */
	
	/**
	 * Create Empty CommandLine object with no source files, 
	 * properties file, or Refactoring object
	 */
	CommandLine() {
		sourceFiles = new ArrayList<Files>();
		pFile = new Files();
		refactor = new Refactoring();
		tokenizer = new CountTokens();
	}
	
	/* Getters and Setters */
	
	/**
	 * returns a list of source files
	 * @return sourceFiles list of sourceFiles
	 */
	public List<Files> getSourceFiles() {
		return sourceFiles;
	}
	
	/**
	 * Add source file to list if not already accounted for
	 * 
	 * @param file source file to add to sourceFiles
	 * 
	 * @post new source file is added to sourceFiles
	 */
	public void addSourceFile(Files file) {
		if (!(sourceFiles.contains(file))) {
			sourceFiles.add(file);
		}
	}
	
	/**
	 *  retrieve properties file
	 * @return pFile the properties file 
	 */
	public Files getPropertiesFile() {
		return pFile;
	}
	
	/**
	 *  set user provided properties file as pFile
	 *  
	 *  @post set pFile as properties file
	 */
	public void setPropertiesFile(Files propFile) {
		pFile = propFile;
	}
	
	/**
	 * retrieve the Refactoring object
	 * @return refactor Refactoring object
	 */
	public Refactoring getRefactor( ) {
		return refactor;
	}
	
	/* Command Line Interface operations*/
	
	/**
	 * Will print sections 1 and 2 of refactoring suggestions
	 * to the command line
	 * 
	 * 
	 * @post Refactoring suggestions printed to command line
	 */
	public void printSuggestions() {
		// Section 1 of refactoring suggestions
		System.out.println("Files scanned:");
		for(Files file: this.getSourceFiles()) {
			tokenizer.setText(file.contentToString(file.getPath()));
			tokenizer.Counting();
			System.out.println("    " + file.getPath() + ", " + tokenizer.getNumOfTokens());	
		}
	}
	
	/**
	 * Read the properties file for key-value pairs, and update 
	 * appropriate variable values within Refactoring.
	 * 
	 * @post Refactoring variables updated
	 */
	public void readPropertiesFile() { 
		
	}
	
	/**
	 * Read command line arguments passed from main and create Files 
	 * types from arguments.
	 *  
	 * @parameter args command line arguments passed from main
	 * @post File objects added to sourceFiles list and pFile set
	 *  from command line arguments
	 */
	public void parseParameters(String[] args) {
		/* The extensions list will be replaced by given source file 
		   extensions from propertiesFile once that unit is ready 
		   Default is .cpp, .h
		*/
	}
	/**
	 * Called by parseParameters, take command line argument and searches 
	 * for source files to add to list of source files.
	 * 
	 * @parameter argument file path String
	 * @parameter pFile Files containing source file extensions
	 * 
	 * @post recursively retrieves all source files within given directory
	 * and adds them to sourceFiles
	 */
	public void findSourceFiles(String argument) {
		File sourceDir = new File(argument);
		// Create collection of all files found
		Vector<File> allFiles = this.searchDirectories(sourceDir);
		
		// trim list to only include source files
		List<String> extensions = new ArrayList<String>();
		
		for( String ext: pFile.getFileExtensions() )
	    {
			extensions.add(ext);
		} 
		
		for( File file: allFiles) {
			for( String ext: extensions) {
				if( file.getName().endsWith(ext)) {
					Files source = new Files(file.getAbsolutePath());
					addSourceFile(source);
				}
			}
		}
	}
	
	/**
	 * Recursively searches directory structure to find all files
	 * 
	 * @param argument
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
		
		return foundFiles;
	}
	
	/**
	 *  Compares command line objects (There shouldn't be another CommandLine
	 *  object)
	 *  
	 *  @returns true if objects are equivalent
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
	 * @returns hashCode integer for CommandLine
	 */
	@Override
	public int hashCode() {
		int sourceFileHash = 0;
		
		for( Files sourceFile: this.getSourceFiles()) {
			sourceFileHash = sourceFileHash + sourceFile.hashCode() * 11;
		}
		
		return sourceFileHash + pFile.hashCode() * 13 + refactor.hashCode() * 17;
	}
	
}