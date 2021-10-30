package edu.odu.cs.cs350;

import java.util.List;


/*
// @author cs_alee035
//
*/

public class CommandLine {
	
	private List<Files> sourceFiles;
	private Files pFile;
	private Refactoring refactor;
	
	/* Getters and Setters */
	
	/**
	 * returns a list of source files
	 * @return sourceFiles list of sourceFiles
	 */
	public List<Files> getSourceFiles() {
		return sourceFiles;
	}
	
	/**
	 * Add source file to list
	 * @param file source file to add to sourceFiles
	 */
	public void addSourceFile(Files file) {
		
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
	 * Read command line arguments and create Files types from
	 * arguments.
	 *  
	 * @post File objects added to sourceFiles list and pFile set
	 *  from command line arguments
	 */
	public void parseParameters() {
		
	}
	
	public void findSourceFiles() {
		
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